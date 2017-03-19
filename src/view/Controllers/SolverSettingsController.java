/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;
import resources.Constants;
import utility.Captchas.CAPTCHA;

/**
 * FXML Controller class
 *
 * @author Vojta
 */
public class SolverSettingsController extends AbstractController implements Initializable {

    
    private CAPTCHA captcha;
    public WebView captchaView;
    
    public TreeView<String> treeView;
    
     public void initView(){
        
        captcha.getWebView(captchaView);
        captchaView.setZoom(0.7);
    }
     
        public void setCaptcha(CAPTCHA cap){
        this.captcha = cap;
    }        
       
    @FXML    
    private void onBackClicked(ActionEvent event){        
        
        stageController.loadNextStage(PREVIOUS_SCENE); 
        CAPTCHASettingsController windowController = (CAPTCHASettingsController)stageController.getWindowController();
        windowController.setCaptcha(captcha);  
        windowController.initView();        
        stageController.showStage();
    }
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Creating the root node
//        final TreeItem<String> root = new TreeItem<>("Root node");
//        root.setExpanded(true);
//
//        // Creating the tree items that will be the first children of the root node
//        // and the parent to the child nodes.
//        final TreeItem<String> parentNode1 = new TreeItem<>("Parent node 1");
//        final TreeItem<String> parentNode2 = new TreeItem<>("Parent node 2");
//        final TreeItem<String> parentNode3 = new TreeItem<>("Parent node 3");
//
//        // Creating the tree items that will be the children of the parent
//        // nodes.
//        final TreeItem<String> childNode1 = new TreeItem<>("Child Node 1");
//        final TreeItem<String> childNode2 = new TreeItem<>("Child Node 2");
//        final TreeItem<String> childNode3 = new TreeItem<>("Child Node 3");
//
//        // Adding tree items to the root
//        root.getChildren().setAll(parentNode1, parentNode2, parentNode3);
//
//        // Add the child nodes to all children of the root
//        for (TreeItem<String> parent : root.getChildren()) {
//            parent.getChildren().addAll(childNode1, childNode2, childNode3);
//        }
//
//        // Creating a tree table view
//
//        // We set show root to false. This will hide the root and only show it's children in the treeview.
//        treeView.setRoot(root);
//        treeView.setShowRoot(false);
//
//        treeView.setCellFactory(e -> new CustomCell());
//        
        
            NEXT_SCENE = Constants.RESULT_WINDOW;
            PREVIOUS_SCENE = Constants.CAPTCHA_SETTINGS_WINDOW;
    }
}
