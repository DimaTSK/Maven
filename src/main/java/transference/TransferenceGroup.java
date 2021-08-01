package transference;

import company.Departments;
import human.Candidate;
import human.CandidateGroup;
import human.Department;
import human.Employee;
import writefile.WriteFileInfCompany;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TransferenceGroup {

    public static List<CandidateGroup> findCandidatesGroup(Department department) {
        List<CandidateGroup> result = new ArrayList<>();
        List<CandidateGroup> lists = new ArrayList<>();

        for (int i = 2; i < department.getEmployeeList().size(); i++) {

            lists.addAll(combinations2(department, i, 0, new Employee[i]));


            BigDecimal departmentAvgSalary = department.getAvgSalary();

            for (CandidateGroup candidateGroup : lists) {


                if (departmentAvgSalary.compareTo(candidateGroup.getAvgSalary()) > 0) {


                    result.add(candidateGroup);
                }

            }
            lists.clear();

        }
        return result;

    }


    static List<CandidateGroup> combinations2(Department department, int len, int startPosition, Employee[] result) {
        List<CandidateGroup> resultMeth = new ArrayList<>();


        if (len == 0) {
            CandidateGroup candidateGroup = new CandidateGroup(department);

            for (Employee employee : result) {
                candidateGroup.addEmployee(employee);

            }

            resultMeth.add(candidateGroup);
            return resultMeth;
        }
        for (int i = startPosition; i <= department.getEmployeeList().size() - len; i++) {
            result[result.length - len] = department.getEmployeeList().get(i);

            resultMeth.addAll(combinations2(department, len - 1, i + 1, result));
        }
        return resultMeth;
    }

    public static void moveCandidates(Departments departments, List<CandidateGroup> candidateGroups, WriteFileInfCompany writeFileInfCompany) {
        for (CandidateGroup candidateGroup : candidateGroups) {
            BigDecimal groupAvgSalary = candidateGroup.getAvgSalary();
            for (Department department : departments.getDepartments()) {
                if (!(department.equals(candidateGroup.getDepartment()))) {
                    BigDecimal avgSalary = department.getAvgSalary();
                    if (avgSalary.compareTo(groupAvgSalary) < 0) {

                        StringBuilder stringData = new StringBuilder();
                        stringData.append("Name employees - ");
                        for (Employee employee : candidateGroup.getCandidateList()) {
                            stringData.append(employee.getName()).append(" ");
                        }
                        stringData.append(", From department - ").append(candidateGroup.getDepartment().getName());
                        stringData.append(", In to department -").append(department.getName());

                        BigDecimal newAvgSalary = (department.getSalary().add(candidateGroup.getSalary())).divide(BigDecimal.valueOf(department.getEmployeeList().size() + candidateGroup.getCandidateList().size())).setScale(2, RoundingMode.HALF_UP);
                        stringData.append(", Old Avgsalary -  ").append(avgSalary);
                        stringData.append(", New Avgsalary -  ").append(newAvgSalary).append("\n");

                        writeFileInfCompany.writeFile(stringData.toString());


                    }

                }
            }
        }


    }
}





