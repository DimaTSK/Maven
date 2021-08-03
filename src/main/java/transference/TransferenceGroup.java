package transference;

import company.Departments;
import human.Department;
import human.Employee;
import writefile.WriteFileInfCompany;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class TransferenceGroup {

    public static void transferenceGroup(Departments departments, WriteFileInfCompany writeFileInfCompany) {

        for (Department department : departments.getDepartments()) {

            for (int i = 2; i < department.getEmployeeList().size(); i++) {
                transferCombinationGroup(department, i, 0, new Employee[i], departments, writeFileInfCompany);

            }
        }

    }

    static void transferCombinationGroup(Department department, int len, int startPosition, Employee[] result, Departments departments, WriteFileInfCompany writeFileInfCompany) {

        if (len == 0) {
            Department departmentGroup = new Department(department.getName());

            for (Employee employee : result) {
                departmentGroup.addEmployee(employee);

            }
            BigDecimal groupAvgSalary = departmentGroup.getAvgSalary();
            if (department.getAvgSalary().compareTo(groupAvgSalary) > 0) {
                for (Department departmentCheck : departments.getDepartments()) {
                    if (!(departmentCheck.equals(department))) {
                        BigDecimal avgSalary = departmentCheck.getAvgSalary();
                        if (avgSalary.compareTo(groupAvgSalary) < 0) {
                            writeFileInfCompany.writeFile(showString(departmentGroup, departmentCheck, avgSalary));

                        }
                    }
                }
            }
            return;
        }
        for (int i = startPosition; i <= department.getEmployeeList().size() - len; i++) {
            result[result.length - len] = department.getEmployeeList().get(i);
            transferCombinationGroup(department, len - 1, i + 1, result, departments, writeFileInfCompany);

        }

    }

    public static String showString(Department departmentGroup, Department department, BigDecimal departmentAvgSalary) {

        StringBuilder stringData = new StringBuilder();
        stringData.append("Name employees - ");
        for (Employee employee : departmentGroup.getEmployeeList()) {
            stringData.append(employee.getName()).append(" ");
        }
        stringData.append(", From department - ").append(departmentGroup.getName());
        stringData.append(", In to department -").append(department.getName());

        BigDecimal newAvgSalary = (department.getSalary().add(departmentGroup.getSalary())).divide(BigDecimal.valueOf(department.getEmployeeList().size() + departmentGroup.getEmployeeList().size()), 2, RoundingMode.HALF_UP);
        stringData.append(", Old Avgsalary -  ").append(departmentAvgSalary);
        stringData.append(", New Avgsalary -  ").append(newAvgSalary).append("\n");

        return stringData.toString();


    }
}