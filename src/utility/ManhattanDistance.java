/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 * Manhattan distance wrapper class
 * @author Vojta
 */
public class ManhattanDistance extends AbstractDistance{

    /**
     * Getter for name of the distance in representative form
     * @return Name of the distance in lowercase with first capital letter
     */
    @Override
    public String getName() {
        return "Manhattan";
    }

    /**
     * Getter for name of the distance in shortened form for usage as a command line parameter
     * @return Shortened name of the distance in lowercase
     */
    @Override
    public String getParameterName() {
        return "manhattan";
    }
    
}
