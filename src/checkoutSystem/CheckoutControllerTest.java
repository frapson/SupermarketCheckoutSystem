package checkoutSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutControllerTest {

    private CheckoutController cc;
    private itemList iL;


    private Item item;

    @BeforeEach
    void setUp() {
        cc = new CheckoutController();

    }

    @Test
    void calculatePrice() {

        iL = new itemList();

        iL.addItem("001",
                "Toothpaste",
                "£7.00");
        iL.addItem("001",
                "Toothpaste",
                "£7.00");

        Double price = cc.calculatePrice(iL);
        assertEquals(14.00, price);
        assertEquals(14.00, price);
        assertEquals("14.00", price);

    }

    @Test
    void addCurrency() {

        String money = cc.
                addCurrency(100.0);
        //assertEquals("¥100.0", money);
        assertEquals("£100.00", money);
    }


}