package anderssjoberg.maze.generation;

import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;

import anderssjoberg.maze.Maze;
import anderssjoberg.maze.Node;
import anderssjoberg.maze.generation.GenNode;

/**
 * Class for generating mazes using depth first search
 */
public class DepthFirstGenerator implements Generator{

    Random randomGenerator;

    /**
     * Class constructor
     */
    public DepthFirstGenerator(){
        randomGenerator = new Random();
    }

    /**
     * Starts the DFS-generation
     * @return A Maze object
     */
    @Override
    public Maze generate(int sizeX, int sizeY){
        //Set up an array of genNodes
        //which will later be converted to a Maze object
        GenNode[] maze = new GenNode[sizeX * sizeY];
        for(int i = 0; i < sizeX; ++i){
            for(int j = 0; j < sizeY; ++j){
                maze[i + j * sizeX] = new GenNode(i, j);
            }
        }


        //Set up stack for searching
        Stack<GenNode> stack = new Stack<GenNode>();
        //Set up a history
        ArrayList<GenNode> history = new ArrayList<GenNode>();
        //Set a radnom starting point
        GenNode start = maze[randomGenerator.nextInt(maze.length)];
        //ArrayList which stores potential goals
        ArrayList<GenNode> goalCandidates = new ArrayList<GenNode>();
        //Add starting point to stack
        stack.push(start);


        //Start generating maze
        while(!stack.empty()){
            //Get first GenNode in stack
            GenNode node = stack.pop();
            //Store node in history
            history.add(node);
            //Mark node as visited
            node.setVisited(true);
            //Unblock node
            node.setBlocked(false);

            //Add a random valid neighbour to stack
            //Also unblock the node inbetween
            ArrayList<GenNode[]> neighbours = getNeighbours(maze, node, sizeX, sizeY);
            if(neighbours.size() > 0){
                int which = randomGenerator.nextInt(neighbours.size());
                neighbours.get(which)[0].setVisited(true);
                neighbours.get(which)[0].setBlocked(false);
                stack.push(neighbours.get(which)[1]);
            } else {
                goalCandidates.add(node);
                //Step back until a node which has free neighbours is reached
                for(int i = history.size() - 1; i >= 0; --i){
                    if(getNeighbours(
                        maze, history.get(i), sizeX, sizeY).size() > 0){
                        stack.push(history.get(i));
                        history.subList(i, history.size()).clear();
                    } else if(history.get(i).equals(start)){
                        break;
                    }
                }
            }
        }

        //Randomly select a goal
        GenNode goal = goalCandidates.get(
            randomGenerator.nextInt(goalCandidates.size()));

        //Convert the GenNode array to a Maze
        return convertToMaze(maze, sizeX, sizeY, start, goal);
    }

    /**
     * Converts a GenNode array to a maze
     * @param maze GenNode array
     * @param sizeX Maze size x
     * @param sizeY Maze size y
     * @param start The starting Node
     * @param goal The goal node
     * @return A maze
     */
    private Maze convertToMaze(GenNode[] maze,
        int sizeX, int sizeY, GenNode start, GenNode goal){
        Node[] tempMaze = new Node[maze.length];
        for(int i = 0; i < maze.length; ++i){
            tempMaze[i] = new Node(
                maze[i].getX(), maze[i].getY(), maze[i].isBlocked());
        }
        Node startNode = new Node(start.getX(), start.getY(), false);
        Node goalNode = new Node(goal.getX(), goal.getY(), false);
        return new Maze(tempMaze, sizeX, sizeY, startNode, goalNode);
    }

    /**
     * Gets a nodes neighbours that are valid
     * @param maze An array of GenNodes representing a maze
     * @param node The node
     * @param sizeX Maze size X
     * @param sizeY Maze size Y
     * @return Neighbours to the node
     */
    private ArrayList<GenNode[]> getNeighbours(GenNode[] maze, GenNode node, int sizeX, int sizeY){
        ArrayList<GenNode[]> neighbours = new ArrayList<GenNode[]>();
        int position = -1;
        for(int i = 0; i < maze.length; ++i){
            if(maze[i].equals(node)){
                position = i;
                break;
            }
        }

        if(position == -1){
            return neighbours;
        }

        //Check top neighbour
        if((position - sizeX * 2)  / sizeX > 0 &&
            !maze[position - sizeX * 2].isVisited() &&
            !maze[position - sizeX].isVisited()){
            GenNode[] freeNodes = new GenNode[2];
            freeNodes[0] = maze[position - sizeX];
            freeNodes[1] = maze[position - sizeX * 2];
            neighbours.add(freeNodes);
        }
        //Check bottom neighbour
        if((position + sizeX * 2)  / sizeX <= sizeY - 1  &&
            !maze[position + sizeX * 2].isVisited() &&
            !maze[position + sizeX].isVisited()){
            GenNode[] freeNodes = new GenNode[2];
            freeNodes[0] = maze[position + sizeX];
            freeNodes[1] = maze[position + sizeX * 2];
            neighbours.add(freeNodes);
        }
        //Check left neighbour
        if((position - 2)  / sizeX == position / sizeX &&
            !maze[position - 2].isVisited() &&
            !maze[position - 1].isVisited()){
            GenNode[] freeNodes = new GenNode[2];
            freeNodes[0] = maze[position - 1];
            freeNodes[1] = maze[position - 2];
            neighbours.add(freeNodes);
        }
        //Check right neighbour
        if((position + 2)  / sizeX == position / sizeX &&
            !maze[position + 2].isVisited() &&
            !maze[position + 1].isVisited()){
            GenNode[] freeNodes = new GenNode[2];
            freeNodes[0] = maze[position + 1];
            freeNodes[1] = maze[position + 2];
            neighbours.add(freeNodes);
        }

        return neighbours;
    }
}
