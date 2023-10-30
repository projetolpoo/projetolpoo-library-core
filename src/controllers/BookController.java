package src.controllers;

import java.time.LocalDate;

import src.enums.GenderEnum;
import src.helpers.Input;
import src.models.Book;
import src.models.Library;
import src.models.LibraryItem;
import src.repositories.LibraryItemRepository;
import src.repositories.LibraryRepository;

public class BookController {

    private LibraryItemRepository itemRepository;
    private LibraryRepository libraryRepository;
    
    public BookController() {
    	this.itemRepository=LibraryItemRepository.getInstance();
    	this.libraryRepository=LibraryRepository.getInstance();
    }
    
    public void create() {        
        try {
            Input input = Input.getInstance();

			String title=input.getString("Book title (between 1 and 50): ", 1, 50, true);
			String author=input.getString("Book author (between 4 and 50): ", 4, 50, true);
			GenderEnum gender = Input.getInstance().getGender(true);
			int inventory=input.getInt("Quantity (between 10 and 500000): ", 10, 50000, true);
			double fineValue=input.getDouble("Value fine (between 0 and 20): ", 0, 20, true);
			String isbn = input.getString("ISBN (between 10 and 20): ", 10, 20, true);
			LocalDate publication = input.getDate(
	                "Type the publication date: ", 
	                LocalDate.now().minusYears(0), 
	                LocalDate.now().minusYears(223), 
	                "dd/MM/yyyy",
	                true
	            );
			
			int id = input.getInt("Type the library's id: ", 1, 9999, true);

            Library library = this.libraryRepository.getById(id);

            if (library == null) {
                throw new Exception("Sorry, invalid library :(\n");
            }
            
            Book book=new Book(title, author, inventory, library, fineValue, gender, isbn, publication);
            this.itemRepository.add(book);
            
            library.addItem(book);//adiciona o item criado na lista de item em Library
            this.libraryRepository.update(library);//como um novo item foi adicionado na Library, precisa ser atualizada
            
            System.out.println("Book sucefully created!\n");
		} catch (Exception e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
		}
    }
    public void update() {        
        try {
            Input input = Input.getInstance();

			int id=input.getInt("Book's id: ", 1, 9999, true);
			LibraryItem item=this.itemRepository.getById(id);
			
			if (item!=null && item instanceof Book) {
				Book book=(Book) item;
				String title=input.getString("Book title (between 1 and 50 character): ", 1, 50, false);
				String author=input.getString("Book author (between 4 and 50 character): ", 4, 50, false);
				GenderEnum gender = Input.getInstance().getGender(false);
				int inventory=input.getInt("Quantity (between 0 and 500000): ", 0, 50000, false);
				double fineValue=input.getDouble("Value fine (between 0 and 50): ", 0, 50, false);
				String isbn = input.getString("ISBN (between 10 and 20 character): ", 10, 20, false);
				LocalDate publication = input.getDate(
		                "Type the publication date: ", 
		                LocalDate.now().minusYears(0), 
		                LocalDate.now().minusYears(223), 
		                "dd/MM/yyyy",
		                false
		            );
				
				int id1 = input.getInt("Type the library's id: ", 1, 9999, true);
	            Library library = this.libraryRepository.getById(id1);
	            
	            book.setTitle((title != null) ? title : book.getTitle());
	            book.setAuthor((author != null) ? author : book.getAuthor());
				book.setGender((gender != null) ? gender : book.getGender());
	            book.setInventory((inventory != 0) ? inventory : book.getInventory());
	            book.setFineValue((fineValue != 0) ? fineValue : book.getFineValue());
	            book.setIsbn((isbn != null) ? isbn : book.getIsbn());
	            book.setPublication((publication != null) ? publication : book.getPublication());
	            book.setLibrary((library != null) ? library : book.getLibrary());

				this.itemRepository.update(book);
	            System.out.println("Update successfully done!");
			}else System.out.println("Book not found!");
			
		} catch (Exception e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
		}
    }
    public void list() {
        try {
            for (LibraryItem item: itemRepository.getAll()) {
                if (item instanceof Book) {
                	Book i=((Book)item);
                    System.out.println(i.getId() + ". " + i.toString());
                }
            }
        } catch (Exception e) {
			System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
    public void delete() {
        try {
            Input input = Input.getInstance();

            int id = input.getInt("Type the book' id: ", 1, 9999, true);

            boolean result = this.itemRepository.delete(id);

            if (result) {
                System.out.println("Book sucefully deleted!!\n");
            } else {
                System.out.println("Sorry, book not found :(\n");
            }
        } catch (Exception e) {
			System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
	
}
