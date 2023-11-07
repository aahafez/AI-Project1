package code;
import java.util.ArrayList;
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
    static String monetaryCost;
    static String nodesExpanded;
    static int delay;
    static String deliveryType;

    public static String solve(String initialState, String strategy, boolean visualize){
        initializeVariables(initialState);
        // INSERT LOGIC HERE
        plan = arrayListToString(planArr, ",");
        String result = plan + ";" + monetaryCost + ";" + nodesExpanded;
        return result;
    }

    static void requestFood(){
        decrementResources();
        money -= unitPriceFood * amountRequestFood;
        delay = delayRequestFood;
        deliveryType = "FOOD";
        planArr.add("RequestFood");
    }

    static void requestMaterials(){
        decrementResources();
        money -= unitPriceMaterials * amountRequestMaterials;
        delay = delayRequestMaterials;
        deliveryType = "MATERIALS";
        planArr.add("RequestMaterials");
    }

    static void requestEnergy(){
        decrementResources();
        money -= unitPriceEnergy * amountRequestEnergy;
        delay = delayRequestEnergy;
        deliveryType = "ENERGY";
        planArr.add("RequestEnergy");
    }

    static void WAIT(){
        decrementResources();
        planArr.add("WAIT");
    }
    
    static void build1(){    // only actions that affect prosperity level
        planArr.add("BUILD1");
    }

    static void build2(){
        planArr.add("BUILD2");
    }

    static void decrementResources(){
        food--; materials--; energy--;
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
