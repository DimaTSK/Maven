package transference;

import company.Departments;
import human.Department;
import human.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transference {

    private int maxSize;


    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public static List<Employee> findCandidates(Departments departments) {
        List<Employee> candidates = new ArrayList<>();
        for (Department department : departments.getDepartments()) {

            if (department.getEmployeeList().size() < 2) {
                continue;
            }
            BigDecimal avgSalary = department.getAvgSalary();
            //BigDecimal totalSalary = department.getSalary();
            for (Employee employee : department.getEmployeeList()) {
                // BigDecimal localAvgSalary = totalSalary.subtract(employee.getSalary()).divide(BigDecimal.valueOf(countEmployees - 1), 2, RoundingMode.HALF_UP);
                if (avgSalary.compareTo(employee.getSalary()) > 0) {
                    //System.out.println("Candidate " + employee.getName() + ", Avgsalary before = " + avgSalary + ", Avgsalary after =  " + localAvgSalary);

                    candidates.add(employee);
                }

            }
        }
        return candidates;
    }

    public static void moveCandidates(Departments departments, List<Employee> candidates) {
        for (Employee candidate : candidates) {
            for (Department department : departments.getDepartments()) {
                BigDecimal avgSalary = department.getAvgSalary();

                if (avgSalary.compareTo(candidate.getSalary()) < 0) {
                    department.addEmployee(candidate);
                    BigDecimal newAvgSalary = department.getAvgSalary();
                    department.removeLastEmployee();
                    System.out.println("Candidate - " + candidate.getName() + ", Avgsalary before = " + avgSalary + ", Avgsalary after =  " + newAvgSalary + ", In to department name = " + department.getName());

                }

            }
        }
    }

}
