package src.repositories;

import src.models.Borrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowRepository implements IRepository<Borrow>{
    private static BorrowRepository instance;
    private Map<Integer, Borrow> items = new HashMap<>();
    private int borrowIdCounter = 1;


    public static BorrowRepository getInstance() {
        if (instance == null) {
            synchronized (BorrowRepository.class) {
                if (instance == null) {
                    instance = new BorrowRepository();
                }
            }
        }

        return instance;
    }

    @Override
    public void add(Borrow borrow) {
        borrow.setId(borrowIdCounter++);
        items.put(borrow.getId() ,borrow);
    }

    @Override
    public Borrow getById(int id) {
        return items.get(id);
    }


    @Override
    public List<Borrow> getAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public boolean update(Borrow borrow) {
        if (items.containsKey(borrow.getId())) {
            items.put(borrow.getId(), borrow);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (items.containsKey(id)){
            items.remove(id);
            return true;
        }
        return false;
    }
}
