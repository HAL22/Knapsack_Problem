package algorithm.sa.main;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class SimulatedAnnealing
{

    private ArrayList<KnapsackItem> Knapsack;
    private int Knapsack_capacity;

    public SimulatedAnnealing(ArrayList<KnapsackItem> Knapsack,int capacity)
    {
        this.Knapsack= Knapsack;
        this.Knapsack_capacity=capacity;
    }


    public Sack execute()
    {
        double temperature = Configuration.instance.initialTemperature;

        Sack currentSolution = Sack.createRandomSack(Knapsack);
        removeuntilUnderCap(currentSolution);

        Sack bestSolution = new Sack(currentSolution);

        while (temperature > Configuration.instance.finishTemperature)
        {
            Sack newSolution = new Sack(currentSolution);

            addTosack(newSolution);

            int currentEnergy = currentSolution.getFitness();
            int neighbourEnergy = newSolution.getFitness();

            if (acceptanceProbability(currentEnergy, neighbourEnergy, temperature) > Configuration.instance.randomGenerator.nextDouble()) {
                currentSolution = new Sack(newSolution);
            }

            if(currentSolution.getFitness()>bestSolution.getFitness())
            {
                bestSolution = new Sack(currentSolution);
            }
            temperature *= 1 - Configuration.instance.alpha;






        }



        return  bestSolution;



    }


    public void removeuntilUnderCap(Sack s)
    {

            while(s.getWeight()>this.Knapsack_capacity)
            {
                int number = Configuration.instance.randomGenerator.nextInt(s.getSize_of_sack());

                s.removefromSack(number);


            }



    }

    public void addTosack(Sack s)
    {


                int number = Configuration.instance.randomGenerator.nextInt(s.getSize_of_sack());

                s.addtoSack(number);





                removeuntilUnderCap(s);




    }

    public double acceptanceProbability(int currentEnergy, int neighbourEnergy, double temperature)
    {
        double value;


        if (neighbourEnergy > currentEnergy)
            value = 1.0;

        value = Math.exp(-(currentEnergy - neighbourEnergy) / temperature);

       // System.out.println("acceptanceProbability (" + currentEnergy + " | " + neighbourEnergy + " | " + Configuration.instance.decimalFormat.format(temperature) + " | " + Configuration.instance.decimalFormat.format(value) + ")");
        return value;
    }


}
