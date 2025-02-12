package example.model.employee;

public interface EmployeeProjection {
    String getFirstName();
    String getLastName();
    String getPosition();

    DepartmentInfo getDepartment();

    interface DepartmentInfo {
        String getName();
    }

    default String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}

