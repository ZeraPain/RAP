package de.h_da.library.client.registration;

import de.h_da.library.LibraryException;
import de.h_da.library.RegistrationException;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.manager.CustomerManager;
import de.h_da.library.datamanagement.usecase.CustomerManagementRemote;
import de.h_da.library.registration.usecase.RegistrationRemote;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class RegistrationController implements QuasarController{

    @FXML
    private AnchorPane registerPane;

    @FXML
    private TextField nameRegisterTextBox;

    @FXML
    private TextField addressRegisterTextBox;

    @FXML
    private Label nameRegisterLabel;

    @FXML
    private Label addressRegisterLabel;

    @FXML
    private Button registerButton;

    @FXML
    private AnchorPane modifyPane;

    @FXML
    private TextField nameModifyTextBox;

    @FXML
    private TextField idModifyTextBox;

    @FXML
    private Label nameModifyLabel;

    @FXML
    private Label addressModifyLabel;

    @FXML
    private TextField addressModifyTextBox;

    @FXML
    private Label idModifyLabel;

    @FXML
    private Label infoModify;
    
    @FXML
    private Label infoRegister;
    
    RegistrationRemote registration;
    
    
    
    
    
    private FadeTransition fadeOutTransition = new FadeTransition(
    	    Duration.millis(3000)
    	);

	private void fadeOut(Label node, String text) {
	    fadeOutTransition.setNode(node);

	    fadeOutTransition.setFromValue(1.0);
	    fadeOutTransition.setToValue(0.0);
	    fadeOutTransition.setCycleCount(1);
	    fadeOutTransition.setAutoReverse(false);
	    
		node.setText(text);
	    infoRegister.setVisible(true);
	    fadeOutTransition.playFromStart();
	}
	
    @FXML
    void modifyEvent(MouseEvent event) {
    	System.out.println("modify");
    	
    	Customer customer = new Customer();
    	try {
    		long id = Long.valueOf(idModifyTextBox.getText()).longValue();
    		customer = registration.findCustomerById(id);
            
            customer.setAddress(addressModifyTextBox.getText());
            customer.setName(nameModifyTextBox.getText());
			registration.modifyRestistration(customer);
			this.fadeOut(infoModify, "Modified successfully");
		} catch (RegistrationException e) {
			// TODO Auto-generated catch block
			this.fadeOut(infoModify, e.getMessage());
    	} catch (NumberFormatException e) {
    		this.fadeOut(infoModify, "ID Value not a Number");
    	} catch (LibraryException e) {
			// TODO Auto-generated catch block
    		this.fadeOut(infoModify, e.getMessage());
		}		
    }

    @FXML
    void registerEvent(MouseEvent event) {
    	System.out.println("register");
        Customer customer = new Customer();
        customer.setAddress(addressRegisterTextBox.getText());
        customer.setName(nameRegisterTextBox.getText());
        try {
			Long id = registration.register(customer);
			System.out.println("Customer ID " + id.toString());
			this.fadeOut(infoRegister, "Stored with Customer ID: " + id.toString());
		} catch (RegistrationException e) {
			// TODO Auto-generated catch block
			this.fadeOut(infoRegister, e.getMessage());

		}
    }
    
    
    

	@Override
	public void init() {
        registration = ServerFacade.getInstance().lookup("RegistrationImpl", RegistrationRemote.class);
        //entity1List.addAll(reigstration.useCaseMethod1());
        //entity1ListView.setItems(entity1List);

        //attribute2Combo.setItems(FXCollections.observableArrayList(DataType1.values()));
		
	}

}

