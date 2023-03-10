package com.learnjwt.jwt_learn.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learnjwt.jwt_learn.Entity.ImageModel;
import com.learnjwt.jwt_learn.Entity.Product;
import com.learnjwt.jwt_learn.Service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = { "/addnewProduct" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Product addProduct(@RequestPart("product") Product product,
            @RequestPart("imagefile") MultipartFile[] file) {
        // return productService.addnewProduct(product);
        try {
            Set<ImageModel> images= uploadImage(file);
            product.setProductImages(images);
            return productService.addnewProduct(product);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imageModels.add(imageModel);
        }

        return imageModels;
    }
}
