//Team: Random1
//Team members name: Niall Meagher - 20768511
//                   Nathan Mahady - 20522563
//                   Floriana Melania Munteanu - 20349023

public class Piece implements Game{
    private type shape;
    private colour team;
    private int size; //The number of tiles the piece is made up of
    private int[][] block; //The physical representation of the piece

    public Piece(colour team, type shape){
        if(!(shape instanceof type))
            throw new IllegalArgumentException("Invalid shape");
        else this.shape = shape;

        if(team!=colour.WHITE && team!=colour.BLACK)
            throw new IllegalArgumentException("Invalid colour");
        else this.team = team;

        switch(shape){ //Based on shape assigns size and draws the physical matrix of the piece
            case I1: I1(); break;
            case I2: I2(); break;
            case I3: I3(); break;
            case I4: I4(); break;
            case I5: I5(); break;
            case V3: V3(); break;
            case L4: L4(); break;
            case Z4: Z4(); break;
            case O4: O4(); break;
            case L5: L5(); break;
            case T5: T5(); break;
            case V5: V5(); break;
            case N: N(); break;
            case Z5: Z5(); break;
            case T4: T4(); break;
            case P: P(); break;
            case W: W(); break;
            case U: U(); break;
            case F: F(); break;
            case X: X(); break;
            case Y: Y(); break;
            default: throw new IllegalArgumentException("Invalid shape");
        }
    }

    public type getShape(){
        return shape;
    }

    public colour getTeam(){
        return team;
    }

    public int getSize(){
        return size;
    }

    public int getBlock(int i, int j){
        return block[i][j];
    }

    public String toString(){
        return getShape().name();
    }

    public boolean checkValid(int x, int y){ //Checks validity of placing a piece on the board
        int row, col;

        for (int i = 0; i < getSize(); i++) {
            //Update row and col positions
            row = x + getBlock(i,0);
            col = y + getBlock(i,1);

            //Is the tile on the board?
            if(row<0 || row>BOARD_SIZE-1 || col<0 || col>BOARD_SIZE-1)
                return false;
            //Is the tile blank?
            if(Board.getBoard(row, col) != colour.BLANK && Board.getBoard(row,col) != colour.START)
                return false;

            //Is an adjacent tile the same colour as this tile?
            //We add some boundary checks to prevent an ArrayOutOfBounds exception
            if(Board.getBoard(row-1, col)==getTeam() || Board.getBoard(row+1, col)==getTeam()
                    || Board.getBoard(row, col+1)==getTeam() || Board.getBoard(row, col-1)==getTeam())
                return false;
        }

        return true; //This method cannot check for diagonal collision
    }

    public boolean checkDiagonal(int x, int y){//Checks for connecting diagonal of same colour
        if(Board.firstMove)
            return true; //if it's the first move we don't care about diagonals

        int row, col;
        for (int i = 0; i < getSize(); i++) {
            //Update row and col positions
            row = x + getBlock(i,0);
            col = y + getBlock(i,1);

            if( Board.getBoard(row-1,col+1)==getTeam() || Board.getBoard(row+1,col+1)==getTeam()
                    || Board.getBoard(row-1,col-1)==getTeam() || Board.getBoard(row+1,col-1)==getTeam())
                return true;
        }

        return false;

    }

    //Checks that the piece is being placed on one of the starting tiles
    public boolean checkStart(int x, int y){
        int row, col;
        int startRow, startCol;

        //Assigns the start position based on the team
        if(getTeam() == colour.WHITE){
            startRow = 9;
            startCol = 4;
        } else {
            startRow = 4;
            startCol = 9;
        }

        for (int i = 0; i < getSize(); i++) {
            //Update row and col
            row = x + getBlock(i,0);
            col = y + getBlock(i,1);

            //Check if these coordinates match a starting tile
            if(row==startRow && col==startCol)
                return true;
        }

        return false;
    }

    public boolean canPlace(int x, int y){
        return checkValid(x, y) && checkDiagonal(x,y);
    }

    //This is the method for placing a piece on the board
    //The input coordinates x and y are the position of the origin of the pieces
    //Only one of the diagonal checks needs to be true for any given piece

    public void place(int x, int y){
        //First we do our validation checks
        if(!canPlace(x,y)){ //checks every tile is not taken
            throw new IllegalArgumentException(getShape().name() + " piece cannot be placed there!");
        }
        if(Board.firstMove){
            if(!checkStart(x,y))
                throw new IllegalArgumentException("Piece must be placed on the starting tile!");
        }

        //Now we move onto actually placing the tile onto the board
        for (int i = 0; i < getSize(); i++) {
            Board.setBoard(x+getBlock(i, 0), y+getBlock(i,1), getTeam());
        }
    }

