package algorithm.aco.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class AntColony
{
    private ArrayList<KnapsackItem>Knapsack;
    private int KnapsackSize;
    private double[] pheromoneVector;
    private Ant[] ants;

    public AntColony(ArrayList<KnapsackItem>Knapsack)
    {
        this.Knapsack=Knapsack;
        this.KnapsackSize = Knapsack.size();

        pheromoneVector = new double[KnapsackSize];
        for(int i=0;i<pheromoneVector.length;i++)
        {
            pheromoneVector[i] = Configuration.instance.startPheromoneValue;
        }

        ants = new Ant[Configuration.instance.numberOfAnts];

        for(int i=0;i<ants.length;i++)
        {
            ants[i] = new Ant(Knapsack,KnapsackSize,this);
        }

    }

    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }

    public int getKnapsackSize() {
        return KnapsackSize;
    }

    public void setKnapsackSize(int knapsackSize) {
        KnapsackSize = knapsackSize;
    }

    public double[] getPheromoneVector() {
        return pheromoneVector;
    }

    public void setPheromoneVector(double[] pheromoneVector) {
        this.pheromoneVector = pheromoneVector;
    }

    public Ant[] getAnts() {
        return ants;
    }

    public void setAnts(Ant[] ants) {
        this.ants = ants;
    }
}
