package com.projects.blogs.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projects.blogs.bean.PostBean;
import com.projects.blogs.repositories.PostRepo;
import com.projects.blogs.services.FileService;
import com.projects.blogs.services.PostService;
import com.projects.blogs.utils.ApiResponse;
import com.projects.blogs.utils.AppConstants;
import com.projects.blogs.utils.PostResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fs;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostBean> createPost(@RequestBody PostBean postBean,
			@PathVariable("categoryId") Integer categoryId, @PathVariable("userId") Integer userId) {
		PostBean post = postService.createPost(postBean, userId, categoryId);
		return new ResponseEntity<PostBean>(post, HttpStatus.CREATED);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostBean> updatePost(@RequestBody PostBean postBean, @PathVariable("postId") Integer postId) {
		PostBean post = postService.updatePost(postBean, postId);
		return new ResponseEntity<PostBean>(post, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostBean>> getPostsByUser(@PathVariable("userId") Integer userId) {
		List<PostBean> posts = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostBean>>(posts, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostBean>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostBean> posts = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostBean>>(posts, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse posts = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostBean> getPostById(@PathVariable("postId") Integer postId) {
		PostBean post = postService.getPostById(postId);
		return new ResponseEntity<PostBean>(post, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully deleted", false), HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostBean>> getPostById(@PathVariable("keywords") String keywords) {
		List<PostBean> posts = postService.searchPosts(keywords);
		return new ResponseEntity<List<PostBean>>(posts, HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostBean> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException{
		System.out.println("in");
		PostBean post=postService.getPostById(postId);
		System.out.println(post);
		String fileName=fs.uploadImage(path, image);
		post.setImageName(fileName);
		PostBean updatedPost=postService.updatePost(post, postId);
		return new ResponseEntity<PostBean>(updatedPost, HttpStatus.OK);
	}

}
