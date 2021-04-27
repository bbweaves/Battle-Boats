public class Boat {
    private int boatLength;
    private boolean horizontal;
    private Cell[] placement;

    // constructor
    public Boat(int initLength, boolean initHorizontal){
        this.boatLength = initLength;
        this.horizontal = initHorizontal;
    }

    //getters/setters
    public boolean isHorizontal() {
        return horizontal;
    }
    public Cell[] getPlacement() {
        return placement;
    }

    public void setPlacement(Cell[] placement) {
        this.placement = placement;
    }
} //boat
