package src.controllers;

import java.time.LocalDate;

import src.enums.GenderEnum;
import src.helpers.Input;
import src.models.Library;
import src.models.LibraryItem;
import src.models.Magazine;
import src.repositories.LibraryItemRepository;
import src.repositories.LibraryRepository;

public class MagazineController{
    private LibraryItemRepository itemRepository;
    private LibraryRepository libraryRepository;
    
    public MagazineController() {
    	this.itemRepository=LibraryItemRepository.getInstance();
    	this.libraryRepository=LibraryRepository.getInstance();
    }
    
    public void create() {        
        try {
            Input input = Input.getInstance();

			String title=input.getString("Magazine title (between 0 and 50 characters): ", 1, 50, true);
			String author=input.getString("Magazine author (between 4 and 50 characters): ", 4, 50, true);
			GenderEnum gender = Input.getInstance().getGender(true);
			int inventory=input.getInt("Quantity (between 10 and 50000): ", 10, 50000, true);
			double fineValue=input.getDouble("Value fine (between 0 and 50): ", 0, 50, true);
			String issue = input.getString("ISBN (between 10 and 20 characters): ", 10, 20, true);
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
            Magazine magazine = new Magazine(title, author, inventory, library, fineValue, gender, issue, publication);
            this.itemRepository.add(magazine);
            
            library.addItem(magazine);//adiciona o item criado na lista de item em Library
            this.libraryRepository.update(library);//como um novo item foi adicionado na Library, precisar ser atualizada
            
            System.out.println("Magazine sucefully created!\n");
		} catch (Exception e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
		}
    }
    public void update() {        
        try {
            Input input = Input.getInstance();

			int id=input.getInt("Magazine id: ", 1, 9999, true);
			LibraryItem item=this.itemRepository.getById(id);
			
			if (item!=null && item instanceof Magazine) {
				Magazine magazineToUpdate=(Magazine)item;

				String title=input.getString("Magazine title (between 0 and 50 characters): ", 1, 50, false);
				String author=input.getString("Magazine author (between 4 and 50 characters): ", 4, 50, false);
				GenderEnum gender = Input.getInstance().getGender(false);
				int inventory=input.getInt("Quantity (between 10 and 50000): ", 10, 50000, false);
				double fineValue=input.getDouble("Value fine (between 0 and 50): ", 0, 50, false);
				String isbn = input.getString("ISBN (between 10 and 20 characters): ", 10, 20, false);
				LocalDate publication = input.getDate(
		                "Type the publication date: ", 
		                LocalDate.now().minusYears(0), 
		                LocalDate.now().minusYears(223), 
		                "dd/MM/yyyy",
		                false
		            );
				
				int id1 = input.getInt("Type the library's id: ", 1, 9999, true);

	            Library library = this.libraryRepository.getById(id1);

	            magazineToUpdate.setTitle((title != null) ? title : magazineToUpdate.getTitle());
	            magazineToUpdate.setAuthor((author != null) ? author : magazineToUpdate.getAuthor());
				magazineToUpdate.setGender((gender != null) ? gender : magazineToUpdate.getGender());
	            magazineToUpdate.setInventory((inventory != 0) ? inventory : magazineToUpdate.getInventory());
	            magazineToUpdate.setFineValue((fineValue != 0) ? fineValue : magazineToUpdate.getFineValue());
	            magazineToUpdate.setIssueNumber((isbn != null) ? isbn : magazineToUpdate.getIssueNumber());
	            magazineToUpdate.setPublication((publication != null) ? publication : magazineToUpdate.getPublication());
	            magazineToUpdate.setLibrary((library != null) ? library : magazineToUpdate.getLibrary());

				this.itemRepository.update(magazineToUpdate);
	            System.out.println("Update successfully done!");
			}else System.out.println("Magazine not found!");
			
		} catch (Exception e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
		}
    }
    public void list() {
        try {
            for (LibraryItem item: itemRepository.getAll()) {
                if (item instanceof Magazine) {
                	Magazine i=((Magazine)item);
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

            int id = input.getInt("Type the magazine' id: ", 1, 9999, true);

            boolean result = this.itemRepository.delete(id);

            if (result) {
                System.out.println("Magazine sucefully deleted!!\n");
            } else {
                System.out.println("Sorry, magazine not found :(\n");
            }
        } catch (Exception e) {
			System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }
}
