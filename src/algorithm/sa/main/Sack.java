package algorithm.sa.main;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class Sack
{
    private String sack;
    private int weight;
    private int fitness;
    private int size_of_sack;

    public ArrayList<KnapsackItem> Knapsack;

    public Sack(String sack,int weight,int fitness,int size_of_sack,ArrayList<KnapsackItem> Knapsack)
    {
        this.sack=sack;
        this.weight=weight;
        this.fitness=fitness;
        this.size_of_sack=size_of_sack;
        this.Knapsack=Knapsack;
    }

    public Sack(Sack s)
    {
        this.sack = s.getSack();
        this.weight = s.getWeight();
        this.fitness = s.fitness;
        this.size_of_sack = s.getSize_of_sack();
        this.Knapsack = s.getKnapsack();

    }




    public String getSack() {
        return sack;
    }

    public void setSack(String sack) {
        this.sack = sack;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getSize_of_sack() {
        return size_of_sack;
    }

    public void setSize_of_sack(int size_of_sack) {
        this.size_of_sack = size_of_sack;
    }

    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }


    public int calculateWeight()
    {
        int weight=0;

        char[] charArray = this.sack.toCharArray();

        for(int i=0;i<size_of_sack;i++)
        {
            if(charArray[i]=='1')
            {
                weight = weight + this.Knapsack.get(i).getWeight();

            }
        }


        return weight;
    }

    public int calculateFitness()
    {
        int fitness=0;

        char[] charArray = this.sack.toCharArray();

        for(int i=0;i<size_of_sack;i++)
        {
            if(charArray[i]=='1')
            {
                fitness = fitness + this.Knapsack.get(i).getValue();

            }
        }


        return fitness;


    }

    public void addtoSack(int pos)
    {
        char[] charArray = this.sack.toCharArray();

        if(charArray[pos]=='0')
        {
            charArray[pos]='1';
        }

        this.sack = String.valueOf(charArray);

        this.fitness=calculateFitness();
        this.weight=calculateWeight();



    }

    public void removefromSack(int pos)
    {
        char[] charArray = this.sack.toCharArray();

        if(charArray[pos]=='1')
        {
            charArray[pos]='0';
        }

        this.sack = String.valueOf(charArray);

        this.fitness =calculateFitness();
       this.weight= calculateWeight();


    }


    public static Sack createRandomSack(ArrayList<KnapsackItem> Knapsack)
    {
        char charArray[] = new char[Configuration.instance.numberOfItems];

        for(int i=0;i<charArray.length;i++)
        {
            if(Configuration.instance.randomGenerator.nextFloat()>0.5)
            {
                charArray[i]='1';
            }
            else
            {
                charArray[i]='0';
            }


        }

        Sack s = new Sack(String.valueOf(charArray),0,0,Configuration.instance.numberOfItems,Knapsack);
        s.setWeight(s.calculateWeight());
        s.setFitness(s.calculateFitness());

        return s;

    }


}
