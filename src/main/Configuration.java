package main;

import random.MersenneTwisterFast;

import java.text.DecimalFormat;

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
    double crossoverRatio = 0.2;
    double elitismRatio = 0.2;
    double mutationRatio = .5555;
    public int select_K_Chromosomes = 40;

    //Simulated Annealing
    public double initialTemperature = 10000;
    public double alpha = 0.0003; // cooling rate
    public double finishTemperature = 1;

        // SA search for best parameters
        public double SA_startAlpha = 0.0001;
        public double SA_alphaInc = 0.0001;





    //ACO
    public  double decayFactor = 0.1;
    public  double startPheromoneValue = 50;
    public  int numberOfAnts = 10;
    public double theta=1;
    public double beta = 1;

    //PSO
    public double VMAX = 4;
    public double VMIN = -4;
    public int numberOfParticles = 20;
    public double cognitiveFactor = 1.42694;
    public double socialFactor = 1.42694;
    public double w = 1;













}