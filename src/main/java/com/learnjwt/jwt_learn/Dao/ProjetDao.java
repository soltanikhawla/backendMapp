package com.learnjwt.jwt_learn.Dao;

import com.learnjwt.jwt_learn.Entity.Projet;
import com.learnjwt.jwt_learn.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface ProjetDao extends CrudRepository<Projet,Long> {
}
