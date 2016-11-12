package anderssjoberg.maze.generation;

import anderssjoberg.maze.generation.Generator;
import anderssjoberg.maze.generation.DepthFirstGenerator;
import anderssjoberg.maze.Maze;

/**
 * Generates a 2D maze
 * @author Anders Sjöberg
 */
public class MazeGenerator{
    Generator generator;

    /**
     * Class constructor
     */
    public MazeGenerator(){
        generator = new DepthFirstGenerator();
    }

    /**
     * Generates a maze
     * @param sizeX Width of the maze
     * @param sizeY Height of the maze
     * @return A two-dimensional Maze object
     */
    public Maze generateMaze(int sizeX, int sizeY){
        return generator.generate(sizeX, sizeY);
    }
}
