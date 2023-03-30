package com.projects.blogs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.blogs.entity.CategoryEntity;
import com.projects.blogs.entity.PostEntity;
import com.projects.blogs.entity.UserEntity;

public interface PostRepo extends JpaRepository<PostEntity, Integer> {
	List<PostEntity> findByUser(UserEntity user);
	List<PostEntity> findByCategory(CategoryEntity user);
	
	@Query("select k from PostEntity k where k.title like %:key%")
	List<PostEntity> searchByTitleKeyword(@Param("key") String title);
}
