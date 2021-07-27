package transference;

import company.Departments;
import human.Candidate;
import human.Department;
import human.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transference {

    public static List<Candidate> findCandidates(Departments departments) {
        List<Candidate> candidates = new ArrayList<>();
        for (Department department : departments.getDepartments()) {

            if (department.getEmployeeList().size() < 2) {
                continue;
            }
            BigDecimal avgSalary = department.getAvgSalary();
            for (Employee employee : department.getEmployeeList()) {
                if (avgSalary.compareTo(employee.getSalary()) > 0) {

                    candidates.add(new Candidate(employee, department));
                }

            }
        }
        return candidates;
    }

    public static String moveCandidates(Departments departments, List<Candidate> candidates) {
        StringBuilder result = new StringBuilder();
        for (Candidate candidate : candidates) {
            for (Department department : departments.getDepartments()) {
                if (!(department.equals(candidate.getDepartment()))) {
                    BigDecimal avgSalary = department.getAvgSalary();

                    if (avgSalary.compareTo(candidate.getEmployee().getSalary()) < 0) {
                        department.addEmployee(candidate.getEmployee());
                        BigDecimal newAvgSalary = department.getAvgSalary();
                        department.removeLastEmployee();
                        Department fromDepartment = candidate.getDepartment();
                        BigDecimal fromAvgSalary = fromDepartment.getAvgSalaryWithOutEmployee(candidate.getEmployee());
                        result.append("Candidate - ").append(candidate.getEmployee().getName());
                        result.append(", Avgsalary before = ").append(avgSalary);
                        result.append(", Avgsalary after =  ").append(newAvgSalary);
                        result.append(", In to department name = ").append(department.getName());
                        result.append(", From department name = ").append(candidate.getDepartment().getName());
                        result.append(" , From avgSalary = ").append(fromAvgSalary);
                        result.append("\n");

                    }


                }

            }
        }return result.toString();
    }
}
