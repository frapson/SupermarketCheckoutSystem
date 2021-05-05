package checkoutSystem;

import java.io.Serializable;

public class Item implements Serializable {
    private String productCode;
    private String description;
    private Integer itemPrice;

    public Item(String productCode,
                String description,
                Integer itemPrice) {

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

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return productCode + " "
                + description + " "
                + itemPrice;
    }
}
