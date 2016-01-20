package auctionsystemgui;
/**
 * Dennis Sosa
 * Assignment: #6 (Extracting Auction Data Online)
 * CSE 214 
 * Stony Brook University
 * @author DennisSosa
 * 
 * AuctionSystemGUI Class
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class AuctionSystemGUI extends Application {
    private static AuctionTable auctionTable = new AuctionTable();
    private static String username;
    public static String outputResult = "Starting...\n";
    private static String out = "";
    /**
    * The main method runs a menu driver application which first creates an empty AuctionTable object;
    * the program is an implemented Graphical User Interface using JavaFX Instructions;
    * The GUI allows the user to click buttons to execute an operation
    * in accordance to the menu;
    * The main method runs a menu driven application which allows the user to interact
    * with the database by listing open Auctions, making bids on open Auctions, and creating
    * new Auctions for different items; In addition, the class provides the functionality to load
    * a saved (serialized) AuctionTable or create a new one if a saved table does not exist.
    * @param primaryStage
    *       sets up the stage for the JavaFX application
    */
    @Override
    public void start(Stage primaryStage) {
        ScrollPane sp = new ScrollPane();
        //AuctionTable auctionTable = new AuctionTable();
        final VBox v3 = new VBox(7);
        
        v3.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observe, Object oldValue, Object newValue){
                    sp.setVvalue((Double)newValue);
            }
        });
        
        sp.setVmax(440);
        sp.setPrefSize(800,1200);
        sp.setStyle("-fx-font-family: monospace");
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,20,10,20));
        
        HBox topPane = new HBox();
        topPane.setStyle("-fx-background-color: blue");
        
        Text title = new Text(100,100, "Dennis Sosa's Online Auction Data Extractor\nCSE 214\n");
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
                + "\nyour selection corresponding to the AuctionTable.\n");
        message.setFill(Color.WHITE);
        message.setFont(boldFont);
        message.setTextAlignment(TextAlignment.CENTER);
        
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().add(message);
        
        borderPane.setBottom(bottomPane);
        
        HBox centerPane = new HBox();
        centerPane.setStyle("-fx-border-color:red; -fx-font-family: monospace");
        
        try{
		FileInputStream file = new FileInputStream("auction.obj");
		ObjectInputStream inStream = new ObjectInputStream(file);
		AuctionTable auctions;
			
		auctions = (AuctionTable) inStream.readObject();
		auctionTable = (AuctionTable) auctions.clone();
		System.out.println("Loading previous Auction Table...");
                outputResult += ("Loading previous Auction Table...\n");
                outputResult += ("Please click on the \"Please Select a Username\" button on the left before proceeding.\n");
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
	} catch(FileNotFoundException ex){
		System.out.println("No previous auction table detected.");
		System.out.println("Creating new table...");
                outputResult += ("No previous auction table detected.\nCreating new table...\n");
                outputResult += ("Please click on the \"Please Select a Username\" button on the left before proceeding.\n");
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
	} catch(IOException ex){
		System.out.println("No previous auction table detected.");
		System.out.println("Creating new table...");
                outputResult += ("No previous auction table detected.\nCreating new table...\n");
                outputResult += ("Please click on the \"Please Select a Username\" button on the left before proceeding.\n");
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
	} catch(ClassNotFoundException ex){
		System.out.println("No previous auction table detected.");
		System.out.println("Creating new table...");
                outputResult += ("No previous auction table detected.\nCreating new table...\n");
                outputResult += ("Please click on the \"Please Select a Username\" button on the left before proceeding.\n");
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
	}
        
        borderPane.setCenter(centerPane);

        VBox leftPane = new VBox(10);
        VBox rightPane = new VBox(10);
        
        Button btnD = new Button();
        btnD.setText("(D) Import Data from URL");
        
        Button enterURL = new Button("Enter URL");
        VBox importDataMenu = new VBox(10);
        
        HBox h1 = new HBox(5);
        h1.getChildren().add(new Label("Import Data from URL:\n"));
        h1.setAlignment(Pos.CENTER);
                
        TextField tf1 = new TextField();
                
        HBox h2 = new HBox(5);
        h2.getChildren().addAll(new Label("Please enter a URL:"),tf1);
        h2.setAlignment(Pos.CENTER);
                
        HBox h3 = new HBox(5);
        h3.getChildren().add(enterURL);
        h3.setAlignment(Pos.CENTER);
        
        importDataMenu.getChildren().addAll(h1,h2,h3);
        importDataMenu.setAlignment(Pos.CENTER);
        
        Button btnE = new Button();
        btnE.setText("(E) Please Select a Username");
        
        Button enterUsername = new Button("Enter Username");
        VBox userNameMenu = new VBox(10);
        
        HBox hu1 = new HBox(5);
        hu1.getChildren().add(new Label("Enter a username:\n"));
        hu1.setAlignment(Pos.CENTER);
                
        TextField tf0 = new TextField();
                
        HBox hu2 = new HBox(5);
        hu2.getChildren().addAll(new Label("Please enter a username:"),tf0);
        hu2.setAlignment(Pos.CENTER);
                
        HBox hu3 = new HBox(5);
        hu3.getChildren().add(enterUsername);
        hu3.setAlignment(Pos.CENTER);
        
        userNameMenu.getChildren().addAll(hu1,hu2,hu3);
        userNameMenu.setAlignment(Pos.CENTER);
        
        btnE.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnE is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                try{
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(userNameMenu);
                    borderPane.setCenter(centerPane);
                    enterUsername.setOnAction(e -> performE());
                    
		} catch(Exception ex){
                    System.out.println(ex.getMessage());
                    outputResult += (ex.getMessage()+"\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
		} 
            }
            
            public void performE(){
                centerPane.getChildren().clear();
                
                username = tf0.getText();
                
                System.out.println("\nThank you, please proceed.");
                outputResult += ("\nThank you, please proceed.\n");
 
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
                borderPane.setCenter(centerPane);
            }
        });
        
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
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(importDataMenu);
                    borderPane.setCenter(centerPane);
                    enterURL.setOnAction(e -> performD());    
		} catch(Exception ex){
                    System.out.println(ex.getMessage());
                    outputResult += (ex.getMessage()+"\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
		} 
            }
            public void performD(){
                centerPane.getChildren().clear();
                String URL = tf1.getText();
                try {
                    auctionTable = auctionTable.buildFromURL(URL);
                    System.out.println("Loading...");
                    outputResult += ("Loading...\n");
                    System.out.println("Auction data loaded successfully!");
                    outputResult += ("Auction data loaded successfully!\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                    
                } catch(IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                    outputResult += (ex.getMessage()+"\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                }
            }
        });
        
        
        Button btnA = new Button();
        btnA.setText("(A) Create a New Auction");
        
        Button enterNew = new Button("Enter Auction");
        VBox newAuctionMenu = new VBox(10);
        
        TextField tfAID = new TextField();
        TextField auctionT = new TextField();
        TextField itemIn = new TextField();
        
        HBox ha1 = new HBox(5);
        ha1.getChildren().addAll(new Label("Please enter an Auction ID: "),tfAID);
        ha1.setAlignment(Pos.CENTER);
                
        HBox ha2 = new HBox(5);
        ha2.getChildren().addAll(new Label("Please enter an Auction time (hours): "),auctionT);
        ha2.setAlignment(Pos.CENTER);
        
        HBox ha3 = new HBox(5);
        ha3.getChildren().addAll(new Label("Please enter some Item Info: "),itemIn);
        ha3.setAlignment(Pos.CENTER);
        
        newAuctionMenu.getChildren().addAll(ha1,ha2,ha3,enterNew);
        newAuctionMenu.setAlignment(Pos.CENTER);
        
        btnA.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnA is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                System.out.println("\nCreating new Auction as "+username+".");
                outputResult += ("\nCreating new Auction as "+username+".\n");
                centerPane.getChildren().add(newAuctionMenu);
                enterNew.setOnAction(e -> performA());
            }
            
            public void performA(){
                centerPane.getChildren().clear();
                System.out.println("Please enter an Auction ID: ");
		String auctionID0 = tfAID.getText();
		System.out.println("Please enter an Auction time (hours): ");
		int auctionTime = Integer.parseInt(auctionT.getText());
		System.out.println("Please enter some Item Info: ");
		String itemInfo = itemIn.getText();
		Auction newAuction = new Auction(auctionTime,auctionID0,username,itemInfo);
						
		try{
                    auctionTable.putAuction(auctionID0, newAuction);
                    System.out.println("Auction "+auctionID0+" inserted into table.");
                    outputResult += ("Auction "+auctionID0+" inserted into table.\n");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                    
		} catch(IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                    outputResult += (ex.getMessage()+"\n");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
		}
            }
        });
        
        Button btnB = new Button();
        btnB.setText("(B) Bid on an Item");
        
        Button enterID = new Button("Enter Auction ID");
        VBox bidMenu = new VBox(10);
        
        TextField tfID = new TextField();
        
        HBox hb1 = new HBox(5);
        hb1.getChildren().addAll(new Label("Please enter an Auction ID: "),tfID);
        hb1.setAlignment(Pos.CENTER);
                
        HBox hb2 = new HBox(5);
        hb2.getChildren().add(enterID);
        hb2.setAlignment(Pos.CENTER);
        
        bidMenu.getChildren().addAll(hb1,hb2);
        bidMenu.setAlignment(Pos.CENTER);
        
        btnB.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnI is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                out = "";
                centerPane.getChildren().clear();
                centerPane.getChildren().add(bidMenu);
                enterID.setOnAction(e -> performB()); 
            }
            /**
             * Following method is instantiated 
             * once required fields are required
             */
            public void performB(){
               centerPane.getChildren().clear();
               String auctionID = tfID.getText();
						
		if(auctionTable.getAuction(auctionID)!=null && auctionTable.getAuction(auctionID).getTimeRemaining()!=0){
                    System.out.println("\nAuction "+auctionID+" is OPEN");
                    out += ("\nAuction "+auctionID+" is OPEN\n");
                    double cb = auctionTable.getAuction(auctionID).getCurrentBid();
							
                    if(cb==0){
                        System.out.println("\tCurrent Bid: None");
                        out += ("\tCurrent Bid: None\n");
                    }
                    
                    else{
                        System.out.printf("\tCurrent Bid: $ %.2f\n",cb);
                        out += String.format("\tCurrent Bid: $ %.2f\n",cb);
                    }
                    
                    System.out.print("What would you like to bid?: ");
                    Button enterBid = new Button("Enter Bid");
                    VBox otherBidMenu = new VBox(10);
        
                    TextField tfBid = new TextField();
                    HBox hb3 = new HBox(5);
                    hb3.getChildren().addAll(new Label(out));
                    hb3.setAlignment(Pos.CENTER);
        
                    HBox hb4 = new HBox(5);
                    hb4.getChildren().addAll(new Label("What would you like to bid?: "),tfBid);
                    hb4.setAlignment(Pos.CENTER);
                
                    HBox hb5 = new HBox(5);
                    hb5.getChildren().add(enterBid);
                    hb5.setAlignment(Pos.CENTER);
        
                    otherBidMenu.getChildren().addAll(hb3,hb4,hb5);
                    otherBidMenu.setAlignment(Pos.CENTER);
                    
                    centerPane.getChildren().add(otherBidMenu);
                    enterBid.setOnAction(e -> performB2(auctionID,tfBid));
		}
						
		else if(auctionTable.getAuction(auctionID)!=null && auctionTable.getAuction(auctionID).getTimeRemaining()==0){
                    System.out.println("\nAuction "+auctionID+" is CLOSED");
                    outputResult += ("\nAuction "+auctionID+" is CLOSED\n");
                    double cb = auctionTable.getAuction(auctionID).getCurrentBid();
							
                    if(cb==0){
			System.out.println("\tCurrent Bid: None");
                        outputResult += ("\tCurrent Bid: None\n");
                    }
                    else{
                        System.out.printf("\tCurrent Bid: $ %.2f\n",cb);
                        outputResult += String.format("\tCurrent Bid: $ %.2f\n",cb);
                    }
							
                    System.out.println("You can no longer bid on this item.");
                    outputResult += ("You can no longer bid on this item.");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
		}
						
		else if(auctionTable.getAuction(auctionID)==null){
                    System.out.println("\nAuction "+auctionID+" was not found in the AuctionTable.");
                    outputResult += ("\nAuction "+auctionID+" was not found in the AuctionTable.\n");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                }
            }
            
            public void performB2(String auctionID,TextField tfBid){
                centerPane.getChildren().clear();
                double bidValue = Double.parseDouble(tfBid.getText());
  							
                try{
                    auctionTable.getAuction(auctionID).newBid(username,bidValue);
                    outputResult += auctionTable.getAuction(auctionID).getBidAccept();
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                    
                } catch(ClosedAuctionException ex){
                    System.out.println(ex.getMessage());
                    outputResult += (ex.getMessage()+"\n");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                }
            }
        });
        
        
        
        Button btnI = new Button();
        btnI.setText("(I) Get Info on Auction");
        
        Button enterInfo = new Button("Enter Auction ID");
        VBox infoMenu = new VBox(10);
        
        TextField tfInfo = new TextField();
        
        HBox hi1 = new HBox(5);
        hi1.getChildren().addAll(new Label("Please enter an Auction ID: "),tfInfo);
        hi1.setAlignment(Pos.CENTER);
                
        HBox hi2 = new HBox(5);
        hi2.getChildren().add(enterInfo);
        hi2.setAlignment(Pos.CENTER);
        
        infoMenu.getChildren().addAll(hi1,hi2);
        infoMenu.setAlignment(Pos.CENTER);
        
        btnI.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnR is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                centerPane.getChildren().add(infoMenu);
                enterInfo.setOnAction(e -> performI());                
            }
            
            public void performI(){
                centerPane.getChildren().clear();
                System.out.print("Please enter an Auction ID: ");
                String auctionID2 = tfInfo.getText();
						
		if(auctionTable.getAuction(auctionID2)!=null){
                    System.out.println("\n\n\nAuction "+auctionID2+":");
                    outputResult += ("\nAuction "+auctionID2+":\n");
                    System.out.println("\tSeller: "+auctionTable.getAuction(auctionID2).getSellerName());
                    outputResult += ("\tSeller: "+auctionTable.getAuction(auctionID2).getSellerName()+"\n");
                    System.out.println("\tBuyer: "+auctionTable.getAuction(auctionID2).getBuyerName());
                    outputResult += ("\tBuyer: "+auctionTable.getAuction(auctionID2).getBuyerName()+"\n");
                    System.out.println("\tTime: "+auctionTable.getAuction(auctionID2).getTimeRemaining());
                    outputResult += ("\tTime: "+auctionTable.getAuction(auctionID2).getTimeRemaining()+"\n");
                    System.out.println("\tInfo: "+auctionTable.getAuction(auctionID2).getItemInfo());
                    outputResult += ("\tInfo: "+auctionTable.getAuction(auctionID2).getItemInfo()+"\n\n");
                    System.out.println();
		}
						
		else if(auctionTable.getAuction(auctionID2)==null){
                    System.out.println("\nAuction "+auctionID2+" was not found in the AuctionTable.");
                    System.out.println("No information can be retrieved.");
                    outputResult += ("\nAuction "+auctionID2+" was not found in the AuctionTable.\n"+"No information can be retrieved.\n");
		}
                
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
                borderPane.setCenter(centerPane);
            }
        });
        
        Button btnP = new Button();
        btnP.setText("(P) Print All Auctions");
        
        
        btnP.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Performs the following
             * lines of code once btnP is pressed
             * @param event
             *      ActionEvent handler to detect button pressed
             */
            @Override
            public void handle(ActionEvent event) {
                centerPane.getChildren().clear();
                
                auctionTable.printTable();
                outputResult += (auctionTable.toString()+"\n\n");
                
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
                borderPane.setCenter(centerPane);
            }
            
        });
        
        HBox menu1 = new HBox();
        
        Label menu = new Label("Selection Menu");
        menu.setStyle("-fx-font-weight:bold");
        menu.setUnderline(true);
        
        menu1.getChildren().add(menu);
        menu1.setAlignment(Pos.CENTER);
   
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.getChildren().addAll(btnE,new HBox(5),menu1,btnD,btnA,btnB,btnI);
        leftPane.setStyle("-fx-border-color:red");
        borderPane.setLeft(leftPane);
        
        Button btnR = new Button();
        btnR.setText("(R) Remove Expired Auctions");
        
        btnR.setOnAction(new EventHandler<ActionEvent>() {
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
		System.out.println("Removing expired auctions...");
                outputResult += ("\nRemoving expired auctions...\n");
                
		auctionTable.removeExpiredAuctions();
		System.out.println("All expired auctions removed.");
                outputResult += ("All expired auctions removed.\n");
                
                v3.getChildren().clear();
                Label except = new Label(outputResult);
                v3.getChildren().add(except);
                
                sp.setContent(v3);
                
                centerPane.setAlignment(Pos.CENTER);
                centerPane.getChildren().add(sp);
                borderPane.setCenter(centerPane);
                
            }
        }); 
        
        Button btnT = new Button();
        btnT.setText("(T) Let Time Pass");
        
        Button enterTime = new Button("Enter Time");
        VBox letTimePassMenu = new VBox(10);
        
        TextField tfTime = new TextField();
        
        HBox ht1 = new HBox(5);
        ht1.getChildren().addAll(new Label("How many hours should pass: "),tfTime);
        ht1.setAlignment(Pos.CENTER);
                
        HBox ht2 = new HBox(5);
        ht2.getChildren().add(enterTime);
        ht2.setAlignment(Pos.CENTER);
        
        letTimePassMenu.getChildren().addAll(ht1,ht2);
        letTimePassMenu.setAlignment(Pos.CENTER);
        
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
               centerPane.getChildren().add(letTimePassMenu);
               enterTime.setOnAction(e -> performT());

            }
            
            public void performT(){
                centerPane.getChildren().clear();
                System.out.println("Time passing...");
                outputResult += ("Time passing...\n");
                int numHours = Integer.parseInt(tfTime.getText());
                
                try{
                    auctionTable.letTimePass(numHours);
                    System.out.println("Auction times updated.");
                    outputResult += ("Auction times updated.\n");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
		} catch(IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                    outputResult += (ex.getMessage()+"\n");
                    
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
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
                
                System.out.println("Writing Auction Table to file...");
                outputResult += ("Writing Auction Table to file...\n");
                
		//add Serialize the Auction Table
                try{
                    FileOutputStream file = new FileOutputStream("auction.obj");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    AuctionTable auctions = new AuctionTable();
                    auctions = (AuctionTable) auctionTable.clone();
                    outStream.writeObject(auctions);
                    System.out.println("Done!\n");
                    System.out.println("Goodbye.");
                
                    outputResult += ("Done!\n"+"Goodbye.\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);

                } catch(FileNotFoundException ex){
                    System.out.println("ERROR when making a new serializable file.");
                    outputResult += ("ERROR when making a new serializable file.\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
                } catch(IOException ex){
                    System.out.println("ERROR when making a new serializable file.");
                    outputResult += ("ERROR when making a new serializable file.\n");
                    v3.getChildren().clear();
                    Label except = new Label(outputResult);
                    except.setTextFill(Color.RED);
                    v3.getChildren().add(except);
                
                    sp.setContent(v3);
                
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(sp);
                    borderPane.setCenter(centerPane);
		}
                
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
        rightPane.getChildren().addAll(menu2,btnP,btnR,btnT,btnQ);
        rightPane.setStyle("-fx-border-color:red");
        borderPane.setRight(rightPane);

        Scene scene = new Scene(borderPane, 1250, 700);
        
        primaryStage.setTitle("Online Auction Data Extractor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Launches the JavaFX application/program
     * @param args 
     *      the empty command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
