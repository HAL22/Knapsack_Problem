package main;

import algorithm.aco.base.Ant;
import algorithm.aco.base.AntColony;
import algorithm.ga.base.Chromosome;
import algorithm.ga.base.Population;
import algorithm.ga.evolution.crossover.OnePoint;
import algorithm.ga.evolution.crossover.TwoPoint;
import algorithm.pso.base.Swarm;
import algorithm.pso.recommender.PsoRecommender;
import algorithm.sa.main.Sack;
import algorithm.sa.main.SimulatedAnnealing;
import algorithm.sa.recommender.SimulatedAnnealingRecommender;

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


        // Reading in the data from the Knapsack csv file
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
        Swarm swarm = new Swarm(Knapsack,Configuration.instance.VMAX,Configuration.instance.VMIN,Configuration.instance.w,Configuration.instance.socialFactor,Configuration.instance.cognitiveFactor);
        SimulatedAnnealing sa = new SimulatedAnnealing(Knapsack,Configuration.instance.maximumCapacity,Configuration.instance.alpha);


        switch(args[0])
        {
            case "-ga":
                if(args[1].equals("-best"))
                {
                    GA(population,"best");

                }
                else if(args[1].equals("-default"))
                {
                    GA(population,"default");

                }
                else if(args[1].equals("-search_best_configuration"))
                    {

                    }

                break;

            case "-sa":


                if(args[1].equals("-best"))
                {
                    SA(sa,"best");

                }
                else if(args[1].equals("-default"))
                {
                    SA(sa,"default");

                }
                else if(args[1].equals("-search_best_configuration"))
                {
                    SimulatedAnnealingRecommender.parameterSearch(0.80,Knapsack);

                }

                break;


            case "-aco":


                if(args[1].equals("-best"))
                {
                    ACO(antColony,"best");

                }
                else if(args[1].equals("-default"))
                {
                    ACO(antColony,"default");

                }
                else if(args[1].equals("-search_best_configuration"))
                {

                }

                break;


            case "-pso":


                if(args[1].equals("-best"))
                {
                    PSO(swarm,"best");

                }
                else if(args[1].equals("-default"))
                {
                    PSO(swarm,"default");

                }
                else if(args[1].equals("-search_best_configuration"))
                {
                    PsoRecommender.parameterSearch(0.80,Knapsack);

                }

                break;

            case "-best-algorithm":

                BestAlgo(population,sa,antColony,swarm);
                 break;

            default:
                System.out.println("ERROR");


        }




    }


    public static void GA(Population population,String config)
    {
        int i=0;

        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
        {
            population.evolve();
            Arrays.sort(population.getPopulation(),Collections.reverseOrder());
        }


        Chromosome bestChromosome = population.getPopulation()[0];

        System.out.println("Genetic algorithm running with "+config+" configuration:");

        System.out.println("The fitness: "+bestChromosome.getFitness());

        System.out.println("The gene: "+bestChromosome.getGene());



    }

    public  static void SA(SimulatedAnnealing sa,String config)
    {
        Sack bestSack = sa.execute();

        System.out.println(" Simulated Annealing algorithm running with "+config+" configuration");
        System.out.println("The value: "+bestSack.getFitness());



    }

    public static void ACO(AntColony antColony, String config)
    {
        int i=0;
        int bestfitness=0;

        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
        {
            antColony.solve();

            Arrays.sort(antColony.getAnts(),Collections.reverseOrder());

            int fitness =  antColony.getAnts()[0].getValueOfsack();

            if(fitness>bestfitness)
                bestfitness=fitness;

        }

        System.out.println(" ACO algorithm running with "+config+" configuration");
        System.out.println("The value: "+bestfitness);


    }

    public static  void PSO(Swarm swarm,String config)
    {
        int i=0;
        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
        {
            swarm.execute();


        }

        System.out.println("PSO algorithm running with "+config+" configuration");
        System.out.println("The value: "+swarm.getOptimalValue());

    }

    public static void BestAlgo(Population population,SimulatedAnnealing sa,AntColony antColony, Swarm swarm)
    {
        int i=0;
        int bestfitness=0;

        while ((i++ <= Configuration.instance.maximumNumberOfGenerations))
        {
            population.evolve();

            Arrays.sort(population.getPopulation(), Collections.reverseOrder());

            swarm.execute();


            antColony.solve();

            Arrays.sort(antColony.getAnts(),Collections.reverseOrder());

            int fitness =  antColony.getAnts()[0].getValueOfsack();

            if(fitness>bestfitness)
                bestfitness=fitness;

        }


        Sack bestSack = sa.execute();


        Arrays.sort(population.getPopulation(), Collections.reverseOrder());
        Chromosome bestChromosome = population.getPopulation()[0];


        int bestAlgo = Math.max(Math.max(bestfitness,swarm.getOptimalValue()),Math.max(bestChromosome.getFitness(),bestSack.getFitness()));

        if(bestAlgo==bestSack.getFitness())
        {
            System.out.println("The best algorithm in terms of best knapsack value(minimum 90%) was: SA with value = "+bestAlgo);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("GA value(minimum 90%) = "+bestChromosome.getFitness());
            System.out.println("PSO value(minimum 90%) = "+swarm.getOptimalValue());
            System.out.println("ACO value(minimum 90%) = "+bestfitness);


        }
        else if(bestAlgo==bestChromosome.getFitness())
            {
                System.out.println("The best algorithm in terms of best knapsack value(minimum 90%) was: GA with value = "+bestChromosome.getFitness());
                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.println("SA value(minimum 90%) = "+bestSack.getFitness());
                System.out.println("PSO value(minimum 90%) = "+swarm.getOptimalValue());
                System.out.println("ACO value(minimum 90%) = "+bestfitness);

            }

        else if(bestAlgo==swarm.getOptimalValue())
        {
            System.out.println("The best algorithm in terms of best knapsack value(minimum 90%) was: PSO with value = "+bestAlgo);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("SA value(minimum 90%) = "+bestSack.getFitness());
            System.out.println("GA value(minimum 90%) = "+bestChromosome.getFitness());
            System.out.println("ACO value(minimum 90%) = "+bestfitness);

        }

        else if(bestAlgo==bestfitness)
        {
            System.out.println("The best algorithm in terms of best knapsack value(minimum 90%) was: ACO with value = "+bestAlgo);
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("SA value(minimum 90%) = "+bestSack.getFitness());
            System.out.println("PSO value(minimum 90%) = "+swarm.getOptimalValue());
            System.out.println("GA value(minimum 90%) = "+bestChromosome.getFitness());

        }

        else
            {
                System.out.println("ERROR");
            }



    }
}


