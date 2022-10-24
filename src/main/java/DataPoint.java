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
}
