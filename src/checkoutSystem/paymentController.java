package checkoutSystem;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class paymentController {

    @FXML
    public ListView<Item> receiptListView;
    public Button closeButton;
    public Button payButton;

    @FXML
    public Button cashButton;

    @FXML
    public Button cardButton;

    @FXML
    public Label cashLabel;

    @FXML
    public TextField TFcardNumber;

    @FXML
    public TextField TFcvv2;

    @FXML
    public TextField TFamountOfCash;

    @FXML
    public TextField TFcashChange;
    public VBox cashVBox;
    public VBox cardVBox;

    private Double price;




    @FXML
    public void initialize(itemList list, String mode) {

        receiptListView.setItems(list);

        if(mode.equals("cash")){
            cardButton.setDisable(true);
            cashVBox.setVisible(true);


        }

    }




    public void closeButtonPressed(ActionEvent actionEvent) {

        ((Stage)closeButton.getScene().getWindow()).close();
    }

    public void payButtonPressed(ActionEvent actionEvent){

        //cashVBox contains all the elements required to make the cash payment
        //cardVbox contains all the elements required to make the card payment
        if(cashVBox.isVisible() && ( !TFamountOfCash.getText().equals("") || !TFcashChange.getText().equals(""))){

            TFamountOfCash.clear();
            TFcashChange.clear();


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The payment was successfully made", ButtonType.OK);
            alert.showAndWait();

            ((Stage)closeButton.getScene().getWindow()).close();


        }else if(TFamountOfCash.getText().equals("") && cashVBox.isVisible()){

            Alert alert = new Alert(Alert.AlertType.WARNING, "There is no sufficient data", ButtonType.OK);
            alert.showAndWait();

        }else if( (TFcardNumber.getText().equals("") || TFcvv2.getText().equals("")) && cardVBox.isVisible()){

            Alert alert = new Alert(Alert.AlertType.WARNING, "There is no sufficient data", ButtonType.OK);
            alert.showAndWait();

        }else if( cardVBox.isVisible() && ( !TFcardNumber.getText().equals("") || !TFcvv2.getText().equals("")) ){



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The payment was successfully made", ButtonType.OK);
            alert.showAndWait();

            ((Stage)closeButton.getScene().getWindow()).close();


        }
    }


    public void getPrice(Double price){
        this.price = price;
    }


    //it calculates the change when you pay by cash
    public void changeCalculations(ActionEvent actionEvent) {

        String providedMoney = TFamountOfCash.getText();
        Double difference;

        if (providedMoney.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You have to insert any money", ButtonType.OK);
            alert.showAndWait();

        }else if(Double.parseDouble(providedMoney) < price) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You have to insert more money", ButtonType.OK);
            alert.showAndWait();


        }else{
            difference = Double.parseDouble(providedMoney) - price;
            TFcashChange.setText(String.valueOf(difference));
        }

    }

    public void payByCash(ActionEvent actionEvent) {

        cashVBox.setVisible(true);
        cardVBox.setVisible(false);

    }

    public void payByCard(ActionEvent actionEvent) {

        cardVBox.setVisible(true);
        cashVBox.setVisible(false);

    }
}
