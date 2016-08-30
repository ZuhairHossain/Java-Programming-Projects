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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import static jcd.data.Variable.PUBLIC_TYPE;
import static jcd.data.Variable.STATIC_FINAL_TYPE;
import saf.ui.AppMessageDialogSingleton;

/**
 * This is a draggable class for our pose application.
 * 
 * @author Dennis Sosa
 * @version 1.0
 */
public class DraggableInterface extends VBox {    
    Text className;
    Text variables;
    Text methods;
    
    String packName;
    
    HBox classBox;
    HBox varBox;
    HBox methBox;
    
    ArrayList<String> inheritParents;
    
    ArrayList<Variable> variablesList;
    ArrayList<Method> methodList;
    ArrayList<String> imports;
    
    String name;
    
    TableView<Variable> variableTable;
    final ObservableList<Variable> variableData;
    TableColumn nameCol;
    TableColumn typeCol;
    TableColumn accessCol;
    TableColumn staticCol;
    ArrayList<String> variableText;
    ArrayList<String> methodText;
    
    TableView<Method> methodTable;
    final ObservableList<Method> methodData;
    TableColumn nameMCol;
    TableColumn returnCol;
    TableColumn accessMCol;
    TableColumn<Method,Boolean> staticMCol;
    TableColumn<Method,Boolean> abstractCol;
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
    
    ListView<String> importList;
    final ObservableList<String> importData;
    
    TableView<VBox> paneList;
    final ObservableList<VBox> paneData;
    TableColumn paneNameCol;
    
    ListView<String> externalList;
    final ObservableList<String> externalData;
    
    TableView<DraggableInterface> parentList;
    final ObservableList<DraggableInterface> parentData;
    TableColumn parentNameCol;
    
    Label classInterfaceLabel;
    Label parentCol;
    Label interfaceCol;
    GridPane parentGrid;
    HBox h1;
    Button parentButton;
    HBox h2;
    Button addExternalButton;
    Button removeExternalButton;
    HBox h3;
    Button externalParentButton;
    HBox h4;
    Button removeParentButton;
    
    ArrayList<String> externalStorage;
    ArrayList<String> paneStorage;
    
    public DraggableInterface(){
        methodList = new ArrayList();
        variablesList = new ArrayList();
        imports = new ArrayList();
        variableText = new ArrayList();
        methodText = new ArrayList();
        inheritParents = new ArrayList();
        
        classBox = new HBox();
        className = new Text(10, 50, "<<Interface>>"+"\n"+"Default");
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
        name = "Default";
        
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
                new PropertyValueFactory<DraggableInterface, String>("interfaceName"));
        parentList.setItems(parentData);
        parentList.getColumns().add(parentNameCol);
        
        parentGrid = new GridPane();
        parentGrid.setPadding(new Insets(5));
        parentGrid.setHgap(10);
        parentGrid.setVgap(15);
        
        classInterfaceLabel = new Label("Workspace Interfaces");
        GridPane.setHalignment(classInterfaceLabel, HPos.CENTER);
        parentGrid.add(classInterfaceLabel, 0, 0);
        
        parentButton = new Button("Parent");
        
