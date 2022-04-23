package com.expressionbesoins.restexpbesoin.model;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private PrivilegeEnum name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )

    private Collection<Privilege> privileges;

    public Role(PrivilegeEnum name) {
        this.name = name;
    }

    public Role() {

    }
}
