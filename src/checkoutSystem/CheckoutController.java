package checkoutSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutController {


    public TextField TFloyaltyCard;
    public Button useLoyaltyCardButton;
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

    private String mode;

    private int counter = 0;

    public void whatMode(String mode){
        this.mode = mode;
    }

    private loyaltyCard lC;

    @FXML
    public void initialize() {

        listOfItems = new itemList();
        listOfItems.addItem("001", "Toothpaste", "£7.00");
        listOfItems.addItem("002", "Snickers", "£1.00");
        listOfItems.addItem("003", "Pizza", "£3.50");
        listOfItems.addItem("004", "Sauce", "£2.50");
        listOfItems.addItem("005", "Smoked pork", "£3.50");
        listOfItems.addItem("006", "Soda can", "£2.00");
        listOfItems.addItem("007", "Plastic jug", "£8.00");
        listOfItems.addItem("008", "Salmon fillets", "£4.75");
        listOfItems.addItem("009", "Lemonade", "£1.60");
        listOfItems.addItem("010", "Chocolate bag", "£1.00");
        itemList.setItems(listOfItems);

        basketItems = new itemList();

        lC = new loyaltyCard();
        lC.addElement(new loyalCustomer("1971", "Hedwig", "Kowalik"));
        lC.addElement(new loyalCustomer("1850", "Mark", "Suski"));
        lC.addElement(new loyalCustomer("1250", "Millie", "Me"));




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


            } else if (!mode.equals("5items")){

                basketItems.addItem(wantedItem.getProductCode(), wantedItem.getDescription(),
                        wantedItem.getItemPrice());
                basket.setItems(basketItems);
                TFscanItems.clear();


                TFtotalAmount.setText(addCurrency(calculatePrice(basketItems)));

            //If we selected max 5 items checkout
            }else{

                if(counter < 5){

                    basketItems.addItem(wantedItem.getProductCode(), wantedItem.getDescription(),
                            wantedItem.getItemPrice());
                    basket.setItems(basketItems);
                    TFscanItems.clear();

                    TFtotalAmount.setText(addCurrency(calculatePrice(basketItems)));

                    counter += 1;

                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING, "You cannot buy more than 5 items", ButtonType.OK);
                    alert.showAndWait();
                }


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

    //Implementation of loading other stages and scenes was taken from:
    //Ilias Tachmazidis (2021) https://huddersfield.brightspace.com/d2l/le/content/133711/viewContent/962151/View

    public void payButtonPressed(javafx.event.ActionEvent actionEvent) throws IOException {


        if(!TFtotalAmount.getText().equals("")) {

            FXMLLoader loader = new FXMLLoader((getClass().getResource("payment.fxml")));

            Scene newScene = new Scene(loader.load());

            paymentController controller = loader.getController();

            controller.initialize(basketItems, mode);
            controller.getPrice(calculatePrice(basketItems));


            Stage popUpStage = new Stage();
            popUpStage.setScene(newScene);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.show();
        }else{

            Alert alert = new Alert(Alert.AlertType.WARNING, "You have not picked any of available items", ButtonType.OK);
            alert.showAndWait();

        }
    }



    //calculates total price of items
    public Double calculatePrice(itemList list){
        
        Double sum = 0.0;

        for (Item i : list) {
            //substring(1) cuts off the first letter
            sum += Double.parseDouble(i.getItemPrice().substring(1));
            
        }

        return sum;
        
    }

    //Implementation of adding currency to a String was taken from:
    //Subhashi (February 2020) https://stackoverflow.com/questions/2379221/java-currency-number-format
    public String addCurrency(Double money){

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);

        return nf.format(money);

    }


    public void useLoyaltyCard(ActionEvent actionEvent) {

        String searchingID = TFloyaltyCard.getText();
        Double calculating;



        if(lC.findCustomer(searchingID)){
            calculating = Double.parseDouble(TFtotalAmount.getText().substring(1)) * 0.98;
            TFtotalAmount.setText(addCurrency(calculating));
            useLoyaltyCardButton.setDisable(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "You are not the part of our community!", ButtonType.OK);
            alert.showAndWait();
        }

    }
}
