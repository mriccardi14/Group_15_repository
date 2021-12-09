package complexnumber_calculatorTest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import complexnumber_calculator.Calculator;
import complexnumber_calculator.ComplexNumber;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static junit.framework.Assert.*;
import org.junit.*;

/**
 * Test Class for Calculator Class
 * 
 * @author Group 15
 */
public class CalculatorTest {
    
    private ComplexNumber z1, z2, z3, z4, z5, z6, z7, z8;
    
    @Before
    public void setUp(){
        z1 = new ComplexNumber();
        z2 = new ComplexNumber(1, -4);
        z3 = new ComplexNumber(-8, -17);
        z4 = new ComplexNumber(90, -38);
        z5 = new ComplexNumber(4, 65);
        z6 = new ComplexNumber(-10, 20);
        z7 = new ComplexNumber(4,2);
        z8 = new ComplexNumber(1,1);
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
    public void argTest(){
    //First Case: z has both part equal to 0
        assertEquals(0.0, Calculator.arg(z1));
    //Second Case: z has real part >0 and imaginary part <0 
        assertEquals(-1.325817663668032465059239210428475631184440601306368843360096381, Calculator.arg(z2));
    //Third Case: z has real part <0 and imaginary part >0
        assertEquals(2.0344439357957027354455779231009658441271217539736731742984053848, Calculator.arg(z6));
    //Fourth Case: z has both part <0
        assertEquals(-2.010638909610633, Calculator.arg(z3));
    //Fifth Case: z has both part >0
        assertEquals(1.5093353709121306633894642605308698765311588250744224056390418032, Calculator.arg(z5));
    
    }
    
    @Test
    public void modTest(){
    //First Case: z has both part equal to 0
        assertEquals(0.0, Calculator.mod(z1));
    //Second Case: z has real part >0 and imaginary part <0 
        assertEquals(4.123105625617661, Calculator.mod(z2));
    //Third Case: z has real part <0 and imaginary part >0
        assertEquals(22.360679774997896964091736687312762354406183596115257242708972454, Calculator.mod(z6));
    //Fourth Case: z has both part <0
        assertEquals(18.788294228055936, Calculator.mod(z3));
    //Fifth Case: z has both part >0
        assertEquals(65.122960620659745923568890220061483378425955313861933083772910771, Calculator.mod(z5));
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
        //First Case
        ComplexNumber z12 = new ComplexNumber(10,10);
        ComplexNumber z22 = new ComplexNumber(20,20);
        ComplexNumber z32 = new ComplexNumber(0, 400);
        assertEquals(z32, Calculator.multiply(z12,z22));
        //Second Case
        ComplexNumber z1 = new ComplexNumber(1,3);
        ComplexNumber z2 = new ComplexNumber(2,1);
        ComplexNumber z3 = new ComplexNumber(-1, 7);
        assertEquals(z3, Calculator.multiply(z1,z2));
        //Third Case
        ComplexNumber z4 = new ComplexNumber(3,0);
        ComplexNumber z5 = new ComplexNumber(0,5);
        ComplexNumber z6= new ComplexNumber(0, 15);
        assertEquals(z6, Calculator.multiply(z4,z5));
        assertEquals(z6, Calculator.multiply(z5,z4));
        //Fourth Case
        ComplexNumber z7 = new ComplexNumber(0,7);
        ComplexNumber z8 = new ComplexNumber(7,0);
        ComplexNumber z9 = new ComplexNumber(0,49);
        assertEquals(z9, Calculator.multiply(z7,z8));
    }
    
    @Test
    public void InverseTest(){
        //First Case
        ComplexNumber z11 = new ComplexNumber(-3,6);
        ComplexNumber z22 = new ComplexNumber(3,-6);
        assertEquals(z22, Calculator.inverse(z11));
        //Second Case
        ComplexNumber z33 = new ComplexNumber(-21,12);
        ComplexNumber z44 = new ComplexNumber(21,-12);
        assertEquals(z44, Calculator.inverse(z33));
        //Third Case
        ComplexNumber z55 = new ComplexNumber(-11, -19);
        ComplexNumber z66 = new ComplexNumber(11,19);
        assertEquals(z66, Calculator.inverse(z55));
        //Fourth Case
        ComplexNumber z77 = new ComplexNumber(0,0);
        assertEquals(z77, Calculator.inverse(z77));
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
    
    @Test
    public void ExponentialTest(){
        //Fisrt Case: z has both part equal to 0
        assertEquals(new ComplexNumber(1,0) , Calculator.exp(z1));
        //Second Case: z has both positive part
        assertEquals(new ComplexNumber(-30.70894,45.14332), Calculator.exp(z5));
        //Third Case: z has both negative part
        assertEquals(new ComplexNumber(-0.00009,0.00032), Calculator.exp(z3));
        //Fourth Case: z has positive real part and negative imaginary part
        assertEquals(new ComplexNumber(-1.77679,2.05720), Calculator.exp(z2));
        //Fifth Case: z has negative real part and positive imaginary part
        assertEquals(new ComplexNumber(0.00002,0.00004), Calculator.exp(z6));
    }
    
    @Test
    public void cosTest(){
    //First Case
        assertEquals(new ComplexNumber(1.0,0.0), Calculator.cos(z1));
    //Second Case
        assertEquals(new ComplexNumber(14.75470117048376, 22.963673499193042), Calculator.cos(z2));
    //Third Case
        assertEquals(new ComplexNumber(-2.4591352139173837, 2.7448170067921541), Calculator.cos(z7));
    }
    
    @Test
    public void logTest(){
    //First Case
        assertEquals(new ComplexNumber(-Infinity,0.0), Calculator.log(z1));
    //Second Case
        assertEquals(new ComplexNumber(1.416606672028108, -1.3258176636680326), Calculator.log(z2));
    //Third Case
        assertEquals(new ComplexNumber(1.4978661367769956, 0.4636476090008061), Calculator.log(z7));
    }
    
    @Test
    public void sinTest(){
    //First Case
        assertEquals(new ComplexNumber(0.0,0.0), Calculator.sin(z1));
    //Second Case
        assertEquals(new ComplexNumber(22.97908557788613, -14.744805188558727), Calculator.sin(z2));
    //Third Case
        assertEquals(new ComplexNumber(-2.8472390868488278, -2.370674169352002), Calculator.sin(z7));    
    }

    @Test
    public void powTest(){
    //First case
        assertEquals(new ComplexNumber(-4.0,-4.0), Calculator.pow(z8, 5));
    //Second case
        assertEquals(new ComplexNumber(0.00015,-0.00004), Calculator.pow(z3, -3));
    //Third case
        assertEquals(new ComplexNumber(1,0), Calculator.pow(z4, 0));
    //Fourth case
        assertEquals(new ComplexNumber(-10,20), Calculator.pow(z6, 1));
    }
    
    @Test
    public void arcsinTest(){
    //First case
        assertEquals(new ComplexNumber(0.0, 0.0), Calculator.arcsin(z1));
    //Second case
        assertEquals(new ComplexNumber(-0.43929774384081915, -3.6268326861821203), Calculator.arcsin(z3));
    //Third case
        assertEquals(new ComplexNumber(1.1712620531141804, -5.274962869289355), Calculator.arcsin(z4));
    //Fourth case
        assertEquals(new ComplexNumber(-0.4632475290061642, 3.8006511697417187), Calculator.arcsin(z6));
    }
    
    
    @Test
    public void arccosTest(){
    //First case
        assertEquals(new ComplexNumber(1.5707963267948966192313216916397514420985846996875529104874722961, 0.0), Calculator.arccos(z1));
    //Second case
        assertEquals(new ComplexNumber(2.01005852662301, 3.6270412585003178), Calculator.arccos(z3));
    //Third case
        assertEquals(new ComplexNumber(0.39899593047893156, 5.274141380077213), Calculator.arccos(z4));
    //Fourth case
        assertEquals(new ComplexNumber(1.509342584590729, -4.869482842745701), Calculator.arccos(z5));
    }
    @Test
    public void tanTest(){
    //First case
        assertEquals(new ComplexNumber(0.0, 0.0), Calculator.tan(z1));
    //Second case
        assertEquals(new ComplexNumber(0.0, -1.0), Calculator.tan(z3));
    //Third case
        assertEquals(new ComplexNumber(0.0, -1.0), Calculator.tan(z4));
    //Fourth case
        assertEquals(new ComplexNumber(0.0, 1.0), Calculator.tan(z5));
    }
}