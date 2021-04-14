package edu.colorado.caterpillars.weapons;

public abstract class Weapon{
    protected String weaponName;
    protected int uses;
    protected boolean locked;

    public Weapon(){
        uses = -1;
        locked = false;
    }

    public final String use(int row, int col){
        validChecker(row, col);
        String result = fire(row, col);
        if(uses > 0) // uses < 0 means unlimited
            uses--;
        return result;
    }
    public abstract void undoUse(int row, int col);

    public abstract String fire(int row, int col);

    private void validChecker(int row, int col){
        if(row < 0 || row >= 10 || col < 0 || col >= 10){
            throw new IndexOutOfBoundsException("Not a valid area to fire " + weaponName + "!");
        }
        if(locked){
            lockedErrorHook();
        }
        if(uses == 0){
            throw new RuntimeException("No more uses for " + weaponName + "!");
        }
    }

    public void lockedErrorHook(){
        throw new RuntimeException(weaponName + " is locked, cannot be used right now!");
    }
}
