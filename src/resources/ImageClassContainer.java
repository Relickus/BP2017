/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class wrapping all available image classes and performing operations on them
 * @author Vojta
 */
public class ImageClassContainer {

    private static final HashMap<ImageClass, Set<ImageClass>> synMap  = new HashMap<>();
    private static final ArrayList<ImageClass> imgClassesArr = new ArrayList<>();

    /**
     * Loads available image classes and their synonyms from a resource file, initializes imageclass - synonyms hashmap
     */
    public static void init() {            
        int counter=0;

        try (BufferedReader br = new BufferedReader(new FileReader(Constants.CONFIG_IMAGE_CLASSES))) {
            String line;
            ImageClass imageclass;
            
            while ((line = br.readLine()) != null) {                
                ++counter;
                
               int delimPos = line.indexOf(":");
               if(delimPos == -1){
                   imageclass = new ImageClass(line);
                   
                   addRow(imageclass);
                   imgClassesArr.add(imageclass);
                   continue;
               }
               
               imageclass = new ImageClass(line.substring(0,delimPos));
               String synonyms = line.substring(delimPos+1);
               String [] syns = synonyms.split(",");
               
               addRow(imageclass, syns);
               imgClassesArr.add(imageclass);
            }
        }
        catch(IOException ex){
            System.err.println("NESLO NACIST CONFIG FILE!!!  " + ex.getMessage());
            System.exit(1);
        }
      
        Constants.NUMBER_OF_CLASSES = counter;
    }
    
    /**
     * Adds new imageclass without synonyms to hashmap
     * @param imgclass Image class to be added
     */
    public static void addRow(ImageClass imgclass) {
        synMap.put(imgclass, null);
    }

    /**
     * Adds new imageclass and its synonyms to hashmap
     * @param imgclass Image class to be added
     * @param syns Set of synonyms of added image class
     */
    public static void addRow(ImageClass imgclass, Set<ImageClass> syns) {
        synMap.put(imgclass, syns);
    }

    /**
     * Adds new imageclass and its synonyms to hashmap
     * @param imgclass Image class to be added
     * @param syns String array of names of synonymous classes to added image class
     */
    public static void addRow(ImageClass imgclass, String[] syns) {
        
        Set<ImageClass> synSet = new HashSet<>();
        
        for(String s : syns){
            synSet.add(new ImageClass(s,imgclass.getValue()));
        }
        
        addRow(imgclass,synSet);        
    }

    /**
     * Returns set of synonyms to image class in paramter
     * @param imgclass Image class to get synonyms of
     * @return A set of synonyms
     */
    public static Set<ImageClass> getSynonyms(ImageClass imgclass) {
        return synMap.get(imgclass);
    }
    
    /**
     * Returns an image class identified by integer value
     * @param val Integer value of an image class to be returned
     * @return Image class instance
     */
    public static ImageClass getClassByVal(int val){
        
        return imgClassesArr.get(val);
    }

    /**
     * Returns an image class identified by name
     * @param name String representing a name of an image class to be returned
     * @return Image class instance
     */
    public static ImageClass getClassByName(String name){
        
        for(ImageClass i : imgClassesArr){
            if(i.getName().equalsIgnoreCase(name))
                return i;
        }
        
        return null;
    }

    /**
     * Returns a list of all available image classes 
     * @return List of all available classes
     */
    public static ArrayList<ImageClass> getClassesArr() {
        return imgClassesArr;
    }

    
    
}
