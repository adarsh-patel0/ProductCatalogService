package com.example.ProductCatalogServiceProxyy.Controller;

import com.example.ProductCatalogServiceProxyy.Models.Product;
import com.example.ProductCatalogServiceProxyy.Services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_getProducts_ReceivesSuccessfulResponse() throws Exception {
        //Arrange
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setTitle("IPhone12");
        Product product2 = new Product();
        product2.setTitle("MacBook");
        productList.add(product);
        productList.add(product2);
        when(productService.getProducts()).thenReturn(productList);

        //object -> JSON -> STRING
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("IPhone12"));

    }

    @Test
    public void Test_getProductById_ReceivesSuccessfulResponse() throws Exception {
        // Arrange
        Product product = new Product();
        product.setId(1000L);
        product.setTitle("IPhone12");
        product.setDescription("Latest model");

        when(productService.getProduct(1000L)).thenReturn(product);

        mockMvc.perform(get("/product/{id}", 1000L))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)))
                .andExpect(jsonPath("$.id").value(1000L))
                .andExpect(jsonPath("$.title").value("IPhone12"))
                .andExpect(jsonPath("$.description").value("Latest model"));
    }

    @Test
    public void Test_createProduct_ReceivesSuccessfulResponse() throws Exception {
        Product productToCreate = new Product();
        productToCreate.setTitle("Orange");
        productToCreate.setDescription("Freshy and Juicy");

        Product expectedProduct = new Product();
        expectedProduct.setId(1000L);
        expectedProduct.setTitle("Orange");
        expectedProduct.setDescription("Freshy and Juicy");

        when(productService.createProduct(any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)));
//                .andExpect(jsonPath("$.length()").value(10))
//                .andExpect(jsonPath("$.title").value("Orange"));

    }

    @Test
    public void Test_updateProduct_ReceivesSuccessfulResponse()  throws Exception{

        Product ExistingProduct = new Product();
        ExistingProduct.setId(1000L);
        ExistingProduct.setTitle("Orange");
        ExistingProduct.setDescription("Freshy and Juicy");

        Product updatedProduct = new Product();
        updatedProduct.setId(1000L);
        updatedProduct.setTitle("Apple");
        updatedProduct.setDescription("Red and Juicy");

        Product ExpectedProduct = new Product();
        ExpectedProduct.setId(1000L);
        ExpectedProduct.setTitle("Apple");
        ExpectedProduct.setDescription("Red and Juicy");

        when(productService.updateProduct(any(Long.class),any(Product.class))).thenReturn(ExpectedProduct);

        mockMvc.perform(patch("/product/{id}",1000L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(ExpectedProduct)));

    }
}
