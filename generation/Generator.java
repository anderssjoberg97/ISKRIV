package anderssjoberg.maze.generation;

import anderssjoberg.maze.Maze;
/**
 * Interface for maze generation alogorithms
 * @author Anders Sjöberg
 */
public interface Generator{
    Maze generate(int sizeX, int sizeY);
}
