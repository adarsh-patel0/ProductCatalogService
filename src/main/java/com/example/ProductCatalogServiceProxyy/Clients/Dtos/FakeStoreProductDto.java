package com.example.ProductCatalogServiceProxyy.Clients.Dtos;

import com.example.ProductCatalogServiceProxyy.Models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FakeStoreProductDto  {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private String imageUrl;

    private String category;

    private FakeStoreProductDto fakeStoreProductDto;
}
