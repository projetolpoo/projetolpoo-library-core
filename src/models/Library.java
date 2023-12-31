package src.models;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int id;
    private String name;
    private List<Librarian> librarians = new ArrayList<>();
    private List<LibraryItem> libraryItems = new ArrayList<>();


    public Library(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
    }

    public void removeLibrarian(Librarian librarian) {
        librarians.remove(librarian);
    }
    public List<LibraryItem> getItems() {
    	return libraryItems;
    }
    
    public void addItem(LibraryItem item) {
    	libraryItems.add(item);
    }
    
    public void removeItem(LibraryItem item) {
    	libraryItems.remove(item);
    }

    @Override
    public String toString() {
        return "Library [id=" + id + ", name=" + name + ", librarians=" + librarians.toString() + "]";
    }
}
