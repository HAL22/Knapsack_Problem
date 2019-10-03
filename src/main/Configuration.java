package main;

import random.MersenneTwisterFast;

public enum Configuration
{
    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");
    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String dataFilePath = dataDirectory + "knapsack_instance.csv";
    public String dataRDirectory = userDirectory;

    public MersenneTwisterFast randomGenerator = new MersenneTwisterFast(System.nanoTime());

    public int numberOfItems = 150;
    public int maximumCapacity = 822;
    public int bestKnownOptimum = 1013;

    int maximumNumberOfGenerations = 10000;
    // Tournament selection


    //Genetic algorithm
    double crossoverRatio = 0.1;
    double elitismRatio = 0.1;
    double mutationRatio = .5555;
    public int select_K_Chromosomes = 40;

    //Simulated Annealing
    public double initialTemperature = 10000;
    public double alpha = 0.003; // cooling rate
    public double finishTemperature = 1;






}