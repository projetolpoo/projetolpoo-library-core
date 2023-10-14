package src.controllers;

import java.time.LocalDate;

import src.helpers.Authentication;
import src.helpers.Input;
import src.models.Librarian;
import src.models.User;
import src.repositories.UserRepository;

public class UserController {
    
    private UserRepository userRepository;

    public UserController()
    {
        this.userRepository = UserRepository.getInstance();
    }

    public boolean login()
    {
        try {
            Input input = Input.getInstance();
            Authentication auth = Authentication.getInstance();

            String documentNumber = input.getString("Type your document number: ", 4, 255, true);
            String password = input.getString("Type the password: ", 6, 255, true);
            
            User user = this.userRepository.getByDocumentNumber(documentNumber);

            if (user == null) {
                throw new Exception("sorry, invalid user :(");
            }

            if (!user.getPassword().equals(Authentication.sha256(password))) {
                throw new Exception("sorry, invalid user :(");
            }

            auth.setUser(user);
            System.out.println("User sucefully logged!!\n");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void signIn()
    {
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
            String documentNumber = input.getString("Type your document number (11 characters): ", 11, 11, true);

            if (this.userRepository.exists(documentNumber)) {
                throw new Exception("sorry, user already exists");
            }

            User user = new User(name, Authentication.sha256(password), birthDate, documentNumber);
            this.userRepository.add(user);

            System.out.println("User sucefully created!!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createGodUser()
    {
        Librarian user = new Librarian("admin", Authentication.sha256("@admin"), LocalDate.now().minusYears(18), "admin", true);
        this.userRepository.add(user);
    }
}
