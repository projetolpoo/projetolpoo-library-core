package src.controllers;

import java.util.ArrayList;

public class BorrowItemsController {
    private ArrayList<String> borrowed = new ArrayList<>();

    public BorrowItemsController(ArrayList<String> borrowed) {
        this.borrowed = borrowed;
    }

    public void add(String value){
        borrowed.add(value);
    }
}
