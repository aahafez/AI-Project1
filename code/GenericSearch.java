package code;
import java.util.Queue;

public abstract class GenericSearch {
    Queue<Node> queue;
    protected Node makeNode(Node parent, String action, int cost, int delay, String deliveryType, int prosperity, int food, int energy, int materials, int moneySpent){
        return new Node(parent, action, cost, delay, deliveryType, prosperity, food, energy, materials, moneySpent);
    }
    
    protected boolean goalTest(Node node){
        return node.getState().getProsperity() >= 100;
    }

    protected abstract Queue<Node> makeQ(Node node, String strategy);

    protected abstract Queue<Node> expand(Node node, String[] operators);

   // protected abstract <T> T genericSearch(Problem problem, Queue<Node> q);

   /*  public static class Operator {
        // Define operator properties here
    } */
}

