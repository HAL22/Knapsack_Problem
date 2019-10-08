package algorithm.aco.base;

import main.KnapsackItem;

import java.util.ArrayList;

public class Ant
{
    private AntColony antColony;
    private int size;
    private int weightOfsack;
    private int valueOfsack;
    private ArrayList<KnapsackItem> Knapsack;
    private ArrayList<KnapsackItem> Sack;
    private ArrayList<KnapsackItem> NotYetVisited;

    public Ant(ArrayList<KnapsackItem>Knapsack,int size,AntColony antColony)
    {
        this.Knapsack = Knapsack;
        this.size = size;
        this.antColony = antColony;

        this.weightOfsack =0;
        this.valueOfsack = 0;
        this.Sack =null;
        this.NotYetVisited = null;
    }

    public void newRound()
    {
        this.weightOfsack =0;
        this.valueOfsack = 0;

        this.Sack = new ArrayList<>();

        for(KnapsackItem k:Knapsack)
        {
            this.NotYetVisited.add(new KnapsackItem(k.getIndex(),k.getWeight(),k.getValue()));
        }

    }











    public AntColony getAntColony() {
        return antColony;
    }

    public void setAntColony(AntColony antColony) {
        this.antColony = antColony;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeightOfsack() {
        return weightOfsack;
    }

    public void setWeightOfsack(int weightOfsack) {
        this.weightOfsack = weightOfsack;
    }

    public int getValueOfsack() {
        return valueOfsack;
    }

    public void setValueOfsack(int valueOfsack) {
        this.valueOfsack = valueOfsack;
    }

    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }

    public ArrayList<KnapsackItem> getSack() {
        return Sack;
    }

    public void setSack(ArrayList<KnapsackItem> sack) {
        Sack = sack;
    }

    public ArrayList<KnapsackItem> getNotYetVisited() {
        return NotYetVisited;
    }

    public void setNotYetVisited(ArrayList<KnapsackItem> notYetVisited) {
        NotYetVisited = notYetVisited;
    }
}
