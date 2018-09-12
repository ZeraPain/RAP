package de.h_da.library.client.reminder;

import de.h_da.library.client.QuasarController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ReminderController implements QuasarController {

    @FXML
    private Button refreshBtn;

    @FXML
    void onBtnRefreshClicked(MouseEvent event) {
    		System.out.println("clicked");
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("init reminder controller");
	}

}
