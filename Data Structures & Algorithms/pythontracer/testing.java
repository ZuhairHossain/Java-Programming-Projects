import java.util.Scanner;
import java.io.File;

public class testing {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter filename: ");
		String input = in.nextLine();
		System.out.println(input.substring(0,input.length()-3));
		File myFile = new File(input);
		String data = "";
		
		try{
			Scanner fileIn = new Scanner(myFile);
			while(fileIn.hasNextLine()){
				data = fileIn.nextLine();
				if(data.equals(""))
					continue;
				
				if(data.trim().charAt(0) == '#')
					continue;
			
				System.out.println(data);
				/*
				if(data.contains("for") ||data.contains("while")){
					if(data.contains("log_N")){
						System.out.println(data);
						System.out.println("O(log(N))");
					}
						
					else if(data.contains("N")){
						System.out.println(data);
						System.out.println("O(N)");
					}
				} */
			}
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		
	}
}
