package anderssjoberg.maze.search;

import java.util.Stack;

import anderssjoberg.maze.Maze;
import anderssjoberg.maze.Node;

/**
 * Depth first search
 */
public class DepthFirstSearch implements Search{
    private Maze maze;

    /**
     * Class constructor, saves the maze
     */
    public DepthFirstSearch(Maze maze){
        this.maze = maze;
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
        while(!stack.empty()){
            Node node = stack.pop();

            if(!node.isVisited() && !node.isBlocked()){
                //Check if goal has been reached
                if(node.equals(goal)){
                    return true;
                }

                //Mark node as visited
                node.setVisited(true);
                //Add neighbours to stack
                Node[] neighbours = maze.getNeighbours(node);
                for(int i = 0; i < neighbours.length; ++i){
                    if(neighbours[i] != null){
                        stack.push(neighbours[i]);
                    }
                }
            }

        }


        return false;
    }
}
