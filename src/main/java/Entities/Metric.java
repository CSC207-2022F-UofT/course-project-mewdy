package Entities;

import java.util.ArrayList;
import java.util.Date;

public class Metric {

    private String name;
    private ArrayList<DataPoint> dataPointList;
    private double upperBound;
    private double lowerBound;

    public Metric(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound) {
        //this is an Entities.Metric constructor that takes in an ArrayList of DataPoints in addition to the other
        //instance variables
        if (name.equalsIgnoreCase("sleep")) {
            upperBound = 24;
            lowerBound = 0;
        } else if (name.equalsIgnoreCase("mood")) {
            upperBound = 10;
            lowerBound = 0;
        }
        this.name = name;
        this.dataPointList = dataPointList;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public Metric(String name, double upperBound, double lowerBound) {
        //this is an overloaded Entities.Metric constructor for when we want to initialize a new empty Entities.Metric
        if (name.equalsIgnoreCase("sleep")) {
            upperBound = 24;
            lowerBound = 0;
        } else if (name.equalsIgnoreCase("mood")) {
            upperBound = 10;
            lowerBound = 0;
        }
        this.name = name;
        this.dataPointList = new ArrayList<DataPoint>();
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    public ArrayList<DataPoint> getDataPoints() {
        //this is a method for getting the list of DataPoints from an Entities.Metric
        return this.dataPointList;
    }

    public boolean preexistingDataPoint(Date date) {
        //checks every DataPoint in this Metric to see if there already exists a DataPoint with this Date
        for (DataPoint point:this.dataPointList) {
            if (point.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public void addDataPoint(DataPoint entry) {
        //this is a method for adding an Entities.DataPoint to the end of an Entities.Metric's dataPointList
        this.dataPointList.add(entry);
    }
    public void popDataPoint() {
        //this is a method for removing the most recently added Entities.DataPoint from the Entities.Metric
        this.dataPointList.remove(dataPointList.size() - 1);
    }

    public String getName() {
        //this is a getter method for the name variable from this Entities.Metric
        return this.name;
    }

    public double getUpperBound() {
        //this is a getter method for the upperBound variable from this Entities.Metric
        return this.upperBound;
    }

    public double getLowerBound() {
        //this is a getter method for the lowerBound variable from this Entities.Metric
        return this.lowerBound;
    }
}