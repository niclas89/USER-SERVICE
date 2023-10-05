package sst.userservice.model;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;

   @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "email",nullable = false)
    //@Email(message = "Email must be valid, adress@provider.com")
    private String email;

    @Column(name = "birth_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate date;

    public User() {
    }

    public User(long id, String userName, String firstName, String email, LocalDate date) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.email = email;
        this.date = date;
    }

    public void update(User user){
        if(user.userName != null) {
            this.userName = user.userName;
        }
        if(user.firstName != null) {
            this.firstName = user.firstName;
        }
        if(user.email != null) {
            this.email = user.email;
        }
        if(user.date != null) {
            this.date = user.date;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
