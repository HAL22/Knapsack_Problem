package main;

import algorithm.ga.base.Chromosome;
import algorithm.ga.base.Population;
import algorithm.ga.evolution.crossover.OnePoint;
import algorithm.ga.evolution.crossover.TwoPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Application {
    // --- command line ---
    // -algorithm [ga | sa | aco | pso | best-algorithm] -configuration [default | best] -search_best_configuration
    public static void main(String... args)  throws  Exception
    {
        ArrayList<KnapsackItem> Knapsack = new ArrayList<>();
        double currentBestFitness =0;


        BufferedReader csvReader = new BufferedReader(new FileReader(Configuration.instance.dataFilePath));

        String row="";
        while ((row = csvReader.readLine()) != null)
        {
            if(row.contains("#")==false)
            {
                String[] line = row.split(";");
                Knapsack.add(new KnapsackItem(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2])));

            }


        }



        Population population = new Population(Knapsack.size(),Configuration.instance.crossoverRatio,Configuration.instance.elitismRatio,Configuration.instance.mutationRatio,Knapsack);

        int i = 0;
        Chromosome bestChromosome = population.getPopulation()[0];

        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
        {
            population.evolve();




        }

        Arrays.sort(population.getPopulation(), Collections.reverseOrder());

        bestChromosome = population.getPopulation()[0];

        System.out.println(bestChromosome.getFitness());









    }
}