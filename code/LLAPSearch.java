package code;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Comparator;
import java.util.PriorityQueue;
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
    static String deliveryType;
    static int cost;

    public static String solve(String initialState, String strategy, boolean visualize){
        initializeVariables(initialState);
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
                result = UC();
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
        return result;
    }

    @Override
    protected Queue<Node> makeQ(Node node, String strategy){
        switch (strategy) {
            case "BF":
                //queue
                break;
            case "DF":
                //stack
                break;
            case "ID":
                //stack
                break;
            default:
                //prio
                break;
        }
    }

    @Override
    protected Queue<Node> expand(Node node, String[] operators){
        //same for all
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

    static String UC(){
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
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

    static void requestFood(){
        decrementResources();
        delay = delayRequestFood;
        deliveryType = "FOOD";
        planArr.add("RequestFood");
    }

    static void requestMaterials(){
        decrementResources();
        delay = delayRequestMaterials;
        deliveryType = "MATERIALS";
        planArr.add("RequestMaterials");
    }

    static void requestEnergy(){
        decrementResources();
        delay = delayRequestEnergy;
        deliveryType = "ENERGY";
        planArr.add("RequestEnergy");
    }

    static void WAIT(){
        decrementResources();
        if (delay > 0) delay--;
        planArr.add("WAIT");
    }
    
    static void build1(){    // only actions that affect prosperity level
        if (delay > 0) delay--;
        cost = unitPriceEnergy * amountRequestEnergy + unitPriceFood * amountRequestFood + unitPriceMaterials * amountRequestMaterials;
        monetaryCost += cost;
        money -= cost;
        planArr.add("BUILD1");
    }

    static void build2(){
        if (delay > 0) delay--;
        planArr.add("BUILD2");
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

    static void initializeVariables(String initialState){
        money = 100000;
        planArr = new ArrayList<String>();
        monetaryCost = "";
        nodesExpanded = "";
        String[] values = initialState.split(";|,");
        int index = 0;

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
        solve(initialState0,"ok",false);
        printVariables();
    }
}

class UCComparator implements Comparator<Node> {
    public int compare(Node str1, Node str2)
    {
        //return second_Str.compareTo(first_Str);
    }
}

class GRComparator implements Comparator<Node> {
    public int compare(Node str1, Node str2)
    {
        //return second_Str.compareTo(first_Str);
    }
}

class ASComparator implements Comparator<Node> {
    public int compare(Node str1, Node str2)
    {
        //return second_Str.compareTo(first_Str);
    }
}
