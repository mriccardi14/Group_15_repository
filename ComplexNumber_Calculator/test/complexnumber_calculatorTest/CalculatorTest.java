package complexnumber_calculatorTest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import complexnumber_calculator.Calculator;
import complexnumber_calculator.ComplexNumber;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Test Class for Calculator Class
 * 
 * @author Group 15
 */
public class CalculatorTest {
    
    private ComplexNumber z1, z2, z3, z4;
    
    @Before
    public void setUp(){
        z1 = new ComplexNumber();
        z2 = new ComplexNumber(1, -4);
        z3 = new ComplexNumber(-8, -17);
        z4 = new ComplexNumber(90, -38);
    }
    
    @Test
    public void conjugateTest(){
    //First Case
        z1.setIm(-1);
        assertEquals(new ComplexNumber(0, 1), Calculator.conjugate(z1));
    //Second Case
        assertEquals(new ComplexNumber(1, 4), Calculator.conjugate(z2));
    //Third Case
        assertEquals(new ComplexNumber(-8, 17), Calculator.conjugate(z3));
    //Fourth Case
        assertEquals(new ComplexNumber(90, 38), Calculator.conjugate(z4));
    }
    
    @Test
    public void modTest(){
    //First Case
        assertEquals(0.0, Calculator.mod(z1));
    //Second Case
        assertEquals(4.123105625617661, Calculator.mod(z2));
    //Third Case
        assertEquals(18.788294228055936, Calculator.mod(z3));
    //Fourth Case
        assertEquals(97.6933979345585, Calculator.mod(z4));
    }
    
    @Test
    public void SumTest(){
        ComplexNumber z1 = new ComplexNumber(1.5,3.7);
        ComplexNumber z2 = new ComplexNumber(2.33,1.87);
        ComplexNumber z3 = new ComplexNumber(3.83, 5.57);
        assertEquals(z3, Calculator.addition(z1,z2));
    }
    
    @Test
    public void SubTest(){
        ComplexNumber z1 = new ComplexNumber(5.33,3.7);
        ComplexNumber z2 = new ComplexNumber(2.33,1.7);
        ComplexNumber z3 = new ComplexNumber(3, 2);
        assertEquals(z3, Calculator.subtract(z1,z2));
    }
    
    @Test
    public void MultiplyTest(){
        ComplexNumber z12 = new ComplexNumber(10,10);
        ComplexNumber z22 = new ComplexNumber(20,20);
        ComplexNumber z32 = new ComplexNumber(0, 400);
        assertEquals(z32, Calculator.multiply(z12,z22));
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
    public void RootTest(){
        //First Case
        ComplexNumber z1 = new ComplexNumber(1.2,-3.5);
        ComplexNumber z2 = new ComplexNumber(1.56525, -1.11803);
        assertEquals(z2, Calculator.root(z1));
        //Second Case
        ComplexNumber z3 = new ComplexNumber(0, 353);
        ComplexNumber z4 = new ComplexNumber(13.28533, 13.28533);
        assertEquals(z4, Calculator.root(z3));
        //Third Case
        ComplexNumber z5 = new ComplexNumber(-3.7,-9.4);
        ComplexNumber z6 = new ComplexNumber(1.78913, -2.62697);
        assertEquals(z6, Calculator.root(z5));
    }
}