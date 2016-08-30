/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.test_bed;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import jcd.data.DataManager;
import jcd.data.DraggableClass;
import jcd.data.DraggableInterface;
import jcd.data.DraggableLine;
import jcd.data.Method;
import jcd.data.Variable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DennisSosa
 */
public class TestSaveJUnit4ThreeTest {
    
    public TestSaveJUnit4ThreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    

    /**
     * Test of hardTest3 method, of class TestSave.
     */
    @Test
    public void testHardTest3() {
        System.out.println("hardTest3");
        TestSave instance = new TestSave();
        instance.hardTest3();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of testLoad method, of class TestSave.
     */
    @Test
    public void testTestLoad() {
        System.out.println("testLoad");
        TestLoad tl = new TestLoad();
        TestSave instance = new TestSave();
        instance.testLoad(tl);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        DataManager dm = tl.getDM();
        /*
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
                        for(int z=0; z<m.getVariables().size(); z++){
                            Variable v = m.getVariables().get(z);
                            System.out.print(v.getType()+" "+v.getName());
                            if(z < m.getVariables().size()-2)
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
                        for(int z=0; z<m.getVariables().size(); z++){
                            Variable v = m.getVariables().get(z);
                            System.out.print(v.getType()+" "+v.getName());
                            if(z < m.getVariables().size()-2)
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
        }*/
        assertEquals("ThreadExample",dm.getDraggableClasses().get(0).getClassName());
        assertEquals("ThreadExample",dm.getDraggableClasses().get(0).getPackageName());
        assertEquals(false, dm.getDraggableClasses().get(0).isAbstract());
        assertEquals("Application", dm.getDraggableClasses().get(0).getParentName());
        assertEquals("CounterTask", dm.getDraggableClasses().get(2).getClassName());
        assertEquals("Task", dm.getDraggableClasses().get(2).getParentName());
        assertEquals("EventHandler", dm.getDraggableInterfaces().get(0).getInterfaceName());
        assertEquals("Application", dm.getDraggableClasses().get(1).getClassName());
        assertEquals(true, dm.getDraggableClasses().get(1).isAbstract());
        assertEquals(300.0, dm.getDraggableClasses().get(1).getLayoutX(),0);
    }
    
}
