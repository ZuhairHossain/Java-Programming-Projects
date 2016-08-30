package jcd.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import saf.components.AppDataComponent;
import saf.components.AppFileComponent;
import jcd.data.DataManager;
import jcd.data.DraggableClass;
import jcd.data.DraggableInterface;
import jcd.data.DraggableLine;
import jcd.data.Method;
import jcd.data.Variable;

import static jcd.data.Method.PUBLIC_TYPE;
import static jcd.data.Method.PRIVATE_TYPE;
import static jcd.data.Method.PROTECTED_TYPE;
import static jcd.data.Method.NO_TYPE; 
import static jcd.data.Method.STATIC_TYPE;
import static jcd.data.Method.ABSTRACT_TYPE;
import static jcd.data.Method.NONE;
import static jcd.data.Variable.STATIC_FINAL_TYPE;
import static jcd.data.DraggableLine.INHERIT_TYPE;
import static jcd.data.DraggableLine.IMPLEMENT_TYPE;
import static jcd.data.DraggableLine.AGGREGATE_TYPE;

/**
 * This class serves as the file management component for this application,
 * providing all I/O services.
 *
 * @author Richard McKenna
 * @version 1.0
 */
public class FileManager implements AppFileComponent {
    // FOR JSON LOADING
    
    static final String DEFAULT_DOCTYPE_DECLARATION = "<!doctype html>\n";
    static final String DEFAULT_ATTRIBUTE_VALUE = "";
    
    ObservableList<Node> tempPaneChildren;
    
 
    /**
     * This method is for saving user work, which in the case of this
     * application means the data that constitutes the page DOM.
     * 
     * @param data The data management component for this application.
     * 
     * @param filePath Path (including file name/extension) to where
     * to save the data to.
     * 
     * @throws IOException Thrown should there be an error writing 
     * out data to the file.
     */
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	DataManager dataManager = (DataManager)data;
        
        StringWriter sw = new StringWriter();
        
        //dataManager.getWorkspace().reloadWorkspace();
        
        //JsonArrayBuilder arrayBuilderImport = Json.createArrayBuilder();
        JsonArrayBuilder arrayBuilder0 = Json.createArrayBuilder();
        JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();
        JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();
        
        tempPaneChildren = dataManager.getCWorkspace().getChildren();
        //arrayBuilderImport.add(makeOneImportJsonObject(dataManager.getImports()));
        
        for(int i=0; i<tempPaneChildren.size(); i++){
            Object o = tempPaneChildren.get(i);
            
            if(o instanceof DraggableClass){
                JsonArrayBuilder methTemp = Json.createArrayBuilder();
                JsonArrayBuilder arrayBuilderImport = Json.createArrayBuilder();
                JsonArrayBuilder interfacesTemp = Json.createArrayBuilder();
                
                if(!((DraggableClass)o).getMethodList().isEmpty()){
                    for(Method m: ((DraggableClass)o).getMethodList()){
                        methTemp.add(makeOneMethodJsonObject(m));
                    }
                }
                
                if(!((DraggableClass)o).getImports().isEmpty()){
                    arrayBuilderImport.add(makeOneImportJsonObject(((DraggableClass)o).getImports()));
                }
                
                if(!((DraggableClass)o).getImplementParents().isEmpty()){
                    interfacesTemp.add(makeOneInterfaceJsonObject(((DraggableClass)o).getImplementParents()));
                }
                
                JsonArray tempMeth = methTemp.build();
                JsonArray tempImport = arrayBuilderImport.build();
                JsonArray tempInterfaces = interfacesTemp.build();
                arrayBuilder0.add(makeOneClassJsonObject((DraggableClass)o,i,tempMeth,tempImport,tempInterfaces));
                
                //arrayBuilder0.add(makeOneClassJsonObject((DraggableClass)o,i));
            }
            
           else if(o instanceof DraggableInterface){
               JsonArrayBuilder methTemp = Json.createArrayBuilder();
               JsonArrayBuilder arrayBuilderImport = Json.createArrayBuilder();
               JsonArrayBuilder inheritParents = Json.createArrayBuilder();
               
                if(!((DraggableInterface)o).getMethodList().isEmpty()){
                    for(Method m: ((DraggableInterface)o).getMethodList()){
                        methTemp.add(makeOneMethodJsonObject(m));
                    }
                }
                
                if(!((DraggableInterface)o).getImports().isEmpty()){
                    arrayBuilderImport.add(makeOneImportJsonObject(((DraggableInterface)o).getImports()));
                }
                
                if(!((DraggableInterface)o).getInheritParents().isEmpty()){
                    inheritParents.add(makeOneInterfaceJsonObject(((DraggableInterface)o).getInheritParents()));
                }
                
                JsonArray temp = methTemp.build();
                JsonArray tempImport = arrayBuilderImport.build();
                JsonArray tempParents = inheritParents.build();
                arrayBuilder1.add(makeOneInterfaceJsonObject((DraggableInterface)o,i,temp,tempImport,tempParents));
                
               //arrayBuilder1.add(makeOneInterfaceJsonObject((DraggableInterface)o,i));
           }
            
           else if(o instanceof DraggableLine){
               arrayBuilder2.add(makeOneLineJsonObject((DraggableLine)o, i));
           }
        }
        
