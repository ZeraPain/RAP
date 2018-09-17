package de.h_da.library.client.reminder;

import de.h_da.library.LibraryException;
import de.h_da.library.client.QuasarController;
import de.h_da.library.client.ServerFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import de.h_da.library.reminding.usecase.RemindingRemote;;


public class ReminderController implements QuasarController  {

	RemindingRemote reminderRemote;
	   
	
	 @FXML
    private Button sendAllReminders;

    @FXML
    private Button closeReminderBtn;
    
    @FXML
    private TextArea textfield_id;
    

    @FXML
    void closeReminderBtn(MouseEvent event) {
    	String id = textfield_id.getText();
    	
    	System.out.println(id);
    	
    	long invoiceId = new Long( Integer.parseInt(id));
    	
    	try {
			reminderRemote.closeReminder(invoiceId);
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }



    @FXML
    void sentReminderOutBtn(MouseEvent event) {

    	try {
			reminderRemote.sendReminders();
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		reminderRemote = ServerFacade.getInstance().lookup("RemindingImpl", RemindingRemote.class);
		
		
		
	}

}
