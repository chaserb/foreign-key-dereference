package com.example.demo.repo;

import com.example.demo.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cbarrett
 * @since 2020-08-31
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@EnableConfigurationProperties
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    protected TestEntityManager entityManager;

    private Employee ceo;
    private Employee cpo;
    private Employee coo;
    private Employee execSecr;
    private Employee prodSecr;
    private Employee operSecr;
    private Employee engrMgr;
    private Employee quasMgr;
    private Employee mktgMgr;
    private Employee custMgr;
    private Employee engr1;
    private Employee admin;

    @Before
    public void setup() {

        // Create the employees
        ceo = employeeRepo.save(Employee.builder().tid("default").title("ceo").build());
        cpo = employeeRepo.save(Employee.builder().tid("default").title("cpo").build());
        coo = employeeRepo.save(Employee.builder().tid("default").title("coo").build());
        execSecr = employeeRepo.save(Employee.builder().tid("default").title("execSecr").build());
        prodSecr = employeeRepo.save(Employee.builder().tid("default").title("prodSecr").build());
        operSecr = employeeRepo.save(Employee.builder().tid("default").title("operSecr").build());
        engrMgr = employeeRepo.save(Employee.builder().tid("default").title("engrMgr").build());
        quasMgr = employeeRepo.save(Employee.builder().tid("default").title("quasMgr").build());
        mktgMgr = employeeRepo.save(Employee.builder().tid("default").title("mktgMgr").build());
        custMgr = employeeRepo.save(Employee.builder().tid("default").title("custMgr").build());
        engr1 = employeeRepo.save(Employee.builder().tid("default").title("engr1").build());
        admin = employeeRepo.save(Employee.builder().tid("default").title("admin").build());
        Employee engr2 = employeeRepo.save(Employee.builder().tid("default").title("engr2").build());
        Employee quas1 = employeeRepo.save(Employee.builder().tid("default").title("quas1").build());
        Employee quas2 = employeeRepo.save(Employee.builder().tid("default").title("quas2").build());
        Employee mktg1 = employeeRepo.save(Employee.builder().tid("default").title("mktg1").build());
        Employee mktg2 = employeeRepo.save(Employee.builder().tid("default").title("mktg2").build());
        Employee cust1 = employeeRepo.save(Employee.builder().tid("default").title("cust1").build());
        Employee cust2 = employeeRepo.save(Employee.builder().tid("default").title("cust2").build());

        // Create the hierarchy
        cpo.setManager(ceo);
        coo.setManager(ceo);
        execSecr.setManager(ceo);
        prodSecr.setManager(cpo);
        operSecr.setManager(coo);
        engrMgr.setManager(cpo);
        quasMgr.setManager(cpo);
        mktgMgr.setManager(coo);
        custMgr.setManager(coo);
        engr1.setManager(engrMgr);
        engr2.setManager(engrMgr);
        quas1.setManager(quasMgr);
        quas2.setManager(quasMgr);
        mktg1.setManager(mktgMgr);
        mktg2.setManager(mktgMgr);
        cust1.setManager(custMgr);
        cust2.setManager(custMgr);

        // Reset the persistence context
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testFindRootManagers() {
        List<Employee> rootManagers = employeeRepo.findRootManagers();
        assertThat(rootManagers).containsOnly(ceo, admin);
    }

}

