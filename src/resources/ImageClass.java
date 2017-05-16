/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Objects;

/**
 *
 * Respresents a class of an image
 */
public class ImageClass {
    
    // static counter - assigns values to newly created instances
    private static int counter = 0;
    
    private final int value;
    private final String name;

    /**
     * Constructs an instance
     * @param name Name of the class
     */
    public ImageClass(String name) {
        this.value = counter++;        
        this.name  = name;
    }
    
    /**
     * Constructs an instance
     * @param name Name of the class 
     * @param val Integer identification of the class
     */
    public ImageClass(String name, int val){        
        this.value = val;        
        this.name  = name;
    }
    
    /**
     * Value getter 
     * @return identification integer value of an image class
     */
    public int getValue(){
        return value;
    }

    /**
     * Name getter
     * @return Name of an image class
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for name of an image class in lowercase with capital first letter
     * @return Name in said format
     */
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
    
    /**
     * Checks whether an instance of image class is equivalent to one in parameter. 
     * Instance I of an image class is eqivalent to image class J if either I has the same name as J or J has the same as one of I's synonymous image classes  
     * @param oth Instance of image class to compare this instance to
     * @return boolean value denoting whether two compared image classes are equivalent
     */
    public boolean equivalentTo(ImageClass oth){
        
        if(oth == null)
            return false;
        
        if(this.equals(oth))
            return true;
        
        if(ImageClassContainer.getSynonyms(this) != null)
            return ImageClassContainer.getSynonyms(this).contains(oth);
        
        return false;  
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}
