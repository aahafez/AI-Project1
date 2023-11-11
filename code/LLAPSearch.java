package code;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.HashSet;
public class LLAPSearch extends GenericSearch {
    static int prosperity,
    food, materials, energy,
    unitPriceFood, unitPriceMaterials, unitPriceEnergy,
    amountRequestFood, delayRequestFood,
    amountRequestMaterials, delayRequestMaterials,
    amountRequestEnergy, delayRequestEnergy,
    priceBUILD1, foodUseBUILD1, materialsUseBUILD1, energyUseBUILD1, prosperityBUILD1,
    priceBUILD2, foodUseBUILD2, materialsUseBUILD2, energyUseBUILD2, prosperityBUILD2;

    static int money;
    static ArrayList<String> planArr;
    static String plan;
    static int monetaryCost;
    static String nodesExpanded;
    static int delay;
 //   static String deliveryType;
    static int cost;
    static int currentCost;
    static int currentProsperity;
    static int currentFood;
    static int currentEnergy;
    static int currentMaterials;        
    static int moneySpent;
    static int currentDelay;
    static String currentDeliveryType;
    static int expandedNodes;
    static Set<Node> visitedNodes;

    public static String solve(String initialState, String strategy, boolean visualize){
        Node root = initializeVariables(initialState);

        // INSERT LOGIC HERE
        String result;
        switch (strategy) {
            case "BF":
                result = BF();
                break;
            case "DF":
                result = DF();
                break;
            case "UC":
                result = UC(root);
                break;
            case "ID":
                result = ID();
                break;
            case "GR1":
                result = GR1();
                break;
            case "GR2":
                result = GR2();
                break;
            case "AS1":
                result = AS1();
                break;
            case "AS2":
                result = AS2();
                break;
            default:
                result = "no result";
                break;
        }
        System.out.println(result);
        return result;
    }

    @Override
    protected Queue<Node> makeQ(Node node, String strategy){
        Queue<Node> list;
        switch (strategy) {
            case "BF":
                //queue
                list = new LinkedList<>();
                break;
            case "DF":
                //stack
                //list = new Stack<Node>();
                list = new LinkedList<>();
                break;
            case "ID":
                //stack
                list = new LinkedList<>();
                break;
            default:
                //prio
                list = new PriorityQueue<>();
                break;
        }
        return list;
    }

    public static Queue<Node> expand(Node node){
        Queue<Node> expansionList = new LinkedList<>();
        // which children to make
        if (node.getDelay() == 0){
            expansionList.add(requestFood(node));
            expansionList.add(requestEnergy(node));
            expansionList.add(requestMaterials(node));
        }
        expansionList.add(WAIT(node));
        expansionList.add(build1(node));
        expansionList.add(build2(node));
        return expansionList;
    }

    public static int getCost(String action) {
        switch (action.toLowerCase()) {
            case "requestfood":
            case "requestmaterials":
            case "requestenergy":
            case "wait":
                cost = unitPriceEnergy + unitPriceFood + unitPriceMaterials;
                break;
            case "build1":
                cost = priceBUILD1 + unitPriceEnergy * energyUseBUILD1 + unitPriceFood * foodUseBUILD1 + unitPriceMaterials * materialsUseBUILD1;
                break;
            case "build2":
                cost = priceBUILD2 + unitPriceEnergy * energyUseBUILD2 + unitPriceFood * foodUseBUILD2 + unitPriceMaterials * materialsUseBUILD2;
                break;
            default:
                cost = 0;
                break;
        }
        return cost;
    }

