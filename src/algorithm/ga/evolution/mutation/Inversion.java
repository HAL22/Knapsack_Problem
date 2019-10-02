package algorithm.ga.evolution.mutation;

import main.Configuration;

public class Inversion
{

    public static String doMutation(char[] charArray)
    {
        int pivot1 = Configuration.instance.randomGenerator.nextInt(charArray.length-1);
        int pivot2 = Configuration.instance.randomGenerator.nextInt(((charArray.length)-pivot1)-1) + (pivot1+1);

        for(int i=pivot1;i<pivot2+1;i++)
        {
            charArray[i] = flip(charArray[i]);

        }

        return String.valueOf(charArray);

    }

    public static char flip(char i)
    {
        if(i=='1')
            return '0';
        else
            return '1';

    }


}