package com.sharknados.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote {
    void print(String message) throws RemoteException;
}
