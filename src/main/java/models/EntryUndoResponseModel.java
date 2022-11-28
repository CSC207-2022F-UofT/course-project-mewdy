package models;

public class EntryUndoResponseModel {
    private String information;
    private double value;
    private String date;
    private String metric;

    public EntryUndoResponseModel(double value, String date, String metric) {
        this.value = value;
        this.date = date;
        this.metric = metric;
    }

    public EntryUndoResponseModel(String information) {
        this.information = information;
    }


    public double getValue() {
        return this.value;
    }

    public String getDate() {
        return this.date;
    }

    public String getMetric() {
        return metric;
    }
}
