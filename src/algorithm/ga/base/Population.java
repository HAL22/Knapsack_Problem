package algorithm.ga.base;

import algorithm.ga.evolution.crossover.OnePoint;
import algorithm.ga.evolution.crossover.TwoPoint;
import algorithm.ga.evolution.mutation.BitFlip;
import algorithm.ga.evolution.mutation.Inversion;
import algorithm.ga.evolution.selection.RouletteWheel;
import algorithm.ga.evolution.selection.Tournament;
import main.Configuration;
import main.KnapsackItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Population
{
    private double elitismRatio;
    private double mutationRatio;
    private double crossoverRatio;
    private Chromosome[] population;
    private int numberOfCrossoverOperations = 0;
    private int numberOfMutationOperations = 0;
    private ArrayList<KnapsackItem> Knapsack;

    public Population(int size, double crossoverRatio, double elitismRatio, double mutationRatio,ArrayList<KnapsackItem> Knapsack) {
        this.crossoverRatio = crossoverRatio;
        this.elitismRatio = elitismRatio;
        this.mutationRatio = mutationRatio;
        this.Knapsack = Knapsack;
        population = new Chromosome[size];


        for (int i = 0; i < size; i++)
        {
            population[i] = Chromosome.generateRandom();
            population[i].setKnapsack(Knapsack);
            population[i].setFitness();
        }

        //Arrays.sort(population, Collections.reverseOrder());

    }

    public int getNumberOfCrossoverOperations() {
        return numberOfCrossoverOperations;
    }

    public int getNumberOfMutationOperations() {
        return numberOfMutationOperations;
    }

    public Chromosome[] getPopulation() {
        return population;
    }

    public void setPopulation(Chromosome[] population) {
        this.population = population;
    }

    public void evolve()
    {
        Chromosome[] chromosomeArray = new Chromosome[population.length];
        int index = (int) Math.round(population.length * elitismRatio);
        System.arraycopy(population, 0, chromosomeArray, 0, index);

        while (index < chromosomeArray.length)
        {
            if (Configuration.instance.randomGenerator.nextFloat() <= crossoverRatio)
            {
                Chromosome parent1 = Tournament.selectParent(population);
                Chromosome parent2 = Tournament.selectParent(population);




                Chromosome[] children = TwoPoint.doCrossover(parent1,parent2);
                numberOfCrossoverOperations++;

                if (Configuration.instance.randomGenerator.nextFloat() <= mutationRatio)
                {
                    Chromosome c =  new Chromosome(BitFlip.doMutation(children[0].getGene().toCharArray()));
                    chromosomeArray[(index++)] = c;
                    numberOfMutationOperations++;

                }
                else
                    chromosomeArray[(index++)] = children[0];

                if (index < chromosomeArray.length)

                    if (Configuration.instance.randomGenerator.nextFloat() <= mutationRatio)
                    {
                        Chromosome c =  new Chromosome(BitFlip.doMutation(children[1].getGene().toCharArray()));
                        chromosomeArray[index] = c;
                        numberOfMutationOperations++;
                    }else
                        chromosomeArray[index] = children[1];


            }

            else if (Configuration.instance.randomGenerator.nextFloat() <= mutationRatio)
            {
                Chromosome c= new Chromosome(BitFlip.doMutation(population[index].getGene().toCharArray()));
                c.setKnapsack(Knapsack);
                c.setFitness();
                chromosomeArray[index] = c;
                numberOfMutationOperations++;
            } else {
                chromosomeArray[index] = population[index];
            }

            index++;



        }


      //  Arrays.sort(chromosomeArray,Collections.reverseOrder());
        population = chromosomeArray;


    }






}
