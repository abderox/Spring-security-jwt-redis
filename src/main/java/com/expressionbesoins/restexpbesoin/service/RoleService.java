package com.expressionbesoins.restexpbesoin.service;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;
}
