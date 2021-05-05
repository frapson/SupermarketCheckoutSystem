package checkoutSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutController {


//private ObservableList<Item> listOfItems;

    private itemList listOfItems;
    private itemList basketItems;

    @FXML
    public ListView<Item> itemList;

    @FXML
    private ListView<Item> basket;

    @FXML
    public TextField TFscanItems;

    @FXML
    public TextField TFtotalAmount;

    public Button scanButton;

    public Button deleteItemButton;

    public Button payButton;


    @FXML
    public void initialize() {

        listOfItems = new itemList();
        listOfItems.addItem("0001", "Polena Rex - proszek", 25);
        listOfItems.addItem("0002", "Polena Rex2 - proszek", 25);
        listOfItems.addItem("0003", "Polena Rex3 - proszek", 25);
        listOfItems.addItem("0004", "Polena Rex4 - proszek", 25);
        itemList.setItems(listOfItems);

        basketItems = new itemList();

    }



    @FXML
    private void scanButtonPressed() {

        String findItem = TFscanItems.getText();
        Item wantedItem;

        if (findItem.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Enter Product Code", ButtonType.OK);
            alert.showAndWait();
        } else {

            wantedItem = listOfItems.findItemByCode(findItem);

            if (wantedItem == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Item Not Found", ButtonType.OK);
                alert.showAndWait();

            } else {
                basketItems.addItem(wantedItem.getProductCode(), wantedItem.getDescription(),
                        wantedItem.getItemPrice());
                basket.setItems(basketItems);
                TFscanItems.clear();


                TFtotalAmount.setText(addCurrency(calculatePrice(basketItems)));
            }
        }

    }

    @FXML
    private void deleteButtonPressed() {

        int index = basket.getSelectionModel().getSelectedIndex();

        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You have to select the item", ButtonType.OK);
            alert.showAndWait();
        } else {

            basketItems.remove(index);
            TFtotalAmount.setText(addCurrency(calculatePrice(basketItems)));

        }

    }



    //calculates total price of items
    public int calculatePrice(itemList list){
        
        int sum = 0;

        for (Item i : list) {

            sum += i.getItemPrice();
            
        }

        return sum;
        
    }


    //https://stackoverflow.com/questions/2379221/java-currency-number-format
    public String addCurrency(int money){

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);

        return nf.format(money);

    }



}
