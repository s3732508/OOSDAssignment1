module com.sharknados {
    requires javafx.controls;
    requires java.rmi;
    exports com.sharknados.models;
    exports com.sharknados;
    exports com.sharknados.models.pieces.eagles;
    exports com.sharknados.models.pieces.sharks;
    exports com.sharknados.models.pieces;
    exports com.sharknados.views;
    exports com.sharknados.views.pieces;
    exports com.sharknados.views.pieces.eagles;
    exports com.sharknados.views.pieces.sharks;
}