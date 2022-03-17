//Team: Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main implements Game{
    public static void main(String[] args) throws IOException {
        int startingPlayer = 0;
        Scanner in = new Scanner(System.in);

        Random rand = new Random();
        startingPlayer = rand.nextInt(2)+1;

        //analyse the command line arguments
        if(args.length > 0) {
            if(args[0].equals("-x") || args[0].equals("-X"))
                startingPlayer = 1;
            else if(args[0].equals("-o") || args[0].equals("-O"))
                startingPlayer = 2;
        }

        colour c1;
        colour c2;

        if(startingPlayer == 1){
            c1 = colour.WHITE;
            c2 = colour.BLACK;
        } else{
            c1 = colour.BLACK;
            c2 = colour.WHITE;
        }

        //set the players names
        Player p1 = new Player(1, c1);
        p1.setName(in);
        Player p2 = new Player(2, c2);
        p2.setName(in);

        //create and display the board
        Board.createBoard();
        Board.display();

        UI.draw("\n"+p1.toString());
        p1.getPlayersPieces(System.out);// get p1's gamePieces
        UI.draw("\n"+p2.toString());
        p2.getPlayersPieces(System.out);// get p2's gamePieces

        //First move for each player
        Move.move(p1, in);
        Move.move(p2, in);

        Board.firstMove = false;

        //game loop
        //while there are possible moves for either player, continue looping
        while(Move.checkForPossibleMoves(p1) || Move.checkForPossibleMoves(p2)){
            if(Move.checkForPossibleMoves(p1)){
                Move.move(p1, in);
            }
            else{
                UI.draw("\nNo possible moves for Player 1. Next Player's turn!");
            }

            if(Move.checkForPossibleMoves(p2)){
                Move.move(p2, in);
            }
            else{
                UI.draw("\nNo possible moves for Player 2. Next Player's turn!");
            }

        }

        UI.draw("\nNo more possible moves! The game is over!\n");

        //get the final score of each player
        p1.finalizeScore();
        p2.finalizeScore();

        //output the final score for each player
        UI.draw(p1.getName() + " score: " + p1.getScore() + "\n");
        UI.draw(p2.getName() + " score: " + p2.getScore() + "\n");

        //decide the winner
        if(p1.getScore() > p2.getScore()){
            UI.draw("\n" + p1.getName() + " wins!");
        }
        else if(p1.getScore() < p2.getScore()){
            UI.draw("\n" + p2.getName() + " wins!");
        }
        else{
            UI.draw("\nDraw!");
        }
    }
}