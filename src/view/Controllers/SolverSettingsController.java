/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
import utility.CustomTreeItem;
import utility.KNNParameters;
import utility.PleaseWaitDialog;
import utility.ScriptExecutor;
import utility.Solvers.Solver;

/**
 * controller class for solver settings window
 *
 * @author Vojta
 */
public class SolverSettingsController extends AbstractController implements Initializable {

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

    /**
     * initiates the window
     */
    public void initView() {

        captchaContainer.getChildren().add(captchaHolder);
        initDialogView();
        initTreeView();

    }

    /**
     * captcha object setter
     *
     * @param captcha captcha object
     */
    public void setCaptcha(CAPTCHA captcha) {
        this.captcha = captcha;
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

    private void initDialogView() {

        dialogGrid = new GridPane();
        dialogGrid.setHgap(10);
        dialogGrid.setVgap(10);
        dialogGrid.setPadding(new Insets(10, 10, 10, 10));

        Label Kparam = new Label("K parameter:");
        ComboBox kComboBox = new ComboBox();
        kComboBox.getItems().addAll(
                KNNParameters.getAvailableKs()
        );

        kComboBox.getSelectionModel().selectFirst();

        ComboBox distance = new ComboBox();
        distance.getItems().addAll(
                KNNParameters.getAvailableDistances()
        );
        distance.getSelectionModel().selectFirst();

        ComboBox scaleComboBox = new ComboBox();
        scaleComboBox.getItems().addAll(
                KNNParameters.getAvailableScales()
        );

        scaleComboBox.getSelectionModel().select(1);

        dialogGrid.add(Kparam, 0, 0);
        dialogGrid.add(kComboBox, 1, 0);
        dialogGrid.add(distance, 2, 0);
        dialogGrid.add(scaleComboBox, 3, 0);

        knnParamsDialog = new Dialog();
        knnParamsDialog.getDialogPane().setContent(dialogGrid);
        ButtonType buttonTypeOk = new ButtonType("Apply", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        knnParamsDialog.setResultConverter(button -> {
            return new KNNParameters(
                    kComboBox.getValue(),
                    (AbstractDistance) distance.getValue(),
                    scaleComboBox.getValue()
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

                //this is not redundant, it updates the view, do not remove!!!
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
        } else {
            pickedSolversArr.clear();
        }

        for (CheckBoxTreeItem<CustomTreeItem> i : checkboxArr) {
            if (i.isSelected()) {
                if (i.getValue().getSolver() != null) {
                    pickedSolversArr.add(i.getValue().getSolver());
                    if (i.getValue().getSolver().hasParams()) {
                        if (i.getValue().getSolver().getName().equalsIgnoreCase("knn")) {
                            if (knnParams != null) {
                                i.getValue().getSolver().setParameters(knnParams);
                            }
                        }

                    }
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
        ScriptExecutor se = new ScriptExecutor();
        pleaseWaitDialog.setEstimatedTime(se.getEstimatedTime(pickedSolversArr));
        pleaseWaitDialog.setGranularity(pickedSolversArr.size(), captcha.getChallenge().getPayloadSize());

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            public Boolean call() {
                return se.launchScripts(pickedSolversArr, captcha, pleaseWaitDialog);
            }
        };

        task.setOnRunning((e) -> pleaseWaitDialog.show());
        task.setOnSucceeded((e) -> {

            //if it is already hidden by clicking Stop, return to SolverSettings
            if (!pleaseWaitDialog.isShowing()) {
                return;
            }
            pleaseWaitDialog.hide();

            stageController.loadNextStage(NEXT_SCENE);
            ResultWindowController windowController = (ResultWindowController) stageController.getWindowController();
            windowController.setCaptcha(captcha);
            windowController.setResults(pickedSolversArr);
            windowController.initView();

            stageController.showStage();
        });

        Thread t = new Thread(task);
        t.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NEXT_SCENE = Constants.RESULT_WINDOW;
        PREVIOUS_SCENE = Constants.CAPTCHA_SETTINGS_WINDOW;
    }

}
