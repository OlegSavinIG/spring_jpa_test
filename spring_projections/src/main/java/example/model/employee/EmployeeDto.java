package example.model.employee;

public record EmployeeDto(
        String firstName,
        String lastName,
        String position,
        double salary,
        Long departmentId
) {}
