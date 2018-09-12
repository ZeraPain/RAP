package de.h_da.library.client.registration;

import de.h_da.library.RegistrationException;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.registration.usecase.RegistrationRemote;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    void modifyEvent(MouseEvent event) {
    	System.out.println("modify");
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
		} catch (RegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    	
    }
    
    RegistrationRemote registration;

	@Override
	public void init() {
        registration = ServerFacade.getInstance().lookup("RegistrationImpl", RegistrationRemote.class);
        //entity1List.addAll(reigstration.useCaseMethod1());
        //entity1ListView.setItems(entity1List);

        //attribute2Combo.setItems(FXCollections.observableArrayList(DataType1.values()));
		
	}

}

