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
        mazeGenerator = new MazeGenerator();
        //generateMazes(256, 256, 1000);
        Maze maze = new Maze("sizeX128sizeY128/sizeX128sizeY128num223.maze");

        Search search = new DepthFirstSearch(maze);

        if(search.search()){
            printMaze(maze);
            long timeElapsed = System.out.println(search.getTime());
            ArrayList<Node> history = search.getHistory();
        }

    }


    private void generateMazes(int sizeX, int sizeY, int count){
        for(int i = 0; i < count; ++i){
            System.out.println(i);
            Maze maze = mazeGenerator.generateMaze(sizeX, sizeY);
            printMaze(maze);
            saveMaze(maze, "sizeX" + sizeX +
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
