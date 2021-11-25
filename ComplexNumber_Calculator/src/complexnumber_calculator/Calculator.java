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
        return new ComplexNumber(z1.getRe() + z2.getRe(), z1.getIm() + z2.getIm());
    }
    
    public static ComplexNumber sub(ComplexNumber z1, ComplexNumber z2){
        return new ComplexNumber(z1.getRe() - z2.getRe(), z1.getIm() - z2.getIm());
    }
    /**
     * This method do the multiply between two complex numbers
     * @param z the first ComplexNumber
     * @param y the second ComplexNumber
     * @return the correct resultant ComplexNumber (z*y)
     */
    public static ComplexNumber multiply(ComplexNumber z, ComplexNumber y){
        double real = z.getRe() * y.getRe() - z.getIm() * y.getIm();
        double img = z.getRe() *  y.getIm() + z.getIm() * y.getRe();
        return new ComplexNumber(real, img);
    }
    /**
    * Divides one ComplexNumber by another
    * @param z1 the first ComplexNumber
    * @param z2 the second ComplexNumber
    * @return the correct resultant ComplexNumber (z1/z2)  
    */		
    public static ComplexNumber divide(ComplexNumber z1, ComplexNumber z2){  
        ComplexNumber output = multiply(z1,z2.conjugate());
        double div = Math.pow(z2.mod(),2);
        double outputRe = (int)(Math.round((output.getRe()/div) * 100000))/100000.0;
        double outputIm = (int)(Math.round((output.getIm()/div) * 100000))/100000.0;
        return new ComplexNumber(outputRe, outputIm);
	}
    
    /**
     * This method do the inverse of a complex number
     * @param z the ComplexNumber
     * @return a ComplexNumber which is the inverse of z
     */
    public static ComplexNumber inverse(ComplexNumber z){
        if(z.getRe() == 0 && z.getIm() != 0)
            return new ComplexNumber(z.getRe(),-z.getIm());
        if(z.getRe() != 0 && z.getIm() == 0)
            return new ComplexNumber(-z.getRe(),z.getIm());
        if(z.getRe() == 0 && z.getIm() == 0)
            return z;
        else
            return new ComplexNumber(-z.getRe(),-z.getIm());
    }
    
}
