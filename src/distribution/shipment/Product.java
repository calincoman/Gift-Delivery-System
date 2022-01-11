package distribution.shipment;

import fileio.input.GiftInputData;

/**
 * Abstract class which defines a product
 */
public abstract class Product {
    private final String productName;
    private final Double price;

    /**
     * Constructor which creates a Product object from the gift input data
     */
    public Product(final GiftInputData giftInputData) {
        this.productName = giftInputData.getProductName();
        this.price = giftInputData.getPrice();
    }

    public final String getProductName() {
        return productName;
    }

    public final Double getPrice() {
        return price;
    }
}
