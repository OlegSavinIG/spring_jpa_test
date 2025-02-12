package example.service;

import example.model.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto dto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(Long id);

    DepartmentDto updateDepartment(Long id, DepartmentDto dto);

    void deleteDepartment(Long id);
}
