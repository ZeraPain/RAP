package de.h_da.library.client.bookmanagement;

import de.h_da.library.client.QuasarController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private Label infoModify;

    @FXML
    void addBookEvent(MouseEvent event) {

    }

    @FXML
    void modifyBookEvent(MouseEvent event) {

    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
