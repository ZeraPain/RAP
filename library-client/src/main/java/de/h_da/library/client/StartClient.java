package de.h_da.library.client;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class StartClient extends Application {

    private StartDialogCore dialogCore = new StartDialogCore();
    private AnchorPane content = new AnchorPane();
    
    private ButtonType role = null;
    private ButtonType librarienOption = new ButtonType("Librarien");
    private ButtonType customerOption = new ButtonType("Customer");
    private ButtonType accountantOption = new ButtonType("Accountant");

    public static void main(String[] args) {
        launch(args);
    }

    protected void loadUseCases(){
        try {        	
        	if(role == null) {
        		dialogCore.loadView("Use case 1", "/de/h_da/library/client/UseCase1.fxml");
                dialogCore.loadView("Registration", "/de/h_da/library/client/RegistrationUseCase.fxml");
                dialogCore.loadView("Reminder", "/de/h_da/library/client/ReminderUseCase.fxml");
                dialogCore.loadView("Search", "/de/h_da/library/client/SearchUseCase.fxml"); 
        	}else if ( role == librarienOption) {
        		dialogCore.loadView("Use case 1", "/de/h_da/library/client/UseCase1.fxml");
                dialogCore.loadView("Registration", "/de/h_da/library/client/RegistrationUseCase.fxml");
                dialogCore.loadView("Reminder", "/de/h_da/library/client/ReminderUseCase.fxml");
                dialogCore.loadView("Search", "/de/h_da/library/client/SearchUseCase.fxml"); 
                dialogCore.loadView("BookManagement", "/de/h_da/library/client/BookManagementUseCase.fxml");
        	}else if ( role == customerOption) {
                dialogCore.loadView("Search", "/de/h_da/library/client/SearchUseCase.fxml"); 
        	}else if ( role == accountantOption) {
        		dialogCore.loadView("Use case 1", "/de/h_da/library/client/UseCase1.fxml");
        		dialogCore.loadView("Accounting", "/de/h_da/library/client/Accounting.fxml");
        	} 
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    private void showRoleChooseDialog() {
    	 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Select");
        alert.setHeaderText("Choose User Role:");      
 
        // Remove default ButtonTypes
        alert.getButtonTypes().clear(); 
        alert.getButtonTypes().addAll(librarienOption, customerOption, accountantOption);
 
        Optional<ButtonType> option = alert.showAndWait();
 
        if(option != null)
        	this.role = option.get();
    }

    @Override
    public void start(Stage primaryStage) {
        
    	showRoleChooseDialog();
    	
    	primaryStage.setTitle("Library App");

        loadUseCases();

        SplitPane root = new SplitPane();
        Scene scene = new Scene(root, 1024, 768);
        scene.setRoot(root);
        primaryStage.setScene(scene);

        root.setOrientation(Orientation.HORIZONTAL);
        root.setDividerPositions(0.2f);
        root.getItems().addAll(createListView(), content);


        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ServerFacade.getInstance().closeContext();
    }

    private ListView<Pair<String, Parent>> createListView() {
        ListView<Pair<String, Parent>> list = new ListView<>();
        list.setItems(dialogCore.getUserCases());

        list.getSelectionModel().selectedItemProperty().addListener((ob, oldVal, newVal) -> {
            content.getChildren().clear();
            if (newVal != null) {
                content.getChildren().add(newVal.getValue());
                AnchorPane.setBottomAnchor(newVal.getValue(), 0.0);
                AnchorPane.setRightAnchor(newVal.getValue(), 0.0);
                AnchorPane.setTopAnchor(newVal.getValue(), 0.0);
                AnchorPane.setLeftAnchor(newVal.getValue(), 0.0);
            }
        });

        list.setCellFactory(new Callback<ListView<Pair<String, Parent>>, ListCell<Pair<String, Parent>>>() {
            @Override
            public ListCell<Pair<String, Parent>> call(ListView<Pair<String, Parent>> param) {
                return new ListCell<Pair<String, Parent>>(){

                    @Override
                    protected void updateItem(Pair<String, Parent> item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null){
                            setText(item.getKey());
                        }
                    }
                };
            }
        });
        list.getSelectionModel().select(0);
        return list;
    }
}
