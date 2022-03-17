//Team: Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import control.*;

public class MoveTest{
    @Test
    void checkForPossibleMovesTest(){
        Board.createBoard();
        Player p1 = new Player(1, Game.colour.WHITE);

        for(int i = 0; i < Game.BOARD_SIZE; i++){
            for(int j = 0; j < Game.BOARD_SIZE; j++){
                //test case 1, no blank squares
                //this is also used in test case 4
                Board.setBoard(i, j, Game.colour.WHITE);

                //test case 2 blank squares but no squares covered by the players colour
                /*if (i % 2 == 0 && j % 2 == 1) {
                    Board.setBoard(i, j, Game.colour.BLACK);
                }
                if(i % 2 == 1 && j % 2 == 0){
                    Board.setBoard(i, j, Game.colour.BLACK);
                }*/
            }
        }

        //test case 4 - one possible placement for the I1 piece
        Board.setBoard(0, 0, Game.colour.BLANK);
        Board.setBoard(0, 1, Game.colour.BLANK);
        Board.setBoard(1, 0, Game.colour.BLANK);

        //test case 3 one piece of the board
        //p1.getGamePiece(Game.type.W).place(9,4);

        Board.firstMove = false;

        Board.display();
        boolean result = Move.checkForPossibleMoves(p1);
        assertTrue(result); //test 3 and 4
        //assertFalse(result);//test 1 and 2

    }
  
   @Test
    void testUndoManipulations() {
        //We rotate and flip a piece then undoManipulations and assert that the shape is gone back to its default shape
        Random rand = new Random();
        Piece manipulatedPiece = new Piece(Game.colour.WHITE, Game.type.F); //Piece to be manipulated
        Piece controlPiece = new Piece(Game.colour.WHITE, Game.type.F); //Control that doesn't get manipulated

       for (int i = 0; i < rand.nextInt(6); i++) { //Add up to 5 random manipulations
           //Because there are only 2 possible manipulations we just use a random boolean to decide between the 2
           if(rand.nextBoolean()){
               manipulatedPiece.rotateClockwise();
               Move.manipulations.add('r');
           } else {
               manipulatedPiece.flip();
               Move.manipulations.add('f');
           }
       }

        Move.undoManipulations(manipulatedPiece);

        //Check to ensure the two pieces have the same coordinates (ie the rotations have been undone)
       for (int i = 0; i < controlPiece.getSize(); i++) {
           assertEquals(controlPiece.getBlock(i,0), manipulatedPiece.getBlock(i,0));
           assertEquals(controlPiece.getBlock(i,1), manipulatedPiece.getBlock(i,1));
       }
    }
}

