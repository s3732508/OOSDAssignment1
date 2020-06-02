package com.sharknados.models.pieces.shark;

import com.sharknados.models.pieces.Piece;
import com.sharknados.models.pieces.PieceAttribute;
import com.sharknados.models.pieces.PieceMode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttackMode implements PieceMode {
    @Override
    public List<PieceMode> connectsTo() {
        return Arrays.asList(
            new RangeMode()
        );
    }

    @Override
    public Map<PieceAttribute, Integer> getBonuses(Piece piece) {
        return new HashMap<>() {{
            put(piece.getMovementAttribute(), 1);
        }};
    }
}
