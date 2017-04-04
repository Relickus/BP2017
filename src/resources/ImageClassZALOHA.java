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
public enum ImageClassZALOHA {
    
   
    DOG(1),
    HOUSE(2),
    BOAT(3),
    TREE(4),
    CAR(5),
    PLANE(6),
    CAT(7),
    HORSE(8),
    HUMAN(9),
    FLOWER(10);
  
    private final int value;

    private ImageClassZALOHA(int value) {
        this.value = value;
    }
    
    public static ImageClassZALOHA getEnum(int value) {
      for(ImageClassZALOHA e: ImageClassZALOHA.values()) {
        if(e.value == value) {
          return e;
        }
      }
      return null;
    }
    
    public int getValue(){
        return value;
    }
    
    public static ImageClassZALOHA getEnum(String str){
        
        for(ImageClassZALOHA e: ImageClassZALOHA.values()) {
        if(e.printableName().toLowerCase().equals( str.toLowerCase() ) ) {
          return e;
        }
      }
      return null;
    }
    
    public String printableName(){
        
        String tmp = this.name().toLowerCase();
        
        return tmp.substring(0, 1).toUpperCase() + tmp.substring(1);        
    }
    
    
}
