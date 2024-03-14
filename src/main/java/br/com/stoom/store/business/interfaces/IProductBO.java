package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Product;

import java.util.List;
import java.util.Map;

public interface IProductBO  {

    List<Product> findAll(Boolean isOnlyActive);

    Product findById(Long id);

    void delete(Long id);

    Product create(Product product);

    Product update(Long id, Product product);

    List<Product> findByBrand(String brand, Boolean isActive);

    List<Product> findByCategory(String category, Boolean isActive);

    Product updatePartial(Long id, Product update);
}
