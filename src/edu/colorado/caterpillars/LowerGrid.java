package edu.colorado.caterpillars;

public class LowerGrid extends Grid{

    private Fleet fleet = new Fleet();
    private  int[][] submergedGrid = new int[10][10];

    public Fleet getFleet(){
        return fleet;
    }

    public int[][] getSubmergedGrid(){
        return submergedGrid;
    }

    public void addShip(Ship ship, int row, int col, String direction,boolean submerge){
        int [][] g;
        if(submerge){
            if(!ship.canSubmerge()){
                throw new IllegalArgumentException("This ship cannot be submerged!");
            }else {
                g = submergedGrid;
            }

        }else{
            g = grid;
        }
        if(isInvalidPlacement(ship, row, col, direction,g)) // checks for overlap, out of bounds, and invalid direction
            throw new IllegalArgumentException("Ships cannot overlap or hang off of grid!");
        fleet.addShip(ship);
        int id = ship.getID();
        int cid = ship.getCID();
        for(int i = 0; i < ship.getDimension()[0]; i++){
            for(int j = 0; j < ship.getDimension()[1]; j++){
                if (direction == "N"){
                    if(ship.getShape()[i][j] == 2){
                        g[row-j][col+i] = cid;
                    }else if(ship.getShape()[i][j] == 1){
                        g[row-j][col+i] = id;
                    }
                }
                else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
                    if(ship.getShape()[i][j] == 2){
                        g[row+j][col-i] = cid;
                    }else if(ship.getShape()[i][j] == 1){
                        g[row+j][col-i] = id;
                    }
                }
                else if (direction == "E"){
                    if(ship.getShape()[i][j] == 2){
                        g[row+i][col+j] = cid;
                    }else if(ship.getShape()[i][j] == 1){
                        g[row+i][col+j] = id;
                    }
                }
                else if (direction == "W"){
                    if(ship.getShape()[i][j] == 2){
                        g[row-i][col-j] = cid;
                    }else if(ship.getShape()[i][j] == 1){
                        g[row-i][col-j] = id;
                    }
                }
            }
        }
    }

    private boolean isInvalidPlacement(Ship ship, int row, int col, String direction,int[][] g){
        for(int i = 0; i < ship.getDimension()[0]; i++){
            for(int j = 0; j < ship.getDimension()[1]; j++){
                if (direction == "N"){
                    if(row-j >= 10 || row-j < 0 || col+i >= 10 ||
                            col+i < 0 || g[row-j][col+i] != 0)
                        return true;
                }
                else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
                    if(row+j >= 10 || row+j < 0 || col-i >= 10 ||
                            col-i < 0 || g[row+j][col-i] != 0)
                        return true;
                }
                else if (direction == "E"){
                    if(row+i >= 10 || row+i < 0 || col+j >= 10 ||
                            col+j < 0 || g[row+i][col+j] != 0)
                        return true;
                }
                else if (direction == "W"){
                    if(row-i >= 10 || row-i < 0 || col-j >= 10 ||
                            col-j < 0 || g[row-i][col-j] != 0)
                        return true;
                }
                else{
                    throw new IllegalArgumentException("Not a valid direction!");
                }
            }
        }
        return false;
    }

    public String receiveAttack(int row, int col){
        BasicAttack basic = new BasicAttack(this);
        return basic.use(row, col);
    }
    public String receiveAttack(Weapon weapon, int row, int col) {
        return weapon.use(row, col);
    }

    public int[][] moveFleet(String dir) {
        int[][] lowerGrid = getGrid();
        int[][] subGrid = getSubmergedGrid();
        int[][] newGrid = new int[10][10];
        int[][] newSubGrid = new int[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0 || i == 9 && j == 0 || j == 9){
                    if(lowerGrid[i][j] > 0 || subGrid[i][j] > 0){
                        throw new IllegalArgumentException("Fleet cannot be moved that direction, ship located on the corner of the grid.");
                    }
                }
            }
        }
        if(dir == "W") { //a = row b = col
            for(int a = 0; a < 10; a++) {
                for (int b = 1; b < 10; b++) {
                    newGrid[a][b-1] = lowerGrid[a][b];
                    newSubGrid[a][b-1] = subGrid[a][b];
                }
            }
        }
        else if(dir == "E") {
            for(int a = 0; a < 10; a++) {
                for (int b = 0; b < 9; b++) {
                    newGrid[a][b+1] = lowerGrid[a][b];
                    newSubGrid[a][b+1] = subGrid[a][b];
                }
            }
        }
        else if(dir == "S") {
            for(int a = 0; a < 9; a++) {
                for (int b = 0; b < 10; b++) {
                    newGrid[a+1][b] = lowerGrid[a][b];
                    newSubGrid[a+1][b] = subGrid[a][b];
                }
            }
        }
        else if(dir == "N") {
            for(int a = 1; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    newGrid[a-1][b] = lowerGrid[a][b];
                    newSubGrid[a-1][b] = subGrid[a][b];
                }
            }
        }
        else{
            throw new IllegalArgumentException("Not a valid direction!");
        }
        for(int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                lowerGrid[x][y] = newGrid[x][y];
                subGrid[x][y] = newSubGrid[x][y];
            }
        }
        return lowerGrid;
        // I dont know if we have to return the subgrid as well
    }


//        if(grid[row][col] > 0){
//            int id = grid[row][col];
//            String result = fleet.hitShipById(id);
//            if(!result.equals("MISS")){
//                grid[row][col] = -id; // -ship.id represents a ship coord that has been hit (should be <= -2)
//            }
//            if(result.contains("SUNK") || result.equals("SURRENDER")){
//                Ship ship = fleet.getShipById(id);
//                int sid = ship.getID();
////                int cid = ship.getCID();
//                int h = grid.length;
//                int w = grid[0].length;
//                for(int i = 0; i < h; i++){
//                    for(int j = 0; j < w; j++){
//                        if(grid[i][j] == sid) {
//                            grid[i][j] = -sid;
//                        }
////                        else if(grid[i][j] == cid){
////                            grid[i][j] = -cid;
////                        }
//                    }
//                }
//
//            }
//            return result;
//        }
//        else{
//            grid[row][col] = -1; // -1 should indicate miss
//            return "MISS";
//        }
    }

