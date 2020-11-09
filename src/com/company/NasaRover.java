package com.company;

/**
 * NasaRover class uses a two-dimensional array as a coordinate grid map.
 * The grid size is based on user input. Each point on the grid is represented
 * by a pair of numbers (X Y) coordinates that map the grid.
 *
 * Methods are package private.
 */
public class NasaRover {
    private int [][] grid;
    private int colPosition, rowPosition, originalColSize, originalRowSize;
    private char heading;

    public NasaRover(int x, int y) {
        grid = new int [x] [y];
        originalColSize = x;
        originalRowSize = y;
        colPosition = 0;
        rowPosition = 0;
    }

    public int getColPosition() {
        return colPosition;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public char getHeading() {
        return heading;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public void setHeading(char heading) {
        this.heading = heading;
    }

    void updatePosition(String [] positionAndHeading) {
        // grid check
        int x = Integer.valueOf(positionAndHeading[0]);
        int y = Integer.valueOf(positionAndHeading[1]);
        // ignore invalid position updates
        if(isValidCoordinate(x, y)) {
            setColPosition(x);
            setRowPosition(y);
            setHeading(positionAndHeading[2].charAt(0));
        }
    }

    void move(String instruction) {
        for (int i=0; i<instruction.length(); ++i) {
            updateHeading(instruction.charAt(i));
        }
        // log coordinates
        System.out.println(getColPosition() + " " + getRowPosition() + " " + getHeading());
    }

    private boolean isValidCoordinate(int x, int y) {
        boolean isValidResponse = true;
        if(x < 0 || x > originalColSize)
            isValidResponse = false;
        if(y < 0 || y > originalRowSize)
            isValidResponse = false;
        return isValidResponse;
    }

    private void updateHeading(char newHeading) {
        switch (newHeading) {
            case 'L':
                // turn 90 degrees left
                if(heading == 'N')
                    heading = 'W';
                else if(heading == 'S')
                    heading = 'E';
                else if(heading == 'E')
                    heading = 'N';
                else if(heading == 'W')
                    heading = 'S';
                break;
            case 'R':
                // turn 90 degrees right
                if(heading == 'N')
                    heading = 'E';
                else if(heading == 'S')
                    heading = 'W';
                else if(heading == 'E')
                    heading = 'S';
                else if(heading == 'W')
                    heading = 'N';
                break;
            case 'M':
                // move forward one grid point
                if(heading == 'N')
                    setRowPosition(getRowPosition() + 1);
                else if(heading == 'S')
                    setRowPosition(getRowPosition() - 1);
                else if(heading == 'E')
                    setColPosition(getColPosition() + 1);
                else if(heading == 'W')
                    setColPosition(getColPosition() - 1);
                break;
            default:
                // do nothing
                break;
        }
    }
}
