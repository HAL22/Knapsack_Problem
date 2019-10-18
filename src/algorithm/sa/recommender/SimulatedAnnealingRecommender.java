package algorithm.sa.recommender;

import algorithm.sa.main.SimulatedAnnealing;
import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class SimulatedAnnealingRecommender
{
    public static  void parameterSearch(double percent, ArrayList<KnapsackItem>Knapsack) throws Exception
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


            alpha = alpha+ Configuration.instance.SA_alphaInc;



        }

        StringToDOM(alpha);


    }

    public static boolean meetsPercentage(double percent, double solution)
    {
        double value_max = Configuration.instance.bestKnownOptimum * percent;

        return solution>=value_max;

    }

    public static void StringToDOM(double alpha) throws  Exception
    {




        String xml = String.format("<SA_Best_Config>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Alpha/Cooling_rate</name>\n" +
                "        <value>\n" +
                "            %f\n" +
                "        </value>\n" +
                "\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Initial Temperature</name>\n" +
                "        <value>\n" +
                "            10000\n" +
                "        </value>\n" +
                "\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Finish Temperature</name>\n" +
                "        <value>\n" +
                "            1\n" +
                "        </value>\n" +
                "\n" +
                "    </parameter>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</SA_Best_Config>",alpha);



        System.out.println("SA Best configuration written");


        java.io.FileWriter fw = new java.io.FileWriter("src/algorithm/sa/recommender/sa_best.xml");

        fw.write(xml);
        fw.close();



    }

}
