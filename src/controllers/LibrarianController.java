package src.controllers;

import java.time.LocalDate;

import src.helpers.Authentication;
import src.helpers.Input;
import src.models.Librarian;
import src.models.Library;
import src.models.User;
import src.repositories.LibraryRepository;
import src.repositories.UserRepository;

public class LibrarianController {

    private UserRepository userRepository;
    private LibraryRepository libraryRepository;


    public LibrarianController()
    {
        this.userRepository = UserRepository.getInstance();
        this.libraryRepository = LibraryRepository.getInstance();
    }

    public void create(){
        try {
            Input input = Input.getInstance();

            String name = input.getString("Type the name (between 3 and 255 characters): ", 3, 255, true);
            String password = input.getString("Type the password (between 8 and 255 characters): ", 8, 255, true);
            LocalDate birthDate = input.getDate(
                "Type the birth date (between 18 and 90 years)", 
                LocalDate.now().minusYears(18), 
                LocalDate.now().minusYears(90), 
                "dd/MM/yyyy",
                true
            );
            String documentNumber = input.getString("Type your document number (between 4 and 255 characters): ", 4, 255, true);

            if (this.userRepository.exists(documentNumber)) {
                throw new Exception("sorry, librarian already exists");
            }

            int id = input.getInt("Type the library's id: ", 1, 9999, true);

            Library library = this.libraryRepository.getById(id);

            if (library == null) {
                throw new Exception("Sorry, invalid library :(\n");
            }

            Librarian librarian = new Librarian(name, Authentication.sha256(password), birthDate, documentNumber, library);
            this.userRepository.add(librarian);

            library.addLibrarian(librarian);
            this.libraryRepository.update(library);

            System.out.println("Librarian sucefully created!!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(){
        try {
            Input input = Input.getInstance();
            Authentication auth = Authentication.getInstance();

            int librarianId = input.getInt("Type the librarian's id: ", 1, 9999, true);

            User librarian = this.userRepository.getById(librarianId);

            if (librarian == null || !(librarian instanceof Librarian) || librarian.getId() == auth.getUser().getId()) {
                throw new Exception("Sorry, invalid librarian :(\n");
            }

            String name = input.getString("Type the name (between 3 and 255 characters): ", 3, 255, false);
            String password = input.getString("Type the password (between 8 and 255 characters): ", 8, 255, false);
            LocalDate birthDate = input.getDate(
                "Type the birth date (between 18 and 90 years)", 
                LocalDate.now().minusYears(18), 
                LocalDate.now().minusYears(90), 
                "dd/MM/yyyy",
                false
            );
            String documentNumber = input.getString("Type your document number (between 4 and 255 characters): ", 4, 255, false);

            if (documentNumber != null) {
                if (this.userRepository.exists(documentNumber)) {
                    throw new Exception("sorry, librarian already exists");
                }
            }

            Integer libraryId = input.getInt("Type the library's id: ", 1, 9999, false);

            Library library = (libraryId == null) ? null : this.libraryRepository.getById(libraryId);

            Librarian updatedLibrarian = (Librarian) librarian;

            if (library != null) {
                Library oldLibrary = updatedLibrarian.getLibrary();
    
                oldLibrary.removeLibrarian(updatedLibrarian);
                this.libraryRepository.update(library);
            }

            updatedLibrarian.setName((name != null) ? name : updatedLibrarian.getName());
            updatedLibrarian.setPassword((password != null) ? Authentication.sha256(password) : updatedLibrarian.getPassword());
            updatedLibrarian.setBirthDate((birthDate != null) ? birthDate : updatedLibrarian.getBirthDate());
            updatedLibrarian.setDocumentNumber((documentNumber != null) ? documentNumber : updatedLibrarian.getDocumentNumber());

            if (library != null) {
                updatedLibrarian.setLibrary(library);
                library.addLibrarian(updatedLibrarian);
                this.libraryRepository.update(library);
            }

            this.userRepository.update(librarian);
            System.out.println("Librarian sucefully created!!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void list() {
        try {
            for (User user: userRepository.getAll()) {
                if (user instanceof Librarian) {
                    System.out.println(user.getId() + ". " + user.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        try {
            Input input = Input.getInstance();
            Authentication auth = Authentication.getInstance();

            int id = input.getInt("Type the librarian's id: ", 1, 9999, true);

            if (id == auth.getUser().getId()) {
                System.out.println("Sorry, Librarian not found :(\n");
                return;
            }

            boolean result = this.userRepository.delete(id);

            if (result) {
                System.out.println("Librarian sucefully created!!\n");
            } else {
                System.out.println("Sorry, Librarian not found :(\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
