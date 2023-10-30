package minesweeper;

import java.util.Scanner;

public class Game {

    MineField feelingLucky = new MineField();

    public void start() {

        feelingLucky.inputMines();
        feelingLucky.printFog();
        inputCoordinates();
    }

    public void inputCoordinates() {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.print("Set/unset mine marks or claim a cell as free");
            int col = input.nextInt() - 1;
            int row = input.nextInt() - 1;
            String command = input.next();

            switch (command) {

                case "mine":
                    feelingLucky.markField(row, col);
                    feelingLucky.printField();
                    break;

                case "free":
                    feelingLucky.initializeField(row, col);
                    feelingLucky.clickedFree(row, col);
                    feelingLucky.printField();
                    break;
            }
        }
    }
}