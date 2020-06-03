package com.sharknados.models.pieces.modes;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceAttribute;
import com.sharknados.models.pieces.PieceMode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefenceMode implements PieceMode {
    @Override
    public List<PieceMode> connectsTo() {
        return Arrays.asList(
            new MoveMode()
        );
    }

    @Override
    public String getName() {
        return "Defence";
    }

    @Override
    public Map<PieceAttribute, Integer> getBonuses(Piece piece) {
        return new HashMap<>(){{
            put(piece.getDefenceAttribute(), 1);
        }};
    }
}
