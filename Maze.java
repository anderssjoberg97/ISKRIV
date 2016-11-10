/**
 * Class which stores mazes
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
    public Node[] getNeighbours(Node node){
        //Set up array
        Node[] neighbours = new Node[4];
        int nextIndex = 0;

        //Add top neighbour to array
        if(node.getY() > 0){
            neighbours[nextIndex] = this.maze[
                node.getX() + sizeX * (node.getY() - 1)];
        }
        //Add right neighbour to array
        if(node.getX() < sizeX - 1){
            neighbours[nextIndex] = this.maze[
                node.getX() + 1 + sizeX * (node.getY())];
        }
        //Add bottom neighbour to array
        if(node.getY() < sizeY - 1){
            neighbours[nextIndex] = this.maze[
                node.getX() + sizeX * (node.getY() + 1)];
        }
        //Add left neighbour to array
        if(node.getX() > 0){
            neighbours[nextIndex] = this.maze[
                node.getX() - 1 + sizeX * (node.getY())];
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
