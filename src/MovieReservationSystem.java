import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MovieReservationSystem {
	public ArrayList<Movie> movies;
	public HashMap<String, Screening> screenings;
	
	
	public static void main(String[] args) {		
		MovieReservationSystem mrs = new MovieReservationSystem();
		mrs.readCSV();
		
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
//		https://blog.gitnux.com/code/java-csv-write/
		String outputPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"-reservations.csv";

        try {
            FileWriter fileWriter = new FileWriter(outputPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //No header, just like sir Ryan's sample
            
            //Not sorted in ascending order but my organization in the HashMap
            Set<String> keys = screenings.keySet();
	        for(String key: keys) {
	        	for(Ticket ticket: screenings.get(key).getSoldTickets()) {
	        		bufferedWriter.write(ticket.toString());
	                bufferedWriter.newLine();
	        	}
	        }
            bufferedWriter.close();
            System.out.println("CSV file created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while writing to the CSV file.");
        }
	}

}
