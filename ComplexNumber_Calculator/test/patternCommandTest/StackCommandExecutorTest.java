/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternCommandTest;

import complexnumber_calculator.ComplexNumber;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import patternCommand.ClearCommand;
import patternCommand.DuplicateCommand;
import patternCommand.StackCommand;
import patternCommand.StackCommandExecutor;

/**
 *
 * @author Group 15
 */
public class StackCommandExecutorTest {
    
    private Deque<StackCommand> command_stack;
    private StackCommandExecutor executor;
    private Stack<ComplexNumber> stack;
    private StackCommand command;
    
    @Before
    public void setUp(){
       
        command_stack = new ArrayDeque<>();
        executor = new StackCommandExecutor(command_stack);
        stack = new Stack<>();
        
        stack.push(new ComplexNumber(0, 1));
        stack.push(new ComplexNumber(2, 45));
        stack.push(new ComplexNumber(-43, -1));
        stack.push(new ComplexNumber(23, -98));
    }
    
    @Test
    public void executeTest(){
        
        //FirstCommand
        command = new DuplicateCommand(stack);
        executor.execute(command);
        assertEquals(stack.pop(), stack.peek());
        
        //Second Command
        command = new ClearCommand(stack);
        executor.execute(command);
        assertEquals(0, stack.size());
    }
}
