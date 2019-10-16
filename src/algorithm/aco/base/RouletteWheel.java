package algorithm.aco.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;
import java.util.Arrays;

public class RouletteWheel
{
    public static KnapsackItem selectKnapsackItem(double[] pheromoneVector,ArrayList<KnapsackItem>NotYetVisited)
    {

        double sum =0.0;

        for(KnapsackItem k:NotYetVisited)
        {
            sum =sum + (Math.pow(((double)k.getValue()/(k.getWeight()*k.getWeight())),(Configuration.instance.beta)))*(Math.pow((pheromoneVector[k.getIndex()-1]),(Configuration.instance.theta)));
        }

        //

        double[] roulette = createRoulette(sum,pheromoneVector,NotYetVisited);

        double number = Configuration.instance.randomGenerator.nextDouble();

        for(int i=0;i<roulette.length;i++)
        {
            if(number > roulette[i])
            {
                return NotYetVisited.get(i);
            }
        }


        return NotYetVisited.get(0);






    }

    public static double[] createRoulette(double sum,double[] pheromoneVector,ArrayList<KnapsackItem>NotYetVisited)
    {
        double[] roulette = new double[NotYetVisited.size()];

        double[] sortedroulette = new double[NotYetVisited.size()];

        for(int i=0;i<NotYetVisited.size();i++)
        {
            KnapsackItem k = NotYetVisited.get(i);
            double kValue = (Math.pow(((double)k.getValue()/(k.getWeight()*k.getWeight())),(Configuration.instance.beta)))*(Math.pow((pheromoneVector[k.getIndex()-1]),(Configuration.instance.theta)));
            sortedroulette[i] = kValue/sum;
            roulette[i] = kValue/sum;
        }

        Arrays.sort(sortedroulette);

        double min = sortedroulette[0];
        double max = sortedroulette[sortedroulette.length-1];

        //Normalise the array

        for(int i=0;i<roulette.length;i++)
        {
            roulette[i] = 1 - ((roulette[i] - min) / (max - min));
        }



        return roulette;
    }



}
