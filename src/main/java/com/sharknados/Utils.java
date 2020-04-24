package com.sharknados;

import javafx.scene.control.Labeled;
import javafx.scene.text.Font;

public class Utils {
    public static void setFontSize(Labeled node, int size) {
        node.setFont(Font.font(size));
    }
}
