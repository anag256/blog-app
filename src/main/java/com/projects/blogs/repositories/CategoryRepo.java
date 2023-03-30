package com.projects.blogs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.blogs.bean.CategoryBean;
import com.projects.blogs.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer>{
//
//	 CategoryBean createCategory(CategoryBean categoryBean);
//	 CategoryBean updateCategory(CategoryBean categoryBean,Integer categoryId);
//	 void deleteCategory(Integer categoryId);
//	 CategoryBean getCategory(Integer categoryId);
//	 List<CategoryBean> getCategories();
}
