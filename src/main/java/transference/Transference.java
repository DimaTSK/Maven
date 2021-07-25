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
            for (Employee employee: department.getEmployeeList()) {
                if (avgSalary.compareTo(employee.getSalary()) > 0) {

                    candidates.add(new Candidate(employee,department.getName()));
                }

            }
        }
        return candidates;
    }

    public static void moveCandidates(Departments departments, List<Candidate> candidates) {
        for (Candidate candidate : candidates) {
            for (Department department : departments.getDepartments()) {
                BigDecimal avgSalary = department.getAvgSalary();

                if (avgSalary.compareTo(candidate.getEmployee().getSalary()) < 0) {
                    department.addEmployee(candidate.getEmployee());
                    BigDecimal newAvgSalary = department.getAvgSalary();
                    department.removeLastEmployee();
                    System.out.println("Candidate - " + candidate.getEmployee().getName() + ", Avgsalary before = " + avgSalary + ", Avgsalary after =  " + newAvgSalary + ", In to department name = " + department.getName() + ", From department name = " + candidate.getDepartmentName());

                }

            }
        }
    }

}
