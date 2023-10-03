import java.time.LocalDate;
import java.time.LocalTime;

public class Movie {
	private String name;
	private double length;
	private LocalDate showingDate;
	private LocalTime[] startTime;
	
	public Movie(String name, double length, LocalDate showingDate, LocalTime[] startTime) {
		this.name = name;
		this.length = length;
		this.showingDate = showingDate;
		this.startTime = startTime;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLength() {
		return length;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public LocalDate getShowingDate() {
		return showingDate;
	}
	
	public void setShowingDate(LocalDate showingDate) {
		this.showingDate = showingDate;
	}
	
	public LocalTime[] getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalTime[] startTime) {
		this.startTime = startTime;
	}
	
}