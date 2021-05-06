package checkoutSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindowController {
    public Button casualCheckoutButton;
    public Button onlyCashCheckoutButton;
    public Button only5ItemsButton;
    public TextField TFAllPayments;

    public void loadCasualCheckout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("checkout.fxml")));

        Scene newScene = new Scene(loader.load());

        CheckoutController controller = loader.getController();
        controller.whatMode("casual");



        Stage popUpStage = new Stage();
        popUpStage.setScene(newScene);
        popUpStage.initModality(Modality.NONE);
        popUpStage.show();
    }

    public void loadOnlyCashCheckout(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader((getClass().getResource("checkout.fxml")));

        Scene newScene = new Scene(loader.load());

        CheckoutController controller = loader.getController();
        controller.whatMode("cash");


        Stage popUpStage = new Stage();
        popUpStage.setScene(newScene);
        popUpStage.initModality(Modality.NONE);
        popUpStage.show();
    }

    public void load5ItemsOnly(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader((getClass().getResource("checkout.fxml")));

        Scene newScene = new Scene(loader.load());

        CheckoutController controller = loader.getController();
        controller.whatMode("5items");


        Stage popUpStage = new Stage();
        popUpStage.setScene(newScene);
        popUpStage.initModality(Modality.NONE);
        popUpStage.show();
    }

}
