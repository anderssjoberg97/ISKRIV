package anderssjoberg.maze;

/**
 * Class which represents nodes
 * @author Anders Sjöberg
 */
public class Node{
    private final int x, y;
    private boolean visited;
    private final boolean blocked;

    /**
     * Class constructor
     * @param x X
     * @param y Y
     * @param blocked True if blocked otherwise false
     */
    public Node(int x, int y, boolean blocked){
        this.x = x;
        this.y = y;
        this.blocked = blocked;

        this.visited = false;
    }

    /**
     * Compares this Node with another and checks if they are equal
     * @param otherNode Node to compare with
     * @return True if x and y are the same node
     */
    public boolean equals(Node otherNode){
        if(this.x == otherNode.getX() && this.y == otherNode.getY()){

            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the distance to another node
     * @return The distance to other node
     */
    public double distanceTo(Node otherNode){
        return Math.sqrt(
        Math.pow(this.getX()-otherNode.getX(), 2) +
        Math.pow(this.getY()-otherNode.getY(), 2));
    }

    /**
     * Gets the node's x-value
     * @return The x-value
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the node's y-value
     * @return The y-value
     */
    public int getY(){
        return y;
    }

    /**
     * Checks whether node has been visited or not
     * @return True if visited otherwise false
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * Checks if node is blocked
     * @return True if blocked otherwise false
     */
    public boolean isBlocked(){
        return blocked;
    }

    /**
     * Sets the node as visited or not visited
     * @param visited True if visited otherwise false
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }
}
