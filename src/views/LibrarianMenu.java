package src.views;

import src.controllers.LibrarianController;
import src.controllers.LibraryController;
import src.helpers.Input;

public class LibrarianMenu {
    private LibraryController libraryController;
    private LibrarianController librarianController;
    private static LibrarianMenu instance;

    public static LibrarianMenu getInstance() {
        if (instance == null) {
            synchronized (LibrarianMenu.class) {
                if (instance == null) {
                    instance = new LibrarianMenu();
                }
            }
        }

        return instance;
    }

    public LibrarianMenu(
    ) {
        this.libraryController = new LibraryController();
        this.librarianController = new LibrarianController();
    }

    private void options() {
        System.out.println("===== Menu =====");
        System.out.println("1. Library");
        System.out.println("2. Librarian");
        System.out.println("3. Log out");
    }

    private void optionsLibrary() {
        System.out.println("===== Menu Library =====");
        System.out.println("1. Create Library");
        System.out.println("2. Update Library");
        System.out.println("3. List Library");
        System.out.println("4. Delete Library");
        System.out.println("5. Return");
    }

    private void library()
    {
        while (true) {
            this.optionsLibrary();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 5);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.libraryController.create();
                    break;
                case 2:
                    this.libraryController.update();
                    break;
                case 3:
                    this.libraryController.list();
                    break;
                case 4:
                    this.libraryController.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void optionsLibrarian() {
        System.out.println("===== Menu Librarian =====");
        System.out.println("1. Create Librarian");
        System.out.println("2. Update Librarian");
        System.out.println("3. List Librarian");
        System.out.println("4. Delete Librarian");
        System.out.println("5. Return");
    }

    private void librarian()
    {
        while (true) {
            this.optionsLibrarian();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 5);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.librarianController.create();
                    break;
                case 2:
                    this.librarianController.update();
                    break;
                case 3:
                    this.librarianController.list();
                    break;
                case 4:
                    this.librarianController.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void run() {        
        while (true) {
            this.options();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 3);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.library();
                    break;
                case 2:
                    this.librarian();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
