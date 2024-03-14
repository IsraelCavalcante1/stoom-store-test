package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.com.stoom.store.exception.ERRORS.PRODUCT_NOT_FOUND;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll(Boolean isOnlyActive) {
        if (isOnlyActive) {
            return productRepository.findAllByAndIsActive(isOnlyActive);
        }
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException(HttpStatus.NOT_FOUND.value(), PRODUCT_NOT_FOUND.getMessage() + id));
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductException(HttpStatus.NOT_FOUND.value(), PRODUCT_NOT_FOUND.getMessage() + id);
        }
    }

    @Override
    public Product update(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElse(null);

        if (productToUpdate != null) {
            productToUpdate.setSku(product.getSku());
            productToUpdate.setName(product.getName());
            productToUpdate.setBrand(product.getBrand());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setIsActive(product.getIsActive());
            return productRepository.save(productToUpdate);
        }
        throw new ProductException(HttpStatus.NOT_FOUND.value(), PRODUCT_NOT_FOUND.getMessage() + id);
    }

    @Override
    // ProductBO.java
    public Product updatePartial(Long id, Product update) {
        Product productToUpdate = productRepository.findById(id).orElse(null);

        if (productToUpdate != null) {
            BeanUtils.copyProperties(update, productToUpdate, ignoreNullPropertyUpdate(update));
            return productRepository.save(productToUpdate);
        }
        throw new ProductException(HttpStatus.NOT_FOUND.value(), PRODUCT_NOT_FOUND.getMessage() + id);
    }

    @Override
    public List<Product> findByBrand(String brand, Boolean isActive) {
        if (isActive) {
            return productRepository.findByBrandAndIsActive(brand, isActive);
        }
        return productRepository.findByBrand(brand);
    }

    private String[] ignoreNullPropertyUpdate(Product source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @Override
    public List<Product> findByCategory(String category, Boolean isActive) {
        if (isActive) {
            return productRepository.findByCategoryAndIsActive(category, isActive);
        }
        return productRepository.findByCategory(category);
    }
}
