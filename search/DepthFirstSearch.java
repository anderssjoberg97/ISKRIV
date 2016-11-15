package anderssjoberg.maze.search;

import java.util.Stack;
import java.util.ArrayList;


import anderssjoberg.maze.Maze;
import anderssjoberg.maze.Node;

/**
 * Depth first search
 */
public class DepthFirstSearch implements Search{
    private Maze maze;
    private long timeElapsed;
    private ArrayList<Node> history;

    /**
     * Class constructor, saves the maze
     */
    public DepthFirstSearch(Maze maze){
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
        Stack<Node> stack = new Stack<Node>();
        Node start = maze.getStart();
        Node goal = maze.getGoal();
        stack.push(start);



        //Start searching stack
        long timeStart = System.nanoTime();
        while(!stack.empty()){
            Node node = stack.pop();

            if(!node.isVisited() && !node.isBlocked()){
                history.add(node);
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
                        stack.push(neighbours.get(i));
                }
                if(neighbours.size() < 1){
                    //Remove this node from history
                    for(int i = history.size() - 1; i >= 0; --i){
                        if(maze.getNeighbours(history.get(i)).size() > 0){
                            break;
                        } else {
                                history.remove(history.get(i));
                        }

                    }
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
