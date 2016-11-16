package anderssjoberg.maze.search;

import java.util.LinkedList;
import java.util.ArrayList;

import anderssjoberg.maze.Maze;
import anderssjoberg.maze.Node;

/**
 * Depth first search
 */
public class BreadthFirstSearch implements Search{
    private Maze maze;
    private long timeElapsed;
    private ArrayList<Node> history;

    /**
     * Class constructor, saves the maze
     */
    public BreadthFirstSearch(Maze maze){
        this.maze = maze;
        timeElapsed = 0;

        history = new ArrayList<Node>();
    }

    /**
     * Starts the DFS-search
     * @return Returns true if goal was reached, otherwise false
     */
    @Override
    public boolean search(){
        //Set up stack for searching
        LinkedList<Node> queue = new LinkedList<Node>();
        Node start = maze.getStart();
        Node goal = maze.getGoal();
        queue.add(start);



        //Start searching queue
        long timeStart = System.nanoTime();
        int level = 1;
        while(!queue.isEmpty()){
            Node node = queue.remove();
            history.add(node);

            if(!node.isVisited() && !node.isBlocked()){
                //Check if goal has been reached
                if(node.equals(goal)){
                    timeElapsed = System.nanoTime() - timeStart;
                    return true;
                }

                //Mark node as visited
                node.setVisited(true);
                //Add neighbours to stack
                ArrayList<Node> neighbours = maze.getNeighbours(node);
                for(int i = 0; i < neighbours.size(); ++i){
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
}
