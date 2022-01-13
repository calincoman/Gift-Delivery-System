package fileio.input;

import enums.Category;

/**
 * Class which holds the input data for a Gift
 * The input data is first loaded in this class when reading
 */
public final class GiftInputData {
    private String productName;
    private Double price;
    private Category category;
    private Integer quantity;

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
