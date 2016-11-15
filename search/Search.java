package anderssjoberg.maze.search;

import java.util.ArrayList;

import anderssjoberg.maze.Node;

/**
 * A abstract class for search algorithms
 */
public interface Search{
    /**
     * Start searching
     * @return True if goal was reached otherwise false
     */
    public boolean search();

    /**
     * Gets the time elapsed searching
     * @return Time elapsed in nanoseconds
     */
    public long getTime();

    public ArrayList<Node> getHistory();

}
