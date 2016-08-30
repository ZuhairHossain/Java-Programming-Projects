package jcd.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * This class serves as a Variable of DraggableClass and DraggableInterface
 *
 * @author Dennis Sosa
 * @version 1.0
 */
public class Variable {
    String name;
    String type;
    
    // FIRST THE THINGS THAT HAVE TO BE SAVED TO Method
    public static final int PUBLIC_TYPE = 1;
    public static final int PRIVATE_TYPE = 2;
    public static final int PROTECTED_TYPE = 3;
    public static final int NO_TYPE = 4;
    
    public static final int STATIC_TYPE = 5;
    public static final int STATIC_FINAL_TYPE = 6;
    public static final int NONE = 7;
    
    String accessType;
    String variableType;
    
    BooleanProperty staticVar;
    
    public Variable(String name, String type, String accessType,boolean staticVar){
        this.staticVar = new SimpleBooleanProperty(false);
        this.name = name;
        this.type = type;
        this.accessType = accessType;
        this.staticVar.set(staticVar);
        this.variableType = "";
    }
    
    public Variable(String name, String type){
        this.name = name;
        this.type = type;
        this.variableType = "";
        this.accessType = "";
        this.staticVar = new SimpleBooleanProperty(false);
    }
    
    public Variable(String name, String type, int accessType, int variableType){
        this.staticVar = new SimpleBooleanProperty(false);
        this.name = name;
        this.type = type;
        
        switch(accessType){
            case 1:
                this.accessType = "public";
                break;
            case 2:
                this.accessType = "private";
                break;
            case 3:
                this.accessType = "protected";
                break;
            case 4:
                this.accessType = "";
                break;
            default:
                this.accessType = "";
                break;
        }
        
        switch(variableType){
            case 5:
                this.variableType = "static";
                this.staticVar = new SimpleBooleanProperty(true);
                break;
            case 6:
                this.variableType = "static final";
                this.staticVar = new SimpleBooleanProperty(true);
                break;
            case 7:
                this.variableType = "";
                break;
            default:
                this.variableType = "";
                break;
        }
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setAccessType(String accessType){
        this.accessType = accessType;
    }
    
    public void setVariableType(String variableType){
        this.variableType = variableType;
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public String getAccessType(){
        return accessType;
    }
    
    public String getVariableType(){
        return variableType;
    }
    
    public void setStatic(boolean s){
        staticVar.set(s);
    }
    
    public BooleanProperty isStaticProperty(){
        return staticVar;
    }
    
    public boolean isStatic(){
        return staticVar.get();
    }
    
    public boolean isFinal(){
        for(char c: name.toCharArray()){
            if(Character.isLowerCase(c) && Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
}
