/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;

/**
 *
 * @author Vojta
 */
public class CustomTreeCell extends TreeCell<String> {
        
    @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            // If the cell is empty we don't show anything.
            if (isEmpty()) {
                setGraphic(null);
                setText(null);
            } else {
                // We only show the custom cell if it is a leaf, meaning it has
                // no children.
                if (this.getTreeItem().isLeaf()) {

                    // A custom HBox that will contain your check box, label and
                    // button.
                    HBox cellBox = new HBox(10);

                    CheckBox checkBox = new CheckBox();
                    Label label = new Label(item);
                    Button button = new Button("Press!");
                    // Here we bind the pref height of the label to the height of the checkbox. This way the label and the checkbox will have the same size. 
                    label.prefHeightProperty().bind(checkBox.heightProperty());

                    cellBox.getChildren().addAll(checkBox, label, button);

                    // We set the cellBox as the graphic of the cell.
                    setGraphic(cellBox);
                    setText(null);
                } else {
                    // If this is the root we just display the text.
                    setGraphic(null);
                    setText(item);
                }
            }
        }
}
