package algorithm.ga.evolution.mutation;

import main.Configuration;

public class Insertion
{
    public static String doMutation(char[] charArray)
    {
        int pivot1 = Configuration.instance.randomGenerator.nextInt(charArray.length-1);

        String mainString = String.valueOf(charArray);

        String pointMoved = mainString.substring(pivot1,pivot1+1);

        String staySection = mainString.substring(0,pivot1)+mainString.substring(pivot1+1);

        int pos =  Configuration.instance.randomGenerator.nextInt(staySection.length());

        return staySection.substring(0,pos) + pointMoved + staySection.substring(pos);




    }
}