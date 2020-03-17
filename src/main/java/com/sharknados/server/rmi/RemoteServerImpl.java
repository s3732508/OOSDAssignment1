package com.sharknados.server.rmi;

import com.sharknados.common.rmi.BeforeServerConnection;
import com.sharknados.common.rmi.RemoteClient;
import com.sharknados.common.rmi.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

public class RemoteServerImpl implements BeforeServerConnection, RemoteServer {
    List<RemoteClient> clients = new ArrayList<>();

    protected RemoteServerImpl() throws RemoteException {}

    @Override
    public void print(String message) {
        System.out.println("Client said: " + message);
    }

    @Override
    public int getNumberOfConnectedClients() {
        return clients.size();
    }

    @Override
    public RemoteServer registerClient(int port) throws RemoteException, NotBoundException {
        RemoteClient newClient = (RemoteClient) LocateRegistry.getRegistry(port).lookup("rmi");
        clients.add(newClient);
        System.out.println("new client connected...");

        for (RemoteClient client : clients) {
            client.print("A new client connected!");
        }

        return this;
    }
}
