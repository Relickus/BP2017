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
 *
 * @author Vojta
 */
public class ImageClassContainer {

    private static final HashMap<ImageClass, Set<ImageClass>> synMap  = new HashMap<>();
    private static final ArrayList<ImageClass> imgClassesArr = new ArrayList<>();

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
    
     public static void addRow(ImageClass imgclass) {
        synMap.put(imgclass, null);
    }

    public static void addRow(ImageClass imgclass, Set<ImageClass> syns) {
        synMap.put(imgclass, syns);
    }
    public static void addRow(ImageClass imgclass, String[] syns) {
        
        Set<ImageClass> synSet = new HashSet<>();
        
        for(String s : syns){
            synSet.add(new ImageClass(s,imgclass.getValue()));
        }
        
        addRow(imgclass,synSet);        
    }

    public static void addSynonym(ImageClass imgclass, ImageClass synonym) {
        synMap.get(imgclass).add(synonym);
    }
    public static void addSynonyms(ImageClass imgclass, Set<ImageClass> synonymArr) {
        synMap.get(imgclass).addAll(synonymArr);
    }

    public static Set<ImageClass> getSynonyms(ImageClass imgclass) {
        return synMap.get(imgclass);
    }
    
    public static ImageClass getClassByVal(int val){
        
        return imgClassesArr.get(val);
    }
    public static ImageClass getClassByName(String name){
        
        for(ImageClass i : imgClassesArr){
            if(i.getName().equalsIgnoreCase(name))
                return i;
        }
        
        return null;
    }

    public static ArrayList<ImageClass> getClassesArr() {
        return imgClassesArr;
    }

    
    
}
