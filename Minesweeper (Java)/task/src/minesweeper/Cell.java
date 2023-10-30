package minesweeper;

public class Cell {

    boolean flag;
    boolean mine;
    boolean fog;
    int minesAround;

    public Cell(boolean flag, boolean mine, boolean fog, int minesAround) {

        this.flag = flag;
        this.mine = mine;
        this.fog = fog;
        this.minesAround = minesAround;
    }

    public void print() {

        if (this.flag) {
            System.out.print("*");
        } else if (this.fog) {
            System.out.print(".");
        } else if (this.mine) {
            System.out.print("X");
        } else if (this.minesAround != 0) {
            System.out.print(this.minesAround);
        } else {
            System.out.print("/");
        }
    }
}
