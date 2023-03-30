package com.projects.blogs.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projects.blogs.bean.PostBean;
import com.projects.blogs.entity.CategoryEntity;
import com.projects.blogs.entity.PostEntity;
import com.projects.blogs.entity.UserEntity;
import com.projects.blogs.exceptions.ResourceNotFoundException;
import com.projects.blogs.repositories.CategoryRepo;
import com.projects.blogs.repositories.PostRepo;
import com.projects.blogs.repositories.UserRepo;
import com.projects.blogs.utils.PostResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostBean createPost(PostBean postBean,Integer userId,Integer categoryId) {
		UserEntity user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		CategoryEntity category=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		PostEntity post= modelMapper.map(postBean,PostEntity.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		PostEntity savedPost=postRepo.save(post);
		return modelMapper.map(savedPost, PostBean.class);
		
	
	}

	@Override
	public PostBean updatePost(PostBean postBean, Integer postId) {
		PostEntity postEnt=modelMapper.map(postBean, PostEntity.class);
		PostEntity post=postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		post.setContent(postEnt.getContent());
		post.setTitle(postEnt.getTitle());
		post.setImageName(postEnt.getImageName());
		PostEntity savedPost=postRepo.save(post);
		return modelMapper.map(savedPost, PostBean.class);
	}

	@Override
	public void deletePost(Integer postId) {
		PostEntity post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
//		List<PostEntity> postList=postRepo.findAll();
//		return postList.stream().map((post)->modelMapper.map(post, PostBean.class)).collect(Collectors.toList());
//		int pageSize=5;
//		int pageNumber=1;
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("Asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		PostResponse postResponse=new PostResponse();
		System.out.println("page"+pageNumber+pageSize);
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		System.out.println(p);
		Page<PostEntity> pagePost = this.postRepo.findAll(p);
		System.out.println(pagePost);
		List<PostEntity> postList=pagePost.getContent();
		List<PostBean> posts=postList.stream().map((post)->modelMapper.map(post, PostBean.class)).collect(Collectors.toList());
		postResponse.setContent(posts);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
//		List<PostEntity> postList= postRepo.findAll();
		return postResponse;
	}

	@Override
	public PostBean getPostById(Integer postId) {
		PostEntity post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		return modelMapper.map(post, PostBean.class);
	}

	@Override
	public List<PostBean> getPostsByCategory(Integer categoryId) {
		CategoryEntity category=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		List<PostEntity> posts=postRepo.findByCategory(category);
		return posts.stream().map((post)->modelMapper.map(post, PostBean.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostBean> getPostsByUser(Integer userId) {
		UserEntity user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		List<PostEntity> posts=postRepo.findByUser(user);
		return posts.stream().map((post)->modelMapper.map(post, PostBean.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostBean> searchPosts(String keyword) {
		List<PostEntity> posts=postRepo.searchByTitleKeyword(keyword);
		return posts.stream().map((post)->modelMapper.map(post, PostBean.class)).collect(Collectors.toList());
	}

}
