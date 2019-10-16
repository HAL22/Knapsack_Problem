package algorithm.ga.evolution.mutation;

import main.Configuration;

public class Displacement
{
    public static String doMutation(char[] charArray)
    {

        int pivot1 = Configuration.instance.randomGenerator.nextInt(charArray.length-1);
        int pivot2 = Configuration.instance.randomGenerator.nextInt(((charArray.length)-pivot1)-1) + (pivot1+1);

        String mainString = String.valueOf(charArray);

        String sectionMoved = mainString.substring(pivot1,pivot2);

        String sectionStay = mainString.substring(0,pivot1) + mainString.substring(pivot2);

        int pos =  Configuration.instance.randomGenerator.nextInt(sectionStay.length());

        return sectionStay.substring(0,pos) + sectionMoved + sectionStay.substring(pos);

    }

}