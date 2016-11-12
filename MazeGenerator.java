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
        ArrayList<Node> startCandidates = new ArrayList<>(),
            goalCandidates = new ArrayList<>();

        //Randomly select which quadrant to start in
        //Goalquadrant is on the opposite side
        int startQuadrant = randomGenerator.nextInt(4);
        int goalQuadrant;
        if(startQuadrant % 2 == 0){
            goalQuadrant = (startQuadrant + 3) % 4;
        } else {
            goalQuadrant = (startQuadrant + 1) % 4;
        }

        //Add possible start and goal Nodes to their respective lists
        for(int i = 0;
            i < mazeArray.length;
            ++i){

            if(i % sizeX / (sizeX / 2)  == startQuadrant % 2 &&
                i / sizeX / (sizeY / 2) == startQuadrant / 2){
                if(!mazeArray[i].isBlocked()){
                    startCandidates.add(mazeArray[i]);
                }
            } else if(i % sizeX / (sizeX / 2)  == goalQuadrant % 2 &&
                i / sizeX / (sizeY / 2) == goalQuadrant / 2){
                if(!mazeArray[i].isBlocked()){
                    goalCandidates.add(mazeArray[i]);
                }
            }
        }
        //Select start and goal point randomly until
        //the distance inbetween them is great enough

        Node start, goal;
        do {
            start = startCandidates.get(
                randomGenerator.nextInt(startCandidates.size()));
            goal = goalCandidates.get(
                randomGenerator.nextInt(goalCandidates.size()));
        } while(start.distanceTo(goal) < distance);

        //System.out.println("Start - X: " + start.getX() + " Y: " + start.getY());
        //System.out.println("Goal - X: " + goal.getX() + " Y: " + goal.getY());


        //Create a Maze object
        Maze maze = new Maze(mazeArray, sizeX, sizeY, start, goal);


        //Check if solveable
        Search check = new BreadthFirstSearch(maze);
        if(!check.search()){
            return null;
        }

        return maze;
    }
}
