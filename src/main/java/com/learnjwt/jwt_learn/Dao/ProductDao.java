package com.learnjwt.jwt_learn.Dao;

import org.springframework.data.repository.CrudRepository;

import com.learnjwt.jwt_learn.Entity.Product;

public interface ProductDao extends CrudRepository<Product,Long>{
    
}
