package com.sharknados.common.rmi;

import com.sharknados.common.Board;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote {
    void print(String message) throws RemoteException;

    void showBoardsStatus(Board[] boards) throws RemoteException;
}
