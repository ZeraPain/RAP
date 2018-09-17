package de.h_da.library.client.borrow;

import java.awt.event.MouseEvent;

import de.h_da.library.LibraryException;
import de.h_da.library.borrowing.usecase.BorrowingRemote;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class BorrowingController implements QuasarController{

    @FXML
    private TextField bookIDTextField;

    @FXML
    private TextField bookOnStockIDField;

    @FXML
    private Button bookAvailableButton;

    @FXML
    private Button borrowButton;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField customerIDTextField;

    @FXML
    private TextField loanIDTextField;

    @FXML
    private Button returnBookButton;

    @FXML
    private Label infoReturnBook;
    
    
    BorrowingRemote borrowing;
    
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
	    node.setVisible(true);
	    fadeOutTransition.playFromStart();
	}
	

    @FXML
    void bookAvailableButton(MouseEvent event) {
    	try {
			Long bookOnStockID = borrowing.bookAvailable(Long.valueOf(bookIDTextField.getText()).longValue());
			bookOnStockIDField.setText(bookOnStockID.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			this.fadeOut(infoLabel, "ID Value not a Number");
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			this.fadeOut(infoLabel, e.getMessage());
		}
    }

    @FXML
    void borrowButtonAction(MouseEvent event) {
    	try {
			Long loanID = borrowing.borrowBook(Long.valueOf(bookOnStockIDField.getText()).longValue(), Long.valueOf(customerIDTextField.getText()).longValue());
			this.fadeOut(infoLabel, "Your Loan ID: " + loanID.toString());
    	} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			this.fadeOut(infoLabel, "ID Value not a Number");
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			this.fadeOut(infoLabel, e.getMessage());
		}

    }

    @FXML
    void returnBookAction(MouseEvent event) {

    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		borrowing = ServerFacade.getInstance().lookup("BorrowingImpl", BorrowingRemote.class);
		
	}

}
