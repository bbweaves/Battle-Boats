public class Cell {
    private int row;
    private int column;
    private char status;

    //constructor
    public Cell(int initRow, int initColumn, char initStatus){
        this.row = initRow;
        this.column = initColumn;
        this.status = initStatus;
    }

    //getters/setters
    public char getStatus(){
        return status;
    }

    public void setStatus(char newStatus){
        status = newStatus;
    }

} // cell
