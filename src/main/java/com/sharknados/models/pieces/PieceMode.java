package com.sharknados.models.pieces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PieceMode extends Serializable {
    List<PieceMode> connectsTo();
    Map<PieceAttribute, Integer> getBonuses(Piece piece);
}
