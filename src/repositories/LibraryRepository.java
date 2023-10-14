package src.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.models.Library;

public class LibraryRepository implements IRepository<Library> {

    private static LibraryRepository instance;
    private Map<Integer, Library> libraries = new HashMap<>();
    private int libraryIdCounter = 1;

    public static LibraryRepository getInstance() {
        if (instance == null) {
            synchronized (LibraryRepository.class) {
                if (instance == null) {
                    instance = new LibraryRepository();
                }
            }
        }

        return instance;
    }

    public void add(Library library) {
        library.setId(libraryIdCounter++);
        libraries.put(library.getId(), library);
    }

    public Library getById(int libraryId) {
        return libraries.get(libraryId);
    }

    public List<Library> getAll() {
        return new ArrayList<>(libraries.values());
    }

    public boolean update(Library updatedLibrary) {
        if (libraries.containsKey(updatedLibrary.getId())) {
            libraries.put(updatedLibrary.getId(), updatedLibrary);
            return true;
        }
        return false; // Library not found
    }

    public boolean delete(int libraryId) {
        if (libraries.containsKey(libraryId)) {
            libraries.remove(libraryId);
            return true;
        }
        return false; // Library not found
    }
}

