package models;

/**
 * Class that represents the ResponseModel for EntryUndo
 */
public class EntryUndoResponseModel {
    private String information;
    private double value;
    private String date;
    private String metric;

    /**
     * Constructor for EntryUndoResponseModel
     *
     * @param value represents the value of the entry
     * @param date represents the date of the entry
     * @param metric represents the metric of the entry
     */
    public EntryUndoResponseModel(double value, String date, String metric){
        this.value = value;
        this.date = date;
        this.metric = metric;
    }

    /**
     * ??????????
     *
     * @param information rerpresents ??????????
     */
    public EntryUndoResponseModel(String information){
        this.information = information;
    }

    /**
     * Getter for value
     *
     * @return the value of the entry
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Getter for date
     *
     * @return the date of the entry
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Getter for metric
     *
     * @return the metric of the entry
     */
    public String getMetric() {
        return metric;
    }
}
