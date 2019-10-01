package algorithm.ga.evolution.mutation;

import main.Configuration;

public class BitFlip
{
    public static String doMutation(char[] charArray)
    {
        int index = Configuration.instance.randomGenerator.nextInt(charArray.length);

        charArray[index] = flip(charArray[index]);

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