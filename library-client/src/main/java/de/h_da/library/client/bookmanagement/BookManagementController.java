package de.h_da.library.client.bookmanagement;

import java.util.List;

import de.h_da.library.LibraryException;
import de.h_da.library.RegistrationException;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.usecase.BookManagementRemote;
import de.h_da.library.datamanagement.usecase.SearchRemote;
import de.h_da.library.registration.usecase.RegistrationRemote;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BookManagementController implements QuasarController {

	  @FXML
	    private AnchorPane addBookPane;

	    @FXML
	    private TextField authorsBookTextBox;

	    @FXML
	    private TextField titleBookTextBox;

	    @FXML
	    private Label authorsBookLabel;

	    @FXML
	    private Label titleBookLabel;

	    @FXML
	    private Button addbookButton;

	    @FXML
	    private TextField stockBookTextBox;

	    @FXML
	    private Label stockBookLabel;

	    @FXML
	    private Label infoBookLabel;

	    @FXML
	    private AnchorPane modifyBookPane;

	    @FXML
	    private TextField authorsBookModifyTextBox;

	    @FXML
	    private TextField idBookModifyTextBox;

	    @FXML
	    private Label authorsBookModifyLabel;

	    @FXML
	    private Label titleBookModifyLabel;

	    @FXML
	    private Button modifyBookButton;

	    @FXML
	    private TextField titleBookModifyTextBox;

	    @FXML
	    private Label idBookModifyLabel;

	    @FXML
	    private Label infoBookModifyLabel;

	    @FXML
	    private AnchorPane modifyBookPane1;

	    @FXML
	    private TextField idBookModifyTextBox1;

	    @FXML
	    private TextField stockBookTextBox1;
	    
	    @FXML
	    private Button modifyBookButton1;

	    @FXML
	    private Label idBookModifyLabel1;

	    @FXML
	    private Label infoBookModifyLabel1;

	    @FXML
	    private Label idBookModifyLabel11;
    
    BookManagementRemote bookManagement;
    SearchRemote search;

    
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
    void addBookEvent(MouseEvent event) {
    	System.out.println("addbook");
        Book book = new Book();
        book.setAuthors(authorsBookTextBox.getText());
        book.setTitle(titleBookTextBox.getText());
        
        try {
        	int numberOfBooksOnStock = Integer.valueOf(stockBookTextBox.getText());
			int id = bookManagement.addBook(book, numberOfBooksOnStock);
			System.out.println("Book ID " + Integer.toString(id));
			this.fadeOut(infoBookLabel, "Stored with Book ID: " + Integer.toString(id));
        } catch (NumberFormatException e) {
        	System.out.println(e.getMessage());
    		this.fadeOut(infoBookModifyLabel, "Stock Value not a Number");
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			this.fadeOut(infoBookLabel, e.getMessage());

		}
    }

    @FXML
    void modifyBookEvent(MouseEvent event) {
    	System.out.println("modify");
    	
    	Book book = new Book();
    	
    	try {
    		Long id = Long.valueOf(idBookModifyTextBox.getText()).longValue();
    		book.setAuthors(authorsBookModifyTextBox.getText());
    		book.setTitle(titleBookModifyTextBox.getText());
    	
    		bookManagement.modifyBook(id, book);
    		
			this.fadeOut(infoBookModifyLabel, "Modified successfully");
    	} catch (NumberFormatException e) {
    		System.out.println(e.getMessage());
    		this.fadeOut(infoBookModifyLabel, "ID Value not a Number");
    	} catch (LibraryException e) {
			// TODO Auto-generated catch block
    		System.out.println(e.getMessage());
    		this.fadeOut(infoBookModifyLabel, e.getMessage());
		}
    }

    @FXML
    void modifyBookStockEvent(MouseEvent event) {
    	System.out.println("modify stock");
    	
    	try {
    		int id = Integer.valueOf(idBookModifyTextBox1.getText());
    		int numberOfBooksOnStock = Integer.valueOf(stockBookTextBox1.getText());
    		bookManagement.addBooksOnStock(id, numberOfBooksOnStock);
			this.fadeOut(infoBookModifyLabel1, "Modified successfully");
    	} catch (NumberFormatException e) {
    		System.out.println(e.getMessage());
    		this.fadeOut(infoBookModifyLabel1, "ID Value not a Number");
    	} catch (LibraryException e) {
			// TODO Auto-generated catch block
    		System.out.println(e.getMessage());
    		this.fadeOut(infoBookModifyLabel1, e.getMessage());
		}
    }

    
	@Override
	public void init() {
        bookManagement = ServerFacade.getInstance().lookup("BookManagementImpl", BookManagementRemote.class);
		
	}


}
