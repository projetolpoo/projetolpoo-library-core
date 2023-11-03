package src.views;

import src.controllers.BorrowController;
import src.controllers.UserController;
import src.helpers.Input;

public class UserMenu {
    private UserController userController;
    private BorrowController borrowController;
    private static UserMenu instance;

    public static UserMenu getInstance(){
        if(instance == null){
            synchronized (UserMenu.class){
                if(instance == null){
                    instance = new UserMenu();
                }
            }
        }
        return instance;
    }

    public UserMenu() {
        this.userController = new UserController();
        this.borrowController = new BorrowController();
    }

    private void userOptions(){
        System.out.println("===== User =====");
        System.out.println("1. List my borrowed items");
        System.out.println("2. List all of my debits ");
        System.out.println("3. List library item options ");
        System.out.println("4. Log out");
    }

    public void run(String documentNumber){
        while(true){
            this.userOptions();

            int choice;
            try{
                choice = Input.getInstance().getInt("Type the option: ", 1, 4,true);
            } catch (Exception e) {
                choice = 999;
            }

            switch (choice){
                case 1:
                    this.borrowController.listBorrowedItemsByUser(documentNumber);
                    break;
                case 2:
                    this.borrowController.fineValue(documentNumber);
                    break;
                case 3:
                    this.borrowController.listToUser();
                    break;
                case 4:
                    return;

            }
        }
    }


}
