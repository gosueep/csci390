import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Move {

    private Square fromSquare, toSquare;
    private Piece pawnPromotionPiece;
    private boolean isCapture;

    public Move(String move) {

        // We'll be doing a simpler notation for our chess game. Notation will be a 5 or 6 character length. Form
        //       will take the shape of {a-h}{1-8}(\-x}{a-h}{1-8}{rnbqRNBQ}.
        //       First character is the from file
        //       Second character is the from rank
        //       Third character is - for move, x for capture
        //       Forth character is the to file
        //       Fifth character is the to rank
        //       Sixth character is the promotion of a pawn to a piece type. This is optional
        Pattern movePattern = Pattern.compile("^[a-h][1-8][-x][a-h][1-8][rnbqRNBQ]{0,1}");
        Matcher moveMatcher = movePattern.matcher(move);
        if(!moveMatcher.find()) {
            //Move is invalid;
            System.out.println("Move is invalid. Please input a valid move.");
            return;
        }

        fromSquare = new Square();
        fromSquare.setFileIndex(calcFileIndex(move.charAt(0)));
        fromSquare.setRankIndex(calcRankIndex(Integer.valueOf(move.substring(1,2))));
        isCapture = (move.charAt(2) == 'x');
        int toFileIndex = calcFileIndex(move.charAt(3));
        int toRankIndex = calcRankIndex(Integer.valueOf(move.substring(4,5)));

        pawnPromotionPiece = null;
        if(move.length() == 6) {
            pawnPromotionPiece = Piece.valueOf(move.substring(5,6));
        }

        toSquare = new Square();
        toSquare.setRankIndex(toRankIndex);
        toSquare.setFileIndex(toFileIndex);
    }

    private static int calcFileIndex(Character file) {
        // Files are associated as follows: a->7, b->6, c->5, d->4, e->3, f->2, g->1, h->0
        switch(file) {
            case 'a' :
                return 0;
            case 'b' :
                return 1;
            case 'c' :
                return 2;
            case 'd' :
                return 3;
            case 'e' :
                return 4;
            case 'f' :
                return 5;
            case 'g' :
                return 6;
            case 'h' :
                return 7;
            default :
                throw new IllegalArgumentException("File Character '" + file + "' is invalid.");
        }
    }

    private static int calcRankIndex(int rankNumber) {
        // Ranks are associated as follows: 1->7, 2->6, 3->5, 4->4, 5->3, 6->2, 7->1, 8->0
        switch(rankNumber) {
            case 1 :
                return 7;
            case 2 :
                return 6;
            case 3 :
                return 5;
            case 4 :
                return 4;
            case 5 :
                return 3;
            case 6 :
                return 2;
            case 7 :
                return 1;
            case 8 :
                return 0;
            default:
                throw new IllegalArgumentException("Rank Value '" + rankNumber + "' is invalid.");

        }
    }


    public Square getFromSquare() {
        return fromSquare;
    }

    public Square getToSquare() {
        return toSquare;
    }

    public Piece getPawnPromotionPiece() {
        return pawnPromotionPiece;
    }

    public boolean isCapture() {
        return isCapture;
    }
}
