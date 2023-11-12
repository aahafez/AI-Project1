package code;
import java.util.Queue;

public abstract class GenericSearch {
    Queue<Node> queue;
    protected Node makeNode(Node parent, String action, int cost, int delay, String deliveryType, int prosperity, int food, int energy, int materials, int moneySpent,
    int h1, int h2){
        return new Node(parent, action, cost, delay, deliveryType, prosperity, food, energy, materials, moneySpent, h1, h2);
    }
    
    protected boolean goalTest(Node node){
        return node.getState().getProsperity() >= 100;
    }

    protected abstract Queue<Node> makeQ(Node node, String strategy);

    

   // protected abstract <T> T genericSearch(Problem problem, Queue<Node> q);

   /*  public static class Operator {
        // Define operator properties here
    } */
}

