package de.h_da.library.client.accounting;

import de.h_da.library.accounting.usecase.AccountingRemote;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccountingController implements QuasarController {

	
    @FXML
    private Button sendInvoice;
    
    @FXML
    private TextField invoiceNameTextfeld;

    @FXML
    private Label invoiceName;

    @FXML
    private Label setAmount;

    @FXML
    private Label setSubject;

    @FXML
    private Button sendButton;

    @FXML
    private Label setAdress;

    @FXML
    private Label setDate;

    @FXML
    private TextField setAmountTextfeld;

    @FXML
    private TextField setSubjectTextfeld;

    @FXML
    private TextField setAdressTextfeld;

    @FXML
    private TextField setDateTextfeld;
    
    @FXML
    private Button sendReceiveMoney;
    
    @FXML
    private TextField setIDtextfeld;

    @FXML
    private Label setID;
    
    private AccountingRemote accounting;

    @FXML
    void sendInvoiceHeandler(MouseEvent event) {
    	accounting.sendInvoice(setSubjectTextfeld.getText(), invoiceNameTextfeld.getText(), setAdressTextfeld.getText(), Integer.parseInt(setAmountTextfeld.getText()));
		// TODO Auto-generated method stub
    }

    @FXML
    void receiveMoneyAction(MouseEvent event) {
    	accounting.receiveMoney(Long.valueOf(setIDtextfeld.getText()).longValue());

    }
    
	@Override
	public void init()
	{
		accounting =  ServerFacade.getInstance().lookup("AccountingImpl", AccountingRemote.class);		
	}

}

