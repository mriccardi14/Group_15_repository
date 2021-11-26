
import complexnumber_calculator.Calculator;
import complexnumber_calculator.ComplexNumber;
import static junit.framework.Assert.*;
import org.junit.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Group 15
 */
public class CalculatorTest {
     @Test
    public void MultiplyTest(){
        //First Case
        ComplexNumber z1 = new ComplexNumber(1,3);
        ComplexNumber z2 = new ComplexNumber(2,1);
        ComplexNumber z3 = new ComplexNumber(-1, 7);
        assertEquals(z3, Calculator.multiply(z1,z2));
        //Second Case
        ComplexNumber z4 = new ComplexNumber(3,0);
        ComplexNumber z5 = new ComplexNumber(0,5);
        ComplexNumber z6= new ComplexNumber(0, 15);
        assertEquals(z6, Calculator.multiply(z4,z5));
        assertEquals(z6, Calculator.multiply(z5,z4));
        //Third Case
        ComplexNumber z7 = new ComplexNumber(0,7);
        ComplexNumber z8 = new ComplexNumber(7,0);
        ComplexNumber z9 = new ComplexNumber(0,49);
        assertEquals(z9, Calculator.multiply(z7,z8));
    }
    @Test
    public void InverseTest(){
        //First Case
        ComplexNumber z1 = new ComplexNumber(3,6);
        ComplexNumber z2 = new ComplexNumber(-3,-6);
        assertEquals(z2, Calculator.inverse(z1));
        //Second Case
        ComplexNumber z3 = new ComplexNumber(0,12);
        ComplexNumber z4 = new ComplexNumber(0,-12);
        assertEquals(z4, Calculator.inverse(z3));
    }
    @Test
    public void DivideTest(){
        //First Case
        ComplexNumber z1 = new ComplexNumber(1.5,3.7);
        ComplexNumber z2 = new ComplexNumber(2.33,1.87);
        ComplexNumber z3 = new ComplexNumber(1.16673, 0.65159);
        assertEquals(z3, Calculator.divide(z1,z2));
        //Second Case
        ComplexNumber z4 = new ComplexNumber(3,0);
        ComplexNumber z5 = new ComplexNumber(0,5);
        ComplexNumber z6= new ComplexNumber(0, -0.6);
        assertEquals(z6, Calculator.divide(z4,z5));
        //Third Case
        ComplexNumber z7 = new ComplexNumber(0,7);
        ComplexNumber z8 = new ComplexNumber(7,0);
        ComplexNumber z9 = new ComplexNumber(0,1);
        assertEquals(z9, Calculator.divide(z7,z8));
    }
    @Test
    public void SumTest(){
        ComplexNumber  z1 = new ComplexNumber(1.5,3.7);
        ComplexNumber z2 = new ComplexNumber(2.33,1.87);
        ComplexNumber z3 = new ComplexNumber(1.16673, 0.65159);
        assertEquals(z3, Calculator.add(z1,z2));
    
    }
    @Test
    public void SubTest(){
        ComplexNumber  z1 = new ComplexNumber(1.5,3.7);
        ComplexNumber z2 = new ComplexNumber(2.33,1.87);
        ComplexNumber z3 = new ComplexNumber(1.16673, 0.65159);
        assertEquals(z3, Calculator.sub(z1,z2));
    }
}
