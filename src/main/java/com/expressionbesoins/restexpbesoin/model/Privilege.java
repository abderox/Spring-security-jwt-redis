package com.expressionbesoins.restexpbesoin.model;
import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="privilege_id")
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

}
