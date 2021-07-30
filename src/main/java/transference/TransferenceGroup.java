package transference;

import company.Departments;
import human.Department;
import human.Employee;
import writefile.WriteFileInfCompany;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TransferenceGroup {

    public static List<Department> findCandidatesGroup(Department department) {
        List<Department> result = new ArrayList<>();
        List<Department> lists = new ArrayList<>();

        for (int i = 2; i < department.getEmployeeList().size(); i++) {
            lists.addAll(combinations2(department, i, 0, new Employee[i]));


            BigDecimal departmentAvgSalary = department.getAvgSalary();

            for (Department departmentCandidate : lists) {

                if (departmentAvgSalary.compareTo(departmentCandidate.getAvgSalary()) > 0) {


                    result.add(departmentCandidate);
                }

            }

        }
        return result;

    }


    static List<Department> combinations2(Department department, int len, int startPosition, Employee[] result) {
        List<Department> resultMeth = new ArrayList<>();


        if (len == 0) {
            Department departmentGroup = new Department(department.getName());

            for (Employee employee : result) {
                departmentGroup.addEmployee(employee);

            }

            resultMeth.add(departmentGroup);
            return resultMeth;
        }
        for (int i = startPosition; i <= department.getEmployeeList().size() - len; i++) {
            result[result.length - len] = department.getEmployeeList().get(i);

            resultMeth.addAll(combinations2(department, len - 1, i + 1, result));
        }
        return resultMeth;
    }

    public static void moveCandidates(Departments departments, List<Department> departmentGroups, WriteFileInfCompany writeFileInfCompany) {
        for (Department departmentGroup : departmentGroups) {
            BigDecimal groupAvgSalary = departmentGroup.getAvgSalary();
            for (Department department : departments.getDepartments()) {
                if (!(department.getName().equals(departmentGroup.getName()))) {
                    BigDecimal avgSalary = department.getAvgSalary();
                    if (avgSalary.compareTo(groupAvgSalary) < 0) {

                        StringBuilder stringData = new StringBuilder();
                        stringData.append("Name employees - ");
                        for (Employee employee : departmentGroup.getEmployeeList()) {
                            stringData.append(employee.getName()).append(" ");
                        }
                        stringData.append(", From department - ").append(departmentGroup.getName());
                        stringData.append(", In to department -").append(department.getName());

                        BigDecimal newAvgSalary = (department.getSalary().add(departmentGroup.getSalary())).divide(BigDecimal.valueOf(department.getEmployeeList().size() + departmentGroup.getEmployeeList().size())).setScale(2, RoundingMode.HALF_UP);
                        stringData.append(", Old Avgsalary -  ").append(avgSalary);
                        stringData.append(", New Avgsalary -  ").append(newAvgSalary).append("\n");

                        writeFileInfCompany.writeFile(stringData.toString());


                    }

                }
            }
        }


    }
}





