/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.test_bed;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import jcd.JClassDesigner;
import jcd.data.DataManager;
import jcd.data.DraggableClass;
import jcd.data.DraggableInterface;
import jcd.data.DraggableLine;
import jcd.data.Method;
import jcd.file.FileManager;
import jcd.data.Variable;


/**
 *
 * @author DennisSosa
 */

public class TestLoad {
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
    
    public void hardTestLoad(){
        
        jcd.makeAppBuilderHook();
        fm = new FileManager();
        
        try{
            dm = new DataManager(jcd);
        } catch(Exception ex){
            
        }
        
        String s = System.getProperty("user.dir")+"/work/DesignSaveTest.json";
        try{
            fm.loadData(dm,s);
        } catch(IOException ex){
            
        }
        
        ObservableList<Node> workspaceNodes = dm.getCanvasDM().getChildren();
        
        System.out.println("\n\n");
        for(Node n: workspaceNodes){
            if(n instanceof DraggableClass){
                DraggableClass dc = (DraggableClass) n;
                System.out.println("Package: "+dc.getPackageName());
                System.out.println("Imports: ");
                for(String x: dc.getImports()){
                    System.out.println(x+";");
                }
                System.out.println("Class: "+dc.getClassText().getText());
                System.out.print("Variables: ");
                if(!dc.getVariableList().isEmpty()){
                    for(Variable v: dc.getVariableList())
                        System.out.println(v.getAccessType()+" "+v.getVariableType()+" "+v.getType()+" "+
                                v.getName()+";");
                    System.out.println("\n\n");
                }
                System.out.println();
                
                System.out.print("Methods: ");
                if(!dc.getMethodList().isEmpty()){
                    for(Method m: dc.getMethodList()){
                        System.out.print(m.getAccessType()+" "+m.getMethodType()+" "+m.getReturnType()+" "+m.getName()+"(");
                        for(int z=0; z<m.getArguments().size(); z++){
                            Variable v = m.getArguments().get(z);
                            System.out.print(v.getType()+" "+v.getName());
                            if(z < m.getArguments().size()-2)
                                System.out.print(",");
                            
                        }
                        System.out.println(");");
                    }
                }
                System.out.println("\n\n");
            }
            
            else if(n instanceof DraggableInterface){
                DraggableInterface di = (DraggableInterface) n;
                System.out.println("Interface: "+di.getClassText().getText());
                System.out.print("Variables: ");
                if(!di.getVariableList().isEmpty()){
                    for(Variable v: di.getVariableList())
                        System.out.println(v.getAccessType()+" "+v.getVariableType()+" "+v.getType()+" "+
                                v.getName()+";");
                    System.out.println(";");
                }
                System.out.println();
                
                System.out.print("Methods: ");
                if(!di.getMethodList().isEmpty()){
                    for(Method m: di.getMethodList()){
                        System.out.print(m.getAccessType()+" "+m.getMethodType()+" "+m.getReturnType()+" "+"(");
                        for(int z=0; z<m.getArguments().size(); z++){
                            Variable v = m.getArguments().get(z);
                            System.out.print(v.getType()+" "+v.getName());
                            if(z < m.getArguments().size()-2)
                                System.out.print(",");
                        }
                        System.out.println(");");
                    }
                }
                System.out.println("\n\n");
            }
            
            else if(n instanceof DraggableLine){
                DraggableLine dl = (DraggableLine) n;
                System.out.println("LayoutX: "+dl.getLayoutX());
                System.out.println("LayoutY: "+dl.getLayoutY());
                System.out.println("StartX: "+dl.getStartX());
                System.out.println("StartY: "+dl.getStartY());
                System.out.println("EndX: "+dl.getEndX());
                System.out.println("EndY: "+dl.getEndY());
                System.out.println("TranslateX: "+dl.getTranslateX());
                System.out.println("TranslateY: "+dl.getTranslateY());
                System.out.println("\n\n");
            }
        }
        
        
    }
    
    public DataManager getDM(){
        return dm;
    }
    
    
    public static void main(String[] args) throws Exception{
        TestLoad tl = new TestLoad();
        tl.hardTestLoad();
    }
    
    
    
}
