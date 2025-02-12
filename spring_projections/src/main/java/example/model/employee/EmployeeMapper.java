package example.model.employee;

import example.model.department.Department;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getDepartment() != null ? employee.getDepartment().getId() : null
        );
    }

    public static Employee toEntity(EmployeeDto dto, Department department) {
        return new Employee(
                null, // ID устанавливается автоматически при сохранении
                dto.firstName(),
                dto.lastName(),
                dto.position(),
                dto.salary(),
                department
        );
    }
}

