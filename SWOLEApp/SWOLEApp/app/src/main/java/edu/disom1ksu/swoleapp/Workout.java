package edu.disom1ksu.swoleapp;

import java.io.Serializable;

public class Workout implements Serializable
{
    private boolean workoutOngoing;
    private int weight;
    private int reps;
    private int sets;
    private int repsLeft;
    private int currentSet;
    private int totalReps;
    private float force = 0;
    public Workout  (int w, int r, int s)
    {
        totalReps = 0;
        currentSet = 1;
        repsLeft = r;
        workoutOngoing = true;
        weight = w;
        reps = r;
        sets = s;
    }

    public int getWeight() {
        return weight;
    }

    public float calculateForce(float maxA)
    {
       return force = (float)(weight *(1/2.205)) * maxA;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int r) {
        repsLeft += r - reps;
        this.reps = r;

    }

    public boolean isWorkoutOngoing()
    {
        return workoutOngoing;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int s) {
        currentSet += s - sets;
        this.sets = s;

    }

    public int getTotalReps(){return totalReps;}

    public int getRepsLeft(){
        return repsLeft;
    }

    public int getcurrentSet() {
        return currentSet;
    }

    public int repComplete()
    {
        totalReps++;
        if(--repsLeft <= 0)
        {
            if(setComplete() <= sets && workoutOngoing)
            {
                repsLeft = reps;
            }
            else
            {
                repsLeft = 0;
            }

        }
        return repsLeft;

    }

    public int setComplete() {
        if(++currentSet > sets)
        {
            endWorkout();
        }
        return currentSet;
    }

    public void endWorkout()
    {
        workoutOngoing = false;

    }

}
