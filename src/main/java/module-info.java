module com.sharknados {
    requires javafx.controls;
    requires java.rmi;
    exports com.sharknados.client.rmi;
    exports com.sharknados.client;
    exports com.sharknados.server.rmi;
    exports com.sharknados.common;
    exports com.sharknados.common.rmi;
}