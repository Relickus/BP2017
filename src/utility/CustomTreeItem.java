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

/**
 *
 * @author Vojta
 */
public class CustomTreeItem extends HBox{
    
    private Label name;
    private Label params;

    public CustomTreeItem(Label txt) {
        super();

        this.name = txt;

        this.getChildren().add(name);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    public CustomTreeItem(Label txt, Label p) {
        super(5);

        this.name = txt;
        this.params = p;

        this.getChildren().addAll(name, params);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    public String toString() {
        
        if(params == null)
            return name.getText().toString() + ": default params";
        
        return name.getText().toString() + ": " + params.getText().toString();
                
    }
    
    
    
}
