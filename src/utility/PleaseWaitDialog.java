/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Vojta
 */
public class PleaseWaitDialog extends Dialog {
 
   private final ButtonType buttontypeclose;
   //private final Button buttonclose;
    private final Label label;
    private final Label time;
    private final Label timelabel;
    private final GridPane waitGrid;
    private final ProgressBar progressBar;
    private int granularity;

    public PleaseWaitDialog() {
        
        label = new Label("Solving in progress, please wait...");
        label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
        GridPane.setConstraints(label, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER);

        
        timelabel = new Label("Estimated time:");        
        timelabel.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");
        GridPane.setConstraints(timelabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);

        time = new Label("");        
        time.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");
        GridPane.setConstraints(time, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER);
        
        progressBar = new ProgressBar();
        GridPane.setConstraints(progressBar, 0, 2, 3, 1, HPos.CENTER, VPos.CENTER);
        progressBar.setMaxWidth(Double.MAX_VALUE);
       
                
        //buttonclose = new Button("Stop");

        waitGrid = new GridPane();
        waitGrid.setHgap(10);
        waitGrid.setVgap(10);
        waitGrid.setPadding(new Insets(10, 10, 10, 10));
        waitGrid.add(label, 0,1,2,1);
        waitGrid.add(timelabel,0,2,1,1);
        waitGrid.add(time,1,2,1,1);
        waitGrid.add(progressBar,0,3,3,1);
        //waitGrid.add(buttonclose, 2, 3);
        
        buttontypeclose = new ButtonType("Stop", ButtonData.CANCEL_CLOSE);

        this.getDialogPane().getButtonTypes().add(buttontypeclose);
        
        this.getDialogPane().setContent(waitGrid);

    }
    
    public void setEstimatedTime(int t){
        time.setText( time.getText() + "~" + t + "secs." );
        
        if(t < 30)
            return;
        if(t < 60){
            time.setTextFill(Color.ORANGE);
        }
        else if(t < 100){
            time.setTextFill(Color.ORANGERED);
        }
        else{
            time.setTextFill(Color.RED);
        }
        
    }

    public void showDialog(){        
        this.showAndWait();
    }
    
    public void setTaskToCancelButton(Task<Boolean> task){
        
        //this.setOnCloseRequest((e)-> task.cancel());
        
//         buttonclose.setOnAction((ActionEvent e) -> {
//             task.cancel();
//         });
    }
    
    public void updateProgress(double progress){
        progressBar.setProgress(progress);
    }
    
    public double getProgress(){
        return progressBar.getProgress();
    }
    
    public void setGranularity(int g, int numberOfPayloadImgs){
        granularity = g * numberOfPayloadImgs;
    }
    
    public int getGranularity(){
        return granularity;
    }
    
}
