package it.polito.tdp.alien;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	AlienDictionary dizionario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInsert;

    @FXML
    private Button btnTranslate;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnClear;

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Welcome to Alien Dictionary v2019.");
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	
    	String s = txtInsert.getText();
    	String traduzione = "";
    	
    	String array[] = s.split(" ");
    	
    	if(array.length == 1) {
    		
    		try {
    			traduzione = dizionario.translateWord(s);
    		}catch(NullPointerException ne) {
    			txtResult.appendText(ne.getMessage());
    			return;
    		}
    		//txtResult.appendText("\nLa traduzione della parola aliena '"+array[0].toLowerCase()+"' è: "+traduzione+".");
    		txtResult.appendText(traduzione);

    		
    	}else if(array.length == 2) {
    		
    		String alienWord = array[0];
    		String translation = array[1];
    		
    		try {
    			dizionario.addWord(alienWord, translation);
    		}catch(InvalidParameterException ie) {
    			txtResult.appendText(ie.getMessage());
    			return;
    		}
    		txtInsert.clear();
    		
    	}else {
    		txtResult.appendText("\nInserire massimo due parole separate da uno spazio.");
    		
    	}
    	
    	

    }

    @FXML
    void initialize() {
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        
        dizionario = new AlienDictionary();

    }
    
}
