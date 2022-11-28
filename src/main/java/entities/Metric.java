package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Metric {
    private final String NAME;
    private final ArrayList<DataPoint> DATAPOINTS;
    private final double UPPOERBOUND;
    private final double LOWERBOUND;


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

        this.NAME = name;
        this.DATAPOINTS = dataPointList;
        this.UPPOERBOUND = upperBound;
        this.LOWERBOUND = lowerBound;
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
        this.NAME = name;
        this.DATAPOINTS = new ArrayList<>();
        this.UPPOERBOUND = upperBound;
        this.LOWERBOUND = lowerBound;
    }

    public ArrayList<DataPoint> getDataPoints() {
        //this is a method for getting the list of DataPoints from an Entities.Metric
        return this.DATAPOINTS;
    }

    public boolean preexistingDataPoint(LocalDateTime date) {
        //checks every DataPoint in this Metric to see if there already exists a DataPoint with this Date
        for (DataPoint point : this.DATAPOINTS) {
            if (point.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public void addDataPoint(DataPoint entry) {
        //this is a method for adding an Entities.DataPoint to the end of an Entities.Metric's dataPointList
        this.DATAPOINTS.add(entry);
    }

    public void popDataPoint() {
        //this is a method for removing the most recently added Entities.DataPoint from the Entities.Metric
        this.DATAPOINTS.remove(DATAPOINTS.size() - 1);
    }

    public String getName() {
        //this is a getter method for the name variable from this Entities.Metric
        return this.NAME;
    }

    public double getUpperBound() {
        //this is a getter method for the upperBound variable from this Entities.Metric
        return this.UPPOERBOUND;
    }

    public double getLowerBound() {
        //this is a getter method for the lowerBound variable from this Entities.Metric
        return this.LOWERBOUND;
    }

    public boolean equals(Metric metric) {
        if (Objects.equals(metric.getName(), this.NAME)
                && metric.getLowerBound() == this.LOWERBOUND
                && metric.getUpperBound() == this.UPPOERBOUND) {
            for (int i = 0; i < this.DATAPOINTS.size(); i++) {
                if (!metric.getDataPoints().get(i).equals(this.DATAPOINTS.get(i))) return false;
            }
            return true;
        }
        return false;
    }
}