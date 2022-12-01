package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents the entity Metric
 */
public class Metric {
    private final String NAME;
    private final ArrayList<DataPoint> DATAPOINTS;
    private final double UPPOERBOUND;
    private final double LOWERBOUND;


    /**
     * Constructor for the Metric class
     *
     * @param name represents the name of the metric
     * @param dataPointList represents the list of data points
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     */
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

    /**
     * Overloaded constructor for the Metric class
     *
     * @param name represents the name of the metric
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     */
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

    /**
     * getDataPointList returns the list of data points
     *
     * @return an ArrayList of DataPoints
     */
    public ArrayList<DataPoint> getDataPoints() {
        return this.DATAPOINTS;
    }

    /**
     * preexistingDataPoint checks if a data point already exists
     *
     * @param date represents the date of the data point
     * @return a boolean value that indicates whether the data point exists
     */
    public boolean preexistingDataPoint(LocalDateTime date) {
        //checks every DataPoint in this Metric to see if there already exists a DataPoint with this Date
        for (DataPoint point : this.DATAPOINTS) {
            if (point.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * addDataPoint adds a data point to the list of data points
     *
     * @param entry represents the entry of the data point
     */
    public void addDataPoint(DataPoint entry) {
        this.DATAPOINTS.add(entry);
    }

    /**
     * popDataPoint removes the most recent data point from the list of data points
     */
    public void popDataPoint() {
        //this is a method for removing the most recently added Entities.DataPoint from the Entities.Metric
        this.DATAPOINTS.remove(DATAPOINTS.size() - 1);
    }

    /**
     * getName returns the name of the metric
     *
     * @return the name of the metric
     */
    public String getName() {
        //this is a getter method for the name variable from this Entities.Metric
        return this.NAME;
    }

    /**
     * getUpperBound returns the upper bound of the metric
     *
     * @return the upper bound of the metric
     */
    public double getUpperBound() {
        //this is a getter method for the upperBound variable from this Entities.Metric
        return this.UPPOERBOUND;
    }

    /**
     * getLowerBound returns the lower bound of the metric
     * @return the lower bound of the metric
     */
    public double getLowerBound() {
        //this is a getter method for the lowerBound variable from this Entities.Metric
        return this.LOWERBOUND;
    }

    /**
     * equals checks if two metrics are equal
     *
     * @param metric represents the metric to be compared
     * @return a boolean value that indicates whether the two metrics are equal
     */
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