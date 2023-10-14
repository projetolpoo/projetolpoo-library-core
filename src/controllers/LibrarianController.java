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

            String name = input.getString("Type the name (between 3 and 255 characters): ", 3, 255);
            String password = input.getString("Type the password (between 8 and 255 characters): ", 8, 255);
            LocalDate birthDate = input.getDate(
                "Type the birth date (between 18 and 90 years)", 
                LocalDate.now().minusYears(18), 
                LocalDate.now().minusYears(90), 
                "dd/mm/yyyy"
            );
            String documentNumber = input.getString("Type your document number (11 characters): ", 11, 11);

            if (this.userRepository.exists(documentNumber)) {
                throw new Exception("sorry, librarian already exists");
            }

            int id = input.getInt("Type the library's id: ", 1, 9999);

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

            int librarianId = input.getInt("Type the librarian's id: ", 1, 9999);

            User librarian = this.userRepository.getById(librarianId);

            if (librarian == null || librarian instanceof User) {
                throw new Exception("Sorry, invalid library :(\n");
            }

            String name = input.getString("Type the name (between 3 and 255 characters): ", 3, 255);
            String password = input.getString("Type the password (between 8 and 255 characters): ", 8, 255);
            LocalDate birthDate = input.getDate(
                "Type the birth date (between 18 and 90 years)", 
                LocalDate.now().minusYears(18), 
                LocalDate.now().minusYears(90), 
                "dd/mm/yyyy"
            );
            String documentNumber = input.getString("Type your document number (11 characters): ", 11, 11);

            if (this.userRepository.exists(documentNumber)) {
                throw new Exception("sorry, librarian already exists");
            }

            int libraryId = input.getInt("Type the library's id: ", 1, 9999);

            Library library = this.libraryRepository.getById(libraryId);

            if (library == null) {
                throw new Exception("Sorry, invalid library :(\n");
            }

            Librarian updatedLibrarian = (Librarian) librarian;

            Library oldLibrary = updatedLibrarian.getLibrary();

            oldLibrary.removeLibrarian(updatedLibrarian);
            this.libraryRepository.update(library);


            updatedLibrarian.setName(name);
            updatedLibrarian.setPassword(Authentication.sha256(password));
            updatedLibrarian.setBirthDate(birthDate);
            updatedLibrarian.setDocumentNumber(documentNumber);
            updatedLibrarian.setLibrary(library);

            library.addLibrarian(updatedLibrarian);
            this.libraryRepository.update(library);

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

            int id = input.getInt("Type the librarian's id: ", 1, 9999);

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
