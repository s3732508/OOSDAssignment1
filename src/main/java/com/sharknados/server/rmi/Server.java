package com.sharknados.server.rmi;

import com.sharknados.common.rmi.RMIServer;

import java.rmi.RemoteException;

public class Server extends RMIServer {
    public Server() throws RemoteException {
        super(new RemoteServerImpl());
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start(3000);
            System.out.println("RMI server running on port 3000...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
