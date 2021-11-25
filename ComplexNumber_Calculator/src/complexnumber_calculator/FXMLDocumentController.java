/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package complexnumber_calculator;

import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
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
 *
 * @author
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
    private Button add_btn;
    @FXML
    private Button storeVar_btn;
    @FXML
    private Button mul_btn;
    @FXML
    private Button sub_btn;
    @FXML
    private Button div_btn;
    @FXML
    private Button equal_btn;
    @FXML
    private Button retrieve_btn;
    
    private ObservableList<ComplexNumber> values;
    @FXML
    private Button insert_btn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        values = FXCollections.observableArrayList();
        values_column.setCellValueFactory(new PropertyValueFactory("complexNumber"));
        stack_value.setItems(values);
        
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
    private void add_function(ActionEvent event) {
        
        ComplexNumber result = new ComplexNumber();
        
        for(ComplexNumber z : values)
            result = Calculator.add(result, z);
        
        values.clear();
        values.add(result);
        
    }
    
    @FXML
    private void sub_function(ActionEvent event) {
        
        ComplexNumber result = values.get(values.size()-1);
        
        for(int i=values.size()-2; i>=0; i--)
            result = Calculator.sub(result, values.get(i));
        
        values.clear();
        values.add(result);
        
    }

    @FXML
    private void mul_function(ActionEvent event) {
        
        ComplexNumber result = new ComplexNumber(1,1);
        
        for(ComplexNumber z : values)
            result = Calculator.multiply(result, z);
        
        values.clear();
        values.add(result);
    }

    @FXML
    private void div_function(ActionEvent event) {
        
        ComplexNumber result = values.get(values.size()-1);
        
        for(int i=values.size()-2; i>=0; i--)
            result = Calculator.divide(result, values.get(i));
        
        values.clear();
        values.add(result);
        
    }

    @FXML
    private void equal_function(ActionEvent event) {
    }

    @FXML
    private void retrieve_function(ActionEvent event) {
    }

    @FXML
    private void insert_function(ActionEvent event) {
        
        values.add(0,ComplexNumber.parseComplex(textArea.getText()));
        textArea.clear();
        
    }

}
