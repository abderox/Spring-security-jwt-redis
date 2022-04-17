package com.expressionbesoins.restexpbesoin.repository;
/**
 * @autor abdelhadi mouzafir
 */
import com.expressionbesoins.restexpbesoin.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege,Long> {

    Privilege findPrivilegeByName(PrivilegeEnum name);

}
