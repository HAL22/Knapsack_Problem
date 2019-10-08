package algorithm.aco.base;

import algorithm.ga.base.Chromosome;
import main.Configuration;
import main.KnapsackItem;

import java.util.ArrayList;

public class Ant implements Comparable<Ant>
{
    private AntColony antColony;
    private int size;
    private int weightOfsack;
    private int valueOfsack;
    private ArrayList<KnapsackItem> Knapsack;
    private ArrayList<KnapsackItem> Sack;
    private ArrayList<KnapsackItem> NotYetVisited;

    public Ant(ArrayList<KnapsackItem>Knapsack,int size,AntColony antColony)
    {
        this.Knapsack = Knapsack;
        this.size = size;
        this.antColony = antColony;

        this.weightOfsack =0;
        this.valueOfsack = 0;
        this.Sack =null;
        this.NotYetVisited = null;
    }

    public void newRound()
    {
        this.weightOfsack =0;
        this.valueOfsack = 0;

        this.Sack = new ArrayList<>();

        this.NotYetVisited = new ArrayList<>();

        for(KnapsackItem k:Knapsack)
        {
            this.NotYetVisited.add(new KnapsackItem(k.getIndex(),k.getWeight(),k.getValue()));
        }

    }

    public void addtoSack(KnapsackItem k)
    {
        this.Sack.add(k);

        this.valueOfsack = this.valueOfsack+k.getValue();
        this.weightOfsack = this.weightOfsack+ k.getWeight();
    }

    public void layPheromone(int bestAnt)
    {
        for(KnapsackItem k:Sack)
        {
            antColony.getPheromoneVector()[k.getIndex()-1] = antColony.getPheromoneVector()[k.getIndex()-1] + (double) 1/1+(bestAnt-((double)getValueOfsack()/bestAnt));
            //antColony.getPheromoneVector()[k.getIndex()-1] = antColony.getPheromoneVector()[k.getIndex()-1] + 1;
        }

    }


    public void lookForWay()
    {
        KnapsackItem randomItem = NotYetVisited.get(Configuration.instance.randomGenerator.nextInt(size));

       addtoSack(randomItem);

        for(int i=0;i<NotYetVisited.size();i++)
        {
            if(randomItem.getIndex()==NotYetVisited.get(i).getIndex())
            {
                NotYetVisited.remove(NotYetVisited.get(i));
                break;

            }
        }

        while(  weightOfsack<Configuration.instance.maximumCapacity)
        {




            if(NotYetVisited.size()>0)
            {

                KnapsackItem item = RouletteWheel.selectKnapsackItem(antColony.getPheromoneVector(),NotYetVisited);



                if(item.getWeight()+weightOfsack<Configuration.instance.maximumCapacity)
                {
                    addtoSack(item);

                    for(int i=0;i<NotYetVisited.size();i++)
                    {
                        if(item.getIndex()==NotYetVisited.get(i).getIndex())
                        {
                            NotYetVisited.remove(NotYetVisited.get(i));
                            break;

                        }
                    }


                }
                else
                {
                    for(int i=0;i<NotYetVisited.size();i++)
                    {
                        if(item.getIndex()==NotYetVisited.get(i).getIndex())
                        {
                            NotYetVisited.remove(NotYetVisited.get(i));
                            break;

                        }
                    }


                }


            }
            else
                {

                    break;
                }
































        }

    }





    public AntColony getAntColony() {
        return antColony;
    }

    public void setAntColony(AntColony antColony) {
        this.antColony = antColony;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeightOfsack() {
        return weightOfsack;
    }

    public void setWeightOfsack(int weightOfsack) {
        this.weightOfsack = weightOfsack;
    }

    public int getValueOfsack() {
        return valueOfsack;
    }

    public void setValueOfsack(int valueOfsack) {
        this.valueOfsack = valueOfsack;
    }

    public ArrayList<KnapsackItem> getKnapsack() {
        return Knapsack;
    }

    public void setKnapsack(ArrayList<KnapsackItem> knapsack) {
        Knapsack = knapsack;
    }

    public ArrayList<KnapsackItem> getSack() {
        return Sack;
    }

    public void setSack(ArrayList<KnapsackItem> sack) {
        Sack = sack;
    }

    public ArrayList<KnapsackItem> getNotYetVisited() {
        return NotYetVisited;
    }

    public void setNotYetVisited(ArrayList<KnapsackItem> notYetVisited) {
        NotYetVisited = notYetVisited;
    }

    public int compareTo(Ant ant) {
        if (valueOfsack < ant.getValueOfsack())
            return -1;

        if (valueOfsack > ant.getValueOfsack())
            return 1;

        return 0;
    }
}
