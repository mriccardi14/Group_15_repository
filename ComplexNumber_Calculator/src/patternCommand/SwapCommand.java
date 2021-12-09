/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternCommand;

import complexnumber_calculator.ComplexNumber;
import java.util.Stack;

/**
 *
 * @author Group 15
 */
public class SwapCommand implements StackCommand{
    
    private Stack<ComplexNumber> stack;

    public SwapCommand(Stack<ComplexNumber> stack) {
        this.stack = stack;
    }
    
    @Override
    public void execute() {
        ComplexNumber z1, z2;
        z1 = stack.pop();
        z2 = stack.pop();
        stack.push(z1);
        stack.push(z2);
    }
    
}
