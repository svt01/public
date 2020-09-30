package com.openlibrary.all.books.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.openlibrary.all.books.controller.UserModel;
import com.openlibrary.all.books.entity.Book;
import com.openlibrary.all.books.entity.User;
import com.openlibrary.all.books.service.UserService;
import com.openlibrary.all.books.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private static Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	public UserModel create(UserModel user) {
		LOGGER.info("In createUser");
		User entity = populateUserEntity(new User(), user);
		User save = userRepository.save(entity);
		return populateEntityToModel(save);
	}

	@Override
	public void update(UserModel user) {
		LOGGER.info("In updateUser");
		User entity = userRepository.getOne(Long.valueOf(user.getId()));
		populateUserEntity(entity, user);
		userRepository.saveAndFlush(entity);

	}

	@Override
	public void delete(UserModel user) {
		LOGGER.info("In updateUser");
		userRepository.deleteById(Long.valueOf(user.getId()));

	}

	@Override
	public List<UserModel> find(UserModel user) {
		LOGGER.info("In findUser"+user);

		User findByUsername = userRepository.findByUsername(user.getUsername());
		UserModel populateEntityToModel = populateEntityToModel(
				findByUsername);
		ArrayList<UserModel> arrayList = new ArrayList<>();
		arrayList.add(populateEntityToModel);
		return arrayList;
	}

	@Override
	public List<UserModel> list() {

		List<UserModel> userLst = new ArrayList<>();
		List<User> findAll = userRepository.findAll();
		for (User user : findAll) {
			userLst.add(populateEntityToModel(user));
		}
		return userLst;
	}

	@Override
	public boolean isExists(UserModel user) {
		LOGGER.info("In isLogUserExists");
		return false;
	}

	private User populateUserEntity(User userEntity, UserModel user) {
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		userEntity.setFirstname(user.getFirstname());
		userEntity.setLastname(user.getLastname());

		return userEntity;
	}

	private UserModel populateEntityToModel(User user) {
		UserModel userModel = new UserModel();
		userModel.setId("" + user.getId());
		userModel.setUsername(user.getUsername());
		userModel.setPassword(user.getPassword());
		return userModel;
	}

	@Override
	public UserModel findOne(UserModel obj) {
		LOGGER.info("In findOne");
		User one = userRepository.getOne(Long.parseLong(obj.getId()));
		return (populateEntityToModel(one));
	}

}
