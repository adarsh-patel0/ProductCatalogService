package com.example.ProductCatalogServiceProxyy.Controller;

import com.example.ProductCatalogServiceProxyy.Dtos.ProductDto;
import com.example.ProductCatalogServiceProxyy.Models.Category;
import com.example.ProductCatalogServiceProxyy.Models.Product;
import com.example.ProductCatalogServiceProxyy.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) {
        try {
            if(productId<1){
                throw new IllegalArgumentException("product id is incorrect");
            }
            Product product =  productService.getProduct(productId);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") @RequestBody Long ProductId, ProductDto productDto) {
        Product product1 = getProduct(productDto);
        return productService.updateProduct(ProductId,product1);
    }

    public Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }


}
