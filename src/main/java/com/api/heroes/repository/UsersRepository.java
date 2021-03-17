package com.api.heroes.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.heroes.model.User;

public interface UsersRepository extends CrudRepository<User, Long> {

}