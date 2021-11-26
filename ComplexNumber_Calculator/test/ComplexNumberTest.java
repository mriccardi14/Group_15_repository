/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import complexnumber_calculator.ComplexNumber;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Test Class for Complex Number Class 
 * 
 * @author Group 15
 */
public class ComplexNumberTest {
    private ComplexNumber z1, z2, z3, z4;
    
    @Before
    public void setUp(){
        z1 = new ComplexNumber();
        z2 = new ComplexNumber(1, -4);
        z3 = new ComplexNumber(-8, -17);
        z4 = new ComplexNumber(90, -38);
    }
    
    @Test
    public void getterTest(){
    //First Case
        assertEquals(0.0, z1.getRe());
        assertEquals(1.0, z2.getRe());
        assertEquals(-8.0, z3.getRe());
        assertEquals(90.0, z4.getRe());
    //Second Case
        assertEquals(0.0, z1.getIm());
        assertEquals(-4.0, z2.getIm());
        assertEquals(-17.0, z3.getIm());
        assertEquals(-38.0, z4.getIm());
    }
    
    @Test
    public void setterTest(){
    //First Case
        z1.setRe(-2);
        assertEquals(-2.0, z1.getRe());
        z2.setRe(7);
        assertEquals(7.0, z2.getRe());
        z3.setRe(54);
        assertEquals(54.0, z3.getRe());
        z4.setRe(-76);
        assertEquals(-76.0, z4.getRe());
    //Second Case
        z1.setIm(-35);
        assertEquals(-35.0, z1.getIm());
        z2.setIm(5);
        assertEquals(5.0, z2.getIm());
        z3.setIm(43);
        assertEquals(43.0, z3.getIm());
        z4.setIm(-1);
        assertEquals(-1.0, z4.getIm());
    }
    
    @Test
    public void ParseComplexNumberTest(){
    //First Case
        assertEquals(z1, ComplexNumber.parseComplex("0+0i"));
    //Second Case
        assertEquals(z2, ComplexNumber.parseComplex("1-4i"));
    //Third Case
        assertEquals(z3, ComplexNumber.parseComplex("-8-17i"));
    //Fourth Case
        assertEquals(z4, ComplexNumber.parseComplex("90-38i"));
    }
    
    @Test
    public void conjugateTest(){
    //First Case
        z1.setIm(-1);
        assertEquals(new ComplexNumber(0, 1), z1.conjugate());
    //Second Case
        assertEquals(new ComplexNumber(1, 4), z2.conjugate());
    //Third Case
        assertEquals(new ComplexNumber(-8, 17), z3.conjugate());
    //Fourth Case
        assertEquals(new ComplexNumber(90, 38), z4.conjugate());
    }
    
    @Test
    public void modTest(){
    //First Case
        assertEquals(0.0, z1.mod());
    //Second Case
        assertEquals(4.123105625617661, z2.mod());
    //Third Case
        assertEquals(18.788294228055936, z3.mod());
    //Fourth Case
        assertEquals(97.6933979345585, z4.mod());
    }
}
