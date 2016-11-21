package anderssjoberg.maze.generation;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import anderssjoberg.maze.Node;

/**
 * File management
 */
public class FileHandler{
    public FileHandler(){

    }

    public static void save(String fileName, ArrayList<String> content){

        try{
            PrintWriter writer = new PrintWriter(
                "build/anderssjoberg/maze/" + fileName, "UTF-8");
            for(String row : content){
                writer.println(row);
            }
            writer.close();
        } catch (Exception e){
            System.out.println("Could not save file");
        }

    }

    public static ArrayList<String> loadMazeContent(String fileName){
        File file;
        ArrayList<String> mazeContent = new ArrayList<String>();
        try{
            file = new File("build/anderssjoberg/maze/" + fileName);
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while((line = br.readLine()) != null){
                    mazeContent.add(line);
                }
            } catch (Exception e) {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

        return mazeContent;

    }
}
