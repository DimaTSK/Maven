package viewdepartments;

import company.Departments;
import human.Department;
import human.Employee;
import writefile.WriteFileInfCompany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrintInfoDep {


    public void viewDepartments(Departments departments, WriteFileInfCompany writeFileInfCompany) {
        StringBuilder data = new StringBuilder();
        for (Department department : departments.getDepartments()) {
            department.print(writeFileInfCompany);

        }

    }

}

