package br.com.stoom.store.exception;

public enum ERRORS {
    PRODUCT_NOT_FOUND(404, "Product not found with id: "),
    PRODUCT_SKU_ALREADY_EXISTS(400, "Product SKU already exists"),
    PRODUCT_SKU_NOT_FOUND(404, "Product SKU not found"),
    PRODUCT_SKU_REQUIRED(400, "Product SKU is required"),
    PRODUCT_NAME_REQUIRED(400, "Product name is required"),
    PRODUCT_BRAND_REQUIRED(400, "Product brand is required"),
    PRODUCT_CATEGORY_REQUIRED(400, "Product category is required"),
    PRODUCT_PRICE_REQUIRED(400, "Product price is required"),
    PRODUCT_IS_ACTIVE_REQUIRED(400, "Product isActive is required"),
    PRODUCT_ID_REQUIRED(400, "Product id is required"),
    PRODUCT_ID_NOT_FOUND(404, "Product id not found"),
    PRODUCT_ID_INVALID(400, "Product id is invalid"),
    PRODUCT_SKU_INVALID(400, "Product SKU is invalid"),
    PRODUCT_NAME_INVALID(400, "Product name is invalid"),
    PRODUCT_BRAND_INVALID(400, "Product brand is invalid"),
    PRODUCT_CATEGORY_INVALID(400, "Product category is invalid"),
    PRODUCT_PRICE_INVALID(400, "Product price is invalid"),
    PRODUCT_IS_ACTIVE_INVALID(400, "Product isActive is invalid");


    private final int code;
    private final String message;

    ERRORS(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
