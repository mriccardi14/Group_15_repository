/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package complexnumber_calculator;

/**
 *
 * @author 
 */
public class ComplexNumber {

    private double Re;
    private double Im;

    public ComplexNumber() {
        this.Re = 0.0;
        this.Im = 0.0;
    }

    public ComplexNumber(double Re, double Im) {
        this.Re = Re;
        this.Im = Im;
    }

    public double getRe() {
        return Re;
    }

    public double getIm() {
        return Im;
    }

    public void setRe(double Re) {
        this.Re = Re;
    }

    public void setIm(double Im) {
        this.Im = Im;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.Re) ^ (Double.doubleToLongBits(this.Re) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.Im) ^ (Double.doubleToLongBits(this.Im) >>> 32));
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
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

    /**
     *
     * @return
     */
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
