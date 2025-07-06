package com.example.ProductCatalogServiceProxyy.Repository;

import com.example.ProductCatalogServiceProxyy.Models.Category;
import com.example.ProductCatalogServiceProxyy.Models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    @Rollback(value = false)
    void demonstrateLoading(){
        Category category = categoryRepo.findById(2L).get();
        System.out.println(category.getName());
        List<Product> products = category.getProducts();
        for(Product product : products){
            System.out.println(product.getId());
        }
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void demonstrateNPlusOnePrblm(){
        List<Category> categories = categoryRepo.findAll();
        for(Category category : categories){
            List<Product> products = category.getProducts();
            if(!products.isEmpty()){
                System.out.println(products.get(0).getPrice());
            }
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void queryTest(){
        Product product = productRepo.findProductById(2L);
        List<Product> products = productRepo.findProductByPriceBetween(100D,1000D);
        String name = productRepo.getProductTitleFromId(1L);
        String xyz = productRepo.getCategoryNameFromProductId(2L);
                System.out.println("debug");
    }
}