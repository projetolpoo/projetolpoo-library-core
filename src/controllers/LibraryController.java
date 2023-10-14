package src.controllers;

import src.helpers.Input;
import src.models.Library;
import src.repositories.LibraryRepository;

public class LibraryController {
    private LibraryRepository libraryRepository;

    public LibraryController()
    {
        this.libraryRepository = LibraryRepository.getInstance();
    }

    public void create(){
        try {
            Input input = Input.getInstance();
            String name = input.getString("Type the name: ", 3, 255, true);
            
            Library library = new Library(name);
            this.libraryRepository.add(library);

            System.out.println("Library sucefully created!!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(){
        try {
            Input input = Input.getInstance();

            int id = input.getInt("Type the library's id: ", 1, 9999, true);
            String name = input.getString("Type the name: ", 3, 255, false);

            Library library = this.libraryRepository.getById(id);

            if (library == null) {
                throw new Exception("Sorry, invalid library :(\n");
            }

            library.setName((name != null) ? name : library.getName());
            this.libraryRepository.update(library);

            System.out.println("Library sucefully updated!!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void list() {
        try {
            for (Library library: libraryRepository.getAll()) {
                System.out.println(library.getId() + ". " + library.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        try {
            Input input = Input.getInstance();

            int id = input.getInt("Type the library's id: ", 1, 9999, true);

            boolean result = this.libraryRepository.delete(id);

            if (result) {
                System.out.println("Library sucefully created!!\n");
            } else {
                System.out.println("Sorry, library not found :(\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
