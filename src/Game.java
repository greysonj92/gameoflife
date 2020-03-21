//TODO add gui and clean up


import java.util.Arrays;

public class Game {

    public static void main(String[] args){

        int rows, columns;
        rows = 5;
        columns = 5;

        //set up board with all spaces, x will denote a living square
        char game[][] = new char[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                game[i][j] = ' ';
            }
        }

        game[2][0] = 'x';
        game[3][1] = 'x';
        game[3][2] = 'x';
        game[2][2] = 'x';
        game[1][2] = 'x';

        printBoard(game);

        int neighbors[][] = new int[rows][columns];

        while(true){
           neighbors = checkBoard(game);
           game = updateBoard(game, neighbors);
           printBoard(game);

        }





    }
    //updates the game board to current iteration, killing and giving birth to cells
    public static char[][] updateBoard(char game[][], int[][] neighbors){

        for( int i = 0; i < game.length -1; i++){
            for( int j = 0; j < game[0].length -1; j++){
                //rule 1, Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                if(game[i][j] == 'x' && neighbors[i][j] < 2){
                    game[i][j] = ' ';
                }
                //rule 2, ny live cell with two or three live neighbours lives on to the next generation.
                else if(game[i][j] == 'x' && (neighbors[i][j] == 2 || neighbors[i][j] == 3) ){
                    //do nothing
                }
                //rule 3, Any live cell with more than three live neighbours dies, as if by overpopulation.
                else if(game[i][j] == 'x' && neighbors[i][j] > 3){
                    game[i][j] = ' ';
                }
                //rule 4, Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                else if(game[i][j] == ' ' && neighbors[i][j] == 3){
                    game[i][j] = 'x';
                }
            }
        }
        return game;
    }

    //checks each cell of the board and determines if it lives or dies in the next round
    public static int[][] checkBoard(char game[][]){

        //new array to hold each cell's number of neighbors, set them initially to zero
        int neighbors[][] = new int[game.length][game[0].length];
        for(int i = 0; i < game.length; i++){
            for(int j = 0; j < game[0].length; j++){
                //checks if neighbors are alive, then increments the neighbor count if it is
                int temp[] = new int[2];
                temp = checkNNeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkNENeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkENeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkSENeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkSNeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkSWNeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkWNeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
                temp = checkNWNeighbor(game, i, j);
                if(game[temp[0]][temp[1]] == 'x'){
                    neighbors[i][j] += 1;
                }
            }
        }
        return neighbors;
    }
    //determines the north neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkNNeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(i -1 >= 0){
            neighborIndex[0] = i-1;
            neighborIndex[1] = j;
            return neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
            neighborIndex[0] = game.length-1;
            neighborIndex[1] = j;
            return neighborIndex;
        }

    }
    //determines the south neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkSNeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if( i + 1 <= game.length-1){
            neighborIndex[0] = i + 1;
            neighborIndex[1] = j;
            return  neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
            neighborIndex[0] = 0;
            neighborIndex[1] = j;
            return neighborIndex;
        }
    }
    //determines the east neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkENeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(j + 1 <= game[0].length -1){
            neighborIndex[0] = i;
            neighborIndex[1] = j + 1;
            return  neighborIndex;
        }
        //if not, return toroidal neighbor
        else{
            neighborIndex[0] = i;
            neighborIndex[1] = 0;
            return neighborIndex;
        }
    }
    //determines the west neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkWNeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(j - 1 >= 0){
            neighborIndex[0] = i;
            neighborIndex[1] = j - 1;
            return neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
            neighborIndex[0] = i;
            neighborIndex[1] = game[0].length -1;
            return neighborIndex;
        }
    }

    //determines the northeast neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkNENeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(i - 1 >= 0 && j + 1 <= game[0].length - 1){
            neighborIndex[0] = i - 1;
            neighborIndex[1] = j + 1;
            return neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
           int[] NENeighbor = new int[2];
           NENeighbor = checkNNeighbor(game, i, j);
           NENeighbor = checkENeighbor(game, NENeighbor[0], NENeighbor[1]);
           return NENeighbor;
        }
    }

    //determines the southeast neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkSENeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(i + 1 <= game.length -1 && j + 1 <= game[0].length -1){
            neighborIndex[0] = i +1;
            neighborIndex[1] = j + 1;
            return neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
            int[] SENeighbor = new int[2];
            SENeighbor = checkSNeighbor(game, i, j);
            SENeighbor = checkENeighbor(game,SENeighbor[0],SENeighbor[1]);
            return SENeighbor;
        }

    }
    //determines the southwest neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkSWNeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(i + 1 <= game.length -1 && j - 1 >= 0){
            neighborIndex[0] = i + 1;
            neighborIndex[1] = j - 1;
            return neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
            int[] SWNeighbor = new int[2];
            SWNeighbor = checkSNeighbor(game, i, j);
            SWNeighbor = checkWNeighbor(game, SWNeighbor[0], SWNeighbor[1]);
            return SWNeighbor;
        }

    }

    //determines the northwest neighbor, if normal neighbor is out of bounds, returns the toroidal neighbor
    public static int[] checkNWNeighbor(char game[][], int i, int j){
        int neighborIndex[] = new int[2];
        //check if normal neighbor exists
        if(i - 1 >= 0 && j - 1 >= 0){
            neighborIndex[0] = i - 1;
            neighborIndex[1] = j - 1;
            return neighborIndex;
        }
        //if not, return the toroidal neighbor
        else{
            int [] NWNeighbor = new int[2];
            NWNeighbor = checkNNeighbor(game, i, j);
            NWNeighbor = checkWNeighbor(game, NWNeighbor[0], NWNeighbor[1]);
            return NWNeighbor;
        }
    }

    //prints the game board to the console
    public static void printBoard(char game[][]){
// didn't accurately print board
//        for(int i = 0; i < game.length -1; i++){
//            for( int j = 0; j < game[0].length -1; j++){
//                System.out.print(game[i][j]);
//            }
//            System.out.print('\n');
//        }
        //https://stackoverflow.com/questions/19648240/java-best-way-to-print-2d-array
        System.out.println(Arrays.deepToString(game)
                .replace("],","\n").replace(",","\t| ")
                .replaceAll("[\\[\\]]", " "));
    }

}
