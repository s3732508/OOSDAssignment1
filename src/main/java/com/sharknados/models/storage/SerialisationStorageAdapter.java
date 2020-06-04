package com.sharknados.models.storage;

import com.sharknados.models.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerialisationStorageAdapter implements StorageAdapter {
    private static final String SAVED_GAME_PATH = "src/main/Saved Game/";
    private static final String SAVED_GAME_FILE_EXTENSION = ".ser";

    private Game loadGameFrom(String filename) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(SAVED_GAME_PATH + filename + SAVED_GAME_FILE_EXTENSION);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game game = (Game) in.readObject();
            in.close();
            fileIn.close();

            return game;
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    private void saveGameTo(Game game, String filename) throws IOException {
        Path saveDirectory = Paths.get(SAVED_GAME_PATH);
        if (!Files.exists(saveDirectory)) {
            Files.createDirectory(saveDirectory);
        }

        Path saveFile = Paths.get(SAVED_GAME_PATH + filename + SAVED_GAME_FILE_EXTENSION);
        if (!Files.exists(saveFile)) {
            Files.createFile(saveFile);
        }
        FileOutputStream gameOut = new FileOutputStream(SAVED_GAME_PATH + filename + SAVED_GAME_FILE_EXTENSION);
        ObjectOutputStream out = new ObjectOutputStream(gameOut);
        out.writeObject(game);
        out.close();
        gameOut.close();
    }

    @Override
    public Game loadSavedGame() throws IOException {
        return loadGameFrom("game");
    }

    @Override
    public Game loadTurn(int turn) throws IOException {
        return loadGameFrom(String.valueOf(turn));
    }

    @Override
    public void save(Game game) throws IOException {
        saveGameTo(game, "game");
        saveGameTo(game, String.valueOf(game.getTurnNumber()));
    }
}
