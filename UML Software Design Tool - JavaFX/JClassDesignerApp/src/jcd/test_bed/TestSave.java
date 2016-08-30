/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.test_bed;

import java.io.IOException;
import javafx.scene.layout.Pane;
import jcd.JClassDesigner;
import jcd.data.DataManager;
import jcd.data.DraggableClass;
import jcd.data.DraggableInterface;
import jcd.data.DraggableLine;
import jcd.data.Method;
import jcd.file.FileManager;
import static jcd.data.Method.PUBLIC_TYPE;
import static jcd.data.Method.PRIVATE_TYPE;
import static jcd.data.Method.PROTECTED_TYPE;
import static jcd.data.Method.NO_TYPE; 
import static jcd.data.Method.STATIC_TYPE;
import static jcd.data.Method.ABSTRACT_TYPE;
import static jcd.data.Method.NONE;
import jcd.data.Variable;
import static jcd.data.Variable.STATIC_FINAL_TYPE;


/**
 *
 * @author DennisSosa
 */
public class TestSave {
    FileManager fm;
    DataManager dm;
    JClassDesigner jcd = new JClassDesigner();
    DraggableClass threadExample = new DraggableClass(false);
    DraggableClass application = new DraggableClass(true);
    DraggableClass counterTask = new DraggableClass(false);
    DraggableClass dateTask = new DraggableClass(false);
    DraggableClass pauseHandler = new DraggableClass(false);
    DraggableClass startHandler = new DraggableClass(false);
    DraggableInterface eventHandler = new DraggableInterface();
    
    DraggableClass extraAbstract = new DraggableClass(true);
    DraggableInterface extraInterface = new DraggableInterface();
            
