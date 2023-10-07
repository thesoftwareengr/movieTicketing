import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Movie {
	private String name;
	private double length;
	private LocalDate showingDate;
	private ArrayList<LocalTime> startTime;
	private Boolean isPremier;
	
	public Movie(String name, double length, LocalDate showingDate, Boolean isPremier) {
		this.name = name;
		this.length = length;
		this.showingDate = showingDate;
		this.startTime = new ArrayList<LocalTime>();
		this.isPremier = isPremier;
	}

	public String getName() {
		return name;
	}
	
	public double getLength() {
		return length;
	}
	
	public LocalDate getShowingDate() {
		return showingDate;
	}
	
	public ArrayList<LocalTime> getStartTime() {
		return startTime;
	}
	
	public void setStartTime(ArrayList<LocalTime> startTime) {
		this.startTime = startTime;
	}
	
	public void addStartingTime(LocalTime time) {
		this.startTime.add(time);
	}
	
	public Boolean getIsPremier() {
		return isPremier;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public void setShowingDate(LocalDate showingDate) {
		this.showingDate = showingDate;
	}
	
	public void setIsPremier(Boolean isPremier) {
		this.isPremier = isPremier;
	}

}
