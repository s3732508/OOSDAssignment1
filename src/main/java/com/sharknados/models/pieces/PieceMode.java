package com.sharknados.models.pieces;

import java.util.List;
import java.util.Map;

public interface PieceMode {
    List<PieceMode> connectsTo();
    Map<PieceAttribute, Integer> getBonuses(Piece piece);
}
