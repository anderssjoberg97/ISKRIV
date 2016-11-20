package anderssjoberg.maze.search;

import java.util.LinkedList;
import java.util.ArrayList;

import anderssjoberg.maze.Maze;
import anderssjoberg.maze.Node;
import anderssjoberg.maze.TreeNode;

/**
 * A* search
 */
public class AStarSearch implements Search{
    private Maze maze;
    private long timeElapsed;
    private ArrayList<Node> history;

    /**
     * Class constructor, saves the maze
     */
    public AStarSearch(Maze maze){
        this.maze = maze;
        timeElapsed = 0;

        history = new ArrayList<Node>();
    }

    /**
     * Starts the A*-search
     * @return Returns true if goal was reached, otherwise false
     */
    @Override
    public boolean search(){
        //Set up stack for searching
        LinkedList<Node> queue = new LinkedList<Node>();
        Node start = maze.getStart();
        Node goal = maze.getGoal();
        queue.add(start);


        //Set up a tree structure
        ArrayList<TreeNode> tree = new ArrayList<TreeNode>();
        tree.add(new TreeNode(start));

        //Start searching queue
        long timeStart = System.nanoTime();


        while(!queue.isEmpty()){
            //Pop the element which has the shortest distance to goal
            Node node = queue.get(0);
            int shortestDistance = distanceToGoal(node);
            for(int i = 1; i < queue.size(); ++i){
                int tempDistance = distanceToGoal(queue.get(i));
                if(tempDistance < shortestDistance){
                    node = queue.get(i);
                    shortestDistance = tempDistance;
                }
            }
            queue.remove(node);

            //Find this node in tree
            TreeNode treeNode = null;
            for(TreeNode tempTreeNode:tree){
                if( tempTreeNode.getNode().getX() == node.getX() &&
                    tempTreeNode.getNode().getY() == node.getY()){
                    treeNode = tempTreeNode;
                    break;
                }
            }

            if(!node.isVisited() && !node.isBlocked()){
                //Check if goal has been reached
                if(node.equals(goal)){
                    timeElapsed = System.nanoTime() - timeStart;
                    //Reconstruct path
                    while(treeNode.getParent() != null){
                        history.add(0, treeNode.getNode());
                        treeNode = treeNode.getParent();
                    }
                    history.add(0, start);

                    return true;
                }

                //Mark node as visited
                node.setVisited(true);
                //Add neighbours to stack
                ArrayList<Node> neighbours = maze.getNeighbours(node);
                for(int i = 0; i < neighbours.size(); ++i){
                        tree.add(new TreeNode(neighbours.get(i), treeNode));
                        queue.add(neighbours.get(i));
                }
            }

        }


        return false;
    }

    /**
     * Gets the time elapsed searching
     * @return Time elapsed in nanoseconds
     */
    @Override
    public long getTime(){
        return timeElapsed;
    }

    /**
     * Gets path history
     * @return Path history
     */
    @Override
    public ArrayList<Node> getHistory(){
        return history;
    }

    /**
     * Calculates the distance to the goal
     * @param node The node to start from
     */
    public int distanceToGoal(Node node){
        return (int)Math.pow(
                node.getX() -
                maze.getGoal().getX()
                , 2) +
            (int)Math.pow(
                node.getY() -
                maze.getGoal().getY()
                , 2);
    }
}
