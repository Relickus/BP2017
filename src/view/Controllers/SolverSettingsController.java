/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import resources.Constants;
import utility.AbstractDistance;
import utility.Captchas.CAPTCHA;
import utility.CosineDistance;
import utility.CustomTreeItem;
import utility.EucleidianDistance;
import utility.KNNParameters;
import utility.ManhattanDistance;
import utility.PleaseWaitDialog;
import utility.ScriptExecutor;
import utility.Solvers.Solver;

/**
 * FXML Controller class
 *
 * @author Vojta
 */
public class SolverSettingsController extends AbstractController implements Initializable, Runnable {

    private CAPTCHA captcha;
    private CAPTCHAHolder captchaHolder;

    private @FXML
    VBox captchaContainer;
    private @FXML
    TreeView<CustomTreeItem> treeView;

    private GridPane dialogGrid;
    private Dialog knnParamsDialog;
    private ArrayList<Solver> availableSolversArr;
    private ArrayList<Solver> pickedSolversArr;
    private KNNParameters knnParams;
    private ArrayList<CheckBoxTreeItem<CustomTreeItem>> checkboxArr;
    private PleaseWaitDialog pleaseWaitDialog;

    public void initView() {

        captchaContainer.getChildren().add(captchaHolder);
        initDialogView();
        initTreeView();

    }

    public void setCaptcha(CAPTCHA cap) {
        this.captcha = cap;
        captchaHolder = new CAPTCHAHolder(captcha);
    }

    @FXML
    private void onBackClicked(ActionEvent event) {

        stageController.loadNextStage(PREVIOUS_SCENE);
        CAPTCHASettingsController windowController = (CAPTCHASettingsController) stageController.getWindowController();
        windowController.setCaptcha(captcha);
        windowController.initView();
        stageController.showStage();
    }

    // fix this method
    private void initDialogView() {

        dialogGrid = new GridPane();
        dialogGrid.setHgap(10);
        dialogGrid.setVgap(10);
        dialogGrid.setPadding(new Insets(10, 10, 10, 10));

        Label Kparam = new Label("K parameter:");
        ComboBox kComboBox = new ComboBox();
        kComboBox.getItems().addAll(
                1, 3, 5, 7, 9, 11, 15, 21, 33
        );

        kComboBox.getSelectionModel().selectFirst();

        CheckBox weightedVotes = new CheckBox("Weighted voting");
        CheckBox crossFolding = new CheckBox("Crossfolding");

        ComboBox distance = new ComboBox();
        distance.getItems().addAll(
                new EucleidianDistance(), new ManhattanDistance(), new CosineDistance()
        );
        distance.getSelectionModel().selectFirst();

        dialogGrid.add(Kparam, 0, 0);
        dialogGrid.add(kComboBox, 1, 0);
        dialogGrid.add(distance, 2, 0);
        dialogGrid.add(weightedVotes, 3, 0);
        dialogGrid.add(crossFolding, 4, 0);

        knnParamsDialog = new Dialog();
        knnParamsDialog.getDialogPane().setContent(dialogGrid);
        ButtonType buttonTypeOk = new ButtonType("Apply", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        knnParamsDialog.setResultConverter(button -> {
            return new KNNParameters(
                    kComboBox.getValue(),
                    (AbstractDistance) distance.getValue(),
                    weightedVotes.isSelected(),
                    crossFolding.isSelected()
            );
        });

        knnParamsDialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

    }

    private void handleMouseClicked(MouseEvent event) {

        if (((TreeItem) treeView.getSelectionModel().getSelectedItem()) == null) {
            return;
        }

        String name = ((CustomTreeItem) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue()).getName().toLowerCase();

        if (event.getButton().equals(MouseButton.SECONDARY) && name.toLowerCase().equals("knn")) {
            knnParamsDialog.showAndWait().ifPresent(result -> {

                knnParams = (KNNParameters) result;

                setKnnParamStringTreeView();
            }
            );
        }
    }

    private void setKnnParamStringTreeView() {

        int i = 0;
        while (true) {
            TreeItem<CustomTreeItem> item = treeView.getRoot().getChildren().get(i);

            if (item.getValue().getName().toLowerCase().equals("knn")) {
                String tmpStr = knnParams.toString();
                CustomTreeItem tmpItem = (CustomTreeItem) item.getValue();
                tmpItem.setParamString(tmpStr);

                item.setValue(null);
                item.setValue(tmpItem);
                return;
            }

            ++i;
        }
    }

    private void initTreeView() {

        CheckBoxTreeItem<CustomTreeItem> root = new CheckBoxTreeItem<>(new CustomTreeItem(new Label("All solvers")));

        availableSolversArr = captcha.getChallenge().getAvailableSolvers();

        checkboxArr = new ArrayList<>();

        for (Solver s : availableSolversArr) {

            checkboxArr.add(new CheckBoxTreeItem<>(new CustomTreeItem(s)));
        }

        root.setExpanded(true);

        root.getChildren().addAll(checkboxArr);

        treeView.setRoot(root);

        // set the cell factory
        treeView.setCellFactory(CheckBoxTreeCell.<CustomTreeItem>forTreeView());

        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            handleMouseClicked(event);
        };

        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);

    }

    private void fillPickedSolversArr() {

        if (pickedSolversArr == null) {
            pickedSolversArr = new ArrayList<>();
        }

        for (CheckBoxTreeItem<CustomTreeItem> i : checkboxArr) {
            if (i.isSelected()) {
                if (i.getValue().getSolver() != null) {
                    pickedSolversArr.add(i.getValue().getSolver());
                }
            }
        }

    }

    @FXML
    private void onProceedClicked(ActionEvent event) {

        fillPickedSolversArr();

        // no solvers were checked
        if (pickedSolversArr.isEmpty()) {
            return;
        }

        pleaseWaitDialog = new PleaseWaitDialog();
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            public Boolean call() {
                ScriptExecutor se = new ScriptExecutor();
                se.launchScripts(pickedSolversArr, captcha);
                return true;
            }
        };

        task.setOnRunning((e) -> pleaseWaitDialog.show());
        task.setOnSucceeded((e) -> {
            pleaseWaitDialog.hide();
            // process return value again in JavaFX thread
              
            stageController.loadNextStage(NEXT_SCENE);
            ResultWindowController windowController = (ResultWindowController) stageController.getWindowController();
            windowController.setCaptcha(captcha);
            windowController.setResults(pickedSolversArr);
            // tohle muze casem prijit pryc, nahradit metodou setResults() po obdrzeni vysledků
            windowController.initView();

            stageController.showStage();
        });
        
        new Thread(task).start();      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NEXT_SCENE = Constants.RESULT_WINDOW;
        PREVIOUS_SCENE = Constants.CAPTCHA_SETTINGS_WINDOW;
    }

    @Override
    public void run() {

    }

}
