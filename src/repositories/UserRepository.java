package src.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.models.User;

public class UserRepository implements IRepository<User> {

    private static UserRepository instance;
    private Map<Integer, User> users = new HashMap<>();
    private int userIdCounter = 1;

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }

        return instance;
    }

    public void add(User user) {
        user.setId(userIdCounter++);
        users.put(user.getId(), user);
    }

    public User getById(int userId) {
        return users.get(userId);
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public boolean update(User updatedUser) {
        if (users.containsKey(updatedUser.getId())) {
            users.put(updatedUser.getId(), updatedUser);
            return true;
        }
        return false; // User not found
    }

    public boolean delete(int userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return true;
        }
        return false; // User not found
    }

    public boolean exists(String documentNumber) {
        return this
            .getAll()
            .stream()
            .anyMatch((User user) -> documentNumber.equals(user.getDocumentNumber()));
    }

    public User getByDocumentNumber(String documentNumber)
    {
        return this
            .getAll()
            .stream()
            .filter((User user) -> documentNumber.equals(user.getDocumentNumber()))
            .findFirst()
            .orElse(null);
    }
}