    static String BF(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static String DF(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static String UC(Node root){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
        priorityQueue.add(root);
        while(!priorityQueue.isEmpty()){
            Node currentNode = priorityQueue.poll();

            if (visitedNodes.contains(currentNode)) {
                continue; // Skip the node if already visited
            }

            visitedNodes.add(currentNode);
            System.out.println(currentNode.getState());
            if (currentNode.isGoal()) {
                System.out.println("Goal found with cost: " + currentNode.getCost());
                System.out.println("Path to goal: " + currentNode.getPath());
                break;
            }

            for (Node child : expand(currentNode)) {
                priorityQueue.add(child);
            }
        }
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + expandedNodes;
        return result;
    }

    static String ID(){
        // slide 65 in lecture 2
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static String GR1(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static String GR2(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static String AS1(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static String AS2(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static Node createChild(Node parent, String action, int prosperity, int food, int energy, int materials){  // WAIT + build1 + build2
        planArr.add(action);
        currentCost = getCost(action);
        currentProsperity = parent.getState().getProsperity() + prosperity;
        if (currentProsperity > 100) currentProsperity = 100;
        currentFood = parent.getState().getFood() - food;
        currentEnergy = parent.getState().getEnergy() - energy;
        currentMaterials = parent.getState().getMaterials() - materials;
        moneySpent =  parent.getState().getMoneySpent() + currentCost;
        currentDeliveryType = parent.getDeliveryType();
        currentDelay = parent.getDelay() - 1;
        if (currentDelay <= 0){
            currentDelay = 0;
            switch (parent.getDeliveryType()) {
                case "food":
                    currentFood += amountRequestFood;
                    if (currentFood > 50) currentFood = 50;
                    currentDeliveryType = null;
                    break;
                case "energy":
                    currentEnergy += amountRequestEnergy;
                    if (currentEnergy > 50) currentEnergy = 50;
                    currentDeliveryType = null;
                    break;
                case "materials":
                    currentMaterials += amountRequestMaterials;
                    if (currentMaterials > 50) currentMaterials = 50;
                    currentDeliveryType = null;
                    break;
                default:
                    break;
            }
        }
        Node child = new Node(parent, action, currentCost, currentDelay, currentDeliveryType, currentProsperity, currentFood, currentEnergy, currentMaterials, moneySpent);
        expandedNodes++;
        return child;
    }
    static Node createChild(Node parent, String action, String deliveryType, int initialDelay){  // request
        planArr.add(action);
        currentCost = getCost(action);
        currentProsperity = parent.getState().getProsperity();
        currentFood = parent.getState().getFood();
        currentEnergy = parent.getState().getEnergy();
        currentMaterials = parent.getState().getMaterials();
        moneySpent =  parent.getState().getMoneySpent() + currentCost;
        Node child = new Node(parent, action, currentCost, initialDelay, deliveryType, currentProsperity, currentFood, currentEnergy, currentMaterials, moneySpent);
        expandedNodes++;
        return child;
    }

    
    static Node requestFood(Node parent){
        decrementResources();

        return createChild(parent, "requestFood", "food", delayRequestFood);
    }

    static Node requestMaterials(Node parent){
        decrementResources();
        return createChild(parent, "requestMaterials", "materials", delayRequestMaterials);
    }

    static Node requestEnergy(Node parent){
        decrementResources();
        return createChild(parent, "requestEnergy", "energy", delayRequestEnergy);
    }

    static Node WAIT(Node parent){
        decrementResources();
        if (delay > 0) delay--;
        return createChild(parent, "WAIT", 0,0,0,0);
    }
    
    static Node build1(Node parent){    // only actions that affect prosperity level
        if (delay > 0) delay--;
        cost = unitPriceEnergy * amountRequestEnergy + unitPriceFood * amountRequestFood + unitPriceMaterials * amountRequestMaterials;
        monetaryCost += cost;
        money -= cost;
        return createChild(parent, "build1", prosperityBUILD1, foodUseBUILD1, energyUseBUILD1, materialsUseBUILD1);
    }

    static Node build2(Node parent){
        if (delay > 0) delay--;
        return createChild(parent, "build2", prosperityBUILD2, foodUseBUILD2, energyUseBUILD2, materialsUseBUILD2);
    }

    static void decrementResources(){
        food--; materials--; energy--;
        cost = unitPriceEnergy + unitPriceFood + unitPriceMaterials;
        monetaryCost += cost;
        money -= cost;
    }

    public static String arrayListToString(ArrayList<String> list, String delimiter) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));

            if (i < list.size() - 1) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    static Node initializeVariables(String initialState){
        money = 100000;
        planArr = new ArrayList<String>();
        monetaryCost = 0;
        nodesExpanded = "";
        delay = 0;
        String[] values = initialState.split(";|,");
        int index = 0;
        visitedNodes = new HashSet<>();


        prosperity = Integer.parseInt(values[index++]);

        food = Integer.parseInt(values[index++]);
        materials = Integer.parseInt(values[index++]);
        energy = Integer.parseInt(values[index++]);

        unitPriceFood = Integer.parseInt(values[index++]);
        unitPriceMaterials = Integer.parseInt(values[index++]);
        unitPriceEnergy = Integer.parseInt(values[index++]);

        amountRequestFood = Integer.parseInt(values[index++]);
        delayRequestFood = Integer.parseInt(values[index++]);

        amountRequestMaterials = Integer.parseInt(values[index++]);
        delayRequestMaterials = Integer.parseInt(values[index++]);

        amountRequestEnergy = Integer.parseInt(values[index++]);
        delayRequestEnergy = Integer.parseInt(values[index++]);

        priceBUILD1 = Integer.parseInt(values[index++]);
        foodUseBUILD1 = Integer.parseInt(values[index++]);
        materialsUseBUILD1 = Integer.parseInt(values[index++]);
        energyUseBUILD1 = Integer.parseInt(values[index++]);
        prosperityBUILD1 = Integer.parseInt(values[index++]);

        priceBUILD2 = Integer.parseInt(values[index++]);
        foodUseBUILD2 = Integer.parseInt(values[index++]);
        materialsUseBUILD2 = Integer.parseInt(values[index++]);
        energyUseBUILD2 = Integer.parseInt(values[index++]);
        prosperityBUILD2 = Integer.parseInt(values[index++]);

        return new Node(null, "", 0, 0, "", prosperity, food, energy, materials, 0);

    }
    
    static void printVariables(){
        System.out.println("initialProsperity: " + prosperity);
        System.out.println("initialFood: " + food);
        System.out.println("initialMaterials: " + materials);
        System.out.println("initialEnergy: " + energy);
        System.out.println("unitPriceFood: " + unitPriceFood);
        System.out.println("unitPriceMaterials: " + unitPriceMaterials);
        System.out.println("unitPriceEnergy: " + unitPriceEnergy);
        System.out.println("amountRequestFood: " + amountRequestFood);
        System.out.println("delayRequestFood: " + delayRequestFood);
        System.out.println("amountRequestMaterials: " + amountRequestMaterials);
        System.out.println("delayRequestMaterials: " + delayRequestMaterials);
        System.out.println("amountRequestEnergy: " + amountRequestEnergy);
        System.out.println("delayRequestEnergy: " + delayRequestEnergy);
        System.out.println("priceBUILD1: " + priceBUILD1);
        System.out.println("foodUseBUILD1: " + foodUseBUILD1);
        System.out.println("materialsUseBUILD1: " + materialsUseBUILD1);
        System.out.println("energyUseBUILD1: " + energyUseBUILD1);
        System.out.println("prosperityBUILD1: " + prosperityBUILD1);
        System.out.println("priceBUILD2: " + priceBUILD2);
        System.out.println("foodUseBUILD2: " + foodUseBUILD2);
        System.out.println("materialsUseBUILD2: " + materialsUseBUILD2);
        System.out.println("energyUseBUILD2: " + energyUseBUILD2);
        System.out.println("prosperityBUILD2: " + prosperityBUILD2);
    }
    
    public static void main(String[] args) {
        String initialState0 = "17;" +
                    "49,30,46;" +
                    "7,57,6;" +
                    "7,1;20,2;29,2;" +
                    "350,10,9,8,28;" +
                    "408,8,12,13,34;";
        solve(initialState0,"UC",false);
        printVariables();
    }
}

// class UCComparator implements Comparator<Node> {
//     public int compare(Node str1, Node str2)
//     {
//         //return second_Str.compareTo(first_Str);
//     }
// }

// class GRComparator implements Comparator<Node> {
//     public int compare(Node str1, Node str2)
//     {
//         //return second_Str.compareTo(first_Str);
//     }
// }

// class ASComparator implements Comparator<Node> {
//     public int compare(Node str1, Node str2)
//     {
//         //return second_Str.compareTo(first_Str);
//     }

