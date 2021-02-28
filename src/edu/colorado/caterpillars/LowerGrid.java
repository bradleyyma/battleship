package edu.colorado.caterpillars;

public class LowerGrid extends Grid{
    public void addShip(Ship ship, int row, int col, String direction){
//        if(doesOverlap(ship, row, col, direction))
//            throw new IllegalArgumentException("Ships cannot overlap!");

        for(int i = 0; i < ship.getSize(); i++){
            if (direction == "N"){
                grid[row--][col] = ship.id;
            }
            else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
                grid[row++][col] = ship.id;
            }
            else if (direction == "E"){
                grid[row][col++] = ship.id;
            }
            else if (direction == "W"){
                grid[row][col--] = ship.id;
            }
            else{
                System.out.println("addShip is broken");
            }
        }
    }
//
//    private boolean doesOverlap(int [][] grid,Ship ship,int row,int col, String direction){
//        for(int i = 0; i < ship.getSize(); i++){
//            if (direction == "N"){
//                if(grid[row--][col] != 0)
//                    return true;
//            }
//            else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
//                if(grid[row++][col] != 0)
//                    return true;
//            }
//            else if (direction == "E"){
//                if(grid[row][col++] != 0)
//                    return true;
//            }
//            else if (direction == "W"){
//                if(grid[row][col--] != 0)
//                    return true;
//            }
//            else{
//                // TODO: throw exception for invalid direction
//                return false;
//            }
//        }
//        return false;
//    }
}
