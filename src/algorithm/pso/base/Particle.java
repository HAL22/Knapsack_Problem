package algorithm.pso.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class Particle
{
    public ArrayList<KnapsackItem> Knapsack;
    private String Position;
    private String best_Position;
    private double[] velocity;
    private Swarm swarm;


    public Particle(ArrayList<KnapsackItem> Knapsack,Swarm s)
    {
        this.swarm = s;

        this.Knapsack = Knapsack;

        generateRandomPosAndVec();




    }


    public void generateRandomPosAndVec()
    {
        velocity =  new double[Configuration.instance.numberOfItems];

        char[] pos =  new char[Configuration.instance.numberOfItems];

        for(int i=0;i<velocity.length;i++)
        {
            velocity[i] = Configuration.instance.VMIN + Configuration.instance.randomGenerator.nextDouble() * Configuration.instance.VMAX;

            if(Configuration.instance.randomGenerator.nextDouble()<sigmoid_function(velocity[i]))
            {
                pos[i] = '1';
            }
            else
                {
                    pos[i] = '0';
                }


        }

        this.Position = String.valueOf(pos);

        this.best_Position = this.Position;

    }


    public void update_best_position()
    {
        if(ObjectiveValue(this.Position,Knapsack)>ObjectiveValue(this.best_Position,Knapsack))
        {
            this.best_Position = this.Position;
        }
    }

    public int convertCharToInt(char c)
    {
        int i = c - '0';

        return i;
    }

    public void computeNewPosition()
    {
        // update velocity

        for(int i=0;i<velocity.length;i++)
        {
             double r1 = Configuration.instance.randomGenerator.nextDouble();
             double r2 = Configuration.instance.randomGenerator.nextDouble();

            //velocity[i] = velocity[i] + ((Configuration.instance.cognitiveFactor * r1) * (ObjectiveValue(this.best_Position,Knapsack)-ObjectiveValue(this.Position,Knapsack))) +( (Configuration.instance.socialFactor * r2)* (ObjectiveValue(this.swarm.getBest_global_Position(),Knapsack)-ObjectiveValue(this.Position,Knapsack)));

            velocity[i] = Configuration.instance.w * velocity[i] + Configuration.instance.cognitiveFactor * r1 * (convertCharToInt(this.best_Position.charAt(i)) - convertCharToInt(this.Position.charAt(i))) + Configuration.instance.socialFactor * r2 * (convertCharToInt(this.swarm.getBest_global_Position().charAt(i)) - convertCharToInt(this.Position.charAt(i)));

            if(velocity[i]> Configuration.instance.VMAX)
            {
                velocity[i] = Configuration.instance.VMAX;
            }

            else if(velocity[i] < Configuration.instance.VMIN)
            {
                velocity[i] = Configuration.instance.VMIN;
            }
        }


        // update position

        char[] char_position =  new char[this.Position.length()];

        for(int i=0;i<char_position.length;i++)
        {
            if(Configuration.instance.randomGenerator.nextDouble()<sigmoid_function(velocity[i]))
            {
                char_position[i] = '1';
            }
            else
                {
                    char_position[i] = '0';
                }


        }


        this.Position = String.valueOf(char_position);





    }


    public double sigmoid_function(double i)
    {
        double result = 0.0;
        result = 1/ (1 + Math.exp(-i));
        return  result;

    }






   public static  int ObjectiveValue(String particle_position, ArrayList<KnapsackItem> Knapsack)
   {

       int fitValue =0;
       int weightValue =0;

       for(int i=0;i<particle_position.length();i++)
       {
           if(particle_position.charAt(i)=='1')
           {
               fitValue = fitValue + Knapsack.get(i).getValue();
               weightValue = weightValue + Knapsack.get(i).getWeight();
           }
       }

       //System.out.println(weightValue);



       if(weightValue> Configuration.instance.maximumCapacity)
       {
           return 0;
       }
       else
       {
           return fitValue;
       }


   }

   public static String generateRandomPosition()
   {

       char charArray[] = new char[Configuration.instance.numberOfItems];

       for(int i=0;i<charArray.length;i++)
       {
           if(Configuration.instance.randomGenerator.nextFloat()>0.8)
           {
               charArray[i]='1';
           }
           else
           {
               charArray[i]='0';
           }




       }

       return  String.valueOf(charArray);


   }

   public static double[] generateRandomVelocity()
   {
       double[] velocity = new double[Configuration.instance.numberOfItems];

       for(int i=0;i<velocity.length;i++)
       {

           //r.nextInt((max - min) + 1) + min;


          // velocity[i] = (double) Configuration.instance.randomGenerator.nextInt(((int)Configuration.instance.VMAX-(int)Configuration.instance.VMIN)+1) + (int)Configuration.instance.VMIN;

           velocity[i] = Configuration.instance.randomGenerator.nextDouble() * Configuration.instance.VMAX;

       }


       return  velocity;

   }

    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getBest_Position() {
        return best_Position;
    }

    public void setBest_Position(String best_Position) {
        this.best_Position = best_Position;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public Swarm getSwarm() {
        return swarm;
    }

    public void setSwarm(Swarm swarm) {
        this.swarm = swarm;
    }
}
