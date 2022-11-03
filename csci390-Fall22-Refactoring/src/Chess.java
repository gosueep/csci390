import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;

public class Chess {

    Piece[][] board = new Piece[8][8];
    boolean playerTurnIsWhite;

    public Chess() {
        setupBlackPieces();

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

        playerTurnIsWhite = true;
    }

    private void setupBlackPieces() {
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

    public void play() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        while(!gameIsOver()) {
            printBoardToConsole();

            // TODO: Homework - Is this really the chess game's responsibility to do the move conversion?
            //                       Refactor this code to make it the responsibility of a different cla
            //                       DONE
            Move move = new Move(inputReader.readLine());

            //Validate Piece Movement
            if (validMove(move)) {
                if (move.isCapture())
                    capturePiece(move);
                else
                    movePiece(move);
            }
            // We are not going to worry about special moves like castling and en passant
        }

        System.out.println("Game over.");
        System.out.println("Thanks for playing!");
    }

    private void movePiece(Move move) {

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

        //Change the player's turn
        playerTurnIsWhite = !playerTurnIsWhite;
    }

    private boolean validMove(Move move) {

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
            System.out.println("Select a square with a piece.");
            return false;
        }

        if (correctPlayerNotMovingTheirPiece(fromPiece)) return false;

        if(fromPiece.toString().equalsIgnoreCase("n")) {
            if (!((Math.abs(fromFileIndex - toFileIndex) == 2 && Math.abs(fromRankIndex - toRankIndex) == 1) || (Math.abs(fromFileIndex - toFileIndex) == 1 && Math.abs(fromRankIndex - toRankIndex) == 2))) {
                System.out.println("Invalid move for Knight.");
                return false;
            }
        } else if(fromPiece.toString().equalsIgnoreCase("r")) {
            validateRookMove(toFileIndex, toRankIndex, fromFileIndex, fromRankIndex);
            return false;
        } else if(fromPiece.toString().equalsIgnoreCase("b")) {
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
        } else if (fromPiece.toString().equalsIgnoreCase("q")) {
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
        } else if(fromPiece.toString().equalsIgnoreCase("k")) {
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
        } else if (fromPiece.toString().equalsIgnoreCase("p")) {
            if(fromFileIndex != toFileIndex) {
                System.out.println("Cannot create valid path for Pawn.");
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
        }
        return true;
    }

    private void validateRookMove(int toFileIndex, int toRankIndex, int fromFileIndex, int fromRankIndex) {
        if(fromFileIndex == toFileIndex && toRankIndex == fromRankIndex) {
            System.out.println("Rook must move at least 1 square.");
            return;
        } else if(fromFileIndex == toFileIndex) {
            if(toRankIndex > fromRankIndex) {
                for(int i = fromRankIndex +1; i<= toRankIndex; i++) {
                    if(board[i][fromFileIndex] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            } else {
                for(int i = fromRankIndex -1; i>= toRankIndex; i--) {
                    if(board[i][fromFileIndex] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            }
        } else if(fromRankIndex == toRankIndex){
            if(toFileIndex > fromFileIndex) {
                for(int i = fromFileIndex +1; i<= toFileIndex; i++) {
                    if(board[fromRankIndex][i] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            } else {
                for(int i = fromFileIndex -1; i>= fromFileIndex; i--) {
                    if(board[fromRankIndex][i] != null) {
                        System.out.println("Cannot create valid path for Rook.");
                        return;
                    }
                }
            }
        } else {
            System.out.println("Cannot create valid path for Rook.");
            return;
        }
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

    // TODO: Homework - Refactor this method to use a single parameter
    // TODO: There is no difference between a capture and move

    private void capturePiece(Move move) {


        // TODO: Homework - Create capture logic when a piece is capturing another piece
        //           Remember: Pieces can only capture opposing pieces
        //                     Pawns can only capture diagonally in front of them
        //                     We are not worrying about en passant. This is just the simple and basic moves.
        //           Use inspiration from the move method. Think about what can be refactored.
        //                     Extract method is your friend.

        //Move piece, if the move is allowed.
        Square fromSquare = move.getFromSquare();
        Square toSquare = move.getToSquare();

        Piece toPiece = board[toSquare.getFileIndex()][toSquare.getRankIndex()];
        Piece fromPiece = board[fromSquare.getFileIndex()][fromSquare.getRankIndex()];

        // checks the pieces are opposite colors
        if((Character.isLowerCase(toPiece.toString().charAt(0)) && Character.isUpperCase((fromPiece.toString().charAt(0))))
                || (Character.isUpperCase(toPiece.toString().charAt(0)) && Character.isLowerCase((fromPiece.toString().charAt(0))))
        ){
            movePiece(move);
        }
    }

    //Todo: overload with a capture Piece with a promotion


    private boolean gameIsOver() {
        return isPositionCheckmate() || isPositionStalemate();
    }

    private boolean isPositionStalemate() {
        return false;
    }

    private boolean isPositionCheckmate() {
        return false;
    }

    private void printBoardToConsole() {
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
        System.out.println(sb);
    }
}
