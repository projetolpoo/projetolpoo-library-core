package src.models;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String password;
    private LocalDate birthDate;
    private String documentNumber;

    public User(String name, String password, LocalDate birthDate, String documentNumber) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getDocumentNumber() {
        return documentNumber;
    }
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", birthDate=" + birthDate
                + ", documentNumber=" + documentNumber + "]";
    }
}