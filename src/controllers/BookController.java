package src.controllers;

import java.time.LocalDate;

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

			String title=input.getString("Book title: ", 1, 50, true);
			String author=input.getString("Book author: ", 4, 50, true);
			int inventory=input.getInt("Quantity: ", 10, 50000, true);
			double fineValue=input.getDouble("Value fine: ", 0, 20, true);
			String isbn = input.getString("ISBN: ", 10, 20, true);
			LocalDate publication = input.getDate(
	                "Type the publication date: ", 
	                LocalDate.now().minusYears(-1000), 
	                LocalDate.now().minusYears(0), 
	                "dd/MM/yyyy",
	                true
	            );
			
			int id = input.getInt("Type the library's id: ", 1, 9999, true);

            Library library = this.libraryRepository.getById(id);

            if (library == null) {
                throw new Exception("Sorry, invalid library :(\n");
            }
            
            Book book=new Book(title, author, inventory, library, fineValue, null, isbn, publication); //null é o genero
            this.itemRepository.add(book);
            
            library.addItem(book);//adiciona o item criado na lista de item em Library
            this.libraryRepository.update(library);//como um novo item foi adicionado na Library, precisar ser atualizada
            
            System.out.println("Book sucefully created!\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void update() {        
        try {
            Input input = Input.getInstance();

			int id=input.getInt("Book's id: ", 1, 9999, true);
			LibraryItem item=this.itemRepository.getById(id);
			
			if (item!=null && item instanceof Book) {
				Book book=(Book) item;
				String title=input.getString("Book title: ", 1, 50, false);
				String author=input.getString("Book author: ", 4, 50, false);
				int inventory=input.getInt("Quantity: ", 0, 50000, false);
				double fineValue=input.getDouble("Value fine: ", 0, 50, false);
				String isbn = input.getString("ISBN: ", 10, 20, false);
				LocalDate publication = input.getDate(
		                "Type the publication date: ", 
		                LocalDate.now().minusYears(-1000), 
		                LocalDate.now().minusYears(0), 
		                "dd/MM/yyyy",
		                false
		            );
				
				int id1 = input.getInt("Type the library's id: ", 1, 9999, true);
	            Library library = this.libraryRepository.getById(id1);
	            
	            book.setTitle((title != null) ? title : book.getTitle());
	            book.setAuthor((author != null) ? author : book.getAuthor());
	            book.setInventory((inventory != 0) ? inventory : book.getInventory());
	            book.setFineValue((fineValue != 0) ? fineValue : book.getFineValue());
	            book.setIsbn((isbn != null) ? isbn : book.getIsbn());
	            book.setPublication((publication != null) ? publication : book.getPublication());
	            book.setLibrary((library != null) ? library : book.getLibrary());
	            System.out.println("update ta ok!");
			}else System.out.println("Book not found!");
			
		} catch (Exception e) {
			System.out.println("Error!");
			e.getMessage();
		}
    }
    public void list() {
        try {
            for (LibraryItem item: itemRepository.getAll()) {
                if (item instanceof Book) {
                	Book i=((Book)item);
                    System.out.println(i.getIdItem() + ". " + i.toString());
                }
            }
        } catch (Exception e) {
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
            System.out.println(e.getMessage());
        }
    }
	
}
