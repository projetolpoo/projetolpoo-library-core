package src.controllers;

import src.helpers.Authentication;
import src.helpers.Input;
import src.models.Borrow;
import src.models.LibraryItem;
import src.models.User;
import src.repositories.BorrowRepository;
import src.repositories.LibraryItemRepository;
import src.repositories.UserRepository;

import java.time.LocalDate;

import static src.enums.StatusEnum.BORROWED;

public class BorrowController {
    private UserRepository userRepository;
    private BorrowRepository borrowRepository;
    private LibraryItemRepository libraryItemRepository;
    private LibraryItem libraryItem;

    public BorrowController() {
        this.userRepository = UserRepository.getInstance();
        this.libraryItemRepository = LibraryItemRepository.getInstance();
    }
    public void create(){
        try {
            Input input = Input.getInstance();

            System.out.println("===== Items =====");
            System.out.println(libraryItemRepository.getAll().toString()); //printa todos os itens armazenados

            int libraryItemId = input.getInt("Type the Library Item id: ", 1, 999, true);
            String documentNumber = input.getString("Type the user document number: ", 1, 255, true);

            //LocalDate startDate = input.getDate("Tell the start date: ", LocalDate., LocalDate.now(), "dd/MM/yyyy", true);
            int quantity = input.getInt("Inform the quantity you want to get: ", 1, 999, true);

            if(userRepository != null) {
                for( User user : userRepository.getAll()) {
                    if (user.getDocumentNumber().equals(documentNumber)) {
                        if (libraryItemRepository.getAll().contains(libraryItemRepository.getById(libraryItemId))) {
                            libraryItem = libraryItemRepository.getById(libraryItemId);
                            Borrow borrow = new Borrow(libraryItem, user, LocalDate.now(), null, quantity, BORROWED, 0.0F);
                            borrowRepository = new BorrowRepository();
                            borrowRepository.add(borrow);
                            System.out.println("Great! You just got borrowed the item!\n" + libraryItemRepository.getById(libraryItemId));

                        } else {
                            System.out.println("Item id not found!");
                        }
                    }
                }
            } else{
                System.out.println("User doesn't exist");
            }

        } catch (Exception e) {
            System.out.println("User does't exist");
        }
    }

    public void uptade() {
        try {
            // implementar metodo
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void list() {
        try {
            for (Borrow borrow : borrowRepository.getAll()) {
                System.out.println(borrow);
            }
        } catch (Exception e) {
            System.out.println("No borrowed items");
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

            boolean result = this.borrowRepository.delete(id);

            if (result) {
                System.out.println("Borrowed item sucefully created!!\n");
            } else {
                System.out.println("Sorry, borrow item not found :(\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
