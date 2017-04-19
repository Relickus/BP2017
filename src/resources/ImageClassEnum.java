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
public enum ImageClassEnum {
      
    DOG(0),
    FLOWER(1),  //plant?
    BUILDING(2),   //building
    BOAT(3),    // ship
    TREE(4),
    CAR(5),
    BIRD(6),
    CAT(7),
    HORSE(8),
    FACE(9);    //person?
    
     private final int value;

    private ImageClassEnum(int value) {
        this.value = value;
    }
    
    public static ImageClassEnum getEnum(int value) {
      for(ImageClassEnum e: ImageClassEnum.values()) {
        if(e.value == value) {
          return e;
        }
      }
      return null;
    }
    
    public int getValue(){
        return value;
    }
    
    public static ImageClassEnum getEnum(String str){
        
        for(ImageClassEnum e: ImageClassEnum.values()) {
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
