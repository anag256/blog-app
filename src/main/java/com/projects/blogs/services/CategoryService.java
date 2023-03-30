package com.projects.blogs.services;

import java.util.List;

import com.projects.blogs.bean.CategoryBean;

public interface CategoryService {
	 CategoryBean createCategory(CategoryBean categoryBean);
	 CategoryBean updateCategory(CategoryBean categoryBean,Integer categoryId);
	 void deleteCategory(Integer categoryId);
	 CategoryBean getCategory(Integer categoryId);
	 List<CategoryBean> getCategories();
}
