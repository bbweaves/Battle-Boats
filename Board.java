import java.lang.Math;
import java.util.Random;

public class Board {
    Random random = new Random();
    private  int width;
    private int length;
    private Cell [][] board;
    private Boat [] boatsArray;
    private int boats = 0;
    private int turns = 1;
    private int shots = 0;
    private int totalBoats = 0;
    private int droneNum, missileNum;

    //constructors

    public Board(int width, int length){
        this.width = width;
        this.length = length;
        this.board = new Cell[length][width];
    }

    public Board(){}


    //getters/setters


    public int getDroneNum() { return droneNum; }
    public int getMissileNum() { return missileNum; }
    public int getTotalBoats() { return totalBoats; }
    public int getShots() { return shots; }
    public int getTurns() { return turns; }
    public int getLength() { return length; }
    public int getWidth() { return width; }
    public Boat[] getBoatsArray() { return boatsArray; }

    public void setDroneNum(int droneNum) {
        this.droneNum = droneNum;
    }

    public void setMissileNum(int missileNum) { this.missileNum = missileNum; }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTotalBoats(int totalBoats) {
        this.totalBoats = totalBoats;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setBoard() {
        this.board = new Cell[length][width];
    }

    public void setBoatsArray(Boat [] newArray){
        this.boatsArray = newArray;
    }

    // fills the array/board with cells
    public void fillBoard(){
        int i,j;
        for(i = 0; i < board.length; i++){
            for(j = 0; j < board[i].length; j++){
                Cell newCell = new Cell(i,j,'-');
                board[i][j] = newCell;
            }
        }
    } //fillBoard

    // places specified number of boats on the board
    public void placeBoats(){
        //determines size and how many boats to create
       if(width == 3 | length == 3){
           boatsArray = new Boat[1];
           Boat boat1 = boatCreator(2);
           boatsArray[0] = boat1;
       } else if(width == 4 | length == 4 ){
           boatsArray = new Boat[2];
           Boat boat1 = boatCreator(2);
           Boat boat2 = boatCreator(3);
           boatsArray[0] = boat1;
           boatsArray[1] = boat2;
       }else if(width <= 6 | length <= 6 ) {
           boatsArray = new Boat[3];
           Boat boat1 =boatCreator(2);
           Boat boat2 =boatCreator(3);
           Boat boat3 =boatCreator(3);
           boatsArray[0] = boat1;
           boatsArray[1] = boat2;
           boatsArray[2] = boat3;
       }else if(width <= 8 | length <= 8 ) {
           boatsArray = new Boat[4];
           Boat boat1 =boatCreator(2);
           Boat boat2 =boatCreator(3);
           Boat boat3 =boatCreator(3);
           Boat boat4 =boatCreator(4);
           boatsArray[0] = boat1;
           boatsArray[1] = boat2;
           boatsArray[2] = boat3;
           boatsArray[3] = boat4;
       }else if(width <= 10 | length <= 10 ){
           boatsArray = new Boat[5];
           Boat boat1 =boatCreator(2);
           Boat boat2 =boatCreator(3);
           Boat boat3 =boatCreator(3);
           Boat boat4 =boatCreator(4);
           Boat boat5 =boatCreator(5);
           boatsArray[0] = boat1;
           boatsArray[1] = boat2;
           boatsArray[2] = boat3;
           boatsArray[3] = boat4;
           boatsArray[4] = boat5;
       }

    } //placeBoats

    //this function creates and sets the boats
    public Boat boatCreator(int boatLength){
        Boat newBoat = new Boat(2, Math.random() > .5);
        Cell[] boatPlacement = new Cell[boatLength];
        //length = 3 0,1,2
        boolean allClear = false;
        //determines if horizontal
        if(newBoat.isHorizontal()){
            while(!allClear) {
                int difference = 0;
                String checkCondition = "";
                // if length and width differ
                if(length >= width){
                    difference = length - width;
                }
                //gives random coordinates
                int xCor = random.nextInt(length);
                int yCor = random.nextInt(length - boatLength + 1 - difference);
                for (int i = 0; i < boatLength; i++) {
                    char condition = board[xCor][yCor + i].getStatus();

                    checkCondition += condition;
                }
                // checks if random coordinates are open
                if(!checkCondition.contains("B")) {
                    for (int i = 0; i < boatLength; i++) {
                        boatPlacement[i] = board[xCor][yCor + i];
                        board[xCor][yCor + i].setStatus('B');
                    }
                    newBoat.setPlacement(boatPlacement);
                    allClear = true;
                }
            }
            //same as above, for vertical case
        }else {
            while(!allClear){
                int difference = 0;
                String checkCondition = "";
                if(width >= length){
                    difference = width - length;
                }
                int xCor = random.nextInt(width - boatLength + 1 - difference);
                int yCor = random.nextInt(width);
                for (int i = 0; i < boatLength; i++) {
                    char condition = board[xCor + i][yCor].getStatus();
                    checkCondition += condition;
                    }
                if(!checkCondition.contains("B")){
                    board[xCor][yCor].setStatus('B');
                    for (int i = 0; i < boatLength; i++) {
                        boatPlacement[i] = board[xCor + i][yCor];
                        board[xCor + i][yCor].setStatus('B');
                }
                    newBoat.setPlacement(boatPlacement);
                    allClear = true;
            }
            }
        }
        return newBoat;
    } // boatCreator


    //number of drones allotted found via board dimensions
    public void droneNumbers() {
        int dimension = length * width;

         if(dimension > 99) {
            setDroneNum(5);
        } else if(dimension > 50){
            setDroneNum(4);
        } else if(dimension > 24){
            setDroneNum(3);
        } else if(dimension > 16){
            setDroneNum(2);
        } else{
             setDroneNum(1);
         }
    } // droneNumbers

    //number of missile allotted found via board dimensions
    public void missileNumbers() {
        int dimension = length * width;

        if(dimension > 99) {
            setMissileNum(4);
        } else if(dimension > 50){
            setMissileNum(3);
        } else if(dimension > 24){
            setMissileNum(2);
        } else if(dimension > 16){
            setMissileNum(1);
        } else{
            setMissileNum(0);
        }
    } // missileNum

    //fire at coordinate
    public Boat [] fire(String strXCor, String strYCor){
        int skippedTurn = getTurns() + 1;
        int xCor = Integer.parseInt(strXCor);
        int yCor = Integer.parseInt(strYCor);
        // try catch to determine if chosen coordinates are valid
            try {
                // gets status of cell and changes it accordingly
                char cellStatus = board[xCor][yCor].getStatus();
                if (cellStatus == '-') {
                    System.out.println("Turn " + getTurns() + ":" + " Resulted in a Miss! \n");
                    board[xCor][yCor].setStatus('M');
                } else if (cellStatus == 'B') {
                    board[xCor][yCor].setStatus('H');
                    // checks all boats to determine if any need to be removed
                    Boat sunkenBoat = boatSunkenStatus(boatsArray);
                    if (sunkenBoat != null) {
                        //boat removal function
                        System.out.println("Turn " + getTurns() + ":" + " Resulted in a Hit and Sink!!! \n");
                        boatsArray = sunkenBoatRemover(boatsArray, sunkenBoat);
                    } else {
                        System.out.println("Turn " + getTurns() + ":" + " Resulted in a Hit! \n");
                    }
                    // same coordinates twice case
                } else if (cellStatus == 'M' | cellStatus == 'H') {
                    System.out.println("You've already shot at this location. Skip next turn.");
                    System.out.println("Turn " + skippedTurn + " has been skipped. \n");
                    setTurns(getTurns() + 1);
                }
                return boatsArray;
                //catch if invalid coordinates
            } catch (Exception ArrayIndexOutOfBoundsException) {
                System.out.println("Those coordinates are out of bounds. Skip next turn.");
                System.out.println("Turn " + skippedTurn + " has been skipped. \n");
                setTurns(getTurns() + 1);
                return boatsArray;
            }
    } //fire

    // use drone
    public int drone(String scanType, int scanLoc){
        int boatNum = 0;
        // ensures that scanType is a valid character
        if (scanType.equals("r")){
            //scans row and adds to boatNum if there is a boat spotted
            for(int i = 0; i < board[scanLoc].length; i++){
                if(board[scanLoc][i].getStatus() == 'B'){
                    boatNum ++;
                }
            }
            return boatNum;

            // same as above, column case
        } else if (scanType.equals("c")){
            for(int i = 0; i < board[scanLoc].length; i++){
                if(board[i][scanLoc].getStatus() == 'B'){
                    boatNum ++;
                }
            }
            return boatNum;

        }
        return -1;
    } //drone

    //missile function
    public Boat [] missile(String strXCor, String strYCor){
        int xCor = Integer.parseInt(strXCor) - 1;
        int tempYCor = Integer.parseInt(strYCor) - 1;
        int yCor = tempYCor;
        char cellStatus;
        int targetsHit = 0;
        int boatsSunk = 0;

        // ensures that coordinates are valid
        if(xCor + 1 < 0 | xCor >= width - 1 | yCor + 1 < 0 | yCor >= length - 1){
            return null;
        }

        // changes cell status in a 3 x 3 cube to H or M
        for(int i = 0; i < 3; i++, xCor++){
            for(int j = 0; j < 3; j++, yCor ++){
                // ensure coordinates are on the board
                if (xCor >= 0 & xCor <= width - 1){
                    if(yCor >= 0 & yCor <= length - 1){
                        // same as fire()
                        cellStatus = board[xCor][yCor].getStatus();
                        if (cellStatus == '-') {
                            board[xCor][yCor].setStatus('M');
                        } else if (cellStatus == 'B') {
                            targetsHit ++;
                            board[xCor][yCor].setStatus('H');
                            Boat sunkenBoat = boatSunkenStatus(boatsArray);
                            if (sunkenBoat != null) {
                                boatsSunk ++;
                                boatsArray = sunkenBoatRemover(boatsArray, sunkenBoat);
                            }
                        }
                    }
                }
            }
            //reset yCor to original
         yCor = tempYCor;
        }
        System.out.println("Your missile hit " + targetsHit + " undamaged targets and sank " + boatsSunk + " boats.");
        return boatsArray;

    }//missile


    //determines whether or not any boat has been sunk
    public Boat boatSunkenStatus(Boat[] boatPlacement){
        String status = "";
        // goes through boat array by boat
        for(int i = 0; i < boatPlacement.length; i++){
            Cell[] currentBoat = boatPlacement[i].getPlacement();
            for(int j = 0; j < currentBoat.length; j++){
                status = currentBoat[j].getStatus() + status;
                }
            // checks if all cells are hit or H
            if(!status.contains("B")){
                return boatPlacement[i];
            }
            status = "";


        }
        return null;
    } //boatSunkenStatus

    //removes sunken boat from boatList
    // code taken from source #2
    public Boat[] sunkenBoatRemover(Boat[] sunkenBoatsArray, Boat sunkenBoat){
        // creates new array, copies old boatArray and skips the sunken boat
        Boat [] newBoatArray = new Boat[sunkenBoatsArray.length - 1];
        for (int i = 0, k = 0; i < sunkenBoatsArray.length; i++) {
            if (boatsArray[i] == sunkenBoat) {
                continue;
            }
            newBoatArray[k++] = boatsArray[i];
        }
        return newBoatArray;
    } //sunkenBoatRemover

    // prints out entire board and cell statuses
    public void print(){
        int i, j;
        String finishedString = "\n";
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length;j++ ) {
                finishedString += board[i][j].getStatus();

            }
            finishedString += "\n";
        }
        System.out.println(finishedString);
    }// print

    // prints out board display while hiding the boat
    public void display(){
        int i, j;
        String finishedString = "\n";
        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board[0].length;j++ ) {
                if (board[i][j].getStatus() == 'B'){
                    finishedString += '-';
                }
                else {
                    finishedString += board[i][j].getStatus();
                }
            }
            finishedString += "\n";
        }
        System.out.println(finishedString);
    } //display




} // board
