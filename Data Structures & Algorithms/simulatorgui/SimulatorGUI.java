package simulatorgui;
/**
 * Dennis Sosa
 * Assignment: #4 (Router Network Simulator)
 * CSE 214
 * Stony Brook University
 * @author DennisSosa
 * 
 * SimulatorGUI Class
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

import java.util.ArrayList;

public class SimulatorGUI extends Application {
    
    public static final int MAX_PACKETS = 3;
    
    /**
    * Runs the simulator, calculates the
    * average time each packet spends within the network
    * @return
    * 	the average time each packet spends within the network
    */
    public double simulate(int totalServiceTime, int totalPacketsArrived){
	return (double) totalServiceTime/totalPacketsArrived;
    }
     
    /**
    * Helper method that can generate a random number between minVal & maxVal
    * @param minVal
    * 		minVal of range for random number
    * @param maxVal
    * 		maxVal of range for random number
    * @return
    * 		the randomly generated number
    */
    private int randInt(int minVal, int maxVal){
        return (int)(Math.random()*((maxVal-minVal)+1))+minVal;
    }
    
    /**
    * The main method runs a menu driver application which first creates an empty Router object;
    * the program is an implemented Graphical User Interface using JavaFX Instructions;
    * The GUI allows the user to click buttons to execute an operation
    * in accordance to the menu
    * Main method that tests the simulator. All values are received from user input.
    * Prompts the user for inputs to the simulator.
    * Runs the simulator, and outputs the result.
    * @param primaryStage
    *       sets up the stage for the JavaFX application
    * @throws IllegalArgumentException
    *       indicates invalid entries within the user-inputs & 
    *       FullBufferException class within the main program
    */
    @Override
    public void start(Stage primaryStage) {
        ScrollPane sp = new ScrollPane();
        
        sp.setVmax(440);
        sp.setPrefSize(145,200);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,20,10,20));
        
        HBox topPane = new HBox();
        topPane.setStyle("-fx-background-color: green");
        
        Text title = new Text(100,100, "Dennis Sosa's Router Network Simulator\nCSE 214\n");
        title.setFill(Color.WHITE);
        title.setTextAlignment(TextAlignment.CENTER);
        
        Font boldFont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24);
        title.setFont(boldFont);
        
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().add(title);
        
        borderPane.setTop(topPane);
        
        HBox bottomPane = new HBox();
        bottomPane.setStyle("-fx-background-color: green");
        
        Text message = new Text(100,100, "MESSAGE: Please click on the button of your choice to enter"
                + "\nyour selection corresponding to the Router Simulator.\n");
        message.setFill(Color.WHITE);
        message.setFont(boldFont);
        
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.getChildren().add(message);
        
        borderPane.setBottom(bottomPane);
        
        HBox centerPane = new HBox();
        centerPane.setStyle("-fx-border-color:blue");
        
        borderPane.setCenter(centerPane);

        VBox leftPane = new VBox(10);
        VBox rightPane = new VBox(10);
        
        Button btnS = new Button();
        btnS.setText("Start Simulator");
        
        Button enterInput = new Button("Enter Simulator Inputs");
        VBox simulatorMenu = new VBox(10);
        
        HBox hs1 = new HBox(5);
        HBox hs2 = new HBox(5);
        HBox hs3 = new HBox(5);
        HBox hs4 = new HBox(5);
        HBox hs5 = new HBox(5);
        HBox hs6 = new HBox(5);
        HBox hs7 = new HBox(5);
        HBox hs8 = new HBox(5);
        
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();
        TextField tf7 = new TextField();
                
        hs1.getChildren().add(new Label("Starting simulator... "));
        hs1.setAlignment(Pos.CENTER);
                
        hs2.getChildren().addAll(new Label("Enter the number of Intermediate routers:"),tf1);
        hs2.setAlignment(Pos.CENTER);
        
        hs3.getChildren().addAll(new Label("Enter the arrival probability of a packet:"),tf2);
        hs3.setAlignment(Pos.CENTER);
        
        hs4.getChildren().addAll(new Label("Enter the maximum buffer size of a router:"),tf3);
        hs4.setAlignment(Pos.CENTER);
        
        hs5.getChildren().addAll(new Label("Enter the minimum size of a packet:"),tf4);
        hs5.setAlignment(Pos.CENTER);
        
        hs6.getChildren().addAll(new Label("Enter the maximum size of a packet:"),tf5);
        hs6.setAlignment(Pos.CENTER);
        
        hs7.getChildren().addAll(new Label("Enter the bandwidth size:"),tf6);
        hs7.setAlignment(Pos.CENTER);
        
        hs8.getChildren().addAll(new Label("Enter the simulation duration:"),tf7);
        hs8.setAlignment(Pos.CENTER);
                
        simulatorMenu.getChildren().addAll(hs1,hs2,hs3,hs4,hs5,hs6,hs7,hs8,enterInput);
        simulatorMenu.setAlignment(Pos.CENTER);
        
        btnS.setOnAction(new EventHandler<ActionEvent>() {
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
                    centerPane.setAlignment(Pos.CENTER);
                    centerPane.getChildren().add(simulatorMenu);
                    borderPane.setCenter(centerPane);
                    enterInput.setOnAction(e -> simulate());
                    
		} catch(Exception ex){
                    System.out.println(ex.getMessage());
                    centerPane.setAlignment(Pos.CENTER);
                    Label except = new Label(ex.getMessage());
                    except.setTextFill(Color.RED);
                    centerPane.getChildren().add(except);
                    borderPane.setCenter(centerPane);
		}
            }
            
            public void simulate(){
                SimulatorGUI sim = new SimulatorGUI();
		String resultOutput = "";
		int totalServiceTime = 0;
		int totalPacketsArrived = 0;
		int packetsDropped = 0;
                Packet tempPacket = new Packet();
                tempPacket.packetCount=0;
                boolean cont = true;
		
		while(cont){
                    System.out.println("\nStarting simulator...\n");
                    System.out.print("Enter the number of Intermediate routers: ");
                    int numIntRouters = Integer.parseInt(tf1.getText());
		
                    System.out.print("\nEnter the arrival probability of a packet: ");
                    double arrivalProb = Double.parseDouble(tf2.getText());
			
                    try{
                        centerPane.getChildren().clear();
                        arrivalProb = checkArrivalProb(arrivalProb);
                    } catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                        resultOutput += ("\n\n"+ex.getMessage()+"\n\n");
                        break;
                    }
		
                    System.out.print("\nEnter the maxmimum buffer size of a router: ");
                    int maxBufferSize = Integer.parseInt(tf3.getText());
		
                    System.out.print("\nEnter the minimum size of a packet: ");
                    int minPacketSize = Integer.parseInt(tf4.getText());
		
                    System.out.print("\nEnter the maximum size of a packet: ");
                    int maxPacketSize = Integer.parseInt(tf5.getText());
                    
                    if(maxPacketSize < minPacketSize || minPacketSize > maxPacketSize){
                        System.out.print("Invalid input for minPacketSize & maxPacketSize.");
                        break;
                    }
                    
                    System.out.print("\nEnter the bandwidth size: ");
                    int bandwidth = Integer.parseInt(tf6.getText());
		
                    System.out.print("\nEnter the simulation duration: ");
                    int duration = Integer.parseInt(tf7.getText());

		
                    try{
                        centerPane.getChildren().clear();
                        duration = checkDuration(duration);
                    } catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                        resultOutput += ("\n\n"+ex.getMessage()+"\n\n");
                        break;
                    }
			
                    if(arrivalProb==0||duration==0||numIntRouters==0||maxBufferSize==0||bandwidth==0){
                        System.out.println("\nNo action to be performed.");
                        resultOutput += ("\nNo action to be performed.\n\n");
      
                        break;
                    }
			
                    Router dispatcher = new Router();
                    Router destination = new Router();
                    ArrayList<Router> routers = new ArrayList<Router>(numIntRouters+1);
                    Router currentAdd = new Router();
		
                    totalServiceTime = 0;
                    totalPacketsArrived = 0;
                    packetsDropped = 0;
		
                    routers.add(new Router());
		
                    for(int i=1; i<=numIntRouters; i++){
			routers.add(new Router());
                    }
			
                    for(int i=1; i<=duration; i++){
			dispatcher = new Router();
			for(int j=1; j<=MAX_PACKETS; j++){
                            if(Math.random() < arrivalProb){
				Packet p = new Packet();
				p.packetCount++;
				p.setId(p.packetCount);
				p.setPacketSize(sim.randInt(minPacketSize, maxPacketSize));
				p.setTimeArrive(i);
				p.setTimeToDest(p.getPacketSize()/100);
				dispatcher.enqueue(p);
                            }
			}
			currentAdd = (Router) dispatcher.clone();
				
			System.out.println("\nTime: "+i);
			resultOutput+=("\nTime: "+i+"\n");

			if(dispatcher.isEmpty()){
                            System.out.println("No packets arrived.");
                            resultOutput+=("No packets arrived.\n");

                            if(i>1){
                                int packetArrival = 0;
				int bandwidthTracker = 0;
						
				for(int z=1; z<=numIntRouters; z++){
                                    if(routers.get(z).isEmpty())
					continue;
							
                                    Router temp = routers.get(z);
							
                                    if(temp.get(0).getTimeToDest()!=0 && (!currentAdd.contains(temp.get(0))||currentAdd.isEmpty()))
					temp.get(0).setTimeToDest(temp.get(0).getTimeToDest()-1);
							
                                    if(temp.get(0).getTimeToDest()==0){
					if(destination.contains(temp.get(0))&&destination.get(0).equals(temp.get(0))){
                                            if(bandwidthTracker!=bandwidth){
                                                packetArrival = (i-destination.peek().getTimeArrive());
						totalServiceTime += (i-destination.peek().getTimeArrive());
										
						System.out.println("Packet "+destination.peek().getId()+" has successfully reached its destination: +"+packetArrival);
						resultOutput += ("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival+"\n");

						temp.dequeue();
						totalPacketsArrived++;
						routers.set(z,temp);
						bandwidthTracker++;
                                            }
					}
								
					else if(!destination.contains(temp.get(0))){
                                            destination.enqueue(temp.get(0));
									
                                            if(bandwidthTracker!=bandwidth){
                                                packetArrival = (i-destination.peek().getTimeArrive());
						totalServiceTime += (i-destination.peek().getTimeArrive());
										
						System.out.println("Packet "+destination.peek().getId()+" has successfully reached its destination: +"+packetArrival);
						resultOutput += ("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival+"\n");

						temp.dequeue();
						totalPacketsArrived++;
						routers.set(z,temp);
						bandwidthTracker++;
                                            }
					}
                                    } 
				}
                            }
			}
				
			else{
                            Packet[] dispatcherArray = dispatcher.toArray(new Packet[dispatcher.size()]);
				
                            for(int k=0; k<dispatcherArray.length; k++){
                                System.out.println("Packet "+dispatcherArray[k].getId()+" arrives at dispatcher with"
					+" size "+dispatcherArray[k].getPacketSize()+".");
                                resultOutput += ("Packet "+dispatcherArray[k].getId()+" arrives at dispatcher with"
					+" size "+dispatcherArray[k].getPacketSize()+".\n");
                            }
				
			for(int x=0; x<dispatcherArray.length; x++){
                            try{
                                int sendIndex = dispatcher.sendPacketTo(routers, numIntRouters, maxBufferSize);
					
				System.out.println("Packet "+dispatcherArray[x].getId()+" sent to Router "+
						sendIndex+".");
				resultOutput += ("Packet "+dispatcherArray[x].getId()+" sent to Router "+
						sendIndex+".\n");
						
				routers.get(sendIndex).enqueue(dispatcher.dequeue());
						
                            } catch(FullBufferException ex){

				System.out.println(ex.getMessage());
                                System.out.println("\nNetwork is congested. Packet "+dispatcherArray[x].getId()+" is dropped.");
				packetsDropped++;

                            resultOutput += (ex.getMessage()+"\n");
                            resultOutput += ("\nNetwork is congested. Packet "+dispatcherArray[x].getId()+" is dropped.\n");
                            }
			}
					
			if(i>1){
                            int packetArrival = 0;
                            int bandwidthTracker = 0;
						
                            for(int z=1; z<=numIntRouters; z++){
                                if(routers.get(z).isEmpty())
                                    continue;
							
				Router temp = routers.get(z);
							
				if(temp.get(0).getTimeToDest()!=0 && (!currentAdd.contains(temp.get(0))||currentAdd.isEmpty()))
                                    temp.get(0).setTimeToDest(temp.get(0).getTimeToDest()-1);
							
				if(temp.get(0).getTimeToDest()==0){
                                    if(destination.contains(temp.get(0))&&destination.get(0).equals(temp.get(0))){
					if(bandwidthTracker!=bandwidth){
                                            packetArrival = (i-destination.peek().getTimeArrive());
                                            totalServiceTime += (i-destination.peek().getTimeArrive());
										
                                            System.out.println("Packet "+destination.peek().getId()+" has successfully reached its destination: +"+packetArrival);
                                            resultOutput += ("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival+"\n");
										
                                            temp.dequeue();
                                            totalPacketsArrived++;
                                            routers.set(z,temp);
                                            bandwidthTracker++;
					}
                                    }
								
                                    else if(!destination.contains(temp.get(0))){
                                        destination.enqueue(temp.get(0));
									
					if(bandwidthTracker!=bandwidth){
                                            packetArrival = (i-destination.peek().getTimeArrive());
                                            totalServiceTime += (i-destination.peek().getTimeArrive());
										
                                            System.out.println("Packet "+destination.peek().getId()+" has successfully reached its destination: +"+packetArrival);
                                            resultOutput += ("Packet "+destination.dequeue().getId()+" has successfully reached its destination: +"+packetArrival+"\n");

                                            temp.dequeue();
                                            totalPacketsArrived++;
                                            routers.set(z,temp);
                                            bandwidthTracker++;
					}
                                    }
				} 
                            }
			}	
                    }
				
                    for(int y=1; y<=numIntRouters; y++){
			if(routers.get(y).isEmpty()){
                            System.out.println("R"+y+": {}");
                            resultOutput += ("R"+y+": {}\n");
                        }

                        else{
                            System.out.println("R"+y+": {"+routers.get(y).toString()+"}");
                            resultOutput += ("R"+y+": {"+routers.get(y).toString()+"}\n");
                        }
                    }
                }
                break;  
            }
            System.out.println("\nSimulation ending...");
            resultOutput += ("\nSimulation ending...\n");

            System.out.println("Total service time: "+totalServiceTime);
            resultOutput += ("Total service time: "+totalServiceTime+"\n");

            System.out.println("Total packets served: "+totalPacketsArrived);
            resultOutput += ("Total packets served: "+totalPacketsArrived+"\n");

            System.out.printf("Average service time per packet: %.2f\n"
			,sim.simulate(totalServiceTime,totalPacketsArrived));
            resultOutput += String.format("Average service time per packet: %.2f\n"
			,sim.simulate(totalServiceTime,totalPacketsArrived));

            System.out.println("Total packets dropped: "+packetsDropped);
            resultOutput += ("Total packets dropped: "+packetsDropped+"\n");

            System.out.println("Do you want to try another simulation? If so, click on the "
                            + "\'Start Simulator\' button again. Else, click on the \'Quit\' button.");

            resultOutput += ("\n\nDo you want to try another simulation? If so, click on the "
                            + "\'Start Simulator\' button again. Else, click on the \'Quit\' button.\n\n");
            sp.setContent(new Label(resultOutput));
                        
            centerPane.getChildren().clear();
            centerPane.setAlignment(Pos.CENTER);
            centerPane.getChildren().add(sp);

            borderPane.setCenter(sp);
            
            }
        });
        
        HBox menu1 = new HBox();
        
        Label menu = new Label("Selection Menu");
        menu.setStyle("-fx-font-weight:bold");
        menu.setUnderline(true);
        
        menu1.getChildren().add(menu);
        menu1.setAlignment(Pos.CENTER);
   
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.getChildren().addAll(menu1,btnS);
        leftPane.setStyle("-fx-border-color:blue");
        borderPane.setLeft(leftPane);

        Button btnQ = new Button();
        btnQ.setText("Quit");
        
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
        rightPane.getChildren().addAll(menu2,btnQ);
        rightPane.setStyle("-fx-border-color:blue");
        borderPane.setRight(rightPane);

        Scene scene = new Scene(borderPane, 1250, 700);
        
        primaryStage.setTitle("Router Network Simulator");
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
    
    /**
     * Checks arrivalProb
     * @param arrivalProb
     *      user-input value
     * @return
     *      same user-input value
     * @throws IllegalArgumentException 
     *      indicates invalid entry
     */
    public static double checkArrivalProb(double arrivalProb) throws IllegalArgumentException{
        if(arrivalProb < 0.0 || arrivalProb > 1.0)
            throw new IllegalArgumentException("\nInvalid entry for arrivalProb.\n");
	return arrivalProb;
    }
    
    /**
     * Checks duration variable
     * @param duration
     *      user-input value for time simulation
     * @return
     *      same user-input value if valid
     * @throws IllegalArgumentException 
     *      indicates no simulation required
     */
    public static int checkDuration(int duration) throws IllegalArgumentException{
        if(duration < 0)
            throw new IllegalArgumentException("\nNo Simulation.\n");
	return duration;
    }
    
}
