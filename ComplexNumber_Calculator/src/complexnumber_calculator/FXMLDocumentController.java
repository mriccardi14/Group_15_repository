/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package complexnumber_calculator;

import patternCommand.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private TableView<ComplexNumber> stack_value;
    @FXML
    private TableColumn<ComplexNumber, String> values_column;
    @FXML
    private TableView<Variable> var_tab;
    @FXML
    private TableColumn<Character, Character> var_column;
    @FXML
    private TableColumn<ComplexNumber, String> val_column;
    @FXML
    private Button insert_btn, add_btn, sub_btn, mul_btn, div_btn, inverse_btn, sqrt_btn;
    @FXML
    private Button clear_btn, drop_btn, dup_btn, swap_btn, over_btn;
    @FXML
    private Button storeVar_btn, retrieveVar_btn, plusVar_btn, subVar_btn;
    @FXML
    private Button ins_op_btn, retr_op_btn, modify_op_btn, delete_op_btn, exec_op_btn;
    @FXML
    private Button mod_btn, arg_btn, pow_btn, log_btn, exp_btn, sin_btn, cos_btn, tan_btn, arcsin_btn, arccos_btn, arctan_btn; 
    @FXML
    private CheckBox def_op_ckb;
    @FXML
    private MenuItem save_btn, load_btn;
    
    private ObservableList<ComplexNumber> values;   //Auxiliary Data Structure for the Calculator view 
    private Stack<ComplexNumber> stack;             //Auxiliary Data Structure for the Calculator memory   
    private StackCommandExecutor executor;
    private Deque<StackCommand> stackCommands;
            
    private List<Character> listKeys;
    private ObservableList<Variable> variables;
    private Map<Character, ComplexNumber> map_var;
    private Stack<Variable> variable_stack;
    private static final int NUM_VARIABLES = 26;
    
    private Map<String,String> userOperations;
    @FXML
    private AnchorPane paneTop;
    @FXML
    private Menu file_menu;
    @FXML
    private MenuItem exit_btn;
    @FXML
    private AnchorPane paneDown;
    @FXML
    private Button clc_btn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        values = FXCollections.observableArrayList();
        values_column.setCellValueFactory(new PropertyValueFactory("complexNumber"));
        stack_value.setItems(values);
        
        variables = FXCollections.observableArrayList();
        var_column.setCellValueFactory(new PropertyValueFactory("key"));
        val_column.setCellValueFactory(new PropertyValueFactory("valueS"));
        var_tab.setItems(variables);
        
        executor = new StackCommandExecutor(stackCommands);
        
        map_var = new HashMap<>();
        stack = new Stack<>();
        listKeys = new ArrayList<>();
        
        this.dataSetInitialize();
        this.viewInitialize();
        
        variable_stack = new Stack<>();
        userOperations = new HashMap<>();
        
        userOperations.put("Solve1Degree", "save >b >a <b inverse <a / restore");
        userOperations.put("Solve2Degree", "save >c >b >a <b <b * 4 <a <c * * - sqrt >d <b inverse <d - 2 <a * / <b inverse <d + 2 <a * / restore");
        userOperations.put("Hypothenuse", "save >b >a <a <a * <b <b * + sqrt restore");
        
    }
   
    /**
     * Support method for view initialization
     */
    private void viewInitialize(){
       
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
        mod_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        arg_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        pow_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        log_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        exp_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        sin_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        cos_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        tan_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        arcsin_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        arccos_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        arctan_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        
        plusVar_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        subVar_btn.disableProperty().bind(Bindings.when(slpr.emptyProperty()).then(true).otherwise(false));
        
        insert_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(true).otherwise(false));
        exec_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        ins_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        retr_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        modify_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        delete_op_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        save_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        load_btn.disableProperty().bind(Bindings.when(def_op_ckb.selectedProperty()).then(false).otherwise(true));
        
    }
    
    /**
     * Support method for variables stack
     */
    private void dataSetInitialize(){
        
        for(int i=0; i<NUM_VARIABLES; i++){
            variables.add(new Variable((char)(97+i), new ComplexNumber()));
            map_var.put((char)(97+i), new ComplexNumber());
            listKeys.add((char)(97+i));
        }
  
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
        
        StackCommand command = new ClearCommand(stack);
        executor.execute(command);
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
        
        StackCommand command = new DropCommand(stack);
        executor.execute(command);
        values.remove(0);
        
    }
    
    /**
     * Method associated with the Dup button 
     * 
     * @param event 
     */
    @FXML
    private void dup_function(ActionEvent event) {
        
        StackCommand command = new DuplicateCommand(stack);
        executor.execute(command);
        values.add(0,values.get(0));
    }
    
    /**
     * Method associated with the Swap button 
     * 
     * @param event 
     */
    @FXML
    private void swap_function(ActionEvent event) {
        
        StackCommand command = new SwapCommand(stack);
        executor.execute(command);
        values.add(1,values.remove(0));
    }

    /**
     * Method associated with the Over button 
     * 
     * @param event 
     */
    @FXML
    private void over_function(ActionEvent event) {
        
        StackCommand command = new OverCommand(stack);
        executor.execute(command);
        values.add(0,values.get(1));
    }
    
    /*------------------ Variable Manipulation Functions ------------------*/

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
            Character c = result.get();
            this.support_storeVar(c);
        }
    }
    
    /**
     * Support method for execute the storeVar_function method
     * 
     * @param c variable read from the ChoiceDialog
     */
    private void support_storeVar(Character c){
        
        ComplexNumber z = stack.pop();
        values.remove(0);
        map_var.put(c, z);
        for(Variable var: variables){
            if(c.equals(var.getKey())){
                var.setValue(z);
                break;
            }
        }
    }
    
    /**
     * Method associated with the <X button
     * 
     * @param event 
     */
    @FXML
    private void retrieveVar_function(ActionEvent event) {
        
        ChoiceDialog<Character> dialog = new ChoiceDialog<>(listKeys.get(0), listKeys); 
        dialog.setTitle("Retrieve Variable"); 
        dialog.setHeaderText("Select the variable from which retrieve the value");
        dialog.setContentText("Choose your variable:"); // Traditional way to get the response value. 
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            Character c = result.get();
            this.support_retrieveVar(c);
        }
    }
    
    /**
     * Support method for execute the retrieveVar_function method
     * 
     * @param c variable read from the ChoiceDialog
     */
    private void support_retrieveVar(Character c){

        ComplexNumber z = map_var.get(c);
        if(z != null){
            stack.push(z);
            values.add(0, z);
        }
    }
    
    /**
     * Method associated with the +X button 
     * 
     * @param event 
     */
    @FXML
    private void plusVar_function(ActionEvent event) {
        
        ChoiceDialog<Character> dialog = new ChoiceDialog<>(listKeys.get(0), listKeys); 
        dialog.setTitle("Add Variable"); 
        dialog.setHeaderText("Select the variable in which the value is stored");
        dialog.setContentText("Choose your variable:");
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            Character c = result.get();
            map_var.put(c, Calculator.addition(map_var.get(c), stack.peek()));
            for(Variable var: variables){
                if(c.equals(var.getKey())){
                    var.setValue(Calculator.addition(var.getValueC(), stack.peek()));
                    break;
                }
            }   
        }
    }
    
    /**
     * Method associated with the -X button 
     * 
     * @param event 
     */
    @FXML
    private void subVar_function(ActionEvent event) {
        
        ChoiceDialog<Character> dialog = new ChoiceDialog<>(listKeys.get(0), listKeys); 
        dialog.setTitle("Sub Variable"); 
        dialog.setHeaderText("Select the variable in which the value is stored");
        dialog.setContentText("Choose your variable:");
        Optional<Character> result = dialog.showAndWait(); 
        if (result.isPresent()){
            Character c = result.get();
            map_var.put(c, Calculator.subtract(map_var.get(c), stack.peek()));
            for(Variable var: variables){
                if(c.equals(var.getKey())){
                    var.setValue(Calculator.subtract(var.getValueC(), stack.peek()));
                    break;
                }
            }
        }
    }
    
    /**
     * Method that save the all variables' value on the variable stack
     * 
     * @param event 
     */
    @FXML
    private void save_var_function(ActionEvent event) {
        
        for(Character c: map_var.keySet()){
            variable_stack.push(new Variable(c, map_var.get(c)));
        }
    }

    /**
     * Method that restore the all variables' value from the variable stack
     * 
     * @param event 
     */
    @FXML
    private void restore_var_function(ActionEvent event) {
        
        if(variable_stack.isEmpty()){
            String title = "Error in retrieving of variables' old values";
            String context = "There aren't previous saved copies";
            this.alertMessage(AlertType.ERROR, title, null, context);
            return;
        }
        variables.clear();
        for(int i = 0; i<NUM_VARIABLES; i++){
            Variable var = variable_stack.pop();
            map_var.put(var.getKey(), var.getValueC());
            variables.add(0, var);
        }
    }

    
    /*------------------ Defined Operation Manipulation ------------------*/
    
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
                for(String op : user_op){ System.err.println(op + " | " );
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
                        case "inverse":
                            this.inverse_function(null);
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
                        case "save":
                            this.save_var_function(null);
                            break;
                        case "restore":
                            this.restore_var_function(null);
                            break;
                        default:
                            if(op.startsWith(">")){
                                this.support_storeVar(op.charAt(1));
                                break;
                            }
                            if(op.startsWith("<")){
                                this.support_retrieveVar(op.charAt(1));
                                break;
                            }
                            ComplexNumber z = ComplexNumber.parseComplex(op);
                            stack.push(z);
                            values.add(0,z);
                            textArea.clear();
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
     * 
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
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Method associated with the "Modify Operation" button of the menu, 
     * that allows the user to modify a prevoius defined operation
     * 
     * @param event 
     */
    @FXML
    private void modify_op_function(ActionEvent event) {
        
        if(textArea.getText().isEmpty()){
            String title = "Error in modify an operation";
            String context = "It hasn't been inserted any operation";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
        else{
            userOperations.put(textField.getText(), textArea.getText());
            textArea.clear();
            textField.clear();
        }
    }

     /**
     * Method associated with the "Delete Operation" button of the menu, that allows the user
     * to delete from a prevoius saved file a defined operation 
     * 
     * @param event 
     */
    @FXML
    private void delete_op_func(ActionEvent event) {
        
        if(textField.getText().isEmpty()){
            String title = "Error in deleting an operation";
            String context = "It hasn't been specified the name of the operation";
            this.alertMessage(AlertType.ERROR, title, null, context);
        }
        else{
            String operation = userOperations.get(textField.getText());
            if(operation != null){
                userOperations.remove(textField.getText());
                textField.clear();
                String title = "Remove user operation information";
                String context = "The deletion of the user operation has been completed correctly";
                this.alertMessage(AlertType.INFORMATION, title, null, context);
            }
            else{
                String title = "Error in deleting an operation";
                String context = "The specified operation hasn't been found";
                this.alertMessage(AlertType.ERROR, title, null, context);
            }
        }
        
    }

    /*------------------ Trascendental Functions ------------------*/
    
    /**
     * Method associated with the Module button that calculates the module 
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void mod_function(ActionEvent event) {
        
        ComplexNumber z = stack.pop(), complex_result;
        complex_result = new ComplexNumber(Calculator.mod(z), 0);
        values.remove(0);
        stack.push(complex_result);
        values.add(0,complex_result);
    }

    /**
     * Method associated with the Argument button that calculates the argument 
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void arg_function(ActionEvent event) {
        
        ComplexNumber z = stack.pop(), complex_result;
        complex_result = new ComplexNumber(Calculator.arg(z), 0);
        values.remove(0);
        stack.push(complex_result);
        values.add(0,complex_result);
    }

    /**
     * Method associated with Pow button that calculates the pow
     * of the complex number from top of the stack
     * 
     * @param event 
     */
    @FXML
    private void pow_function(ActionEvent event) {
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Power function");
        dialog.setHeaderText("");
        dialog.setContentText("Insert exponent");
        Optional<String> result = dialog.showAndWait();
        
        if(result.isPresent()){
            ComplexNumber z = Calculator.pow(stack.pop(), Integer.parseInt(result.get()));
            values.remove(0);
            stack.push(z);
            values.add(0,z);
        }            
    }

    /**
     * Method associated with the Logarithm button that calculates the logarithm
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void log_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.log(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }

    /**
     * Method associated with the Exponential button that computes the 
     * exponential e^(a+bi), where (a+bi) is the complex number from the top
     * of the stack
     * 
     * @param event 
     */
    @FXML
    private void exp_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.exp(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }

    /**
     * Method associated with the Sine button that calculates the sine 
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void sin_function(ActionEvent event) {   
        ComplexNumber result = stack.pop();
        result = Calculator.sin(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }
    
    /**
     * Method associated with the Cosine button that calculates the cosine 
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void cos_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.cos(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }
    
    /**
     * Method associated with the Tan button that calculates the tangent 
     * of the complex number from the top of the stack 
     * 
     * @param event 
     */
    @FXML
    private void tan_function(ActionEvent event) {
        ComplexNumber result = stack.pop();
        result = Calculator.tan(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }

    
    /**
     * Method associated with the Arcsine button that calculates the arcsine 
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void arcsin_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.arcsin(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }

    /**
     * Method associated with the Arccosine button that calculates the arccosine 
     * of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void arccos_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.arccos(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }

    /**
     * Method associated with the Arctangent button that calculates the
     * arctangent of the complex number from the top of the stack
     * 
     * @param event 
     */
    @FXML
    private void arctan_function(ActionEvent event) {
        
        ComplexNumber result = stack.pop();
        result = Calculator.arctan(result);
        values.remove(0);
        stack.push(result);
        values.add(0,result);
    }  

    @FXML
    private void eq_1_degree_functions(ActionEvent event) {
        
        textField.setText("Solve1Degree");
        textArea.setText(userOperations.get("Solve1Degree"));
    }

    @FXML
    private void eq_2_degree_function(ActionEvent event) {
        
        textField.setText("Solve2Degree");
        textArea.setText(userOperations.get("Solve2Degree"));
    }

    @FXML
    private void hyp_eq_function(ActionEvent event) {
        
        textField.setText("Hypothenuse");
        textArea.setText(userOperations.get("Hypothenuse"));
    }

    @FXML
    private void clc_function(ActionEvent event) {
        
        textArea.clear();
    }
}