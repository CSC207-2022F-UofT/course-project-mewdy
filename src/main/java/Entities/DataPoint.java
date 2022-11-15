package Entities;

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

    public LocalDateTime getDate() {
        //returns the date of the Entities.DataPoint
        return this.DATE;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof DataPoint)) return false;

        DataPoint dp = (DataPoint) o;
        return this.DATE == dp.getDate() && this.VALUE == dp.getValue();
    }

    private LocalDateTime formatDate(String date) {
        return LocalDateTime.parse(date, FORMAT);
    }
}