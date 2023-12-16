package com.springteam.TechProduct.service;

import com.springteam.TechProduct.entity.Product;
import com.springteam.TechProduct.exception.FileNotFoundException;
import com.springteam.TechProduct.exception.ProductNotFoundException;
import com.springteam.TechProduct.exception.UserNotFoundException;
import com.springteam.TechProduct.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductServiceimpl implements ProductService{

    private ProductRepository productRepository;

    //inject product's repo to product's service
    @Autowired
    public ProductServiceimpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //get all product as Page type
    @Override
    public Page<Product> getPaggingProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }



    @Override
    public List<Product> getProductByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }


    //insert new product
    @Override
    @Transactional
    public void insertNewProduct(Product product, MultipartFile file)  throws IOException{

        if(uploadImages(file).length() > 0)
            product.setImageUrl(uploadImages(file));

        productRepository.save(product);

    }

    //delete product by id
    @Override
    @Transactional
    public void deleteProductById(int id) {

        //get existing product by id
        Product oldProduct = productRepository.findProductById(id).orElseThrow(
                () -> new ProductNotFoundException("not found product for id " + id));

        //delete product by oldProduct's id
        productRepository.deleteById(oldProduct.getId());
    }

    //delete all product
    public void deleteAllProduct(){
        productRepository.deleteAll();
    }

    //update product by given product's id
    @Override
    @Transactional
    public void updateProductById(int id, Product newProduct, MultipartFile file) {

        //get existing product by id
        Product oldProduct = productRepository.findProductById(id).orElseThrow(
                                                        () -> new ProductNotFoundException("not found product for id " + id));
        //get new images path
        String newImagesPath = uploadImages(file);

        //set new image path if exists new image
        if(newImagesPath.length() > 0)
            oldProduct.setImageUrl(newImagesPath);


        //validate input
        if(newProduct.getName() != null && !newProduct.getName().trim().equals("")
                                        && !newProduct.getName().equals(oldProduct.getName()))
            oldProduct.setName(newProduct.getName());

        if (newProduct.getPrice() > 0 && newProduct.getPrice() != oldProduct.getPrice())
            oldProduct.setPrice(newProduct.getPrice());

        if (newProduct.getColor() != null && !newProduct.getColor().trim().equals("")
                                          &&!newProduct.getColor().equals(oldProduct.getColor()))
            oldProduct.setColor(newProduct.getColor());

        //update
        productRepository.save(oldProduct);

    }

    //upload product's images
    private String uploadImages(MultipartFile file)  {

        //get images upload folder path
        String Path_Directory = "\\spring-beginer\\project\\TechProduct\\src\\main\\resources\\static\\images";

        //images format
        String [] imagesSuffixes = {".png", ".jpg"};

        //get original file name exam: pic1.png
        String originalFileName = file.getOriginalFilename();

        String fullPath = "";
        //validate images
        for (String suffixes: imagesSuffixes
        ) {
            //validate file from parameter not null and file format valid with images format
            if(originalFileName.contains(suffixes) && !file.isEmpty()){

                //get images file full path
                fullPath = Path_Directory + File.separator + originalFileName;

                //copy file to folder and return file full path
                try {
                    Files.copy(file.getInputStream(), Paths.get(fullPath),StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    originalFileName = "";
                }

            }
        }
        return fullPath;

    }


}
