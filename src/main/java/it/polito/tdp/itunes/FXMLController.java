/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnComponente"
    private Button btnComponente; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnSet"
    private Button btnSet; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doComponente(ActionEvent event) {
    	Album a1 = cmbA1.getValue();
	   	if (a1== null) {
	   		txtResult.setText("Scegli un album");
	   		return ;
	   	}
	   	Result r = model.calcolaConnessa(a1);
	   	txtResult.appendText("La componente connessa è formata da: "+ r.getConnesse()+"\n");
	   	txtResult.appendText("La durata totale è : "+ r.getDurata());
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String input = txtDurata.getText();
    	if (input.compareTo("")==0){
    		txtResult.setText("Inserisci una durata");
    		return;
    	}
    	
    	double durata = 0;
    	try {
    		durata = Double.parseDouble(input);
    	}catch (NumberFormatException e){
    		txtResult.setText("La durata deve essere un numero");
    	}
    	double duratamill = durata*60000;
    	model.creaGrafo(duratamill);
    	txtResult.appendText("Grafo Creato \n");
    	txtResult.appendText("#Vertici: "+ model.getVertici()+"\n");
    	txtResult.appendText("#ARchi: "+ model.getArchi()+"\n");
    	List<Album> a = model.getAlbum();
    	cmbA1.getItems().addAll(a);
    	
    }

    @FXML
    void doEstraiSet(ActionEvent event) {
    	String input = txtX.getText();
    	if (input== "") {
    		txtResult.setText("Inserisci una durata");
    		return;
    	}
    	double d = 0;
    	try {
    		d = Double.parseDouble(input);
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserisci una durata in minuti");
    	}
    	Album a1 = cmbA1.getValue();
	   	if (a1== null) {
	   		txtResult.setText("Scegli un album");
	   		return ;
	   	}
    	Set<Album> set = model.trovaSet(a1, d);
    	for (Album a: set) {
    		txtResult.appendText("\n "+a );
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnComponente != null : "fx:id=\"btnComponente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSet != null : "fx:id=\"btnSet\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
