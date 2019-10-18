package algorithm.pso.recommender;

import algorithm.pso.base.Swarm;
import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class PsoRecommender
{
    public static  void parameterSearch(double percent, ArrayList<KnapsackItem> Knapsack) throws Exception
    {

        double bestVMAX=1.0;
        double bestVMIN =-1.0;
        double bestW=0.1;
        double bestSocialfactor =0.1;
        double bestcogfactor = 0.1;



        double VMAX = 1.0;

        double w = 0.1;

        double socialfactor = 0.1;

        double cogfactor = 0.1;

        boolean found = false;

        for(double i=VMAX;i<40;i++)
        {

            for(double j=w;j<1.2;j=j+0.1)
            {
                for(double k=socialfactor;k<2;k=k+0.01)
                {
                    for(double v=cogfactor;v<2;v=v+0.01)
                    {


                        Swarm swarm = new Swarm(Knapsack,i,-1*i,j,k,v);

                        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
                        {
                            swarm.execute();
                        }



                        if(meetsPercentage(percent,swarm.getOptimalValue()))
                        {
                            found= true;
                        }

                        if(found)
                        {

                             bestVMAX=i;
                             bestVMIN =-1*i;
                             bestW=w;
                             bestSocialfactor =k;
                             bestcogfactor = v;

                             StringToDOM(bestVMAX,bestVMIN,bestW,bestSocialfactor,bestcogfactor);






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

            if(found)
            {
                break;
            }
        }

















    }

    public static void StringToDOM(double vmax,double vmin,double w,double socialfactor,double cogfactor) throws  Exception
    {
        String xml = String.format("<PSO_Best_Config>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Velocity Max</name>\n" +
                "        <value>\n" +
                "            %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name>Velocity Min</name>\n" +
                "        <value>\n" +
                "            %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name> Number of particles</name>\n" +
                "        <value>\n" +
                "\n" +
                "            20\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name> Cognitive Factor </name>\n" +
                "        <value>\n" +
                "\n" +
                "            %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name> Social Factor </name>\n" +
                "        <value>\n" +
                "\n" +
                "            %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "    <parameter>\n" +
                "        <name> Inertia(w)</name>\n" +
                "        <value>\n" +
                "\n" +
                "            %f\n" +
                "\n" +
                "        </value>\n" +
                "    </parameter>\n" +
                "\n" +
                "\n" +
                "</PSO_Best_Config>",vmax,vmin,cogfactor,socialfactor,w);




        System.out.println("PSO Best configuration written");


        java.io.FileWriter fw = new java.io.FileWriter("src/algorithm/pso/recommender/pso_best.xml");

        fw.write(xml);
        fw.close();
    }




    public static boolean meetsPercentage(double percent, double solution)
    {
        double value_max = Configuration.instance.bestKnownOptimum * percent;



        return solution>=value_max;

    }
}
