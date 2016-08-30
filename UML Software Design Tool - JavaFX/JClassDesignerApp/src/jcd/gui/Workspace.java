package jcd.gui;

import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import static jcd.PropertyType.PLUS_ICON;
import static jcd.PropertyType.PLUS_VAR_TOOLTIP;
import static jcd.PropertyType.MINUS_ICON;
import static jcd.PropertyType.MIN_IMPORT_TOOLTIP;
import static jcd.PropertyType.MIN_VAR_TOOLTIP;
import static jcd.PropertyType.PLUS_METH_TOOLTIP;
import static jcd.PropertyType.MIN_METH_TOOLTIP;
import static jcd.PropertyType.PLUS_IMPORT_TOOLTIP;
import jcd.data.DataManager;
import jcd.data.DraggableClass;
import jcd.data.DraggableInterface;
import jcd.data.DraggableLine;
import jcd.data.Variable;
import saf.ui.AppYesNoCancelDialogSingleton;
import saf.ui.AppMessageDialogSingleton;
import saf.ui.AppGUI;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.controller.AppFileController;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;

/**
 * This class serves as the workspace component for this application, providing
 * the user interface controls for editing work.
 *
 * @author Dennis Sosa
 * @version 1.0
 */
public class Workspace extends AppWorkspaceComponent {

    // THESE CONSTANTS ARE FOR TYING THE PRESENTATION STYLE OF
    // THIS Workspace'S COMPONENTS TO A STYLE SHEET THAT IT USES
    static final String CLASS_MAX_PANE = "max_pane";
    static final String CLASS_RENDER_CANVAS = "render_canvas";
    static final String CLASS_BUTTON = "button";
    static final String CLASS_EDIT_TOOLBAR = "edit_toolbar";
    static final String CLASS_EDIT_TOOLBAR_ROW = "edit_toolbar_row";
    static final String CLASS_COLOR_CHOOSER_PANE = "color_chooser_pane";
    static final String CLASS_COLOR_CHOOSER_CONTROL = "color_chooser_control";
    static final String EMPTY_TEXT = "";
    static final int BUTTON_TAG_WIDTH = 75;

    // HERE'S THE APP
    AppTemplate app;

    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;
    
    AppFileController update;

    // HAS ALL THE CONTROLS FOR EDITING
    VBox editToolbar;
    
    //SET RIGHT PANE
    // FIRST ROW
    GridPane fieldManager;
    Label className;
    TextField classNameText;
    Label packageName;
    TextField packageNameText;
    Label parentName;
    
    Label classInterfaceList;
    Label parentCol;
    Label interfaceCol;
    ScrollPane parentScrollPane;
    GridPane parentGrid;
    HBox h1;
    Button parentButton;
    Button interfaceButton;
    HBox h2;
    Button addExternalButton;
    Button removeExternalButton;
    HBox h3;
    Button externalParentButton;
    Button externalInterfaceButton;
    HBox h4;
    Button removeParentButton;
    HBox h5;
    Button removeInterfaceButton;
    
    Button refresh;
    
    ObservableList<String> paneClasses;
    ListView<String> classesListView;

    ObservableList<String> parent;
    ListView<String> parentListView;
    
    ObservableList<String> interfaces;
    ListView<String> interfaceListView;
    
    ObservableList<String> external;
    ListView<String> externalListView;

    // SECOND ROW
    VBox variablesManager;
    HBox varTitleLayout;
    HBox variableButtons;
    Label varTitle;
    Button addVariableButton;
    Button removeVariableButton;
    ScrollPane varScrollPane;
    TableView<Variable> varTable;
    
    VBox importsManager;
    HBox importTitleLayout;
    HBox importButtons;
    Label importTitle;
    Button addImportButton;
    Button removeImportButton;
    ScrollPane importScrollPane;
    ListView<String> importList;
    

