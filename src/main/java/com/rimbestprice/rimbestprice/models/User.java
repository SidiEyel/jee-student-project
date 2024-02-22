package com.rimbestprice.rimbestprice.models;

import org.hibernate.mapping.Set;
// for the hashing of the password
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = false;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
private List<UserReservation> reservations;  
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }
    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "'}"; 
    }


    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
  

}

