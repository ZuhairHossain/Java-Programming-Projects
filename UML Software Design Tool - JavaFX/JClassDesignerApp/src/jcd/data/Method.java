package jcd.data;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * This class serves as the Method of DraggableClass and DraggableInterface
 *
 * @author Dennis Sosa
 * @version 1.0
 */
public class Method {
    // FIRST THE THINGS THAT HAVE TO BE SAVED TO Method
    public static final int PUBLIC_TYPE = 1;
    public static final int PRIVATE_TYPE = 2;
    public static final int PROTECTED_TYPE = 3;
    public static final int NO_TYPE = 4;
    
    public static final int STATIC_TYPE = 5;
    public static final int ABSTRACT_TYPE = 6;
    public static final int NONE = 7;
    
    String accessType;
    String methodType;
    
    boolean abstractMethod;
    boolean privateMethod;
    boolean protectedMethod;
    boolean publicMethod;
    
    String name;
    int parameters;
    String returnType;
    
    ArrayList<Variable> arguments;
    
    BooleanProperty abstractMeth;
    BooleanProperty staticMeth;
    
    public Method(String name, String returnType, int parameters, String accessType){
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.accessType = accessType;
        arguments = new ArrayList();
        this.abstractMeth = new SimpleBooleanProperty(false);
        this.staticMeth = new SimpleBooleanProperty(false);
        this.methodType = "";
    }
    
    public Method(String name, String returnType, String accessType){
        this.name = name;
        this.returnType = returnType;
        this.accessType = accessType;
        arguments = new ArrayList();
        this.abstractMeth = new SimpleBooleanProperty(true);
        this.staticMeth = new SimpleBooleanProperty(false);
        this.methodType = "";
    }
    
    public Method(String name, String returnType, int parameters, int accessType, int methodType){
        arguments = new ArrayList();
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.abstractMeth = new SimpleBooleanProperty(false);
        this.staticMeth = new SimpleBooleanProperty(false);
        
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
        
        switch(methodType){
            case 5:
                this.methodType = "static";
                break;
            case 6:
                this.methodType = "abstract";
                break;
            case 7:
                this.methodType = "";
                break;
            default:
                this.methodType = "";
                break;
        }
    }
    
    public void addParameter(String name, String type, int accessType, int variableType){
        Variable temp = new Variable(name,type,accessType,variableType);
        arguments.add(temp);
    }
    
    public void addParameter(String name, String type){
        int index = -1;
        Variable temp = new Variable(name,type);
        
        for(Variable v: arguments){
            if(v.getName().equals(name)){
                index = arguments.indexOf(v);
                break;
            }
        }
        
        if(index!=-1){
            arguments.set(index,temp);
        }
        else
            arguments.add(temp);
    }
    
    public void removeParameter(String name){
        int index = -1;
        
        for(Variable v: arguments){
            if(v.getName().equals(name)){
                index = arguments.indexOf(v);
                arguments.remove(v);
                break;
            }
        }
        
        for(int i=index; i<arguments.size(); i++){
            arguments.get(i).setName("arg"+(i+1));
        }
    }
    
    //public boolean isAbstract(){
        //return abstractMethod;
    //}
        
    public ArrayList<Variable> getArguments(){
        return arguments;
    }
    
    public String getReturnType(){
        return returnType;
    }
    
    public void setReturnType(String returnType){
        this.returnType = returnType;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getParameters(){
        return parameters;
    }
    
    public String getAccessType(){
        return accessType;
    }
    
    public void setAccessType(String accessType){
        this.accessType = accessType;
    }
    
    public String getMethodType(){
        return methodType;
    }
    
    public void setAbstract(boolean s){
        abstractMeth.set(s);
    }
    
    public BooleanProperty isAbstractProperty(){
        return abstractMeth;
    }
    
    public boolean isAbstract(){
        return abstractMeth.get();
    }
    
    public void setStatic(boolean s){
        staticMeth.set(s);
    }
    
    public BooleanProperty isStaticProperty(){
        return staticMeth;
    }
    
    public boolean isStatic(){
        return staticMeth.get();
    }
    
    public boolean isFinal(){
        for(char c: name.toCharArray()){
            if(Character.isLowerCase(c) && Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
    
    public String getArg1(){
        if(arguments.size()>=1)
            return arguments.get(0).getType();
        else
            return "";
    }
    
    public String getArg2(){
        if(arguments.size()>=2)
            return arguments.get(1).getType();
        else
            return "";
    }
    
    public String getArg3(){
        if(arguments.size()>=3)
            return arguments.get(2).getType();
        else
            return "";
    }
    
    public String getArg4(){
        if(arguments.size()>=4)
            return arguments.get(3).getType();
        else
            return "";
    }
    
    public String getArg5(){
        if(arguments.size()>=5)
            return arguments.get(4).getType();
        else
            return "";
    }
    
    public String getArg6(){
        if(arguments.size()>=6)
            return arguments.get(5).getType();
        else
            return "";
    }
    
    public String getArg7(){
        if(arguments.size()>=7)
            return arguments.get(6).getType();
        else
            return "";
    }
    
    public String getArg8(){
        if(arguments.size()>=8)
            return arguments.get(7).getType();
        else
            return "";
    }
    
    public String getArg9(){
        if(arguments.size()>=9)
            return arguments.get(8).getType();
        else
            return "";
    }
    
    public String getArg10(){
        if(arguments.size()>=10)
            return arguments.get(9).getType();
        else
            return "";
    }
}