        //JsonArray importArray = arrayBuilderImport.build();
        JsonArray classArray = arrayBuilder0.build();
        JsonArray interfaceArray = arrayBuilder1.build();
        JsonArray lineArray = arrayBuilder2.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add("Classes", classArray)
            .add("Interfaces", interfaceArray)
            .add("Lines", lineArray)
            .build();
        
        Map<String,Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();
        
        OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    private JsonObject makeOneClassJsonObject(DraggableClass dc, int index, JsonArray methods, JsonArray imports,JsonArray interfaces){
        JsonObject jso = Json.createObjectBuilder()
            .add("index", index)
            .add("imports", imports)
            .add("x_location", dc.getLayoutX())
            .add("y_location", dc.getLayoutY())
            .add("classNameText", dc.getClassText().getText())
            .add("className", dc.getClassName())
            .add("variablesText", dc.getVarText().getText())
            .add("methodsText", dc.getMethText().getText())
            .add("methods", methods)
            .add("package", dc.getPackageName())
            .add("parent", dc.getParentName())
            .add("interfaces", interfaces)
            .add("variables", makeVariablesArray(dc.getVariableList()))
            .add("abstract", dc.isAbstract())
            .add("translateX", dc.getTranslateX())
            .add("translateY", dc.getTranslateY())
            .build();
        return jso;
    }
    
    private JsonObject makeOneInterfaceJsonObject(DraggableInterface di, int index, JsonArray methods, JsonArray imports,JsonArray parents){
        JsonObject jso = Json.createObjectBuilder()
            .add("index", index)
            .add("imports", imports)
            .add("x_location", di.getLayoutX())
            .add("y_location", di.getLayoutY())
            .add("classNameText", di.getClassText().getText())
            .add("className", di.getInterfaceName())
            .add("variablesText", di.getVarText().getText())
            .add("methodsText", di.getMethText().getText())
            .add("methods", methods)
            .add("package", di.getPackageName())
            .add("parents",parents)
            .add("variables", makeVariablesArray(di.getVariableList()))
            .add("translateX", di.getTranslateX())
            .add("translateY", di.getTranslateY())
            .build();
        return jso;
    }
    
    private JsonObject makeOneMethodJsonObject(Method m){
        JsonObject jso = Json.createObjectBuilder()
                .add("name", m.getName())
                .add("parameters", m.getParameters())
                .add("returnType", m.getReturnType())
                .add("accessType", m.getAccessType())
                .add("methodType",(m.isAbstract()?"abstract":m.isStatic()?"static":"")) //m.getMethodType())
                .add("variables", makeVariablesArray(m.getArguments()))
		.build();
        return jso;
    }
    
    private JsonArray makeVariablesArray(ArrayList<Variable> vl){
        JsonArrayBuilder varTemp = Json.createArrayBuilder();
        if(!vl.isEmpty()){
            for(Variable v: vl){
                JsonObject jso = Json.createObjectBuilder()
                .add("name",v.getName())
                .add("type",v.getType())
                .add("accessType",v.getAccessType())
                .add("variableType",v.isStatic()?"static":"")
		.build();
                varTemp.add(jso);
            }
        }     
        JsonArray temp = varTemp.build();
        return temp;
    }
    
