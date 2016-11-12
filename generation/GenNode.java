package anderssjoberg.maze.generation;

/**
 * Similar to Node class except the blocked field is not final
 * @author Anders Sjöberg
 */
 public class GenNode{
     private final int x, y;
     private boolean visited;
     private boolean blocked;

     /**
      * Class constructor
      * @param x X
      * @param y Y
      */
     public GenNode(int x, int y){
         this.x = x;
         this.y = y;
         this.blocked = true;

         this.visited = false;
     }

     /**
      * Compares this GenNode with another and checks if they are equal
      * @param otherNode GenNode to compare with
      * @return True if x and y are the same GenNode
      */
     public boolean equals(GenNode otherNode){
         if(this.x == otherNode.getX() && this.y == otherNode.getY()){

             return true;
         } else {
             return false;
         }
     }

     /**
      * Calculates the distance to another GenNode
      * @return The distance to other GenNode
      */
     public double distanceTo(GenNode otherNode){
         return Math.sqrt(
         Math.pow(this.getX()-otherNode.getX(), 2) +
         Math.pow(this.getY()-otherNode.getY(), 2));
     }

     /**
      * Gets the GenNode's x-value
      * @return The x-value
      */
     public int getX(){
         return x;
     }

     /**
      * Gets the GenNode's y-value
      * @return The y-value
      */
     public int getY(){
         return y;
     }

     /**
      * Checks whether GenNode has been visited or not
      * @return True if visited otherwise false
      */
     public boolean isVisited(){
         return visited;
     }

     /**
      * Checks if GenNode is blocked
      * @return True if blocked otherwise false
      */
     public boolean isBlocked(){
         return blocked;
     }

     /**
      * Sets the blocked value
      * @param blocked True for blocked otherwise false
      */
     public void setBlocked(boolean blocked){
         this.blocked = blocked;
     }


     /**
      * Sets the GenNode as visited or not visited
      * @param visited True if visited otherwise false
      */
     public void setVisited(boolean visited){
         this.visited = visited;
     }
 }
