package example.service;

import example.model.department.Department;
import example.model.employee.Employee;
import example.model.employee.EmployeeDto;
import example.model.employee.EmployeeMapper;
import example.model.employee.EmployeeProjection;
import example.model.employee.EmployeeProjectionDto;
import example.repository.DepartmentRepository;
import example.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<EmployeeProjectionDto> getAllEmployeeProjections() {
        return employeeRepository.findAllBy()
                .stream()
                .map(proj -> new EmployeeProjectionDto(
                        proj.getFullName(),
                        proj.getPosition(),
                        proj.getDepartment().getName()
                ))
                .toList();
    }
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return EmployeeMapper.toDto(employee);
    }
    public EmployeeDto createEmployee(EmployeeDto dto) {
        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        Employee employee = new Employee();
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setPosition(dto.position());
        employee.setSalary(dto.salary());
        employee.setDepartment(department);

        return EmployeeMapper.toDto(employeeRepository.save(employee));
    }
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }



    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Department department = null;
        if(dto.departmentId() != null) {
            department = departmentRepository.findById(dto.departmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        }

        existing.setFirstName(dto.firstName());
        existing.setLastName(dto.lastName());
        existing.setPosition(dto.position());
        existing.setSalary(dto.salary());
        if(department != null) {
            existing.setDepartment(department);
        }

        return EmployeeMapper.toDto(employeeRepository.save(existing));
    }

    public void deleteEmployee(Long id) {
        if(!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
