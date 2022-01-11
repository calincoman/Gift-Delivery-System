package distribution.shipment;

import enums.Category;
import fileio.input.GiftInputData;

/**
 * Class representing a gift
 */
public class Gift extends Product {
    private final Category category;
    private Integer quantity;

    /**
     * Constructor creating a Gift object from the input data
     */
    public Gift(final GiftInputData giftInputData) {
        super(giftInputData);
        this.category = giftInputData.getCategory();
        this.quantity = giftInputData.getQuantity();
    }

    public void decreaseQuantity() {
        --quantity;
    }

    public final Category getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
