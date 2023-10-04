import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MovieReservationSystem {
	public ArrayList<Movie> movies;
	public HashMap<String, Screening> screenings;
	
	
	public static void main(String[] args) {		
		MovieReservationSystem mrs = new MovieReservationSystem();
		mrs.readCSV();
		
		SeatLayout seatLayout = new SeatLayout();
		seatLayout.display();
		System.out.println("\nLegend: [Xn ] = available seat, [Xn*] = reserved seat");

		int inputValue = 0;
		int seats;

		String str;
		Scanner scan = new Scanner(System.in);
		//Ticket ticket = new Ticket();		
		// = new String[40];
		do {
			try {
				System.out.println("Please Input \"1\" or \"2\" to Reserve or Cancel a Seat in the Cinema"
						+ "\n[1] - Reserve"
						+ "\n[2] - Cancel Reservation"
						+ "\n[3] - Exit");
				inputValue = scan.nextInt();
			}catch(Exception e){

			}
		}while(inputValue < 1 || inputValue > 3) ;

		switch(inputValue) {
		case 1:
			System.out.println("Please input seats to be reserved for this transaction:");
			scan.nextLine();
			str = scan.nextLine();
			String[] inputedStrings = str.split(",");
			ArrayList<String>[] errors = new ArrayList[2];
			ArrayList<String> reservations = new ArrayList<String>();
			errors[0] = new ArrayList<String>();
			errors[1] = new ArrayList<String>();
			
			for(String inputs : inputedStrings ) {
				seatLayout.inputValidation(errors, inputs,reservations);
			}
			
			System.out.println(errors[0].size() + " , " + errors[1].size() +" , "+reservations.size());
			break;
			
		}
	}
	
	MovieReservationSystem(){
		movies = new ArrayList<Movie>();
		screenings = new HashMap<String, Screening>();
	}
	
	public void readCSV() {
		Movie movieData;
		Screening screeningData;
		String compositeKey, line;
		String[] values;
        int i, movieCtr;
		
		try (BufferedReader br = new BufferedReader(new FileReader("MovieSchedule.csv"))) {
		    while ((line = br.readLine()) != null) {
		    	i=0;
		    	movieCtr=0;
		        values = line.split(",");
		        
//		        CSV indexes
//		        0 - date
//		        1 - cinema num
//		        2 - start time
//		        3 - isPremiere //dne
//		        4 - title
//		        5 - duration
		        
		        values[0] = values[0].substring(1, values[0].length()-1);
		        values[1] = values[1].substring(1, values[1].length()-1);
		        values[2] = values[2].substring(1, values[2].length()-1);
		        values[3] = values[3].substring(1, values[3].length()-1);
		        values[4] = values[4].substring(1, values[4].length()-1);
		        values[5] = values[5].substring(1, values[5].length()-2);
		        		        
		        movieData= new Movie(values[4], Double.parseDouble(values[5]), LocalDate.parse(values[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")), Boolean.valueOf(values[3]));
		        
		        for(; i< movies.size(); i++) {
		        	if(movies.get(i).getName().equals(values[4])) {
		        		break;
		        	}
		        }
		        if(i==movies.size()) { //empty or new movie
		        	movies.add(movieData);
		        }
		        movies.get(i).addStartingTime(LocalTime.parse(values[2], DateTimeFormatter.ofPattern("HH:mm")));
		        
		        screeningData = new Screening(movies.get(i), Integer.valueOf((values[1])));
		        Set<String> keys = screenings.keySet();
		        for(String key: keys) {
		        	if(screenings.get(key).getCinemaNum()==Integer.valueOf(values[1])) {
		        		movieCtr++;
		        	}
		        }
		        
		        compositeKey=values[1]+(char) ('A'+movieCtr);
		        if(!screenings.containsKey(compositeKey)) {
		        	screenings.put(compositeKey, screeningData);
		        }
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void generateReservationsCSV() {
		
	}

}
