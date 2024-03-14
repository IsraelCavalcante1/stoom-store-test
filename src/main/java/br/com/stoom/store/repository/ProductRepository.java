package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandAndIsActive(String brand, Boolean isActive);
    List<Product> findByBrand(String brand);
    List<Product> findByCategoryAndIsActive(String category, Boolean isActive);
    List<Product> findByCategory(String category);
    List<Product> findAllByAndIsActive(Boolean isActive);
    List<Product> findAll();
}