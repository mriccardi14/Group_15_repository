/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package complexnumber_calculator;

/**
 * Class for complex numbers representation
 * @author Group_15
 */
public class ComplexNumber {

    private double Re;
    private double Im;

    /**
     * Empty costructor for a complex number that builds a complex number with 
     * real and imaginary parts both equal to 0.0
     */
    public ComplexNumber() {
        this.Re = 0.0;
        this.Im = 0.0;
    }

    /**
     * Costructor for a complex number
     * 
     * @param Re real part of a complex number
     * @param Im imaginary part of a complex number
     */
    public ComplexNumber(double Re, double Im) {
        this.Re = Re;
        this.Im = Im;
    }

    /**
     * Return the value of real part of this complex number
     * 
     * @return the value of Re attribute
     */
    public double getRe() {
        return Re;
    }

    /**
     * Return the value of imaginary part of this complex number
     * 
     * @return the value of Im attribute
     */
    public double getIm() {
        return Im;
    }

    /**
     * Set the new value of real part of this complex number
     * 
     * @param Re the new real part
     */
    public void setRe(double Re) {
        this.Re = Re;
    }

    /**
     * Set the new value of imaginary part of this complex number
     * 
     * @param Im the new imaginary part
     */
    public void setIm(double Im) {
        this.Im = Im;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.Re) ^ (Double.doubleToLongBits(this.Re) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.Im) ^ (Double.doubleToLongBits(this.Im) >>> 32));
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComplexNumber other = (ComplexNumber) obj;
        if (Double.doubleToLongBits(this.Re) != Double.doubleToLongBits(other.Re)) {
            return false;
        }
        if (Double.doubleToLongBits(this.Im) != Double.doubleToLongBits(other.Im)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        String real = this.Re + "";
        String imaginary = "";

        if (this.Im < 0) {
            imaginary = this.Im + "i";
        } else {
            imaginary = "+" + this.Im + "i";
        }

        return real + imaginary;
    }

    /**
     * Parses the String as a ComplexNumber of type x+yi.
     *
     * @param s the input complex number as string
     * @return a ComplexNumber which is represented by the string.
     */
    public static ComplexNumber parseComplex(String s) {

        s = s.replaceAll(" ", "");
        ComplexNumber parsed = null;
        
        if (s.contains(String.valueOf("+")) || (s.contains(String.valueOf("-")) && s.lastIndexOf('-') > 0)) {
            String re = "";
            String im = "";
            s = s.replaceAll("i", "");
            s = s.replaceAll("I", "");
            
            if (s.indexOf('+') > 0) {
                re = s.substring(0, s.indexOf('+'));
                im = s.substring(s.indexOf('+') + 1, s.length());
                parsed = new ComplexNumber(Double.parseDouble(re), Double.parseDouble(im));
            } 
            else if (s.lastIndexOf('-') > 0) {
                re = s.substring(0, s.lastIndexOf('-'));
                im = s.substring(s.lastIndexOf('-') + 1, s.length());
                parsed = new ComplexNumber(Double.parseDouble(re), -Double.parseDouble(im));
            }
            
        } 
        else {
            // Pure imaginary number
            if (s.endsWith("i") || s.endsWith("I")) {
                s = s.replaceAll("i", "");
                s = s.replaceAll("I", "");
                parsed = new ComplexNumber(0, Double.parseDouble(s));
            } // Pure real number
            else {
                parsed = new ComplexNumber(Double.parseDouble(s), 0);
            }
        }
        return parsed;
    }

}
