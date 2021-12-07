/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package complexnumber_calculator;

import java.util.Objects;

/**
 *
 * @author Group 15
 */
public class Variable {
    
    private Character key;
    private ComplexNumber value;

    public Variable() {
    }

    public Variable(Character key, ComplexNumber value) {
        this.key = key;
        this.value = value;
    }

    public Character getKey() {
        return key;
    }

    public void setKey(Character key) {
        this.key = key;
    }

    public ComplexNumber getValueC() {
        return value;
    }
    
    public String getValueS() {
        return value.getComplexNumber();
    }

    public void setValue(ComplexNumber value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.key);
        hash = 37 * hash + Objects.hashCode(this.value);
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
        final Variable other = (Variable) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}