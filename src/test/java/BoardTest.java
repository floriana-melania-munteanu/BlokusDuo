//Team Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import control.*;

class BoardTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void createBoard() {
        Board.createBoard();
        //checks the dimensions of the board
        assertEquals(14, Board.board.length);
        assertEquals(14, Board.board[0].length);
        boolean isEmpty = false;
        //checks that every square is not null
        for(int row = 0; row < Board.board.length; row++)
            for(int col = 0; col < Board.board[0].length; col++){
                if(Board.board[row][col] == null){
                    isEmpty = true;
                    break;
                }
            }
        assertFalse(isEmpty);
}

    @Test
    void setBoard() {
        Board.createBoard();
        Board.setBoard(4,4, Game.colour.WHITE);
        assertEquals(Game.colour.WHITE, Board.getBoard(4,4));
        Board.setBoard(8,8, Game.colour.BLACK);
        assertEquals(Game.colour.BLACK, Board.getBoard(8,8));
        Board.setBoard(12,12, Game.colour.BLANK);
        assertEquals(Game.colour.BLANK, Board.getBoard(12,12));

        assertThrows(IllegalArgumentException.class,
                () -> Board.setBoard(-1,-1, Game.colour.WHITE));
        assertThrows(IllegalArgumentException.class,
                () -> Board.setBoard(14,14, Game.colour.WHITE));
    }

    @Test
    void changeEnumToChar() {
        assertEquals('.', Board.changeEnumToChar(Game.colour.BLANK));
        assertEquals('X', Board.changeEnumToChar(Game.colour.WHITE));
        assertEquals('O', Board.changeEnumToChar(Game.colour.BLACK));
        assertEquals('*', Board.changeEnumToChar(Game.colour.START));
    }

    @Test void Display(){
        Board.createBoard();
        Board.display();
        assertEquals("""
                13 . . . . . . . . . . . . . .
                12 . . . . . . . . . . . . . .
                11 . . . . . . . . . . . . . .
                10 . . . . . . . . . . . . . .
                 9 . . . . * . . . . . . . . .
                 8 . . . . . . . . . . . . . .
                 7 . . . . . . . . . . . . . .
                 6 . . . . . . . . . . . . . .
                 5 . . . . . . . . . . . . . .
                 4 . . . . . . . . . * . . . .
                 3 . . . . . . . . . . . . . .
                 2 . . . . . . . . . . . . . .
                 1 . . . . . . . . . . . . . .
                 0 . . . . . . . . . . . . . .
                   0 1 2 3 4 5 6 7 8 9 10111213""", outputStreamCaptor.toString().trim());
    }
}