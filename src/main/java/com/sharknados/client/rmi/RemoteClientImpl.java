package com.sharknados.client.rmi;

import com.sharknados.common.rmi.RemoteClient;

import java.rmi.RemoteException;

public class RemoteClientImpl implements RemoteClient {
    protected RemoteClientImpl() {}

    @Override
    public void print(String message) {
        System.out.println("Server said: " + message);
    }
}
