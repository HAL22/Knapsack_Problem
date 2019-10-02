package algorithm.ga.evolution.crossover;

import algorithm.ga.base.Chromosome;
import main.Configuration;

public class TwoPoint
{

    public static Chromosome[] doCrossover(Chromosome c1, Chromosome c2)
    {
        char[] charArray01 = c1.getGene().toCharArray();
        char[] charArray02 = c2.getGene().toCharArray();

        int pivot1 = Configuration.instance.randomGenerator.nextInt(charArray01.length-1);
        int pivot2 = Configuration.instance.randomGenerator.nextInt(((charArray01.length)-pivot1)-1) + (pivot1+1);

        char[] child01 = new char[c1.getGene().length()];
        char[] child02 = new char[c1.getGene().length()];

        System.arraycopy(charArray01, 0, child01, 0, pivot1);
        System.arraycopy(charArray02, pivot1, child01, pivot1, pivot2-pivot1);
        System.arraycopy(charArray01, pivot2, child01, pivot2, child01.length-pivot2);

        System.arraycopy(charArray02, 0, child02, 0, pivot1);
        System.arraycopy(charArray01, pivot1, child02, pivot1, pivot2-pivot1);
        System.arraycopy(charArray02, pivot2, child02, pivot2, child02.length-pivot2);


        return new Chromosome[]{new Chromosome(String.valueOf(child01)),new Chromosome(String.valueOf(child02))};

    }

}