    // THIRD ROW
    VBox methodsManager;
    HBox methTitleLayout;
    HBox methodButtons;
    Label methTitle;
    Button addMethodButton;
    Button removeMethodButton;
    ScrollPane methScrollPane;
    TableView methTable;
    ScrollPane toolbarScrollPane;
    
    //SET LEFT PANE
    // THIS IS WHERE WE'LL RENDER OUR DRAWING
    BorderPane workspaceBorderPane;
    Pane canvas;
    ScrollPane canvasScrollPane;  

    // HERE ARE OUR DIALOGS
    AppMessageDialogSingleton messageDialog;
    AppYesNoCancelDialogSingleton yesNoCancelDialog;
    
    // FOR DISPLAYING DEBUG STUFF
    Text debugText;
    

    /**
     * Constructor for initializing the workspace, note that this constructor
     * will fully setup the workspace user interface for use.
     *
     * @param initApp The application this workspace is part of.
     *
     * @throws IOException Thrown should there be an error loading application
     * data for setting up the user interface.
     */
    public Workspace(AppTemplate initApp) throws IOException {
	// KEEP THIS FOR LATER
	app = initApp;
        update = new AppFileController(app);
	// KEEP THE GUI FOR LATER
	gui = app.getGUI();

	layoutGUI();
	setupHandlers();
    }
  
