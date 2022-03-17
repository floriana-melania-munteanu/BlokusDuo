//Team: Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Game{

    private String name;
    private int score;
    private colour team;
    private ArrayList<Piece> gamePieces = new ArrayList<Piece>(); //stores the pieces

    //constructor
    public Player(int playerNumber, colour team){
        if(playerNumber != 1 && playerNumber != 2)
            throw new IllegalArgumentException("Only 2 players are allowed!");

        setTeam(team);
        setGamePieces();
        this.score = 0;
        this.name = "Player " + playerNumber;
    }

    //getting input for the player name
    public void setName(Scanner scanner) {
        UI.draw(getName() + " enter your name: ");

        String name = scanner.nextLine();
        if(name.trim().equals("")){
            //checks if the string for the input is an empty string
            //any other input is seen as valid including symbols
            throw new IllegalArgumentException("Invalid input for the name of the player");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int finalizeScore(){
        //if the player is out of pieces, give them bonus points
        if(getGamePieces().isEmpty()){
            setScore(getScore() + 15);
        }
        //reduce points by the size of the pieces they did not play
        for(Piece p: getGamePieces()){
            setScore(getScore() - p.getSize());
        }
     return score;
    }

    //setting the colour for the player
    public void setTeam(Game.colour c) {
        if(c.equals(Game.colour.BLACK) || c.equals(Game.colour.WHITE)){
            this.team = c;
        }
        else{
            throw new IllegalArgumentException("Invalid input for colour " + c);
        }
    }

    public colour getTeam() {
        return team;
    }

    //accessor method for the array of game pieces the player has
    public ArrayList<Piece> getGamePieces() {
        return gamePieces;
    }

    //prints out the content of the gamePieces arrayList on a line with a space after each piece
    public void getPlayersPieces(OutputStream outputStream) throws IOException {
        for(Piece p: getGamePieces()){
            outputStream.write((p.getShape() + " ").getBytes(StandardCharsets.UTF_8));
        }
    }

    //Retrieve a specific piece from array
    public Piece getGamePiece(type shape){
        for(Piece p: getGamePieces()){
            if(p.getShape() == shape)
                return p;
        }
        throw new IllegalArgumentException("Not an available shape!");
    }

    //initialising the array of pieces the player has
    public void setGamePieces() {
        for(type p: type.values()){
            this.gamePieces.add(new Piece(this.team, p));
        }
    }

    //toString method to display the player status (not finished still have to display the pieces the player has in their array)
    public String toString(){
        return "Player " + getName() + " (" + getTeam() + ") " + "Score: " + getScore() + " game pieces: ";
    }
}

