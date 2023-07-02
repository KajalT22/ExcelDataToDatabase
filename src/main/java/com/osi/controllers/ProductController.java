package com.osi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.entities.Product;
import com.osi.helper.Helper;
import com.osi.services.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		if(Helper.checkExcelFormat(file)) {
			this.productService.save(file);
			return ResponseEntity.ok(Map.of("message","file is uploaded and data is saved to db"));
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
	}
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return this.productService.getAllproducts();
	}

}