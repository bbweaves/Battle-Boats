import java.util.Scanner;
public class Game {

    public static void main(String[] args){
        Board newBoard = new Board();
        boolean debug = false;


        // determines width and length of board is set correctly
        Scanner myScanner = new Scanner(System.in);
            System.out.println("Welcome!");
            // allows for debug mode initialization
            System.out.println("Would you like to play this game in debug mode? Enter Y for yes");
            if (myScanner.next().equals("Y")){
                System.out.println("This game will be played in debug mode.\n");
                debug = true;
            } else{
                System.out.println("Debug mode will not run in this game.\n");
            }
            System.out.println("Please enter the size of your board ranging from 3 to 10");
            System.out.println("The size of the board will determine how many missiles and drones are available.");
            System.out.println("Enter the width of the board: ");

            // ensures width and legth are within the bounds
            int width = myScanner.nextInt();
            if( width > 10){
                System.out.println("Value entered over limit, width defaulting to 10");
                width = 10;
            } else if ( width < 3){
                System.out.println("Value entered under limit, length defaulting to 3");
                width = 3;
            }
            System.out.println("Enter the length of the board: ");
            int length = myScanner.nextInt();
            if( length > 10){
                System.out.println("Value entered over limit, length defaulting to 10");
                length = 10;
            } else if ( length < 3){
                System.out.println("Value entered under limit, length defaulting to 3");
                length = 3;
            }

            //creates and sets board, randomly places boats, gives missile and drone numbers
            newBoard.setLength(length);
            newBoard.setWidth(width);
            newBoard.setBoard();
            newBoard.fillBoard();
            newBoard.placeBoats();
            newBoard.droneNumbers();
            newBoard.missileNumbers();;
            newBoard.setTotalBoats(newBoard.getBoatsArray().length);
            // used to tell user how many boats were sunk
            int originalBoats = newBoard.getBoatsArray().length;

            //for board printing
            while(newBoard.getBoatsArray().length != 0){
                if(debug){
                    newBoard.print();
                } else{
                    newBoard.display();
                }

                //user information
                System.out.println("Please enter your coordinates like so: 0 0 or 0 1");
                System.out.println("Type 'missile' to use a missile or 'drone' to use a drone");
                if(newBoard.getDroneNum() != 1){
                    System.out.println("You have " + newBoard.getDroneNum() + " drones remaining.");
                }else {
                    System.out.println("You have 1 drone left.");
                }
                if(newBoard.getMissileNum() != 1){
                    System.out.println("You have " + newBoard.getMissileNum() + " missiles remaining.");
                }else {
                    System.out.println("You have 1 missile left.");
                }
                if(newBoard.getTotalBoats() > 1){
                    System.out.println("There are " + newBoard.getTotalBoats() + " boats remaining!");
                } else{
                    System.out.println("There is 1 boat remaining!");
                }
                System.out.print("Turn " + newBoard.getTurns() + ": Select Coordinates:");

                // user input
                String input1 = myScanner.next();
                //drone check
                if(input1.equals("drone")){
                    // ensures there are drones remaining
                    if(newBoard.getDroneNum() == 0){
                        System.out.println("No drones remaining. Please enter new coordinates.");
                    } else {
                        // ensures droneChar is valid
                        System.out.println("Drone initialized, please enter 'r' for row or 'c' for column.");
                        String droneChar = myScanner.next();
                        int drone = newBoard.drone(droneChar, 0);
                        while (drone == -1) {
                            System.out.println("Incorrect input. Please enter 'r' or 'c'.");
                            droneChar = myScanner.next();
                            drone = newBoard.drone(droneChar, 0);
                        }
                        //ensures row/column is valid
                        if (droneChar.equals("r")) {
                            System.out.println("Please enter the row to be scanned.");
                            int droneCoord = myScanner.nextInt();
                            while (droneCoord < 0 | droneCoord > newBoard.getLength()) {
                                System.out.println("Invalid input. Please type a number within the bounds of the board");
                                droneCoord = myScanner.nextInt();
                            }
                            // drone used
                            int boatNum = newBoard.drone(droneChar, droneCoord);
                            newBoard.setDroneNum(newBoard.getDroneNum() - 1);
                            newBoard.setTurns(newBoard.getTurns() + 1);
                            System.out.println("Your drone spotted " + boatNum + " undamaged targets in this row.");
                            System.out.println("You have " + newBoard.getDroneNum() + " drones remaining.");
                        //same as above - column case
                        } else if (droneChar.equals("c")) {
                            System.out.println("Please enter the column to be scanned");
                            int droneCoord = myScanner.nextInt();
                            while (droneCoord < 0 | droneCoord > newBoard.getWidth()) {
                                System.out.println("Invalid input. Please type a number within the bounds of the board");
                                droneCoord = myScanner.nextInt();
                            }
                            int boatNum = newBoard.drone(droneChar, droneCoord);
                            newBoard.setDroneNum(newBoard.getDroneNum() - 1);
                            newBoard.setTurns(newBoard.getTurns() + 1);
                            System.out.println("Your drone spotted " + boatNum + " undamaged targets in this column.");
                            System.out.println("You have " + newBoard.getDroneNum() + " drones remaining.");
                        }
                    }

                    //check if missile is called
                } else if (input1.equals("missile")) {
                    // ensures there are enough missiles
                    if(newBoard.getMissileNum() == 0) {
                        System.out.println("No missiles remaining. Please enter new coordinates.");
                    } else{
                        // asks user for input
                        System.out.println("Missile initialized. Please enter valid coordinates.");
                        String inputM1 = myScanner.next();
                        String inputM2 = myScanner.next();
                        Boat [] missile = newBoard.missile(inputM1, inputM2);
                        //ensures coordinates are correct
                        while (missile == null) {
                            System.out.println("Invalid. Please enter coordinates contained within the board.");
                            inputM1 = myScanner.next();
                            inputM2 = myScanner.next();
                            missile = newBoard.missile(inputM1, inputM2);
                            newBoard.setBoatsArray(missile);
                        }
                        // decreases missile count
                        newBoard.setMissileNum(newBoard.getMissileNum() - 1);
                        System.out.println("You have " + newBoard.getMissileNum() + " missiles remaining.");
                    }

                }
                // if missile or drone are not initialized fire is used
                else {
                    String input2 = myScanner.next();
                    newBoard.setBoatsArray(newBoard.fire(input1, input2));
                }
                // counts boats and increases shots and turns
                newBoard.setTotalBoats(newBoard.getBoatsArray().length);
                newBoard.setTurns(newBoard.getTurns() + 1);
                newBoard.setShots(newBoard.getShots() + 1);
            }
            // victory display
            myScanner.close();
            newBoard.display();
            System.out.println("Congratulations, you completed a game on a " + length + " x " + width + " board." );
            System.out.println("You had " + newBoard.getTurns() + " turns, " + newBoard.getShots() + " shots" +
                    " and sank " + originalBoats + " boat(s).");
    }//main

} // game
