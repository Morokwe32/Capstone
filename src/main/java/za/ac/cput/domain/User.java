
package za.ac.cput.domain;

/**
 * User.java
 *Domain of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@OneToMany(mappedBy = "user")
    //private List<Rental> rentals = new ArrayList<>();

    private String userName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private String pictureUrl;


    public User() {
        // Default constructor
    }

    public User(int id, String userName, String email, String pictureUrl) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }
    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    //Setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPictureUrl(), user.getPictureUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getFirstName(), getLastName(), getPhoneNumber(), getPassword(), getEmail(), getPictureUrl());
    }

    public static class Builder
    {
        private int id;
        private String userName;
        private String email;
        private String pictureUrl;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String password;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder pictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }


        public User build()
        {
            User user = new User();
            user.id = this.id;
            user.userName = this.userName;
            user.email = this.email;
            user.pictureUrl = this.pictureUrl;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.phoneNumber = this.phoneNumber;
            user.password = this.password;
            return user;
        }

        public Builder copy(User user) {
            this.id = user.id;
            this.userName = user.userName;
            this.email = user.email;
            this.pictureUrl = user.pictureUrl;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.phoneNumber = user.phoneNumber;
            this.password = user.password;
            return this;
        }
    }
}

