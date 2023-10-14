package src.models;

import java.time.LocalDate;

public class Librarian extends User{
    private Library library;

    private boolean isGod;

    public Librarian(String name, String password, LocalDate birthDate, String documentNumber, Library library) {
        super(name, password, birthDate, documentNumber);
        this.library = library;
    }

    public Librarian(String name, String password, LocalDate birthDate, String documentNumber, boolean isGod) {
        super(name, password, birthDate, documentNumber);
        this.isGod = isGod;
    }

    public boolean isGod() {
        return isGod;
    }

    public void setGod(boolean isGod) {
        this.isGod = isGod;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
