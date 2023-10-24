package src.models;
import java.time.LocalDate;

import src.enums.GenderEnum;

public class Book extends LibraryItem{
    private String isbn;
	private LocalDate publication;
	
	public Book(String title, String author, int inventory, Library library,
			double fineValue, GenderEnum gender,
			String isbn, LocalDate publication) {
		super(title, author, inventory, library, fineValue, gender);
		this.isbn = isbn;
		this.publication = publication;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getPublication() {
		return publication;
	}

	public void setPublication(LocalDate publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", publication=" + publication + ", title=" + title + ", author=" + author
				+ ", inventory=" + inventory + ", library=" + library + ", fineValue=" + fineValue + ", gender="
				+ gender + ", id=" + id + "]";
	}
	
}
