package jcd.data;

import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.util.Callback;
import static jcd.PropertyType.MINUS_ICON;
import static jcd.PropertyType.MIN_IMPORT_TOOLTIP;
import static jcd.PropertyType.PLUS_ICON;
import static jcd.PropertyType.PLUS_IMPORT_TOOLTIP;
import saf.ui.AppMessageDialogSingleton;

/**
 * This is a draggable class for our pose application.
 * 
 * @author Dennis Sosa
 * @version 1.0
 */

public class DraggableClass extends VBox {    
    Text className;
    Text variables;
    Text methods;
    String packName;
    
    HBox classBox;
    HBox varBox;
    HBox methBox;
    
    boolean abstractClass;
    boolean concreteClass;
    
    String classString;
    
    ArrayList<Variable> variablesList;
    ArrayList<Method> methodList;
    ArrayList<String> imports;
    
    TableView<Variable> variableTable;
    final ObservableList<Variable> variableData;
    TableColumn nameCol;
    TableColumn typeCol;
    TableColumn<Variable,Boolean> staticCol;
    TableColumn accessCol;
    ArrayList<String> variableText;
    
    TableView<Method> methodTable;
    final ObservableList<Method> methodData;
    TableColumn nameMCol;
    TableColumn returnCol;
    TableColumn<Method,Boolean> staticMCol;
    TableColumn<Method,Boolean> abstractCol;
    TableColumn accessMCol;
    TableColumn arg1Col;
    TableColumn arg2Col;
    TableColumn arg3Col;
    TableColumn arg4Col;
    TableColumn arg5Col;
    TableColumn arg6Col;
    TableColumn arg7Col;
    TableColumn arg8Col;
    TableColumn arg9Col;
    TableColumn arg10Col;
    ArrayList<String> methodText;
    
    ListView<String> importList;
    final ObservableList<String> importData;
    
    //ArrayList<DraggableClass> inheritParent;
    ArrayList<String> implementParents;
    
    String parentName;
    String interfaceName;
    
    TableView<VBox> paneList;
    final ObservableList<VBox> paneData;
    TableColumn paneNameCol;
    
    ListView<String> externalList;
    final ObservableList<String> externalData;
    
    TableView<DraggableClass> parentList;
    final ObservableList<DraggableClass> parentData;
    TableColumn parentNameCol;
    
    TableView<DraggableInterface> interfaceList;
    final ObservableList<DraggableInterface> interfaceData;
    TableColumn interfaceNameCol;
    
    Label classInterfaceLabel;
    Label parentCol;
    Label interfaceCol;
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
    
    ArrayList<String> externalStorage;
    ArrayList<String> paneStorage;
    
