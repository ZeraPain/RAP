package de.h_da.library.client.search;

import java.util.List;

import de.h_da.library.LibraryException;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;
import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;
import de.h_da.library.datamanagement.usecase.SearchRemote;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class SearchController implements QuasarController{

    @FXML
    private TextField bookTitleField;

    @FXML
    private TextField bookAuthorField;

    @FXML
    private Button searchBookButton;
    
    @FXML
    private Label searchBookInfoLabel;

    @FXML
    private ListView<String> foundBooksListView;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerAddressField;

    @FXML
    private Button searchCustomerButton;
    
    @FXML
    private Label searchCustomerInfoLabel;

    @FXML
    private ListView<String> foundCustomersListView;

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
	    node.setVisible(true);
		node.setText(text);
	    fadeOutTransition.playFromStart();
	}
    
    @FXML
    void bookSearchEvent(MouseEvent event) {
    	System.out.println("bookSearch clicked");
    	
    	String title = bookTitleField.getText();
    	String author = bookAuthorField.getText();
    	List<Book> foundBooks = null;
    	
    	if(title.isEmpty() && author.isEmpty()) {
    		this.fadeOut(searchBookInfoLabel, "Please enter a Title and or Author to search a book.");
    		foundBooksListView.getItems().clear();
    		return;
    	}
    		
    	Book filter = new Book();
    	if(!title.isEmpty())
    		filter.setTitle(title);
    	if(!author.isEmpty())
    		filter.setAuthors(author);
    	
    	try {
			 foundBooks = search.findBooksByAttributes(filter);
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.fadeOut(searchBookInfoLabel, e.getMessage());
		}
    	
    	if(foundBooks == null || foundBooks.isEmpty()) {
    		this.fadeOut(searchBookInfoLabel, "No books found!");
    		foundBooksListView.getItems().clear();
    	}else {
    		ObservableList<String> items = FXCollections.observableArrayList();
    		for(Book book : foundBooks) {
    			items.add("ID: " + book.getId()+" | Title: " +book.getTitle() + " | Authors: " + book.getAuthors());
    		}
    		foundBooksListView.setItems(items);
    	}
    }

    @FXML
    void customerSearchEvent(MouseEvent event) {
    	System.out.println("customerSearch clicked");
    	
    	String name = customerNameField.getText();
    	String address = customerAddressField.getText();
    	List<Customer> foundCustomers = null;
    	
    	if(name.isEmpty() && address.isEmpty()) {
    		this.fadeOut(searchCustomerInfoLabel, "Please enter a Name and or Address to search a customer.");
    		foundCustomersListView.getItems().clear();
    		return;
    	}
    		
    	Customer filter = new Customer();
    	if(!name.isEmpty())
    		filter.setName(name);
    	if(!address.isEmpty())
    		filter.setAddress(address);
    	
    	try {
			 foundCustomers = search.findCustomersByAttributes(filter);
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.fadeOut(searchCustomerInfoLabel, e.getMessage());
		}
    	
    	if(foundCustomers == null || foundCustomers.isEmpty()) {
    		this.fadeOut(searchCustomerInfoLabel, "No customers found!");
    		foundCustomersListView.getItems().clear();
    	}else {
    		ObservableList<String> items = FXCollections.observableArrayList();
    		for(Customer customer : foundCustomers) {
    			items.add("ID: " + customer.getId() + " | Name: " +customer.getName() + " | Address: " + customer.getAddress());
    		}
    		foundCustomersListView.setItems(items);
    	}
    }

	@Override
	public void init() {
		search = ServerFacade.getInstance().lookup("SearchImpl", SearchRemote.class);
		// TODO Auto-generated method stub
		
	}

}
