package algorithm.ga.evolution.crossover;

import algorithm.ga.base.Chromosome;
import main.Configuration;

public class OnePoint
{
    public static Chromosome[] doCrossover(Chromosome c1,Chromosome c2)
    {
        char[] charArray01 = c1.getGene().toCharArray();
        char[] charArray02 = c2.getGene().toCharArray();

        int pivot = Configuration.instance.randomGenerator.nextInt(charArray01.length);
        char[] child01 = new char[c1.getGene().length()];
        char[] child02 = new char[c1.getGene().length()];

        System.arraycopy(charArray01, 0, child01, 0, pivot);
        System.arraycopy(charArray02, pivot, child01, pivot, child01.length - pivot);
        System.arraycopy(charArray02, 0, child02, 0, pivot);
        System.arraycopy(charArray01, pivot, child02, pivot, child02.length - pivot);

        return new Chromosome[]{new Chromosome(String.valueOf(child01)),new Chromosome(String.valueOf(child02))};

    }


}
