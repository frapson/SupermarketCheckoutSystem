package checkoutSystem;

import java.io.Serializable;

public class Item implements Serializable {
    private String productCode;
    private String description;
    private String itemPrice;

    public Item(String productCode,
                String description,
                String itemPrice) {

        this.productCode = productCode;
        this.description = description;
        this.itemPrice = itemPrice;

    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return productCode + " "
                + description + " "
                + itemPrice;
    }
}
