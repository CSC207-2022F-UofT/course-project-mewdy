import java.util.ArrayList;
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

    public double getValue() {
        //returns the value of the DataPoint
        return this.value;
    }

    public Date getDate() {
        //returns the date of the DataPoint
        return this.date;
    }

    public String getMetricName() {
        //returns the name of the Metric containing this dataPoint
        return this.metricName;
    }

    public String getTextEntry() {
        //returns the textEntry of this DataPoint
        return this.textEntry;
    }
}
