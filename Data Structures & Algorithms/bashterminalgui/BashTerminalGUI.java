
package bashterminalgui;
/**
 * Dennis Sosa
 * Assignment: #5 (Ternary Tree File System)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * BashTerminalGUI Class
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class BashTerminalGUI extends Application {
      
   public static String outputResult = "Starting bash terminal.\n"+"[dsosa@CSE214Net]: $ ";
   
    /**
     * The main method runs a menu driven application which takes user input
     * and builds a DirectoryTree using the commands indicated so the user
     * can interact with a File System implemented by the DirectoryTree;
     * These commands all have the same effect as if they were executed in a 
     * live bash shell on any Unix-based OS
     * @param primaryStage
     *       sets up the stage for the JavaFX application
    */
    @Override
    public void start(Stage primaryStage) {
        ScrollPane sp = new ScrollPane();
        DirectoryTree root = new DirectoryTree();
        final VBox v3 = new VBox(7);
        
        v3.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observe, Object oldValue, Object newValue){
                    sp.setVvalue((Double)newValue);
            }
        });
        
        System.out.println("Starting bash terminal.");
        
        sp.setVmax(440);
        sp.setPrefSize(800,1200);
        sp.setStyle("-fx-background: black");
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,20,10,20));
        
        HBox topPane = new HBox();
        topPane.setStyle("-fx-background-color: blue");
        
        Text title = new Text(100,100, "Dennis Sosa's Bash Terminal\nCSE 214\n");
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        
        Font boldFont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24);
        title.setFont(boldFont);
        
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().add(title);
        
        borderPane.setTop(topPane);
        
        HBox bottomPane = new HBox();
        bottomPane.setStyle("-fx-background-color: blue");
        
        Text message = new Text(100,100, "MESSAGE: Please enter your Bash Terminal Command on the left.\n"
                + "Click on the button \"Enter Command Input \" & current output will be shown in the middle.\n\n");
        message.setFill(Color.WHITE);
        message.setFont(boldFont);
        message.setTextAlignment(TextAlignment.CENTER);
        
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().add(message);
        
        borderPane.setBottom(bottomPane);
        
        HBox centerPane = new HBox(7);
        centerPane.setStyle("-fx-border-color:#00bfff");
        
        borderPane.setCenter(centerPane);

        VBox leftPane = new VBox(10);
        VBox rightPane = new VBox(10);
             
        Button enterInput = new Button("Enter Command Input");
        VBox terminalMenu = new VBox(10);
        
        HBox hs1 = new HBox(5);
        TextField tf1 = new TextField();
          
        hs1.getChildren().add(new Label("[dsosa@CSE214Net]: $ "));
        hs1.setAlignment(Pos.CENTER);
                               
        terminalMenu.getChildren().addAll(hs1,tf1,enterInput);
        terminalMenu.setAlignment(Pos.CENTER);
        
        enterInput.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once enterInput is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                
                //Main menu of program
		
		//System.out.println();
			
		System.out.println("[dsosa@CSE214Net]: $ ");
			
		String selection = tf1.getText();
			
		//Goes on to the 'switch' statement in accordance to user-input
			
		if(selection.equals("pwd")){
                    System.out.println(root.presentWorkingDirectory());
                    outputResult += (selection+"\n"+root.presentWorkingDirectory()+"\n");
                }
			
		else if(selection.equals("ls")){
                    System.out.println(root.listDirectory());
                    outputResult += (selection+"\n"+root.listDirectory()+"\n");
                }
			
		else if(selection.equals("ls -R")){
                    root.printDirectoryTree();
                    System.out.println();
                    outputResult += (selection+"\n"+root.getDirectoryTree()+"\n");
		}
			
		else if(selection.equals("cd /")){
                    root.resetCursor();
                    outputResult += (selection+"\n");
                }
			
		else if(selection.equals("cd ..")){
                    outputResult += (selection+"\n");
                    try{
                        root.moveUp();
                    } catch(NotFoundException ex){
                        outputResult += (ex.getMessage()+"\n");
                    }
                }
			
		else if(selection.contains("cd ")&&selection.contains("/")){
                    String pathName = selection.substring(3, selection.length());
                    try{
                        outputResult += (selection+"\n");
                        root.changeToDirectoryPath(pathName);
                    } catch(NotADirectoryException ex){
                        System.out.println(ex.getMessage());
                        outputResult += (ex.getMessage()+"\n");
                    }
		}
				
				
		else if(selection.contains("cd ")){
                    String name = selection.substring(3, selection.length());
				
                    try{
                        outputResult += (selection+"\n");
			root.changeDirectory(name);
                    } catch(NotADirectoryException ex){
			System.out.println(ex.getMessage());
                        outputResult += (ex.getMessage()+"\n");
                    } catch(NotFoundException ex){
                        outputResult += (ex.getMessage()+"\n");
                        System.out.println();
                    }
		}

		else if(selection.contains("mkdir ")){
                    String name = selection.substring(6,selection.length());
				
                    try{
                        outputResult += (selection+"\n");
                        root.makeDirectory(name);
                    } catch(IllegalArgumentException ex){
			System.out.println(ex.getMessage());
                        outputResult += (ex.getMessage()+"\n");
                    } catch(FullDirectoryException ex){
			System.out.println(ex.getMessage());
                        outputResult += (ex.getMessage()+"\n");
                    }
		}
			
		else if(selection.contains("touch ")){
                    String name = selection.substring(6,selection.length());
				
                    try{
                        outputResult += (selection+"\n");
                        root.makeFile(name);
                    } catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                        outputResult += (ex.getMessage()+"\n");
                    } catch(FullDirectoryException ex){
                        outputResult += (ex.getMessage()+"\n");
			System.out.println(ex.getMessage());
                    }
		}
			
		else if(selection.contains("find ")){
                    String name = selection.substring(5, selection.length());
                    outputResult += (selection+"\n");
                    try{
                        root.findNode(name);
                        outputResult += root.getFind() + "\n";
                    } catch(NotFoundException ex){
                        outputResult += (ex.getMessage()+"\n");
                        System.out.println();
                    }
		}
			
		else if(selection.contains("mv ")){
                    String[] pathArr = selection.split("\\s+");
                    outputResult += (selection+"\n");
                    
                    if(pathArr.length < 3 || pathArr.length > 3){
			System.out.println("ERROR: Invalid entry, please try again.");
                        outputResult += ("ERROR: Invalid entry, please try again.");
                    }
                    else{
			String file = pathArr[1];				
			String destination = pathArr[2];
			root.moveFile(file, destination);
                    }
		}
				
		else if(selection.equals("exit")){
                    System.out.println("Program terminating normally");
                    outputResult += (selection+"\n"+"Program terminating normally\n");
                    outputResult += "Please click on the button to the right to fully exit.";
		}

                else{
                    System.out.println("Invalid entry, please try again.");
                    outputResult += (selection+"\n"+"Invalid entry, please try again.\n");
                }
                
                outputResult += ("[dsosa@CSE214Net]: $ ");
                
                v3.getChildren().clear();
                Label l3 = new Label(outputResult);
                l3.setTextFill(Color.LIGHTGREEN);
                v3.getChildren().add(l3);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
                borderPane.setCenter(centerPane);
                    
            }
            
        });
        
        HBox menu1 = new HBox();
        
        Label menu = new Label("Terminal Input");
        menu.setStyle("-fx-font-weight:bold");
        menu.setUnderline(true);
        
        menu1.getChildren().add(menu);
        menu1.setAlignment(Pos.CENTER);
   
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.getChildren().addAll(menu1,terminalMenu);
        leftPane.setStyle("-fx-border-color:#00bfff");
        borderPane.setLeft(leftPane);

        Button btnQ = new Button();
        btnQ.setText("Exit");
        
        btnQ.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnQ is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                System.out.println("\nProgram terminating normally...");
                
                System.exit(0);
            }
        });
        
        HBox h2 = new HBox(7);
        Label l2 = new Label(outputResult);
        l2.setTextFill(Color.LIGHTGREEN);
        h2.getChildren().add(l2);
        sp.setContent(h2);
        centerPane.getChildren().clear();
     
        centerPane.setAlignment(Pos.CENTER);
        centerPane.getChildren().add(sp);
        borderPane.setCenter(centerPane);
        
        HBox menu2 = new HBox();
        
        Label menuCont = new Label("Exit Terminal");
        menuCont.setStyle("-fx-font-weight: bold");
        menuCont.setUnderline(true);
        
        menu2.getChildren().add(menuCont);
        menu2.setAlignment(Pos.CENTER);
        rightPane.setAlignment(Pos.CENTER_LEFT);
        rightPane.getChildren().addAll(menu2,btnQ);
        rightPane.setStyle("-fx-border-color:#00bfff");
        borderPane.setRight(rightPane);

        Scene scene = new Scene(borderPane, 1250, 700);
        
        primaryStage.setTitle("Terminal - dsosa@CSE214Net: ~/Directory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Launches the JavaFX application/program
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
