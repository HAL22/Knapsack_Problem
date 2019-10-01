package algorithm.ga.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class Chromosome implements Comparable<Chromosome>
{

    private String gene;
    private int fitness;
    public ArrayList<KnapsackItem>Knapsack;
    public double probability ; // used by the Roulette wheel selection

    public Chromosome(String gene)
    {
        this.gene = gene;
        Knapsack = null;
        fitness =0;
        probability = 0.0;

    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }


    // Before you set the fitness make sure you set the knapsack array

    public void setFitness()
    {
        this.fitness = calculateFintness();
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public static Chromosome generateRandom()
    {
        char charArray[] = new char[Configuration.instance.numberOfItems];

        for(int i=0;i<charArray.length;i++)
        {
            if(Configuration.instance.randomGenerator.nextFloat()>0.8)
            {
                charArray[i]='1';
            }
            else
                {
                    charArray[i]='0';
                }




        }




        return new Chromosome(String.valueOf(charArray));
    }

    public int calculateFintness()
    {
        int fitValue =0;
        int weightValue =0;

        for(int i=0;i<this.gene.length();i++)
        {
            if(this.gene.charAt(i)=='1')
            {
                fitValue = fitValue + Knapsack.get(i).getValue();
                weightValue = weightValue + Knapsack.get(i).getWeight();
            }
        }

        //System.out.println(weightValue);



        if(weightValue>Configuration.instance.maximumCapacity)
        {
            return 0;
        }
        else
            {
                return fitValue;
            }
    }

    public int compareTo(Chromosome chromosome) {
        if (fitness < chromosome.getFitness())
            return -1;

        if (fitness > chromosome.getFitness())
            return 1;

        return 0;
    }


    public int hashCode()
    {
        return (gene + fitness).hashCode();
    }









}
