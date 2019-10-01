package main;

/*
/Will represent a item in a knapsack
 */

public class KnapsackItem
{
    private int index;
    private int weight;
    private int value;

    public KnapsackItem(int index,int weight,int value)
    {
        this.index = index;
        this.weight = weight;
        this.value = value;

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
