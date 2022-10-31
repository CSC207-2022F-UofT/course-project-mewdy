package Entities;

import java.util.Date;

public class DataPoint {

    private double value;
    private Date date;
    private String metricName;
    private String textEntry;

    public DataPoint(double value, Date date, String metricName, String textEntry) {
        this.value = value;
        this.date = date;
        this.metricName = metricName;
        this.textEntry = textEntry;
    }

    //overload constructor for creating Entities.DataPoint at current date time
    public DataPoint(double value, String metricName, String textEntry) {
        this.value = value;
        this.date = new Date();
        this.metricName = metricName;
        this.textEntry = textEntry;
    }

    public double getValue() {
        //returns the value of the Entities.DataPoint
        return this.value;
    }

    public Date getDate() {
        //returns the date of the Entities.DataPoint
        return this.date;
    }

    public String getMetricName() {
        //returns the name of the Entities.Metric containing this dataPoint
        return this.metricName;
    }

    public String getTextEntry() {
        //returns the textEntry of this Entities.DataPoint
        return this.textEntry;
    }
}