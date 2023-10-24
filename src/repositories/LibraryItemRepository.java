package src.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.models.LibraryItem;

public class LibraryItemRepository implements IRepository<LibraryItem>{
    private static LibraryItemRepository instance;
    private Map<Integer, LibraryItem> items = new HashMap<>();
    private int itemIdCounter = 1;
    
    public static LibraryItemRepository getInstance() {
        if (instance == null) {
            synchronized (LibraryItemRepository.class) {
                if (instance == null) {
                    instance = new LibraryItemRepository();
                }
            }
        }

        return instance;
    }
    
	@Override
	public void add(LibraryItem item) {
		item.setId(itemIdCounter++);
        items.put(item.getId(), item);
	}

	@Override
	public LibraryItem getById(int itemId) {
		return items.get(itemId);
	}

	@Override
	public List<LibraryItem> getAll() {
		return new ArrayList<>(items.values());
	}

	@Override
	public boolean update(LibraryItem item) {
		if (items.containsKey(item.getId())) {
            items.put(item.getId(), item);
            return true;
        }
        return false; // item não encontrado
	}

	@Override
	public boolean delete(int id) {
		if (items.containsKey(id)) {
			items.remove(id);
		}
		return false; // item não encontrado
	}
	
}
