package algorithm.pso.base;

import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class Swarm
{
    public ArrayList<KnapsackItem> Knapsack;
    private String best_global_Position=null;
    private Particle[] particles;


    public Swarm(ArrayList<KnapsackItem> Knapsack)
    {
        this.Knapsack = Knapsack;

        particles = new Particle[Configuration.instance.numberOfParticles];

        for(int i=0;i<particles.length;i++)
        {
            particles[i] =  new Particle(this.Knapsack,this);
            update_best_Position(particles[i].getPosition());
        }



    }


    public void update()
    {
       for(int i=0;i<particles.length;i++)
       {
           update_best_Position(particles[i].getBest_Position());
       }
    }


    public void execute()
    {

        //Update best local pos

        for(int i=0;i<particles.length;i++)
        {
            particles[i].update_best_position();
            update_best_Position(particles[i].getPosition());

        }

        //Update best global pos



        // update postion

        for(int i=0;i<particles.length;i++)
        {
            particles[i].computeNewPosition();

        }




    }





    public void update_best_Position(String position)
    {
        if(this.best_global_Position==null)
        {
            this.best_global_Position =  position;

        }
        else
            {
                if(Particle.ObjectiveValue(position,this.Knapsack)>Particle.ObjectiveValue(this.best_global_Position,this.Knapsack))
                {
                    this.best_global_Position = position;
                    System.out.println(Particle.ObjectiveValue(position,this.Knapsack));
                }
            }

    }



    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }

    public String getBest_global_Position() {
        return best_global_Position;
    }

    public void setBest_global_Position(String best_global_Position) {
        this.best_global_Position = best_global_Position;
    }

    public Particle[] getParticles() {
        return particles;
    }

    public void setParticles(Particle[] particles) {
        this.particles = particles;
    }

    public int getOptimalValue()
    {
        return Particle.ObjectiveValue(this.best_global_Position,this.Knapsack);
    }
}
