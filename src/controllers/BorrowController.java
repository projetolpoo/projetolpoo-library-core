package src.controllers;

import src.helpers.Input;
import src.models.*;
import src.repositories.BorrowRepository;
import src.repositories.LibraryItemRepository;
import src.repositories.UserRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static src.enums.StatusEnum.AVAILABLE;
import static src.enums.StatusEnum.BORROWED;

public class BorrowController {
    private UserRepository userRepository;
    private BorrowRepository borrowRepository;
    private LibraryItemRepository libraryItemRepository;

    public BorrowController() {
        this.userRepository = UserRepository.getInstance();
        this.borrowRepository = BorrowRepository.getInstance();
        this.libraryItemRepository = LibraryItemRepository.getInstance();
    }

    public void create() {
        try {
            Input input = Input.getInstance();

            System.out.println("===== Items =====");
            for (LibraryItem instance : this.libraryItemRepository.getAll()) {
                System.out.println("ID. " + instance.getId() + "- " + instance.getTitle() + ": " + instance.getInventory() + " unit."); //printa itens disponiveis
            }

            int libraryItemId = input.getInt("Type the Library Item id (between 1 and 999 characters): ", 1, 999, true);
            String documentNumber = input.getString("Type the user document number (11 characters): ", 10, 11, true);
            int quantity = input.getInt("Inform the quantity you want to get (between 1 and 999): ", 1, 999, true);

            if (userRepository != null) {
                User user = this.userRepository.getByDocumentNumber(documentNumber);

                if (user != null) {
                    LibraryItem libraryItem = this.libraryItemRepository.getById(libraryItemId);

                    if (libraryItem != null) {
                        if (libraryItem.getInventory() >= quantity) {
                            Borrow borrow = new Borrow(libraryItem, user, LocalDate.now(), null, quantity, BORROWED, libraryItem.getFineValue());
                            this.borrowRepository.add(borrow);
                            libraryItem.setInventory(libraryItem.getInventory() - quantity);
                            this.libraryItemRepository.update(libraryItem);
                            System.out.println("Great! Borrow item created, you just borrowed an item!\nItem: " + libraryItem.getTitle() + "\n");
                        } else {
                            System.out.println("Invalid quantity! Not enough inventory for this item.");
                        }
                    } else {
                        System.out.println("Item id not found!");
                    }
                } else {
                    System.out.println("User not found!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error! Invalid input or unexpected error occurred.");
        }
    }


    public void update() {
        System.out.println("===== Update Borrow =====");
        try {
            Input input = Input.getInstance();

            for (Borrow borrow : this.borrowRepository.getAll()) {
                if (borrow.getQuantity() <= 0) {
                    break;
                } else {
                    if (borrow.getLibraryItem() instanceof Book) {
                        System.out.print("BOOK: ");
                    } else if (borrow.getLibraryItem() instanceof Magazine) {
                        System.out.print("MAGAZINE: ");
                    } else if (borrow.getLibraryItem() instanceof Movie) {
                        System.out.print("MOVIE: ");
                    }
                    if (borrow.getQuantity() > 0) {
                        System.out.println(borrow.getLibraryItem().getTitle() + ": " + borrow.getQuantity() + " unit. |User: " + borrow.getUser().getName() + "- " + borrow.getUser().getDocumentNumber() + " STATUS: " + borrow.getStatus());
                    }
                }
            }

            String documentNumber = input.getString("Type the user document number you want to update (11 characters): ", 10, 20, true);

            boolean userFound = false;

            for (User user : this.userRepository.getAll()) {
                if (user.getDocumentNumber().equals(documentNumber)) {
                    userFound = true;
                    break;
                }
            }

            if (!userFound) {
                System.out.println("User not found.");
            } else {
                while (true) {
                    System.out.println("1. Update item quantity");
                    System.out.println("2. Update item status");
                    System.out.println("3. Return");

                    int option = input.getInt("Type the option: ", 1, 3, true);

                    switch (option) {
                        case 1:
                            int quantity = input.getInt("Type how many items you want at all: ", 1, 999, true);
                            for (Borrow borrow : this.borrowRepository.getAll()) {
                                if (borrow.getUser().getDocumentNumber().equals(documentNumber)) {
                                    LibraryItem libraryItem = borrow.getLibraryItem();
                                    libraryItem.setInventory(libraryItem.getInventory() + borrow.getQuantity());
                                    for (LibraryItem item : LibraryItemRepository.getInstance().getAll()) {
                                        if (item.getId() == libraryItem.getId()) {
                                            item.setInventory(item.getInventory() - quantity);
                                        }
                                    }
                                    borrow.setQuantity(quantity);
                                    return;
                                }
                            }
                            break;
                        case 2:
                            for (Borrow borrow : this.borrowRepository.getAll()) {
                                if (borrow.getUser().getDocumentNumber().equals(documentNumber)) {
                                    System.out.println(borrow.getLibraryItem().getTitle() + " STATUS: " + borrow.getStatus());
                                    while (true) {
                                        System.out.println("1. Change status to AVAILABLE");
                                        System.out.println("2. Return");
                                        int statusOption = input.getInt("Choose an option: ", 1, 2, true);

                                        switch (statusOption) {
                                            case 1:
                                                borrow.setStatus(AVAILABLE);
                                                borrow.setEndDate(LocalDate.now());
                                                System.out.println("Item just got returned at " + LocalDate.now());
                                                LibraryItem libraryItem = borrow.getLibraryItem();
                                                libraryItem.setInventory(libraryItem.getInventory() + borrow.getQuantity());
                                                for (Borrow instance : this.borrowRepository.getAll())
                                                    instance.setQuantity(0);
                                                return;
                                            case 2:
                                                return;
                                            default:
                                                System.out.println("Invalid status option");
                                        }
                                    }
                                }
                            }
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Invalid option");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Update error!");
        }
    }

    public void list() {
        System.out.println("===== All Borrowed List Items =====");
        try {
            if (this.borrowRepository.getAll().isEmpty()) {
                System.out.println("No registered item ");
            } else {
                for (Borrow borrow : this.borrowRepository.getAll()) {
                    if (borrow.getQuantity() <= 0) {
                        continue;
                    } else if (borrow.getLibraryItem() instanceof Book) {
                        System.out.print("BOOK: ");
                    } else if (borrow.getLibraryItem() instanceof Magazine) {
                        System.out.print("MAGAZINE: ");
                    } else if (borrow.getLibraryItem() instanceof Movie) {
                        System.out.print("MOVIE: ");
                    }
                    if (borrow.getQuantity() > 0) {
                        System.out.println(borrow.getLibraryItem().getTitle() + ": " + borrow.getQuantity() + " unit. |User: " + borrow.getUser().getName() + "- " + borrow.getUser().getDocumentNumber());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("No borrowed items");
        }
    }

    public void delete() {
        System.out.println("===== Items to delete =====");
        if (this.borrowRepository.getAll().isEmpty()) {
            System.out.println("No user has borrowed any items from the library.");
        } else {
            try {
                Input input = Input.getInstance();

                String documentNumber = input.getString("Type the user document (11 characters): ", 11, 20, true);

                boolean userFound = false;

                for (User user : this.userRepository.getAll()) {
                    if (user.getDocumentNumber().equals(documentNumber)) {
                        userFound = true;
                        break;
                    }
                }

                if (!userFound) {
                    System.out.println("User not found.");
                } else {
                    for (Borrow borrow : this.borrowRepository.getAll()) {
                        if (borrow.getUser().getDocumentNumber().equals(documentNumber)) {
                            if (borrow.getQuantity() <= 0) {
                                continue;
                            } else if (borrow.getLibraryItem() instanceof Book) {
                                System.out.print("BOOK: ");
                            } else if (borrow.getLibraryItem() instanceof Magazine) {
                                System.out.print("MAGAZINE: ");
                            } else if (borrow.getLibraryItem() instanceof Movie) {
                                System.out.print("MOVIE: ");
                            }
                            System.out.println(borrow.getLibraryItem().getTitle() + "- ID. " + borrow.getLibraryItem().getId());

                            int id = input.getInt("Type the item ID to delete (between 1 and 999 characters): ", 1, 999, true);

                            try {
                                this.borrowRepository.delete(id);
                                System.out.println("Item just got deleted!");

                                LibraryItem libraryItem = this.libraryItemRepository.getById(id);
                                if (libraryItem != null) {
                                    libraryItem.setInventory(libraryItem.getInventory() + borrow.getQuantity());
                                    borrow.setQuantity(0);
                                } else {
                                    System.out.println("Item doesn't exist");
                                }
                            } catch (Exception e) {
                                System.out.println("Item not found!");
                            }
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Error! Type the parameters correctly");
            }
        }
    }
    public void listBorrowedItemsByUser(String documentNumber) {
        System.out.println("Items borrowed by user with document number " + documentNumber + ": ");

        boolean userHasBorrowedItems = false;

        try {
            for (Borrow borrow : this.borrowRepository.getAll()) {
                if (borrow.getUser().getDocumentNumber().equals(documentNumber)) {
                    if (borrow.getQuantity() > 0) {
                        userHasBorrowedItems = true;
                        System.out.println("Title: " + borrow.getLibraryItem().getTitle() + ", Quantity: " + borrow.getQuantity() + ", Status: " + borrow.getStatus());
                    }
                }
            }

            if (!userHasBorrowedItems) {
                System.out.println("No items borrowed");
            }
        } catch (Exception e) {
            System.out.println("Error while listing borrowed items.");
        }
    }
    public void fineValue(String documentNumber) {
        if (borrowRepository.getAll().isEmpty()) {
            System.out.println("No items available to calculate :(");
            return;
        }

        double totalFineValue = 0.0;

        for (Borrow borrow : this.borrowRepository.getAll()) {
            if (borrow.getUser().getDocumentNumber().equals(documentNumber) && borrow.getEndDate() == null) {
                LocalDate endDate = borrow.getEndDate();
                if (endDate != null) {
                    LocalDate currentDate = LocalDate.now();
                    long differenceInDays = endDate.until(currentDate, ChronoUnit.DAYS);
                    if (differenceInDays > 7) {
                        double fineValue = differenceInDays * borrow.getFineValue();
                        totalFineValue += fineValue;
                    }
                }
            }
        }
        if (totalFineValue > 0) {
            System.out.println("Total of your fines: R$" + totalFineValue);
        } else {
            System.out.println("No fines at all");
        }
    }

    public void listToUser(){
        System.out.println("===== Items Available =====");
        for(LibraryItem i : libraryItemRepository.getAll()){
            if(i.getInventory() == 0){
                System.out.println(i.getTitle()+": empty inventory");
            }else{
                System.out.println(i.getTitle()+": "+i.getInventory()+" units available");
            }
        }
    }
}