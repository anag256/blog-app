package com.projects.blogs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.blogs.bean.CategoryBean;
import com.projects.blogs.entity.CategoryEntity;
import com.projects.blogs.exceptions.ResourceNotFoundException;
import com.projects.blogs.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryBean createCategory(CategoryBean categoryBean) {
		CategoryEntity cat = modelMapper.map(categoryBean, CategoryEntity.class);
		CategoryEntity savedCat = categoryRepo.save(cat);
		return modelMapper.map(savedCat, CategoryBean.class);
	}

	@Override
	public CategoryBean updateCategory(CategoryBean categoryBean, Integer categoryId) {
		CategoryEntity cat = modelMapper.map(categoryBean, CategoryEntity.class);
		CategoryEntity foundCat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
		foundCat.setCategoryTitle(cat.getCategoryTitle());
		foundCat.setCategoryDescription(cat.getCategoryDescription());
		CategoryEntity updatedCat = categoryRepo.save(foundCat);
		return modelMapper.map(updatedCat, CategoryBean.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepo.deleteById(categoryId);
	}

	@Override
	public CategoryBean getCategory(Integer categoryId) {
		CategoryEntity cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));;
		return modelMapper.map(cat, CategoryBean.class);
	}

	@Override
	public List<CategoryBean> getCategories() {
		List<CategoryEntity> catList=categoryRepo.findAll();
//		List<CategoryBean> catbeanList=new ArrayList<>();
//		catList.forEach((cat)->{
//		CategoryBean bean=modelMapper.map(cat, CategoryBean.class);
//			catbeanList.add(bean); 
//		});
//		return catbeanList;
	return	catList.stream().map((cat)->modelMapper.map(cat, CategoryBean.class)).collect(Collectors.toList());
		
	}

}
