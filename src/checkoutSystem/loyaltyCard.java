package checkoutSystem;

import java.util.ArrayList;

public class loyaltyCard {

    private ArrayList<loyalCustomer> loyaltyCard = new ArrayList<>();

    public loyaltyCard(){}


    public void addElement(loyalCustomer lC){
        loyaltyCard.add(lC);
    }

    public boolean findCustomer(String ID){

        for(loyalCustomer i : loyaltyCard){

            if(i.getLoyalID().equals(ID)){
                return true;
            }

        }

        return false;

    }


}