    private void layoutGUI() {
	// THIS WILL GO IN THE RIGHT SIDE OF THE WORKSPACE
        className = new Label("Class Name: ");
        classNameText = new TextField();
        fieldManager = new GridPane();
        fieldManager.setVgap(3);
        fieldManager.setHgap(7);
        fieldManager.add(className, 0, 0);
        fieldManager.add(classNameText, 1, 0);
        
        packageName = new Label("Package: ");
        packageNameText = new TextField();
        fieldManager.add(packageName, 0, 1);
        fieldManager.add(packageNameText, 1, 1);
        
        parentName = new Label("Extend/Implement Parents: ");
        fieldManager.add(parentName, 0, 2);
        refresh = new Button("Refresh");
        fieldManager.add(refresh,1,2);
        
        
        parentGrid = new GridPane();
        parentGrid.setPadding(new Insets(5));
        parentGrid.setHgap(10);
        parentGrid.setVgap(15);
        
        classInterfaceList = new Label("Workspace Classes/Interfaces");
        GridPane.setHalignment(classInterfaceList, HPos.CENTER);
        parentGrid.add(classInterfaceList, 0, 0);
        
        parentButton = new Button("Parent");
        interfaceButton = new Button("Interface");
        h1 = new HBox();
        h1.getChildren().addAll(new Label("Specify Parent: "),parentButton,interfaceButton);
        h1.setAlignment(Pos.CENTER);
        parentGrid.add(h1,0,1);
        
        paneClasses = FXCollections
        .observableArrayList();
        classesListView = new ListView<>(paneClasses);
        parentGrid.add(classesListView, 0, 2);
        
        h2 = new HBox();
        h2.getChildren().add(new Label("Add/Remove External Class/Interface: "));
        addExternalButton = gui.initChildButton(h2, PLUS_ICON.toString(), PLUS_IMPORT_TOOLTIP.toString(), false);
        removeExternalButton = gui.initChildButton(h2, MINUS_ICON.toString(), MIN_IMPORT_TOOLTIP.toString(), false);
        h2.setAlignment(Pos.CENTER);
        parentGrid.add(h2,1,0);
        
        externalParentButton = new Button("Parent");
        externalInterfaceButton = new Button("Interface");
        h3 = new HBox();
        h3.getChildren().addAll(new Label("Specify Selected Parent: "),externalParentButton,externalInterfaceButton);
        h3.setAlignment(Pos.CENTER);
        parentGrid.add(h3,1,1);
        
        external = FXCollections.observableArrayList();
        externalListView = new ListView<>(external);
        parentGrid.add(externalListView, 1, 2);
        
        parentCol = new Label("Specified Parent(s)");
        GridPane.setHalignment(parentCol, HPos.CENTER);
        parentGrid.add(parentCol, 2, 0);
        
        h4 = new HBox();
        h4.getChildren().add(new Label("Remove Selected Parent: "));
        removeParentButton = gui.initChildButton(h4, MINUS_ICON.toString(), MIN_IMPORT_TOOLTIP.toString(), false);
        h4.setAlignment(Pos.CENTER);
        parentGrid.add(h4,2,1);
        
        parent = FXCollections.observableArrayList();
        parentListView = new ListView<>(parent);
        parentGrid.add(parentListView, 2, 2);
        
        interfaceCol = new Label("Specified Interfaces");
        GridPane.setHalignment(interfaceCol, HPos.CENTER);
        parentGrid.add(interfaceCol, 3, 0);
        
        h5 = new HBox();
        h5.getChildren().add(new Label("Remove Selected Interface: "));
        removeInterfaceButton = gui.initChildButton(h5, MINUS_ICON.toString(), MIN_IMPORT_TOOLTIP.toString(), false);
        h5.setAlignment(Pos.CENTER);
        parentGrid.add(h5,3,1);
        
        interfaces = FXCollections.observableArrayList();
        interfaceListView = new ListView<>(interfaces);
        parentGrid.add(interfaceListView, 3, 2);
        
        parentScrollPane = new ScrollPane();
        parentScrollPane.setContent(parentGrid);
        parentScrollPane.setMaxWidth(500);
        parentScrollPane.setMaxHeight(400);
        
        importsManager = new VBox(2);
        importTitleLayout = new HBox(5);
        importTitle = new Label("Import Packages: ");
        importButtons = new HBox();
        addImportButton = gui.initChildButton(importButtons, PLUS_ICON.toString(), PLUS_IMPORT_TOOLTIP.toString(), false);
        removeImportButton = gui.initChildButton(importButtons, MINUS_ICON.toString(), MIN_IMPORT_TOOLTIP.toString(), false);
        //addVariableButton.setStyle("-fx-background-color:transparent;");
        
        importTitleLayout.getChildren().addAll(importTitle,importButtons);
        
        importList = new ListView<>(FXCollections.observableArrayList());
        importList.setMinWidth(400);
        
        importScrollPane = new ScrollPane();
        importScrollPane.setContent(importList);
        importScrollPane.setMaxWidth(500);
        importScrollPane.setMaxHeight(400);
        importsManager.getChildren().addAll(importTitleLayout,importScrollPane);
        
        variablesManager = new VBox(2);
        varTitleLayout = new HBox(5);
        varTitle = new Label("Variables: ");
        variableButtons = new HBox();
        addVariableButton = gui.initChildButton(variableButtons, PLUS_ICON.toString(), PLUS_VAR_TOOLTIP.toString(), false);
        removeVariableButton = gui.initChildButton(variableButtons, MINUS_ICON.toString(), MIN_VAR_TOOLTIP.toString(), false);
        //addVariableButton.setStyle("-fx-background-color:transparent;");
        
        varTitleLayout.getChildren().addAll(varTitle,variableButtons);
        
        varTable = new TableView();
        
        TableColumn nameCol = new TableColumn("Name");
        TableColumn typeCol = new TableColumn("Type");
        TableColumn<Variable,Boolean> staticCol = new TableColumn<Variable,Boolean>("Static");
        TableColumn accessCol = new TableColumn("Access");
        
        varTable.getColumns().addAll(nameCol, typeCol, staticCol, accessCol);
        varScrollPane = new ScrollPane();
        varScrollPane.setContent(varTable);
        varScrollPane.setMaxWidth(500);
        varScrollPane.setMaxHeight(400);
        variablesManager.getChildren().addAll(varTitleLayout,varScrollPane);
        
        methodsManager = new VBox(2);
        methTitleLayout = new HBox(5);
        methTitle = new Label("Methods: ");
        methodButtons = new HBox();
        methTitleLayout.getChildren().add(methTitle);
        addMethodButton = gui.initChildButton(methodButtons, PLUS_ICON.toString(), PLUS_METH_TOOLTIP.toString(), false);
        removeMethodButton = gui.initChildButton(methodButtons, MINUS_ICON.toString(), MIN_METH_TOOLTIP.toString(), false);
        methTitleLayout.getChildren().add(methodButtons);
        methTable = new TableView();
        TableColumn nameMCol = new TableColumn("Name");
        TableColumn returnCol = new TableColumn("Return");
        TableColumn staticMCol = new TableColumn("Static");
        TableColumn abstractCol = new TableColumn("Abstract");
        TableColumn accessMCol = new TableColumn("Access");
        TableColumn arg1Col = new TableColumn("Arg1");
        TableColumn arg2Col = new TableColumn("Arg2");
        TableColumn arg3Col = new TableColumn("Arg3");
        TableColumn arg4Col = new TableColumn("Arg4");
       
        
        methTable.getColumns().addAll(nameMCol,returnCol,staticMCol,abstractCol,accessMCol,arg1Col,arg2Col,arg3Col,arg4Col);
        methScrollPane = new ScrollPane();
        methScrollPane.setContent(methTable);
        methScrollPane.setMaxWidth(500);
        methScrollPane.setMaxHeight(400);
        methodsManager.getChildren().addAll(methTitleLayout,methScrollPane);
        
        canvas = new Pane();
        canvas.setStyle("-fx-background-color:lightyellow;");
        canvas.setPrefWidth(1700);
        canvas.setPrefHeight(1000);
        //canvas.getChildren().add(new Label("HELLO"));
        
        canvasScrollPane = new ScrollPane();
        
        canvasScrollPane.setContent(canvas);
        canvasScrollPane.setPrefWidth(785);
        canvasScrollPane.setPrefHeight(700);
        //canvasScrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        //canvasScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        //canvasScrollPane.setPrefViewportWidth(500);
        //canvasScrollPane.setPrefViewportHeight(500);
        
        editToolbar = new VBox(17);
        editToolbar.getChildren().addAll(fieldManager,parentScrollPane,importsManager,variablesManager,methodsManager);
        toolbarScrollPane = new ScrollPane();
        toolbarScrollPane.setContent(editToolbar);
        
        
        workspace = new BorderPane();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        canvasScrollPane.setLayoutX(bounds.getMinX());
        canvasScrollPane.setLayoutY(bounds.getMinY());
        canvasScrollPane.setPrefWidth(bounds.getWidth()-530);
        canvasScrollPane.setPrefHeight(bounds.getHeight()-100);
        //BorderPane bp = new BorderPane();
        //bp.setLeft(canvasScrollPane);
        //bp.setRight(editToolbar);
        
        
        canvas.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observe, Object oldValue, Object newValue){
                canvasScrollPane.setVvalue((Double)newValue);
            }
        });
        
        DataManager dm = (DataManager)app.getDataComponent();
        /*
        Polygon polygon = dm.createPolyAction(new Polygon());
        polygon.getPoints().addAll(new Double[]{
            12.5, 37.5,//endX,endY
            37.5, 12.5,
            62.5, 37.5,
            37.5, 62.5});
        
        Polygon polygon1 = dm.createPolyAction(new Polygon());
        polygon1.getPoints().addAll(new Double[]{
            87.5, 62.5,
            112.5, 87.5, //endX,endY
            87.5, 112.5});
        
        //Polygon polygon2 = dm.createPolyAction(new Polygon());
        //polygon2.getPoints().addAll(new Double[]{
            //157.5, 137.5, //endX,endY
            //137.5, 112.5,
            //162.5, 137.5,
            //137.5, 162.5});
        
        DraggableLine l1 = dm.createLineAction(new DraggableLine(0,37.5,12.5,40.5,1));
        l1.setStrokeWidth(2.0);
        DraggableLine l2 = dm.createLineAction(new DraggableLine(0,87.5,87.5,87.5,1));
        l2.setStrokeWidth(2.0);
        DraggableLine l3 = dm.createLineAction(new DraggableLine(0,137.5,157.5,137.50,4));
        l3.setUses(dm.createPolyAction(l3.getUses()));
        //l3.setStrokeWidth(2.0);
        
        
        l1.endXProperty().bind(polygon.translateXProperty());
        l1.endYProperty().bind(polygon.translateYProperty());
        
        l2.endXProperty().bind(polygon1.translateXProperty());
        l2.endYProperty().bind(polygon1.translateYProperty());
        
        //l3.endXProperty().bind(polygon2.translateXProperty());
        //l3.endYProperty().bind(polygon2.translateYProperty());
        
        
        System.out.println(polygon1.toString());
        canvas.getChildren().addAll(polygon,polygon1,l3.getUses(),l1,l2,l3);*/
        
        //((BorderPane)workspace).setCenter(bp);
        ((BorderPane)workspace).setLeft(canvasScrollPane);
        ((BorderPane)workspace).setRight(toolbarScrollPane);
        
        gui.getWindow().widthProperty().addListener( 
            new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, 
                            Object oldValue, Object newValue) {
                Double width = (Double)newValue;
                canvasScrollPane.setPrefWidth(width-530);
            }
        });
        
        /*
        gui.getWindow().heightProperty().addListener(
            new ChangeListener() {
            public void changed(ObservableValue observable, 
                            Object oldValue, Object newValue) {
                Double height = (Double)newValue;
                canvasScrollPane.setPrefHeight(height);
            }
        });*/
        
    }
    
    public void setDebugText(String text) {
	debugText.setText(text);
    }
    
    
    private void setupHandlers() {
        
        classNameText.setOnAction(e ->{
           DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    dataManager.updateSelectedClassName();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    dataManager.updateSelectedInterfaceName();
           }
        });
        
        packageNameText.setOnAction(e ->{
           DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    dataManager.updateSelectedClassPackage();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    dataManager.updateSelectedInterfacePackage();
           }
           //dataManager.updateSelectedPackage();
        });
        
        canvas.setOnMouseClicked(e ->{
            DataManager dataManager = (DataManager) app.getDataComponent();
            if(dataManager.getClassEnable())
                dataManager.handleAddClassCanvas(e.getX(),e.getY());
            else if(dataManager.getInterfaceEnable())
                dataManager.handleAddInterfaceCanvas(e.getX(),e.getY());
        });
        
        addVariableButton.setOnAction(e ->{
            DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    ((DraggableClass)dataManager.getSelectedItem().get(0)).addVariableRow();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    ((DraggableInterface)dataManager.getSelectedItem().get(0)).addVariableRow();
           }
        });
        
        removeVariableButton.setOnAction(e ->{
            DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    ((DraggableClass)dataManager.getSelectedItem().get(0)).removeVariableRow();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    ((DraggableInterface)dataManager.getSelectedItem().get(0)).removeVariableRow();
           }
        });
        
        addMethodButton.setOnAction(e ->{
           DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    ((DraggableClass)dataManager.getSelectedItem().get(0)).addMethodRow();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    ((DraggableInterface)dataManager.getSelectedItem().get(0)).addMethodRow();
           }
        });
        
        removeMethodButton.setOnAction(e ->{
           DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    ((DraggableClass)dataManager.getSelectedItem().get(0)).removeMethodRow();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    ((DraggableInterface)dataManager.getSelectedItem().get(0)).removeMethodRow();
           }
        });
        
        addImportButton.setOnAction(e ->{
           DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
               
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    ((DraggableClass)dataManager.getSelectedItem().get(0)).addImportRow();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                    ((DraggableInterface)dataManager.getSelectedItem().get(0)).addImportRow();
           }
        });
        
        removeImportButton.setOnAction(e ->{
           DataManager dataManager = (DataManager) app.getDataComponent();
           if(!dataManager.getSelectedItem().isEmpty()){
                if(dataManager.getSelectedItem().get(0) instanceof DraggableClass)
                    ((DraggableClass)dataManager.getSelectedItem().get(0)).removeImportRow();
                else if(dataManager.getSelectedItem().get(0) instanceof DraggableInterface)
                   ((DraggableInterface)dataManager.getSelectedItem().get(0)).removeImportRow();
           }
        });
        
        refresh.setOnAction(e ->{
            DataManager dm = (DataManager)app.getDataComponent();
            for(Node n: canvas.getChildren()){
                if(n instanceof DraggableClass){
                    if(!((DraggableClass)n).getParentList().getItems().isEmpty()){
                        String s = ((DraggableClass)n).getParentList().getItems().get(0).getClassName();
                        System.out.println(s);
                        for(Node n2: canvas.getChildren()){
                            if(n2 instanceof DraggableClass){
                            if(((DraggableClass)n2).getClassName().equals(s)){//CHECK
                                //DraggableLine dl = dm.createLineAction(new DraggableLine(
                                //dm.getSelectedItem().get(0).getTranslateX(),dm.getSelectedItem().get(0).getTranslateY(),
                                //((DraggableClass)n2).getTranslateX(),((DraggableClass)n2).getTranslateY(),1));
                                //dl.setInheritance(dm.createPolyAction(dl.getInheritance()));
                                DoubleProperty startX = new SimpleDoubleProperty(100);
                                DoubleProperty startY = new SimpleDoubleProperty(100);
                                DoubleProperty endX   = new SimpleDoubleProperty(300);
                                DoubleProperty endY   = new SimpleDoubleProperty(200);

                                ((DraggableClass)n).bindLine(startX, startY);
                                ((DraggableClass)n2).bindLine(endX, endY);

                                DraggableLine dl = new DraggableLine(n.getLayoutX(),n.getLayoutY(),n2.getLayoutX(),n2.getLayoutY(),1);
                                dl = dm.createLineAction(dl);
                                dl.bindNow(startX, startY, endX, endY);
                                canvas.getChildren().addAll(dl);
                                break;
                            }}
                        }
                        break;
                    }
                    
                    else if(!((DraggableClass)n).getInterfaceList().getItems().isEmpty()){
                        String s = ((DraggableClass)n).getInterfaceList().getItems().get(0).getInterfaceName();
                        System.out.println(s);
                        for(Node n2: canvas.getChildren()){
                            if(n2 instanceof DraggableInterface){
                            if(((DraggableInterface)n2).getInterfaceName().equals(s)){//CHECK
                                //DraggableLine dl = dm.createLineAction(new DraggableLine(
                                //dm.getSelectedItem().get(0).getTranslateX(),dm.getSelectedItem().get(0).getTranslateY(),
                                //((DraggableClass)n2).getTranslateX(),((DraggableClass)n2).getTranslateY(),1));
                                //dl.setInheritance(dm.createPolyAction(dl.getInheritance()));
                                DoubleProperty startX = new SimpleDoubleProperty(100);
                                DoubleProperty startY = new SimpleDoubleProperty(100);
                                DoubleProperty endX   = new SimpleDoubleProperty(300);
                                DoubleProperty endY   = new SimpleDoubleProperty(200);

                                ((DraggableClass)n).bindLine(startX, startY);
                                ((DraggableInterface)n2).bindLine(endX, endY);

                                DraggableLine dl = new DraggableLine(n.getLayoutX(),n.getLayoutY(),n2.getLayoutX(),n2.getLayoutY(),2);
                                dl = dm.createLineAction(dl);
                                dl.bindNow(startX, startY, endX, endY);
                                canvas.getChildren().addAll(dl);
                                break;
                            }}
                        }
                        break;
                    }
                }
                
                else if(n instanceof DraggableInterface){
                    if(!((DraggableInterface)n).getParentList().getItems().isEmpty()){
                        String s = ((DraggableInterface)n).getParentList().getItems().get(0).getInterfaceName();
                        System.out.println(s);
                        for(Node n2: canvas.getChildren()){
                            if(n2 instanceof DraggableInterface){
                            if(((DraggableInterface)n2).getInterfaceName().equals(s)){//CHECK
                                //DraggableLine dl = dm.createLineAction(new DraggableLine(
                                //dm.getSelectedItem().get(0).getTranslateX(),dm.getSelectedItem().get(0).getTranslateY(),
                                //((DraggableClass)n2).getTranslateX(),((DraggableClass)n2).getTranslateY(),1));
                                //dl.setInheritance(dm.createPolyAction(dl.getInheritance()));
                                DoubleProperty startX = new SimpleDoubleProperty(100);
                                DoubleProperty startY = new SimpleDoubleProperty(100);
                                DoubleProperty endX   = new SimpleDoubleProperty(300);
                                DoubleProperty endY   = new SimpleDoubleProperty(200);

                                ((DraggableInterface)n).bindLine(startX, startY);
                                ((DraggableInterface)n2).bindLine(endX, endY);

                                DraggableLine dl = new DraggableLine(n.getLayoutX(),n.getLayoutY(),n2.getLayoutX(),n2.getLayoutY(),1);
                                dl = dm.createLineAction(dl);
                                dl.bindNow(startX, startY, endX, endY);
                                canvas.getChildren().addAll(dl);
                                break;
                            }}
                        }
                        break;
                    }
                    
                }
            }
        });
        
        
    }
    
    @Override
    public Pane getCanvas() {
	return canvas;
    }
    
    public void setImage(ButtonBase button, String fileName) {
	// LOAD THE ICON FROM THE PROVIDED FILE
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + fileName;
        Image buttonImage = new Image(imagePath);
	
	// SET THE IMAGE IN THE BUTTON
        button.setGraphic(new ImageView(buttonImage));	
    }

    /**
     * This function specifies the CSS style classes for all the UI components
     * known at the time the workspace is initially constructed. Note that the
     * tag editor controls are added and removed dynamicaly as the application
     * runs so they will have their style setup separately.
     */
    @Override
    public void initStyle() {
	// NOTE THAT EACH CLASS SHOULD CORRESPOND TO
	// A STYLE CLASS SPECIFIED IN THIS APPLICATION'S
	// CSS FILE
	canvas.getStyleClass().add(CLASS_RENDER_CANVAS);
        editToolbar.getStyleClass().add("right_toolbar");
    }

    /**
     * This function reloads all the controls for editing tag attributes into
     * the workspace.
     */
    @Override
    public void reloadWorkspace() {
	DataManager dataManager = (DataManager)app.getDataComponent();
        
        
        update.markAsEdited(gui);
        
    }
    
    public void loadSelectedShapeSettings(Shape shape) {

    }
    
    public TextField getClassNameText(){
        return classNameText;
    }
    
    public TextField getPackageNameText(){
        return packageNameText;
    }
    
    public ScrollPane getImportScrollPane(){
        return importScrollPane;
    }
    
    public ScrollPane getVarScrollPane(){
        return varScrollPane;
    }
    
    public ScrollPane getMethScrollPane(){
        return methScrollPane;
    }
    
    @Override
    public ScrollPane getCanvasScrollPane(){
        return canvasScrollPane;
    }
    
    public ScrollPane getParentScrollPane(){
        return parentScrollPane;
    }
    
    public void disableButtons(boolean enable){
        classNameText.setDisable(enable);
        packageNameText.setDisable(enable);
        addVariableButton.setDisable(enable);
        removeVariableButton.setDisable(enable);
        addMethodButton.setDisable(enable);
        removeMethodButton.setDisable(enable);
        addImportButton.setDisable(enable);
        removeImportButton.setDisable(enable);
        parentButton.setDisable(enable);
        interfaceButton.setDisable(enable);
        addExternalButton.setDisable(enable);
        removeExternalButton.setDisable(enable);
        externalParentButton.setDisable(enable);
        externalInterfaceButton.setDisable(enable);
        removeParentButton.setDisable(enable);
        removeInterfaceButton.setDisable(enable);
    }
}
