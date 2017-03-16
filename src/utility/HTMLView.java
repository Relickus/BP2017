/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

/**
 *
 * @author Vojta
 */
public class HTMLView extends StackPane{
    
    final WebView htmlview = new WebView();

    public HTMLView(String url) {
        htmlview.getEngine().load(url);
        getChildren().add(htmlview);
    }
    
}
