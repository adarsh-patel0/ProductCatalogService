package com.example.ProductCatalogServiceProxyy.Repository;

import com.example.ProductCatalogServiceProxyy.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
}
