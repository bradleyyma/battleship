package edu.colorado.caterpillars;

public class LowerGrid extends Grid{

    private Fleet fleet = new Fleet();

    public void addShip(Ship ship, int row, int col, String direction){
        if(isInvalidPlacement(ship, row, col, direction)) // checks for overlap, out of bounds, and invalid direction
            throw new IllegalArgumentException("Ships cannot overlap!");

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
        String result;
        if(grid[row][col] > 0){
            int id = grid[row][col];
            Ship targetShip = fleet.getShipById(id);
            targetShip.hit();
            if(targetShip.isSunk()){
                if(fleet.getNumSurvivingShips() == 0)
                    return "SURRENDER";
                return "SUNK " + targetShip.getName();
            }
            return "HIT";
        }
        else{
            return "MISS";
        }
    }


//    public String attack(int row, int col, int playerNum){
//
//        if(lower[row][col] > 0){ // indicates ship is at location ( a hit)
//            int id = lower[row][col];
//            lower[row][col] *= -1; // cannot be -1 (ship.id's will start at 2)
//            upper[row][col] = 1; //indicates hit
//            result = "HIT";
//            // Interact with fleet and update ship info
//            // TODO: Put in try catch
//            Ship targetShip = fleet.getShipById(id);
//            targetShip.hit();
//            if(targetShip.isSunk()){
//                result = "SUNK " + targetShip.getName();
//                if(fleet.getNumSurvivingShips() == 0)
//                    result = "SURRENDER";
//            }
//
//        }
//        else{ // indicates a miss
//            lower[row][col] = upper[row][col] = -1;
//            result = "MISS";
//        }
//        return result;
//    }

}
