/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package complexnumber_calculator;

/**
 * Utility Class Calculator for Complex Numbers
 * 
 * @author Group_15
 */
public class Calculator {
    
    public static ComplexNumber add(ComplexNumber z1, ComplexNumber z2){
        	return new ComplexNumber(z1.Re + z2.Re, z1.Im + z2.Im);
    }
    
    public static ComplexNumber sub(ComplexNumber z1, ComplexNumber z2){
        return new ComplexNumber(z1.Re + z2.Re, z1.Im + z2.Im);
    }
    
    public static ComplexNumber mul(ComplexNumber z1, ComplexNumber z2){
        double _real = z1.Re*z2.Re - z1.Im*z2.Im;
		double _imaginary = z1.Re*z2.Im + z1.Im*z2.Re;
		return new ComplexNumber(_real,_imaginary);
    }
    
    public static ComplexNumber div(ComplexNumber z1, ComplexNumber z2){
        	ComplexNumber output = multiply(z1,z2.conjugate());
		double div = Math.pow(z2.mod(),2);
		return new ComplexNumber(output.Re/div,output.Im/div);
    }
    
    public static ComplexNumber invertSign(ComplexNumber z){
        return null;
    } 

    public static ComplexNumber multiply(ComplexNumber z1, ComplexNumber z2)
	{
		double _real = z1.Re*z2.Re - z1.Im*z2.Im;
		double _imaginary = z1.Re*z2.Im + z1.Im*z2.Re;
		return new ComplexNumber(_real,_imaginary);
	}
}
