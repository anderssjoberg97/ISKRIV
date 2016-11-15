package anderssjoberg.maze;

import java.util.ArrayList;
import anderssjoberg.maze.generation.FileHandler;

/**
 * Class which stores mazes
 * @author Anders Sjöberg
 */
public class Maze{
    private Node start, goal;
    private int sizeX, sizeY;
    private Node[] maze;

    public Maze(Node[] maze, int sizeX, int sizeY, Node start, Node goal){
        this.start = start;
        this.goal = goal;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.maze = maze;
    }

    public Maze(String fileName){
        ArrayList<String> mazeContent = FileHandler.loadMazeContent(fileName);

        int sizeX = mazeContent.get(0).length();
        int sizeY = mazeContent.size();
        Node start = new Node(0, 0, false), goal = new Node(0, 0, false);
        Node[] maze = new Node[sizeX * sizeY];

        for(int i = 0; i < sizeX; ++i){
            for(int j = 0; j < sizeY; ++j){
                char state = mazeContent.get(j).charAt(i);
                if(state == 'S'){
                    start = new Node(i, j, false);
                    maze[j * sizeX + i] = start;
                } else if(state == 'G'){
                    goal = new Node(i, j, false);
                    maze[j * sizeX + i] = goal;
                } else if(state == '#'){
                    maze[j * sizeX + i] = new Node(i, j, true);
                } else {
                    maze[j * sizeX + i] = new Node(i, j, false);
                }
            }
        }
        this.start = start;
        this.goal = goal;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.maze = maze;
    }

    /**
     * Gets the start Node
     * @return Starting Node
     */
    public Node getStart(){
        return start;
    }

    /**
     * Gets the goal Node
     * @return Goal Node
     */
    public Node getGoal(){
        return goal;
    }

    /**
     * Gets a nodes neighbours
     * @return Neighbouring nodes as an array
     */
    public ArrayList<Node> getNeighbours(Node node){
        //Set up array
        ArrayList<Node> neighbours = new ArrayList<Node>();
        //Add top neighbour to array
        if(node.getY() > 0){
            Node neighbourNode = this.maze[
                node.getX() + sizeX * (node.getY() - 1)];
            if(!neighbourNode.isBlocked() && !neighbourNode.isVisited()){
                neighbours.add(neighbourNode);
            }
        }
        //Add right neighbour to array
        if(node.getX() < sizeX - 1){
            Node neighbourNode = this.maze[
                node.getX() + 1 + sizeX * (node.getY())];
            if(!neighbourNode.isBlocked() && !neighbourNode.isVisited()){
                neighbours.add(neighbourNode);
            }
        }
        //Add bottom neighbour to array
        if(node.getY() < sizeY - 1){
            Node neighbourNode = this.maze[
                node.getX() + sizeX * (node.getY() + 1)];
            if(!neighbourNode.isBlocked() && !neighbourNode.isVisited()){
                neighbours.add(neighbourNode);
            }
        }
        //Add left neighbour to array
        if(node.getX() > 0){
            Node neighbourNode = this.maze[
                node.getX() - 1 + sizeX * (node.getY())];
            if(!neighbourNode.isBlocked() && !neighbourNode.isVisited()){
                neighbours.add(neighbourNode);
            }
        }

        return neighbours;
    }

    /**
     * Gets the width
     * @return The width of the maze
     */
    public int getSizeX(){
        return sizeX;
    }

    /**
     * Gets the height
     * @return The height of the maze
     */
    public int getSizeY(){
        return sizeY;
    }

    /**
     * Gets the node array data
     * @return Array of nodes
     */
    public Node[] getRawData(){
        return maze;
    }
}
