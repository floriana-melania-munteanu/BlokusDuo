//Team: Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  @Test
    void setNameTest() throws IOException {
        Player p = new Player(1, Game.colour.BLACK);

        //set up a file output stream
        FileOutputStream fos = new FileOutputStream(new File("input.txt"));
        fos.write("Mary".getBytes(StandardCharsets.UTF_8)); //write a valid input to the file

        //set up the input stream as the file
        Scanner in = new Scanner(new File("input.txt"));

        //check valid input
        //set the player name to be the input from the file
        p.setName(in);

        String expectedName = "Mary"; //expected output
        String actualName = p.getName(); //actual output

        assertEquals(expectedName,actualName);

        //check invalid input
        //write an invalid input to the file
        fos.write(" ".getBytes(StandardCharsets.UTF_8));

        //assert if an exception is thrown
        Exception exception = assertThrows(IllegalArgumentException.class, () -> { p.setName(in);});

        String expectedMessage = "Invalid input for the name of the player";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);//check if the message displayed is correct
    }

    @Test
    void setScoreTest() {
        Player p1 = new Player(1, Game.colour.BLACK);

        assertEquals(0, p1.getScore()); //test to see if the score is initially zero

        //two tests to see if the score gets updated correctly
        p1.setScore(2);
        assertEquals(2, p1.getScore());
        p1.setScore(p1.getScore() + 5);
        assertEquals(7, p1.getScore());
    }


    @Test
    void setTeamTest() {
        Player p = new Player(1, Game.colour.BLACK);

        //check valid input
        assertEquals(Game.colour.BLACK, p.getTeam());

        //check invalid input
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {p.setTeam(Game.colour.BLANK);});

        String expectedMessage = "Invalid input for colour BLANK";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void setGamePieces(){
        Player p = new Player(1, Game.colour.BLACK);

        // the player's array of game pieces should contain I1,I2,I3,I4,I5,V3,L4
        p.setGamePieces();

        //test to see if these pieces are in the array
        assertEquals(Game.type.I1.toString(), p.getGamePiece(Game.type.I1).toString());
        assertEquals(Game.type.I2.toString(), p.getGamePiece(Game.type.I2).toString());
        assertEquals(Game.type.I3.toString(), p.getGamePiece(Game.type.I3).toString());
        assertEquals(Game.type.I4.toString(), p.getGamePiece(Game.type.I4).toString());
        assertEquals(Game.type.I5.toString(), p.getGamePiece(Game.type.I5).toString());
        assertEquals(Game.type.V3.toString(), p.getGamePiece(Game.type.V3).toString());
        assertEquals(Game.type.L4.toString(), p.getGamePiece(Game.type.L4).toString());

    }

    @Test
    void getPlayersPiecesTest() throws IOException {
        Player p = new Player(1, Game.colour.BLACK);

        //set up the output stream
        OutputStream outputStream = new FileOutputStream("output.txt");
        p.getPlayersPieces(outputStream);

        String expectedOutput = "I1 I2 I3 V3 I4 L4 Z4 O4 T4 I5 L5 T5 V5 N Z5 P W U F X Y ";

        //get the information from the output stream
        InputStream inputStream = new FileInputStream("output.txt");
        Scanner scanner = new Scanner(inputStream);
        String actualOutput = scanner.nextLine();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void finalizeScoreTest(){
        //checks that the score is correctly reduced by the number of game pieces left
        Player p = new Player(1, Game.colour.BLACK);
        p.setScore(89);
        assertEquals(0, p.finalizeScore());

        //checks that when there are no pieces the  15 point bonus is added
        p.setScore(89);
        p.getGamePieces().clear();
        assertEquals(104, p.finalizeScore());
    }
}