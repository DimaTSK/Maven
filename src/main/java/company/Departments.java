package company;

import human.Department;
import human.Employee;
import viewdepartments.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departments {
    private Map<String, Department> departments = new HashMap<>();


    public Department addDepartment(String departmentName) {
        /*String employeeName = list.get(0);
        String departmentName = list.get(1);
        BigDecimal salary = new BigDecimal(list.get(2)).setScale(2, RoundingMode.HALF_UP);
        Employee employee = new Employee(employeeName, salary);
        Department department = departments.get(departmentName);/*/

        return departments.computeIfAbsent(departmentName, Department::new);

    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments.values());
    }
}