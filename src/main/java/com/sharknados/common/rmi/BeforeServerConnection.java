package com.sharknados.common.rmi;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BeforeServerConnection extends Remote {
    int getNumberOfConnectedClients() throws RemoteException;
    RemoteServer registerClient(int port) throws RemoteException, NotBoundException;
}
