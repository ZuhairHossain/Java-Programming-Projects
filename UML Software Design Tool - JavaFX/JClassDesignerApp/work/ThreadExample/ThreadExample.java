package ThreadExample;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
public class ThreadExample extends Application {
    public static final String START_TEXT= null;
    public static final String PAUSE_TEXT= null;
    private  Stage window;
    private  BorderPane appPane;
    private  FlowPane topPane;
    private  Button startButton;
    private  Button pauseButton;
    private  ScrollPane scrollPane;
    private  TextArea textArea;
    private  Thread dateThread;
    private  Task dateTask;
    private  Thread counterThread;
    private  Task counterTask;
    private  boolean work;
    public  void start(Stage primaryStage){
    }
    public  void startWork(){
    }
    public  void pauseWork(){
    }
    public  boolean doWork(){
    return false;
    }
    public  void appendText(String textToAppend){
    }
    public  void sleep(int timeToSleep){
    }
    private  void initLayout(){
    }
    private  void initHandlers(){
    }
    private  void initWindow(Stage initPrimaryStage){
    }
    public  void initThreads(){
    }
    public static void main(String[] args){
    }
}