    public DraggableClass(boolean abstractClass){
        
        this.abstractClass = abstractClass;
        methodList = new ArrayList();
        variablesList = new ArrayList();
        imports = new ArrayList();
        variableText = new ArrayList();
        methodText = new ArrayList();
        //inheritParent = new ArrayList();
        implementParents = new ArrayList();
        externalStorage = new ArrayList();
        paneStorage = new ArrayList();
        
        classBox = new HBox();
        
        if(this.abstractClass)
            className = new Text(10, 50, "{abstract}"+"\n"+"Default");
        else
            className = new Text(10, 50, "Default");
        
        className.setWrappingWidth(250);
        classBox.getChildren().add(className);
        classBox.setStyle("-fx-border-width: 1px; -fx-border-color: #000000;-fx-background-color:#FFFFFF;-fx-padding:10px;");
        
        varBox = new HBox();
        variables = new Text(10, 50, "");
        variables.setWrappingWidth(250);
        varBox.getChildren().add(variables);
        varBox.setStyle("-fx-border-width: 1px; -fx-border-color: #000000; -fx-background-color:#FFFFFF;-fx-padding:10px;");
        
        methBox = new HBox();
        methods = new Text(10, 50, "");
        methods.setWrappingWidth(250);
        methBox.getChildren().add(methods);
        methBox.setStyle("-fx-border-width: 1px; -fx-border-color: #000000; -fx-background-color:#FFFFFF;-fx-padding:10px;");
        
        packName = "default";
        
        getChildren().addAll(classBox,varBox,methBox);
        
        parentName = "";
        interfaceName = "";
        classString = "Default";
        
        importData = FXCollections.observableArrayList();
        importList = new ListView<>(importData);
        
        importList.setEditable(true);
        importList.setMinWidth(400);
        importList.setCellFactory(TextFieldListCell.forListView());
        
        importList.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
		importList.getItems().set(t.getIndex(), t.getNewValue());
                imports.set(t.getIndex(),t.getNewValue());
            }
						
	});
        
        paneData = FXCollections.observableArrayList();
        paneList = new TableView();
        paneNameCol = new TableColumn("Name");
        paneNameCol.setMinWidth(150);
        paneNameCol.setCellValueFactory(
                new PropertyValueFactory<VBox, String>("name"));
        paneList.setItems(paneData);
        paneList.getColumns().add(paneNameCol);
        
        externalData = FXCollections.observableArrayList();
        externalList = new ListView<>(externalData);
        
        externalList.setEditable(true);
        externalList.setMinWidth(400);
        externalList.setCellFactory(TextFieldListCell.forListView());
        
        externalList.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
		externalList.getItems().set(t.getIndex(), t.getNewValue());
                externalStorage.set(t.getIndex(),t.getNewValue());
            }
						
	});
        
        parentData = FXCollections.observableArrayList();
        parentList = new TableView();
        parentNameCol = new TableColumn("Name");
        parentNameCol.setMinWidth(150);
        parentNameCol.setCellValueFactory(
                new PropertyValueFactory<DraggableClass, String>("className"));
        parentList.setItems(parentData);
        parentList.getColumns().add(parentNameCol);
        
        interfaceData = FXCollections.observableArrayList();
        interfaceList = new TableView();
        interfaceNameCol = new TableColumn("Name");
        interfaceNameCol.setMinWidth(150);
        interfaceNameCol.setCellValueFactory(
                new PropertyValueFactory<DraggableInterface, String>("interfaceName"));
        interfaceList.setItems(interfaceData);
        interfaceList.getColumns().add(interfaceNameCol);
        
        parentGrid = new GridPane();
        parentGrid.setPadding(new Insets(5));
        parentGrid.setHgap(10);
        parentGrid.setVgap(15);
        
        classInterfaceLabel = new Label("Workspace Classes/Interfaces");
        GridPane.setHalignment(classInterfaceLabel, HPos.CENTER);
        parentGrid.add(classInterfaceLabel, 0, 0);
        
        parentButton = new Button("Parent");
        interfaceButton = new Button("Interface");
        
        parentButton.setOnAction(e -> {
            DraggableClass potential =(DraggableClass) paneList.getSelectionModel().getSelectedItem();
            if (potential != null && (parentData.size()<1)) {
                paneList.getSelectionModel().clearSelection();
                paneData.remove(potential);
                parentData.add(potential);
                parentName = potential.getClassName();
                paneStorage.add(potential.getClassName());
            }
        });
        
        interfaceButton.setOnAction(e -> {
            DraggableInterface potential = (DraggableInterface) paneList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                paneList.getSelectionModel().clearSelection();
                paneData.remove(potential);
                interfaceData.add(potential);
                implementParents.add(potential.getInterfaceName());
                paneStorage.add(potential.getInterfaceName());
            }
        });
        
        h1 = new HBox();
        h1.getChildren().addAll(new Label("Specify Parent: "),parentButton,interfaceButton);
        h1.setAlignment(Pos.CENTER);
        parentGrid.add(h1,0,1);
        
        parentGrid.add(paneList, 0, 2);
        
        h2 = new HBox();
        addExternalButton = new Button();
        Image plus = new Image("file:./images/Plus.png");
        addExternalButton.setGraphic(new ImageView(plus));
        
        addExternalButton.setOnAction(e ->{
           addExternalRow(); 
        });
        
        removeExternalButton = new Button();
        Image min = new Image("file:./images/Minus.png");
        removeExternalButton.setGraphic(new ImageView(min));
        
        removeExternalButton.setOnAction(e ->{
            removeExternalRow();
        });
        
        h2.getChildren().addAll(new Label("Add/Remove External Class/Interface: "),addExternalButton,removeExternalButton);
        h2.setAlignment(Pos.CENTER);
        parentGrid.add(h2,1,0);
        
        externalParentButton = new Button("Parent");
        externalInterfaceButton = new Button("Interface");
        h3 = new HBox();
        h3.getChildren().addAll(new Label("Specify Selected Parent: "),externalParentButton,externalInterfaceButton);
        h3.setAlignment(Pos.CENTER);
        parentGrid.add(h3,1,1);
        
        externalParentButton.setOnAction(e -> {
            String potential = externalList.getSelectionModel().getSelectedItem();
            if (potential != null && (parentData.size()<1)) {
                DraggableClass dcTemp = new DraggableClass(false);
                dcTemp.setClassName(potential);
                externalList.getSelectionModel().clearSelection();
                externalData.remove(potential);
                parentData.add(dcTemp);
                parentName = potential;
            }
        });
        
        externalInterfaceButton.setOnAction(e -> {
            String potential = externalList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                DraggableInterface diTemp = new DraggableInterface();
                diTemp.setInterfaceName(potential);
                externalList.getSelectionModel().clearSelection();
                externalData.remove(potential);
                interfaceData.add(diTemp);
                implementParents.add(potential);
            }
        });
        
        parentGrid.add(externalList, 1, 2);
        
        parentCol = new Label("Specified Parent(s)");
        GridPane.setHalignment(parentCol, HPos.CENTER);
        parentGrid.add(parentCol, 2, 0);
        
        h4 = new HBox();
        removeParentButton = new Button();
        removeParentButton.setGraphic(new ImageView(min));
        h4.getChildren().addAll(new Label("Remove Selected Parent: "),removeParentButton);
        h4.setAlignment(Pos.CENTER);
        parentGrid.add(h4,2,1);
        
        removeParentButton.setOnAction(e -> {
            DraggableClass potential = (DraggableClass) parentList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                parentList.getSelectionModel().clearSelection();
                parentData.remove(potential);
                if(externalStorage.contains(potential.getClassName())){
                    externalData.add(potential.getClassName());
                    externalList.getSelectionModel().select(potential.getClassName());
                }
                else if(paneStorage.contains(potential.getName())){
                    paneData.add(potential);
                    paneList.getSelectionModel().select(potential);
                }
                paneStorage.remove(potential.getClassName());
                parentName = "";
            }
        });
        
        parentGrid.add(parentList, 2, 2);
        
        interfaceCol = new Label("Specified Interfaces");
        GridPane.setHalignment(interfaceCol, HPos.CENTER);
        parentGrid.add(interfaceCol, 3, 0);
        
        h5 = new HBox();
        removeInterfaceButton = new Button();
        removeInterfaceButton.setGraphic(new ImageView(min));
        h5.getChildren().addAll(new Label("Remove Selected Interface: "),removeInterfaceButton);
        h5.setAlignment(Pos.CENTER);
        parentGrid.add(h5,3,1);
        
        removeInterfaceButton.setOnAction(e -> {
            DraggableInterface potential = (DraggableInterface)interfaceList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                interfaceList.getSelectionModel().clearSelection();
                interfaceData.remove(potential);
                if(externalStorage.contains(potential.getName())){
                    externalData.add(potential.getName());
                    externalList.getSelectionModel().select(potential.getName());
                }
                else if(paneStorage.contains(potential.getName())){
                    paneData.add(potential);
                    paneList.getSelectionModel().select(potential);
                }
                paneStorage.remove(potential.getInterfaceName());
                implementParents.remove(potential.getInterfaceName());
            }
        });
        
        parentGrid.add(interfaceList, 3, 2);
        
        variableTable = new TableView();
        
        variableData = FXCollections.observableArrayList();
        variableTable.setEditable(true);
        
        nameCol = new TableColumn("Name");
        typeCol = new TableColumn("Type");
        accessCol = new TableColumn("Access");
        staticCol = new TableColumn<Variable,Boolean>("Static");
 
        staticCol.setCellValueFactory(new PropertyValueFactory<Variable, Boolean>("isStatic"));
        
        // set the cell factory in the static TableColumn
        staticCol.setMinWidth(100);
        //staticCol.setCellFactory(CheckBoxTableCell.forTableColumn(staticCol));
        
        staticCol.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Integer param) {
                System.out.println("Variable "+variableData.get(param).getName()+" changed value to " +variableData.get(param).isStatic());
                
                String tempS = "";
                Variable varTemp = variableData.get(param);
                if(!variableText.isEmpty()){
                variableText.set(variablesList.indexOf(varTemp),      
                    (varTemp.getAccessType().equals("private")?"-":varTemp.getAccessType().equals("public")?"+":
                    varTemp.getAccessType().equals("protected")?"#":"~")+(varTemp.isStatic()?"$ ":" ")
                    +varTemp.getName()+":"+varTemp.getType()+"\n");
                        
                for(String s: variableText){
                    tempS+=s;
                }
                
                variables.setText(tempS);
                }
                return variableData.get(param).isStaticProperty();
            }
        }));
        
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Variable, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Variable, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Variable, String> t) {
                    boolean badFlag = false;
                    for(Variable v: variablesList){
                        if(v.getName().equals(t.getNewValue())){
                            badFlag = true;
                            break;
                        }
                    }
                    if(badFlag){
                        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                        dialog.show("Edit Variable Error", "ERROR: Variable name already exists.");
                    }
                    else{
                    ((Variable) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setName(t.getNewValue());
                        
                        String tempS = "";
                        Variable varTemp = ((Variable) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                        variableText.set(variablesList.indexOf(varTemp),      
                        (varTemp.getAccessType().equals("private")?"-":varTemp.getAccessType().equals("public")?"+":
                        varTemp.getAccessType().equals("protected")?"#":"~")+(varTemp.isStatic()?"$ ":" ")
                        +varTemp.getName()+":"+varTemp.getType()+"\n");
                        
                        for(String s: variableText){
                            tempS+=s;
                        }
        
                        variables.setText(tempS);
                    }
                }
            }
        );
        
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(
                new PropertyValueFactory<Variable, String>("type"));
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Variable, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Variable, String> t) {
                    ((Variable) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setType(t.getNewValue());
                    
                    String tempS = "";
                    Variable varTemp = ((Variable) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    variableText.set(variablesList.indexOf(varTemp),      
                    (varTemp.getAccessType().equals("private")?"-":varTemp.getAccessType().equals("public")?"+":
                    varTemp.getAccessType().equals("protected")?"#":"~")+(varTemp.isStatic()?"$ ":" ")
                    +varTemp.getName()+":"+varTemp.getType()+"\n");
                        
                    for(String s: variableText){
                        tempS+=s;
                    }
        
                    variables.setText(tempS);
                }
            }
        );
        
        accessCol.setMinWidth(100);
        accessCol.setCellValueFactory(
                new PropertyValueFactory<Variable, String>("accessType"));
        accessCol.setCellFactory(TextFieldTableCell.forTableColumn());
        accessCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Variable, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Variable, String> t) {
                    ((Variable) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setAccessType(t.getNewValue());
                        
                        String tempS = "";
                        Variable varTemp = ((Variable) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                        variableText.set(variablesList.indexOf(varTemp),      
                        (varTemp.getAccessType().equals("private")?"-":varTemp.getAccessType().equals("public")?"+":
                        varTemp.getAccessType().equals("protected")?"#":"~")+(varTemp.isStatic()?"$ ":" ")
                        +varTemp.getName()+":"+varTemp.getType()+"\n");
                        
                        for(String s: variableText){
                            tempS+=s;
                        }
        
                        variables.setText(tempS);
                }
            }
        );
 
        variableTable.setItems(variableData);
        
        variableTable.getColumns().addAll(nameCol, typeCol, accessCol, staticCol);
        
        methodTable = new TableView();
        methodData = FXCollections.observableArrayList();
        methodTable.setEditable(true);
        
        nameMCol = new TableColumn("Name");
        returnCol = new TableColumn("Return");
        accessMCol = new TableColumn("Access");
        staticMCol = new TableColumn<Method,Boolean>("Static");
        abstractCol = new TableColumn<Method,Boolean>("Abstract");
        arg1Col = new TableColumn("Arg1");
        arg2Col = new TableColumn("Arg2");
        arg3Col = new TableColumn("Arg3");
        arg4Col = new TableColumn("Arg4");
        arg5Col = new TableColumn("Arg5");
        arg6Col = new TableColumn("Arg6");
        arg7Col = new TableColumn("Arg7");
        arg8Col = new TableColumn("Arg8");
        arg9Col = new TableColumn("Arg9");
        arg10Col = new TableColumn("Arg10");
        
        staticMCol.setCellValueFactory(new PropertyValueFactory<Method, Boolean>("isStatic"));
        
        // set the cell factory in the static TableColumn
        staticMCol.setMinWidth(100);
        //staticCol.setCellFactory(CheckBoxTableCell.forTableColumn(staticCol));
        
        staticMCol.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Integer param) {
                
                String tempS = "";
                String tempA = "";
                Method methTemp = methodData.get(param);
                
                for(Variable v: methTemp.getArguments()){
                    tempA += (v.getName()+":"+v.getType());
                    if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                        tempA += ", ";
                    }
                }
                if(!methodText.isEmpty()){
                methodText.set(methodList.indexOf(methTemp),      
                    (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                    methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                    +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                for(String s: methodText){
                    tempS+=s;
                }
                
                methods.setText(tempS);
                }
                return methodData.get(param).isStaticProperty();
            }
        }));
        
        abstractCol.setCellValueFactory(new PropertyValueFactory<Method, Boolean>("isAbstract"));
        
        // set the cell factory in the static TableColumn
        abstractCol.setMinWidth(100);
        //staticCol.setCellFactory(CheckBoxTableCell.forTableColumn(staticCol));
        
        abstractCol.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Integer param) {
                
                String tempS = "";
                String tempA = "";
                Method methTemp = methodData.get(param);
                
                for(Variable v: methTemp.getArguments()){
                    tempA += (v.getName()+":"+v.getType());
                    if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                        tempA += ", ";
                    }
                }
                if(!methodText.isEmpty()){
                methodText.set(methodList.indexOf(methTemp),      
                    (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                    methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                    +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                for(String s: methodText){
                    tempS+=s;
                }
                
                methods.setText(tempS);
                }
                if(methodData.get(param).isAbstract())
                    setAbstract(true);
                return methodData.get(param).isAbstractProperty();
            }
        }));
        
        nameMCol.setMinWidth(150);
        nameMCol.setCellValueFactory(
                new PropertyValueFactory<Method, String>("name"));
        nameMCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameMCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                }
            }
        );
        
        returnCol.setMinWidth(100);
        returnCol.setCellValueFactory(
                new PropertyValueFactory<Method, String>("returnType"));
        returnCol.setCellFactory(TextFieldTableCell.forTableColumn());
        returnCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setReturnType(t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                }
            }
        );
        
        accessMCol.setMinWidth(100);
        accessMCol.setCellValueFactory(
                new PropertyValueFactory<Method, String>("accessType"));
        accessMCol.setCellFactory(TextFieldTableCell.forTableColumn());
        accessMCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setAccessType(t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                }
            }
        );
        
        arg1Col.setMinWidth(100);
        arg1Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg1"));
        arg1Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg1Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg1",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg1");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg2Col.setMinWidth(100);
        arg2Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg2"));
        arg2Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg2Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg2",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg2");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg3Col.setMinWidth(100);
        arg3Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg3"));
        arg3Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg3Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg3",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg3");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg4Col.setMinWidth(100);
        arg4Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg4"));
        arg4Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg4Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg4",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg4");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg5Col.setMinWidth(100);
        arg5Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg5"));
        arg5Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg5Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg5",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg5");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg6Col.setMinWidth(100);
        arg6Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg6"));
        arg6Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg6Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg6",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg6");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg7Col.setMinWidth(100);
        arg7Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg7"));
        arg7Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg7Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg7",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg7");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg8Col.setMinWidth(100);
        arg8Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg8"));
        arg8Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg8Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg8",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg8");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg9Col.setMinWidth(100);
        arg9Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg9"));
        arg9Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg9Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg9",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg9");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        arg10Col.setMinWidth(100);
        arg10Col.setCellValueFactory(
                new PropertyValueFactory<Method, String>("arg10"));
        arg10Col.setCellFactory(TextFieldTableCell.forTableColumn());
        arg10Col.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Method, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Method, String> t) {
                    /*boolean badFlag = false;
                    for(Method m: methodList){
                        if(m.getName().equals(t.getNewValue())){
                            badFlag = true;
                            break;
                        }
                    }
                    
                    if(badFlag){
                        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                        dialog.show("Edit Method Error", "ERROR: Method already exists.");
                    }*/
                    if(!t.getNewValue().equals("")){
                    ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).addParameter("arg10",t.getNewValue());
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                    else if(t.getNewValue().equals("")){
                        ((Method) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).removeParameter("arg10");
                        
                    String tempS = "";
                    String tempA = "";
                    
                    Method methTemp = ((Method) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                    
                    for(Variable v: methTemp.getArguments()){
                        tempA += (v.getName()+":"+v.getType());
                        if((methTemp.getArguments().indexOf(v)+1)!=methTemp.getArguments().size()){
                            tempA += ", ";
                        }
                    }
                    
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                        methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
 
        methodTable.setItems(methodData);
        methodTable.getColumns().addAll(nameMCol,returnCol,accessMCol,staticMCol,abstractCol,
            arg1Col,arg2Col,arg3Col,arg4Col,arg5Col,arg6Col,arg7Col,arg8Col,arg9Col,arg10Col);
        
        /*this.classBox.prefHeightProperty().bind(this.heightProperty());
        this.varBox.prefHeightProperty().bind(this.heightProperty());
        this.methBox.prefHeightProperty().bind(this.heightProperty());
        */
        
        //this.setMinWidth(this.getWidth());
        //this.setMinHeight(this.getHeight());
        
    }
    
    public String getName(){
        return classString;
    }
    
    public String getParentName(){
        return parentName;
    }
    
    public void setParentName(String s){
        parentName = s;
    }
    
    public String getInterfaceName(){
        return interfaceName;
    }
    
    public void setInterfaceName(String s){
        interfaceName = s;
    }
    
    public void addMethod(Method m){
        methodList.add(m);
    }
    
    public ArrayList<Method> getMethodList(){
        return methodList;
    }
    
    public void setPackageName(String s){
        packName = s;
    }
    
    public String getPackageName(){
        return packName;
    }
    
    public Text getClassText(){
        return className;
    }
    
    public String getClassName(){
        return classString;
    }
    
    public void setClassName(String s){
        classString = s;
    }
    
    public void setClassText(String s){
        if(abstractClass){
            className.setText("{abstract}"+"\n"+s);
        }
        else{
            className.setText(s);
        }
    }
    
    public Text getVarText(){
        return variables;
    }
    
    public void setVarText(String s){
        variables.setText(s);
    }
    
    public Text getMethText(){
        return methods;
    }
    
    public void setMethText(String s){
        methods.setText(s);
    }
    
    public boolean isAbstract(){
        return abstractClass;
    }
    
    public void setAbstract(boolean abstractClass){
        this.abstractClass = abstractClass;
    }
    
    public ArrayList<Variable> getVariableList(){
        return variablesList;
    }
    
    public void addVariable(Variable v){
        variablesList.add(v);
    }
    
    public void addImport(String i){
        imports.add(i);
    }
    
    public ArrayList<String> getImports(){
        return imports;
    }
    
    public TableView<Variable> getVariableTable(){
        return variableTable;
    }
    
    public ObservableList<Variable> getVariableData(){
        return variableData;
    }
    
    public TableView<Method> getMethodTable(){
        return methodTable;
    }
    
    public ObservableList<Method> getMethodData(){
        return methodData;
    }
    
    public ListView<String> getImportList(){
        return importList;
    }
    
    public HBox getClassBox(){
        return classBox;
    }
    
    public HBox getVarBox(){
        return varBox;
    }
    
    public HBox getMethBox(){
        return methBox;
    }
    
    public void addVariableRow(){
        int rand = variablesList.size()+1;
        Variable varTemp = new Variable(("defaultVar"+rand),"int","private",false);
        
        variableData.add(varTemp);
        variableTable.getSelectionModel().select(varTemp);
        variablesList.add(varTemp);
        
        variableText.add(
                (varTemp.getAccessType().equals("private")?"-":varTemp.getAccessType().equals("public")?"+":
                varTemp.getAccessType().equals("protected")?"#":"~")+(varTemp.isStatic()?"$ ":" ")
                +varTemp.getName()+":"+varTemp.getType()+"\n");
        
        variables.setText(variables.getText()+variableText.get(variableText.size()-1));
    }
    
    public void removeVariableRow(){
        if(variableTable.getSelectionModel().getSelectedItem()!=null){
            String tempVariableText = "";
            variablesList.remove(variableTable.getSelectionModel().getSelectedItem());
            for(String n: variableText){
                if(n.contains(variableTable.getSelectionModel().getSelectedItem().getName())){
                    variableText.remove(n);
                    break;
                }
            }
            
            for(String n: variableText){
                tempVariableText += n;
            }
            
            variables.setText(tempVariableText);
            variableData.remove(variableTable.getSelectionModel().getSelectedItem());
            
        }
        else{
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Remove Variable Error", "ERROR: No Variable is selected.");
        }
    }
    
    public void addMethodRow(){
        int rand = methodList.size()+1;
        Method methTemp = new Method("defaultMeth"+rand, "void", 0, "public");
        
        methodData.add(methTemp);
        methodTable.getSelectionModel().select(methTemp);
        methodList.add(methTemp);
        
        methodText.add(
                (methTemp.getAccessType().equals("private")?"-":methTemp.getAccessType().equals("public")?"+":
                methTemp.getAccessType().equals("protected")?"#":"~")+(methTemp.isStatic()?"$ ":" ")
                +methTemp.getName()+"("+")"+":"+methTemp.getReturnType()+(methTemp.isAbstract()?"{abstract}":"")+"\n");
        
        methods.setText(methods.getText()+methodText.get(methodText.size()-1));
    }
    
    public void removeMethodRow(){
        if(methodTable.getSelectionModel().getSelectedItem()!=null){
            String tempMethodText = "";
            methodList.remove(methodTable.getSelectionModel().getSelectedItem());
            for(String n: methodText){
                if(n.contains(methodTable.getSelectionModel().getSelectedItem().getName())){
                    methodText.remove(n);
                    break;
                }
            }
            
            for(String n: methodText){
                tempMethodText += n;
            }
            
            methods.setText(tempMethodText);
            methodData.remove(methodTable.getSelectionModel().getSelectedItem());
            
        }
        else{
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Remove Variable Error", "ERROR: No Variable is selected.");
        }
    }
    
    public void addImportRow(){
        int rand = imports.size()+1;
        String impTemp = "default"+rand;
        
        importData.add("default"+rand);
        imports.add(impTemp);
        importList.getSelectionModel().select(impTemp);
    }
    
    public void removeImportRow(){
        if(importList.getSelectionModel().getSelectedItem()!=null){
            imports.remove(importList.getSelectionModel().getSelectedItem());
            importData.remove(importList.getSelectionModel().getSelectedItem());
        }
        else{
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Remove Import Error", "ERROR: No Import is selected.");
        }
    }
    
    public void addExternalRow(){
        int rand = externalData.size()+1;
        String impTemp = "default"+rand;
        
        externalData.add(impTemp);
        externalList.getSelectionModel().select(impTemp);
        externalStorage.add(impTemp);
    }
    
    public void removeExternalRow(){
        if(externalList.getSelectionModel().getSelectedItem()!=null){
            externalData.remove(externalList.getSelectionModel().getSelectedItem());
            externalStorage.remove(externalList.getSelectionModel().getSelectedItem());
        }
        else{
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show("Remove Import Error", "ERROR: No Import is selected.");
        }
    }
    
    public void updatePaneList(Pane p){
        for(Node n: p.getChildren()){
            if(n instanceof DraggableClass){
                
                if(((DraggableClass)n).equals(this)){
                    //System.out.println(((DraggableClass)n).getClassName());
                    continue;
                }
                
                else if(!paneList.getItems().contains(((DraggableClass)n)) &&
                    !parentList.getItems().contains(((DraggableClass)n))){
                    //System.out.println(((DraggableClass)n).getClassName());
                    paneData.add(((DraggableClass)n));
                }
            }
            
            else if(n instanceof DraggableInterface){
                if(!paneList.getItems().contains(((DraggableInterface)n)) &&
                    !interfaceList.getItems().contains(((DraggableInterface)n))){
                    //System.out.println(((DraggableInterface)n).getInterfaceName());
                    paneData.add(((DraggableInterface)n));
                }
            }
            }
    }
    
    public ObservableList<String> getImportData(){
        return importData;
    }
    
    public TableView<VBox> getPaneList(){
        return paneList;
    }
    
    public ObservableList<VBox> getPaneData(){
        return paneData;
    }
    
    public ListView<String> getExternalList(){
        return externalList;
    }
    
    public ObservableList<String> getExternalData(){
        return externalData;
    }
    
    public TableView<DraggableClass> getParentList(){
        return parentList;
    }
    
    public ObservableList<DraggableClass> getParentData(){
        return parentData;
    }
    
    public TableView<DraggableInterface> getInterfaceList(){
        return interfaceList;
    }
    
    public ObservableList<DraggableInterface> getInterfaceData(){
        return interfaceData;
    }
    
    public GridPane getParentGrid(){
        return parentGrid;
    }
    
    public ArrayList<String> getImplementParents(){
        return implementParents;
    }
    
    public ArrayList<String> getExternalStorage(){
        return externalStorage;
    }
    
    public ArrayList<String> getPaneStorage(){
        return paneStorage;
    }
    
    public void bindLine(DoubleProperty x, DoubleProperty y) {
      x.bind(Bindings.add(layoutXProperty(),translateXProperty()));
      y.bind(Bindings.add(layoutYProperty(),translateYProperty()));
    }
    
    public ArrayList<String> getVariableText(){
        return variableText;
    }
    
    public ArrayList<String> getMethodText(){
        return methodText;
    }
}
