package algorithm.ga.evolution.mutation;

import main.Configuration;

public class Exchange {

    public static String doMutation(char[] charArray) {

        int pos1 = Configuration.instance.randomGenerator.nextInt(charArray.length);

        int pos2 = Configuration.instance.randomGenerator.nextInt(charArray.length);

        //Exchange

        char cholder = charArray[pos1];
        charArray[pos1] = charArray[pos2];
        charArray[pos2]= cholder;

        return String.valueOf(charArray);


    }


}