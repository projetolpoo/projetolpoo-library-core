package src.models;

import java.time.LocalDate;

import src.enums.GenderEnum;

public class Magazine extends LibraryItem{
    private String issueNumber;
	 private LocalDate publication;
	 
	public Magazine(String title, String author, int inventory, Library library, double fineValue, GenderEnum gender,
			String issueNumber, LocalDate publication) {
		super(title, author, inventory, library, fineValue, gender);
		this.issueNumber = issueNumber;
		this.publication = publication;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public LocalDate getPublication() {
		return publication;
	}

	public void setPublication(LocalDate publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "Magazine [issueNumber=" + issueNumber + ", publication=" + publication + ", title=" + title
				+ ", author=" + author + ", inventory=" + inventory + ", library=" + library + ", fineValue="
				+ fineValue + ", gender=" + gender + ", id=" + id + "]";
	}
	
}
