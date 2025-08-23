package com.example.ProductCatalogServiceProxyy.Services;

import com.example.ProductCatalogServiceProxyy.Dtos.UserDto;
import com.example.ProductCatalogServiceProxyy.Models.Product;
import com.example.ProductCatalogServiceProxyy.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Service
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;
//
//    @Autowired
//    private RestTemplate restTemplate;

//    public StorageProductService(ProductRepo productRepo){
//        productRepo = this.productRepo;
//    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public Product getProduct(Long ProductId) {
        Product product = productRepo.findProductById(ProductId);
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        Product resultProduct = productRepo.save(product);
        return resultProduct;
    }

    @Override
    public Product updateProduct(Long ProductId, Product product) {
        return null;
    }

    @Override
    public Product getProductdetails(Long userId, Long productId) {
        RestTemplate restTemplate = new RestTemplate();
        Product product = productRepo.findProductById(productId);
        UserDto userDto = restTemplate.getForEntity("https://localhost:9000/users/{id}",UserDto.class,userId).getBody();
        System.out.println(userDto.getEmail());
        return product;
    }
}
