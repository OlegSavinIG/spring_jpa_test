package example.service;

import example.model.department.Department;
import example.model.employee.Employee;
import example.model.employee.EmployeeProjectionDto;
import example.repository.DepartmentRepository;
import example.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(EmployeeServiceImpl.class)
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    @Transactional
    void getAllEmployeeProjections_shouldReturnCorrectProjections() {
        Department department = new Department();
        department.setName("Engineering");
        departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Developer");
        employee.setDepartment(department);
        employeeRepository.save(employee);

        List<EmployeeProjectionDto> result = employeeService.getAllEmployeeProjections();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).fullName()).isEqualTo("John Doe");
        assertThat(result.get(0).position()).isEqualTo("Developer");
        assertThat(result.get(0).departmentName()).isEqualTo("Engineering");
    }
}