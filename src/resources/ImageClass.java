/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Objects;

/**
 *
 * @author Vojta
 */
public class ImageClass {
    
    // static counter - assigns values to newly created instances
    private static int counter = 0;
    
    private final int value;
    private final String name;

    public ImageClass(String name) {
        this.value = counter++;        
        this.name  = name;
    }
    
    public ImageClass(String name, int val){        
        this.value = val;        
        this.name  = name;
    }
    
    public int getValue(){
        return value;
    }

    public String getName() {
        return name;
    }

    public void resetCounter(){
        counter = 0;
    }
        
    public String printableName(){
        
        String tmp = name;        
        return tmp.substring(0, 1).toUpperCase() + tmp.substring(1).toLowerCase();        
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof ImageClass))return false;
        
        return name.equalsIgnoreCase(((ImageClass)other).getName() );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}
