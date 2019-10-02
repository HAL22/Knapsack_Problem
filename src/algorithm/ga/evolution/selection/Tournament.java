package algorithm.ga.evolution.selection;

import algorithm.ga.base.Chromosome;
import main.Configuration;

import java.util.Arrays;
import java.util.Collections;

public class Tournament
{
    public static Chromosome selectParent(Chromosome[] population)
    {


        Chromosome bestChromosome=null;

        Chromosome[] K_Chromosomes =  new Chromosome[Configuration.instance.select_K_Chromosomes];

        for(int i=0;i<Configuration.instance.select_K_Chromosomes;i++)
        {
            int index = Configuration.instance.randomGenerator.nextInt(population.length);
            K_Chromosomes[i] = population[index];

        }

        Arrays.sort(K_Chromosomes, Collections.reverseOrder());

        bestChromosome = K_Chromosomes[0];





        return bestChromosome;


    }
}
