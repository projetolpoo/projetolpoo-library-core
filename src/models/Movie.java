package src.models;

import java.time.LocalDate;
import src.enums.GenderEnum;

public class Movie extends LibraryItem{
    private LocalDate publication;
	private int duration;
	
	public Movie(String title, String author, int inventory, Library library, double fineValue, GenderEnum gender,
			LocalDate publication, int duration) {
		super(title, author, inventory, library, fineValue, gender);
		this.publication = publication;
		this.duration = duration;
	}

	public LocalDate getPublication() {
		return publication;
	}
	public void setPublication(LocalDate publication) {
		this.publication = publication;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Movie [publication=" + publication + ", duration=" + duration + ", title=" + title + ", author="
				+ author + ", inventory=" + inventory + ", library=" + library + ", fineValue=" + fineValue
				+ ", gender=" + gender + ", id=" + id + "]";
	}
	
}
