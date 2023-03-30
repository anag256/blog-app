package com.projects.blogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.blogs.entity.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity,Integer> {

}
