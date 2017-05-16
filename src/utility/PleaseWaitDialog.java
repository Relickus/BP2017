/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

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
 * This class represents a loading dialog shown during challenge solution 
 *
 * @author Vojta
 */
public class PleaseWaitDialog extends Dialog {

    private final ButtonType buttontypeclose;
    private final Label label;
    private final Label time;
    private final Label timelabel;
    private final GridPane waitGrid;
    private final ProgressBar progressBar;
    private int granularity;

    /**
     * constructs the loading dialog
     */
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

        waitGrid = new GridPane();
        waitGrid.setHgap(10);
        waitGrid.setVgap(10);
        waitGrid.setPadding(new Insets(10, 10, 10, 10));
        waitGrid.add(label, 0, 1, 2, 1);
        waitGrid.add(timelabel, 0, 2, 1, 1);
        waitGrid.add(time, 1, 2, 1, 1);
        waitGrid.add(progressBar, 0, 3, 3, 1);

        buttontypeclose = new ButtonType("Stop", ButtonData.CANCEL_CLOSE);

        this.getDialogPane().getButtonTypes().add(buttontypeclose);

        this.getDialogPane().setContent(waitGrid);

    }

    /**
     * Sets time estimation  for computation
     * @param secs Time in seconds estimated for computation
     */
    public void setEstimatedTime(int secs) {
        time.setText(time.getText() + "~" + secs + "secs.");

        if (secs < 30) {
            return;
        }
        if (secs < 60) {
            time.setTextFill(Color.ORANGE);
        } else if (secs < 100) {
            time.setTextFill(Color.ORANGERED);
        } else {
            time.setTextFill(Color.RED);
        }

    }

    /**
     * displays the dialog
     */
    public void showDialog() {
        this.showAndWait();
    }

    /**
     * Updates graphics of a progressbar in the dialog 
     * @param progress new double value denoting computation progress
     */
    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }

    /**
     * Returns current value in progressbar
     * @return value denoting the calculation progress
     */
    public double getProgress() {
        return progressBar.getProgress();
    }

    /**
     * Sets granularity of steps for progressbar in order to display computattion progress correctly
     * @param numSolvers number of solvers selected for solving a challenge
     * @param numberOfPayloadImgs number of payload images of solved challenge 
     */
    public void setGranularity(int numSolvers, int numberOfPayloadImgs) {
        granularity = numSolvers * numberOfPayloadImgs;
    }

    /**
     * Granularity getter
     * @return integer value
     */
    public int getGranularity() {
        return granularity;
    }

}
