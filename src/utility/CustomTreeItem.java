/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utility.Solvers.CNNSolver;
import utility.Solvers.KNNSolver;
import utility.Solvers.OnlineSolver;
import utility.Solvers.Solver;

/**
 *
 * @author Vojta
 */
public class CustomTreeItem extends HBox{
    
    private Label name;
    private Label params;
    private Solver solver;

    public CustomTreeItem(Label txt) {
        super();

        this.solver = null;
        this.name = txt;
        this.params = new Label("");

        this.getChildren().add(name);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    public CustomTreeItem(Label txt, Label p) {
        super(5);
    
        this.solver = null;
        this.name = txt;
        this.params = p;

        this.getChildren().addAll(name, params);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    public CustomTreeItem(Solver solver){
        
        super(5);

        this.solver = solver;
        this.name = new Label(solver.getName());
        if(solver.hasParams())
            this.params = new Label(solver.getParameters().toString());
        else
            this.params = new Label("");

        this.getChildren().addAll(name, params);
        this.setAlignment(Pos.CENTER_LEFT);
        
    }
    
    @Override
    public String toString() {
        
        if(params.getText().isEmpty())
            return name.getText();
        
        return name.getText() + ": " + params.getText();
                
    }
    
    public String getName(){
        
         return name.getText();
    }
    
    public Solver getSolver(){
        return solver;
    }
    
    public void setParamString(String str){
        params.setText(str);
    }
    
    
}
