import java.util.Stack;

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
        long timeStart = System.nanoTime();
        while(!stack.empty()){
            //Check if search has taken too long
            if(System.nanoTime() - timeStart > 100_000){
                System.out.println("Too long");
            } else {
                //System.out.println("Time: " + (System.nanoTime() - timeStart));
            }
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
