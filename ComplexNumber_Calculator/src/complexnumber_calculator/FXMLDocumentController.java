/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package complexnumber_calculator;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button dub_btn;
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
    private Button equal_btn;
    @FXML
    private Button retrieve_btn;
    
    private ObservableList<ComplexNumber> values;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        values = FXCollections.observableArrayList();
        values_column.setCellValueFactory(new PropertyValueFactory("complexNumber"));
        stack_value.setItems(values);
        
    }
    
    @FXML
    private void insert_function(ActionEvent event) {
        
        values.add(0,ComplexNumber.parseComplex(textArea.getText()));
        textArea.clear();
        
    }
    
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

    @FXML
    private void sqrt_function(ActionEvent event) {
    }

    @FXML
    private void dup_function(ActionEvent event) {
    }

    @FXML
    private void over_function(ActionEvent event) {
    }

    @FXML
    private void inverse_function(ActionEvent event) {
    }

    @FXML
    private void plusVar_function(ActionEvent event) {
    }

    @FXML
    private void drop_function(ActionEvent event) {
        values.remove(values.size()-1);
    }

    @FXML
    private void clear_function(ActionEvent event) {
    }

    @FXML
    private void subVar_function(ActionEvent event) {
    }

    @FXML
    private void swap_function(ActionEvent event) {
    }
    
    @FXML
    private void storeVar_function(ActionEvent event) {
    }
    
    @FXML
    private void equal_function(ActionEvent event) {
    }

    @FXML
    private void retrieve_function(ActionEvent event) {
    }
}