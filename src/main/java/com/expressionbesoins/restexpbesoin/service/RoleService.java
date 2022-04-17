package com.expressionbesoins.restexpbesoin.service;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public Role findByName(PrivilegeEnum name) {
        if (name != null) {
            return roleRepo.findRoleByName(name);
        }
        return null;
    }

    public Role saveRole(Role role, Collection<Privilege> privileges){
        role = roleRepo.findRoleByName(role.getName());
        if(role == null) {
            role.setPrivileges(privileges);
            roleRepo.save(role);
        }
        return role;
    }
}
