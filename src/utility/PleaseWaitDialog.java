/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Vojta
 */
public class PleaseWaitDialog extends Dialog {
 
   private final ButtonType buttontypeclose;
    private final Label label;
    private final GridPane waitGrid;

    public PleaseWaitDialog() {

        label = new Label("Solving in progress, please wait...");
        label.setStyle("-fx-font-size: 25px; -fx-family-style: italic;");

        waitGrid = new GridPane();
        waitGrid.setHgap(10);
        waitGrid.setVgap(10);
        waitGrid.setPadding(new Insets(10, 10, 10, 10));
        waitGrid.add(label, 1, 0);

        buttontypeclose = new ButtonType("Stop", ButtonData.CANCEL_CLOSE);

        this.getDialogPane().getButtonTypes().add(buttontypeclose);
        this.getDialogPane().setContent(waitGrid);

    }

    public void showDialog(){        
        this.showAndWait();
    }
    
}
