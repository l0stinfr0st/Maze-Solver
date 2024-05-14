package mazesolverproject;

public class MazeSolverProject {

    public static void main(String[] args) {
        int grid = 10;
        int[][] maze = getMaze(grid);
        Stack<String> path = new Stack<String>();
        movement move = new movement(maze, grid, path);
        maze[9][9] = 3;
        MazeUtility.plotMaze(maze);
        System.out.println("----------------------------------------------");
        move.exploreMaze();
        System.out.print("Path To Take: ");
        printStackReverse(move.shortestPath);
        System.out.println("");
    }

    public static int[][] getMaze(int grid) {
        MazeGenerator maze = new MazeGenerator(grid);
        String str = maze.toString();
        int[][] maze2D = MazeUtility.Convert2D(str);
        return maze2D;
    }

    public static void printStackReverse(Stack<String> stack) {
        if (!stack.isEmpty()) {
            String item = stack.pop();
            printStackReverse(stack);
            System.out.print(item);
            stack.push(item);
        }
    }

    private static class movement {

        int[][] maze;
        int grid;
        int locX = 1;
        int locY = 1;
        Stack<String> backTrack;
        Stack<String> shortestPath;

        public movement(int[][] maze, int grid, Stack<String> path) {
            this.maze = maze;
            this.backTrack = new Stack<String>();
            this.shortestPath = path;
            this.grid = grid;
            maze[locY][locX] = 2;
            maze[2 * grid - 1][2 * grid - 1] = 3;
        }

        public void exploreMaze() {
                while (maze[2 * grid - 1][2 * grid - 1] != 2) {
                System.out.println("");
                if (maze[locY][locX + 1] != 4 && move("R")) {
                    shortestPath.push("R");
                    backTrack.push("L");
                    MazeUtility.plotMaze(maze);
                    System.out.println("----------------------------------------------");
                } else if (maze[locY + 1][locX] != 4 && move("D")) {
                    shortestPath.push("D");
                    backTrack.push("U");
                    MazeUtility.plotMaze(maze);
                    System.out.println("----------------------------------------------");
                } else if (maze[locY][locX - 1] != 4 && move("L")) {
                    shortestPath.push("L");
                    backTrack.push("R");
                    MazeUtility.plotMaze(maze);
                    System.out.println("----------------------------------------------");
                } else if (maze[locY - 1][locX] != 4 && move("U")) {
                    shortestPath.push("U");
                    backTrack.push("D");
                    MazeUtility.plotMaze(maze);
                    System.out.println("----------------------------------------------");
                } else if (!backTrack.isEmpty()) {
                    move(backTrack.pop());
                    shortestPath.pop();
                    MazeUtility.plotMaze(maze);
                    System.out.println("----------------------------------------------");
                }
            }
        }

        public boolean move(String direction) {
            switch (direction) {
                case "R":
                    if (maze[locY][locX + 1] == 1) {
                        return false;
                    } else {
                        maze[locY][locX] = 4;
                        locX++;
                        maze[locY][locX] = 2;
                        return true;
                    }
                case "D":
                    if (maze[locY + 1][locX] == 1) {
                        return false;
                    } else {
                        maze[locY][locX] = 4;
                        locY++;
                        maze[locY][locX] = 2;
                        return true;
                    }
                case "L":
                    if (maze[locY][locX - 1] == 1) {
                        return false;
                    } else {
                        maze[locY][locX] = 4;
                        locX--;
                        maze[locY][locX] = 2;
                        return true;
                    }
                case "U":
                    if (maze[locY - 1][locX] == 1) {
                        return false;
                    } else {
                        maze[locY][locX] = 4;
                        locY--;
                        maze[locY][locX] = 2;
                        return true;
                    }
            }
            return false;
        }
    }
}
