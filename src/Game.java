//TODO toroidal array works, checkBoard now counts up all neighbors and returns it as an array.
//TODO next task is implementing the logic of the game




public class Game {

    public static void main(String[] args){

        int rows, columns;
        rows = 50;
        columns = 50;

        //set up board with all spaces, x will denote a living square
        char game[][] = new char[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                game[i][j] = ' ';
            }
        }





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

}
