/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternCommandTest;

import complexnumber_calculator.ComplexNumber;
import java.util.Stack;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;
import org.junit.*;
import patternCommand.StackCommand;
import patternCommand.ClearCommand;


/**
 * Test class for Clear command
 *
 * @author Gropu 15
 */
public class ClearCommandTest {
    
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
        StackCommand command = new ClearCommand(stack);
        command.execute();
        assertEquals(0, stack.size());
    }
}
