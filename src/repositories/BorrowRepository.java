package src.repositories;

import src.models.Borrow;

import java.util.ArrayList;
import java.util.List;

public class BorrowRepository implements IRepository<Borrow>{

    private ArrayList<Borrow> items = new ArrayList<>();

    @Override
    public void add(Borrow borrow) {
        items.add(borrow);
    }

    @Override
    public Borrow getById(int id) {
        return null;
    }

    @Override
    public List<Borrow> getAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean update(Borrow entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
