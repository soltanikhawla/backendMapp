package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.repository.CrudRepository;

import com.learnjwt.jwt_learn.Entity.User;

public interface UserDao extends CrudRepository<User,String>{
    
}
