import java.time.LocalDate;
import java.time.LocalTime;

public class Movie {
	private String name;
	private double length;
	private LocalDate showingDate;
	private LocalTime[] startTime;
	private Boolean isPremier;
	
public Movie(String name, double length, LocalDate showingDate, LocalTime[] startTime, Boolean isPremier) {

		this.name = name;
		this.length = length;
		this.showingDate = showingDate;
		this.startTime = startTime;
		this.isPremier = isPremier;
	}

	// Getters
	public String getName() {
		return name;
	}
	
	public double getLength() {
		return length;
	}
	
	public LocalDate getShowingDate() {
		return showingDate;
	}
	
	public LocalTime[] getStartTime() {
		return startTime;
	}
	
	public Boolean getIsPremier() {
		return isPremier;
	}
	
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public void setShowingDate(LocalDate showingDate) {
		this.showingDate = showingDate;
	}
	
	public void setStartTime(LocalTime[] startTime) {
		this.startTime = startTime;
	}
	
	public void setIsPremier(Boolean isPremier) {
		this.isPremier = isPremier;
	}


}