package example.service;

import example.model.department.Department;
import example.model.department.DepartmentDto;
import example.model.department.DepartmentMapper;
import example.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto save(DepartmentDto dto) {
        return null;
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDto)
                .toList();
    }

    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));
        return DepartmentMapper.toDto(department);
    }

    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        existing.setName(dto.name());
        return DepartmentMapper.toDto(departmentRepository.save(existing));
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found");
        }
        departmentRepository.deleteById(id);
    }
}
