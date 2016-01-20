package cse214hw2.gui;
/**
 * Dennis Sosa
 * Assignment: #2 (Train Car Manager)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * TrainManagerGUI Class
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


public class TrainManagerGUI extends Application {
    
    /**
    * The main method runs a menu driver application which first creates an empty TrainLinkedList object;
    * the program is an implemented Graphical User Interface using JavaFX Instructions;
    * The GUI allows the user to click buttons to execute an operation
    * in accordance to the menu
    * @param primaryStage
    *       sets up the stage for the JavaFX application
    * @throws IllegalArgumentException
    *       indicates invalid entries within the TrainLinkedList & ProductLoad class 
    *       & within the main program
    */
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Welcome to Dennis Sosa's Train Manager.\n");
        System.out.println("\nPlease click on the buttons to enter your selection.");
        System.out.println();
        System.out.println("(F) Cursor Forward");
	System.out.println("(B) Cursor Backward");
        System.out.println("(I) Insert Car After Cursor");
	System.out.println("(R) Remove Car At Cursor");
	System.out.println("(L) Set Product Load");
	System.out.println("(S) Search For Product");
	System.out.println("(T) Display Train");
	System.out.println("(M) Display Manifest");
	System.out.println("(D) Remove Dangerous Cars");
	System.out.println("(Q) Quit");
	System.out.println();
        
        TrainLinkedList train = new TrainLinkedList();
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,20,10,20));
        
        HBox topPane = new HBox();
        topPane.setStyle("-fx-background-color: blue");
        
        Text title = new Text(100,100, "Dennis Sosa's Train Car Manager\nCSE 214\n");
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        
        Font boldFont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24);
        title.setFont(boldFont);
        
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().add(title);
        
        borderPane.setTop(topPane);
        
        HBox bottomPane = new HBox();
        bottomPane.setStyle("-fx-background-color: blue");
        
        Text message = new Text(100,100, "MESSAGE: Please click on the button of your choice to enter"
                + "\nyour selection corresponding to the Train Doubly Linked List.\n");
        message.setFill(Color.WHITE);
        message.setFont(boldFont);
        
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().add(message);
        
        borderPane.setBottom(bottomPane);
        
        HBox centerPane = new HBox();
        centerPane.setStyle("-fx-border-color:red; -fx-font-family: monospace");
        
        borderPane.setCenter(centerPane);

        VBox leftPane = new VBox(10);
        VBox rightPane = new VBox(10);
        
        Button btnF = new Button();
        btnF.setText("(F) Cursor Forward");
        
        btnF.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnF is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                try{
                    train.cursorForward();
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(new Label("\nCursor moved forward."));
                    borderPane.setCenter(centerPane);
                    System.out.println("\nCursor moved forward.");
		} catch(Exception ex){
                    System.out.println(ex.getMessage());
                    centerPane.setAlignment(Pos.CENTER);
                    Label except = new Label(ex.getMessage());
                    except.setTextFill(Color.RED);
                    centerPane.getChildren().add(except);
                    borderPane.setCenter(centerPane);
		}
            }
        });
        
        
        Button btnB = new Button();
        btnB.setText("(B) Cursor Backward");
        
        btnB.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnB is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                try{
                    train.cursorBackward();
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(new Label("\nCursor moved backward."));
                    borderPane.setCenter(centerPane);
                    System.out.println("\nCursor moved backward.");
                } catch(Exception ex){
                    System.out.println(ex.getMessage());
                    centerPane.setAlignment(Pos.CENTER);
                    Label except = new Label(ex.getMessage());
                    except.setTextFill(Color.RED);
                    centerPane.getChildren().add(except);
                    borderPane.setCenter(centerPane);
                }
            }
        });
        
        Button btnI = new Button();
        btnI.setText("(I) Insert Car After Cursor");
        
        Button enterInsert = new Button("Enter Length & Weight");
        VBox insertMenu = new VBox(10);
        
        HBox h1 = new HBox(5);
        h1.getChildren().add(new Label("Enter the car length in meters and Enter the car weight in tons below:\n"));
        h1.setAlignment(Pos.CENTER);
                
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
                
        HBox h2 = new HBox(5);
        h2.getChildren().addAll(new Label("Car Length (m):"),tf1,new Label("Car Weight (t):"),tf2);
        h2.setAlignment(Pos.CENTER);
                
        HBox h3 = new HBox(5);
        h3.getChildren().addAll(enterInsert);
        h3.setAlignment(Pos.CENTER);
        
        insertMenu.getChildren().addAll(h1,h2,h3);
        insertMenu.setAlignment(Pos.CENTER);
        
        btnI.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnI is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();

                System.out.println();
                System.out.println("Enter car length in meters: ");
						
                System.out.println("Enter car weight in tons: ");
                
                centerPane.getChildren().add(insertMenu);
                centerPane.setAlignment(Pos.CENTER);
                borderPane.setCenter(centerPane); 
                
                enterInsert.setOnAction(e -> perform());
            }
            /**
             * Following method is instantiated 
             * once required fields are required
             */
            public void perform(){
                double carLen = Double.parseDouble(tf1.getText());
                double carWeight = Double.parseDouble(tf2.getText());
                
                try{
                    TrainCar insert = new TrainCar(carLen,carWeight);
						
                    train.insertAfterCursor(insert);
                    
                    System.out.printf("\nNew train car %.1f meters %.1f tons "
				+ "inserted into train.\n",carLen,carWeight);
                    
                    centerPane.getChildren().clear();
                    
                    String posMessage = String.format("\nNew train car %.1f meters %.1f tons "
				+ "inserted into train.\n",carLen,carWeight);
                    centerPane.getChildren().add(new Label(posMessage));
                    centerPane.setAlignment(Pos.CENTER);
                    borderPane.setCenter(centerPane);
                    
		} catch(IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                    centerPane.setAlignment(Pos.CENTER);
                    Label except = new Label(ex.getMessage());
                    except.setTextFill(Color.RED);
                    centerPane.getChildren().add(except);
                    borderPane.setCenter(centerPane); 
                }
            }
        });
        
        
        
        Button btnR = new Button();
        btnR.setText("(R) Remove Car At Cursor");
        
        btnR.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnR is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                try{
                    TrainCar removedCar = train.removeCursor();

                    System.out.println("Car successfully unlinked. The following load has been removed from the train: ");
                    System.out.println();
                    System.out.println(" Name\t\tWeight (t)\tValue ($)\tDangerous");
                    System.out.println("=============================================================");
                    String danger = (removedCar.getProductLoad()!=null && removedCar.getProductLoad().getDangerous())?"YES":"NO";
							
                    System.out.printf(" %-15s%-16.1f%,-16.2f%-7s\n",(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getName():"Empty",
			(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getWeight():0,
                        (removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getValue():0,danger);
                    
                    String a = ("Car successfully unlinked. The following load has been removed from the train: \n\n");
                    String b = (" Name\t\tWeight (t)\tValue ($)\tDangerous\n");
                    String c = ("=============================================================\n");
                    String danger1 = (removedCar.getProductLoad()!=null && removedCar.getProductLoad().getDangerous())?"YES":"NO";
							
                    String e = String.format(" %-15s%-16.1f%,-16.2f%-7s\n",(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getName():"Empty",
			(removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getWeight():0,
                        (removedCar.getProductLoad()!=null)?removedCar.getProductLoad().getValue():0,danger1);
                    centerPane.setAlignment(Pos.CENTER);
                    
                    //centerPane.setStyle("-fx-font-family: monospace");
                    centerPane.getChildren().add(new Label(a+b+c+e));
                    borderPane.setCenter(centerPane);
                    
		} catch(Exception ex){
                    System.out.println(ex.getMessage());
                    
                    centerPane.setAlignment(Pos.CENTER);
                    Label except = new Label(ex.getMessage());
                    except.setTextFill(Color.RED);
                    centerPane.getChildren().add(except);
                    borderPane.setCenter(centerPane);  
                }
            }
        });
        
        Button btnL = new Button();
        btnL.setText("(L) Set Product Load");
        
        VBox setMenu = new VBox(10);
        Button setProd = new Button("Enter All Fields");
        
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();
        
        HBox hl1 = new HBox(5);
        hl1.getChildren().add(new Label("Enter all of the information for the product at the cursor below:\n"));
        hl1.setAlignment(Pos.CENTER);
                
        HBox hl2 = new HBox(5);
        hl2.getChildren().addAll(new Label("Product Name:"),tf3,new Label("Product Weight (tons):"),tf4);
        hl2.setAlignment(Pos.CENTER);
        
        HBox hl3 = new HBox(5);
        hl3.getChildren().addAll(new Label("Product Value ($):"),tf5,new Label("Product Dangerous? (y/n):"),tf6);
        hl3.setAlignment(Pos.CENTER);
                
        HBox hl4 = new HBox(5);
        hl4.getChildren().add(setProd);
        hl4.setAlignment(Pos.CENTER);
        
        setMenu.getChildren().addAll(hl1,hl2,hl3,hl4);
        setMenu.setAlignment(Pos.CENTER);
        
        btnL.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnL is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                
                System.out.println();
                System.out.println("Enter product name: ");
		System.out.println("Enter product weight in tons: ");
		System.out.println("Enter product value in dollars: ");
		System.out.println("Enter is product dangerous? (y/n): ");

                centerPane.getChildren().add(setMenu);
                centerPane.setAlignment(Pos.CENTER);
                borderPane.setCenter(centerPane); 
                
                setProd.setOnAction(e -> performL());
            }
            /**
             * Following method is instantiated 
             * once required fields are required
             */
            public void performL(){
                String prodName = tf3.getText();
                double prodWeight = Double.parseDouble(tf4.getText());
                double prodValue = Double.parseDouble(tf5.getText());
                String dangerousInput = tf6.getText();

		char dangerousCheck = dangerousInput.charAt(0);
                
                centerPane.getChildren().clear();
		switch(dangerousCheck){
                    case 'y':
                        try{
                            if(train.getCursorData().getProductLoad() != null){
                                train.decProdWeight(train.getCursorData().getProductLoad().getWeight());
                                train.decProdValue(train.getCursorData().getProductLoad().getValue());
                                if(train.getCursorData().getProductLoad().getDangerous())
                                    train.decDanger();
                            }
                            ProductLoad tempLoadDangY = new ProductLoad(prodName,
                                    prodWeight,prodValue,true);
                            train.getCursorData().setProductLoad(tempLoadDangY);
                            
                            train.addProdWeight(prodWeight);
                            train.addProdValue(prodValue);
                            train.addDanger();

                            System.out.println();
                            System.out.println(prodWeight+" tons of "+prodName+" added to the current car.");
                            
                            centerPane.getChildren().add(new Label("\n"+prodWeight+" tons of "+prodName+" added to the current car.\n"));
                            centerPane.setAlignment(Pos.CENTER);
                            borderPane.setCenter(centerPane);
                            
                        } catch(IllegalArgumentException ex){
                            System.out.println(ex.getMessage());
                            Label exceptMes = new Label(ex.getMessage());
                            exceptMes.setTextFill(Color.RED);
                            centerPane.getChildren().add(exceptMes);
                            centerPane.setAlignment(Pos.CENTER);
                            borderPane.setCenter(centerPane);
                        } catch(Exception ex){
                            System.out.println(ex.getMessage());
                            
                            Label exceptMes = new Label(ex.getMessage());
                            exceptMes.setTextFill(Color.RED);
                            centerPane.getChildren().add(exceptMes);
                            centerPane.setAlignment(Pos.CENTER);
                            borderPane.setCenter(centerPane);
                        }
			break;

                    case 'n':
			try{
                            if(train.getCursorData().getProductLoad() != null){
                                train.decProdWeight(train.getCursorData().getProductLoad().getWeight());
                                train.decProdValue(train.getCursorData().getProductLoad().getValue());
                                if(train.getCursorData().getProductLoad().getDangerous())
                                    train.decDanger();
                            }
                            ProductLoad tempLoadDangY = new ProductLoad(prodName,
                                prodWeight,prodValue,false);
                            train.getCursorData().setProductLoad(tempLoadDangY);
                            
                            train.addProdWeight(prodWeight);
                            train.addProdValue(prodValue);

                            System.out.println();
                            System.out.println(prodWeight+" tons of "+prodName+" added to the current car.");
                            
                            centerPane.getChildren().add(new Label("\n"+prodWeight+" tons of "+prodName+" added to the current car.\n"));
                            centerPane.setAlignment(Pos.CENTER);
                            borderPane.setCenter(centerPane);
                            
                        } catch(IllegalArgumentException ex){
                            System.out.println(ex.getMessage());
                            Label exceptMes = new Label(ex.getMessage());
                            exceptMes.setTextFill(Color.RED);
                            centerPane.getChildren().add(exceptMes);
                            centerPane.setAlignment(Pos.CENTER);
                            borderPane.setCenter(centerPane);
                        } catch(Exception ex){
                            System.out.println(ex.getMessage());
                            Label exceptMes = new Label(ex.getMessage());
                            exceptMes.setTextFill(Color.RED);
                            centerPane.getChildren().add(exceptMes);
                            centerPane.setAlignment(Pos.CENTER);
                            borderPane.setCenter(centerPane);
                        }
			break;

                    default:
			System.out.println("Invalid input for product danger input.\nTry again.");
                        Label invalid = new Label("Invalid input for product danger input.");
                        invalid.setTextFill(Color.RED);
                        centerPane.getChildren().add(invalid);
                        centerPane.setAlignment(Pos.CENTER);
                        borderPane.setCenter(centerPane);
			break;
                }
            
            }
        });
        
        HBox menu1 = new HBox();
        
        Label menu = new Label("Selection Menu");
        menu.setStyle("-fx-font-weight:bold");
        menu.setUnderline(true);
        
        menu1.getChildren().add(menu);
        menu1.setAlignment(Pos.CENTER);
   
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.getChildren().addAll(menu1,btnF,btnB,btnI,btnR,btnL);
        leftPane.setStyle("-fx-border-color:red");
        borderPane.setLeft(leftPane);
        
        Button btnS = new Button();
        btnS.setText("(S) Search For Product");
        
        Button productName = new Button("Enter Name");
        
        HBox hs1 = new HBox(5);
        HBox hs2 = new HBox(5);
        
        TextField tf7 = new TextField();
        
        VBox searchMenu = new VBox(10);
        
        hs1.getChildren().add(new Label("Enter The Product Name Being Searched: "));
        hs1.setAlignment(Pos.CENTER);
                
        hs2.getChildren().addAll(new Label("Product Name:"),tf7,productName);
        hs2.setAlignment(Pos.CENTER);
                
        searchMenu.getChildren().addAll(hs1,hs2);
        searchMenu.setAlignment(Pos.CENTER);
        
        btnS.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnS is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                
                System.out.println();
		System.out.println("Enter product name: ");
                
                centerPane.getChildren().add(searchMenu);
                centerPane.setAlignment(Pos.CENTER);
                borderPane.setCenter(centerPane); 
                
                productName.setOnAction(e -> performS());
            }
            /**
             * Following method is instantiated 
             * once required fields are required
             */
            public void performS(){
                String productName = tf7.getText();
                                
                train.findProduct(productName);
                centerPane.getChildren().clear();
                
                //centerPane.setStyle("-fx-font-family: monospace");
                centerPane.getChildren().add(new Label(train.searchProdString()));
                centerPane.setAlignment(Pos.CENTER);
                borderPane.setCenter(centerPane);
            }
        }); 
        
        Button btnT = new Button();
        btnT.setText("(T) Display Train");
        
        btnT.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnT is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                System.out.println(train.toString());
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(new Label(train.toString()));
                borderPane.setCenter(centerPane);
            }
        });
        
        Button btnM = new Button();
        btnM.setText("(M) Display Manifest");
        
        btnM.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnM is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                train.printManifest();
                
                //centerPane.setStyle("-fx-font-family: monospace");
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(new Label(train.manifestString()));
                borderPane.setCenter(centerPane);
            }
        });
        
        Button btnD = new Button();
        btnD.setText("(D) Remove Dangerous Cars");
        
        btnD.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnD is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                
                try{
                    train.removeDangerousCars();
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(new Label("If the train contained dangerous cars, "
                            + "they were successfully removed from the train."));
                    borderPane.setCenter(centerPane);
		} catch(Exception ex){
                    System.out.println(ex.getMessage());
                    centerPane.setAlignment(Pos.CENTER);
                    Label except = new Label(ex.getMessage());
                    except.setTextFill(Color.RED);
                    centerPane.getChildren().add(except);
                    borderPane.setCenter(centerPane);
		}
            }
        });
        
        Button btnQ = new Button();
        btnQ.setText("(Q) Quit");
        
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
                System.out.println("\nProgram terminating successfully...");
                
                System.exit(0);
            }
        });
        
        HBox menu2 = new HBox();
        
        Label menuCont = new Label("Selection Menu Cont.");
        menuCont.setStyle("-fx-font-weight: bold");
        menuCont.setUnderline(true);
        
        menu2.getChildren().add(menuCont);
        menu2.setAlignment(Pos.CENTER);
        rightPane.setAlignment(Pos.CENTER_LEFT);
        rightPane.getChildren().addAll(menu2,btnS,btnT,btnM,btnD,btnQ);
        rightPane.setStyle("-fx-border-color:red");
        borderPane.setRight(rightPane);

        Scene scene = new Scene(borderPane, 1250, 700);
        
        primaryStage.setTitle("Train Car Manager");
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
