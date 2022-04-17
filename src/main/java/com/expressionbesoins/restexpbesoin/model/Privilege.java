package com.expressionbesoins.restexpbesoin.model;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.enums.PrivilegeEnum;
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
    @Enumerated
    private PrivilegeEnum name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(PrivilegeEnum name) {
        this.name = name;
    }
}
