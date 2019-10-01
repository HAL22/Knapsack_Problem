package algorithm.ga.evolution.selection;

import algorithm.ga.base.Chromosome;
import main.Configuration;

public class Tournament
{
    public static Chromosome[] selectParent(Chromosome[] population)
    {

        //Chromosome parent =

        Chromosome[] parentArray = new Chromosome[2];

        for (int i = 0; i < 2; i++)
        {
            parentArray[i] = population[Configuration.instance.randomGenerator.nextInt(population.length)];

            for (int j = 0; j < 3; j++) {
                int index = Configuration.instance.randomGenerator.nextInt(population.length);
                if (population[index].compareTo(parentArray[i]) > 0)
                    parentArray[i] = population[index];
            }


        }



        return parentArray;


    }
}
