package minesweeper;

import java.util.*;

public class MineField {
    Scanner scanner = new Scanner(System.in);
    Cell[][] objectMineField = new Cell[9][9];
    boolean lazy = false;
    int numberOfMines;

    public void clickedFree(int row, int col) {

        if (objectMineField[row][col].mine) {
            objectMineField[row][col].fog = false;
            System.out.println("Stepped on a mine and failed!");
            gameOver();
            return;
        }
        floodFill(objectMineField, row, col);
    }

    public void gameOver() {

        for (int i = 0; i < objectMineField.length; i++) {
            for (int j = 0; j < objectMineField[0].length; j++) {
                Cell cell = objectMineField[i][j];
                cell.fog = false;
                cell.flag = false;
            }
        }
    }

    public void inputMines() {
        System.out.print("How many mines do you want on the field? ");
        numberOfMines = scanner.nextInt();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                objectMineField[i][j] = new Cell(false, false, true, 0);
            }
        }
    }

    public void initializeField(int row, int col) {

        if (lazy) return;
        lazy = true;

        Random random = new Random();
        while (numberOfMines > 0) {
            int rowForMine = random.nextInt(9);
            int colForMine = random.nextInt(9);
            if (rowForMine == row && colForMine == col) continue;
            if (objectMineField[rowForMine][colForMine].mine) continue;
            objectMineField[rowForMine][colForMine].mine = true;
            numberOfMines--;
        }
        generateNumbers();
    }

    public void printField() {

        System.out.print(" " + "|");
        for (int i = 1; i < 10; i++) {
            System.out.print(i);
        }
        System.out.print("|\n");
        System.out.println("-|---------|");

        int x = 1;

        for (int i = 0; i < 9; i++) {
            System.out.print(x++ + "|");
            for (int j = 0; j < 9; j++) {
                var cell = objectMineField[i][j];
                cell.print();
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    public void generateNumbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int count = countMinesAround(i, j);
                objectMineField[i][j].minesAround = count;

            }
        }
    }

    public int countMinesAround(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < 9 && newCol >= 0 && newCol < 9 && objectMineField[newRow][newCol].mine) {
                    count++;
                }
            }
        }
        return count;
    }

    public void markField(int row, int col) {

        if (objectMineField[row][col].flag) {
            objectMineField[row][col].flag = false;
        } else {
            objectMineField[row][col].flag = true;
        }
    }

    public void floodFill(Cell[][] grid, int r, int c) {

        //quit if off the grid:
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;

        //quit if visited:
        if (!grid[r][c].fog) return;
        grid[r][c].fog = false;
        grid[r][c].flag = false;

        //quit if hit wall:
        if (grid[r][c].minesAround > 0) return;

        //recursively fill in all directions
        floodFill(grid, r + 1, c);
        floodFill(grid, r - 1, c);
        floodFill(grid, r, c + 1);
        floodFill(grid, r, c - 1);
        floodFill(grid, r + 1, c + 1);
        floodFill(grid, r - 1, c - 1);
        floodFill(grid, r + 1, c - 1);
        floodFill(grid, r - 1, c + 1);

    }

    public void printFog() {

        System.out.print(" " + "|");
        for (int i = 1; i < 10; i++) {
            System.out.print(i);
        }
        System.out.print("|\n");
        System.out.println("-|---------|");

        int x = 1;

        for (int i = 0; i < 9; i++) {
            System.out.print(x++ + "|");
            for (int j = 0; j < 9; j++) {
                System.out.print(".");
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
}