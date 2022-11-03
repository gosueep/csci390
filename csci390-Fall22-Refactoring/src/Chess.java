import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Character;

public class Chess {

    Board board;

    public Chess() {
        board = new Board();
    }


    public void play() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        while(!gameIsOver()) {
            printBoardToConsole();

            // Create move
            Move move = new Move(inputReader.readLine());

            //Validate Piece Movement
            if (board.validMove(move)) {
                if (move.isCapture())
                    board.capturePiece(move);
                else
                    board.movePiece(move);

            }
            // We are not going to worry about special moves like castling and en passant
        }

        System.out.println("Game over.");
        System.out.println("Thanks for playing!");
    }


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
        System.out.println(board);
    }
}
