package com.osi.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
                     
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.osi.entities.Product;

public class Helper {
	//check file is of excel type or not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		System.out.println(contentType);
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	//convert excel to list
	public static List<Product> convertExcelToList(InputStream ip){
		List<Product> list = new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(ip);
			XSSFSheet sheet = workbook.getSheet("data");
			
			int rowNumber=0;
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				if(rowNumber==0) {
					rowNumber++;
					continue;
				}
				
				//cell
				Iterator<Cell> cells = row.iterator();
				int cellId=0;
				
				//to store data
				Product product = new Product();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					switch (cellId) {
					case 0: {
						System.out.println(cell.getNumericCellValue());
						product.setProductId((int) cell.getNumericCellValue());
						break;
					}
					case 1:{
						System.out.println(cell.getStringCellValue());
						product.setProductName(cell.getStringCellValue());
						break;
					}
		
					case 2:
						product.setProductPrice(cell.getNumericCellValue());
						break;
					default:
						break;
					
					}
					
					cellId++;
					
				}
				list.add(product);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
