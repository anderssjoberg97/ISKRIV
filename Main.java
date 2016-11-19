package anderssjoberg.maze;

import java.util.ArrayList;

import anderssjoberg.maze.generation.MazeGenerator;
import anderssjoberg.maze.Maze;
import anderssjoberg.maze.search.Search;
import anderssjoberg.maze.search.DepthFirstSearch;
import anderssjoberg.maze.search.BreadthFirstSearch;
import anderssjoberg.maze.generation.FileHandler;

public class Main{
    private MazeGenerator mazeGenerator;
    public static void main(String[] args){
        Main program = new Main();
    }

    public Main(){
        int size = 32;
        int num = 223;

        mazeGenerator = new MazeGenerator();
        Maze maze = new Maze("sizeX" + size + "sizeY" + size + "/sizeX" + size + "sizeY" + size + "num" + num + ".maze");
        printMaze(maze);
        Search search = new BreadthFirstSearch(maze);
        if(search.search()){
            System.out.println("Done");
            ArrayList<Node> history = search.getHistory();
            for(int i = 0; i < history.size(); ++i){
                System.out.println(history.get(i).getX() + " " + history.get(i).getY());
            }
        }


        /*ArrayList<String> out = new ArrayList<String>();

        for(int i = 0; i < 1000; ++i){
            Maze maze = new Maze("sizeX128sizeY128/sizeX128sizeY128num" + i + ".maze");

            Search search = new DepthFirstSearch(maze);

            if(search.search()){
                printMaze(maze);
                long timeElapsed = search.getTime();
                ArrayList<Node> history = search.getHistory();
                System.out.println("Maze(" + i + ") - " + i + " Time: " + timeElapsed + " Moves: " + history.size());
                out.add(i + ";" + timeElapsed + ";" + history.size());
            }
        }

        FileHandler.save("results/DFS128.csv", out);*/



    }


    private void generateMazes(int sizeX, int sizeY, int count){
        for(int i = 0; i < count; ++i){
            System.out.println(i);
            Maze maze = mazeGenerator.generateMaze(sizeX, sizeY);
            printMaze(maze);
            saveMaze(maze, "mazes/sizeX" + sizeX +
                "sizeY" + sizeY +
                "/sizeX" + sizeX +
                "sizeY" + sizeY +
                "num" + i + ".maze");
        }
    }

    /**
     * Saves the maze in a .maze file
     * @param maze Maze to be saved
     * @param filename Filename
     */
    private void saveMaze(Maze maze, String fileName){
        ArrayList<String> content = new ArrayList<String>();
        String row = "";

        Node[] rawData = maze.getRawData();
        for(int i = 0; i < maze.getSizeY(); ++i){
            row = "";
            for(int j = 0; j < maze.getSizeX(); ++j){
                if(maze.getStart().equals(rawData[i * maze.getSizeX() + j])){
                    row += "S";
                } else if(maze.getGoal().equals(rawData[i * maze.getSizeX() + j])){
                    row += "G";
                } else if(rawData[i * maze.getSizeX() + j].isBlocked()){
                    row += "#";
                } else {
                    row += " ";
                }
            }
            content.add(row);
        }

        FileHandler.save(fileName, content);

    }

    /**
     * Prints out the maze on screen
     * @param maze The maze to be printed
     */
    private void printMaze(Maze maze){
        String row = "";
        //Top border
        for(int i = 0; i < maze.getSizeX() + 2; ++i){
            row += "#";
        }
        System.out.println(row);
        //Print out maze row by row
        Node[] rawData = maze.getRawData();
        for(int i = 0; i < maze.getSizeY(); ++i){
            row = "#";
            for(int j = 0; j < maze.getSizeX(); ++j){
                if(maze.getStart().equals(rawData[i * maze.getSizeX() + j])){
                    row += "S";
                } else if(maze.getGoal().equals(rawData[i * maze.getSizeX() + j])){
                    row += "G";
                } else if(rawData[i * maze.getSizeX() + j].isBlocked()){
                    row += "#";
                } else {
                    row += " ";
                }
            }
            row += "#";
            System.out.println(row);
        }
        //Bottom border
        row = "";
        for(int i = 0; i < maze.getSizeX() + 2; ++i){
            row += "#";
        }
        System.out.println(row);

    }
}
