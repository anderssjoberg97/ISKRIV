package anderssjoberg.maze;

import anderssjoberg.maze.Node;

public class TreeNode{
    Node node;
    TreeNode parentNode;

    public TreeNode(Node node){
        this.node = node;
        this.parentNode = null;
    }

    public TreeNode(Node node, TreeNode parentNode){
        this.node = node;
        this.parentNode = parentNode;
    }

    public TreeNode getParent(){
        return parentNode;
    }

    public Node getNode(){
        return node;
    }
}
