/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Vojta
 */
public class PleaseWaitDialog extends Dialog {
 
   private final ButtonType buttontypeclose;
    private final Label label;
    private final Label time;
    private final Label timelabel;
    private final GridPane waitGrid;

    public PleaseWaitDialog() {

        label = new Label("Solving in progress, please wait...");
        label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold");
        GridPane.setConstraints(label, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER);

        
        timelabel = new Label("Estimated time:");        
        timelabel.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");
        GridPane.setConstraints(timelabel, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);

        time = new Label("");        
        time.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");
        GridPane.setConstraints(time, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);

        waitGrid = new GridPane();
        waitGrid.setHgap(10);
        waitGrid.setVgap(10);
        waitGrid.setPadding(new Insets(10, 10, 10, 10));
        waitGrid.add(label, 0, 1);
        waitGrid.add(timelabel,0,2);
        waitGrid.add(time,1,2);

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
    
}