    private JsonObject makeOneLineJsonObject(DraggableLine dl, int index){
        JsonObject jso = Json.createObjectBuilder()
            .add("index", index)
            .add("lineType", dl.getLineType())
            .add("x_location", dl.getLayoutX())
            .add("y_location", dl.getLayoutY())
            .add("StartX", dl.getStartX())
            .add("StartY",dl.getStartY())
            .add("EndX", dl.getEndX())
            .add("EndY", dl.getEndY())
            .add("SplitX",dl.getSplitX())
            .add("SplitY", dl.getSplitY())
            .add("translateX", dl.getTranslateX())
            .add("translateY", dl.getTranslateY())
            .add("Parent", dl.getParentName())
            .add("Child", dl.getChildName())
            .build();
        return jso;
    }
    
    private JsonObject makeOneImportJsonObject(ArrayList<String> imports){
        if(imports.isEmpty()){
            JsonObject json = Json.createObjectBuilder()
                    .add("size",imports.size())
                    .add("importList", "")
                    .build();
            return json;
        }
        
        else{
            String temp = "";
            JsonArrayBuilder arr = Json.createArrayBuilder();
            
            for(String s: imports){
                arr.add(s);
            }
        
            JsonArray finArr = arr.build();
        
            JsonObject jso = Json.createObjectBuilder()
                .add("size",imports.size())
                .add("importList", finArr)
		.build();
        
            return jso;
        }
    }
    
    private JsonObject makeOneInterfaceJsonObject(ArrayList<String> implementParents){
        if(implementParents.isEmpty()){
            JsonObject json = Json.createObjectBuilder()
                    .add("size",implementParents.size())
                    .add("interfaceList", "")
                    .build();
            return json;
        }
        
        else{
            JsonArrayBuilder arr = Json.createArrayBuilder();
            
            for(String s: implementParents){
                arr.add(s);
            }
        
            JsonArray finArr = arr.build();
        
            JsonObject jso = Json.createObjectBuilder()
                .add("size",implementParents.size())
                .add("interfaceList", finArr)
		.build();
        
            return jso;
        }
    }
    /**
     * This method loads data from a JSON formatted file into the data 
     * management component and then forces the updating of the workspace
     * such that the user may edit the data.
     * 
     * @param data Data management component where we'll load the file into.
     * 
     * @param filePath Path (including file name/extension) to where
     * to load the data from.
     * 
     * @throws IOException Thrown should there be an error reading
     * in data from the file.
     */
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
        
	DataManager dataManager = (DataManager)data;
	dataManager.reset();
        
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
	
	// LOAD THE TAG TREE
	JsonArray jsonClassArray = json.getJsonArray("Classes");
        JsonArray jsonInterfaceArray = json.getJsonArray("Interfaces");
        JsonArray jsonLineArray = json.getJsonArray("Lines");
        
        int num = (jsonClassArray.size() + jsonInterfaceArray.size() + jsonLineArray.size())-1;
        int z = 0;
        
