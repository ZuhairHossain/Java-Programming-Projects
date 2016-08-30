package jcd.data;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import jcd.gui.Workspace;
import properties_manager.PropertiesManager;
import saf.components.AppDataComponent;
import saf.AppTemplate;
import saf.controller.AppFileController;
import saf.ui.AppGUI;
import saf.ui.AppMessageDialogSingleton;
import saf.ui.AppYesNoCancelDialogSingleton;

/**
 * This class serves as the data management component for this application.
 *
 * @author Dennis Sosa
 * @version 1.0
 */
public class DataManager implements AppDataComponent {
    // FIRST THE THINGS THAT HAVE TO BE SAVED TO FILES

    // THIS IS A SHARED REFERENCE TO THE APPLICATION
    AppTemplate app;
    
    // USE THIS WHEN THE SHAPE IS SELECTED
    Effect highlightedEffect;
    
    double initialSceneX, initialSceneY, initialTranslateX, initialTranslateY;
    Text classN;
    //Pane canvas;
    
    Pane tempCanvas;
    VBox v1; 
    HBox h1;
    HBox h2;
    Text vars;
    HBox h3;
    Text meths;
    
    DraggableClass dc;
    DraggableInterface di;
    ArrayList<DraggableClass> workspaceClasses;
    ArrayList<DraggableInterface> workspaceInterfaces;
    ArrayList<DraggableLine> workspaceLines;
    
    static ArrayList<VBox> selectedItem = new ArrayList();
    static ArrayList<DraggableLine> selectedLine = new ArrayList();
    
    boolean selectionToolEnable;
    boolean addClassEnable;
    boolean addInterfaceEnable;
    boolean resizeToolEnable;
    
    Workspace workspace;
    
    double initialLayoutX;
    double initialLayoutY;
    
    double initialX;
    double initialY;

    /**
     * THis constructor creates the data manager and sets up the
     *
     *
     * @param initApp The application within which this data manager is serving.
     */
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
	workspaceClasses = new ArrayList();
        workspaceInterfaces = new ArrayList();
        workspaceLines = new ArrayList();
        //canvas = new Pane();
        tempCanvas = new Pane();
        
