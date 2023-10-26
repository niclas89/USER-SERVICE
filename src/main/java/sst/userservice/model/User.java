package sst.userservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;



import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private long userId;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "email",nullable = false)
    private String email;


    @Column(name = "birth_date",nullable = false)
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate date;

    public User() {
    }

    public User(long userId, String userName, String firstName, String email, LocalDate date) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.email = email;
        this.date = date;
    }

    public User(String userName, String firstName, String email, LocalDate date) {
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

    public boolean complete(){
        return userName != null && firstName != null && email != null && date != null;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
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
