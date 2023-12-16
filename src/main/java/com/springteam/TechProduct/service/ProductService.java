package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

     Page<Product> getPaggingProduct(Pageable pageable);

     List<Product> getProductByName(String name, Pageable pageable);

     Product getProductById(int id);

     void insertNewProduct(Product product, MultipartFile file) throws IOException;

     void updateProductById(int id, Product newProduct, MultipartFile file);

     void deleteProductById(int id);
}
