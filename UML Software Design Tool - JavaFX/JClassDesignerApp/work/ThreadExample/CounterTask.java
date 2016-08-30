package ThreadExample;
import javafx.application.Platform;
import javafx.concurrent.Task;
public class CounterTask extends Task implements Drink {
    private  ThreadExample app;
    private  int counter;
    public   CounterTask(ThreadExample initApp){
    }
    protected  Void call(){
    return null;
    }
    public  void handleDrink(){
    }
}