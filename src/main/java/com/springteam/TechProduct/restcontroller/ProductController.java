package com.springteam.TechProduct.restcontroller;

import com.springteam.TechProduct.dto.ProductResponeDTO;
import com.springteam.TechProduct.entity.Product;
import com.springteam.TechProduct.exception.FileNotFoundException;
import com.springteam.TechProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/crud")
public class ProductController {

    private ProductService productService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public ProductController(ProductService productService, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.productService = productService;
    }

    //get all product controller
    @GetMapping("/products")
    public ResponseEntity<ProductResponeDTO> getAllProduct(@RequestParam Optional<Integer> p) {

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = productService.getPaggingProduct(pageable);

        ProductResponeDTO productRespone = new ProductResponeDTO();

        productRespone.setData(page.get().toList());
        productRespone.setFirstPage(page.isFirst());
        productRespone.setLastPage(page.isLast());
        productRespone.setPageNumber(page.getNumber());
        productRespone.setPageSize(page.getSize());
        productRespone.setTotalElement(page.getTotalElements());

        return new ResponseEntity<>(productRespone, HttpStatus.OK);
    }

    //add product controller
    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addNewProduct(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "color") String color
    ) throws IOException {
        int integerPrice = Integer.parseInt(price);
        Product product = new Product(name, "", integerPrice, color);



        productService.insertNewProduct(product, file);

        return new ResponseEntity<>("create success", HttpStatus.CREATED);

    }

    //update product controller
    @PutMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "color") String color,
            @RequestParam(value = "file") MultipartFile file
    ) {

        int integerId = Integer.parseInt(id);
        int integerPrice = (price.equals("")) ? 0 : Integer.parseInt(price);

        Product newProduct = new Product(name, "", integerPrice, color);

        productService.updateProductById(integerId, newProduct, file);

        return new ResponseEntity<>("update success", HttpStatus.CREATED);
    }

    //delete product by id
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

        productService.deleteProductById(id);

        return new ResponseEntity<>("success delete product for id: " + id, HttpStatus.OK);
    }

}
