package algorithm.aco.recommender;

import algorithm.aco.base.AntColony;
import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AcoRecommender
{
    public static  void parameterSearch(double percent, ArrayList<KnapsackItem> Knapsack) throws Exception
    {
        //ACO
        double decayFactor = 0.1;
        double startPheromoneValue = 10;
        int numberOfAnts = 2;

        int i=0;
        int bestfitness=0;
        boolean found = false;


        for(int j=numberOfAnts;j<40;j++)
        {
            for(double k= decayFactor;k<0.20;k=k+0.1)
            {
                for(double n=startPheromoneValue;n<20;n++)
                {

                    AntColony antColony = new AntColony(Knapsack,n,j,k);


                    while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
                    {
                        antColony.solve();

                        Arrays.sort(antColony.getAnts(), Collections.reverseOrder());

                        int fitness =  antColony.getAnts()[0].getValueOfsack();

                        if(fitness>bestfitness)
                            bestfitness=fitness;

                    }

                    if(meetsPercentage(0.80,bestfitness))
                    {

                        found=true;

                        StringToDOM(i,n,k);

                        break;

                    }




                }

                if(found)
                {
                    break;
                }
            }

            if(found)
            {
                break;
            }
        }



    }






    public static boolean meetsPercentage(double percent, double solution)
    {
        double value_max = Configuration.instance.bestKnownOptimum * percent;

        return solution>value_max;

    }

    public static void StringToDOM(int numberOfAnts,double startpheromone,double decayfactor) throws  Exception
    {
        String xml = String.format("<ACO_Best>\n" +
                "\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Decay factor</name>\n" +
                "        <value>\n" +
                "        %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Start Pheromone Value</name>\n" +
                "        <value>\n" +
                "          %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Number of ants</name>\n" +
                "        <value>\n" +
                "            %o\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Beta</name>\n" +
                "        <value>\n" +
                "            1\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Theta</name>\n" +
                "        <value>\n" +
                "            1\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</ACO_Best>",decayfactor,startpheromone,numberOfAnts);

        System.out.println("ASO Best configuration written");


        java.io.FileWriter fw = new java.io.FileWriter("src/algorithm/aco/recommender/aco_best.xml");

        fw.write(xml);
        fw.close();

    }

}
