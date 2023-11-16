/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihanmf7.Model;

/**
 *
 * @author Blizzard
 */
public class AirportTicketModels {

    private String Costumer_name;
    private int qty, destination,creditcard_number,payment_method;

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public String getCostumer_name() {
        return Costumer_name;
    }

    public void setCostumer_name(String Costumer_name) {
        this.Costumer_name = Costumer_name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getCreditcard_number() {
        return creditcard_number;
    }

    public void setCreditcard_number(int creditcard_number) {
        this.creditcard_number = creditcard_number;
    }
    
    

    
}
