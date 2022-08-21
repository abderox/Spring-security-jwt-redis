package com.expressionbesoins.restexpbesoin.model;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="privileges")
@ToString(exclude = {"roles"})
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="privilege_id")
    private Long id;
    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private PrivilegeEnum name;

//    @ManyToMany(mappedBy = "privileges")
//    private Collection<Role> roles;

    public Privilege(PrivilegeEnum name) {
        this.name = name;
    }

    public Privilege() {

    }
}
