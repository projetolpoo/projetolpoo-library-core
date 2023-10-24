package src.controllers;

import java.time.LocalDate;

import src.enums.GenderEnum;
import src.helpers.Input;
import src.models.Library;
import src.models.LibraryItem;
import src.models.Movie;
import src.repositories.LibraryItemRepository;
import src.repositories.LibraryRepository;

public class MovieController {
    private LibraryItemRepository itemRepository;
    private LibraryRepository libraryRepository;
    
    public MovieController() {
    	this.itemRepository=LibraryItemRepository.getInstance();
    	this.libraryRepository=LibraryRepository.getInstance();
    }
    
    public void create() {        
        try {
            Input input = Input.getInstance();

			String title=input.getString("Movie title: ", 1, 50, true);
			String author=input.getString("Movie author: ", 4, 50, true);
			GenderEnum gender = Input.getInstance().getGender(true);
			int inventory=input.getInt("Quantity: ", 10, 50000, true);
			double fineValue=input.getDouble("Value fine: ", 0, 50, true);
			int duration = input.getInt("Durantion: ", 30, 600, true);
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
            Movie movie = new Movie(title, author, inventory, library, fineValue, gender, publication, duration);
            this.itemRepository.add(movie);
            
            library.addItem(movie);//adiciona o item criado na lista de item em Library
            this.libraryRepository.update(library);//como um novo item foi adicionado na Library, precisar ser atualizada
            
            System.out.println("Movie sucefully created!\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void update() {        
        try {
            Input input = Input.getInstance();

			int id=input.getInt("Movie  id: ", 1, 9999, true);

			LibraryItem item=this.itemRepository.getById(id);
			
			if (item!=null && item instanceof Movie) {
				Movie movieToUpdate=(Movie) item;
				String title=input.getString("Movie title: ", 1, 50, false);
				String author=input.getString("Movie author: ", 4, 50, false);
				GenderEnum gender = Input.getInstance().getGender(false);
				int inventory=input.getInt("Quantity: ", 0, 50000, false);
				double fineValue=input.getDouble("Value fine: ", 0, 50, false);
				int duration = input.getInt("Duration: ", 30, 600, false);
				LocalDate publication = input.getDate(
		                "Type the publication date: ", 
		                LocalDate.now().minusYears(-1000), 
		                LocalDate.now().minusYears(0), 
		                "dd/MM/yyyy",
		                false
		            );
				
				int id1 = input.getInt("Type the library's id: ", 1, 9999, true);

	            Library library = this.libraryRepository.getById(id1);
	            
	            movieToUpdate.setTitle((title != null) ? title : movieToUpdate.getTitle());
	            movieToUpdate.setAuthor((author != null) ? author : movieToUpdate.getAuthor());
				movieToUpdate.setGender((gender != null) ? gender : movieToUpdate.getGender());
	            movieToUpdate.setInventory((inventory != 0) ? inventory : movieToUpdate.getInventory());
	            movieToUpdate.setFineValue((fineValue != 0) ? fineValue : movieToUpdate.getFineValue());
	            movieToUpdate.setDuration((duration != 0) ? duration : movieToUpdate.getDuration());
	            movieToUpdate.setPublication((publication != null) ? publication : movieToUpdate.getPublication());
	            movieToUpdate.setLibrary((library != null) ? library : movieToUpdate.getLibrary());

				this.itemRepository.update(movieToUpdate);
	            System.out.println("update ta ok!");
			}else System.out.println("Movie not found!");
			
		} catch (Exception e) {
			System.out.println("Error!");
			System.out.println( e.getMessage());
		}
    }
    public void list() {
        try {
            for (LibraryItem item: itemRepository.getAll()) {
                if (item instanceof Movie) {
                	Movie i=((Movie)item);
                    System.out.println(i.getId() + ". " + i.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void delete() {
        try {
            Input input = Input.getInstance();

            int id = input.getInt("Type the movie' id: ", 1, 9999, true);

            boolean result = this.itemRepository.delete(id);

            if (result) {
                System.out.println("Movie sucefully deleted!!\n");
            } else {
                System.out.println("Sorry, movie not found :(\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