        while(z<=num){
            
            for(int i=0; i<jsonClassArray.size(); i++){
                if(jsonClassArray.getJsonObject(i).getInt("index") == z){
                    loadClass(jsonClassArray,dataManager,i);
                    z++;
                    break;
                }
            }
            
            for(int i=0; i<jsonInterfaceArray.size(); i++){
                if(jsonInterfaceArray.getJsonObject(i).getInt("index") == z){
                    loadInterface(jsonInterfaceArray,dataManager,i);
                    z++;
                    break;
                }
            }
            
            for(int i=0; i<jsonLineArray.size(); i++){
                if(jsonLineArray.getJsonObject(i).getInt("index") == z){
                    loadLine(jsonLineArray,dataManager,i);
                    z++;
                    break;
                }
            }
            
            
        }
    }
    
    private void loadClass(JsonArray jsonClassArray, DataManager dataManager, int index){
        if(jsonClassArray.isEmpty())
            return;
        
        DraggableClass dc = new DraggableClass(jsonClassArray.getJsonObject(index).getBoolean("abstract"));
        dc = dataManager.createClassAction(dc);
        
        dc.setLayoutX(getDataAsDouble(jsonClassArray.getJsonObject(index),"x_location"));
        dc.setLayoutY(getDataAsDouble(jsonClassArray.getJsonObject(index),"y_location"));
        
        JsonArray importArray = jsonClassArray.getJsonObject(index).getJsonArray("imports");
        
        if(!importArray.isEmpty()){
        JsonArray importList = importArray.getJsonObject(0).getJsonArray("importList");
        for(int j=0; j<importList.size(); j++){
            dc.addImport(importList.getString(j));
            dc.getImportData().add(importList.getString(j));
        }
        }
        
        JsonArray interfaceArray = jsonClassArray.getJsonObject(index).getJsonArray("interfaces");
        
        if(!interfaceArray.isEmpty()){
        JsonArray interfaceList = interfaceArray.getJsonObject(0).getJsonArray("interfaceList");
        for(int j=0; j<interfaceList.size(); j++){
            dc.getImplementParents().add(interfaceList.getString(j));
            DraggableInterface diTemp = new DraggableInterface();
            diTemp.setInterfaceName(interfaceList.getString(j));
            dc.getInterfaceData().add(diTemp);
        }
        }
        
        dc.setClassText(jsonClassArray.getJsonObject(index).getString("classNameText"));
        dc.setClassName(jsonClassArray.getJsonObject(index).getString("className"));
        dc.setVarText(jsonClassArray.getJsonObject(index).getString("variablesText"));
        dc.setMethText(jsonClassArray.getJsonObject(index).getString("methodsText"));
        
        String varTemp[] = (jsonClassArray.getJsonObject(index).getString("variablesText")).split("\\r?\\n");
        String methTemp[] = (jsonClassArray.getJsonObject(index).getString("methodsText")).split("\\r?\\n");
        for(String st: varTemp){
            dc.getVariableText().add(st+"\n");
        }
        
        for(String st: methTemp){
            dc.getMethodText().add(st+"\n");
        }
        
        JsonArray methodArray = jsonClassArray.getJsonObject(index).getJsonArray("methods");
        
        for(int j=0; j<methodArray.size(); j++){
            int accessT;
            int methodT;
            boolean isStatic = false;
            boolean isAbstract = false;
            
            switch (methodArray.getJsonObject(j).getString("accessType")) {
                case "public":
                    accessT = PUBLIC_TYPE;
                    break;
                case "private":
                    accessT = PRIVATE_TYPE;
                    break;
                case "protected":
                    accessT = PROTECTED_TYPE;
                    break;
                default:
                    accessT = NO_TYPE;
                    break;
            }
            
            switch(methodArray.getJsonObject(j).getString("methodType")){
                case "static":
                    methodT = STATIC_TYPE;
                    isStatic = true;
                    break;
                case "abstract":
                    methodT = ABSTRACT_TYPE;
                    isAbstract = true;
                    break;
                default:
                    methodT = NONE;
                    break;
            }
            Method m = new Method(methodArray.getJsonObject(j).getString("name"), 
                    methodArray.getJsonObject(j).getString("returnType"), 
                    methodArray.getJsonObject(j).getInt("parameters"),
                    accessT, methodT);
            
            m.setStatic(isStatic);
            m.setAbstract(isAbstract);
            
            JsonArray variableList = methodArray.getJsonObject(j).getJsonArray("variables");
            for(int k=0; k<variableList.size(); k++){
                m.addParameter(variableList.getJsonObject(k).getString("name"),
                                 variableList.getJsonObject(k).getString("type"),
                                 NO_TYPE,NONE);
            }
            dc.addMethod(m);
            dc.getMethodData().add(m);
        }
        
        dc.setPackageName(jsonClassArray.getJsonObject(index).getString("package"));
        dc.setParentName(jsonClassArray.getJsonObject(index).getString("parent"));
        DraggableClass dcTemp = new DraggableClass(false);
        dcTemp.setClassName(jsonClassArray.getJsonObject(index).getString("parent"));
        if(!dcTemp.getClassName().equals(""))
            dc.getParentData().add(dcTemp);
        //dc.setInterfaceName(jsonClassArray.getJsonObject(index).getString("interface"));
        
        JsonArray variableArray = jsonClassArray.getJsonObject(index).getJsonArray("variables");
        
        //if(variableArray.size()>0){
        for(int j=0; j<variableArray.size(); j++){
            int aT;
            int vT;

            switch (variableArray.getJsonObject(j).getString("accessType")) {
                case "public":
                    aT = PUBLIC_TYPE;
                    break;
                case "private":
                    aT = PRIVATE_TYPE;
                    break;
                case "protected":
                    aT = PROTECTED_TYPE;
                    break;
                default:
                    aT = NO_TYPE;
                    break;
            }
            
            switch(variableArray.getJsonObject(j).getString("variableType")){
                case "static":
                    vT = STATIC_TYPE;
                    break;
                case "static final":
                    vT = STATIC_FINAL_TYPE;
                    break;
                default:
                    vT = NONE;
                    break;
            }
            Variable tempV = new Variable(variableArray.getJsonObject(j).getString("name"),
                                    variableArray.getJsonObject(j).getString("type"), aT,vT);
            
            dc.getVariableData().add(tempV);
            dc.addVariable(tempV);            
        }
        
        dc.setAbstract(jsonClassArray.getJsonObject(index).getBoolean("abstract"));
        dc.setTranslateX(getDataAsDouble(jsonClassArray.getJsonObject(index),"translateX"));
        dc.setTranslateY(getDataAsDouble(jsonClassArray.getJsonObject(index),"translateY"));
        dataManager.getCanvasDM().getChildren().add(dc);
        dataManager.getDraggableClasses().add(dc);
        
        if(dataManager.getWorkspace()!=null){
        //try{
            dataManager.getWorkspace().getCanvas().getChildren().add(dc);
        //} catch(NullPointerException ex){
            //System.out.println("Empty Workspace");
        }//}
    }
    
    private void loadInterface(JsonArray jsonInterfaceArray, DataManager dataManager, int index){
        if(jsonInterfaceArray.isEmpty())
            return;
        
        DraggableInterface di = new DraggableInterface();
        di = dataManager.createInterfaceAction(di);
        
        di.setLayoutX(getDataAsDouble(jsonInterfaceArray.getJsonObject(index),"x_location"));
        di.setLayoutY(getDataAsDouble(jsonInterfaceArray.getJsonObject(index),"y_location"));
        
        JsonArray importArray = jsonInterfaceArray.getJsonObject(index).getJsonArray("imports");
        
        if(importArray.size()>0){
        JsonArray importList = importArray.getJsonObject(0).getJsonArray("importList");
        for(int j=0; j<importList.size(); j++){
            di.addImport(importList.getString(j));
            di.getImportData().add(importList.getString(j));
        }
        }
        
        JsonArray parentArray = jsonInterfaceArray.getJsonObject(index).getJsonArray("parents");
        
        if(!parentArray.isEmpty()){
        JsonArray parentList = parentArray.getJsonObject(0).getJsonArray("interfaceList");
        for(int j=0; j<parentList.size(); j++){
            di.getInheritParents().add(parentList.getString(j));
            DraggableInterface diTemp = new DraggableInterface();
            diTemp.setInterfaceName(parentList.getString(j));
            di.getParentData().add(diTemp);
        }
        }
        
        di.setClassText((jsonInterfaceArray.getJsonObject(index).getString("classNameText").substring(14)));
        di.setInterfaceName(jsonInterfaceArray.getJsonObject(index).getString("className"));
        
        di.setVarText(jsonInterfaceArray.getJsonObject(index).getString("variablesText"));
        di.setMethText(jsonInterfaceArray.getJsonObject(index).getString("methodsText"));
        
        String varTemp[] = (jsonInterfaceArray.getJsonObject(index).getString("variablesText")).split("\\r?\\n");
        String methTemp[] = (jsonInterfaceArray.getJsonObject(index).getString("methodsText")).split("\\r?\\n");
        for(String st: varTemp){
            di.getVariableText().add(st+"\n");
        }
        
        for(String st: methTemp){
            di.getMethodText().add(st+"\n");
        }
        
        JsonArray methodArray = jsonInterfaceArray.getJsonObject(index).getJsonArray("methods");
        
        for(int j=0; j<methodArray.size(); j++){
            int accessT;
            int methodT;

            switch (methodArray.getJsonObject(j).getString("accessType")) {
                case "public":
                    accessT = PUBLIC_TYPE;
                    break;
                case "private":
                    accessT = PRIVATE_TYPE;
                    break;
                case "protected":
                    accessT = PROTECTED_TYPE;
                    break;
                default:
                    accessT = NO_TYPE;
                    break;
            }
            
            switch(methodArray.getJsonObject(j).getString("methodType")){
                case "static":
                    methodT = STATIC_TYPE;
                    break;
                case "abstract":
                    methodT = ABSTRACT_TYPE;
                    break;
                default:
                    methodT = NONE;
                    break;
            }
            Method m = new Method(methodArray.getJsonObject(j).getString("name"), 
                    methodArray.getJsonObject(j).getString("returnType"), 
                    methodArray.getJsonObject(j).getInt("parameters"),
                    accessT, methodT);
            JsonArray variableList = methodArray.getJsonObject(j).getJsonArray("variables");
            for(int k=0; k<variableList.size(); k++){
                m.addParameter(variableList.getJsonObject(k).getString("name"),
                                 variableList.getJsonObject(k).getString("type"),
                                 NO_TYPE,NONE);
            }
            di.addMethod(m);
            di.getMethodData().add(m);
        }
        
        di.setPackageName(jsonInterfaceArray.getJsonObject(index).getString("package"));
        
        JsonArray variableArray = jsonInterfaceArray.getJsonObject(index).getJsonArray("variables");

        for(int j=0; j<variableArray.size(); j++){
            int aT = PUBLIC_TYPE;
            int vT = STATIC_FINAL_TYPE;
            
            Variable tempV = new Variable(variableArray.getJsonObject(j).getString("name"),
                                    variableArray.getJsonObject(j).getString("type"), aT,vT);
            di.addVariable(tempV);
            di.getVariableData().add(tempV);
        }
        
        
        di.setTranslateX(getDataAsDouble(jsonInterfaceArray.getJsonObject(index),"translateX"));
        di.setTranslateY(getDataAsDouble(jsonInterfaceArray.getJsonObject(index),"translateY"));
        
        dataManager.getCanvasDM().getChildren().add(di);
        dataManager.getDraggableInterfaces().add(di);
        if(dataManager.getWorkspace()!=null){
        //try{
            dataManager.getWorkspace().getCanvas().getChildren().add(di);
        //} catch(NullPointerException ex){
            //System.out.println("Empty Workspace");
        }//}
        
    }
    
    private void loadLine(JsonArray jsonLineArray, DataManager dataManager, int index){
        if(jsonLineArray.isEmpty())
            return;
        
        int lt;
        switch(jsonLineArray.getJsonObject(index).getString("lineType")){
            case "Inheritance":
                lt = INHERIT_TYPE;
                break;
            case "Interface":
                lt = IMPLEMENT_TYPE;
                break;
            case "Aggregate":
                lt = AGGREGATE_TYPE;
                break;
            default:
                lt = -1;
                break;
        }
        
        DraggableLine dl = new DraggableLine(lt);
        //dl = dataManager.createLineAction(dl);
        
        dl.setLayoutX(getDataAsDouble(jsonLineArray.getJsonObject(index), "x_location"));
        dl.setLayoutY(getDataAsDouble(jsonLineArray.getJsonObject(index), "y_location"));
        dl.setStartX(getDataAsDouble(jsonLineArray.getJsonObject(index), "StartX"));
        dl.setStartY(getDataAsDouble(jsonLineArray.getJsonObject(index), "StartY"));
        dl.setEndX(getDataAsDouble(jsonLineArray.getJsonObject(index), "EndX"));
        dl.setEndY(getDataAsDouble(jsonLineArray.getJsonObject(index), "EndY"));
        dl.setSplitX(getDataAsDouble(jsonLineArray.getJsonObject(index), "SplitX"));
        dl.setSplitY(getDataAsDouble(jsonLineArray.getJsonObject(index), "SplitY"));        
        dl.setTranslateX(getDataAsDouble(jsonLineArray.getJsonObject(index), "translateX"));
        dl.setTranslateY(getDataAsDouble(jsonLineArray.getJsonObject(index), "translateY"));
        dl.setParentName(jsonLineArray.getJsonObject(index).getString("Parent"));
        dl.setChildName(jsonLineArray.getJsonObject(index).getString("Child"));
        
        dataManager.getCanvasDM().getChildren().add(dl);
        dataManager.getDraggableLines().add(dl);
        if(dataManager.getWorkspace()!=null){
        //try{
            dataManager.getWorkspace().getCanvas().getChildren().add(dl);
        //} catch(NullPointerException ex){
            //System.out.println("Empty Workspace");
        }
    }
    
    
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
    
    
    
    /**
     * This method exports the contents of the data manager to a 
     * Web page including the html page, needed directories, and
     * the CSS file.
     * 
     * @param data The data management component.
     * 
     * @param filePath Path (including file name/extension) to where
     * to export the page to.
     * 
     * @throws IOException Thrown should there be an error writing
     * out data to the file.
     */
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        
        DataManager dataManager = (DataManager)data;
        //String s = System.getProperty("user.dir")+"/work/DesignSaveTest.json";
        
        //File myFile = new File(filePath);
        
        ObservableList<Node> workspaceNodes = dataManager.getCWorkspace().getChildren();
        
	//PrintWriter pw=new PrintWriter(filePath);
        
        
        for(Node n: workspaceNodes){
            if(n instanceof DraggableClass){
                DraggableClass dc = (DraggableClass) n;
                String myFile = filePath+"/"+dc.getPackageName();
                String fileTemp = filePath;
                //File myFile = new File(filePath+"/"+dc.getPackageName());
                //myFile.mkdir();
                File temp = new File(myFile);
                
                System.out.println(dc.getPackageName());
                
                if(dc.getPackageName().contains(".")){
                    
                    String[] arr = dc.getPackageName().split("\\.");
                    
                    for(int i=0; i<arr.length; i++){
                        System.out.println(arr[i]);
                        fileTemp+=("/"+arr[i]);
                        //temp.mkdirs();
                        //myFile = new File(temp.getPath());
                    }
                    temp = new File(/*myFile.getPath()*/fileTemp);
                    temp.mkdirs();
                }
                
                else
                    temp.mkdir();
                //System.out.println(tempPack);
                //File myFile = new File(filePath+"/"+dc.getPackageName());
                //myFile.mkdir();
                
                //myFile.mkdir();
                PrintWriter pw = new PrintWriter("/"+temp/*myFile*/+"/"+dc.getClassName()+".java");
                //myFile.mkdir();
                String writer = "";
                
                writer += ("package "+dc.getPackageName()+";\n");
                
                for(String x: dc.getImports()){
                    writer += ("import "+x+";\n");
                }
                
                writer += "public ";
                if(dc.isAbstract())
                    writer += "abstract ";
                writer += "class ";
                
                writer += dc.getClassName()+" ";
                
                if(!dc.getParentName().equals(""))
                    writer += "extends "+dc.getParentName()+" ";
                if(!dc.getImplementParents().isEmpty()){
                    writer += "implements ";
                    for(String st: dc.getImplementParents()){
                        writer += st;
                        if((dc.getImplementParents().indexOf(st)+1)!=dc.getImplementParents().size()){
                            writer += ",";
                        }
                    }
                }
                //if(!dc.getInterfaceName().equals(""))
                 //   writer += "implements "+dc.getInterfaceName()+" ";
                
                writer +="{\n";
                
                
                if(!dc.getVariableList().isEmpty()){
                    for(Variable v: dc.getVariableList()){
                        if(v.getVariableType().equals("static final")||(v.isStatic()&&v.isFinal())){
                            if(Character.isUpperCase(v.getType().charAt(0)))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= null;\n");
                        
                            else if(v.getType().equals("int") || v.getType().equals("double") || v.getType().equals("float"))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= 0;\n");
                            
                            else if(v.getType().equals("boolean"))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= false;\n");
                            
                            else if(v.getType().equals("char"))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= ' ';\n");
                            else
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= null;\n");
                        }
                        else
                            writer += ("    "+v.getAccessType()+" "+(v.isStatic()?"static":"")+(v.isFinal()?"final":"")+" "+v.getType()+" "+
                                v.getName()+";\n");
                    }
                    
                }
                
                if(!dc.getMethodList().isEmpty()){
                    for(Method m: dc.getMethodList()){
                        writer += ("    "+m.getAccessType()+" "+(m.isAbstract()?"abstract ":m.isStatic()?"static ":"")+m.getMethodType()+" "+m.getReturnType()+" "+m.getName()+"(");
                        for(int z=0; z<m.getArguments().size(); z++){
                            Variable v = m.getArguments().get(z);
                            writer += (v.getType()+" "+v.getName());
                            if((z+1)!=m.getArguments().size()){
                                writer += ", ";
                            }
                            //if(z < m.getArguments().size()-2)
                               // writer += (",");
                            
                        }
                        
                        if(!m.getReturnType().equals("")){
                            if(Character.isUpperCase(m.getReturnType().charAt(0))){
                                writer += ("){\n"+"    "+"return null;"+"\n"+"    }\n");
                            }
                            
                            else if(m.getReturnType().equals("int") || m.getReturnType().equals("double")||m.getReturnType().equals("float"))
                                writer += ("){\n"+"    "+"return 0;"+"\n"+"    }\n");
                            
                            else if(m.getReturnType().equals("boolean"))
                                writer += ("){\n"+"    "+"return false;"+"\n"+"    }\n");
                            
                            else if(m.getReturnType().equals("char"))
                                writer += ("){\n"+"    "+"return ' ';"+"\n"+"    }\n");
                            else
                                writer += ("){\n"+"    "+"}\n");
                            
                        }
                        else if(m.isAbstract()){
                            writer += (");\n");
                        }
                        else
                            writer += ("){\n"+"    "+"}\n");
                    }
                }
                writer+="}";
                pw.write(writer);
                pw.close();
            }
            
            
            else if(n instanceof DraggableInterface){
                //System.out.println("FOUND INTERFACE");
                DraggableInterface di = (DraggableInterface) n;
                String myFile = filePath+"/"+di.getPackageName();
                String fileTemp = filePath;
                //File myFile = new File(filePath+"/"+dc.getPackageName());
                //myFile.mkdir();
                File temp = new File(myFile);
                
                System.out.println(di.getPackageName());
                
                if(di.getPackageName().contains(".")){
                    
                    String[] arr = di.getPackageName().split("\\.");
                    
                    for(int i=0; i<arr.length; i++){
                        System.out.println(arr[i]);
                        fileTemp+=("/"+arr[i]);
                        //temp.mkdirs();
                        //myFile = new File(temp.getPath());
                    }
                    temp = new File(/*myFile.getPath()*/fileTemp);
                    temp.mkdirs();
                }
                
                else
                    temp.mkdir();
                /*
                File myFile = new File(filePath+"/"+di.getPackageName());
                myFile.mkdir();
                if(di.getPackageName().contains(".")){
                    String[] arr = di.getPackageName().split(".");
                    for(int i=0; i<arr.length; i++){
                        File temp = new File(filePath+"/"+arr[i]);
                        temp.mkdir();
                        myFile = new File(filePath+"/"+arr[i]);
                    }
                }*/
                
                PrintWriter pw = new PrintWriter("/"+temp/*myFile*/+"/"+di.getInterfaceName()+".java");
                //myFile.mkdir();
                String writer = "";
                
                writer += ("package "+di.getPackageName()+";\n");
                
                for(String x: di.getImports()){
                    writer += ("import "+x+";\n");
                }
                
                writer += "public ";
                
                writer += "interface ";
                
                writer += di.getInterfaceName()+" ";
                
                if(!di.getInheritParents().isEmpty()){
                    writer += "extends ";
                    for(String st: di.getInheritParents()){
                        writer += st;
                        if((di.getInheritParents().indexOf(st)+1)!=di.getInheritParents().size()){
                            writer += ",";
                        }
                    }
                }
                
                writer +="{\n";
                
                
                if(!di.getVariableList().isEmpty()){
                    for(Variable v: di.getVariableList()){
                        if(v.getVariableType().equals("static final")||(v.isStatic()&&v.isFinal())){
                            if(Character.isUpperCase(v.getType().charAt(0)))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= null;\n");
                        
                            else if(v.getType().equals("int") || v.getType().equals("double") || v.getType().equals("float"))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= 0;\n");
                            
                            else if(v.getType().equals("boolean"))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= false;\n");
                            
                            else if(v.getType().equals("char"))
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= ' ';\n");
                            else
                                writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+"= null;\n");
                        }
                        else
                            writer += ("    "+v.getAccessType()+" "+"static final"+" "+v.getType()+" "+
                                v.getName()+";\n");
                    }    
                    
                }
                
                if(!di.getMethodList().isEmpty()){
                    for(Method m: di.getMethodList()){
                        writer += ("    "+m.getAccessType()+" "+(m.isAbstract()?"abstract ":m.isStatic()?"static ":"")+" "+m.getReturnType()+" "+m.getName()+"(");
                        for(int z=0; z<m.getArguments().size(); z++){
                            Variable v = m.getArguments().get(z);
                            writer += (v.getType()+" "+v.getName());
                            if((z+1) != m.getArguments().size()){
                                writer += ",";
                            }
                            //if(z < m.getArguments().size()-2)
                              //  writer += (",");
                            
                        }
                        
                        
                        writer += (");\n");
                    }
                }
                writer+="}";
                //System.out.println(writer);
                pw.write(writer);
                pw.close();
            }
        }
        
        
    }
    
    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
	// NOTE THAT THE Web Page Maker APPLICATION MAKES
	// NO USE OF THIS METHOD SINCE IT NEVER IMPORTS
	// EXPORTED WEB PAGES
    }
}
