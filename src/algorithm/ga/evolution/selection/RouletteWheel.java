package algorithm.ga.evolution.selection;

import algorithm.ga.base.Chromosome;
import main.Configuration;

public class RouletteWheel
{
    public static Chromosome[] selectParent(Chromosome[] population)
    {
        Chromosome[] parentArray = new Chromosome[2];

        for(int j=0;j<2;j++)
        {
            int sum_of_fitness=0;

            double sum_of_probability =0;

            for(int i=0;i<population.length;i++)
            {
                sum_of_fitness=sum_of_fitness+population[i].getFitness();
            }

            for(int i=0;i<population.length;i++)
            {
                double probability = sum_of_probability + (population[i].getFitness()/sum_of_fitness);
                sum_of_probability = sum_of_probability + probability;
                population[i].setProbability(probability);
            }

            float number = Configuration.instance.randomGenerator.nextFloat(false,false);

            for(int i=0;i<population.length;i++)
            {
                if(i==population.length-1)
                {
                    int next_index = 0;

                    if(number > population[i].getProbability() && number < population[next_index].getProbability()){   parentArray[j]= population[i];
                        break;}




                }

                else
                {
                    int next_index = i+1;

                    if(number > population[i].getProbability() && number < population[next_index].getProbability()){ parentArray[j]= population[i];
                        break;}




                }


            }


        }







        return parentArray;

    }


}
