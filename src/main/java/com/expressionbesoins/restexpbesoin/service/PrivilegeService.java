package com.expressionbesoins.restexpbesoin.service;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.repository.PrivilegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    @Autowired
    PrivilegeRepo privilegeRepo;

    Privilege findByName(PrivilegeEnum name) {
        if (name != null) {
            return privilegeRepo.findByName(name);
        }
        return null;
    }

    public Privilege savePrivilege( Privilege privilege){
        privilege = privilegeRepo.findByName(privilege.getName());
        if(privilege == null) {
            privilegeRepo.save(privilege);
        }
        return privilege;
    }
}
