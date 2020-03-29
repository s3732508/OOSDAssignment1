package com.sharknados.common.rmi;

import com.sharknados.common.Board;
import com.sharknados.common.Tile;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {
    void print(String message) throws RemoteException;

    Board[] generateNewBoards() throws RemoteException;
}
