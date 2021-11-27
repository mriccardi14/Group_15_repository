/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package complexnumber_calculator;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for Graphical User Interface
 * 
 * @author Group 15
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private SplitPane rootPane;
    @FXML
    private AnchorPane paneTop;
    @FXML
    private TextArea textArea;
    @FXML
    private TableView<ComplexNumber> stack_value;
    @FXML
    private TableColumn<ComplexNumber, String> values_column;
    @FXML
    private AnchorPane paneDown;
    @FXML
    private Button insert_btn, add_btn, sub_btn, mul_btn, div_btn;
    @FXML
    private Button sqrt_btn;
    @FXML
    private Button dup_btn;
    @FXML
    private Button over_btn;
    @FXML
    private Button inverse_btn;
    @FXML
    private Button plusVar_btn;
    @FXML
    private Button drop_btn;
    @FXML
    private Button clear_btn;
    @FXML
    private Button subVar_btn;
    @FXML
    private Button swap_btn;
    @FXML
    private Button storeVar_btn;
    @FXML
    private Button exit_btn;
    @FXML
    private Button retrieve_btn;
    
    private ObservableList<ComplexNumber> values;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        values = FXCollections.observableArrayList();
        values_column.setCellValueFactory(new PropertyValueFactory("complexNumber"));
        stack_value.setItems(values);
        
//        insert_btn.disableProperty().bind(Bindings.when(textArea.textProperty().isEmpty()).then(true).otherwise(false));
        SimpleListProperty slpr = new SimpleListProperty(values);
        drop_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        clear_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        dup_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        storeVar_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        retrieve_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        swap_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        subVar_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        plusVar_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        inverse_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        over_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        sqrt_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        
        add_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        sub_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        mul_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        div_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
    }
   
    /**
     * Method associated with the stack insertion button
     * 
     * @param event 
     */
    @FXML
    private void insert_function(ActionEvent event) {
        
        if(textArea.getText().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error in insertion of a number");
            alert.setHeaderText(null);
            alert.setContentText("It hasn't been inserted any complex number");
            alert.showAndWait();
        }
            
        values.add(0,ComplexNumber.parseComplex(textArea.getText()));
        textArea.clear();
        
    }
    
    /**
     * Method associated with the SUM button 
     * 
     * @param event 
     */
    @FXML
    private void add_function(ActionEvent event) {
        
        List<ComplexNumber> list = new ArrayList<>();
        while(!values.isEmpty())
            list.add(values.remove(0));
        
        ComplexNumber result = new ComplexNumber();
        for(ComplexNumber z : list)
            result = Calculator.addition(result, z);
        
        values.add(result);    
    }
    
    /**
     * Method associated with the subtraction button 
     * 
     * @param event 
     */
    @FXML
    private void sub_function(ActionEvent event) {
        
        List<ComplexNumber> list = new ArrayList<>();
        while(!values.isEmpty())
            list.add(values.remove(0));
        
        ComplexNumber result = list.get(list.size()-1);
        for(int i=list.size()-2; i>=0; i--)
            result = Calculator.subtract(result, list.get(i));
        
        values.add(result);
    }

    /**
     * Method associated with the Multiply button 
     * 
     * @param event 
     */
    @FXML
    private void mul_function(ActionEvent event) {
        
        List<ComplexNumber> list = new ArrayList<>();
        while(!values.isEmpty())
            list.add(values.remove(0));
        
        ComplexNumber result = new ComplexNumber(1,0);
        for(ComplexNumber z : list)
            result = Calculator.multiply(result, z);
        
        values.add(result);
    }

    /**
     * Method associated with the Division button 
     * 
     * @param event 
     */
    @FXML
    private void div_function(ActionEvent event) {
        
        List<ComplexNumber> list = new ArrayList<>();
        while(!values.isEmpty())
            list.add(values.remove(0));
        
        ComplexNumber result = list.get(list.size()-1);
        for(int i=list.size()-2; i>=0; i--)
            result = Calculator.divide(result, list.get(i));
        
        values.add(result);
    }

    /**
     * Method associated with the Sqrt button 
     * 
     * @param event 
     */
    @FXML
    private void sqrt_function(ActionEvent event) {
    }
    
    /**
     * Method associated with the dup button 
     * 
     * @param event 
     */
    @FXML
    private void dup_function(ActionEvent event) {
        values.add(0,values.get(0));
    }

    /**
     * Method associated with the over button 
     * 
     * @param event 
     */
    @FXML
    private void over_function(ActionEvent event) {
    }

    /**
     * Method associated with the inverse button 
     * 
     * @param event 
     */
    @FXML
    private void inverse_function(ActionEvent event) {
    }

    /**
     * Method associated with the +X button 
     * 
     * @param event 
     */
    @FXML
    private void plusVar_function(ActionEvent event) {
    }
    
    /**
     * Method associated with the drop button 
     * 
     * @param event 
     */
    @FXML
    private void drop_function(ActionEvent event) {
        values.remove(0);    
    }
    
    /**
     * Method associated with the clear button 
     * 
     * @param event 
     */
    @FXML
    private void clear_function(ActionEvent event) {
        
        values.clear();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("clear Information");
        alert.setHeaderText(null);
        alert.setContentText("The Deletion of elements has been completed correctly");
        alert.showAndWait();
        
    }

    /**
     * Method associated with the -X button 
     * 
     * @param event 
     */
    @FXML
    private void subVar_function(ActionEvent event) {
    }

    /**
     * Method associated with the swap button 
     * 
     * @param event 
     */
    @FXML
    private void swap_function(ActionEvent event) {
    }
    
    /**
     * Method associated with the >X button 
     * 
     * @param event 
     */
    @FXML
    private void storeVar_function(ActionEvent event) {
    }
    
    /**
     * Method aociated with the exit button
     * 
     * @param event 
     */
    @FXML
    private void exit_function(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Method associated with the <X button 
     * 
     * @param event 
     */
    @FXML
    private void retrieve_function(ActionEvent event) {
    }
}