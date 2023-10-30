package src.views;

import src.controllers.*;
import src.helpers.Input;

public class LibrarianMenu {
    private LibraryController libraryController;
    private LibrarianController librarianController;
    private BookController bookController;
    private MagazineController magazineController;
    private MovieController movieController;
    private BorrowController borrowController;
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
        this.bookController = new BookController();
        this.magazineController = new MagazineController();
        this.movieController = new MovieController();
        this.borrowController = new BorrowController();
    }

    private void options() {
        System.out.println("===== Menu =====");
        System.out.println("1. Library");
        System.out.println("2. Librarian");
        System.out.println("3. Library item");
        System.out.println("4. Borrow item");
        System.out.println("5. Log out");
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
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
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
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
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
    
    private void optionsLibraryItem() {
        System.out.println("===== Menu Library items =====");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. Movie");
        System.out.println("4. Return");
    }

    private void optionsBook() {
        System.out.println("===== Menu Book =====");
        System.out.println("1. Create book");
        System.out.println("2. Update book");
        System.out.println("3. List book");
        System.out.println("4. Delete book");
        System.out.println("5. Return");
    }
    private void book()
    {
        while (true) {
            this.optionsBook();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.bookController.create();
                    break;
                case 2:
                    this.bookController.update();
                    break;
                case 3:
                    this.bookController.list();
                    break;
                case 4:
                    this.bookController.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void optionsMagazine() {
        System.out.println("===== Menu Magazine =====");
        System.out.println("1. Create magazine");
        System.out.println("2. Update magazine");
        System.out.println("3. List magazine");
        System.out.println("4. Delete magazine");
        System.out.println("5. Return");
    }
    private void magazine()
    {
        while (true) {
            this.optionsMagazine();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.magazineController.create();
                    break;
                case 2:
                    this.magazineController.update();
                    break;
                case 3:
                    this.magazineController.list();
                    break;
                case 4:
                    this.magazineController.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void optionsMovie() {
        System.out.println("===== Menu Movie =====");
        System.out.println("1. Create movie");
        System.out.println("2. Update movie");
        System.out.println("3. List movie");
        System.out.println("4. Delete movie");
        System.out.println("5. Return");
    }
    private void movie()
    {
        while (true) {
            this.optionsMovie();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.movieController.create();
                    break;
                case 2:
                    this.movieController.update();
                    break;
                case 3:
                    this.movieController.list();
                    break;
                case 4:
                    this.movieController.delete();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void libraryItem()
    {
        while (true) {
            this.optionsLibraryItem();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 4, true);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.book();
                    break;
                case 2:
                    this.magazine();
                    break;
                case 3:
                    this.movie();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void optionsBorrow(){
        System.out.println("===== Borrow Menu =====");
        System.out.println("1. Create borrow");
        System.out.println("2. Uptade borrow"); //falta implementar
        System.out.println("3. List borrow");
        System.out.println("4. Delete borrow");
        System.out.println("5. Return");
    }
    private void borrowItem(){

        while (true){

            this.optionsBorrow();

            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
            }catch (Exception e){
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.borrowController.create();
                    break;
                case 2:
                    this.borrowController.uptade();
                    break;
                case 3:
                    this.borrowController.list();
                    break;
                case 4:
                    this.borrowController.delete();
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
                choice = Input.getInstance().getInt("Type the option: ", 1, 5, true);
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
                    this.libraryItem();
                    break;
                case 4:
                    this.borrowItem();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
