package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents the DataPoint entity.
 */
public class DataPoint {

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final double VALUE;
    private final LocalDateTime DATE;

    /**
     * Constructor for the DataPoint class.
     *
     * @param date represents the date of the data point.
     * @param value represents the value of the data point.
     */
    public DataPoint(String date, double value) {
        LocalDateTime d = formatDate(date);
        this.VALUE = value;
        this.DATE = d;
    }


    /**
     * Overloaded constructor for the DataPoint class.
     *
     * @param value represents the value of the data point.
     */
    public DataPoint(double value) {
        LocalDateTime date = formatDate(LocalDateTime.now().format(FORMAT));
        this.VALUE = value;
        this.DATE = date;
    }

    /**
     * getValue returns the value of the data point.
     *
     * @return the value of the data point.
     */
    public double getValue() {
        return this.VALUE;
    }

    /**
     * getDate returns the date of the data point.
     *
     * @return the date of the data point.
     */
    public String getDate() {
        return this.DATE.format(FORMAT);
    }

    /**
     * formatDate formats the date of the data point.
     *
     * @param dp represents the data point to be compared.
     * @return a boolean value representing whether the data point is equal to the data point being compared.
     */
    public boolean equals(DataPoint dp){
        return this.DATE.format(FORMAT).equals(dp.getDate()) && this.VALUE == dp.getValue();
    }

    /**
     * formatDate formats the date of the data point.
     *
     * @param date represents the date to be formatted.
     * @return the formatted date.
     */
    private LocalDateTime formatDate(String date) {
        return LocalDateTime.parse(date, FORMAT);
    }
}