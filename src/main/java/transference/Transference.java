package transference;

import company.Departments;
import human.Candidate;
import human.Department;
import human.Employee;
import writefile.WriteFileInfCompany;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void moveCandidates(Departments departments, List<Candidate> candidates, WriteFileInfCompany writeFileInfCompany) {
        for (Candidate candidate : candidates) {
            for (Department department : departments.getDepartments()) {
                if (!(department.equals(candidate.getDepartment()))) {
                    BigDecimal avgSalary = department.getAvgSalary();

                    if (avgSalary.compareTo(candidate.getEmployee().getSalary()) < 0) {
                        //department.addEmployee(candidate.getEmployee()); // изменить расчет зп и убрать ремув
                        //BigDecimal newAvgSalary = department.getAvgSalary();
                        BigDecimal newAvgSalary = (department.getSalary().add(candidate.getEmployee().getSalary())).divide(BigDecimal.valueOf(department.getEmployeeList().size() + 1)).setScale(2, RoundingMode.HALF_UP);
                        //department.removeLastEmployee();
                        Department fromDepartment = candidate.getDepartment();
                        BigDecimal fromAvgSalary = fromDepartment.getAvgSalaryWithOutEmployee(candidate.getEmployee());
                        writeFileInfCompany.writeFile("Candidate - " + candidate.getEmployee().getName() + ", Avgsalary before = " + avgSalary +
                                ", Avgsalary after =  " + newAvgSalary + ", In to department name = " + department.getName() +
                                ", From department name = " + candidate.getDepartment().getName() + ", From avgSalary = " + fromAvgSalary + "\n");

                    }


                }

            }
        }
    }


}
