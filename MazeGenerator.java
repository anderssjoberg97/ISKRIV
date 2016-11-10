import java.util.Random;
import java.util.ArrayList;

/**
 * Generates a 2D maze
 * @author Anders Sjöberg
 */
public class MazeGenerator{
    Random randomGenerator;

    /**
     * Class constructor
     */
    public MazeGenerator(){
        randomGenerator = new Random();
    }

    /**
     * Generates a maze
     * @param sizeX Width of the maze
     * @param sizeY Height of the maze
     * @param chance Chance of a node being blocked 0-100
     * @return A two-dimensional array of booleans. 0 for empty, 1 for blocked
     */
    public Maze generateMaze(int sizeX, int sizeY, byte chance, double distance){
        //Create a empty mazeArray
        Node[] mazeArray = new Node[sizeX * sizeY];

        //Block nodes randomly
        for(int i = 0; i < mazeArray.length; ++i){
            mazeArray[i] = new Node(
                i % sizeX,
                i / sizeX,
                randomGenerator.nextInt(100) < chance);
        }

        //Values for starting point and goal
        ArrayList<Node> startCandidates = new ArrayList<>();

        //Add possible start and goal Nodes to a list
        for(int i = 0; i < mazeArray.length; ++i){
            if(!mazeArray[i].isBlocked()){
                candidates.add(mazeArray[i]);
                candidates.add(mazeArray[i]);
            }
        }
        //Select start and goal point randomly until
        //the distance inbetween them is great enough

        Node start, goal;
        do {
            start = candidates.get(
                randomGenerator.nextInt(candidates.size()));
            goal = candidates.get(
                randomGenerator.nextInt(candidates.size()));
        } while(start.distanceTo(goal) < distance);


        //Create a Maze object
        Maze maze = new Maze(mazeArray, sizeX, sizeY, start, goal);


        //Check if solveable
        Search dfsCheck = new DepthFirstSearch(maze);
        if(!dfsCheck.search()){
            return null;
        }

        return maze;
    }
}
