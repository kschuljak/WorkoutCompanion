package com.techelevator.model.profile;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GoalDTO {

    @NotEmpty
    private String name;
    private int reps;
    private int weight;
    private double time;
    private int days;
    private String misc;
    private Date date;
    private boolean isCompleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}