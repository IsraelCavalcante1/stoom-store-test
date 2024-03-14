package br.com.stoom.store;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class StoreApplicationTests {

	@InjectMocks
	private ProductBO productBO;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {
		Product product1 = new Product();
		product1.setId(1L);
		product1.setIsActive(true);

		Product product2 = new Product();
		product2.setId(2L);
		product2.setIsActive(true);

		when(productRepository.findAllByAndIsActive(true)).thenReturn(Arrays.asList(product1, product2));

		List<Product> products = productBO.findAll(true);

		assertEquals(2, products.size());
		verify(productRepository, times(1)).findAllByAndIsActive(true);
	}

	@Test
	public void testFindById() {
		Product product = new Product();
		product.setId(1L);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Product foundProduct = productBO.findById(1L);

		assertEquals(product.getId(), foundProduct.getId());
		verify(productRepository, times(1)).findById(1L);
	}

	@Test
	public void testCreate() {
		Product product = new Product();
		product.setId(1L);

		when(productRepository.save(product)).thenReturn(product);

		Product createdProduct = productBO.create(product);

		assertEquals(product.getId(), createdProduct.getId());
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setId(1L);

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(productRepository.save(product)).thenReturn(product);

		Product updatedProduct = productBO.update(1L, product);

		assertEquals(product.getId(), updatedProduct.getId());
		verify(productRepository, times(1)).findById(1L);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testUpdateNotFound() {
		Product product = new Product();
		product.setId(1L);

		when(productRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ProductException.class, () -> productBO.update(1L, product));
		verify(productRepository, times(1)).findById(1L);
	}

	@Test
	public void testDelete() {
		Product product = new Product();
		product.setId(1L);

		when(productRepository.existsById(1L)).thenReturn(true);
		doNothing().when(productRepository).deleteById(1L);

		productBO.delete(1L);

		verify(productRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testFindByBrand() {
		Product product = new Product();
		product.setId(1L);
		product.setBrand("Brand1");
		product.setIsActive(true);

		when(productRepository.findByBrandAndIsActive("Brand1", true)).thenReturn(Arrays.asList(product));

		List<Product> products = productBO.findByBrand("Brand1", true);

		assertEquals(1, products.size());
		assertEquals("Brand1", products.get(0).getBrand());
		verify(productRepository, times(1)).findByBrandAndIsActive("Brand1", true);
	}

	@Test
	public void testFindByCategory() {
		Product product = new Product();
		product.setId(1L);
		product.setCategory("Category1");
		product.setIsActive(true);

		when(productRepository.findByCategoryAndIsActive("Category1", true)).thenReturn(Arrays.asList(product));

		List<Product> products = productBO.findByCategory("Category1", true);

		assertEquals(1, products.size());
		assertEquals("Category1", products.get(0).getCategory());
		verify(productRepository, times(1)).findByCategoryAndIsActive("Category1", true);
	}
	@Test
	public void testUpdatePartial() {
		Product existingProduct = new Product();
		existingProduct.setId(1L);
		existingProduct.setSku("SKU1");
		existingProduct.setName("Product Name 1");
		existingProduct.setBrand("Brand1");
		existingProduct.setCategory("Category1");
		existingProduct.setPrice(10.0);
		existingProduct.setIsActive(true);

		Product update = new Product();
		update.setId(1L);
		update.setSku(null); // This field should not be updated
		update.setName("Updated Product Name");
		update.setBrand(null); // This field should not be updated
		update.setCategory("Updated Category");
		update.setPrice(null); // This field should not be updated
		update.setIsActive(false);

		when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
		when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

		Product updatedProduct = productBO.updatePartial(1L, update);

		assertEquals(existingProduct.getId(), updatedProduct.getId());
		assertEquals(existingProduct.getSku(), updatedProduct.getSku()); // Should not be updated
		assertEquals(update.getName(), updatedProduct.getName());
		assertEquals(existingProduct.getBrand(), updatedProduct.getBrand()); // Should not be updated
		assertEquals(update.getCategory(), updatedProduct.getCategory());
		assertEquals(existingProduct.getPrice(), updatedProduct.getPrice()); // Should not be updated
		assertEquals(update.getIsActive(), updatedProduct.getIsActive());

		verify(productRepository, times(1)).findById(1L);
		verify(productRepository, times(1)).save(any(Product.class));
	}
}
