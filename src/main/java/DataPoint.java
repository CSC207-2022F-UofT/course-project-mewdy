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
        return this.value;
    }

    public Date getDate() {
        return this.date;
    }

    public String getMetricName() {
        return this.metricName;
    }

    public String getTextEntry() {
        return this.textEntry;
    }
}
