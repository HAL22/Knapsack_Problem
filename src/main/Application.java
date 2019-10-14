package main;

import algorithm.aco.base.Ant;
import algorithm.aco.base.AntColony;
import algorithm.ga.base.Chromosome;
import algorithm.ga.base.Population;
import algorithm.ga.evolution.crossover.OnePoint;
import algorithm.ga.evolution.crossover.TwoPoint;
import algorithm.pso.base.Swarm;
import algorithm.sa.main.Sack;
import algorithm.sa.main.SimulatedAnnealing;

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

        AntColony antColony =  new AntColony(Knapsack);

        Swarm swarm = new Swarm(Knapsack);

        int i = 0;
        Chromosome bestChromosome = population.getPopulation()[0];

        int bestfitness=0;

        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
        {
          //  population.evolve();

            /*

            antColony.solve();

            Arrays.sort(antColony.getAnts(),Collections.reverseOrder());

           int fitness =  antColony.getAnts()[0].getValueOfsack();

           if(fitness>bestfitness)
               bestfitness=fitness;*/

            swarm.execute();






        }
        /*

        Arrays.sort(population.getPopulation(), Collections.reverseOrder());

        bestChromosome = population.getPopulation()[0];

        System.out.println("Genetic algo"+" "+bestChromosome.getFitness());

        SimulatedAnnealing sa = new SimulatedAnnealing(Knapsack,Configuration.instance.maximumCapacity);

        Sack bestSoultion = sa.execute();

        System.out.println(" Simulated Annealing "+" "+bestSoultion.getFitness());

        Arrays.sort(antColony.getAnts(),Collections.reverseOrder());

        Ant bestAnt = antColony.getAnts()[0];

        System.out.println(bestfitness);*/

        System.out.println(swarm.getOptimalValue());











    }
}