/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package latihanmf7.Controler;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import latihanmf7.Model.AirportTicketModels;

/**
 * FXML Controller class
 *
 * @author Blizzard
 */
public class FXMLTiketController implements Initializable {

    //Inititialize model class
    AirportTicketModels mdls = new AirportTicketModels();

    //Curency Formatter
    Locale Indonesia = new Locale("in", "ID");
    NumberFormat formater = NumberFormat.getCurrencyInstance(Indonesia);
    
    @FXML
    private Tab formoder;
    @FXML
    private TextField txtcostumer;
    @FXML
    private ComboBox<String> chbdestination;
    @FXML
    private ComboBox<String> chbpaymentmetod;
    @FXML
    private Button orderbtn;
    @FXML
    private Button resetbtn;
    @FXML
    private TextField txtqty;
    @FXML
    private Button closebtninput;
    @FXML
    private Tab invoice;
    @FXML
    private Label costumer_name;
    @FXML
    private Label destinationlbl;
    @FXML
    private Label paymentmetod;
    @FXML
    private Label entryfreelbl;
    @FXML
    private Label tickefeelbl;
    @FXML
    private Label subtotallbl;
    @FXML
    private Label taxlbl;
    @FXML
    private Label paymentslbl;
    @FXML
    private Label qtyentreefeelbl;
    @FXML
    private Label qtyticketfeelbl;
    @FXML
    private Label totalentryfreelbl;
    @FXML
    private Label totalticketfeelbl;
    @FXML
    private Button closebtnoutput;
    @FXML
    private Button Backbtn;
    @FXML
    private TabPane airlinetickets;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chbdestination.setItems(FXCollections.observableArrayList(
                "--Select Your Destinations--",
                "America",
                "Japan",
                "Singapore"));
        chbdestination.getSelectionModel().select(0);
        chbpaymentmetod.setItems(FXCollections.observableArrayList(
                "--Select Your Payments Method--",
                "Cash",
                "Credit Cards"
        ));
        chbpaymentmetod.getSelectionModel().select(0);
    }
    
    @FXML
    private void orderclick(ActionEvent event) {
        if (txtcostumer.getText().isEmpty()) {
            do {
                TextInputDialog txt = new TextInputDialog();
                txt.setContentText("404 ! THE FIELD IS NULL PLEASE INPUT COSTUMER NAME!!!");
                txt.setTitle("INPUT COSTUMER NAME");
                txt.showAndWait();
                txtcostumer.setText(txt.getResult());
            } while (txtcostumer.getText().isEmpty());
        } else {
            mdls.setCostumer_name(txtcostumer.getText());
            mdls.setQty(Integer.parseInt(txtqty.getText()));
            costumer_name.setText(mdls.getCostumer_name());
            qtyentreefeelbl.setText(String.valueOf(mdls.getQty()));
            qtyticketfeelbl.setText(String.valueOf(mdls.getQty()));
            String Destinations = null;
            String Payment_Methods = null;
            double tax = 0, ticketdiscount = 0, entrydiscount = 0, Entryfee = 0, ticket_fee = 0;
            mdls.setPayment_method(chbpaymentmetod.getSelectionModel().getSelectedIndex());
            mdls.setDestination(chbdestination.getSelectionModel().getSelectedIndex());
            switch (mdls.getDestination()) {
                case 1: {
                    Destinations = "America";
                    ticket_fee = 5000000;
                    tax = 0;
                    Entryfee = 2000000;
                    break;
                }
                case 2: {
                    Destinations = "Japan";
                    ticket_fee = 2000000;
                    tax = 0;
                    Entryfee = 1000000;
                    break;
                }
                case 3: {
                    Destinations = "Singpore";
                    ticket_fee = 1000000;
                    tax = 0.1;
                    Entryfee = 500000;
                    break;
                }
                default: {
                    Destinations = null;
                    ticket_fee = 0;
                    tax = 0;
                    Entryfee = 0;
                }
            }
            if (mdls.getPayment_method() == 2) {
                TextInputDialog txt = new TextInputDialog();
                txt.setContentText("Input Credit Card Number");
                txt.setTitle("INPUT CREDIT Number");
                txt.showAndWait();
                if (txt.getResult().isEmpty()) {
                    Alert art = new Alert(Alert.AlertType.WARNING, "The Field is null Please Input Your Credit Card", ButtonType.YES);
                    art.showAndWait();
                } else {
                    mdls.setCreditcard_number(Integer.parseInt(txt.getResult()));
                    paymentmetod.setText("Credit Card" + "\t" + "(" + " " + String.valueOf(mdls.getCreditcard_number()) + " " + ")");
                    ticketdiscount = 100000;
                    airlinetickets.getSelectionModel().select(1);
                }
            } else {
                paymentmetod.setText("Cash");
                ticketdiscount = 0;
            }
            
            double totalticket_fee = ((mdls.getQty() * ticket_fee) - ticketdiscount);
            double totalentry_fee = mdls.getQty() * Entryfee;
            double totalprice = totalticket_fee + totalentry_fee; 
            double totaltax=tax*Entryfee;
            if(totalprice>100000000){
                entrydiscount = 500000-totalentry_fee;
            }else if(totalprice>50000000){
                
            }
            destinationlbl.setText(Destinations);
            tickefeelbl.setText(formater.format(ticket_fee));
            taxlbl.setText(formater.format(totaltax));
            airlinetickets.getSelectionModel().select(1);
            entryfreelbl.setText(formater.format(Entryfee));
            totalticketfeelbl.setText(formater.format(totalticket_fee));
            totalentryfreelbl.setText(formater.format(totalentry_fee));
            subtotallbl.setText(formater.format(totalprice));
        }
    }
    
    @FXML
    private void resetclick(ActionEvent event) {
        Alert art = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure this data will be reset?", ButtonType.YES, ButtonType.NO);
        art.showAndWait();
        if (art.getResult() == ButtonType.YES) {
            txtcostumer.setText(null);
            chbdestination.getSelectionModel().select(0);
            chbpaymentmetod.getSelectionModel().select(0);
            txtqty.setText(null);
        } else {
            txtcostumer.requestFocus();
        }
    }
    
    @FXML
    private void closeclick(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void Backbtn(ActionEvent event) {
        txtcostumer.setText("");
        txtcostumer.requestFocus();
        
    }
    
    @FXML
    private void invoice_event(Event event
    ) {
        if (txtcostumer.getText().isEmpty() && invoice.isSelected()) {
            {
                Alert art = new Alert(Alert.AlertType.WARNING, "Data Masih Kosong Mohon Isi Terlebih dahulu", ButtonType.YES);
                art.showAndWait();
                if (art.getResult() == ButtonType.YES) {
                    airlinetickets.getSelectionModel().select(0);
                }
            }
        }
    }
    
}
