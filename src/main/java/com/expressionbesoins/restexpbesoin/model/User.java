package com.expressionbesoins.restexpbesoin.model;
/**
 * @autor abdelhadi mouzafir
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Data
@Entity
@Table(name="users" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    // les informations génirales de l'utilisateur
    @Column(name="username" , nullable = false )
    private String username;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @NotNull
    @Column(name="email"  )
    private String email;
    @NotNull
    @Column(name="password" , nullable = false )
    private String password;
    @Column(name="enabled" )
    private boolean enabled;



    // many to many relatioship : user to roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    @JsonBackReference
    private Set<Role> roles = new HashSet<>( );

    // timestamps : creation date
    @CreationTimestamp
    @Column(name="created_at" , nullable = false, updatable = false)
    private Date createdAt;


    public User(String username, String password) {
        this.username = username;
        // ? encrypt password
        this.password = encoder.encode(password);
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User()
    {

    }

//    public boolean isMatchingPassword(String pass) {
//        return encoder.matches(pass, password);
//    }

    public static String encodePassword(String password) {
        return encoder.encode(password);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}
