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
    
    /**
     * The complex conjugate of a complex number
     * @param z a ComplexNumber
     * @return a ComplexNumber object which is the conjugate of the current complex number
     */
    public static ComplexNumber conjugate(ComplexNumber z)
    {
            return new ComplexNumber(z.getRe(),-z.getIm());
    }

    /**
     * The absolute value of a complex number
     * @param z a ComplexNumber
     * @return the absolute value of current complex number
     */
    public static double mod(ComplexNumber z)
    {
            return Math.sqrt(Math.pow(z.getRe(),2) + Math.pow(z.getIm(),2));
    }


    /**
     * The argument of a complex number
     * @param z a ComplexNumber
     * @return the argument of current complex number
     */
    public static double arg(ComplexNumber z) {
        return Math.atan2(z.getIm(),z.getRe());
    }
    
   /**
     * Calculates the addition between two complex numbers
     * @param z the first ComplexNumber
     * @param y the second ComplexNumber
     * @return the correct resultant ComplexNumber (z+y)
     */
   public static ComplexNumber addition(ComplexNumber z, ComplexNumber y){
        return new ComplexNumber(z.getRe() + y.getRe(), z.getIm() + y.getIm());
    }
    
    /**
     * Calculates the subtract between two complex numbers
     * @param z the first ComplexNumber
     * @param y the second ComplexNumber
     * @return the correct resultant ComplexNumber (z-y)
     */
    public static ComplexNumber subtract(ComplexNumber z, ComplexNumber y){
        return new ComplexNumber(z.getRe() - y.getRe(), z.getIm() - y.getIm());
    }
    
    /**
     * Calculates the multiply between two complex numbers
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
    * @param z the first ComplexNumber
    * @param y the second ComplexNumber
    * @return the correct resultant ComplexNumber (z/y)  
    */		
    public static ComplexNumber divide(ComplexNumber z, ComplexNumber y){  
        ComplexNumber output = multiply(z, Calculator.conjugate(y));
        double div = Math.pow(Calculator.mod(y),2);
        double outputRe = (int)(Math.round((output.getRe()/div) * 100000))/100000.0;
        double outputIm = (int)(Math.round((output.getIm()/div) * 100000))/100000.0;
        return new ComplexNumber(outputRe, outputIm);
    }
    
    /**
     * Calculates the inverse of a complex number
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
    
    /**
     * Calculates the square root of a complex number
     * @param z the ComplexNumber
     * @return a ComplexNumber which is the square of z
     */
    public static ComplexNumber root(ComplexNumber z){
        if(z.getRe() == 0.0 && z.getIm() == 0.0)
            return new ComplexNumber(0.0, 0.0);
        if(z.getIm() == 0.0 && z.getRe() > 0)
            return new ComplexNumber(Math.sqrt(z.getRe()), 0.0);
        if(z.getIm() == 0.0 && z.getRe() < 0){
            ComplexNumber z1 = Calculator.inverse(z);
            return new ComplexNumber(0.0, Math.sqrt(z1.getRe()));
        }   
        else{
            double r = Math.sqrt(Calculator.mod(z));
            double theta = Calculator.arg(z)/2;
            double outputr = (int)(Math.round((r*Math.cos(theta)) * 100000))/100000.0;
            double outputh = (int)(Math.round((r*Math.sin(theta)) * 100000))/100000.0;
            return new ComplexNumber(outputr, outputh);
        }
    }
    
    /**
     * Calculates the root of (1-z^2)
     * @param z a Complex Number
     * @return the root of (1-z^2)
     */
    public static ComplexNumber root1subz2(ComplexNumber z) {
        
        ComplexNumber squarez = Calculator.multiply(z, z);
        ComplexNumber subz = Calculator.subtract(new ComplexNumber (1,0), squarez);
        return Calculator.root(subz);
    }
    
    /**
    * This method calculates the exponential of a complex number
    * @param z The input ComplexNumber
    * @return a ComplexNumber which is e^(input z)
    */
    public static ComplexNumber exp(ComplexNumber z){
     
        double re, im;
        double radius = Math.exp(z.getRe());

        re = (int) (Math.round((radius * Math.cos(z.getIm())) * 100000)) / 100000.0;
        im = (int) (Math.round((radius * Math.sin(z.getIm())) * 100000)) / 100000.0;

        return new ComplexNumber(re, im);
    }
    
    /**
    * Calculates the logarithm of a ComplexNumber
    * @param z a ComplexNumber
    * @return the logarithm of z.
    */
    public static ComplexNumber log(ComplexNumber z) {
        return new ComplexNumber(Math.log(Calculator.mod(z)),Calculator.arg(z));
    }

    /**
    * Calculates the cosine of a ComplexNumber
    * @param z a ComplexNumber
    * @return the cosine of z.
    */
    public static ComplexNumber cos(ComplexNumber z) {
        if (z.getRe() == 0.0 && z.getIm() == 0.0) {
            return new ComplexNumber(1, +-0);
        }
        double x = Math.exp(z.getIm());
        double inv_x = 1 / x;
        double r = Math.cos(z.getRe()) * (x + inv_x) / 2;
        double i = -Math.sin(z.getRe()) * (x - inv_x) / 2;
        return new ComplexNumber(r, i);
    }
    
    /**
    * Calculates the sine of the ComplexNumber
    * @param z the input complex number
    * @return a ComplexNumber which is the sine of z.
    */
    public static ComplexNumber sin(ComplexNumber z)
    {
        if (z.getRe() == 0.0 && z.getIm() == 0.0) {
        return new ComplexNumber(0, +-0);
    }
            double x = Math.exp(z.getIm());
            double x_inv = 1/x;
            double r = Math.sin(z.getRe()) * (x + x_inv)/2;
            double i = Math.cos(z.getRe()) * (x - x_inv)/2;
            return new ComplexNumber(r,i);
    }
        
    /**
     * Calculates the power of a complex number given the exponent
     * 
     * @param z the input complex number
     * @param power the input exponent
     * @return
     */
    public static ComplexNumber pow(ComplexNumber z, int power){
        
        double mod,arg,
               mod_n,arg_n;
        
        mod = Calculator.mod(z); 
        arg = Calculator.arg(z); 
        
        mod_n = Math.pow(mod, power);
        arg_n = arg * power;
        
        double a,b,
               re,im;
        
        a = mod_n * Math.cos(arg_n); 
        b = mod_n * Math.sin(arg_n);
        
        re = (int) (Math.round(a * 100000)) / 100000.0;
        im = (int) (Math.round(b * 100000)) / 100000.0;
        
        return new ComplexNumber(re,im);
        
    }
    /**
     * Calculates the arcsine of a complex number 
     * @param z a Complex Number
     * @return the arcsine of the complex number
     */
    public static ComplexNumber arcsin(ComplexNumber z){
        
        ComplexNumber z1 = Calculator.root1subz2(z);
        ComplexNumber sumz = Calculator.addition(z1, Calculator.multiply(z, new ComplexNumber(0,1)));
        ComplexNumber logz = Calculator.log(sumz);
        ComplexNumber result = Calculator.multiply(logz, new ComplexNumber(0, -1));
        return result;
    }
    
    /**
     * Calculates the arccosine of a complex number 
     * @param z a Complex Number
     * @return the arccosine of the complex number
     */
    public static ComplexNumber arccos(ComplexNumber z){
        
        ComplexNumber z1 = Calculator.root1subz2(z);
        ComplexNumber multz = Calculator.multiply(z1, new ComplexNumber(0,1));
        ComplexNumber sumz = Calculator.addition(z, multz);
        ComplexNumber logz = Calculator.log(sumz);
        ComplexNumber result = Calculator.multiply(logz, new ComplexNumber(0, -1));
        return result;
    }
    
    /**
     * Calculates the tangent of a complex number 
     * @param z a Complex Number
     * @return the tangent of the complex number
     */
    public static ComplexNumber tan(ComplexNumber z){
        
        ComplexNumber sinz = Calculator.sin(z);
        ComplexNumber cosz = Calculator.cos(z);
        ComplexNumber result = Calculator.divide(sinz, cosz);
        return result;
    }
    
    /**
     * Calculates the arctangent of a complex number 
     * @param z a Complex Number
     * @return the arctangent of the complex number
     */
    public static ComplexNumber arctan(ComplexNumber z){
         
        ComplexNumber sumz = Calculator.addition(new ComplexNumber(0,1), z); 
        ComplexNumber subz = Calculator.subtract(new ComplexNumber(0,1), z);
        ComplexNumber divz = Calculator.divide(sumz, subz);
        ComplexNumber logz = Calculator.log(divz);
        ComplexNumber divz2 = Calculator.divide(new ComplexNumber (0,1), new ComplexNumber (2,0));
        ComplexNumber multz = Calculator.multiply(logz, divz2);
        return multz;
    }
}  
