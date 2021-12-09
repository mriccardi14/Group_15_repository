/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternCommandTest;

import complexnumber_calculator.ComplexNumber;
import java.util.Stack;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import patternCommand.DropCommand;
import patternCommand.StackCommand;

/**
 *
 * @author Group 15
 */
public class DropCommandTest {
    
    private Stack<ComplexNumber> stack;
    
    @Before
    public void setUp(){
        stack = new Stack<>();
        stack.push(new ComplexNumber(0, 1));
        stack.push(new ComplexNumber(2, 45));
        stack.push(new ComplexNumber(-43, -1));
        stack.push(new ComplexNumber(23, -98));
    }
    
    @Test
    public void executeTest(){
        StackCommand command = new DropCommand(stack);
        int len = stack.size();
        for(int i = 1; i <= len; i++){
            command.execute();
            assertEquals(len - i, stack.size());
        }
    }
}
