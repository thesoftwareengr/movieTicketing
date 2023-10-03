import java.util.ArrayList;
import java.util.Scanner;

public class SeatLayout {
	final int ROWS = 5, COLS = 8;

	boolean seats[][];
	int availableSeats;
	
	public SeatLayout() {
		this.availableSeats = 40;
		seats = new boolean[ROWS][COLS];
	}

	public void display(){
		
		System.out.println("        ********** SCREEN **********");
		for (int i = 0; i < ROWS; i++) {	
			for (int j = 0; j < COLS; j++) {
				System.out.print("["+String.valueOf((char)(i + 'A'))+(j+1)+(seats[i][j]==true?"*":" ")+"]");
			}
			System.out.println();
		}
	}
	
	public void reserve(String[] reservedSeats){
		
		
		
	}
	
	public void inputValidation(ArrayList<String>[] errors){
		
	}
	
	public void inputCorrection(ArrayList<String>[] errors, int index){
		String selection=null;
		Scanner scan = new Scanner(System.in);
		
		for(String error: errors[index]) {
			System.out.println(error);
			while(selection==null) {
				System.out.println("Would you like to corrrect or cancel this seat?");
				System.out.print("Correct or cancel: ");
				selection = scan.nextLine();
				if(selection.equalsIgnoreCase("correct")) {
					
					errors[index].remove(error);
					break;
				}else if(selection.equalsIgnoreCase("cancel")) {
					System.out.println("---cancel transaction error message");
					errors[index].remove(error);
					break;
				}else {
					selection=null;
				}
			}
		}
		scan.close();
	}
	
	public void cancel(){
		
	}
	
	
}