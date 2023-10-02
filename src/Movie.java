import java.sql.Time;
import java.time.LocalDate;

public class Movie {
	private String name;
	private double length;
	private LocalDate showingDate;
	private Time[] startTime;
	
	
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
	
	public Time[] getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Time[] startTime) {
		this.startTime = startTime;
	}
	
	
	
}
