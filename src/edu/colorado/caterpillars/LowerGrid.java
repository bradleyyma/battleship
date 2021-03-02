package edu.colorado.caterpillars;

public class LowerGrid extends Grid{

    private Fleet fleet = new Fleet();

    public void addShip(Ship ship, int row, int col, String direction){
        if(isInvalidPlacement(ship, row, col, direction)) // checks for overlap, out of bounds, and invalid direction
            throw new IllegalArgumentException("Ships cannot overlap!");
        int id = ship.getID();
        for(int i = 0; i < ship.getSize(); i++){
            if (direction == "N"){
                grid[row--][col] = id;
            }
            else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
                grid[row++][col] = id;
            }
            else if (direction == "E"){
                grid[row][col++] = id;
            }
            else if (direction == "W"){
                grid[row][col--] = id;
            }
        }
        fleet.addShip(ship);
    }

    private boolean isInvalidPlacement(Ship ship, int row, int col, String direction){
        for(int i = 0; i < ship.getSize(); i++){
            if (direction == "N"){
                if(grid[row--][col] != 0)
                    return false;
            }
            else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
                if(grid[row++][col] != 0)
                    return true;
            }
            else if (direction == "E"){
                if(grid[row][col++] != 0)
                    return true;
            }
            else if (direction == "W"){
                if(grid[row][col--] != 0)
                    return true;
            }
            else{
                throw new IllegalArgumentException("Not a valid direction!");
            }
        }
        return false;
    }

    public String receiveAttack(int row, int col){
        if(grid[row][col] > 0){
            int id = grid[row][col];
            Ship targetShip = fleet.getShipById(id);
            targetShip.hit();
            grid[row][col] = -id; // -ship.id represents a ship coord that has been hit (should be <= -2)
            if(targetShip.isSunk()){
                if(fleet.getNumSurvivingShips() == 0)
                    return "SURRENDER";
                return "SUNK " + targetShip.getName();
            }
            return "HIT";
        }
        else{
            grid[row][col] = -1; // -1 should indicate miss
            return "MISS";
        }
    }

}
