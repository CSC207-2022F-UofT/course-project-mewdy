package presenters;

public class DataLogFailed extends RuntimeException{
    public DataLogFailed(String error){
        super(error);
    }
}
