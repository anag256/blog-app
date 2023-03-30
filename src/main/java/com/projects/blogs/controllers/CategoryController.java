package com.projects.blogs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.blogs.bean.CategoryBean;
import com.projects.blogs.services.CategoryService;
import com.projects.blogs.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	@PostMapping("/")
	public ResponseEntity<CategoryBean> createCategory(@Valid @RequestBody CategoryBean categoryBean){
			CategoryBean bean=	service.createCategory(categoryBean);
			return new ResponseEntity<CategoryBean>(bean, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryBean> updateCategory(@Valid @RequestBody CategoryBean categoryBean,@PathVariable("categoryId") Integer categoryId){
			CategoryBean bean=	service.updateCategory(categoryBean,categoryId);
			return new ResponseEntity<CategoryBean>(bean, HttpStatus.OK);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
			service.deleteCategory(categoryId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully",true), HttpStatus.OK);
	}
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryBean> getCategory(@PathVariable("categoryId") Integer categoryId){
		CategoryBean bean=service.getCategory(categoryId);
			return new ResponseEntity<CategoryBean>(bean, HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryBean>> getAllCategories(){
		List<CategoryBean> beanList=service.getCategories();
			return new ResponseEntity<List<CategoryBean>>(beanList, HttpStatus.OK);
	}
}
