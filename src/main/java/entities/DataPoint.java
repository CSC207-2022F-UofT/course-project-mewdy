package entities;

<<<<<<< HEAD
import java.text.ParseException;
=======
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataPoint {

    private final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
<<<<<<< HEAD
    private double value;
    private LocalDateTime date;

    public DataPoint(String date, double value) throws ParseException {
        LocalDateTime d = formatDate(date);
        this.value = value;
        this.date = d;
=======
    private final double VALUE;
    private final LocalDateTime DATE;

    public DataPoint(String date, double value) {
        LocalDateTime d = formatDate(date);
        this.VALUE = value;
        this.DATE = d;
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
    }

    //overload constructor for creating Entities.DataPoint at current date time
    public DataPoint(double value) {
<<<<<<< HEAD
        LocalDateTime date = formatDate(LocalDateTime.now().toString());
        this.value = value;
        this.date = date;
=======
        LocalDateTime date = formatDate(LocalDateTime.now().format(FORMAT));
        this.VALUE = value;
        this.DATE = date;
>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
    }

    public double getValue() {
        //returns the value of the Entities.DataPoint
<<<<<<< HEAD
        return this.value;
    }

    public LocalDateTime getDate() {
        //returns the date of the Entities.DataPoint
        return this.date;
    }
=======
        return this.VALUE;
    }

    public String getDate() {
        //returns date in string from
        return this.DATE.format(FORMAT);
    }

    public boolean equals(DataPoint dp){
        return this.DATE.format(FORMAT).equals(dp.getDate()) && this.VALUE == dp.getValue();
    }

>>>>>>> 1a7e3b6895d936eee5e68cf04e9c29d52db1081f
    private LocalDateTime formatDate(String date) {
        return LocalDateTime.parse(date, FORMAT);
    }
}