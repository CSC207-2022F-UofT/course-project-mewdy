package presenters;

public class MetricDeleterFailed extends RuntimeException {
    public MetricDeleterFailed(String error) {
        super(error);
    }
}
