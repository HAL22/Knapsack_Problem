package algorithm.ga.evolution.selection;

import algorithm.ga.base.Chromosome;
import main.Configuration;

public class RouletteWheel
{
    public static Chromosome selectParent(Chromosome[] population)
    {
        Chromosome parentArray =null;


            int sum_of_fitness=0;

            double sum_of_probability =0;

            double probability = 0;

            float number=0f;

            for(int i=0;i<population.length;i++)
            {
                sum_of_fitness=sum_of_fitness+population[i].getFitness();
            }

            double[] roullette = createRoulette(sum_of_fitness,population.length,population);

            number = Configuration.instance.randomGenerator.nextFloat();


            for(int i=0;i<population.length;i++)
            {


                    if(number < roullette[i])
                    {
                        parentArray= population[i];
                        return parentArray;
                    }



            }



        return null;

    }

    public static double[] createRoulette(int sum_of_fitness,int size,Chromosome[] population)
    {
        double[] roulette = new double[size];

        double sum_prob = 0;

        for(int i=0;i<size;i++)
        {
            double prob = sum_prob + (((double)population[i].getFitness())/sum_of_fitness);
            sum_prob = sum_prob + prob;
            roulette[i]= prob;

        }


        return  roulette;


    }


}
