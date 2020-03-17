package com.sharknados.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public abstract class RMIServer {
    private Remote stub;

    public RMIServer(Remote stub) {
        this.stub = stub;
    }

    public void start(int port) throws RemoteException {
        Remote stub = UnicastRemoteObject.exportObject(this.stub, 0);
        LocateRegistry.createRegistry(port).rebind("rmi", stub);
    }
}
