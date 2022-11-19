package Entities;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataPoint {

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private double value;
    private LocalDateTime date;

    public DataPoint(String date, double value) throws ParseException {
        LocalDateTime d = formatDate(date);
        this.value = value;
        this.date = d;
    }

    //overload constructor for creating Entities.DataPoint at current date time
    public DataPoint(double value) {
        LocalDateTime date = formatDate(LocalDateTime.now().toString());
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        //returns the value of the Entities.DataPoint
        return this.value;
    }

    public LocalDateTime getDate() {
        //returns the date of the Entities.DataPoint
        return this.date;
    }
    private LocalDateTime formatDate(String date) {
        return LocalDateTime.parse(date, FORMAT);
    }
}