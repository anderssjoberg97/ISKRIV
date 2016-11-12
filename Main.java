public class Main{
    private MazeGenerator mazeGenerator;
    public static void main(String[] args){
        Main program = new Main();
    }

    public Main(){
        mazeGenerator = new MazeGenerator();
        generateMaze();
    }

    private void generateMaze(){
        int SIZE_X = 64;
        int SIZE_Y = 64;
        int DISTANCE = 10;
        byte CHANCE = 15;
        Maze maze;
        do {
            maze = mazeGenerator.generateMaze(SIZE_X, SIZE_Y, CHANCE, DISTANCE);
        } while(maze == null);
        printMaze(maze);
    }

    private void printMaze(Maze maze){
        String row = "";
        //Top border
        for(int i = 0; i < maze.getSizeX() + 2; ++i){
            row += "#";
        }
        System.out.println(row);
        //Print out maze row by row
        Node[] rawData = maze.getRawData();
        for(int i = 0; i < maze.getSizeY(); ++i){
            row = "#";
            for(int j = 0; j < maze.getSizeX(); ++j){
                if(maze.getStart().equals(rawData[i * maze.getSizeX() + j])){
                    row += "S";
                } else if(maze.getGoal().equals(rawData[i * maze.getSizeX() + j])){
                    row += "G";
                } else if(rawData[i * maze.getSizeX() + j].isBlocked()){
                    row += "#";
                } else {
                    row += " ";
                }
            }
            row += "#";
            System.out.println(row);
        }
        //Bottom border
        row = "";
        for(int i = 0; i < maze.getSizeX() + 2; ++i){
            row += "#";
        }
        System.out.println(row);

    }
}
