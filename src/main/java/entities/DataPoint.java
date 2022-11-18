package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataPoint {

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final double VALUE;
    private final LocalDateTime DATE;

    public DataPoint(String date, double value) {
        LocalDateTime d = formatDate(date);
        this.VALUE = value;
        this.DATE = d;
    }

    //overload constructor for creating Entities.DataPoint at current date time
    public DataPoint(double value) {
        LocalDateTime date = formatDate(LocalDateTime.now().format(FORMAT));
        this.VALUE = value;
        this.DATE = date;
    }

    public double getValue() {
        //returns the value of the Entities.DataPoint
        return this.VALUE;
    }

    public String getDate() {
        //returns date in string from
        return this.DATE.format(FORMAT);
    }

    public boolean equals(DataPoint dp){
        return this.DATE.format(FORMAT).equals(dp.getDate()) && this.VALUE == dp.getValue();
    }

    private LocalDateTime formatDate(String date) {
        return LocalDateTime.parse(date, FORMAT);
    }
}