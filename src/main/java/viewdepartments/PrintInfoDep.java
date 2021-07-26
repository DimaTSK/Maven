package viewdepartments;

import company.Departments;
import human.Department;
import human.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrintInfoDep {


    public String viewDepartments(Departments departments) {
        StringBuilder data = new StringBuilder();
        for (Department department : departments.getDepartments()) {
            data.append(department.print());

        }
        return data.toString();
    }





}

