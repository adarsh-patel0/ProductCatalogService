package com.example.ProductCatalogServiceProxyy.Services;

import com.example.ProductCatalogServiceProxyy.Models.Product;
import com.example.ProductCatalogServiceProxyy.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

//    public StorageProductService(ProductRepo productRepo){
//        productRepo = this.productRepo;
//    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(Long ProductId) {
        return null;
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
}