        parentButton.setOnAction(e -> {
            DraggableInterface potential = (DraggableInterface)paneList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                paneList.getSelectionModel().clearSelection();
                paneData.remove(potential);
                parentData.add(potential);
                inheritParents.add(potential.getInterfaceName());
                paneStorage.add(potential.getInterfaceName());
            }
        });
        
        
        h1 = new HBox();
        h1.getChildren().addAll(new Label("Specify Parent: "),parentButton);
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
        
        h2.getChildren().addAll(new Label("Add/Remove External Interfaces: "),addExternalButton,removeExternalButton);
        h2.setAlignment(Pos.CENTER);
        parentGrid.add(h2,1,0);
        
        externalParentButton = new Button("Parent");
        h3 = new HBox();
        h3.getChildren().addAll(new Label("Specify Selected Parent: "),externalParentButton);
        h3.setAlignment(Pos.CENTER);
        parentGrid.add(h3,1,1);
        
        externalParentButton.setOnAction(e -> {
            String potential = externalList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                DraggableInterface diTemp = new DraggableInterface();
                diTemp.setInterfaceName(potential);
                externalList.getSelectionModel().clearSelection();
                externalData.remove(potential);
                parentData.add(diTemp);
                inheritParents.add(diTemp.getInterfaceName());
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
            DraggableInterface potential = (DraggableInterface) parentList.getSelectionModel().getSelectedItem();
            if (potential != null) {
                parentList.getSelectionModel().clearSelection();
                parentData.remove(potential);
                if(externalStorage.contains(potential.getInterfaceName())){
                    externalData.add(potential.getInterfaceName());
                    externalList.getSelectionModel().select(potential.getInterfaceName());
                }
                else if(paneStorage.contains(potential.getInterfaceName())){
                    paneData.add(potential);
                    paneList.getSelectionModel().select(potential);
                }
                paneStorage.remove(potential.getInterfaceName());
                inheritParents.remove(potential.getInterfaceName());
            }
        });
        
        parentGrid.add(parentList, 2, 2);
        
        variableTable = new TableView();
        
        variableData = FXCollections.observableArrayList();
        variableTable.setEditable(true);
        
        nameCol = new TableColumn("Name");
        typeCol = new TableColumn("Type");
        accessCol = new TableColumn("Access");
        staticCol = new TableColumn("Static");
        
        staticCol.setMinWidth(150);
        staticCol.setCellValueFactory(
                new PropertyValueFactory<Variable, Boolean>("isStatic"));
        
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
                            ).setName((t.getNewValue()).toUpperCase());
                        
                        String tempS = "";
                        Variable varTemp = ((Variable) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                        variableText.set(variablesList.indexOf(varTemp),      
                        (varTemp.getAccessType().equals("public")?"+":
                        "~")+(varTemp.isStatic()?"$ ":" ")
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
                    (varTemp.getAccessType().equals("public")?"+":
                    "~")+(varTemp.isStatic()?"$ ":" ")
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
                    +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
                for(String s: methodText){
                    tempS+=s;
                }
                
                methods.setText(tempS);
                }
                return methodData.get(param).isStaticProperty();
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
                    if(!methodText.isEmpty()){
                    methodText.set(methodList.indexOf(methTemp),      
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
        
        abstractCol.setMinWidth(100);
        abstractCol.setCellValueFactory(
                new PropertyValueFactory<Method, Boolean>("isAbstract"));
        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
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
                        (methTemp.getAccessType().equals("public")?"+":
                        "~")+(methTemp.isStatic()?"$ ":" ")
                        +methTemp.getName()+"("+tempA+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
                        
                    for(String s: methodText){
                        tempS+=s;
                    }
        
                    methods.setText(tempS);
                    }
                }
            }
        );
        
        
 
        methodTable.setItems(methodData);
        methodTable.getColumns().addAll(nameMCol,returnCol,staticMCol,abstractCol,accessMCol,
            arg1Col,arg2Col,arg3Col,arg4Col,arg5Col,arg6Col,arg7Col,arg8Col,arg9Col,arg10Col);
        
        /*
        classBox.prefHeightProperty().bind(this.heightProperty());
        varBox.prefHeightProperty().bind(this.heightProperty());
        methBox.prefHeightProperty().bind(this.heightProperty());*/
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
    
    public String getName(){
        return name;
    }
    
    public String getInterfaceName(){
        return name;
    }
    
    public void setInterfaceName(String s){
        name = s;
    }
    
    public void setClassText(String s){
        className.setText("<<Interface>>"+"\n"+s);
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
        Variable varTemp = new Variable(("DEFAULT_VAR"+rand),"int",PUBLIC_TYPE,STATIC_FINAL_TYPE);
        
        variableData.add(varTemp);
        variableTable.getSelectionModel().select(varTemp);
        variablesList.add(varTemp);
        
        variableText.add(
                (varTemp.getAccessType().equals("public")?"+":
                "~")+(varTemp.isStatic()?"$ ":" ")
                +varTemp.getName()+": "+varTemp.getType()+"\n");
        
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
        Method methTemp = new Method("defaultMeth"+rand, "void", "public");
        
        methodData.add(methTemp);
        methodTable.getSelectionModel().select(methTemp);
        methodList.add(methTemp);
        
        methodText.add(
                (methTemp.getAccessType().equals("public")?"+":
                "~")+(methTemp.isStatic()?"$ ":" ")
                +methTemp.getName()+"("+")"+": "+methTemp.getReturnType()+(methTemp.isAbstract()?" {abstract}":"")+"\n");
        
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
            if(n instanceof DraggableInterface){
                
                if(((DraggableInterface)n).equals(this)){
                    //System.out.println(((DraggableInterface)n).getInterfaceName());
                    continue;
                }
                
                else if(!paneList.getItems().contains(((DraggableInterface)n)) &&
                    !parentList.getItems().contains(((DraggableInterface)n))){
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
    
    public TableView<DraggableInterface> getParentList(){
        return parentList;
    }
    
    public ObservableList<DraggableInterface> getParentData(){
        return parentData;
    }
    
    public GridPane getParentGrid(){
        return parentGrid;
    }
    
    public ArrayList<String> getInheritParents(){
        return inheritParents;
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
