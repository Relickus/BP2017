/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import resources.Constants;
import resources.ImageClass;

/**
 *
 * @author Vojta
 */
public class Loader {

    private static final Loader instance = new Loader();

    public static Loader getInstance() {
        return instance;
    }

    public PayloadImage loadImageFile(ImageClass img) {

        File targetFolder = getTargetFolder(img);
        if (targetFolder == null) {
            return null;   
        }
        Random rand = new Random();
        int imgIndex = rand.nextInt(targetFolder.list().length) + 1;    // returns random integer denoting a real image file in this folder

        
        File imageFile = targetFolder.listFiles()[imgIndex];
        
        PayloadImage result = new PayloadImage(constructImagePath(imageFile));
        
        result.setCorrectClass(img);

        return result;
    }
    

    private File getTargetFolder(ImageClass img) {

        String folderName = img.getName();

        File[] folders = new File(Constants.DATASET_PATH).listFiles();

        for (File folder : folders) {
            if (!folder.isDirectory()) {
                continue;
            }

            if (folder.getName().equalsIgnoreCase(folderName)) {
                return new File(Constants.DATASET_PATH + folderName);

            }
        }

        return null;
    }

    /**
     * Loads "num" different images of the same class
     *
     * @param img Class of images to be load
     * @param num Number of images with same class (needed because of indexes
     * which must be mutually exclusive)
     * @return List of size num with loaded images of desired class
     */
    public ArrayList<PayloadImage> loadNImageFiles(ImageClass img, int num) {

        File targetFolder = getTargetFolder(img);
        if (targetFolder == null) {
            return null;    // this shouldn't happen if using defined enum values
        }
        Random rand = new Random();
        Set<Integer> indexSet = new HashSet<>(num);

        //fills Set with random ints 
        while (indexSet.size() != num) {
            int nextImgIndex = rand.nextInt(targetFolder.list().length) + 1;    // returns random integer denoting a real image file in this folder
            indexSet.add(nextImgIndex);
        }

        ArrayList<PayloadImage> imgArr = new ArrayList<>(num);
        
        File [] images = targetFolder.listFiles();
        
        for (Integer idx : indexSet) {
            PayloadImage pi = new PayloadImage(constructImagePath(images[idx]));
            pi.setCorrectClass(img);
            imgArr.add(pi);
        }

        return imgArr;
    }

    private String constructImagePath(File chosenFile) {
        return chosenFile.toURI().toString();
    }

}
