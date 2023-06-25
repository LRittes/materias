package domain.entity;

import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private List<Email> emails;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String password, String email, List<Email> emails) {
        this.name = name;
        this.email = email;
        this.emails = emails;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void addEmail(Email email) {
        this.emails.add(email);
    }
}
