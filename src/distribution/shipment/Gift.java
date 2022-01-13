package distribution.shipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Category;
import fileio.input.GiftInputData;

/**
 * Class representing a gift
 */
public final class Gift extends Product {
    private final Category category;
    // ignore this field on Jackson serialization
    @JsonIgnore
    private Integer quantity;

    /**
     * Constructor creating a Gift object from the input data
     */
    public Gift(final GiftInputData giftInputData) {
        super(giftInputData);
        this.category = giftInputData.getCategory();
        this.quantity = giftInputData.getQuantity();
    }

    /**
     * Decreases quantity of gift by 1
     */
    public void decreaseQuantity() {
        --quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
