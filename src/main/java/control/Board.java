//Team Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023
package control;
import ui.UI;
public class Board implements Game {

    private static final int BOARD_SIZE = 14;
    public static boolean firstMove = true;

    // board is a 2d array of enum colour
    public static colour[][] board;

    // changes a single square to a different colour

    // setBoard sets the board in the starting position
    public static void createBoard(){
        board = new colour[BOARD_SIZE][BOARD_SIZE];

        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                board[row][col] = colour.BLANK;
            }
        }
        // represents the first cell each player can put their pieces
        board[4][9] = colour.START;
        board[9][4] = colour.START;
    }
    public static void setBoard(int x, int y, colour team) {
        if(x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)
            throw new IllegalArgumentException("That move is outside the board");
        switch (team){
            case BLACK: board[x][y] = colour.BLACK;break;
            case WHITE: board[x][y] = colour.WHITE;break;
            case BLANK: board[x][y] = colour.BLANK;break;
            default: throw new IllegalArgumentException("The colour is invalid");
        }
    }

    public static colour getBoard(int row, int col){
        if(row<0 || row>BOARD_SIZE-1 || col<0 || col>BOARD_SIZE-1)
            return colour.BLANK;

        else return board[row][col];
    }

    // display prints the board
    public static void display(){
        UI.draw("\n");
        // these loops keep the board aligned when the row number takes two digits
        for(int row = BOARD_SIZE-1; row >= 0; row--){
            if(row < 10)
                UI.draw(" " + row);
            else
                UI.draw(row);
            for(int col = 0; col < BOARD_SIZE; col++){
                UI.draw(" " + changeEnumToChar(board[row][col]));
            }
            UI.draw("\n");
        }
        // indents the column numbers
        UI.draw("   ");

        for(int col = 0; col < BOARD_SIZE; col++ )
            if(col < 10)
                UI.draw(col + " ");
            else
                UI.draw(col);
        UI.draw("\n\n");
    }
    public static char changeEnumToChar(colour c){
        switch (c){
            case BLACK: return 'O';
            case WHITE: return 'X';
            case START: return '*';
            case BLANK: return '.';
            default: throw new IllegalArgumentException("The colour is invalid");
        }
    }
}
