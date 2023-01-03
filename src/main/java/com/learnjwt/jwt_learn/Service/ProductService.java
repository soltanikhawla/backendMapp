package com.learnjwt.jwt_learn.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Dao.ProductDao;
import com.learnjwt.jwt_learn.Entity.Product;

@Service
public class ProductService {
    
    @Autowired
    private ProductDao productDao;


    public Product addnewProduct(Product product){
        return productDao.save(product);
    }
}
