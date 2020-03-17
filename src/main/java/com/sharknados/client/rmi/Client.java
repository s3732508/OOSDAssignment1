package com.sharknados.client.rmi;

import com.sharknados.common.rmi.BeforeServerConnection;
import com.sharknados.common.rmi.RMIServer;
import com.sharknados.common.rmi.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Client extends RMIServer {
    public Client() {
        super(new RemoteClientImpl());
    }

    public RemoteServer getServer() throws RemoteException, NotBoundException {
        BeforeServerConnection server = (BeforeServerConnection) LocateRegistry.getRegistry(3000).lookup("rmi");
        int port = 3001 + server.getNumberOfConnectedClients();
        this.start(port);

        return server.registerClient(port);
    }

    public static void main(String[] args) {
        try {
            new Client().getServer().print("Hey server!");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
