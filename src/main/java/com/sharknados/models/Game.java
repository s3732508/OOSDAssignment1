package com.sharknados.models;

public class Game {
    public enum Turn {
        SHARK,
        EAGLE
    }

    private Board board;
    private Turn turn;
    private GameState saveState;
    private GameState loadState;


    public Game() {
        board = new Board(5);
        turn = Turn.SHARK;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Turn nextTurn() {
        if (turn == Turn.SHARK)
            turn = Turn.EAGLE;
        else
            turn = Turn.SHARK;
        return turn;
    }



}
