package example.model.department;

import java.util.Collections;

public class DepartmentMapper {

    public static DepartmentDto toDto(Department department) {
        return new DepartmentDto(department.getName());
    }

    public static Department toEntity(DepartmentDto dto) {
        return new Department(null, dto.name(), Collections.emptyList());
    }
}