	// THIS IS FOR THE SELECTED SHAPE
	DropShadow dropShadowEffect = new DropShadow();
	dropShadowEffect.setOffsetX(0.0f);
	dropShadowEffect.setOffsetY(0.0f);
	dropShadowEffect.setSpread(1.0);
	dropShadowEffect.setColor(Color.YELLOW);
	dropShadowEffect.setBlurType(BlurType.GAUSSIAN);
	dropShadowEffect.setRadius(15);
	highlightedEffect = dropShadowEffect;
        
    }
    
    /**
     * This function clears out the HTML tree and reloads it with the minimal
     * tags, like html, head, and body such that the user can begin editing a
     * page.
     */
    @Override
    public void reset() {
        if(((Workspace)app.getWorkspaceComponent())!=null){
            ((Workspace)app.getWorkspaceComponent()).getCanvas().getChildren().clear();
            ((Workspace)app.getWorkspaceComponent()).disableButtons(true);
        }
        selectedItem.clear();
        selectedLine.clear();
        workspaceClasses.clear();
        workspaceInterfaces.clear();
        workspaceLines.clear();
        
        //canvas.getChildren().clear();
        tempCanvas.getChildren().clear();
    }
        
    public void updateSelectedClassName(){
        workspace = (Workspace) app.getWorkspaceComponent();
        String text = workspace.getClassNameText().getText();
        boolean abstractFlag = false;
        
        if(text.contains("{abstract}")){
            text = text.substring(10).replaceAll("\\s","");
            abstractFlag = true;
        }
        
        boolean badFlag = false;
        
        if(!workspaceClasses.isEmpty()){
            for(DraggableClass d: workspaceClasses){
                if(d.equals((DraggableClass)selectedItem.get(0)))
                    continue;
                
                if(d.getClassName().equals(text) &&
                    d.getPackageName().equals(((DraggableClass)selectedItem.get(0)).getPackageName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(!workspaceInterfaces.isEmpty()){
            for(DraggableInterface d: workspaceInterfaces){
                
                if(d.getInterfaceName().equals(text) &&
                    d.getPackageName().equals(((DraggableClass)selectedItem.get(0)).getPackageName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(badFlag){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Edit Class Name Error", "ERROR: You already defined that class name in current package.");
        }
            
        else{
            if(abstractFlag)
                ((DraggableClass)selectedItem.get(0)).setAbstract(abstractFlag);

            for(Node n: workspace.getCanvas().getChildren()){
                if(n instanceof DraggableClass){
                if(((DraggableClass)n).getClassName().equals(((DraggableClass)selectedItem.get(0)).getClassName())){
                    continue;
                }
                else{
                    ((DraggableClass)n).updatePaneList(workspace.getCanvas());
                    ((DraggableClass)n).getPaneData().set(
                    ((DraggableClass)n).getPaneData().indexOf(((DraggableClass)selectedItem.get(0))), ((DraggableClass)selectedItem.get(0)));
                }
            }}
            ((DraggableClass)selectedItem.get(0)).setClassName(text);
            ((DraggableClass)selectedItem.get(0)).setClassText(text);
            workspace.reloadWorkspace();
        }
        
    }
    
    public void updateSelectedInterfaceName(){
        workspace = (Workspace) app.getWorkspaceComponent();
        String text = workspace.getClassNameText().getText();
        boolean badFlag = false;
        
        if(!workspaceClasses.isEmpty()){
            for(DraggableClass d: workspaceClasses){
            
                if(d.getClassName().equals(text) &&
                    d.getPackageName().equals(((DraggableInterface)selectedItem.get(0)).getPackageName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(!workspaceInterfaces.isEmpty()){
            for(DraggableInterface d: workspaceInterfaces){
                if(d.equals((DraggableInterface)selectedItem.get(0)))
                    continue;
                
                if(d.getInterfaceName().equals(text) &&
                    d.getPackageName().equals(((DraggableInterface)selectedItem.get(0)).getPackageName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(badFlag){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Edit Interface Name Error", "ERROR: You already defined that interface name in current package.");
        }
            
        else{
            for(Node n: workspace.getCanvas().getChildren()){
                if(n instanceof DraggableInterface){
                if(((DraggableInterface)n).getInterfaceName().equals(((DraggableInterface)selectedItem.get(0)).getInterfaceName())){
                    continue;
                }
                else{
                    ((DraggableInterface)n).getPaneData().set(
                    ((DraggableInterface)n).getPaneData().indexOf(((DraggableInterface)selectedItem.get(0))), ((DraggableInterface)selectedItem.get(0)));
                }
                }
                else if(n instanceof DraggableClass){
                    if(!((DraggableClass)n).getPaneData().isEmpty()){
                    ((DraggableClass)n).updatePaneList(workspace.getCanvas());
                    ((DraggableClass)n).getPaneData().set(
                    ((DraggableClass)n).getPaneData().indexOf(((DraggableInterface)selectedItem.get(0))), ((DraggableInterface)selectedItem.get(0)));
                    }
                    else{
                        
                    }
                }
            }
            ((DraggableInterface)selectedItem.get(0)).setInterfaceName(text);
            ((DraggableInterface)selectedItem.get(0)).setClassText(text);
            workspace.reloadWorkspace();
        }
        
    }
    
    public void updateSelectedClassPackage(){
        workspace = (Workspace) app.getWorkspaceComponent();
        String text = workspace.getPackageNameText().getText();
        
        boolean badFlag = false;
        
        if(!workspaceClasses.isEmpty()){
            for(DraggableClass d: workspaceClasses){
                if(d.equals((DraggableClass)selectedItem.get(0)))
                    continue;
                
                if(d.getPackageName().equals(text) &&
                        d.getClassName().equals(((DraggableClass)selectedItem.get(0)).getClassName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(!workspaceInterfaces.isEmpty()){
            for(DraggableInterface d: workspaceInterfaces){
                
                if(d.getPackageName().equals(text) &&
                        d.getInterfaceName().equals(((DraggableClass)selectedItem.get(0)).getClassName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(badFlag){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Edit Package Name Error", "ERROR: The entered package name contains the same \nclass name as current one.");
        }
            
        else{
            ((DraggableClass)selectedItem.get(0)).setPackageName(text);
            workspace.reloadWorkspace();
        }
        
    }
    
    public void updateSelectedInterfacePackage(){
        workspace = (Workspace) app.getWorkspaceComponent();
        String text = workspace.getPackageNameText().getText();
        
        boolean badFlag = false;
        
        if(!workspaceClasses.isEmpty()){
            for(DraggableClass d: workspaceClasses){
                
                if(d.getPackageName().equals(text) &&
                        d.getClassName().equals(((DraggableInterface)selectedItem.get(0)).getInterfaceName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(!workspaceInterfaces.isEmpty()){
            for(DraggableInterface d: workspaceInterfaces){
                if(d.equals((DraggableInterface)selectedItem.get(0)))
                    continue;
                
                if(d.getPackageName().equals(text) &&
                        d.getInterfaceName().equals(((DraggableInterface)selectedItem.get(0)).getInterfaceName())){
                    badFlag = true;
                    break;
                }
            }
        }
        
        if(badFlag){
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Edit Package Name Error", "ERROR: The entered package name contains the same \nclass name as current one.");
        }
            
        else{
            ((DraggableInterface)selectedItem.get(0)).setPackageName(text);
            workspace.reloadWorkspace();
        }
    }
    
    @Override
    public void handleAddClass(){
        workspace = (Workspace)app.getWorkspaceComponent();
        //canvas = workspace.getCanvas();
        workspace.getCanvas().setCursor(Cursor.CROSSHAIR);
        selectionToolEnable = false;
        addInterfaceEnable = false;
        resizeToolEnable = false;
        addClassEnable = true;
    }
    
    @Override
    public void handleAddInterface(){
        workspace = (Workspace)app.getWorkspaceComponent();
        workspace.getCanvas().setCursor(Cursor.CROSSHAIR);
        selectionToolEnable = false;
        resizeToolEnable = false;
        addClassEnable = false;
        addInterfaceEnable = true;
    }
    
    @Override
    public void handleRemove(){
        if(!selectedItem.isEmpty()){
            //ARE YOU SURE?
            PropertiesManager proper = PropertiesManager.getPropertiesManager();
            AppYesNoCancelDialogSingleton box = AppYesNoCancelDialogSingleton.getSingleton();
            box.show("Delete Node Verification","WARNING: Are you sure you want to delete the selected class/interface?");
            
             // AND NOW GET THE USER'S SELECTION
            String optionSelected = box.getSelection();

            // IF THE USER SAID YES, THEN DELETE THE NODE
            if (optionSelected.equals(AppYesNoCancelDialogSingleton.YES)) {
            
                if(!selectedItem.isEmpty()){
                    workspace.getCanvas().getChildren().remove(selectedItem.get(0));
                    if(selectedItem.get(0) instanceof DraggableClass)
                        workspaceClasses.remove((DraggableClass)selectedItem.get(0));
                    else if(selectedItem.get(0) instanceof DraggableInterface)
                        workspaceInterfaces.remove((DraggableInterface)selectedItem.get(0));
                    
                    selectedItem.clear();
                }
            
                workspace.disableButtons(true);
                workspace.reloadWorkspace();
            }
            
            // IF THE USER SAID CANCEL OR NO OR EXITS
            else {
                System.out.println("THE NODE WILL NOT BE DELETED");
            }
        }
    }
    
    @Override
    public void handleUndo(){
        
    }
    
    @Override
    public void handleRedo(){
        
    }
    
    public void handleAddClassCanvas(double x, double y){
        if(addClassEnable){
            workspace = (Workspace)app.getWorkspaceComponent();
            //canvas = workspace.getCanvas();
            if(!selectedItem.isEmpty()){
                selectedItem.get(0).setEffect(null);
                selectedItem.clear();
            }
            
            dc = new DraggableClass(false);
        
            dc.setEffect(highlightedEffect);
            dc.setLayoutX(x);
            dc.setLayoutY(y);
            //System.out.println("X:"+x+"Y:"+y);
            dc = createClassAction(dc);
            workspace.getCanvas().getChildren().add(dc);
            workspace.disableButtons(false);
            
            /*if(!selectedItem.isEmpty()){
                selectedItem.get(0).setEffect(null);
                selectedItem.clear();
            }*/
                
            selectedItem.add(dc);
            workspaceClasses.add(dc);
            
            dc.setClassName("DefaultClass"+workspaceClasses.size());
            dc.setClassText("DefaultClass"+workspaceClasses.size());
            workspace.getClassNameText().setText("DefaultClass"+workspaceClasses.size());
            workspace.getPackageNameText().setText("default");
            workspace.getVarScrollPane().setContent(dc.getVariableTable());
            workspace.getMethScrollPane().setContent(dc.getMethodTable());
            workspace.getImportScrollPane().setContent(dc.getImportList());
            dc.updatePaneList(workspace.getCanvas());
            workspace.getParentScrollPane().setContent(dc.getParentGrid());
        }
    }
    
    public void handleAddInterfaceCanvas(double x, double y){
        if(addInterfaceEnable){
            workspace = (Workspace)app.getWorkspaceComponent();
            //canvas = workspace.getCanvas();
        
            di = new DraggableInterface();
        
            di.setEffect(highlightedEffect);
            di.setLayoutX(x);
            di.setLayoutY(y);
            di = createInterfaceAction(di);
            workspace.getCanvas().getChildren().add(di);
            workspace.disableButtons(false);
            
            if(!selectedItem.isEmpty()){
                selectedItem.get(0).setEffect(null);
                selectedItem.clear();
            }
                
            selectedItem.add(di);
            workspaceInterfaces.add(di);
            
            di.setInterfaceName("DefaultInterface"+workspaceInterfaces.size());
            di.setClassText("DefaultInterface"+workspaceInterfaces.size());
            workspace.getClassNameText().setText("DefaultInterface"+workspaceInterfaces.size());
            workspace.getPackageNameText().setText("default");
            workspace.getVarScrollPane().setContent(di.getVariableTable());
            workspace.getMethScrollPane().setContent(di.getMethodTable());
            workspace.getImportScrollPane().setContent(di.getImportList());
            di.updatePaneList(workspace.getCanvas());
            workspace.getParentScrollPane().setContent(di.getParentGrid());
            
        }
    }
    
    public Polygon createPolyAction(Polygon p) {
        p.setOnMousePressed(e ->{
            //v.setEffect(highlightedEffect);
            if(selectionToolEnable){
                if(!selectedItem.isEmpty()){
                    selectedItem.get(0).setEffect(null);
                    selectedItem.clear();
                }
                
                //selectedItem.add(p);
                workspace.disableButtons(false);
                workspace = (Workspace)app.getWorkspaceComponent();
                //canvas = workspace.getCanvas();
            
            initialSceneX = e.getSceneX();
            initialSceneY = e.getSceneY();
            
            initialTranslateX = p.getTranslateX();
            initialTranslateY = p.getTranslateY();
            
            
            }
        });
        p.setOnMouseDragged(e ->{
            if(selectionToolEnable){
                //c.setLayoutX(initialLayoutX + (e.getX() - initialX));
                //c.setLayoutY(initialLayoutY + (e.getY() - initialY));
                
                p.setTranslateX(initialTranslateX + (e.getSceneX() - initialSceneX));
                p.setTranslateY(initialTranslateY + (e.getSceneY() - initialSceneY));
                //c.setLayoutX((initialLayoutX+(e.getX()-initialX)));
                //c.setLayoutY((initialLayoutY+(e.getY()-initialY)));
                //c.setLayoutX(e.getX());
                //c.setLayoutY(e.getY());
                //System.out.println(c.getLayoutX());
            }
        });   
        return p;
    }
    
    public DraggableLine createLineAction(DraggableLine l) {
        l.setOnMousePressed(e ->{
            //v.setEffect(highlightedEffect);
            if(selectionToolEnable){
                if(!selectedItem.isEmpty()){
                    selectedItem.get(0).setEffect(null);
                    selectedItem.clear();
                }
                
                //selectedItem.add(p);
                workspace.disableButtons(false);
                workspace = (Workspace)app.getWorkspaceComponent();
                //canvas = workspace.getCanvas();
            
            initialSceneX = e.getSceneX();
            initialSceneY = e.getSceneY();
            
            initialTranslateX = l.getTranslateX();
            initialTranslateY = l.getTranslateY();
            
            
            }
        });
        l.setOnMouseDragged(e ->{
            if(selectionToolEnable){
                //c.setLayoutX(initialLayoutX + (e.getX() - initialX));
                //c.setLayoutY(initialLayoutY + (e.getY() - initialY));
                
                l.setTranslateX(initialTranslateX + (e.getSceneX() - initialSceneX));
                l.setTranslateY(initialTranslateY + (e.getSceneY() - initialSceneY));
                //l.setStartY(l.getTranslateY());
                //l.setStartX(l.getTranslateX());
                //l.setEndX(l.getTranslateX());
                //l.setEndY(l.getTranslateY());
                //c.setLayoutX((initialLayoutX+(e.getX()-initialX)));
                //c.setLayoutY((initialLayoutY+(e.getY()-initialY)));
                //c.setLayoutX(e.getX());
                //c.setLayoutY(e.getY());
                //System.out.println(c.getLayoutX());
            }
        });   
        return l;
    }
        
    public DraggableClass createClassAction(DraggableClass c) {
        c.setOnMousePressed(e ->{
            //v.setEffect(highlightedEffect);
            if(selectionToolEnable){
                if(!selectedItem.isEmpty()){
                    selectedItem.get(0).setEffect(null);
                    selectedItem.clear();
                }
                
                c.setEffect(highlightedEffect);
                selectedItem.add(c);
                workspace.disableButtons(false);
                workspace = (Workspace)app.getWorkspaceComponent();
                //canvas = workspace.getCanvas();
                workspace.getClassNameText().setText(c.getClassText().getText());
                workspace.getPackageNameText().setText(c.getPackageName());
                workspace.getVarScrollPane().setContent(c.getVariableTable());
                workspace.getMethScrollPane().setContent(c.getMethodTable());
                workspace.getImportScrollPane().setContent(c.getImportList());
                c.updatePaneList(workspace.getCanvas());
                workspace.getParentScrollPane().setContent(c.getParentGrid());
            
            initialSceneX = e.getSceneX();
            initialSceneY = e.getSceneY();
            
            initialTranslateX = c.getTranslateX();
            initialTranslateY = c.getTranslateY();
            
            initialX = e.getX();
            initialY = e.getY();
            initialLayoutX = c.getLayoutX();
            initialLayoutY = c.getLayoutY();
            
            
            }
        });
        
        c.setOnMouseDragged(e ->{
            if(selectionToolEnable){
                //c.setLayoutX(initialLayoutX + (e.getX() - initialX));
                //c.setLayoutY(initialLayoutY + (e.getY() - initialY));
                
                c.setTranslateX(initialTranslateX + (e.getSceneX() - initialSceneX));
                c.setTranslateY(initialTranslateY + (e.getSceneY() - initialSceneY));
                //c.setLayoutX((initialLayoutX+(e.getX()-initialX)));
                //c.setLayoutY((initialLayoutY+(e.getY()-initialY)));
                //c.setLayoutX(Math.round(e.getX()));
                //c.setLayoutY(Math.round(e.getY()));
                //System.out.println(c.getLayoutX());
            }
            
            else if(resizeToolEnable){
                //c.setPrefWidth(initialLayoutX+(e.getX() - initialSceneX));
                //c.setPrefHeight(initialLayoutY+(e.getY() - initialSceneY));
                
                //c.getClassBox().setPrefHeight(initialLayoutY+(e.getY() - initialSceneY));
                //c.getVarBox().setPrefHeight(initialLayoutY+(e.getY() - initialSceneY));
                //c.getMethBox().setPrefHeight(initialLayoutY+(e.getY() - initialSceneY));
                
                
                c.setPrefWidth((e.getX() - initialX) + (initialTranslateX + (e.getSceneX() - initialSceneX)));
                c.setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                
                c.getClassBox().setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                c.getVarBox().setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                c.getMethBox().setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                
                /*
                c.setPrefWidth((c.getWidth()/2.0) + (e.getSceneX() - initialSceneX));
                c.setPrefHeight((c.getHeight()/2.0) + (e.getSceneY() - initialSceneY));
                
                c.getClassBox().setPrefHeight((c.getHeight()/2.0) + (e.getSceneY() - initialSceneY));
                c.getVarBox().setPrefHeight((c.getHeight()/2.0) + (e.getSceneY() - initialSceneY));
                c.getMethBox().setPrefHeight((c.getHeight()/2.0) + (e.getSceneY() - initialSceneY));*/
            }
        });
        
        return c;
    }
    
    public DraggableInterface createInterfaceAction(DraggableInterface i) {
        i.setOnMousePressed(e ->{
            //v.setEffect(highlightedEffect);
            if(selectionToolEnable){
                if(!selectedItem.isEmpty()){
                    selectedItem.get(0).setEffect(null);
                    selectedItem.clear();
                }
                
                i.setEffect(highlightedEffect);
                selectedItem.add(i);
                workspace.disableButtons(false);
                workspace = (Workspace)app.getWorkspaceComponent();
                //canvas = workspace.getCanvas();
                workspace.getClassNameText().setText(i.getInterfaceName());
                workspace.getPackageNameText().setText(i.getPackageName());
                workspace.getVarScrollPane().setContent(i.getVariableTable());
                workspace.getMethScrollPane().setContent(i.getMethodTable());
                workspace.getImportScrollPane().setContent(i.getImportList());
                i.updatePaneList(workspace.getCanvas());
                workspace.getParentScrollPane().setContent(i.getParentGrid());
            
            initialSceneX = e.getSceneX();
            initialSceneY = e.getSceneY();
            initialTranslateX = i.getTranslateX();
            initialTranslateY = i.getTranslateY();
            }
        });
        
        i.setOnMouseDragged(e ->{
            if(selectionToolEnable){
                i.setTranslateX(initialTranslateX + (e.getSceneX() - initialSceneX));
                i.setTranslateY(initialTranslateY + (e.getSceneY() - initialSceneY));
                //System.out.println("TranslateX:"+i.getTranslateX());
                //System.out.println("LayoutX:"+i.getLayoutX());
            }
            
            else if(resizeToolEnable){
                
                /*
                i.setPrefWidth(e.getSceneX() - i.getLayoutX());
                i.setPrefHeight(e.getSceneY() - i.getLayoutY());
                
                i.getClassBox().setPrefHeight(e.getSceneY() - i.getLayoutY());
                i.getVarBox().setPrefHeight(e.getSceneY() - i.getLayoutY());
                i.getMethBox().setPrefHeight(e.getSceneY() - i.getLayoutY());*/
                /*
                i.setPrefWidth((i.getWidth()/2.0) + (e.getSceneX() - initialSceneX));
                i.setPrefHeight((i.getHeight()/2.0) + (e.getSceneY() - initialSceneY));
                
                i.getClassBox().setPrefHeight((i.getHeight()/2.0) + (e.getSceneY() - initialSceneY));
                i.getVarBox().setPrefHeight((i.getHeight()/2.0) + (e.getSceneY() - initialSceneY));
                i.getMethBox().setPrefHeight((i.getHeight()/2.0) + (e.getSceneY() - initialSceneY));*/
                i.setPrefWidth((e.getX() - initialX) + (initialTranslateX + (e.getSceneX() - initialSceneX)));
                i.setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                
                i.getClassBox().setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                i.getVarBox().setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
                i.getMethBox().setPrefHeight((e.getY() - initialY) + (initialTranslateY + (e.getSceneY() - initialSceneY)));
            }
        });
        
        return i;
    }
    
    @Override
    public void enableSelectionTool(){
        workspace = (Workspace)app.getWorkspaceComponent();
        //canvas = workspace.getCanvas();
        workspace.getCanvas().setCursor(Cursor.DEFAULT);
        addClassEnable = false;
        addInterfaceEnable = false;
        resizeToolEnable = false;
        selectionToolEnable = true;
    }
    
    @Override
    public void enableResizeTool(){
        workspace = (Workspace)app.getWorkspaceComponent();
        //canvas = workspace.getCanvas();
        workspace.getCanvas().setCursor(Cursor.CROSSHAIR);
        addClassEnable = false;
        addInterfaceEnable = false;
        selectionToolEnable = false;
        resizeToolEnable = true;
    }
    public Workspace getWorkspace(){
        workspace = (Workspace) app.getWorkspaceComponent();
        return workspace;
    }
    
    public Pane getCWorkspace(){
        workspace = (Workspace) app.getWorkspaceComponent();
        return workspace.getCanvas();
    }
    
    
    public Pane getCanvasDM() {
	return tempCanvas;
    }
    
    public void setCanvasDM(Pane canvas){
        this.tempCanvas = canvas;
    }
    
    public ArrayList<DraggableClass> getDraggableClasses(){
        return workspaceClasses;
    }
    
    public ArrayList<DraggableInterface> getDraggableInterfaces(){
        return workspaceInterfaces;
    }
    
    public ArrayList<DraggableLine> getDraggableLines(){
        return workspaceLines;
    }
    
    public boolean getClassEnable(){
        return addClassEnable;
    }
    
    public boolean getInterfaceEnable(){
        return addInterfaceEnable;
    }
    
    public ArrayList<VBox> getSelectedItem(){
        return selectedItem;
    }
}
