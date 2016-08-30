package saf.controller;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import saf.AppTemplate;

/**
 * This class provides the event programmed responses for the view tool bar controls
 * that are provided by this framework.
 * 
 * @author Dennis Sosa
 * @version 1.0
 */
public class AppViewController {
    // HERE'S THE APP
    AppTemplate app;
    
    static final double MAX_ZOOM_OUT = 1.0;//1.0;
    static final double MAX_ZOOM_IN = 4.0;
    static final double DELTA_ADD = 0.25;//1.1;
    static final double DELTA_SUB = 0.25;//.95;
    boolean gridMode;
    boolean snapMode;

    /**
     * This constructor just keeps the app for later.
     * 
     * @param initApp The application within which this controller
     * will provide file toolbar responses.
     */
    public AppViewController(AppTemplate initApp) {
        app = initApp;
    }
    
    /*
     * This function handles the Zoom In Button in the View Toolbar
    */
    public void handleZoomInRequest(){
        
        if(app.getWorkspaceComponent().getCanvas().getScaleX() < MAX_ZOOM_IN && 
                app.getWorkspaceComponent().getCanvas().getScaleY() < MAX_ZOOM_IN){
            app.getWorkspaceComponent().getCanvas().setScaleX(app.getWorkspaceComponent().getCanvas().getScaleX()+DELTA_ADD);
            app.getWorkspaceComponent().getCanvas().setScaleY(app.getWorkspaceComponent().getCanvas().getScaleY()+DELTA_ADD);
            //app.getWorkspaceComponent().getCanvasScrollPane().setPrefHeight(app.getWorkspaceComponent().getCanvasScrollPane().getHeight()+.25);
            
            for(Node n: app.getWorkspaceComponent().getCanvas().getChildren()){
                if(n.getScaleX()<1.0 && n.getScaleY()<1.0){
                    n.setScaleX(1.0);
                    n.setScaleY(1.0);
                    app.getWorkspaceComponent().getCanvas().setScaleX(1.0);
                    app.getWorkspaceComponent().getCanvas().setScaleY(1.0);
                }
            }
        }
    }
    
    /*
     * This function handles the Zoom Out Button in the View Toolbar
    */
    public void handleZoomOutRequest(){
        if(app.getWorkspaceComponent().getCanvas().getScaleX() > MAX_ZOOM_OUT && 
                app.getWorkspaceComponent().getCanvas().getScaleY() > MAX_ZOOM_OUT ){
            app.getWorkspaceComponent().getCanvas().setScaleX(app.getWorkspaceComponent().getCanvas().getScaleX()-DELTA_SUB);
            app.getWorkspaceComponent().getCanvas().setScaleY(app.getWorkspaceComponent().getCanvas().getScaleY()-DELTA_SUB);
            //app.getWorkspaceComponent().getCanvasScrollPane().setPrefHeight(app.getWorkspaceComponent().getCanvasScrollPane().getHeight()-.25);
        }
        else {
            for(Node n: app.getWorkspaceComponent().getCanvas().getChildren()){
                if(n.getScaleX()>0.75 && n.getScaleY()>0.75){
                    n.setScaleX(n.getScaleX()-DELTA_SUB);
                    n.setScaleY(n.getScaleY()-DELTA_SUB);
                }
            }
        }
    }
    
    /*
     * This function handles the enabling of Grid Mode in the workspace
    */
    public void enableGridMode(boolean isSelected){
        if(isSelected)
            //Cited Source/Credit: Online Tutorial
            app.getWorkspaceComponent().getCanvas().setStyle("-fx-background-color: lightyellow,\n" +
            "linear-gradient(from 0.5px 0px to 11px 0px, repeat, black 5%, transparent 5%),\n" +
            "linear-gradient(from 0px 0.5px to 0px 11px, repeat, black 5%, transparent 5%);");
        else
            app.getWorkspaceComponent().getCanvas().setStyle("-fx-background-color: lightyellow;");
            
    }
    
    public void enableSnapMode(){
        for(Node n: app.getWorkspaceComponent().getCanvas().getChildren()){
            if(n instanceof VBox){
                double x = ((VBox)n).getTranslateX();
                double y = ((VBox)n).getTranslateY();
                double x1 = ((VBox)n).getLayoutX();
                double y1 = ((VBox)n).getLayoutY();
                ((VBox)n).setTranslateX(21*((Math.round(x))/21));
                ((VBox)n).setTranslateY(21*((Math.round(y))/21));
                ((VBox)n).setLayoutX(21*((Math.round(x1))/21));
                ((VBox)n).setLayoutY(21*((Math.round(y1))/21));
            }
        }
    }
    
    public boolean isGridMode(){
        return gridMode;
    }
    
    public boolean isSnapMode(){
        return snapMode;
    }
}
