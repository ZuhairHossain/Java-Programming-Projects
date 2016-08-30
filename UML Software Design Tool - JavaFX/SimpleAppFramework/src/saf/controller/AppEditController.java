package saf.controller;

import saf.ui.AppYesNoCancelDialogSingleton;
import saf.ui.AppMessageDialogSingleton;
import saf.ui.AppGUI;
import saf.components.AppFileComponent;
import saf.components.AppDataComponent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import properties_manager.PropertiesManager;
import saf.AppTemplate;

/**
 * This class provides the event programmed responses for the edit tool bar controls
 * that are provided by this framework.
 * 
 * @author Dennis Sosa
 * @version 1.0
 */
public class AppEditController {
    // HERE'S THE APP
    AppTemplate app;
    
    // KEEP TRACK OF EVERY CHANGE IN THE WORKSPACE
    // FOR UNDO & REDO FUNCTIONS
    ArrayList changeInWorkspace;
    ArrayList selectionTool;
    boolean resizeEnable;

    /**
     * This constructor just keeps the app for later.
     * 
     * @param initApp The application within which this controller
     * will provide file toolbar responses.
     */
    public AppEditController(AppTemplate initApp) {
        app = initApp;
    }
    
    public void markAsChanged(AppGUI gui){
        
    }
    
    public void enableSelectionTool(){
        app.getDataComponent().enableSelectionTool(); 
    }
    
    public void resizeEnable(){
        app.getDataComponent().enableResizeTool();
    }
    
    public void enableResize(){
        resizeEnable = true;
    }
    
    public void handleAddClassRequest(){
        app.getDataComponent().handleAddClass(); 
    }
    
    public void handleAddInterfaceRequest(){
        app.getDataComponent().handleAddInterface();
    }
    
    public boolean promptToDelete(){
        return false;
    }
    
    public void handleUndoRequest(){
        app.getDataComponent().handleUndo();
    }
    
    public void handleRedoRequest(){
        app.getDataComponent().handleRedo();
    }
    
    public void handleRemoveRequest(){
        app.getDataComponent().handleRemove();
    }
}
