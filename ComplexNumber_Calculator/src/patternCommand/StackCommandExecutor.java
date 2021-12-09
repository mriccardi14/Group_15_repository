/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternCommand;

import java.util.*;

/**
 *
 * @author Group 15
 */
public class StackCommandExecutor {
    
    private Deque<StackCommand> stack;

    public StackCommandExecutor(Deque<StackCommand> stack) {
        this.stack = new ArrayDeque<>();
    }
    
    public void execute(StackCommand command){
        stack.addLast(command);
        command.execute();
    }
}
