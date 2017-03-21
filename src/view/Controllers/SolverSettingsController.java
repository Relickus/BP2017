/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import resources.Constants;
import utility.AbstractDistance;
import utility.Captchas.CAPTCHA;
import utility.CosineDistance;
import utility.CustomTreeItem;
import utility.EucleidianDistance;
import utility.KNNParameters;
import utility.ManhattanDistance;
import utility.Solvers.KNNSolver;
import utility.Solvers.Solver;

/**
 * FXML Controller class
 *
 * @author Vojta
 */
public class SolverSettingsController extends AbstractController implements Initializable {

    
    private CAPTCHA captcha;
    public WebView captchaView;
    private GridPane dialogGrid;
    private Dialog knnParamsDialog;
    private Dialog waitDialog;
    private boolean resultsReady = false;
    
    public TreeView<CustomTreeItem> treeView;
    private ArrayList<Solver> solversArr;
    private KNNParameters knnParams;
    
     public void initView(){
        
        captcha.getWebView(captchaView);
        captchaView.setZoom(0.7);
    }
     
        public void setCaptcha(CAPTCHA cap){
        this.captcha = cap;
    }      

    public SolverSettingsController() {
        solversArr = new ArrayList<>(6);
    }
        
    private void setSolvers(ArrayList<Solver> solvers){
            
        this.solversArr = solvers;
    }
        
    @FXML    
    private void onBackClicked(ActionEvent event){        
        
        stageController.loadNextStage(PREVIOUS_SCENE); 
        CAPTCHASettingsController windowController = (CAPTCHASettingsController)stageController.getWindowController();
        windowController.setCaptcha(captcha);  
        windowController.initView();        
        stageController.showStage();
    }
    
  
    private void initDialogView(){
        
        dialogGrid = new GridPane();
        dialogGrid.setHgap(10);
        dialogGrid.setVgap(10);
        dialogGrid.setPadding(new Insets(10, 10, 10, 10));
        
       
        Label Kparam = new Label("K parameter:");
        ComboBox kComboBox = new ComboBox();
        kComboBox.getItems().addAll(
                1,3,5,7,9,11,15,21,33
        );
        
        kComboBox.getSelectionModel().selectFirst();
        
        CheckBox weightedVotes = new CheckBox("Weighted voting");
        CheckBox crossFolding = new CheckBox("Crossfolding");
        
        ComboBox distance = new ComboBox();
        distance.getItems().addAll(
                new EucleidianDistance(),new ManhattanDistance(),new CosineDistance()
        );
        distance.getSelectionModel().selectFirst();
        
        dialogGrid.add(Kparam,0,0);
        dialogGrid.add(kComboBox,1,0);
        dialogGrid.add(distance,2,0);
        dialogGrid.add(weightedVotes,3,0);
        dialogGrid.add(crossFolding,4,0);
        
        knnParamsDialog = new Dialog();
        knnParamsDialog.getDialogPane().setContent(dialogGrid);
        ButtonType buttonTypeOk = new ButtonType("Apply", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
                
         knnParamsDialog.setResultConverter(button -> {
            return new KNNParameters(
                    kComboBox.getValue(),
                    (AbstractDistance)distance.getValue(),
                    weightedVotes.isSelected(),
                    crossFolding.isSelected()
            
            );
        });
        
         
        knnParamsDialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk,buttonTypeCancel);

        
        
    }
    
    private void handleMouseClicked(MouseEvent event) {
        
        if( ((TreeItem)treeView.getSelectionModel().getSelectedItem())== null )
            return;
        
        String name = ((CustomTreeItem)((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue()).getName().toLowerCase();

        if( event.getButton().equals(MouseButton.SECONDARY) && name.equals("knn")){
            knnParamsDialog.showAndWait().ifPresent(result->{

                knnParams = (KNNParameters)result;

                setKnnParamStringTreeView();
            }
            );
        }
    }
    
    private void setKnnParamStringTreeView(){
        
        int i = 0;
        while(true){
        TreeItem<CustomTreeItem> item = treeView.getRoot().getChildren().get(i);
        
        if(item.getValue().getName().equals("knn")){
            String tmpStr = knnParams.toString();
            CustomTreeItem tmpItem = (CustomTreeItem)item.getValue();
            tmpItem.setParamString(tmpStr);
            
            item.setValue(null);
            item.setValue(tmpItem);
            return;
        }
                
        ++i;        
        }
    }
    
    private void removeSolver(Solver solver){        
        for(int i = 0 ; i < solversArr.size() ; ++i){            
            if(solversArr.get(i) instanceof KNNSolver){
                solversArr.remove(i);
            }            
        }        
    }
    
    private void addSolver(Solver solver){
        
        solversArr.add(solver);
        
    }
    
    private void initTreeView(){
         CheckBoxTreeItem<CustomTreeItem> knn = new CheckBoxTreeItem<>(new CustomTreeItem(new Label("kNN"),new Label("   ...right click this row to set parameters")));
         CheckBoxTreeItem<CustomTreeItem> onlineRoot = new CheckBoxTreeItem<>(new CustomTreeItem(new Label("Online solvers"),new Label("some params")));
         CheckBoxTreeItem<CustomTreeItem> onlineSolver1 = new CheckBoxTreeItem<>(new CustomTreeItem(new Label("someAPI"),new Label("some params")));

         CheckBoxTreeItem<CustomTreeItem> root = new CheckBoxTreeItem<>(new CustomTreeItem(new Label("Solvers")));
         root.setExpanded(true);
         root.getChildren().addAll(knn, onlineRoot);

         onlineRoot.getChildren().add(onlineSolver1);

         treeView.setRoot(root);

         // set the cell factory
         treeView.setCellFactory(CheckBoxTreeCell.<CustomTreeItem>forTreeView());

         EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
                handleMouseClicked(event);
            };

           treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
           
           
    }
    
    private void fillSolversArr(){
        
    }
    
    private void showPleaseWaitDialog(){
        waitDialog = new Dialog();
        
        Label label = new Label("Solving in progress, please wait...");
        label.setStyle("-fx-font-size: 25px; -fx-family-style: italic;");
        
        GridPane waitGrid = new GridPane();
        waitGrid.setHgap(10);
        waitGrid.setVgap(10);
        waitGrid.setPadding(new Insets(10, 10, 10, 10));
        waitGrid.add(label,1,0);
        
        
        ButtonType closeButton = new ButtonType("Stop", ButtonData.CANCEL_CLOSE);
        
        waitDialog.getDialogPane().getButtonTypes().add(closeButton);
        waitDialog.getDialogPane().setContent(waitGrid);
        waitDialog.showAndWait();
        
        
        //when solving is done - close dialog: http://stackoverflow.com/questions/30755370/javafx-close-alert-box-or-any-dialog-box-programatically
        
    }
    
     @FXML    
    private void onProceedClicked(ActionEvent event){    
        
        
        fillSolversArr();
        // create some ScriptExecutor class and pass it the array 
        
        showPleaseWaitDialog();
        
        if(!resultsReady)   //clicked Cancel button in dialog - dont proceed 
            return;
        
        
        stageController.loadNextStage(NEXT_SCENE);   
        
        ResultWindowController windowController = (ResultWindowController)stageController.getWindowController();
        windowController.setCaptcha(captcha);  
        windowController.initView();   
        
        stageController.showStage();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        initDialogView();
        initTreeView();

        NEXT_SCENE = Constants.RESULT_WINDOW;
        PREVIOUS_SCENE = Constants.CAPTCHA_SETTINGS_WINDOW;
    }
}
