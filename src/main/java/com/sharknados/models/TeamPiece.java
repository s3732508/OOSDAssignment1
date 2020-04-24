package com.sharknados.models;

public class TeamPiece {
    private final String team;
    private final String piece;
    private final TeamPiece next;

    public TeamPiece(String team, String piece, TeamPiece next) {
        this.team = team;
        this.piece = piece;
        this.next = next;
    }

    public TeamPiece(String team, String piece) {
        this(team, piece, null);
    }

    public String getTeam() {
        return team;
    }

    public String getPiece() {
        return piece;
    }

    public TeamPiece getNext() {
        return next;
    }
}
