/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.Image;

/**
 *
 * @author Vojta
 */
public abstract class AbstractDistance {
    
    protected Image img1,img2;
    
    public abstract double getDistance(Image img1, Image img2);
    public abstract String getName();

    @Override
    public String toString() {
        return getName() + " distance";
    }
       
    public abstract String getParameterName();
    
}
