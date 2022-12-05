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
    private final double UPPERBOUND;
    private final double LOWERBOUND;
    private double goal;
    private boolean hasGoal;


    /**
     * Constructor for the Metric class for import use case
     *
     * @param name represents the name of the metric
     * @param dataPointList represents the list of data points
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     * @param hasGoal 1 if metric has goal (true), 0 if metric does not have goal (false)
     * @param goal represents goal of metric
     */
    public Metric(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound,
                  int hasGoal, double goal) {
        if (name.equalsIgnoreCase("sleep")) {
            upperBound = 24;
            lowerBound = 0;
        } else if (name.equalsIgnoreCase("mood")) {
            upperBound = 10;
            lowerBound = 0;
        }

        this.NAME = name;
        this.DATAPOINTS = dataPointList;
        this.UPPERBOUND = upperBound;
        this.LOWERBOUND = lowerBound;
        if (hasGoal == 1) {
            this.setGoal(goal);
        }
    }

    /**
     * Overloaded Constructor for the Metric class
     *
     * @param name represents the name of the metric
     * @param dataPointList represents the list of data points
     * @param upperBound represents the upper bound of the metric
     * @param lowerBound represents the lower bound of the metric
     */
    public Metric(String name, ArrayList<DataPoint> dataPointList, double upperBound, double lowerBound) {
        if (name.equalsIgnoreCase("sleep")) {
            upperBound = 24;
            lowerBound = 0;
        } else if (name.equalsIgnoreCase("mood")) {
            upperBound = 10;
            lowerBound = 0;
        }

        this.NAME = name;
        this.DATAPOINTS = dataPointList;
        this.UPPERBOUND = upperBound;
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
        if (name.equalsIgnoreCase("sleep")) {
            upperBound = 24;
            lowerBound = 0;
        } else if (name.equalsIgnoreCase("mood")) {
            upperBound = 10;
            lowerBound = 0;
        }
        this.NAME = name;
        this.DATAPOINTS = new ArrayList<>();
        this.UPPERBOUND = upperBound;
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
    // preexistingDataPoint is never used? Should we remove this method??
    //ASDASDASDASDASDASDASDASDASDASDASDASDASDASD
    public boolean preexistingDataPoint(LocalDateTime date) {
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
        this.DATAPOINTS.remove(DATAPOINTS.size() - 1);
    }

    /**
     * getName returns the name of the metric
     *
     * @return the name of the metric
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * getUpperBound returns the upper bound of the metric
     *
     * @return the upper bound of the metric
     */
    public double getUpperBound() {
        return this.UPPERBOUND;
    }

    /**
     * getLowerBound returns the lower bound of the metric
     *
     * @return the lower bound of the metric
     */
    public double getLowerBound() {
        return this.LOWERBOUND;
    }

    public double getGoal(){ return this.goal; }

    public void setGoal(double goal){
        this.goal = goal;
        this.hasGoal = true;
    }

    public boolean getGoalStatus() { return this.hasGoal; }

    /**
     * equals checks if two metrics are equal
     *
     * @param metric represents the metric to be compared
     * @return a boolean value that indicates whether the two metrics are equal
     */
    public boolean equals(Metric metric) {
        if (Objects.equals(metric.getName(), this.NAME)
                && metric.getLowerBound() == this.LOWERBOUND
                && metric.getUpperBound() == this.UPPERBOUND) {
            for (int i = 0; i < this.DATAPOINTS.size(); i++) {
                if (!metric.getDataPoints().get(i).equals(this.DATAPOINTS.get(i))) return false;
            }
            return true;
        }
        return false;
    }
}
