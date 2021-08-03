package company;

import human.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departments {
    private Map<String, Department> departments = new HashMap<>();


    public Department addDepartment(String departmentName) {
        return departments.computeIfAbsent(departmentName, Department::new);

    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments.values());
    }


}