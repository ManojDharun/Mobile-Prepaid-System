package application;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class State implements Initializable {
	@FXML
	TextField state;
	@FXML
	TableView<Names> tab;
	//@FXML
	//TableColumn<Names,String> col1;
	@FXML
	TableColumn<Names,String> col2;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			//col1.setCellValueFactory(new PropertyValueFactory<Names,String>("ser_no"));
			col2.setCellValueFactory(new PropertyValueFactory<Names,String>("name"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		ObservableList<Names> ob = FXCollections.observableArrayList(
				new Names("Andhra Pradesh"),
				 new Names("Haryana"),
				 new Names("Assam"),
				 new Names("Nagaland"),
				 new Names("Telangana"),
				 new Names("Arunachal Pradesh"),
				 new Names("Bihar"),
				 new Names("Nagaland"),
				 new Names("Odisha"),
				 new Names("West Bengal"),
				 new Names("Tamil Nadu"),
				 new Names("Sikkim"),
				 new Names("Rajasthan"),
				 new Names("Punjab"),
				 new Names("Odisha"),
				 new Names("Nagaland"),
				 new Names("Maharashtra"),
				 new Names("Mizoram"),
				 new Names("Madhya Pradesh"),
				 new Names("Karnataka"),
				 new Names("Jharkhand"),
				 new Names("Himachal Pradesh"),
				 new Names("Uttar Pradesh"),
				 new Names("Gujarat"));
	    tab.setItems(ob);
	    tab.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super Names>) new ChangeListener() {
	        @Override
	        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
	            //Check whether item is selected and set value of selected item to Label
	            if(tab.getSelectionModel().getSelectedItem() != null) 
	            {    
	               TableViewSelectionModel selectionModel = tab.getSelectionModel();
	               ObservableList selectedCells = selectionModel.getSelectedCells();
	               TablePosition tablePosition = (TablePosition) selectedCells.get(0);
	               Object val = tablePosition.getTableColumn().getCellData(newValue);
	               state.setText((String) val);
	            }
	        }
	         });
	}
	
	public void proceed(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Plans_select.fxml"));
		Scene scene = new Scene(root,600,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
