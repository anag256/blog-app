package com.projects.blogs.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.projects.blogs.bean.PostBean;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	private List<PostBean> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	
}
