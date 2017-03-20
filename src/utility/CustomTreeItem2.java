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
public class CustomTreeItem2 extends HBox{
    
    private Label name;
    private Button btn;

    public CustomTreeItem2(Label txt) {
        super();

        this.name = txt;

        this.getChildren().add(name);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    public CustomTreeItem2(Label txt, Button b) {
        super(5);

        this.name = txt;
        this.btn = b;

        this.getChildren().addAll(name, btn);
        this.setAlignment(Pos.CENTER_LEFT);
    }

    
    
}
