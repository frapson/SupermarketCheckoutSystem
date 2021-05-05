package checkoutSystem;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class itemList extends ObservableListWrapper<Item> {

    public itemList() {
        super(FXCollections.observableArrayList());
    }

    public void addItem(String productCode,
                        String description,
                        Integer price){

        super.add(new Item(productCode, description, price));

    }

    //search for an item by a product code
    public Item findItemByCode(String code){

        Item temp;
        int index = -1;

        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);

            if (temp.getProductCode().equals(code)){
                index = i;
                break;
            }
        }

        if (index == -1) {
            return null;
        } else {
            return super.get(index);
        }
    }





}

