package algorithm.aco.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AntColony
{
    private ArrayList<KnapsackItem>Knapsack;
    private int KnapsackSize;
    private double[] pheromoneVector;
    private Ant[] ants;
    private Ant bestAnt;
    private int bestfitness;

    private double startPheromoneValue;
    private int numberOfAnts;
    private double decayFactor;


    public AntColony(ArrayList<KnapsackItem>Knapsack, double startPheromoneValue,int numberOfAnts,double decayFactor)
    {
        this.Knapsack=Knapsack;
        this.KnapsackSize = Knapsack.size();


        this.startPheromoneValue = startPheromoneValue;
        this.numberOfAnts = numberOfAnts;
        this.decayFactor = decayFactor;



        bestAnt = null;
        bestfitness=0;

        pheromoneVector = new double[KnapsackSize];
        for(int i=0;i<pheromoneVector.length;i++)
        {
            pheromoneVector[i] = this.startPheromoneValue;
        }

        ants = new Ant[this.numberOfAnts];

        for(int i=0;i<ants.length;i++)
        {
            ants[i] = new Ant(Knapsack,KnapsackSize,this);
        }

    }

    public void decayPheromone()
    {
        for(int i=0;i<pheromoneVector.length;i++)
            pheromoneVector[i] = this.decayFactor*pheromoneVector[i];

    }

    public void solve()
    {



        for(int i=0;i<ants.length;i++)
        {
            ants[i].newRound();
            ants[i].lookForWay();

        }

        decayPheromone();

        Arrays.sort(ants,Collections.reverseOrder());

        int currentbest = ants[0].getValueOfsack();

        if(currentbest>bestfitness)
        {
            bestfitness=currentbest;
            bestAnt = new Ant(ants[0]);
           // System.out.println(bestfitness);
        }

        //bestAnt.layPheromone(1);

        ants[0].layPheromone(1);







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
