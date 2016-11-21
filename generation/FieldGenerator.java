package anderssjoberg.maze.generation;

import java.util.ArrayList;
import java.util.Random;

import anderssjoberg.maze.Node;
import anderssjoberg.maze.Maze;
import anderssjoberg.maze.search.AStarSearch;

/**
 * Class which generates a field of chosen size with random blockades
 */
public class FieldGenerator implements Generator{
    Random randomGenerator;
    byte chance;

    public FieldGenerator(){
        chance = (byte)10;
        randomGenerator = new Random();
    }

    /**
     * Starts the generation
     * @return A Maze object
     */
    @Override
    public Maze generate(int sizeX, int sizeY){
        ArrayList<Node> candidates = new ArrayList<Node>();

        //Generate the blocks
        Node[] maze = new Node[sizeX * sizeY];
        for(int i = 0; i < sizeX; ++i){
            for(int j = 0; j < sizeY; ++j){
                //Create a node
                boolean blocked = randomGenerator.nextInt(100) < chance;
                maze[j * sizeX + i] = new Node(i, j, blocked);

                //If a node is not blocked, make it a potential
                //candidate for starting and goal points
                if(!blocked){
                    candidates.add(maze[j * sizeX + i]);
                }
            }
        }
        Node start, goal;
        //Set start and goal
        do{
            start = candidates.get(randomGenerator.nextInt(candidates.size()));
            goal = candidates.get(randomGenerator.nextInt(candidates.size()));
        } while(getDistance(start, goal) < sizeX / 2);

        //Check if solvable
        Maze mazeObject = new Maze(maze, sizeX, sizeY, start, goal);
        AStarSearch search = new AStarSearch(mazeObject);
        //Return the maze if solvable otherwise create a new one
        if(search.search()){
            return mazeObject;
        } else {
            return generate(sizeX, sizeY);
        }
    }

    private double getDistance(Node node1, Node node2){
        return Math.sqrt(Math.pow(
                node1.getX() -
                node2.getX()
                , 2) +
            Math.pow(
                node1.getY() -
                node2.getY()
                , 2));
    }
}
