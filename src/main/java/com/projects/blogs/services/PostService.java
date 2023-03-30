package com.projects.blogs.services;

import java.util.List;

import com.projects.blogs.bean.PostBean;
import com.projects.blogs.entity.PostEntity;
import com.projects.blogs.utils.PostResponse;

public interface PostService {
	PostBean createPost(PostBean postBean,Integer userId,Integer categoryId);
	PostBean updatePost(PostBean postBean,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	PostBean getPostById(Integer postId);
	List<PostBean> getPostsByCategory(Integer categoryId);
	List<PostBean> getPostsByUser(Integer userId);
	List<PostBean> searchPosts(String keyword);
}
