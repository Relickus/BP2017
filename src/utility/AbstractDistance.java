/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 * A wrapper class for distance measure used in KNN algorithm script
 *
 */
public abstract class AbstractDistance {
    
    /**
     * Getter for name of the distance in representative form
     * @return Name of the distance in lowercase with first capital letter
     */
    public abstract String getName();

    @Override
    public String toString() {
        return getName() + " distance";
    }

    /**
     * Getter for name of the distance in shortened form for usage as a command
     * line parameter
     *
     * @return Shortened name of the distance in lowercase
     */
    public abstract String getParameterName();

}
