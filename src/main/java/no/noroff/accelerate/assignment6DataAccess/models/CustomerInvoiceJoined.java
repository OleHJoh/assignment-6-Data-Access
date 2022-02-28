package no.noroff.accelerate.assignment6DataAccess.models;

public class CustomerInvoiceJoined {

    private String firstName;
    private String lastName;
    private String total;

    public CustomerInvoiceJoined(String firstName, String lastName, String total) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
