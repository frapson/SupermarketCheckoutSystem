package checkoutSystem;

public class loyalCustomer {

    private String loyalID;
    private String name;
    private String surname;

    public loyalCustomer(String loyalID, String name, String surname) {
        this.loyalID = loyalID;
        this.name = name;
        this.surname = surname;
    }

    public String getLoyalID() {
        return loyalID;
    }

    public void setLoyalID(String loyalID) {
        this.loyalID = loyalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return loyalID  + " " + name + " " + surname ;
    }
}
