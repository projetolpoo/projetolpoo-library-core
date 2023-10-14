package src.views;

import src.controllers.UserController;
import src.helpers.Authentication;
import src.helpers.Input;
import src.models.Librarian;

public class Main {
    private UserController userController;

    public Main(
    ) {
        this.userController = new UserController();
    }

    private void options() {
        System.out.println("===== Welcome =====");
        System.out.println("1. Login");
        System.out.println("2. Sign In");
        System.out.println("3. Exit");
    }

    private void login() {
        boolean result = this.userController.login();
        if (result) {
            Authentication auth = Authentication.getInstance();
            
            if(auth.getUser() instanceof Librarian) {
                LibrarianMenu.getInstance().run();
            } else {
                //implement user menu
            }
        }
    }

    private void defaults() {
        this.userController.createGodUser();
    }

    public void run() {     
        this.defaults();   
        while (true) {
            this.options();
    
            int choice;
            try {
                choice = Input.getInstance().getInt("Type the option: ", 1, 3, true);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice) {
                case 1:
                    this.login();
                    break;
                case 2:
                    this.userController.signIn();
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    Input.getInstance().closeScanner();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
