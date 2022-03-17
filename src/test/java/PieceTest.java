//Team: Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import control.*;

class PieceTest implements Game{
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //Constructor tests
    @Test
    void testConstructor(){
        Piece p = new Piece(colour.BLACK, type.L4);

        assertEquals(colour.BLACK, p.getTeam());
        assertEquals(type.L4, p.getShape());
        assertEquals(4, p.getSize());
        assertEquals(2, p.getBlock(2,0));
    }

    @Test
    void testConstructorBadColour(){
        try{
            Piece p = new Piece(colour.BLANK, type.I1);
            fail("Failed to catch bad colour in constructor");
        } catch(IllegalArgumentException ex){
            System.out.println("Successfully caught bad colour in constructor");
        }
    }

    @Test
    void testI1(){
        Piece p = new Piece(colour.BLACK, type.I1);

        assertEquals(1, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
    }

    @Test
    void testI2(){
        Piece p = new Piece(colour.BLACK, type.I2);

        assertEquals(2, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
    }

    @Test
    void testI3(){
        Piece p = new Piece(colour.BLACK, type.I3);

        assertEquals(3, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(2,p.getBlock(2,0));
        assertEquals(0,p.getBlock(2,1));
    }

    @Test
    void testI4(){
        Piece p = new Piece(colour.BLACK, type.I4);

        assertEquals(4, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(2,p.getBlock(2,0));
        assertEquals(0,p.getBlock(2,1));
        assertEquals(3,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
    }

    @Test
    void testI5(){
        Piece p = new Piece(colour.BLACK, type.I5);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(2,p.getBlock(2,0));
        assertEquals(0,p.getBlock(2,1));
        assertEquals(3,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
        assertEquals(4,p.getBlock(4,0));
        assertEquals(0,p.getBlock(4,1));
    }

    @Test
    void testV3(){
        Piece p = new Piece(colour.BLACK, type.V3);

        assertEquals(3, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(0,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
    }

    @Test
    void testL4(){
        Piece p = new Piece(colour.BLACK, type.L4);

        assertEquals(4, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(2,p.getBlock(2,0));
        assertEquals(0,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(1,p.getBlock(3,1));
    }

    @Test
    void testZ4(){
        Piece p = new Piece(colour.BLACK, type.Z4);

        assertEquals(4, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(-1,p.getBlock(3,1));
    }

    @Test
    void testO4(){
        Piece p = new Piece(colour.BLACK, type.O4);

        assertEquals(4, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(1,p.getBlock(3,1));
    }

    @Test
    void testL5(){
        Piece p = new Piece(colour.BLACK, type.L5);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(0,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(2,p.getBlock(3,1));
        assertEquals(0,p.getBlock(4,0));
        assertEquals(3,p.getBlock(4,1));
    }

    @Test
    void testT5(){
        Piece p = new Piece(colour.BLACK, type.T5);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(2,p.getBlock(2,0));
        assertEquals(0,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(1,p.getBlock(3,1));
        assertEquals(0,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }

    @Test
    void testV5(){
        Piece p = new Piece(colour.BLACK, type.V5);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(2,p.getBlock(2,0));
        assertEquals(0,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(1,p.getBlock(3,1));
        assertEquals(0,p.getBlock(4,0));
        assertEquals(2,p.getBlock(4,1));
    }

    @Test
    void testN(){
        Piece p = new Piece(colour.BLACK, type.N);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(0,p.getBlock(1,0));
        assertEquals(1,p.getBlock(1,1));
        assertEquals(0,p.getBlock(2,0));
        assertEquals(2,p.getBlock(2,1));
        assertEquals(-1,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
        assertEquals(-1,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }

    @Test
    void testZ5(){
        Piece p = new Piece(colour.BLACK, type.Z5);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(0,p.getBlock(1,0));
        assertEquals(1,p.getBlock(1,1));
        assertEquals(1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(-1,p.getBlock(3,1));
        assertEquals(-1,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }

    @Test
    void testT4(){
        Piece p = new Piece(colour.BLACK, type.T4);

        assertEquals(4, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(0,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(-1,p.getBlock(3,1));
    }

    @Test
    void testP(){
        Piece p = new Piece(colour.BLACK, type.P);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(0,p.getBlock(1,0));
        assertEquals(1,p.getBlock(1,1));
        assertEquals(-1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(-1,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
        assertEquals(-2,p.getBlock(4,0));
        assertEquals(0,p.getBlock(4,1));
    }

    @Test
    void testW(){
        Piece p = new Piece(colour.BLACK, type.W);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(-1,p.getBlock(3,1));
        assertEquals(-1,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }

    @Test
    void testU(){
        Piece p = new Piece(colour.BLACK, type.U);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(-1,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
        assertEquals(-1,p.getBlock(4,0));
        assertEquals(1,p.getBlock(4,1));
    }

    @Test
    void testF(){
        Piece p = new Piece(colour.BLACK, type.F);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(1,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(-1,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
        assertEquals(0,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }

    @Test
    void testX(){
        Piece p = new Piece(colour.BLACK, type.X);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(0,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(-1,p.getBlock(3,0));
        assertEquals(0,p.getBlock(3,1));
        assertEquals(0,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }

    @Test
    void testY(){
        Piece p = new Piece(colour.BLACK, type.Y);

        assertEquals(5, p.getSize());
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0,p.getBlock(0,0));
        assertEquals(1,p.getBlock(1,0));
        assertEquals(0,p.getBlock(1,1));
        assertEquals(0,p.getBlock(2,0));
        assertEquals(1,p.getBlock(2,1));
        assertEquals(0,p.getBlock(3,0));
        assertEquals(2,p.getBlock(3,1));
        assertEquals(0,p.getBlock(4,0));
        assertEquals(-1,p.getBlock(4,1));
    }



    //Getter tests
    @Test
    void testGetShape() {
        Piece p = new Piece(colour.WHITE, type.I1);

        assertEquals(type.I1, p.getShape());
    }

    @Test
    void testGetTeam() {
        Piece p = new Piece(colour.WHITE, type.I1);

        assertEquals(colour.WHITE, p.getTeam());
    }

    @Test
    void testGetSize() {
        Piece p = new Piece(colour.WHITE, type.I1);

        assertEquals(1, p.getSize());
    }

    @Test
    void testGetBlock() {
        Piece p = new Piece(colour.WHITE, type.V3);

        assertEquals(0, p.getBlock(0,0));
        assertEquals(0, p.getBlock(0,1));
        assertEquals(1, p.getBlock(1,0));
        assertEquals(0, p.getBlock(1,1));
        assertEquals(0, p.getBlock(2,0));
        assertEquals(1, p.getBlock(2,1));
    }

    //Boolean tests
    @Test
    void testCheckValid() {
        Board.createBoard();
        Board.firstMove = true;
        Piece p1 = new Piece(colour.BLACK, type.I3);
        Piece p2 = new Piece(colour.BLACK, type.I1);
        p2.place(4,9);
        Board.firstMove = false;

        assertTrue(p1.checkValid(5, 10));
        assertFalse(p1.checkValid(4, 9)); // PLacing p1 on top of p2
        assertFalse(p1.checkValid(5, 9)); //Placing p1 adjacent to p2
    }


    @Test
    void testCheckDiagonal() {
        Board.createBoard();
        Board.firstMove = true;
        Piece p1 = new Piece(colour.BLACK, type.I3);
        Piece p2 = new Piece(colour.BLACK, type.I1);
        p2.place(4,9);
        Board.firstMove = false;

        assertTrue(p1.checkDiagonal(5, 10));
        assertFalse(p1.checkDiagonal(5,9));
    }

    @Test
    void testCheckStart() {
        Board.createBoard();
        Piece p = new Piece(colour.WHITE, type.L4);

        assertTrue(p.checkStart(9,4));

        Piece p2 = new Piece(colour.BLACK, type.L4);
        assertTrue(p2.checkStart(4,9));
        assertFalse(p.checkStart(7,7));
    }

    //place() tests
    @Test
    void testPlace(){
        Board.createBoard();
        Board.firstMove = true;
        Piece p = new Piece(colour.BLACK, type.I2);
        p.place(4,9);

        assertEquals(colour.BLACK, Board.getBoard(4,9));
        assertEquals(colour.BLACK, Board.getBoard(5,9));
    }

    @Test
    void testPlaceBadStart(){
        try{
            Board.createBoard();
            Board.firstMove = true;
            Piece p = new Piece(colour.WHITE, type.I2);
            p.place(5,6);
            fail("Failed to catch piece going onto a non-starting tile");
        }catch(IllegalArgumentException ex){
            System.out.println("Successfully caught piece going onto non-starting tile");
        }
    }

    @Test
    void testPlacePieceOverlap(){
        try{
            Board.createBoard();
            Board.firstMove = true;
            Piece p1 = new Piece(colour.WHITE, type.I3);
            Piece p2 = new Piece(colour.BLACK, type.I1);
            p2.place(4,9);
            Board.firstMove = false;
            p1.place(4,8);
            fail("Failed to catch overlapping pieces");
        }catch(IllegalArgumentException ex){
            System.out.println("Successfully caught overlapping pieces");
        }
    }

    @Test
    void testPlaceAdjacentPiece(){
        try{
            Board.createBoard();
            Board.firstMove = true;
            Piece p1 = new Piece(colour.WHITE, type.I3);
            Piece p2 = new Piece(colour.WHITE, type.I1);
            p2.place(4,9);
            Board.firstMove = false;
            p1.place(4,10);
            fail("Failed to catch adjacent friendly piece");
        } catch(IllegalArgumentException ex){
            System.out.println("Successfully caught adjacent friendly piece");
        }
    }

    @Test
    void testPlaceNoDiagonal(){
        try{
            Board.createBoard();
            Piece p1 = new Piece(colour.WHITE, type.I3);
            Piece p2 = new Piece(colour.WHITE, type.I1);
            p2.place(4,9);
            Board.firstMove = false;
            p1.place(5,6);
            fail("Failed to catch lack of diagonally connecting piece");
        }catch(IllegalArgumentException ex){
            System.out.println("Successfully caught lack of connecting diagonal piece");
        }
    }

    //Manipulation tests
    @Test
    void testClockwiseRotation(){
        Piece p = new Piece(colour.WHITE, type.W);
        p.rotateClockwise();

        assertEquals(0, p.getBlock(0,0));
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0, p.getBlock(1,0));
        assertEquals(1, p.getBlock(1,1));
        assertEquals(-1, p.getBlock(2,0));
        assertEquals(1, p.getBlock(2,1));
        assertEquals(1, p.getBlock(3,0));
        assertEquals(0, p.getBlock(3,1));
        assertEquals(1, p.getBlock(4,0));
        assertEquals(-1, p.getBlock(4,1));
    }

    @Test
    void testAntiClockwiseRotation(){
        Piece p = new Piece(colour.WHITE, type.W);
        p.rotateAntiClockwise();

        assertEquals(0, p.getBlock(0,0));
        assertEquals(0, p.getBlock(0,1));
        assertEquals(0, p.getBlock(1,0));
        assertEquals(-1, p.getBlock(1,1));
        assertEquals(1, p.getBlock(2,0));
        assertEquals(-1, p.getBlock(2,1));
        assertEquals(-1, p.getBlock(3,0));
        assertEquals(0, p.getBlock(3,1));
        assertEquals(-1, p.getBlock(4,0));
        assertEquals(1, p.getBlock(4,1));
    }

    @Test
    void testFlip(){
        Piece p = new Piece(colour.BLACK, type.F);
        p.flip();

        assertEquals(0, p.getBlock(0,0));
        assertEquals(0, p.getBlock(0,1));
        assertEquals(1, p.getBlock(1,0));
        assertEquals(0, p.getBlock(1,1));
        assertEquals(1, p.getBlock(2,0));
        assertEquals(-1, p.getBlock(2,1));
        assertEquals(-1, p.getBlock(3,0));
        assertEquals(0, p.getBlock(3,1));
        assertEquals(0, p.getBlock(4,0));
        assertEquals(1, p.getBlock(4,1));
    }

    @Test
    void testPrintPieceI1() {
        Piece p = new Piece(colour.WHITE, type.I1);
        p.printPiece();
        assertEquals("X\n", outputStreamCaptor.toString());

    }

    @Test
    void testPrintPieceV3() {
        Piece p = new Piece(colour.BLACK, type.V3);
        p.printPiece();
        assertEquals("O  \n" +
                             "O O\n", outputStreamCaptor.toString());
    }

    @Test
    void testPrintPieceL5() {
        Piece p = new Piece(colour.WHITE, type.L5);
        p.printPiece();
        assertEquals("X      \n" +
                             "X X X X\n", outputStreamCaptor.toString());
    }

    @Test
    void testPrintPieceW() {
        Piece p = new Piece(colour.BLACK, type.W);
        p.rotateClockwise(); //Rotate the piece to demonstrate the printPiece isn't just hard coded
        p.printPiece();
        assertEquals("O O  \n" +
                             "  O O\n" +
                             "    O\n", outputStreamCaptor.toString());

    }
}