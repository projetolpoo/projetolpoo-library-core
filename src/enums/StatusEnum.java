package src.enums;

public enum StatusEnum {
    BORROWED("borrowed"),
    AVAILABLE("available");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }
}
