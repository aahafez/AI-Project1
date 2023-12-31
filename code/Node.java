package code;

import java.util.Objects;

public class Node {
    private State state;
    private Node parent;
    private int cost;
    private String deliveryType;
    private int delay;
    private String action;
    private int hCost = 0;

    public Node(Node parent, String action, int cost, int delay, String deliveryType, int prosperity, int food, int energy, int materials, int moneySpent) {
        this.parent = parent;
        this.cost = cost;
        this.delay = delay;
        this.deliveryType = deliveryType;
        this.action = action;
        this.state = new State(prosperity, food, energy, materials, moneySpent);
    }

    public State getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

    public int gethCost() {
        return hCost;
    }

    public int getsCost(){
        return hCost + getCumCost();
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getCumCost(){
        // if (parent == null){ //stopping condition
        //     return cost;
        // } else {
        //     return parent.getCost() + cost;
        // }
        return state.getMoneySpent();
    }

    public Node getParent() {
        return parent;
    }

    public int getDelay() {
        return delay;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getAction() {
        return action;
    }

    public void addEnergy(int energy) {
        this.state.setEnergy(this.state.getEnergy() + energy);
    }

    public void addFood(int food) {
        this.state.setEnergy(this.state.getFood() + food);
    }
    
    public void addMaterials(int materials) {
        this.state.setEnergy(this.state.getMaterials() + materials);
    }

    public String getPath(){
        if (parent == null){ //stopping condition
            return action;
        } else {
            return parent.getPath() + "," + action;
        }
    }

    public String getCumStates(){
        if (parent == null){
            return state.toString();
        } else {
            return parent.getCumStates() + " --" + action + "--> " + state.toString();
        }
    }

    public boolean isGoal(){
        return state.getProsperity() >= 100;
    }

    public String toString(){
            return "prosperity=" + state.getProsperity() + ", food=" + state.getFood() +
             ", materials=" + state.getMaterials() + ", energy=" + state.getEnergy() + ", money_spent=" + state.getMoneySpent();
    }

    public static class State {

        private int prosperity;
        private int moneySpent;
        private int food;
        private int energy;
        private int materials;

        public State(int prosperity, int food, int energy, int materials, int moneySpent) {
            this.prosperity = prosperity;
            this.food = food;
            this.energy = energy;
            this.materials = materials;
            this.moneySpent = moneySpent;
        }

        // getters
        public int getProsperity() {
            return prosperity;
        }
        public int getMoneySpent() {
            return moneySpent;
        }
        public int getEnergy() {
            return energy;
        }
        public int getFood() {
            return food;
        }
        public int getMaterials() {
            return materials;
        }
        public void setFood(int food) {
            this.food = food;
        }
        public void setEnergy(int energy) {
            this.energy = energy;
        }
        public void setMaterials(int materials) {
            this.materials = materials;
        }
        public String toString(){
            return "prosperity=" + this.prosperity + ", food=" + this.food +
             ", materials=" + this.materials + ", energy=" + this.energy + ", money_spent=" + this.moneySpent;
        }
    }
}

