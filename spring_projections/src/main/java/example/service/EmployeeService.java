package example.service;

import example.model.employee.EmployeeDto;
import example.model.employee.EmployeeProjectionDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeProjectionDto> getAllEmployeeProjections();

    EmployeeDto createEmployee(EmployeeDto dto);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto updateEmployee(Long id, EmployeeDto dto);

    void deleteEmployee(Long id);
}
