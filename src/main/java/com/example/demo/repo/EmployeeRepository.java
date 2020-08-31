package com.example.demo.repo;

import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeTenantKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeTenantKey> {

    @Query("select rootManager from Employee rootManager " +
            "where not exists (" +
            "   select employee from Employee employee " +
            "   where rootManager.manager = employee" +
            ")")
    List<Employee> findRootManagers();

}
