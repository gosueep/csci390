public class Board {

    private Piece[][] board = new Piece[8][8];
    private boolean playerTurnIsWhite;

    public Board() {
        setupWhite();
        setupBlack();
        playerTurnIsWhite = true;
    }

    public void setupWhite() {
        board[6][0] = Piece.P;
        board[6][1] = Piece.P;
        board[6][2] = Piece.P;
        board[6][3] = Piece.P;
        board[6][4] = Piece.P;
        board[6][5] = Piece.P;
        board[6][6] = Piece.P;
        board[6][7] = Piece.P;
        board[7][0] = Piece.R;
        board[7][1] = Piece.N;
        board[7][2] = Piece.B;
        board[7][3] = Piece.Q;
        board[7][4] = Piece.K;
        board[7][5] = Piece.B;
        board[7][6] = Piece.N;
        board[7][7] = Piece.R;
    }

    private void setupBlack() {
        board[0][0] = Piece.r;
        board[0][1] = Piece.n;
        board[0][2] = Piece.b;
        board[0][3] = Piece.q;
        board[0][4] = Piece.k;
        board[0][5] = Piece.b;
        board[0][6] = Piece.n;
        board[0][7] = Piece.r;
        board[1][0] = Piece.p;
        board[1][1] = Piece.p;
        board[1][2] = Piece.p;
        board[1][3] = Piece.p;
        board[1][4] = Piece.p;
        board[1][5] = Piece.p;
        board[1][6] = Piece.p;
        board[1][7] = Piece.p;
    }


    public void capturePiece(Move move) {
        // There is little difference between movePiece and capturePiece, but it should be kept separate
        // Maybe you later want to keep material / points on each side, or different notation based on captures

        Square fromSquare = move.getFromSquare();
        Square toSquare = move.getToSquare();
        Piece toPiece = board[toSquare.getFileIndex()][toSquare.getRankIndex()];
        Piece fromPiece = board[fromSquare.getFileIndex()][fromSquare.getRankIndex()];
        char toPiece_char = toPiece.toString().charAt(0);
        char fromPiece_char = fromPiece.toString().charAt(0);

        // checks the pieces are opposite colors, and if so, move
        if((Character.isLowerCase(toPiece_char) && Character.isUpperCase(fromPiece_char))
                || (Character.isUpperCase(toPiece_char) && Character.isLowerCase(fromPiece_char)))
        {
            movePiece(move);
        }
    }

    public void movePiece(Move move) {

        Square fromSquare = move.getFromSquare();
        Square toSquare = move.getToSquare();
        int fromFileIndex = fromSquare.getFileIndex();
        int fromRankIndex = fromSquare.getRankIndex();
        int toFileIndex = toSquare.getFileIndex();
        int toRankIndex = toSquare.getRankIndex();
        Piece fromPiece = board[fromRankIndex][fromFileIndex];

        // If we have gotten here, that means the move is valid and update the board position
        if(move.getPawnPromotionPiece() == null)
            board[toRankIndex][toFileIndex] = fromPiece;
        else
            board[toRankIndex][toFileIndex] = move.getPawnPromotionPiece();
        board[fromRankIndex][fromFileIndex] = null;

        playerTurnIsWhite = false;
    }

    public boolean validMove(Move move) {

        Square fromSquare = move.getFromSquare();
        Square toSquare = move.getToSquare();
        if(fromSquare == null || toSquare == null)
            return false;

        int fromFileIndex = fromSquare.getFileIndex();
        int fromRankIndex = fromSquare.getRankIndex();
        int toFileIndex = toSquare.getFileIndex();
        int toRankIndex = toSquare.getRankIndex();
        Piece fromPiece = board[fromRankIndex][fromFileIndex];

        if(fromPiece == null) {
            System.out.println(InvalidMove.EMPTY_SQUARE);
            return false;
        }

        if (correctPlayerNotMovingTheirPiece(fromPiece)) return false;

        switch (fromPiece.toString().toLowerCase()) {
            case "n":
                if (!validKnightMove(fromFileIndex, fromRankIndex, toFileIndex, toRankIndex)) {
                    System.out.println(InvalidMove.KNIGHT);
                    return false;
                }
                break;
            case "r":
                if (!validRookMove(fromFileIndex, fromRankIndex, toFileIndex, toRankIndex)) {
                    System.out.println(InvalidMove.ROOK);
                    return false;
                }
                break;
            case "b":
                if (!validBishopMove(fromFileIndex, fromRankIndex, toFileIndex, toRankIndex)) {
                    System.out.println(InvalidMove.BISHOP);
                    return false;
                }
                break;
            case "q":
                if (!validQueenMove(fromFileIndex, fromRankIndex, toFileIndex, toRankIndex)) {
                    System.out.println(InvalidMove.QUEEN);
                    return false;
                }
                break;
            case "k":
                if (!validKingMove(fromFileIndex, fromRankIndex, toFileIndex, toRankIndex)) {
                    System.out.println(InvalidMove.KING);
                    return false;
                }
                break;
            case "p":
                if (!validPawnMove(fromFileIndex, fromRankIndex, toFileIndex, toRankIndex)) {
                    System.out.println(InvalidMove.PAWN);
                    return false;
                }
                break;
        }

        return true;
    }

    private boolean validKnightMove(int fromFileIndex, int fromRankIndex, int toFileIndex, int toRankIndex) {
        return (Math.abs(fromFileIndex - toFileIndex) == 2 && Math.abs(fromRankIndex - toRankIndex) == 1)
                || (Math.abs(fromFileIndex - toFileIndex) == 1 && Math.abs(fromRankIndex - toRankIndex) == 2);
    }

    private boolean validPawnMove(int fromFileIndex, int fromRankIndex, int toFileIndex, int toRankIndex) {
        if(fromFileIndex != toFileIndex) {
            return false;
        }
        if(playerTurnIsWhite) {
            if(fromRankIndex == 6) {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta > 2 || rankDelta < 1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return false;
                } else if (rankDelta == 1) {
                    if(board[toRankIndex][toFileIndex] != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return false;
                    }
                } else if (rankDelta == 2) {
                    if(board[toRankIndex][toFileIndex] != null || board[toRankIndex -1][toFileIndex] != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return false;
                    }
                }
            } else {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta != 1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return false;
                } else {
                    if(board[toRankIndex][toFileIndex] != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return false;
                    }
                }
            }
        } else {
            if(fromRankIndex == 1) {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta < -2 || rankDelta > -1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return false;
                } else if (rankDelta == -1) {
                    if(board[toRankIndex][toFileIndex] != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return false;
                    }
                } else if (rankDelta == -2) {
                    if(board[toRankIndex][toFileIndex] != null || board[toRankIndex +1][toFileIndex] != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return false;
                    }
                }
            } else {
                int rankDelta = fromRankIndex - toRankIndex;
                if(rankDelta != -1) {
                    System.out.println("Cannot create valid path for Pawn.");
                    return false;
                } else {
                    if(board[toRankIndex][toFileIndex] != null) {
                        System.out.println("Cannot create valid path for Pawn.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validKingMove(int fromFileIndex, int fromRankIndex, int toFileIndex, int toRankIndex) {
        if(fromFileIndex == toFileIndex && toRankIndex == fromRankIndex) {
            System.out.println("Cannot create valid path for King.");
            return false;
        } else if (Math.abs(fromFileIndex - toFileIndex) > 1) {
            System.out.println("Cannot create valid path for King.");
            return false;
        } else if (Math.abs(fromRankIndex - toRankIndex) > 1) {
            System.out.println("Cannot create valid path for King.");
            return false;
        } else if (board[toRankIndex][toFileIndex] != null) {
            System.out.println("Cannot create valid path for King.");
            return false;
        }
        return true;
    }

    private boolean validQueenMove(int fromFileIndex, int fromRankIndex, int toFileIndex, int toRankIndex) {
        if(fromFileIndex == toFileIndex && toRankIndex == fromRankIndex) {
            System.out.println("Cannot create valid path for Queen.");
            return false;
        } else if(fromFileIndex == toFileIndex) {
            if(toRankIndex > fromRankIndex) {
                for(int i = fromRankIndex +1; i<= toRankIndex; i++) {
                    if(board[i][fromFileIndex] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            } else {
                for(int i = fromRankIndex -1; i>= toRankIndex; i--) {
                    if(board[i][fromFileIndex] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            }
        } else if(fromRankIndex == toRankIndex){
            if(toFileIndex > fromFileIndex) {
                for(int i = fromFileIndex +1; i<= toFileIndex; i++) {
                    if(board[fromRankIndex][i] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            } else {
                for(int i = fromFileIndex -1; i>= fromFileIndex; i--) {
                    if(board[fromRankIndex][i] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            }
        } else if(Math.abs(fromFileIndex - toFileIndex) != Math.abs(fromRankIndex - toRankIndex)) {
            System.out.println("Cannot create valid path for Queen.");
            return false;
        } else {
            if (fromFileIndex < toFileIndex && fromRankIndex < toRankIndex) {
                for (int i = 1; i <= toFileIndex - fromFileIndex; i++) {
                    if (board[fromRankIndex + i][fromFileIndex + i] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            } else if (fromFileIndex < toFileIndex && fromRankIndex > toRankIndex) {
                for (int i = 1; i <= toFileIndex - fromFileIndex; i++) {
                    if (board[fromRankIndex - i][fromFileIndex + i] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            } else if (fromFileIndex > toFileIndex && fromRankIndex > toRankIndex) {
                for (int i = 1; i <= fromFileIndex - toFileIndex; i++) {
                    if (board[fromRankIndex - i][fromFileIndex - i] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            } else if (fromFileIndex > toFileIndex && fromRankIndex < toRankIndex) {
                for (int i = 1; i <= fromFileIndex - toFileIndex; i++) {
                    if (board[fromRankIndex + i][fromFileIndex - i] != null) {
                        System.out.println("Cannot create valid path for Queen.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validBishopMove(int fromFileIndex, int fromRankIndex, int toFileIndex, int toRankIndex) {
        if(fromFileIndex == toFileIndex || toRankIndex == fromRankIndex) {
            System.out.println("Cannot create valid path for Bishop.");
            return false;
        } else if(Math.abs(fromFileIndex - toFileIndex) != Math.abs(fromRankIndex - toRankIndex)) {
            System.out.println("Cannot create valid path for Bishop.");
            return false;
        } else {
            if(fromFileIndex < toFileIndex && fromRankIndex < toRankIndex) {
                for(int i = 1; i <= toFileIndex - fromFileIndex; i++) {
                    if(board[fromRankIndex +i][fromFileIndex +i] != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return false;
                    }
                }
            } else if(fromFileIndex < toFileIndex && fromRankIndex > toRankIndex) {
                for(int i = 1; i <= toFileIndex - fromFileIndex; i++) {
                    if(board[fromRankIndex -i][fromFileIndex +i] != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return false;
                    }
                }
            } else if(fromFileIndex > toFileIndex && fromRankIndex > toRankIndex) {
                for(int i = 1; i <= fromFileIndex - toFileIndex; i++) {
                    if(board[fromRankIndex -i][fromFileIndex -i] != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return false;
                    }
                }
            } else if(fromFileIndex > toFileIndex && fromRankIndex < toRankIndex) {
                for(int i = 1; i <= fromFileIndex - toFileIndex; i++) {
                    if(board[fromRankIndex +i][fromFileIndex -i] != null) {
                        System.out.println("Cannot create valid path for Bishop.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validRookMove(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if(fromFileIndex == toFileIndex && toRankIndex == fromRankIndex) {
            System.out.println("Rook must move at least 1 square.");
            return false;
        } else if(fromFileIndex == toFileIndex) {
            if(toRankIndex > fromRankIndex) {
                for(int i = fromRankIndex +1; i<= toRankIndex; i++) {
                    if(board[i][fromFileIndex] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            } else {
                for(int i = fromRankIndex -1; i>= toRankIndex; i--) {
                    if(board[i][fromFileIndex] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            }
        } else if(fromRankIndex == toRankIndex){
            if(toFileIndex > fromFileIndex) {
                for(int i = fromFileIndex +1; i<= toFileIndex; i++) {
                    if(board[fromRankIndex][i] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            } else {
                for(int i = fromFileIndex -1; i>= fromFileIndex; i--) {
                    if(board[fromRankIndex][i] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Cannot create valid path for Rook.");
            return false;
        }
        return true;
    }

    private boolean correctPlayerNotMovingTheirPiece(Piece fromPiece) {
        //Check that the piece is owned by the correct player.
        if(playerTurnIsWhite) {
            if(fromPiece.toString().toLowerCase() == fromPiece.toString()) {
                System.out.println("Select a square with a white piece.");
                return true;
            }
        } else {
            if(fromPiece.toString().toUpperCase() == fromPiece.toString()) {
                System.out.println("Select a square with a black piece.");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        int rankNum = 8;
        for (Piece[] rank : board) {
            sb.append(rankNum + " ");
            for(Piece piece : rank) {
                if(piece != null) {
                    sb.append(piece);
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            rankNum--;
        }
        sb.append("  abcdefgh");

        return sb.toString();
    }
}
