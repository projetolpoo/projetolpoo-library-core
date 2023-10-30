package src.models;

import src.enums.StatusEnum;

import java.time.LocalDate;

public class Borrow {
    private LibraryItem libraryItem;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer quantity;
    private StatusEnum status;
    private Float fineValue;

    public Borrow(LibraryItem libraryItem, User user, LocalDate startDate, LocalDate endDate, Integer quantity, StatusEnum status, Float fineValue) {
        this.libraryItem = libraryItem;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
        this.fineValue = fineValue;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Float getFineValue() {
        return fineValue;
    }

    public void setFineValue(Float fineValue) {
        this.fineValue = fineValue;
    }



    @Override
    public String toString() {
        return "Borrow{" +
                "libraryItem=" + libraryItem +
                ", user=" + user +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", quantity=" + quantity +
                ", status=" + status +
                ", fineValue=" + fineValue +
                '}';
    }
}
