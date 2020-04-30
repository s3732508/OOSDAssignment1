module com.sharknados {
    requires javafx.controls;
    requires java.rmi;
    requires java.desktop;
    exports com.sharknados.models;
    exports com.sharknados;
    exports com.sharknados.models.pieces.eagles;
    exports com.sharknados.models.pieces.sharks;
    exports com.sharknados.models.pieces;
    exports com.sharknados.views;
}