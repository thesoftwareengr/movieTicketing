import java.util.ArrayList;

public class Screening {
	private Movie movieShowing;
	private int cinemaNum;
	private SeatLayout seatLayout;
	private ArrayList<Ticket> soldTickets;
	
	public Screening(Movie movieShowing, int cinemaNum) {
		this.movieShowing = movieShowing;
		this.cinemaNum = cinemaNum;
		this.seatLayout = new SeatLayout();
		this.soldTickets = new ArrayList<Ticket>();
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
}
