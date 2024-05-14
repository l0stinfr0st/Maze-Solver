package mazesolverproject;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;

public class MazeSolverProject1 {

    static int[] current = {1, 1}; //{x,y}
    static int[] back = {1, 1};
    final static int grid = 10;
    static int[][] maze = getMaze(grid);
    static ArrayList<int[]> visited = new ArrayList<int[]>();
    static int stepNumber = 1;

    public static void main(String[] args) {

        Stack path = new Stack();
        path.push(current.clone());
        maze[1][1] = 2;
        MazeUtility.plotMaze(maze);
        System.out.println("- - -");

        while ((maze[2 * grid - 1][2 * grid - 1] != 2)) {
            findPath(path);
            System.out.println("");
            print2DArray(maze);
        }

        System.out.println("☻ ┉ ✪ Maze is solved! ✪ ┉ ☻");
        System.out.println("The path followed is:");
        printStack(path);

    }

    public static void update0() {
        maze[back[1]][back[0]] = 3;// remove the 0 from the previous cell. Any number except 1-2 is ok.
        maze[current[1]][current[0]] = 2;
    }

    public static int[] updateBack() {
        if (back == current.clone()) {
            return back;
        }
        back[0] = current.clone()[0];
        back[1] = current.clone()[1];

        return back;
    }

    public static void findPath(Stack path) {
        System.out.println("----------------------------------"); // first impression matters
        updateBack();
        move(current, path);
        update0();
        for (int i = 0; i < visited.size(); i++) {

            int[] xy = visited.get(i);
            System.out.print(xy[0] + "," + xy[1] + " - ");
        }
        System.out.println(" ");
        stepNumber++;
    }

    public static int[] move(int[] current, Stack path) { // first check right then below then others.

        int xC = current[0];
        int yC = current[1];

        if (rightIsWall() && rightIsVisited()) { //
            visited.add(current.clone());
            current[0] = (xC + 1);

            path.push(current.clone());
            return current.clone();

        } else if (belowIsWall() && belowIsVisited()) {
            visited.add(current.clone());
            current[1] = yC + 1;

            path.push(current.clone());
            return current.clone();

        } else if (aboveIsWall() && aboveIsVisited()) {
            visited.add(current.clone());
            current[1] = (yC - 1);

            path.push(current.clone());
            return current.clone();
        } else if (leftIsWall() && leftIsVisited()) { // something is off :(
            visited.add(current.clone());
            current[0] = (xC - 1);

            path.push(current.clone());
            return current.clone();
        } else { // if there is no available cell pop till there is a branch
            //BUT IT DOESN'T WORK PROPERLY!!! :((
            path.pop();
            MazeSolverProject1.current = (int[]) path.lastElement();

            return current.clone();

        }
    }

    public static boolean rightIsWall() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC][xC + 1] != 1;
    }

    public static boolean leftIsWall() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC][xC - 1] != 1;
    }

    public static boolean belowIsWall() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC + 1][xC] != 1;
    }

    public static boolean aboveIsWall() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC - 1][xC] != 1;
    }

    public static boolean rightIsVisited() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC][xC + 1] != 3;
    }

    public static boolean leftIsVisited() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC][xC - 1] != 3;
    }

    public static boolean belowIsVisited() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC + 1][xC] != 3;
    }

    public static boolean aboveIsVisited() {
        int xC = current.clone()[0];
        int yC = current.clone()[1];

        return maze[yC - 1][xC] != 3;
    }

    public static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void printStack(Stack path) {
        Iterator it = path.iterator();
        while (it.hasNext()) {
            int[] step = (int[]) it.next();
            System.out.print("➼ (" + step[0] + ", " + step[1] + ") ");

        }
        System.out.println(" ");
    }

    public static int[][] getMaze(int grid) {
        MazeGenerator maze = new MazeGenerator(grid);
        String str = maze.toString();
        int[][] maze2D = MazeUtility.Convert2D(str);
        return maze2D;
    }
}