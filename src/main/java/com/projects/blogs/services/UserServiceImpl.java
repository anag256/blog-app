package com.projects.blogs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.blogs.bean.UserBean;
import com.projects.blogs.entity.UserEntity;
import com.projects.blogs.exceptions.ResourceNotFoundException;
import com.projects.blogs.repositories.UserRepo;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserBean createUser(UserBean user) {
		UserEntity userEntity = this.beanToEntity(user);
		UserEntity savedUser=userRepo.save(userEntity);
		return EntityToBean(savedUser);
	}

	@Override
	public UserBean updateUser(UserBean user, Integer userId) {
		UserEntity foundUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
		foundUser.setEmail(user.getEmail());
		foundUser.setAbout(user.getAbout());
		foundUser.setName(user.getName());
		foundUser.setPassword(user.getPassword());
		UserEntity savedUser=userRepo.save(foundUser);
		return EntityToBean(savedUser);
	}

	@Override
	public List<UserBean> getAllUsers() {
		List<UserEntity> users=userRepo.findAll();
//		List<UserBean> userBeanlist=new ArrayList<UserBean>();
//		for(UserEntity user:users) {
//			UserBean bean=EntityToBean(user);
//			userBeanlist.add(bean);
//		}
		return users.stream().map((ent)->EntityToBean(ent)).collect(Collectors.toList());
		
		
	}

	@Override
	public void deleteUser(Integer userId) {
		 userRepo.deleteById(userId);
	}

	private UserEntity beanToEntity(UserBean userBean) {
//		BeanUtils.copyProperties(userBean, userEntity);
//		return userEntity;
//		UserEntity userEntity = new UserEntity();
//		userEntity.setId(userBean.getId());
//		userEntity.setName(userBean.getName());
//		userEntity.setEmail(userBean.getEmail());
//		userEntity.setAbout(userBean.getAbout());
//		userEntity.setPassword(userBean.getPassword());
//		return userEntity;
		UserEntity userEntity= modelMapper.map(userBean,UserEntity.class);
		return userEntity;
	}

	private UserBean EntityToBean(UserEntity userEntity) {
//		BeanUtils.copyProperties(userBean, userEntity);
//		return userEntity;
//		UserBean userBean = new UserBean();
//		userBean.setId(userEntity.getId());
//		userBean.setName(userEntity.getName());
//		userBean.setEmail(userEntity.getEmail());
//		userBean.setAbout(userEntity.getAbout());
//		userBean.setPassword(userEntity.getPassword());
//		return userBean;
		UserBean userBean= modelMapper.map(userEntity,UserBean.class);
		return userBean;
	}

	@Override
	public UserBean getUserById(Integer userId) {
		
		UserEntity foundUser = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user","id",userId));
		return EntityToBean(foundUser);
	}

}
