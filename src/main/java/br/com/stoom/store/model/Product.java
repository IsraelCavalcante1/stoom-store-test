package br.com.stoom.store.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(description = "Detalhes sobre o produto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(notes = "SKU do produto, não pode ser nulo", required = true, position = 1)
    @Column (name = "sku", nullable = false, unique = true)
    private String sku;

    @ApiModelProperty(notes = "Nome do produto, não pode ser nulo", required = true, position = 2)
    @Column(name = "name", nullable = false)
    private String name;
    @ApiModelProperty(notes = "Marca do produto, não pode ser nulo", required = true, position = 3)

    @Column(name = "brand", nullable = false)
    private String brand;
    @ApiModelProperty(notes = "Categoria do produto, não pode ser nulo", required = true, position = 4)

    @Column(name = "category", nullable = false)
    private String category;
    @ApiModelProperty(notes = "Preço do produto, não pode ser nulo", required = true, position = 5)

    @Column(name = "price", nullable = false)
    private Double price;
    @ApiModelProperty(notes = "Indica se o produto está ativo, não pode ser nulo", required = true, position = 6)

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean IsActive) {
        isActive = IsActive;
    }

}