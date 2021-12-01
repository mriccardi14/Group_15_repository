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
import javafx.scene.control.ChoiceDialog;
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
    
    private ObservableList<ComplexNumber> values;   //Auxiliary Data Structure for the Calculator view 
    private Stack<ComplexNumber> stack;             //Auxiliary Data Structure for the Calculator memory   
    
    private Map<Character,ComplexNumber> variables;
    private static final int NUM_VARIABLES = 26;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        values = FXCollections.observableArrayList();
        values_column.setCellValueFactory(new PropertyValueFactory("complexNumber"));
        stack_value.setItems(values);
        stack = new Stack<>();
        variables = new HashMap<>();
        this.mapInitialize();
        
        this.viewInitialize();
    }
   
    /**
     * Support method for view initialization
     */
    private void viewInitialize(){
        
//      insert_btn.disableProperty().bind(Bindings.when(textArea.textProperty().isEmpty()).then(true).otherwise(false));   //soluzione 2 bindings
        SimpleListProperty slpr = new SimpleListProperty(values);
        
        add_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        sub_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        mul_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        div_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
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
        
    }
    
    /**
     * Support method for variable stack
     */
    private void mapInitialize(){
        
        for(int i=0; i<NUM_VARIABLES; i++)
            variables.put((char)(97+i), null);
  
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
            
        ComplexNumber z = ComplexNumber.parseComplex(textArea.getText());
        stack.push(z);
        values.add(0,z);
        textArea.clear();
        
    }
    
    /**
     * Method associated with the Sum button 
     * 
     * @param event 
     */
    @FXML
    private void add_function(ActionEvent event) {
        
        List<ComplexNumber> list = new ArrayList<>();
        while(!stack.isEmpty())
            list.add(stack.pop());
        
        values.clear();
        
        ComplexNumber result = new ComplexNumber();
        for(ComplexNumber z : list)
            result = Calculator.addition(result, z);
        
        stack.push(result);
        values.add(result);    
    }
    
    /**
     * Method associated with the Subtraction button 
     * 
     * @param event 
     */
    @FXML
    private void sub_function(ActionEvent event) {
        
        List<ComplexNumber> list = new ArrayList<>();
        while(!stack.isEmpty())
            list.add(stack.pop());
        
        values.clear();
        
        ComplexNumber result = list.get(list.size()-1);
        for(int i=list.size()-2; i>=0; i--)
            result = Calculator.subtract(result, list.get(i));
        
        stack.push(result);
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
        while(!stack.isEmpty())
            list.add(stack.pop());
        
        values.clear();
        
        ComplexNumber result = new ComplexNumber(1,0);
        for(ComplexNumber z : list)
            result = Calculator.multiply(result, z);
        
        stack.push(result);
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
        while(!stack.isEmpty())
            list.add(stack.pop());
        
        values.clear();
        
        ComplexNumber result = list.get(list.size()-1);
        for(int i=list.size()-2; i>=0; i--)
            result = Calculator.divide(result, list.get(i));
        
        stack.push(result);
        values.add(result);
    }

    /**
     * Method associated with the Sqrt button 
     * 
     * @param event 
     */
    @FXML
    private void sqrt_function(ActionEvent event) {
        ComplexNumber result = stack.peek();
        result = Calculator.root(result);
        values.clear();
        stack.push(result);
        values.add(result);
    }
    
    /**
     * Method associated with the Dup button 
     * 
     * @param event 
     */
    @FXML
    private void dup_function(ActionEvent event) {
        stack.push(stack.peek());
        values.add(0,values.get(0));
    }

    /**
     * Method associated with the Over button 
     * 
     * @param event 
     */
    @FXML
    private void over_function(ActionEvent event) {
        stack.push(stack.peek());
        values.add(0,values.get(1));
    }

    /**
     * Method associated with the Inverse button 
     * 
     * @param event 
     */
    @FXML
    private void inverse_function(ActionEvent event) {
        
        ComplexNumber result = stack.peek();
        result = Calculator.inverse(result);
        values.clear();
        stack.push(result);
        values.add(result);
        
    }
    
    /**
     * Method associated with the Drop button 
     *
     * @param event 
     */
    @FXML
    private void drop_function(ActionEvent event) {
        stack.pop();
        values.remove(0);    
    }
    
    /**
     * Method associated with the Clear button 
     * 
     * @param event 
     */
    @FXML
    private void clear_function(ActionEvent event) {
        
        stack.clear();
        values.clear();
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Clear Information");
        alert.setHeaderText(null);
        alert.setContentText("The deletion of elements has been completed correctly");
        alert.showAndWait();
        
    }

    /**
     * Method associated with the >X button 
     * 
     * @param event 
     */
    @FXML
    private void storeVar_function(ActionEvent event) {
            
            ChoiceDialog<Character> dialog = new ChoiceDialog<>('x',variables.keySet()); 
            dialog.setTitle("Store Variable"); 
            dialog.setHeaderText("Select the variable in which store the value");
            dialog.setContentText("Choose your variable:"); // Traditional way to get the response value. 
            Optional<Character> result = dialog.showAndWait(); 
            if (result.isPresent())
                variables.put(result.get(), stack.peek());
    }
    
    /**
     * Method associated with the <X button
     * 
     * @param event 
     */
    @FXML
    private void retrieve_function(ActionEvent event) {
        
            List<Character> listKey = new ArrayList<>();
            for(Character c : variables.keySet()){
                if(variables.get(c) != null)
                    listKey.add(c);
            }
                
            ChoiceDialog<Character> dialog = new ChoiceDialog<>(listKey.get(0), listKey); 
            dialog.setTitle("Retrieve Variable"); 
            dialog.setHeaderText("Select the variable from which retrieve the value");
            dialog.setContentText("Choose your variable:"); // Traditional way to get the response value. 
            Optional<Character> result = dialog.showAndWait(); 
            if (result.isPresent()){
                Character c = result.get();
                ComplexNumber z = variables.get(c);
                if(z != null){
                    stack.push(z);
                    values.add(0, z);
                }
            }
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
     * Method associated with the -X button 
     * 
     * @param event 
     */
    @FXML
    private void subVar_function(ActionEvent event) {
        List<Character> listKey = new ArrayList<>();
        for(Character c : variables.keySet()){
            if(variables.get(c) != null)
                listKey.add(c);
        }
        ComplexNumber z = new ComplexNumber();
        z = stack.peek();
        ChoiceDialog<Character> dialog = new ChoiceDialog<>('x',variables.keySet()); 
        dialog.setTitle("Sub Variable"); 
        dialog.setHeaderText("Select the variable in which the value is stored");
        dialog.setContentText("Choose your variable:");
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            Character c = result.get();
            ComplexNumber z1 = variables.get(c);
            if(z1 != null)
                variables.put(result.get(), Calculator.subtract(z1, z));
        }
    }
    
     /**
     * Method associated with the Swap button 
     * 
     * @param event 
     */
    @FXML
    private void swap_function(ActionEvent event) {
    }
    
    /**
     * Method aociated with the Exit button
     * 
     * @param event 
     */
    @FXML
    private void exit_function(ActionEvent event) {
        Platform.exit();
    }

    
}