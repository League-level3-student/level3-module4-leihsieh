package _04_Maze_Maker;
import java.awt.Color;
import java.awt.Graphics;

public class Maze {
    // 1. Create a 2D array of cells. Don't initialize it.
    Cell[][] cells;;
    private int rows;
    private int cols;
    int size = 100;
    int dotSize = 4;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // 2. Initialize the cells array using the rows and cols variables
        cells = new Cell[rows][cols];
        // 3. Iterate through each cell and initialize it
        //    using row and col as the cell location
        for(int i=0; i<rows; i++) {
        	for(int j=0; j<cols; j++) {
        		cells[i][j] = new Cell(i, j);
        	}
        }
    }

    // 4. This method iterates through the cells and draws them
    public void draw(Graphics g) {
    	for(int i=0; i<cells.length; i++) {
        	for(int j=0; j<cells[i].length; j++) {
        		cells[i][j].draw(g);
        	}
    	}
    }

    // 5. This method returns the selected cell
    public Cell getCell(int row, int col){
        return cells[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
