package algorithm.aco.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class RouletteWheel
{
    public static KnapsackItem selectKnapsackItem(double[] pheromoneVector,ArrayList<KnapsackItem>NotYetVisited)
    {

        double sum =0.0;

        for(KnapsackItem k:NotYetVisited)
        {
            sum =sum + (Math.pow(((double)k.getValue()/k.getWeight()),(Configuration.instance.beta)))*(Math.pow((pheromoneVector[k.getIndex()-1]),(Configuration.instance.theta)));
        }

        double[] roulette = createRoulette(sum,pheromoneVector,NotYetVisited);

        float number = Configuration.instance.randomGenerator.nextFloat();

        for(int i=0;i<roulette.length;i++)
        {
            if(number < roulette[i])
            {
                return NotYetVisited.get(i);
            }
        }


        return null;






    }

    public static double[] createRoulette(double sum,double[] pheromoneVector,ArrayList<KnapsackItem>NotYetVisited)
    {
        double[] roulette = new double[NotYetVisited.size()];

        for(int i=0;i<NotYetVisited.size();i++)
        {
            KnapsackItem k = NotYetVisited.get(i);
            double kValue = (Math.pow(((double)k.getValue()/k.getWeight()),(Configuration.instance.beta)))*(Math.pow((pheromoneVector[k.getIndex()-1]),(Configuration.instance.theta)));
            roulette[i] = kValue/sum;
        }




        return roulette;
    }



}
