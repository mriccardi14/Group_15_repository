/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternCommand;

import complexnumber_calculator.ComplexNumber;
import java.util.Stack;

/**
 * Class for the specific stack command: Duplicate
 *
 * @author Group 15
 */
public class DuplicateCommand implements StackCommand{
    
    private Stack<ComplexNumber> stack;

    public DuplicateCommand(Stack<ComplexNumber> stack) {
        this.stack = stack;
    }
    
    @Override
    public void execute() {
        stack.push(stack.peek());
    }
}
