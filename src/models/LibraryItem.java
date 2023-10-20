package src.models;

import src.enums.GenderEnum;

public class LibraryItem {
    protected String title;
	protected String author;
	protected int inventory;
	protected Library library;
	protected double fineValue;
	protected GenderEnum gender;
	protected int id;
	
	public LibraryItem(String title, String author, int inventory,
			Library library, double fineValue,
			GenderEnum gender) {
		this.title = title;
		this.author = author;
		this.inventory = inventory;
		this.library = library;
		this.fineValue = fineValue;
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	public double getFineValue() {
		return fineValue;
	}
	public void setFineValue(double fineValue) {
		this.fineValue = fineValue;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	public int getIdItem() {
		return id;
	}
	public void setIdItem(int idItem) {
		this.id = idItem;
	}

	@Override
	public String toString() {
		return "LibraryItem [title=" + title + ", author=" + author + ", inventory=" + inventory + ", library="
				+ library + ", fineValue=" + fineValue + ", gender=" + gender + ", id=" + id + "]";
	}
	
}
