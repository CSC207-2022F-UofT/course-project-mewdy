package entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents the entity Metric
 */
public class Metric {
    private final String name;
    private final ArrayList<DataPoint> dataPoints;
    private final double upperbound;
    private final double lowerbound;
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

        this.name = name;
        this.dataPoints = dataPointList;
        this.upperbound = upperBound;
        this.lowerbound = lowerBound;
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

        this.name = name;
        this.dataPoints = dataPointList;
        this.upperbound = upperBound;
        this.lowerbound = lowerBound;
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
        this.name = name;
        this.dataPoints = new ArrayList<>();
        this.upperbound = upperBound;
        this.lowerbound = lowerBound;
    }

    /**
     * getDataPointList returns the list of data points
     *
     * @return an ArrayList of DataPoints
     */
    public ArrayList<DataPoint> getDataPoints() {
        return this.dataPoints;
    }

    /**
     * addDataPoint adds a data point to the list of data points
     *
     * @param entry represents the entry of the data point
     */
    public void addDataPoint(DataPoint entry) {
        this.dataPoints.add(entry);
    }

    /**
     * popDataPoint removes the most recent data point from the list of data points
     */
    public void popDataPoint() {
        this.dataPoints.remove(dataPoints.size() - 1);
    }

    /**
     * getName returns the name of the metric
     *
     * @return the name of the metric
     */
    public String getName() {
        return this.name;
    }

    /**
     * getUpperBound returns the upper bound of the metric
     *
     * @return the upper bound of the metric
     */
    public double getUpperBound() {
        return this.upperbound;
    }

    /**
     * getLowerBound returns the lower bound of the metric
     *
     * @return the lower bound of the metric
     */
    public double getLowerBound() {
        return this.lowerbound;
    }

    /**
     * @return the goal associated with this metric.
     */
    public double getGoal(){ return this.goal; }

    /**
     * Setter method for goal tracking
     * @param goal is goal value to associated with this metric
     */
    public void setGoal(double goal){
        this.goal = goal;
        this.hasGoal = true;
    }

    /**
     * @return true iff this metric has a goal
     */
    public boolean getGoalStatus() { return this.hasGoal; }

    /**
     * equals checks if two metrics are equal
     *
     * @param metric represents the metric to be compared
     * @return a boolean value that indicates whether the two metrics are equal
     */
    public boolean equals(Metric metric) {
        if (Objects.equals(metric.getName(), this.name)
                && metric.getLowerBound() == this.lowerbound
                && metric.getUpperBound() == this.upperbound) {
            for (int i = 0; i < this.dataPoints.size(); i++) {
                if (!metric.getDataPoints().get(i).equals(this.dataPoints.get(i))) return false;
            }
            return true;
        }
        return false;
    }
}
