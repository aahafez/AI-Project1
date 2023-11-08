package code;
public abstract class Node {
    private State state;

    // root constructor
    public Node(int initialProsperity, int intialFood, int initialEnergy, int initialMaterials){
        state = new State(initialProsperity, intialFood, initialEnergy, initialMaterials, 0);
    }

    // child constructor
    public Node(int prosperity, int food, int energy, int materials, int moneySpent) {
        state = new State(prosperity, food, energy, materials, moneySpent);
    }

    public State getState() {
        return state;
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
        public String toString(){
            return "prosperity=" + this.prosperity + ", food=" + this.food +
             ", materials=" + this.materials + ", energy=" + this.energy + ", money_spent=" + this.moneySpent;
        }
    }
}

