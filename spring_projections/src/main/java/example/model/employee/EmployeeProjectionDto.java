package example.model.employee;

public record EmployeeProjectionDto(
        String fullName,
        String position,
        String departmentName
) {}
