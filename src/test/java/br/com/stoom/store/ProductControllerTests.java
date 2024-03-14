package br.com.stoom.store;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductBO productBO;

    @Test
    public void testFindAll() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setIsActive(true);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setIsActive(true);

        List<Product> products = Arrays.asList(product1, product2);

        when(productBO.findAll(true)).thenReturn(products);

        mockMvc.perform(get("/api/products")
                        .param("isOnlyActive", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product 2")));
    }

    @Test
    public void testFindById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setIsActive(true);

        when(productBO.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")));
    }
    @Test
    public void testCreate() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setIsActive(true);

        when(productBO.create(any())).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Product 1\",\"isActive\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")));
    }

    @Test
    public void testUpdate() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Updated Product 1");
        product.setIsActive(true);

        when(productBO.update(eq(1L), (Product) any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Updated Product 1\",\"isActive\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Product 1")));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(productBO).delete(1L);

        mockMvc.perform(delete("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindByBrand() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setBrand("Brand1");
        product.setIsActive(true);

        List<Product> products = Arrays.asList(product);

        when(productBO.findByBrand("Brand1", true)).thenReturn(products);

        mockMvc.perform(get("/api/products/brand/Brand1")
                        .param("isOnlyActive", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("Brand1")));
    }

    @Test
    public void testFindByCategory() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setCategory("Category1");
        product.setIsActive(true);

        List<Product> products = Arrays.asList(product);

        when(productBO.findByCategory("Category1", true)).thenReturn(products);

        mockMvc.perform(get("/api/products/category/Category1")
                        .param("isOnlyActive", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].category", is("Category1")));
    }
}