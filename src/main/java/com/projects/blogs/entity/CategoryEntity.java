package com.projects.blogs.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="category")
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer CategoryId;
	
	@Column(name="title",length=100,nullable=false)
	private String categoryTitle;
	@Column(name="decsription")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<PostEntity> posts=new ArrayList<>();
}
