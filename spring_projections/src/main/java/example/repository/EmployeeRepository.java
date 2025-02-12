package example.repository;

import example.model.employee.Employee;
import example.model.employee.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    Optional<EmployeeProjection> findByEmployeeById(Long id);
    List<EmployeeProjection> findAllBy();
}
