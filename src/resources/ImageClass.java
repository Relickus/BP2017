/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author Vojta
 */
public class ImageClass {
    
    private final int value;
    private final String name;

    public ImageClass(int value) {
        this.value = value;        
        this.name  = ImageClassEnum.getEnum(value).toString();
    }
    public ImageClass(ImageClassEnum imgenum) {
        this.value = imgenum.getValue();
        this.name = imgenum.toString();
    }
        
    public int getValue(){
        return value;
    }
    
    public String printableName(){
        
        String tmp = name;
        
        return tmp.substring(0, 1).toUpperCase() + tmp.substring(1);        
    }
    
    
}
