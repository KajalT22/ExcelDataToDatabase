package com.osi.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.osi.entities.Product;
import com.osi.helper.Helper;
import com.osi.repositories.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public void save(MultipartFile file) {
		try {
			List<Product> products = Helper.convertExcelToList(file.getInputStream());
			this.productRepo.saveAll(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllproducts(){
		return this.productRepo.findAll();
	}
	

}
