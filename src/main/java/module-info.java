module com.sharknados {
    requires javafx.controls;
    requires java.rmi;
    exports com.sharknados.client.rmi;
    exports com.sharknados.client.application;
    exports com.sharknados.client.shapes;
    exports com.sharknados.server.rmi;
    exports com.sharknados.common.rmi;
    exports com.sharknados.common.models;
    exports com.sharknados.common.models.pieces;
    exports com.sharknados.common.models.pieces.eagles;
    exports com.sharknados.common.models.pieces.sharks;
}