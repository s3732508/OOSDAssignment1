package com.sharknados.models.storage;

import com.sharknados.models.Game;

import java.io.IOException;

public interface StorageAdapter {
    Game loadSavedGame() throws IOException;
    Game loadTurn(int turn) throws IOException;
    void save(Game game) throws IOException;
}
