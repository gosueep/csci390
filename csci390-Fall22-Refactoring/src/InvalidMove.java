public enum InvalidMove {

    EMPTY_SQUARE("Select a square with a piece."),
    KNIGHT("Invalid move for Knight."),
    ROOK("Invalid move for Rook."),
    BISHOP("Invalid move for Bishop."),
    BISHOP_PATH("Cannot create valid path for Bishop."),
    QUEEN("Invalid move for Queen."),
    KING("Invalid move for King."),
    PAWN("Invalid move for Pawn"),
    PAWN_PATH("Cannot create valid path for Pawn.");

    public final String message;

    InvalidMove(String message) {
        this.message = message;
    }
}
