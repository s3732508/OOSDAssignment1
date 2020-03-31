module com.sharknados {
    requires javafx.controls;
    requires java.rmi;
    exports com.sharknados.models;
    exports com.sharknados.models.pieces.eagles;
    exports com.sharknados.models.pieces.sharks;
    exports com.sharknados.models.pieces;
    exports com.sharknados.views;
    exports com.sharknados.views.shapes;
    exports com.sharknados.views.shapes.eagles;
    exports com.sharknados.views.shapes.sharks;
}