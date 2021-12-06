/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package complexnumber_calculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
    private Button insert_btn, add_btn, sub_btn, mul_btn, div_btn, inverse_btn, sqrt_btn;
    @FXML
    private Button clear_btn, drop_btn, dup_btn, swap_btn, over_btn;
    @FXML
    private Button storeVar_btn, retrieve_btn, plusVar_btn, subVar_btn;
    @FXML
    private Button exec_op_btn;
    @FXML
    private CheckBox def_op_ckb;
    @FXML
    private MenuItem save_btn;
    @FXML
    private MenuItem load_btn;
    
    private ObservableList<ComplexNumber> values;   //Auxiliary Data Structure for the Calculator view 
    private Stack<ComplexNumber> stack;             //Auxiliary Data Structure for the Calculator memory   
    
    private List<Character> listKeys;
    private Map<Character,ComplexNumber> variables;
    private ObservableMap<Character,ComplexNumber> observable_variables;
    private static final int NUM_VARIABLES = 26;
    
    private Map<String,String> userOperations;
    
    @FXML
    private Button other_btn;
    @FXML
    private Menu file_menu;
    @FXML
    private MenuItem exit_btn;
    @FXML
    private Button ins_op_btn;
    @FXML
    private TextField textField;
    @FXML
    private Button retr_op_btn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        values = FXCollections.observableArrayList();
        values_column.setCellValueFactory(new PropertyValueFactory("complexNumber"));
        stack_value.setItems(values);
        stack = new Stack<>();
        listKeys = new ArrayList<>();
        variables = new HashMap<>();     //Forse da rendere ordinata
        observable_variables= FXCollections.observableHashMap();
        this.listInitialize();
        this.viewInitialize();
        userOperations = new HashMap<>();
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
        inverse_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        sqrt_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        clear_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        drop_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        dup_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        swap_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        over_btn.disableProperty().bind(Bindings.when(Bindings.lessThan(slpr.sizeProperty(), 2)).then(true).otherwise(false));
        storeVar_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        
        SimpleMapProperty smpr = new SimpleMapProperty(observable_variables);
        
        plusVar_btn.disableProperty().bind(Bindings.when(smpr.emptyProperty()).then(true).otherwise(false));
        subVar_btn.disableProperty().bind(Bindings.when(smpr.emptyProperty()).then(true).otherwise(false));
        retrieve_btn.disableProperty().bind(Bindings.when(smpr.emptyProperty()).then(true).otherwise(false));
        
        insert_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(true).otherwise(false));
        exec_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        ins_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        retr_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        save_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        load_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        
    }
    
    /**
     * Support method for variable stack
     */
    private void listInitialize(){
        
        for(int i=0; i<NUM_VARIABLES; i++)
            listKeys.add((char)(97+i));
  
    }
    
    /**
     * Methods for the visualization of a message from the GUI
     * 
     * @param type    type of the information message
     * @param title   title of the information message
     * @param header  header of the information message
     * @param context context of the information message
     */
    private void alertMessage(AlertType type, String title, String header, String context){
        
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();
        
    }
    
        
    /*------------------ Exit Calculator Functions ------------------*/
    
    /**
     * Method associated with the Exit button
     * 
     * @param event 
     */
    @FXML
    private void exit_function(ActionEvent event) {
        Platform.exit();
    }
    
    
    /*------------------ Basic Operations Functions ------------------*/
    
    /**
     * Method associated with the stack insertion button
     * 
     * @param event 
     */
    @FXML
    private void insert_function(ActionEvent event) {
        
        if(textArea.getText().isEmpty()){
            String title = "Error in insertion of a number";
            String context = "It hasn't been inserted any complex number";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
        else{
            
            ComplexNumber z = ComplexNumber.parseComplex(textArea.getText());
            stack.push(z);
            values.add(0,z);
            textArea.clear();
        }
    }
    
    /**
     * Method associated with the Sum button 
     * 
     * @param event 
     */
    @FXML
    private void add_function(ActionEvent event) {
        
        ComplexNumber result;
        result = Calculator.addition(stack.pop(), stack.pop());
        
        values.remove(0, 2);   
        stack.push(result);
        values.add(0,result);    
    }
    
    /**
     * Method associated with the Subtraction button 
     * assuming subtracting is the last element of the stack 
     * and minuendo is the second last element of the stack
     * 
     * @param event 
     */
    @FXML
    private void sub_function(ActionEvent event) {
        
        ComplexNumber result, minuendo, subtracting;
        
        subtracting = stack.pop();
        minuendo = stack.pop();
        result = Calculator.subtract(minuendo,subtracting);
        
        values.remove(0, 2);   
        stack.push(result);
        values.add(0,result);
    }

    /**
     * Method associated with the Multiply button 
     * 
     * @param event 
     */
    @FXML
    private void mul_function(ActionEvent event) {
        
        ComplexNumber result;
        result = Calculator.multiply(stack.pop(), stack.pop());
        
        values.remove(0, 2);   
        stack.push(result);
        values.add(0,result);  
    }

    /**
     * Method associated with the Division button,
     * assuming divider is the last element of the stack 
     * and dividend is the second last element of the stack
     * 
     * @param event 
     */
    @FXML
    private void div_function(ActionEvent event) {
        
        ComplexNumber result, dividend, divider;
        
        divider = stack.pop();
        dividend = stack.pop();
        result = Calculator.divide(dividend,divider);
        
        values.remove(0, 2);   
        stack.push(result);
        values.add(0,result);  
    }
    
    /**
     * Method associated with the Inverse button 
     * 
     * @param event 
     */
    @FXML
    private void inverse_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.inverse(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
        
    }

    /**
     * Method associated with the Sqrt button 
     * 
     * @param event 
     */
    @FXML
    private void sqrt_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.root(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }
    
    
    /*------------------ Stack Manipulation Functions ------------------*/
    
    /**
     * Method associated with the Clear button 
     * 
     * @param event 
     */
    @FXML
    private void clear_function(ActionEvent event) {
        
        stack.clear();
        values.clear();
        
        String title = "Clear Information";
        String context = "The deletion of elements has been completed correctly";
        this.alertMessage(AlertType.INFORMATION, title, null, context);
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
     * Method associated with the Swap button 
     * 
     * @param event 
     */
    @FXML
    private void swap_function(ActionEvent event) {
        ComplexNumber z1, z2;
        z1 = stack.pop();
        z2 = stack.pop();
        stack.push(z1);
        stack.push(z2);
        values.add(1,values.remove(0));
    }

    /**
     * Method associated with the Over button 
     * 
     * @param event 
     */
    @FXML
    private void over_function(ActionEvent event) {
        ComplexNumber z1,z2;
        z1 = stack.pop();
        z2 = stack.peek();
        stack.push(z1);
        stack.push(z2);
        values.add(0,values.get(1));
    }
    
    /*------------------ Variables Manipulation Functions ------------------*/

    /**
     * Method associated with the >X button 
     * 
     * @param event 
     */
    @FXML
    private void storeVar_function(ActionEvent event) {
            
        ChoiceDialog<Character> dialog = new ChoiceDialog<>('x',listKeys); 
        dialog.setTitle("Store Variable"); 
        dialog.setHeaderText("Select the variable in which store the value");
        dialog.setContentText("Choose your variable:"); // Traditional way to get the response value. 
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            variables.put(result.get(), stack.peek());
            observable_variables.put(result.get(), stack.peek());
        }
    }
    
    /**
     * Method associated with the <X button
     * 
     * @param event 
     */
    @FXML
    private void retrieve_function(ActionEvent event) {
        
        List<Character> KeyUsed = new ArrayList<>();
        for(Character c : variables.keySet()){
            if(variables.get(c) != null)
                KeyUsed.add(c);
        }

        ChoiceDialog<Character> dialog = new ChoiceDialog<>(KeyUsed.get(0), KeyUsed); 
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
        List<Character> KeyUsed = new ArrayList<>();
        for(Character c : variables.keySet()){
            if(variables.get(c) != null)
                KeyUsed.add(c);
        }
        ChoiceDialog<Character> dialog = new ChoiceDialog<>(KeyUsed.get(0), KeyUsed); 
        dialog.setTitle("Add Variable"); 
        dialog.setHeaderText("Select the variable in which the value is stored");
        dialog.setContentText("Choose your variable:");
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            Character c = result.get();
            ComplexNumber z = variables.get(c);
            if(z != null)
                variables.put(result.get(), Calculator.addition(z, stack.peek()));
        }
    }
    
    /**
     * Method associated with the -X button 
     * 
     * @param event 
     */
    @FXML
    private void subVar_function(ActionEvent event) {
        List<Character> KeyUsed = new ArrayList<>();
        for(Character c : variables.keySet()){
            if(variables.get(c) != null)
                KeyUsed.add(c);
        }
        ChoiceDialog<Character> dialog = new ChoiceDialog<>(KeyUsed.get(0), KeyUsed); 
        dialog.setTitle("Sub Variable"); 
        dialog.setHeaderText("Select the variable in which the value is stored");
        dialog.setContentText("Choose your variable:");
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            Character c = result.get();
            ComplexNumber z = variables.get(c);
            if(z != null)
                variables.put(result.get(), Calculator.subtract(z, stack.peek()));
        }
    }

    /**
     * Method associated with the user defined operation, that executes in 
     * sequence the indicated operations on the GUI on the stack's data and
     * visualizes the result
     * 
     * @param event 
     */
    @FXML
    private void exec_op_function(ActionEvent event) {
        
        if(textArea.getText().isEmpty()){
            String title = "Error in insertion of user operation";
            String context = "It hasn't been inserted any operator";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
        else{
            if(stack.isEmpty()){
                String title = "Error in execution of user operation";
                String context = "Stack empty, insert the operands for the operations";
                this.alertMessage(AlertType.ERROR, title, null, context);
            }
            else{
                String[] user_op = textArea.getText().split(" ");
                for(String op : user_op){
                    switch(op){
                        case "+":
                            this.add_function(null);
                            break;
                        case "-": 
                            this.sub_function(null);    
                            break;
                        case "*": 
                            this.mul_function(null);    
                            break;
                        case "/": 
                            this.div_function(null);    
                            break;    
                        case "sqrt": 
                            this.sqrt_function(null);    
                            break;
                        case "dup": 
                            this.dup_function(null);    
                            break;        
                        case "swap": 
                            this.swap_function(null);    
                            break;    
                        case "over":
                            this.over_function(null);
                            break;
                    }
                }
                textArea.clear();
            }
        }
    }
    
    /**
     * Method associated with the user defined operation, that inserts 
     * the indicated operation on the GUI in the Map of user operations using 
     * as key the name of operation and as value the operation itself
     * 
     * @param event 
     */
    @FXML
    private void insert_op_function(ActionEvent event) {
        
        if(textField.getText().isEmpty() || textArea.getText().isEmpty()){
            String title = "Error in insertion of user operation";
            String context = "It hasn't been inserted any operator or the name of the operation";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
        else{
            userOperations.put(textField.getText(), textArea.getText());
            textField.clear();
            textArea.clear();
        }
    }

    /**
     * Method associated with the user defined operation, that retrieves from
     * Map of user operations the operation associated with the name
     * indicated on the GUI
     * 
     * @param event 
     */
    @FXML
    private void retrieve_op_function(ActionEvent event) {
        
        if(textField.getText().isEmpty()){
            String title = "Error in retrieving an operation";
            String context = "It hasn't been specified the name of the operation";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
        else{
            String operation = userOperations.get(textField.getText());
            if(operation != null){
                textArea.setText(operation);
                textField.clear();
            }
            else{
                String title = "Error in retrieving an operation";
                String context = "The specified operation hasn't been found";
                this.alertMessage(AlertType.ERROR, title, null, context);
            }
        }
    }
    
    /**
     * Method associated with the save button of the menu, that allows the user
     * to save on a file a defined operation
     * @param event 
     */
    @FXML
    private void save_opn_function(ActionEvent event) {
        
         if(textArea.getText().isEmpty()){
            String title = "Error in insertion of user operation";
            String context = "It hasn't been inserted any operator";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
         else{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File as...");
            File file = fileChooser.showSaveDialog(rootPane.getScene().getWindow());

            if(file!=null)
                try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))){
                    String user_op = textArea.getText().replaceAll(" ", "|");
                    pw.write(user_op);
                } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Method associated with the load button of the menu, that allows the user
     * to reload from a prevoius saved file a defined operation 
     * 
     * @param event 
     */
    @FXML
    private void load_opn_function(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        
        try(Scanner in = new Scanner(new BufferedReader(new FileReader(file)))){
            String op = in.next().replaceAll("\\|", " ");
            textArea.setText(op);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void other_function(ActionEvent event) {
    }

}