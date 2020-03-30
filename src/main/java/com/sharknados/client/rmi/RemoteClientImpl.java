package com.sharknados.client.rmi;

import com.sharknados.common.models.Board;
import com.sharknados.common.rmi.RemoteClient;

public class RemoteClientImpl implements RemoteClient {
    private Board[] boards;

    protected RemoteClientImpl() {}

    @Override
    public void print(String message) {
        System.out.println("Server said: " + message);
    }

    @Override
    public void showBoardsStatus(Board[] boards) {
        // TODO
    }
}
