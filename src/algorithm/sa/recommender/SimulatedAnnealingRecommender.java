package algorithm.sa.recommender;

import algorithm.sa.main.SimulatedAnnealing;
import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class SimulatedAnnealingRecommender
{
    public static  void parameterSearch(double percent, ArrayList<KnapsackItem>Knapsack)
    {
        double solution = 0.0;
        double bestAlpha = 0.0;
        boolean bestFound=false;

        double alpha = Configuration.instance.SA_startAlpha;

        while(!bestFound)
        {

            SimulatedAnnealing sa = new SimulatedAnnealing(Knapsack,Knapsack.size(),alpha);

            double result = sa.execute().getFitness();

            if(meetsPercentage(percent,result))
            {
                bestFound=true;
                solution = result;
                bestAlpha = alpha;
            }


            alpha += Configuration.instance.SA_alphaInc;
        }

        System.out.println(alpha);







    }

    public static boolean meetsPercentage(double percent, double solution)
    {
        double value_max = Configuration.instance.bestKnownOptimum * percent;

        return solution>=value_max;

    }

}
