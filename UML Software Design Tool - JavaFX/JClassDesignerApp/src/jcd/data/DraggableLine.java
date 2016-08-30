package jcd.data;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * This is a draggable class for our pose application.
 * 
 * @author Dennis Sosa
 * @version 1.0
 */
public class DraggableLine extends Line {    
    String lineType;
    String parent;
    String child;
    
    double splitX;
    double splitY;
    
    public static final int INHERIT_TYPE = 1;
    public static final int IMPLEMENT_TYPE = 2;
    public static final int AGGREGATE_TYPE = 3;
    public static final int USES_TYPE = 4;
    
    Polygon inheritanceShape;
    Polygon aggregateShape;
    Polygon usesShape;
    
    public DraggableLine(int lineType){
        
        switch(lineType){
            case 1:
                this.lineType = "Inheritance";
                break;
            case 2:
                this.lineType = "Interface";
                break;
            case 3:
                this.lineType = "Aggregate";
                break;
            case 4:
                this.lineType = "Uses";
                break;
            default:
                this.lineType = "";
                break;
        }
        parent = "";
        child = "";
    }
    
    public DraggableLine(double startX, double startY, double endX, double endY, int lineType){
        super(startX,startY,endX,endY);
        switch(lineType){
            case 1:
                this.lineType = "Inheritance";
                break;
            case 2:
                this.lineType = "Interface";
                break;
            case 3:
                this.lineType = "Aggregate";
                break;
            case 4:
                this.lineType = "Uses";
                break;
            default:
                this.lineType = "";
                break;
        }
        parent = "";
        child = "";
    }
    
    /*
    public DraggableLine(double startX, double startY, double endX, double endY,int lineType){
        super(startX,startY,endX,endY);
        setStrokeWidth(2.5);
        
        switch(lineType){
            case 1:
                this.lineType = "Inheritance";
                inheritanceShape = new Polygon();
                inheritanceShape.getPoints().addAll(new Double[]{
                    endY, endX-50,
                    endX, endY, //endX=112.5,endY=87.5
                    endY, endX});
                this.endXProperty().bind(inheritanceShape.translateXProperty());
                this.endYProperty().bind(inheritanceShape.translateYProperty());
                break;
            case 2:
                this.lineType = "Interface";
                inheritanceShape = new Polygon();
                inheritanceShape.getPoints().addAll(new Double[]{
                    endY, endX-50,
                    endX, endY, //endX=112.5,endY=87.5
                    endY, endX});
                this.endXProperty().bind(inheritanceShape.translateXProperty());
                this.endYProperty().bind(inheritanceShape.translateYProperty());
                break;
            case 3:
                this.lineType = "Aggregate";
                aggregateShape = new Polygon();
                aggregateShape.getPoints().addAll(new Double[]{
                    endX, endY,//endX=12.5,endY=37.5
                    endY, endX,
                    endX+50, endY,
                    endY, endX+50});
                this.endXProperty().bind(aggregateShape.translateXProperty());
                this.endYProperty().bind(aggregateShape.translateYProperty());
                break;
            case 4:
                this.lineType = "Uses";
                usesShape = new Polygon();
                usesShape.getPoints().addAll(new Double[]{
                    endX, endY, //endX=157.5,endY=137.5
                    endY, endY-25.0,
                    endX+5.0, endY,
                    endY, endX+5.0});
                this.endXProperty().bind(usesShape.translateXProperty());
                this.endYProperty().bind(usesShape.translateYProperty());
                break;
            default:
                this.lineType = "";
                break;
        }
    }*/
        
        
    public String getLineType(){
        return lineType;
    }
    
    public void setLineType(String lineType){
        this.lineType = lineType;
    }
    
    public void setParentName(String parent){
        this.parent = parent;
    }
    
    public String getParentName(){
        return parent;
    }
    
    public void setChildName(String child){
        this.child = child;
    }
    
    public String getChildName(){
        return child;
    }
    
    public double getSplitX(){
        return splitX;
    }
    
    public void setSplitX(double d){
        splitX = d;
    }
    
    public double getSplitY(){
        return splitY;
    }
    
    public void setSplitY(double d){
        splitY = d;
    }
    public Polygon getAggregate(){
        return aggregateShape;
    }
    
    public Polygon getInheritance(){
        return inheritanceShape;
    }
    
    public void setInheritance(Polygon inheritanceShape){
        this.inheritanceShape = inheritanceShape;
    }
    
    public Polygon getUses(){
        return usesShape;
    }
    
    public void setUses(Polygon usesShape){
        this.usesShape = usesShape;
    }
    
    public void bindNow(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
      startXProperty().bind(startX);
      startYProperty().bind(startY);
      endXProperty().bind(endX);
      endYProperty().bind(endY);
      setStrokeWidth(3);
      setStroke(Color.RED);
    }
}
