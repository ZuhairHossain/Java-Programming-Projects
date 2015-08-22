//Dennis Sosa (109198226)
//JavaFX GUI Implementation

package final_project_gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Test extends Application {
    
    public int[] arrRanks= new int[4];
    public int team1=0;
    public int team2=0;
    public int i=0;
    public int max2=0,max3=0,max4=0;
    public char set=' ';
    public boolean player1Win=true;
    public boolean player2Win=false;
    public boolean player3Win=false;
    public boolean player4Win=false;
    public int totalMax=0;
    public boolean player1NoOtherChoice = false;
    
    @Override
    public void start(Stage primaryStage) {
		CardDeck myDeck = new CardDeck();

		myDeck.shuffleDeck();
		myDeck.display();
		
		System.out.println(myDeck.myDeckSize());
		System.out.println("\n\n\n\n\n\n\n");
		
		
		CardHand player1 = new CardHand(myDeck.getCardDeck());
		System.out.println("PLAYER 1:");
		player1.displayPlayer();
		System.out.println("\n\n\n");
                
		
		CardHand player2 = new CardHand(myDeck.getCardDeck());
		System.out.println("PLAYER 2:");
		player2.displayPlayer();
		System.out.println("\n\n\n");
		
		CardHand player3 = new CardHand(myDeck.getCardDeck());
		System.out.println("PLAYER 3:");
		player3.displayPlayer();
		System.out.println("\n\n\n");
		
		CardHand player4 = new CardHand(myDeck.getCardDeck());
		System.out.println("PLAYER 4:");
		player4.displayPlayer();
		System.out.println("\n\n\n");
		
		System.out.println("Welcome to Contract Bridge!");
		System.out.println("You(Player 1) is paired with Player 2");
		System.out.println("You are playing against Player 3 and Player 4");
		System.out.println("You each have 13 cards in your hands.");
                System.out.println("Game flows CLOCKWISE!");
                System.out.println("Click on the Winner of the previous round to proceed to the next round!");
                
                HBox cardsCenterPane = new HBox();
                // center
		VBox centerPane = new VBox();
                BorderPane borderPane = new BorderPane();
                // top
		HBox topPane = new HBox(-30);
                topPane.setStyle("-fx-background-color: blue");
		
		// left
                VBox leftPane = new VBox(-60);
		//GridPane leftPane = new GridPane();		
		
		// right
                VBox rightPane = new VBox(-60);
		//GridPane rightPane = new GridPane();
                
		// bottom
		HBox bottomPane = new HBox(-30);
                bottomPane.setStyle("-fx-background-color: blue");
                ArrayList<String> fileHandTop = new ArrayList<String>();
                ArrayList<String> fileHandLeft = new ArrayList<String>();
                ArrayList<String> fileHandRight = new ArrayList<String>();
		for(int k=0; k<13; k++){
			String fileName = "/final_project_gui/"+player1.cardImage(k);
			System.out.println(fileName);
			ImageView imageView = new ImageView(new Image(fileName));
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(75);
			bottomPane.getChildren().add(imageView);
                        System.out.println(bottomPane.getChildren().indexOf(imageView));
                        
                        String fileNameTop = "/final_project_gui/green_card.png";
                        fileHandTop.add("/final_project_gui/"+player2.cardImage(k));
			System.out.println(fileNameTop);
			ImageView imageViewTop = new ImageView(new Image(fileNameTop));
			imageViewTop.setPreserveRatio(true);
			imageViewTop.setFitWidth(75);
			topPane.getChildren().add(imageViewTop);
                        
                        String fileNameLeft = "/final_project_gui/green_card.png";
			System.out.println(fileNameLeft);
                        fileHandLeft.add("/final_project_gui/"+player3.cardImage(k));
			ImageView imageViewLeft = new ImageView(new Image(fileNameLeft));
			imageViewLeft.setPreserveRatio(true);
			imageViewLeft.setFitWidth(75);
			//leftPane.add(imageView,i/4,i%4);
                        leftPane.getChildren().add(imageViewLeft);
                        
                        String fileNameRight = "/final_project_gui/green_card.png";
			System.out.println(fileNameRight);
                        fileHandRight.add("/final_project_gui/"+player4.cardImage(k));
			ImageView imageViewRight = new ImageView(new Image(fileNameRight));
			imageViewRight.setPreserveRatio(true);
			imageViewRight.setFitWidth(75);
			//rightPane.add(imageView,i/4,i%4);
                        rightPane.getChildren().add(imageViewRight);
                        
                        imageView.setOnMousePressed(ev -> {
                            
                            i=bottomPane.getChildren().indexOf(imageView);
                            if(player3Win==true || player2Win==true || player4Win==true){
                            for(int p=0;p<player1.handSize();p++){
					if(player1.checkSuit(p)==set){
                                                player1NoOtherChoice = false;
						break;
					}
                                        else{
                                            player1NoOtherChoice = true;
                            }
                            }
                            }
                            if(player1Win==true){
                                cardsCenterPane.getChildren().clear();
                                centerPane.getChildren().remove(1,3);
                                //i=bottomPane.getChildren().indexOf(imageView);
                                cardsCenterPane.getChildren().add(imageView);
                                bottomPane.getChildren().remove(imageView);
                                System.out.println();
                                player1.displayPlayer();
				System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
                                
				System.out.print("You played: ");player1.play(i);
				set=player1.checkSuit(i);
				arrRanks[0]=player1.checkRank(i);
                               
				System.out.print("Player 3 played: ");
				for(int p=0;p<player3.handSize();p++){
					if(player3.checkSuit(p)==set){
						max3=player3.checkRank(p);
						break;
					}
					else
                                            max3=player3.checkRank(0);
				}
				
				for(int j=0;j<player3.handSize();j++){
					if(player3.checkSuit(j)==set){
						if(player3.checkRank(j) >= max3)
                                                    max3=player3.checkRank(j);
					}
				}
				player3.play(player3.ind(max3,set));
                                ImageView leftImage = new ImageView(new Image(fileHandLeft.remove(player3.ind(max3,set))));
                                leftImage.setPreserveRatio(true);
                                leftImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(leftImage);
                                leftPane.getChildren().remove(0);
				arrRanks[2]=max3;
				//System.out.println(player3.ind(max3,set));
		
			
				System.out.print("Player 2 played: ");
				for(int p=0;p<player2.handSize();p++){
					if(player2.checkSuit(p)==set){
						max2=player2.checkRank(p);
						break;
					}
					else
						max2=player2.checkRank(0);
				}
			
				for(int j=0;j<player2.handSize();j++){
					if(player2.checkSuit(j)==set){
						if(player2.checkRank(j) >= max2)
							max2=player2.checkRank(j);
					}
				}
				player2.play(player2.ind(max2,set));
                                ImageView topImage = new ImageView(new Image(fileHandTop.remove(player2.ind(max2,set))));
                                topImage.setPreserveRatio(true);
                                topImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(topImage);
                                topPane.getChildren().remove(0);
				arrRanks[1]=max2;
				//System.out.println(player2.ind(max2,set));
			
			
				System.out.print("Player 4 played: ");
				for(int p=0;p<player4.handSize();p++){
					if(player4.checkSuit(p)==set){
						max4=player4.checkRank(p);
						break;
					}
					else
						max4=player4.checkRank(0);
				}
			
				for(int j=0;j<player4.handSize();j++){
					if(player4.checkSuit(j)==set){
						if(player4.checkRank(j) >= max4)
							max4=player4.checkRank(j);
					}
				}
				player4.play(player4.ind(max4,set));
                                ImageView rightImage = new ImageView(new Image(fileHandRight.remove(player4.ind(max4,set))));
                                rightImage.setPreserveRatio(true);
                                rightImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(rightImage);
                                rightPane.getChildren().remove(0);
				arrRanks[3]=max4;
				//System.out.println(player4.ind(max4,set));
			
				totalMax=arrRanks[0];
				for(int a=0; a<arrRanks.length;a++){
					if(arrRanks[a] > totalMax){
						if (a==0 && player1.checkSuit(player1.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==1 && player2.checkSuit(player2.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==2 && player3.checkSuit(player3.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==3 && player4.checkSuit(player4.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
					}
				}
			
				if(totalMax==arrRanks[0]){
					System.out.print("The Highest Card on the Table is: "); 
					player1.play(player1.ind(arrRanks[0],set));
					team1++;
					player1Win=true;
					player2Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[1]){
					System.out.print("The Highest Card on the Table is: "); 
					player2.play(player2.ind(arrRanks[1],set));
					team1++;
					player2Win=true;
					player1Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[2]){
					System.out.print("The Highest Card on the Table is: "); 
					player3.play(player3.ind(arrRanks[2],set));
					team2++;
					player3Win=true;
					player1Win=false;
					player2Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[3]){
					System.out.print("The Highest Card on the Table is: "); 
					player4.play(player4.ind(arrRanks[3],set));
					team2++;
					player4Win=true;
					player1Win=false;
					player2Win=false;
					player3Win=false;
				}
				System.out.println("Team 1: "+team1);
				System.out.println("Team 2: "+team2);
				System.out.println("\n\n\n");
                                Label updateTeam1 = new Label("Team 1: "+team1);
                                Label updateTeam2 = new Label("Team 2: "+team2);
                                //centerPane.getChildren().clear();
                                centerPane.getChildren().addAll(updateTeam1,updateTeam2);
                                System.out.println("Click on the Winner of the previous round to proceed to the next round!");
				player1.drawPlay(i);
				player3.drawPlay(player3.ind(max3,set));
				player2.drawPlay(player2.ind(max2,set));
				player4.drawPlay(player4.ind(max4,set));
                                if((player1.handSize()==0) & (player2.handSize()==0) & 
				(player3.handSize()==0) & (player4.handSize()==0)){
                        System.out.println("You have all played your cards!");
                        if(team1 > team2){
			System.out.println("The Winner of this game is Team 1 with a total score of "+
							team1+" points.");
                        Label winner = new Label("Team 1 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team2 > team1){
			System.out.println("The Winner of this game is Team 2 with a total score of "+
							team2+" points.");
                        Label winner = new Label("Team 2 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team1==team2){
			System.out.println("There is no Winner in this game: DRAW "
					+ "\nTeam 1: "+ team1 + " points.\nTeam 2: "+team2
					+" points.");
                        Label draw = new Label("DRAW!");
                        centerPane.getChildren().add(draw);
                        }
                            }
                            }
                            
                            else if((player3Win==true && player1.checkSuit(i)==set) || (player3Win==true && player1NoOtherChoice)){
                                //i=bottomPane.getChildren().indexOf(imageView);    
                                cardsCenterPane.getChildren().add(imageView);
                                bottomPane.getChildren().remove(imageView);
				System.out.println();
				player1.displayPlayer();
				//System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
				System.out.print("You played: ");player1.play(i);
                                
				if(player1.checkSuit(i)==set)
					arrRanks[0]=player1.checkRank(i);
				else
					arrRanks[0]=2;

				totalMax=arrRanks[2];
				for(int a=0; a<arrRanks.length;a++){
					if(arrRanks[a] > totalMax){
						if (a==0 && player1.checkSuit(player1.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==1 && player2.checkSuit(player2.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==2 && player3.checkSuit(player3.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==3 && player4.checkSuit(player4.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
					}
					
				}
			
				if(totalMax==arrRanks[0]){
					System.out.print("The Highest Card on the Table is: "); 
					player1.play(player1.ind(arrRanks[0],set));
					team1++;
					player1Win=true;
					player2Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[1]){
					System.out.print("The Highest Card on the Table is: "); 
					player2.play(player2.ind(arrRanks[1],set));
					team1++;
					player2Win=true;
					player1Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[2]){
					System.out.print("The Highest Card on the Table is: "); 
					player3.play(player3.ind(arrRanks[2],set));
					team2++;
					player3Win=true;
					player1Win=false;
					player2Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[3]){
					System.out.print("The Highest Card on the Table is: "); 
					player4.play(player4.ind(arrRanks[3],set));
					team2++;
					player4Win=true;
					player1Win=false;
					player2Win=false;
					player3Win=false;
				}
				System.out.println("Team 1: "+team1);
				System.out.println("Team 2: "+team2);
                                System.out.println("Click on the Winner of the previous round to proceed to the next round");
                                Label updateTeam11 = new Label("Team 1: "+team1);
                                Label updateTeam22 = new Label("Team 2: "+team2);
				System.out.println("\n\n\n");
                                //centerPane.getChildren().clear();
                                centerPane.getChildren().addAll(updateTeam11,updateTeam22);
				player1.drawPlay(i);
				player3.drawPlay(player3.ind(max3,set));
				player2.drawPlay(player2.ind(max2,set));
				player4.drawPlay(player4.ind(max4,set));
                                
                                if((player1.handSize()==0) & (player2.handSize()==0) & 
				(player3.handSize()==0) & (player4.handSize()==0)){
                        System.out.println("You have all played your cards!");
                        if(team1 > team2){
			System.out.println("The Winner of this game is Team 1 with a total score of "+
							team1+" points.");
                        Label winner = new Label("Team 1 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team2 > team1){
			System.out.println("The Winner of this game is Team 2 with a total score of "+
							team2+" points.");
                        Label winner = new Label("Team 2 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team1==team2){
			System.out.println("There is no Winner in this game: DRAW "
					+ "\nTeam 1: "+ team1 + " points.\nTeam 2: "+team2
					+" points.");
                        Label draw = new Label("DRAW!");
                        centerPane.getChildren().add(draw);
                        }
                            }
                            }
                            
                            else if((player2Win==true && player1.checkSuit(i)==set) || (player2Win==true && player1NoOtherChoice)){
                                //i=bottomPane.getChildren().indexOf(imageView);
                                cardsCenterPane.getChildren().add(imageView);
                                bottomPane.getChildren().remove(imageView);
                                System.out.println();
				player1.displayPlayer();
				//System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
				System.out.print("You played: ");player1.play(i);
				if(player1.checkSuit(i)==set)
					arrRanks[0]=player1.checkRank(i);
				else
					arrRanks[0]=2;
				System.out.print("Player 3 played: ");
				for(int p=0;p<player3.handSize();p++){
					if(player3.checkSuit(p)==set){
						max3=player3.checkRank(p);
						break;
					}
					else
						max3=player3.checkRank(0);
				}
				
				for(int j=0;j<player3.handSize();j++){
					if(player3.checkSuit(j)==set){
						if(player3.checkRank(j) >= max3)
							max3=player3.checkRank(j);
					}
				}
				player3.play(player3.ind(max3,set));
                                ImageView leftImage1 = new ImageView(new Image(fileHandLeft.remove(player3.ind(max3,set))));
                                leftImage1.setPreserveRatio(true);
                                leftImage1.setFitWidth(75);
                                cardsCenterPane.getChildren().add(leftImage1);
                                leftPane.getChildren().remove(0);
				arrRanks[2]=max3;
				//System.out.println(player3.ind(max3,set));
		
			
				totalMax=arrRanks[1];
				for(int a=0; a<arrRanks.length;a++){
					if(arrRanks[a] > totalMax){
						if (a==0 && player1.checkSuit(player1.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==1 && player2.checkSuit(player2.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==2 && player3.checkSuit(player3.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==3 && player4.checkSuit(player4.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
					}
				}
			
				if(totalMax==arrRanks[0]){
					System.out.print("The Highest Card on the Table is: "); 
					player1.play(player1.ind(arrRanks[0],set));
					team1++;
					player1Win=true;
					player2Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[1]){
					System.out.print("The Highest Card on the Table is: "); 
					player2.play(player2.ind(arrRanks[1],set));
					team1++;
					player2Win=true;
					player1Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[2]){
					System.out.print("The Highest Card on the Table is: "); 
					player3.play(player3.ind(arrRanks[2],set));
					team2++;
					player3Win=true;
					player1Win=false;
					player2Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[3]){
					System.out.print("The Highest Card on the Table is: "); 
					player4.play(player4.ind(arrRanks[3],set));
					team2++;
					player4Win=true;
					player1Win=false;
					player2Win=false;
					player3Win=false;
				}
				System.out.println("Team 1: "+team1);
				System.out.println("Team 2: "+team2);
                                System.out.println("Click on the Winner of the previous round to proceed to the next round!");
				Label updateTeam111 = new Label("Team 1: "+team1);
                                Label updateTeam222 = new Label("Team 2: "+team2);
				System.out.println("\n\n\n");
                                //centerPane.getChildren().clear();
                                centerPane.getChildren().addAll(updateTeam111,updateTeam222);
				player1.drawPlay(i);
				player3.drawPlay(player3.ind(max3,set));
				player2.drawPlay(player2.ind(max2,set));
				player4.drawPlay(player4.ind(max4,set));
                                if((player1.handSize()==0) & (player2.handSize()==0) & 
				(player3.handSize()==0) & (player4.handSize()==0)){
                        System.out.println("You have all played your cards!");
                        if(team1 > team2){
			System.out.println("The Winner of this game is Team 1 with a total score of "+
							team1+" points.");
                        Label winner = new Label("Team 1 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team2 > team1){
			System.out.println("The Winner of this game is Team 2 with a total score of "+
							team2+" points.");
                        Label winner = new Label("Team 2 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team1==team2){
			System.out.println("There is no Winner in this game: DRAW "
					+ "\nTeam 1: "+ team1 + " points.\nTeam 2: "+team2
					+" points.");
                        Label draw = new Label("DRAW!");
                        centerPane.getChildren().add(draw);
                        }
                            }
                            }
                            
                            else if((player4Win==true && player1.checkSuit(i)==set) || (player4Win==true && player1NoOtherChoice)){
                                //i=bottomPane.getChildren().indexOf(imageView);
                                cardsCenterPane.getChildren().add(imageView);
                                bottomPane.getChildren().remove(imageView);
				System.out.println();
				player1.displayPlayer();
				//System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
                               
				System.out.print("You played: ");player1.play(i);
				if(player1.checkSuit(i)==set)
					arrRanks[0]=player1.checkRank(i);
				else
					arrRanks[0]=2;
              
				System.out.print("Player 3 played: ");
				for(int p=0;p<player3.handSize();p++){
					if(player3.checkSuit(p)==set){
						max3=player3.checkRank(p);
						break;
					}
					else
						max3=player3.checkRank(0);
				}
				
				for(int j=0;j<player3.handSize();j++){
					if(player3.checkSuit(j)==set){
						if(player3.checkRank(j) >= max3)
							max3=player3.checkRank(j);
					}
				}
				player3.play(player3.ind(max3,set));
                                ImageView leftImage2 = new ImageView(new Image(fileHandLeft.remove(player3.ind(max3,set))));
                                leftImage2.setPreserveRatio(true);
                                leftImage2.setFitWidth(75);
                                cardsCenterPane.getChildren().add(leftImage2);
                                leftPane.getChildren().remove(0);
				arrRanks[2]=max3;
				//System.out.println(player3.ind(max3,set));
		
				
				System.out.print("Player 2 played: ");
				for(int p=0;p<player2.handSize();p++){
					if(player2.checkSuit(p)==set){
						max2=player2.checkRank(p);
						break;
					}
					else
						max2=player2.checkRank(0);
				}
			
				for(int j=0;j<player2.handSize();j++){
					if(player2.checkSuit(j)==set){
						if(player2.checkRank(j) >= max2)
							max2=player2.checkRank(j);
					}
				}
				player2.play(player2.ind(max2,set));
                                ImageView topImage1 = new ImageView(new Image(fileHandTop.remove(player2.ind(max2,set))));
                                topImage1.setPreserveRatio(true);
                                topImage1.setFitWidth(75);
                                cardsCenterPane.getChildren().add(topImage1);
                                topPane.getChildren().remove(0);
				arrRanks[1]=max2;
				//System.out.println(player2.ind(max2,set));
				
			
				totalMax=arrRanks[3];
				for(int a=0; a<arrRanks.length;a++){
					if(arrRanks[a] > totalMax){
						if (a==0 && player1.checkSuit(player1.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==1 && player2.checkSuit(player2.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==2 && player3.checkSuit(player3.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
						else if (a==3 && player4.checkSuit(player4.ind(arrRanks[a],set))==set)
							totalMax=arrRanks[a];
					}
					
				}
			
				if(totalMax==arrRanks[0]){
					System.out.print("The Highest Card on the Table is: "); 
					player1.play(player1.ind(arrRanks[0],set));
					team1++;
					player1Win=true;
					player2Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[1]){
					System.out.print("The Highest Card on the Table is: "); 
					player2.play(player2.ind(arrRanks[1],set));
					team1++;
					player2Win=true;
					player1Win=false;
					player3Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[2]){
					System.out.print("The Highest Card on the Table is: "); 
					player3.play(player3.ind(arrRanks[2],set));
					team2++;
					player3Win=true;
					player1Win=false;
					player2Win=false;
					player4Win=false;
				}
			
				else if(totalMax==arrRanks[3]){
					System.out.print("The Highest Card on the Table is: "); 
					player4.play(player4.ind(arrRanks[3],set));
					team2++;
					player4Win=true;
					player1Win=false;
					player2Win=false;
					player3Win=false;
				}
                                
				System.out.println("Team 1: "+team1);
				System.out.println("Team 2: "+team2);
                                System.out.println("Click on the Winner of the previous round to proceed to the next round!");
				Label updateTeam1111 = new Label("Team 1: "+team1);
                                Label updateTeam2222 = new Label("Team 2: "+team2);
				System.out.println("\n\n\n");
                                //centerPane.getChildren().clear();
                                centerPane.getChildren().addAll(updateTeam1111,updateTeam2222);
				player1.drawPlay(i);
				player3.drawPlay(player3.ind(max3,set));
				player2.drawPlay(player2.ind(max2,set));
				player4.drawPlay(player4.ind(max4,set));
                                
                                if((player1.handSize()==0) & (player2.handSize()==0) & 
				(player3.handSize()==0) & (player4.handSize()==0)){
                        System.out.println("You have all played your cards!");
                        if(team1 > team2){
			System.out.println("The Winner of this game is Team 1 with a total score of "+
							team1+" points.");
                        Label winner = new Label("Team 1 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team2 > team1){
			System.out.println("The Winner of this game is Team 2 with a total score of "+
							team2+" points.");
                        Label winner = new Label("Team 2 Wins!");
                        centerPane.getChildren().add(winner);
                        }
                        else if(team1==team2){
			System.out.println("There is no Winner in this game: DRAW "
					+ "\nTeam 1: "+ team1 + " points.\nTeam 2: "+team2
					+" points.");
                        Label draw = new Label("DRAW!");
                        centerPane.getChildren().add(draw);
                        }
                        }
                            }
                            else
                                System.out.println("PlAY SAME SUIT!");
                            
                            });

                        
                        imageViewLeft.setOnMousePressed(e3 -> {
			if(player3Win==true){
                                cardsCenterPane.getChildren().clear();
                                centerPane.getChildren().remove(1,3);
				System.out.print("Player 3 played: ");
				set=player3.checkSuit(0);
				for(int p=0;p<player3.handSize();p++){
					if(player3.checkSuit(p)==set){
						max3=player3.checkRank(p);
						break;
					}
					else
						max3=player3.checkRank(0);
				}
				
				for(int j=0;j<player3.handSize();j++){
					if(player3.checkSuit(j)==set){
						if(player3.checkRank(j) >= max3)
							max3=player3.checkRank(j);
					}
				}
				player3.play(player3.ind(max3,set));
                                ImageView leftImage = new ImageView(new Image(fileHandLeft.remove(player3.ind(max3,set))));
                                leftImage.setPreserveRatio(true);
                                leftImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(leftImage);
                                leftPane.getChildren().remove(0);
				arrRanks[2]=max3;
				//System.out.println(player3.ind(max3,set));
		
			
	
				System.out.print("Player 2 played: ");
				for(int p=0;p<player2.handSize();p++){
					if(player2.checkSuit(p)==set){
						max2=player2.checkRank(p);
						break;
					}
					else
						max2=player2.checkRank(0);
				}
			
				for(int j=0;j<player2.handSize();j++){
					if(player2.checkSuit(j)==set){
						if(player2.checkRank(j) >= max2)
							max2=player2.checkRank(j);
					}
				}
                                
				player2.play(player2.ind(max2,set));
                                ImageView topImage = new ImageView(new Image(fileHandTop.remove(player2.ind(max2,set))));
                                topImage.setPreserveRatio(true);
                                topImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(topImage);
                                topPane.getChildren().remove(0);
				arrRanks[1]=max2;
				//System.out.println(player2.ind(max2,set));
			
			
				System.out.print("Player 4 played: ");
				for(int p=0;p<player4.handSize();p++){
					if(player4.checkSuit(p)==set){
						max4=player4.checkRank(p);
						break;
					}
					else
						max4=player4.checkRank(0);
				}
			
				for(int j=0;j<player4.handSize();j++){
					if(player4.checkSuit(j)==set){
						if(player4.checkRank(j) >= max4)
							max4=player4.checkRank(j);
					}
				}
                                
				player4.play(player4.ind(max4,set));
                                ImageView rightImage = new ImageView(new Image(fileHandRight.remove(player4.ind(max4,set))));
                                rightImage.setPreserveRatio(true);
                                rightImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(rightImage);
                                rightPane.getChildren().remove(0);
				arrRanks[3]=max4;
				//System.out.println(player4.ind(max4,set));
                                System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
			}
                        else{
                            System.out.println("NOT THEIR TURN!");
                        }
                        });
                        
                        imageViewTop.setOnMousePressed(e2 -> {
			if(player2Win==true){
                                cardsCenterPane.getChildren().clear();
                                centerPane.getChildren().remove(1,3);
				System.out.print("Player 2 played: ");
				set=player2.checkSuit(0);
				for(int p=0;p<player2.handSize();p++){
					if(player2.checkSuit(p)==set){
						max2=player2.checkRank(p);
						break;
					}
					else
						max2=player2.checkRank(0);
				}
			
				for(int j=0;j<player2.handSize();j++){
					if(player2.checkSuit(j)==set){
						if(player2.checkRank(j) >= max2)
							max2=player2.checkRank(j);
					}
				}
				player2.play(player2.ind(max2,set));
                           
                                ImageView topImage = new ImageView(new Image(fileHandTop.remove(player2.ind(max2,set))));
                                topImage.setPreserveRatio(true);
                                topImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(topImage);
                                topPane.getChildren().remove(0);
				arrRanks[1]=max2;
				//System.out.println(player2.ind(max2,set));
			
			
				System.out.print("Player 4 played: ");
				for(int p=0;p<player4.handSize();p++){
					if(player4.checkSuit(p)==set){
						max4=player4.checkRank(p);
						break;
					}
					else
						max4=player4.checkRank(0);
				}
			
				for(int j=0;j<player4.handSize();j++){
					if(player4.checkSuit(j)==set){
						if(player4.checkRank(j) >= max4)
							max4=player4.checkRank(j);
					}
				}
				player4.play(player4.ind(max4,set));
                                ImageView rightImage = new ImageView(new Image(fileHandRight.remove(player4.ind(max4,set))));
                                rightImage.setPreserveRatio(true);
                                rightImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(rightImage);
                                rightPane.getChildren().remove(0);
				arrRanks[3]=max4;
				//System.out.println(player4.ind(max4,set));
                                System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
                        }
                        else{
                            System.out.println("NOT THEIR TURN!");
                        }
                        });
                        
                        imageViewRight.setOnMousePressed(e4 -> {
			if(player4Win==true) {
                                cardsCenterPane.getChildren().clear();
                                centerPane.getChildren().remove(1,3);
				System.out.print("Player 4 played: ");
				set=player4.checkSuit(0);
				for(int p=0;p<player4.handSize();p++){
					if(player4.checkSuit(p)==set){
						max4=player4.checkRank(p);
						break;
					}
					else
						max4=player4.checkRank(0);
				}
			
				for(int j=0;j<player4.handSize();j++){
					if(player4.checkSuit(j)==set){
						if(player4.checkRank(j) >= max4)
							max4=player4.checkRank(j);
					}
				}
                                
				player4.play(player4.ind(max4,set));
                                ImageView rightImage = new ImageView(new Image(fileHandRight.remove(player4.ind(max4,set))));
                                rightImage.setPreserveRatio(true);
                                rightImage.setFitWidth(75);
                                cardsCenterPane.getChildren().add(rightImage);
                                rightPane.getChildren().remove(0);
				arrRanks[3]=max4;
				//System.out.println(player4.ind(max4,set));
                                System.out.print("\nWhat card would you like to play?(0-"+(player1.handSize()-1)+"): ");
                        }
                        else{
                            System.out.println("NOT THEIR TURN!");
                        }
                        });

		}
             
                bottomPane.setAlignment(Pos.BOTTOM_CENTER);
		borderPane.setBottom(bottomPane);
               
                topPane.setAlignment(Pos.TOP_CENTER);
		borderPane.setTop(topPane);		
		
                leftPane.setAlignment(Pos.CENTER_LEFT);
		borderPane.setLeft(leftPane);		
		
                rightPane.setAlignment(Pos.CENTER_RIGHT);
		borderPane.setRight(rightPane);		
                
                cardsCenterPane.setAlignment(Pos.CENTER);
                Label scoreTeam1 = new Label("Team 1: "+team1);
                Label scoreTeam2 = new Label("Team 2: "+team2);
                centerPane.setStyle("-fx-border-color: red");
                centerPane.getChildren().addAll(cardsCenterPane, scoreTeam1, scoreTeam2);
                centerPane.setAlignment(Pos.CENTER);
                borderPane.setCenter(centerPane);
                
                Scene scene = new Scene(borderPane, 1000, 1200);
		primaryStage.setTitle("Contract Bridge");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}