    DraggableLine threadExampleInherit = new DraggableLine(1);
    DraggableLine pauseHandlerImplement = new DraggableLine(2);
    DraggableLine startHandlerImplement = new DraggableLine(2);
    DraggableLine extraInherit = new DraggableLine(1);
    DraggableLine extraImplement = new DraggableLine(2);
    
    
    public void hardTest1(){
        
        jcd.makeAppBuilderHook();
        fm = new FileManager();
        
        try{
            dm = new DataManager(jcd);
        } catch(Exception ex){
            
        }
        
        threadExample.addImport("import javafx.application.Application");
        threadExample.addImport("import javafx.concurrent.Task");
        threadExample.addImport("import javafx.geometry.Orientation");
        threadExample.addImport("import javafx.scene.Scene");
        threadExample.addImport("import javafx.scene.control.Button");
        threadExample.addImport("import javafx.scene.control.ScrollPane");
        threadExample.addImport("import javafx.scene.control.TextArea");
        threadExample.addImport("import javafx.scene.layout.BorderPane");
        threadExample.addImport("import javafx.scene.layout.FlowPane");
        threadExample.addImport("import javafx.stage.Stage");
        
        threadExample.setClassText("ThreadExample");
        threadExample.setClassName("ThreadExample");
        String tempVarText = "+$ START_TEXT: String" + "\n"+
                "+$ PAUSE_TEXT: String" + "\n"+
                "-window: Stage" + "\n"+
                "-appPane: BorderPane" + "\n"+
                "-topPane: FlowPane" + "\n"+
                "-startButton: Button" + "\n"+
                "-pauseButton: Button" + "\n"+
                "-scrollPane: ScrollPane" + "\n"+
                "-textArea: TextArea" + "\n"+
                "-dateThread: Thread" + "\n"+
                "-dateTask: Task" + "\n"+
                "-counterThread: Thread" + "\n"+
                "-counterTask: Task" + "\n"+
                "-work:boolean";
        
        threadExample.setVarText(tempVarText);
        
        String tempMethText = "+start(primaryStage: Stage): void" + "\n"+
                "+startWork(): void" + "\n"+
                "+pauseWork():void" + "\n"+
                "+doWork(): boolean" + "\n"+
                "+appendText(textToAppend: String): void" + "\n"+
                "+sleep(timeToSleep: int): void" + "\n"+
                "-initLayout(): void" + "\n"+
                "-initHandlers(): void" + "\n"+
                "-initWindow(initPrimaryStage: Stage): void" + "\n"+
                "-initThread(): void" + "\n"+
                "+$main(args: String[]): void";
        threadExample.setMethText(tempMethText);
        
        threadExample.setPackageName("ThreadExample");
        threadExample.setParentName("Application");
        
        threadExample.setLayoutX(100);
        threadExample.setLayoutY(200);
        
        threadExample.setAbstract(false);
        Variable v = new Variable("START_TEXT","String",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        threadExample.addVariable(v);
        v = new Variable("PAUSE_TEXT","String",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        threadExample.addVariable(v);
        v = new Variable("window","Stage",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("appPane","BorderPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("topPane","FlowPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("startButton","Button",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("pauseButton","Button",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("scrollPane","ScrollPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("textArea","TextArea",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("dateThread","Thread",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("dateTask","Task",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("counterThread","Thread",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("counterTask","Task",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("work","boolean",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        
        Method m = new Method("start", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("primaryStage", "Stage", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("startWork", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("pauseWork", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("doWork", "boolean", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("appendText", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("textToAppend", "String", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("sleep", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("timeToSleep", "int", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initLayout", "void", 0, PRIVATE_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initHandlers", "void", 0, PRIVATE_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initWindow", "void", 1, PRIVATE_TYPE, NONE);
        m.addParameter("initPrimaryStage", "Stage", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initThreads", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("main", "void", 1, PUBLIC_TYPE, STATIC_TYPE);
        m.addParameter("args", "String[]", NO_TYPE, NONE);
        threadExample.addMethod(m);
        
        threadExampleInherit.setParentName("Application");
        threadExampleInherit.setChildName("ThreadExample");
        threadExampleInherit.setStartX(100);
        threadExampleInherit.setStartY(50);
        threadExampleInherit.setEndX(400);
        threadExampleInherit.setEndY(300);
        threadExampleInherit.setSplitX(200);
        threadExampleInherit.setSplitY(250);
        
        application.addImport("import javafx.stage.Stage");
        application.setClassText("Application");
        application.setClassName("Application");
        tempVarText = "";
        
        application.setVarText(tempVarText);
        
        tempMethText = "{abstract}"+"\n"+"start(primaryStage: Stage): void";
        
        application.setMethText(tempMethText);
        
        application.setPackageName("ThreadExample");
        application.setParentName("");
        
        application.setLayoutX(300);
        application.setLayoutY(400);
        
        m = new Method("start", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("primaryStage", "Stage", NO_TYPE, NONE);
        application.addMethod(m);
        
        counterTask.addImport("import javafx.application.Platform");
        counterTask.addImport("import javafx.concurrent.Task");
        
        counterTask.setClassText("CounterTask");
        counterTask.setClassName("CounterTask");
        
        tempVarText = "-app: ThreadExample"+"\n"+
            "-counter: int"+"\n";
        
        counterTask.setVarText(tempVarText);
        
        tempMethText = "+CounterTask(initApp: ThreadExample)" +"\n"+
            "#call(): Void";
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        counterTask.addVariable(v);
        v = new Variable("counter","int",PRIVATE_TYPE,NONE);
        counterTask.addVariable(v);
        
        counterTask.setMethText(tempMethText);
        
        counterTask.setPackageName("ThreadExample");
        counterTask.setParentName("Task");
        counterTask.setInterfaceName("Drink");
        
        counterTask.setLayoutX(270);
        counterTask.setLayoutY(350);
        
        m = new Method("CounterTask", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        counterTask.addMethod(m);
        m = new Method("call", "Void", 0, PROTECTED_TYPE, NONE);
        counterTask.addMethod(m);
        m = new Method("handleDrink","void",0,PUBLIC_TYPE,NONE);
        counterTask.addMethod(m);
        
        
        extraImplement.setParentName("Drink");
        extraImplement.setChildName("CounterTask");
        extraImplement.setStartX(140);
        extraImplement.setStartY(500);
        extraImplement.setEndX(470);
        extraImplement.setEndY(350);
        extraImplement.setSplitX(150);
        extraImplement.setSplitY(300);
        
        extraInterface.setClassText("Drink");
        extraInterface.setInterfaceName("Drink");
        
        tempVarText ="";
        
        extraInterface.setVarText(tempVarText);
        
        tempMethText = "handleDrink(): void";
        
        
        extraInterface.setMethText(tempMethText);
        
        extraInterface.setPackageName("ThreadExample");
        
        extraInterface.setLayoutX(370);
        extraInterface.setLayoutY(450);
        
        
        m = new Method("handleDrink", "void", 0, PUBLIC_TYPE, NONE);
        extraInterface.addMethod(m);
        
        
        dateTask.addImport("import java.util.Date");
        dateTask.addImport("import javafx.application.Platform");
        dateTask.addImport("import javafx.concurrent.Task");
        dateTask.addImport("import ThreadExample.ThreadExample");
        
        dateTask.setClassText("DateTask");
        dateTask.setClassName("DateTask");
        
        tempVarText = "-app: ThreadExample"+"\n"+
            "-now: Date"+"\n";
        
        dateTask.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        dateTask.addVariable(v);
        v = new Variable("now","Date",PRIVATE_TYPE,NONE);
        dateTask.addVariable(v);
        
        tempMethText = "+DateTask(initApp: ThreadExample)" +"\n"+
            "#call(): Void";
        
        dateTask.setMethText(tempMethText);
        
        dateTask.setPackageName("ThreadExample");
        dateTask.setParentName("Task");
        
        dateTask.setLayoutX(270);
        dateTask.setLayoutY(350);
        
        m = new Method("DateTask", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        dateTask.addMethod(m);
        m = new Method("call", "Void", 0, PROTECTED_TYPE, NONE);
        dateTask.addMethod(m);
        
        
        pauseHandler.addImport("import javafx.event.Event");
        pauseHandler.addImport("import javafx.event.EventHandler");
        
        pauseHandler.setClassText("PauseHandler");
        pauseHandler.setClassName("PauseHandler");
        
        tempVarText = "-app: ThreadExample"+"\n";
        
        pauseHandler.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        pauseHandler.addVariable(v);

        
        tempMethText = "+PauseHandler(initApp: ThreadExample)" +"\n"+
            "+handle(event: Event): void";
        
        pauseHandler.setMethText(tempMethText);
        
        pauseHandler.setPackageName("ThreadExample");
        pauseHandler.setInterfaceName("EventHandler");
        pauseHandler.setParentName("PlatformEat");
        
        pauseHandler.setLayoutX(270);
        pauseHandler.setLayoutY(350);
        
        m = new Method("PauseHandler", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        pauseHandler.addMethod(m);
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        pauseHandler.addMethod(m);
        m = new Method("handleEat", "void", 0, PUBLIC_TYPE, NONE);
        pauseHandler.addMethod(m);
        
        
        pauseHandlerImplement.setParentName("EventHandler");
        pauseHandlerImplement.setChildName("PauseHandler");
        pauseHandlerImplement.setStartX(100);
        pauseHandlerImplement.setStartY(50);
        pauseHandlerImplement.setEndX(400);
        pauseHandlerImplement.setEndY(300);
        pauseHandlerImplement.setSplitX(200);
        pauseHandlerImplement.setSplitY(250);
        
        extraInherit.setParentName("PlatformEat");
        extraInherit.setChildName("PauseHandler");
        extraInherit.setStartX(140);
        extraInherit.setStartY(500);
        extraInherit.setEndX(470);
        extraInherit.setEndY(350);
        extraInherit.setSplitX(150);
        extraInherit.setSplitY(300);
        
        extraAbstract.setClassText("PlatformEat");
        extraAbstract.setClassName("PlatformEat");
        
        tempVarText ="-eat: int"+"\n";
        
        extraAbstract.setVarText(tempVarText);
        
        tempMethText = "+PlatformEat(initEat: int)" +"\n"+
            "{abstract} handleEat(): void";
        
        v = new Variable("eat","int",PRIVATE_TYPE,NONE);
        extraAbstract.addVariable(v);
        
        
        extraAbstract.setMethText(tempMethText);
        
        extraAbstract.setPackageName("ThreadExample");
        
        extraAbstract.setLayoutX(370);
        extraAbstract.setLayoutY(450);
        
        m = new Method("PlatformEat", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initEat", "int", NO_TYPE, NONE);
        extraAbstract.addMethod(m);
        m = new Method("handleEat", "void", 0, PUBLIC_TYPE, ABSTRACT_TYPE);
        extraAbstract.addMethod(m);
        
        startHandler.addImport("import javafx.event.Event");
        startHandler.addImport("import javafx.event.EventHandler");
        
        startHandler.setClassText("StartHandler");
        startHandler.setClassName("StartHandler");
        
        tempVarText = "-app: ThreadExample"+"\n";
        
        startHandler.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        startHandler.addVariable(v);
        
        tempMethText = "+StartHandler(initApp: ThreadExample)" +"\n"+
            "+handle(event: Event): void";
        
        startHandler.setMethText(tempMethText);
        
        startHandler.setPackageName("ThreadExample");
        startHandler.setInterfaceName("EventHandler");
        
        startHandler.setLayoutX(270);
        startHandler.setLayoutY(350);
        
        m = new Method("StartHandler", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        startHandler.addMethod(m);
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        startHandler.addMethod(m);
        
        startHandlerImplement.setParentName("EventHandler");
        startHandlerImplement.setChildName("StartHandler");
        startHandlerImplement.setStartX(170);
        startHandlerImplement.setStartY(70);
        startHandlerImplement.setEndX(450);
        startHandlerImplement.setEndY(360);
        startHandlerImplement.setSplitX(220);
        startHandlerImplement.setSplitY(280);
        
        eventHandler.setClassText("EventHandler");
        eventHandler.setInterfaceName("EventHandler");
        eventHandler.addImport("import javafx.event.Event");
        
        tempMethText = "+handle(event: Event): void"+"\n";
        
        eventHandler.setMethText(tempMethText);
        
        eventHandler.setPackageName("ThreadExample");
        
        eventHandler.setLayoutX(50);
        eventHandler.setLayoutY(50);
        
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        eventHandler.addMethod(m);
        
        Pane canvas = new Pane();
        canvas.getChildren().addAll(threadExample,application,counterTask,dateTask,pauseHandler,
        startHandler,eventHandler,threadExampleInherit,extraInherit,extraAbstract,extraImplement,pauseHandlerImplement,
        startHandlerImplement,extraInterface);
        
        dm.setCanvasDM(canvas);
        
        String s = System.getProperty("user.dir")+"/work/DesignSaveTest.json";
        
        try{
            fm.saveData(dm,s);
        } catch(IOException ex){
            
        }
    }
    
    public void hardTest2(){
        
        jcd.makeAppBuilderHook();
        fm = new FileManager();
        
        try{
            dm = new DataManager(jcd);
        } catch(Exception ex){
            
        }
        
        threadExample.addImport("import javafx.application.Application");
        threadExample.addImport("import javafx.concurrent.Task");
        threadExample.addImport("import javafx.geometry.Orientation");
        threadExample.addImport("import javafx.scene.Scene");
        threadExample.addImport("import javafx.scene.control.Button");
        threadExample.addImport("import javafx.scene.control.ScrollPane");
        threadExample.addImport("import javafx.scene.control.TextArea");
        threadExample.addImport("import javafx.scene.layout.BorderPane");
        threadExample.addImport("import javafx.scene.layout.FlowPane");
        threadExample.addImport("import javafx.stage.Stage");
        
        threadExample.setClassText("ThreadExample");
        threadExample.setClassName("ThreadExample");
        String tempVarText = "+$ START_TEXT: String" + "\n"+
                "+$ PAUSE_TEXT: String" + "\n"+
                "-window: Stage" + "\n"+
                "-appPane: BorderPane" + "\n"+
                "-topPane: FlowPane" + "\n"+
                "-startButton: Button" + "\n"+
                "-pauseButton: Button" + "\n"+
                "-scrollPane: ScrollPane" + "\n"+
                "-textArea: TextArea" + "\n"+
                "-dateThread: Thread" + "\n"+
                "-dateTask: Task" + "\n"+
                "-counterThread: Thread" + "\n"+
                "-counterTask: Task" + "\n"+
                "-work:boolean";
        
        threadExample.setVarText(tempVarText);
        
        String tempMethText = "+start(primaryStage: Stage): void" + "\n"+
                "+startWork(): void" + "\n"+
                "+pauseWork():void" + "\n"+
                "+doWork(): boolean" + "\n"+
                "+appendText(textToAppend: String): void" + "\n"+
                "+sleep(timeToSleep: int): void" + "\n"+
                "-initLayout(): void" + "\n"+
                "-initHandlers(): void" + "\n"+
                "-initWindow(initPrimaryStage: Stage): void" + "\n"+
                "-initThread(): void" + "\n"+
                "+$main(args: String[]): void";
        threadExample.setMethText(tempMethText);
        
        threadExample.setPackageName("ThreadExample");
        threadExample.setParentName("Application");
        
        threadExample.setLayoutX(100);
        threadExample.setLayoutY(200);
        
        threadExample.setAbstract(false);
        Variable v = new Variable("START_TEXT","String",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        threadExample.addVariable(v);
        v = new Variable("PAUSE_TEXT","String",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        threadExample.addVariable(v);
        v = new Variable("window","Stage",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("appPane","BorderPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("topPane","FlowPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("startButton","Button",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("pauseButton","Button",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("scrollPane","ScrollPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("textArea","TextArea",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("dateThread","Thread",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("dateTask","Task",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("counterThread","Thread",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("counterTask","Task",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("work","boolean",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        
        Method m = new Method("start", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("primaryStage", "Stage", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("startWork", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("pauseWork", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("doWork", "boolean", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("appendText", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("textToAppend", "String", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("sleep", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("timeToSleep", "int", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initLayout", "void", 0, PRIVATE_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initHandlers", "void", 0, PRIVATE_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initWindow", "void", 1, PRIVATE_TYPE, NONE);
        m.addParameter("initPrimaryStage", "Stage", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initThreads", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("main", "void", 1, PUBLIC_TYPE, STATIC_TYPE);
        m.addParameter("args", "String[]", NO_TYPE, NONE);
        threadExample.addMethod(m);
        
        threadExampleInherit.setParentName("Application");
        threadExampleInherit.setChildName("ThreadExample");
        threadExampleInherit.setStartX(100);
        threadExampleInherit.setStartY(50);
        threadExampleInherit.setEndX(400);
        threadExampleInherit.setEndY(300);
        threadExampleInherit.setSplitX(200);
        threadExampleInherit.setSplitY(250);
        
        application.addImport("import javafx.stage.Stage");
        application.setClassText("Application");
        application.setClassName("Application");
        tempVarText = "";
        
        application.setVarText(tempVarText);
        
        tempMethText = "{abstract}"+"\n"+"start(primaryStage: Stage): void";
        
        application.setMethText(tempMethText);
        
        application.setPackageName("ThreadExample");
        application.setParentName("");
        
        application.setLayoutX(300);
        application.setLayoutY(400);
        
        m = new Method("start", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("primaryStage", "Stage", NO_TYPE, NONE);
        application.addMethod(m);
        
        counterTask.addImport("import javafx.application.Platform");
        counterTask.addImport("import javafx.concurrent.Task");
        
        counterTask.setClassText("CounterTask");
        counterTask.setClassName("CounterTask");
        
        tempVarText = "-app: ThreadExample"+"\n"+
            "-counter: int"+"\n";
        
        counterTask.setVarText(tempVarText);
        
        tempMethText = "+CounterTask(initApp: ThreadExample)" +"\n"+
            "#call(): Void";
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        counterTask.addVariable(v);
        v = new Variable("counter","int",PRIVATE_TYPE,NONE);
        counterTask.addVariable(v);
        
        counterTask.setMethText(tempMethText);
        
        counterTask.setPackageName("ThreadExample");
        counterTask.setParentName("Task");
        
        counterTask.setLayoutX(270);
        counterTask.setLayoutY(350);
        
        m = new Method("CounterTask", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        counterTask.addMethod(m);
        m = new Method("call", "Void", 0, PROTECTED_TYPE, NONE);
        counterTask.addMethod(m);
        
        
        dateTask.addImport("import java.util.Date");
        dateTask.addImport("import javafx.application.Platform");
        dateTask.addImport("import javafx.concurrent.Task");
        
        dateTask.setClassText("DateTask");
        dateTask.setClassName("DateTask");
        
        tempVarText = "-app: ThreadExample"+"\n"+
            "-now: Date"+"\n";
        
        dateTask.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        dateTask.addVariable(v);
        v = new Variable("now","Date",PRIVATE_TYPE,NONE);
        dateTask.addVariable(v);
        
        tempMethText = "+DateTask(initApp: ThreadExample)" +"\n"+
            "#call(): Void";
        
        dateTask.setMethText(tempMethText);
        
        dateTask.setPackageName("ThreadExample");
        dateTask.setParentName("Task");
        
        dateTask.setLayoutX(270);
        dateTask.setLayoutY(350);
        
        m = new Method("DateTask", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        dateTask.addMethod(m);
        m = new Method("call", "Void", 0, PROTECTED_TYPE, NONE);
        dateTask.addMethod(m);
        
        
        pauseHandler.addImport("import javafx.event.Event");
        pauseHandler.addImport("import javafx.event.EventHandler");
        
        pauseHandler.setClassText("PauseHandler");
        pauseHandler.setClassName("PauseHandler");
        
        tempVarText = "-app: ThreadExample"+"\n";
        
        pauseHandler.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        pauseHandler.addVariable(v);

        
        tempMethText = "+PauseHandler(initApp: ThreadExample)" +"\n"+
            "+handle(event: Event): void";
        
        pauseHandler.setMethText(tempMethText);
        
        pauseHandler.setPackageName("ThreadExample");
        pauseHandler.setInterfaceName("EventHandler");
        
        pauseHandler.setLayoutX(270);
        pauseHandler.setLayoutY(350);
        
        m = new Method("PauseHandler", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        pauseHandler.addMethod(m);
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        pauseHandler.addMethod(m);
        
        pauseHandlerImplement.setParentName("EventHandler");
        pauseHandlerImplement.setChildName("PauseHandler");
        pauseHandlerImplement.setStartX(100);
        pauseHandlerImplement.setStartY(50);
        pauseHandlerImplement.setEndX(400);
        pauseHandlerImplement.setEndY(300);
        pauseHandlerImplement.setSplitX(200);
        pauseHandlerImplement.setSplitY(250);
        
        startHandler.addImport("import javafx.event.Event");
        startHandler.addImport("import javafx.event.EventHandler");
        
        startHandler.setClassText("StartHandler");
        startHandler.setClassName("StartHandler");
        
        tempVarText = "-app: ThreadExample"+"\n";
        
        startHandler.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        startHandler.addVariable(v);
        
        tempMethText = "+StartHandler(initApp: ThreadExample)" +"\n"+
            "+handle(event: Event): void";
        
        startHandler.setMethText(tempMethText);
        
        startHandler.setPackageName("ThreadExample");
        startHandler.setInterfaceName("EventHandler");
        
        startHandler.setLayoutX(270);
        startHandler.setLayoutY(350);
        
        m = new Method("StartHandler", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        startHandler.addMethod(m);
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        startHandler.addMethod(m);
        
        startHandlerImplement.setParentName("EventHandler");
        startHandlerImplement.setChildName("StartHandler");
        startHandlerImplement.setStartX(170);
        startHandlerImplement.setStartY(70);
        startHandlerImplement.setEndX(450);
        startHandlerImplement.setEndY(360);
        startHandlerImplement.setSplitX(220);
        startHandlerImplement.setSplitY(280);
        
        eventHandler.setClassText("EventHandler");
        eventHandler.setInterfaceName("EventHandler");
        
        tempMethText = "+handle(event: Event): void"+"\n";
        
        eventHandler.setMethText(tempMethText);
        
        eventHandler.setPackageName("ThreadExample");
        
        eventHandler.setLayoutX(50);
        eventHandler.setLayoutY(50);
        
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        eventHandler.addMethod(m);
        
        Pane canvas = new Pane();
        canvas.getChildren().addAll
        (threadExample,application,counterTask,dateTask,pauseHandler,
        startHandler,eventHandler,threadExampleInherit,pauseHandlerImplement,
        startHandlerImplement);
        
        dm.setCanvasDM(canvas);
        
        String s = System.getProperty("user.dir")+"/work/DesignSaveTest.json";
        
        try{
            fm.saveData(dm,s);
        } catch(IOException ex){
            
        }
    }
    
    public void hardTest3(){
        
        jcd.makeAppBuilderHook();
        fm = new FileManager();
        
        try{
            dm = new DataManager(jcd);
        } catch(Exception ex){
            
        }
        
        threadExample.addImport("import javafx.application.Application");
        threadExample.addImport("import javafx.concurrent.Task");
        threadExample.addImport("import javafx.geometry.Orientation");
        threadExample.addImport("import javafx.scene.Scene");
        threadExample.addImport("import javafx.scene.control.Button");
        threadExample.addImport("import javafx.scene.control.ScrollPane");
        threadExample.addImport("import javafx.scene.control.TextArea");
        threadExample.addImport("import javafx.scene.layout.BorderPane");
        threadExample.addImport("import javafx.scene.layout.FlowPane");
        threadExample.addImport("import javafx.stage.Stage");
        
        threadExample.setClassText("ThreadExample");
        threadExample.setClassName("ThreadExample");
        String tempVarText = "+$ START_TEXT: String" + "\n"+
                "+$ PAUSE_TEXT: String" + "\n"+
                "-window: Stage" + "\n"+
                "-appPane: BorderPane" + "\n"+
                "-topPane: FlowPane" + "\n"+
                "-startButton: Button" + "\n"+
                "-pauseButton: Button" + "\n"+
                "-scrollPane: ScrollPane" + "\n"+
                "-textArea: TextArea" + "\n"+
                "-dateThread: Thread" + "\n"+
                "-dateTask: Task" + "\n"+
                "-counterThread: Thread" + "\n"+
                "-counterTask: Task" + "\n"+
                "-work:boolean";
        
        threadExample.setVarText(tempVarText);
        
        String tempMethText = "+start(primaryStage: Stage): void" + "\n"+
                "+startWork(): void" + "\n"+
                "+pauseWork():void" + "\n"+
                "+doWork(): boolean" + "\n"+
                "+appendText(textToAppend: String): void" + "\n"+
                "+sleep(timeToSleep: int): void" + "\n"+
                "-initLayout(): void" + "\n"+
                "-initHandlers(): void" + "\n"+
                "-initWindow(initPrimaryStage: Stage): void" + "\n"+
                "-initThread(): void" + "\n"+
                "+$main(args: String[]): void";
        threadExample.setMethText(tempMethText);
        
        threadExample.setPackageName("ThreadExample");
        threadExample.setParentName("Application");
        
        threadExample.setLayoutX(100);
        threadExample.setLayoutY(200);
        
        threadExample.setAbstract(false);
        Variable v = new Variable("START_TEXT","String",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        threadExample.addVariable(v);
        v = new Variable("PAUSE_TEXT","String",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        threadExample.addVariable(v);
        v = new Variable("window","Stage",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("appPane","BorderPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("topPane","FlowPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("startButton","Button",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("pauseButton","Button",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("scrollPane","ScrollPane",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("textArea","TextArea",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("dateThread","Thread",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("dateTask","Task",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("counterThread","Thread",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("counterTask","Task",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        v = new Variable("work","boolean",PRIVATE_TYPE,NONE);
        threadExample.addVariable(v);
        
        Method m = new Method("start", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("primaryStage", "Stage", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("startWork", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("pauseWork", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("doWork", "boolean", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("appendText", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("textToAppend", "String", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("sleep", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("timeToSleep", "int", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initLayout", "void", 0, PRIVATE_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initHandlers", "void", 0, PRIVATE_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initWindow", "void", 1, PRIVATE_TYPE, NONE);
        m.addParameter("initPrimaryStage", "Stage", NO_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("initThreads", "void", 0, PUBLIC_TYPE, NONE);
        threadExample.addMethod(m);
        m = new Method("main", "void", 1, PUBLIC_TYPE, STATIC_TYPE);
        m.addParameter("args", "String[]", NO_TYPE, NONE);
        threadExample.addMethod(m);
        
        threadExampleInherit.setParentName("Application");
        threadExampleInherit.setChildName("ThreadExample");
        threadExampleInherit.setStartX(100);
        threadExampleInherit.setStartY(50);
        threadExampleInherit.setEndX(400);
        threadExampleInherit.setEndY(300);
        threadExampleInherit.setSplitX(200);
        threadExampleInherit.setSplitY(250);
        
        application.addImport("import javafx.stage.Stage");
        application.setClassText("Application");
        application.setClassName("Application");
        tempVarText = "";
        
        application.setVarText(tempVarText);
        
        tempMethText = "{abstract}"+"\n"+"start(primaryStage: Stage): void";
        
        application.setMethText(tempMethText);
        
        application.setPackageName("ThreadExample");
        application.setParentName("");
        
        application.setLayoutX(300);
        application.setLayoutY(400);
        
        m = new Method("start", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("primaryStage", "Stage", NO_TYPE, NONE);
        application.addMethod(m);
        
        counterTask.addImport("import javafx.application.Platform");
        counterTask.addImport("import javafx.concurrent.Task");
        
        counterTask.setClassText("CounterTask");
        counterTask.setClassName("CounterTask");
        
        tempVarText = "-app: ThreadExample"+"\n"+
            "-counter: int"+"\n";
        
        counterTask.setVarText(tempVarText);
        
        tempMethText = "+CounterTask(initApp: ThreadExample)" +"\n"+
            "#call(): Void";
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        counterTask.addVariable(v);
        v = new Variable("counter","int",PRIVATE_TYPE,NONE);
        counterTask.addVariable(v);
        
        counterTask.setMethText(tempMethText);
        
        counterTask.setPackageName("ThreadExample");
        counterTask.setParentName("Task");
        
        counterTask.setLayoutX(270);
        counterTask.setLayoutY(350);
        
        m = new Method("CounterTask", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        counterTask.addMethod(m);
        m = new Method("call", "Void", 0, PROTECTED_TYPE, NONE);
        counterTask.addMethod(m);
        
        
        dateTask.addImport("import java.util.Date");
        dateTask.addImport("import javafx.application.Platform");
        dateTask.addImport("import javafx.concurrent.Task");
        
        dateTask.setClassText("DateTask");
        dateTask.setClassName("DateTask");
        
        tempVarText = "-app: ThreadExample"+"\n"+
            "-now: Date"+"\n";
        
        dateTask.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        dateTask.addVariable(v);
        v = new Variable("now","Date",PRIVATE_TYPE,NONE);
        dateTask.addVariable(v);
        
        tempMethText = "+DateTask(initApp: ThreadExample)" +"\n"+
            "#call(): Void";
        
        dateTask.setMethText(tempMethText);
        
        dateTask.setPackageName("ThreadExample");
        dateTask.setParentName("Task");
        
        dateTask.setLayoutX(270);
        dateTask.setLayoutY(350);
        
        m = new Method("DateTask", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        dateTask.addMethod(m);
        m = new Method("call", "Void", 0, PROTECTED_TYPE, NONE);
        dateTask.addMethod(m);
        
        
        pauseHandler.addImport("import javafx.event.Event");
        pauseHandler.addImport("import javafx.event.EventHandler");
        
        pauseHandler.setClassText("PauseHandler");
        pauseHandler.setClassName("PauseHandler");
        
        tempVarText = "-app: ThreadExample"+"\n";
        
        pauseHandler.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        pauseHandler.addVariable(v);

        
        tempMethText = "+PauseHandler(initApp: ThreadExample)" +"\n"+
            "+handle(event: Event): void";
        
        pauseHandler.setMethText(tempMethText);
        
        pauseHandler.setPackageName("ThreadExample");
        pauseHandler.setInterfaceName("EventHandler");
        pauseHandler.setParentName("PlatformEat");
        
        pauseHandler.setLayoutX(270);
        pauseHandler.setLayoutY(350);
        
        m = new Method("PauseHandler", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        pauseHandler.addMethod(m);
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        pauseHandler.addMethod(m);
        m = new Method("handleEat", "void", 0, PUBLIC_TYPE, NONE);
        pauseHandler.addMethod(m);
        
        
        pauseHandlerImplement.setParentName("EventHandler");
        pauseHandlerImplement.setChildName("PauseHandler");
        pauseHandlerImplement.setStartX(100);
        pauseHandlerImplement.setStartY(50);
        pauseHandlerImplement.setEndX(400);
        pauseHandlerImplement.setEndY(300);
        pauseHandlerImplement.setSplitX(200);
        pauseHandlerImplement.setSplitY(250);
        
        extraInherit.setParentName("PlatformEat");
        extraInherit.setChildName("PauseHandler");
        extraInherit.setStartX(140);
        extraInherit.setStartY(500);
        extraInherit.setEndX(470);
        extraInherit.setEndY(350);
        extraInherit.setSplitX(150);
        extraInherit.setSplitY(300);
        
        extraAbstract.setClassText("PlatformEat");
        extraAbstract.setClassName("PlatformEat");
        
        tempVarText ="-eat: int"+"\n";
        
        extraAbstract.setVarText(tempVarText);
        
        tempMethText = "+PlatformEat(initEat: int)" +"\n"+
            "{abstract} handleEat(): void";
        
        v = new Variable("eat","int",PRIVATE_TYPE,NONE);
        extraAbstract.addVariable(v);
        
        
        extraAbstract.setMethText(tempMethText);
        
        extraAbstract.setPackageName("ThreadExample");
        
        extraAbstract.setLayoutX(370);
        extraAbstract.setLayoutY(450);
        
        m = new Method("PlatformEat", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initEat", "int", NO_TYPE, NONE);
        extraAbstract.addMethod(m);
        m = new Method("handleEat", "Void", 0, ABSTRACT_TYPE, NONE);
        extraAbstract.addMethod(m);
        
        startHandler.addImport("import javafx.event.Event");
        startHandler.addImport("import javafx.event.EventHandler");
        
        startHandler.setClassText("StartHandler");
        startHandler.setClassName("StartHandler");
        
        tempVarText = "-app: ThreadExample"+"\n";
        
        startHandler.setVarText(tempVarText);
        
        v = new Variable("app","ThreadExample",PRIVATE_TYPE,NONE);
        startHandler.addVariable(v);
        
        tempMethText = "+StartHandler(initApp: ThreadExample)" +"\n"+
            "+handle(event: Event): void";
        
        startHandler.setMethText(tempMethText);
        
        startHandler.setPackageName("ThreadExample");
        startHandler.setInterfaceName("EventHandler");
        
        startHandler.setLayoutX(270);
        startHandler.setLayoutY(350);
        
        m = new Method("StartHandler", "", 1, PUBLIC_TYPE, NONE);
        m.addParameter("initApp", "ThreadExample", NO_TYPE, NONE);
        startHandler.addMethod(m);
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        startHandler.addMethod(m);
        
        startHandlerImplement.setParentName("EventHandler");
        startHandlerImplement.setChildName("StartHandler");
        startHandlerImplement.setStartX(170);
        startHandlerImplement.setStartY(70);
        startHandlerImplement.setEndX(450);
        startHandlerImplement.setEndY(360);
        startHandlerImplement.setSplitX(220);
        startHandlerImplement.setSplitY(280);
        
        eventHandler.setClassText("EventHandler");
        eventHandler.setInterfaceName("EventHandler");
        
        tempMethText = "+handle(event: Event): void"+"\n";
        
        eventHandler.setMethText(tempMethText);
        
        eventHandler.setPackageName("ThreadExample");
        
        eventHandler.setLayoutX(50);
        eventHandler.setLayoutY(50);
        
        m = new Method("handle", "void", 1, PUBLIC_TYPE, NONE);
        m.addParameter("event", "Event", NO_TYPE, NONE);
        eventHandler.addMethod(m);
        
        Pane canvas = new Pane();
        canvas.getChildren().addAll(threadExample,application,counterTask,dateTask,pauseHandler,
        startHandler,eventHandler,extraAbstract,threadExampleInherit,pauseHandlerImplement,
        startHandlerImplement,extraInherit);
        
        dm.setCanvasDM(canvas);
        
        String s = System.getProperty("user.dir")+"/work/DesignSaveTest.json";
        
        try{
            fm.saveData(dm,s);
        } catch(IOException ex){
            
        }
    }
    
    public void testLoad(TestLoad tl){
        tl.hardTestLoad();
    }
    
    public DataManager getDM(){
        return dm;
    }
    
    public static void main(String[] args) throws Exception{
        TestSave ts = new TestSave();
        ts.hardTest2();
        //ts.hardTest1();
    }
    
    
    
}
