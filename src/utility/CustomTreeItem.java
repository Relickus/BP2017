/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utility.Solvers.Solver;

/**
 * Custom container class for displaying items in treeview
 * @author Vojta
 */
public class CustomTreeItem extends HBox {

    private Label name;
    private Label params;
    private Solver solver;

    /**
     * constructs the item
     * @param name name of a solver to be put on the item (used for name of a solver)
     */
    public CustomTreeItem(Label name) {
        super();

        this.solver = null;
        this.name = name;
        this.params = new Label("");

        this.getChildren().add(name);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * constructs the item
     * @param name of a solver to be put on the item
     * @param p parameters of a solver to be put in the item
     */
    public CustomTreeItem(Label name, Label p) {
        super(5);

        this.solver = null;
        this.name = name;
        this.params = p;

        this.getChildren().addAll(name, params);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * 
     * constructs the item
     * @param solver instance of solver to put in the item. All info texts are extracted from the instance
     */
    public CustomTreeItem(Solver solver) {

        super(5);

        this.solver = solver;
        this.name = new Label(solver.getName());
        if (solver.hasParams()) {
            this.params = new Label(solver.getParameters().toString());
        } else {
            this.params = new Label("");
        }

        this.getChildren().addAll(name, params);
        this.setAlignment(Pos.CENTER_LEFT);

    }

    @Override
    public String toString() {

        if (params.getText().isEmpty()) {
            return name.getText();
        }

        return name.getText() + ": " + params.getText();

    }

    /**
     * getter for name of a solver in the item
     * @return string with the main text of the item
     */
    public String getName() {

        return name.getText();
    }

    /**
     * getter for solver instance in the item
     * @return solver instance
     */
    public Solver getSolver() {
        return solver;
    }

    /**
     * setter for parameters of a solver in the item
     * @param str text to be put in the item
     */
    public void setParamString(String str) {
        params.setText(str);
    }
    
}