    public void rotateClockwise(){//rotates piece 90 degrees clockwise
        //Based on 2D rotation matrix, for a 270-degree rotation we get (row, col) -> (-col, row)
        int tmp;
        for(int i=0; i<getSize(); i++){
            tmp = getBlock(i,0);
            block[i][0] = -1 * getBlock(i,1);
            block[i][1] = tmp;
        }
    }

    public void rotateAntiClockwise(){ //rotates piece 90 degrees anticlockwise
        //Based on 2D rotation matrix, for a 90-degree rotation we get (row, col) -> (col, -row)
        int tmp;
        for(int i=0; i<getSize(); i++){
            tmp = getBlock(i,0);
            block[i][0] = getBlock(i,1);
            block[i][1] = -1 * tmp;
        }
    }

    public void flip(){ //flips piece along vertical axis
        for (int i = 0; i < getSize(); i++) {
            block[i][1] *= -1;
        }
    }

    public void printPiece(){
        //these variables store the lowest and highest x coordinate and y coordinate for the piece
        int rowMin,rowMax, colMin, colMax;

        rowMin = rowMax = colMin = colMax = block[0][0];

        for(int row=0;row<size;row++)
            for(int col=0;col<2;col++){
                if(block[row][0] < rowMin){
                    rowMin = block[row][0];
                }
                if(block[row][0] > rowMax){
                    rowMax = block[row][0];
                }
                if(block[row][1] < colMin){
                    colMin = block[row][1];
                }
                if(block[row][1] > colMax){
                    colMax = block[row][1];
                }
            }
        //the dimensions needed for the piece array
        int height = rowMax - rowMin + 1;
        int width = colMax - colMin + 1;

        char [][] piece = new char[height][width];

        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                piece[row][col] = ' ';
            }
        }
        //this is how much we need to add to the coordinates, so it matches the piece array
        int rowDelta = -rowMin;
        int colDelta = -colMin;

        for(int num = 0; num < size; num++){
            //changes each coordinate in the piece array to the correct colour
            piece[block[num][0] + rowDelta] [block[num][1] + colDelta] = Board.changeEnumToChar(getTeam());
        }
        //prints out piece array
        for(int row = height-1; row >= 0; row--){
            for(int col = 0; col < width; col++){
                if(col == 0)
                    UI.draw("" + piece[row][col]);
                else
                    UI.draw(" " + piece[row][col]);
            }
            UI.draw("\n");
        }
    }

    //constructors for different pieces
    //i'm putting these down here so the constructor itself isn't all cluttered

    public void I1(){
        size = 1;
        block = new int[][]{{0, 0}};
    }

    public void I2(){
        size = 2;
        block = new int[][]{{0,0},{1,0}};
    }

    public void I3(){
        size = 3;
        block = new int[][]{{0,0},{1,0},{2,0}};
    }

    public void I4(){
        size = 4;
        block = new int[][]{{0,0},{1,0},{2,0},{3,0}};
    }

    public void I5(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{2,0},{3,0},{4,0}};
    }

    public void V3(){
        size = 3;
        block = new int[][]{{0,0},{1,0},{0,1}};
    }

    public void L4(){
        size = 4;
        block = new int[][]{{0,0},{1,0},{2,0},{0,1}};
    }

    public void Z4(){
        size = 4;
        block = new int[][]{{0,0},{1,0},{1,1},{0,-1}};
    }

    public void O4(){
        size = 4;
        block = new int[][]{{0,0},{1,0},{1,1},{0,1}};
    }

    public void L5(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{0,1},{0,2},{0,3}};
    }

    public void T5(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{2,0},{0,1},{0,-1}};
    }

    public void V5(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{2,0},{0,1},{0,2}};
    }

    public void N(){
        size = 5;
        block = new int[][]{{0,0},{0,1},{0,2},{-1,0},{-1,-1}};
    }

    public void Z5(){
        size = 5;
        block = new int[][]{{0,0},{0,1},{1,1},{0,-1},{-1,-1}};
    }

    public void T4(){
        size = 4;
        block = new int[][]{{0,0},{1,0},{0,1},{0,-1}};
    }

    public void P(){
        size = 5;
        block = new int[][]{{0,0},{0,1},{-1,1},{-1,0},{-2,0}};
    }

    public void W(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{1,1},{0,-1},{-1,-1}};
    }

    public void U(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{1,1},{-1,0},{-1,1}};
    }

    public void F(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{1,1},{-1,0},{0,-1}};
    }

    public void X(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{0,1},{-1,0},{0,-1}};
    }

    public void Y(){
        size = 5;
        block = new int[][]{{0,0},{1,0},{0,1},{0,2},{0,-1}};
    }


}
