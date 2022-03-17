package control;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import ui.UI;
public class Move implements Game{
    public static Stack<Character> manipulations =  new Stack<>();

    //takes the piece name and coordinates as input and places the piece on the board
    public static void move(Player p, Scanner in) throws IOException {
        boolean validMove = false;
        String pieceName = "";
        int x, y;
        Piece piece = null;
        String modification = ""; //stores player's modifications


        UI.draw("\n\nIt is now "+p.getName() + "'s turn.\n");
        UI.draw(p.toString());
        p.getPlayersPieces(System.out);

        //Loops in case of bad input
        while(!validMove){
            try{
                UI.draw("\nWhich piece would you like to place?");
                pieceName = in.next();
                boolean validPiece = false;
                int i = 0;

                //the while loop checks to see if the name of the piece entered by the user is in the gamePieces arrayList
                while(i < p.getGamePieces().size()){
                    validPiece = pieceName.equals(p.getGamePieces().get(i).getShape().toString());
                    if(validPiece){
                        break;
                    }
                    i++;
                }
                //if the piece name is valid moves on to get the coordinates
                if(validPiece){
                    for (type t : type.values()) {
                        if (t.toString().equals(pieceName)) {
                            modification = "";
                            p.getGamePiece(t).printPiece();
                            piece = p.getGamePiece(t);
                            while(!modification.equals("p")){
                                UI.draw("Enter 'r' to rotate, 'f' to flip or 'p' to place the piece ");
                                modification = in.next();
                                if(modification.equals("r")){
                                    manipulations.add('r');
                                    p.getGamePiece(t).rotateClockwise();
                                    p.getGamePiece(t).printPiece();
                                }
                                if(modification.equals("f")){
                                    manipulations.add('f');
                                    p.getGamePiece(t).flip();
                                    p.getGamePiece(t).printPiece();
                                }
                            }
                            UI.draw("\nEnter x and y coordinates on the board: ");
                            x = Integer.parseInt(in.next());
                            y = Integer.parseInt(in.next());

                            if(p.getGamePieces().size() == 1 && (t.equals(type.I1) && p.getGamePieces().contains(p.getGamePiece(t)))){
                                p.getGamePiece(t).place(y,x);
                                p.setScore(p.getScore() + p.getGamePiece(t).getSize() + 5);
                                p.getGamePieces().remove(p.getGamePiece(t));
                            }
                            else{
                                p.getGamePiece(t).place(y,x);
                                p.setScore(p.getScore() + p.getGamePiece(t).getSize());
                                p.getGamePieces().remove(p.getGamePiece(t));
                            }
                            break;
                        }
                    }
                    validMove = true;
                }
                //if the piece name is not valid throws an exception and loops again
                if(!validPiece){
                    throw new IllegalArgumentException("Invalid instruction, please try again!");
                }
            }catch(Exception ex){
                undoManipulations(piece);
                UI.draw("\nInvalid instruction, please try again!");
            }
        }

        //the updated board and player status is displayed
        Board.display();
        UI.draw(p.toString());
        p.getPlayersPieces(System.out);
    }

    //Reverses manipulations made by player
    public static void undoManipulations(Piece piece){
        while(!manipulations.isEmpty()){
            switch(manipulations.pop()){
                case 'r':
                    piece.rotateAntiClockwise();
                    break;
                case 'f':
                    piece.flip();
                    break;
                default:
            }
        }
    }

    //this method checks if a player can place a piece on the board
    public static boolean checkForPossibleMoves(Player p){
        boolean possibleMoves = false;
        Piece stackPiece = null; //Pointer to current piece to undo manipulations

        //iterate through the array of gamePieces the player has
        //while moving along the board check if the piece can pe placed on that square
        //once a piece that can be placed on the board if found, end the loop because there is at least one possible move on the board
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
               for(Piece piece: p.getGamePieces()){
                   stackPiece = piece;
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.rotateClockwise();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.rotateClockwise();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.rotateClockwise();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.flip();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.rotateClockwise();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.rotateClockwise();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   piece.rotateClockwise();
                   if(piece.canPlace(i, j)){
                       possibleMoves = true;
                       break;
                   }
                   undoManipulations(piece); //We return the piece to its original orientation
               }
               if(possibleMoves) break;
            }
            if(possibleMoves) break;
        }
        undoManipulations(stackPiece);
        return possibleMoves;
    }
}


