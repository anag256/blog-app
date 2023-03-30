package com.projects.blogs.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryBean {
	private Integer CategoryId;
	@NotBlank
	@Size(min=4)
	private String categoryTitle;
	@NotBlank
	@Size(min=10)
	private String categoryDescription;
}
