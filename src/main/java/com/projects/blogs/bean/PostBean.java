package com.projects.blogs.bean;

import java.util.Date;

import com.projects.blogs.entity.CategoryEntity;
import com.projects.blogs.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostBean {
	private Integer postId;
	private String title;

	private String content;
	private String imageName;
	private Date addedDate;
	

	private CategoryBean category;

	private UserBean user;
}
