import java.time.LocalTime;
import java.util.ArrayList;

public class Screening {
	private Movie movieShowing;
	private int cinemaNum;
	private SeatLayout seatLayout;
	private ArrayList<Ticket> soldTickets;
	private LocalTime startTime;
	
	public Screening(Movie movieShowing, int cinemaNum, LocalTime startTime) {
		this.movieShowing = movieShowing;
		this.cinemaNum = cinemaNum;
		this.startTime = startTime;
		this.seatLayout = new SeatLayout();
		this.soldTickets = new ArrayList<Ticket>();
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Movie getMovieShowing() {
		return movieShowing;
	}

	public void setMovieShowing(Movie movieShowing) {
		this.movieShowing = movieShowing;
	}

	public int getCinemaNum() {
		return cinemaNum;
	}

	public void setCinemaNum(int cinemaNum) {
		this.cinemaNum = cinemaNum;
	}

	public SeatLayout getSeatLayout() {
		return seatLayout;
	}

	public void setSeatLayout(SeatLayout seatLayout) {
		this.seatLayout = seatLayout;
	}

	public ArrayList<Ticket> getSoldTickets() {
		return soldTickets;
	}

	public void setSoldTickets(ArrayList<Ticket> soldTickets) {
		this.soldTickets = soldTickets;
	}
	
	public static LocalTime endTimeCalc(LocalTime startTime, double length) {
        LocalTime endTime = startTime.plusMinutes((long) (length * 60));
        return endTime;
	}
}
