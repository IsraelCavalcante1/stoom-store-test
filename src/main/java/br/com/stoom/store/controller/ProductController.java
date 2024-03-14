package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(@RequestParam(required = false, defaultValue = "true") Boolean isOnlyActive ) {
        List<Product> products = productService.findAll(isOnlyActive);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updatePartial(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updatePartial(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> findByBrand(@PathVariable String brand, @RequestParam(required = false, defaultValue = "true") Boolean isOnlyActive) {
        List<Product> products = productService.findByBrand(brand, isOnlyActive);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category, @RequestParam(required = false, defaultValue = "true") Boolean isOnlyActive) {
        List<Product> products = productService.findByCategory(category, isOnlyActive);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}