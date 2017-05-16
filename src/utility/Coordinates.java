/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 * Represents coordinates of element in a grid
 * @author Vojta
 */
public class Coordinates {
    
    private final int row;
    private final int col;

    /**
     * Instantiates the class
     * @param row Row of a grid
     * @param col Column of a grid
     */
    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Copy constructor 
     * @param oth Instance of the Coordinates class to be copied
     */
    public Coordinates(Coordinates oth){
        this.row = oth.row;
        this.col = oth.col;
    }

    /**
     * Row getter
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Column getter
     * @return column
     */
    public int getCol() {
        return col;
    }

    @Override
    public String toString(){
        
        String tmp = "[" + getRow() + "," + getCol() + "]";
        return tmp;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Coordinates))return false;
        
        Coordinates tmp = (Coordinates)other;
        return row == tmp.row && col == tmp.col;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.row;
        hash = 29 * hash + this.col;
        return hash;
    }
    
    
    
    
    
}
