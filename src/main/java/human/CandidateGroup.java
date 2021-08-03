package human;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CandidateGroup {
    private List<Employee> candidateList = new ArrayList<>();
    private Department department;

    public List<Employee> getCandidateList() {
        return candidateList;
    }

    public CandidateGroup(Department department) {
        this.department = department;
    }

    public CandidateGroup(List<Employee> candidateList) {
        this.candidateList = candidateList;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getEmployee(int i) {
        return getCandidateList().get(i);
    }

    public BigDecimal getAvgSalary() {
        if (getCandidateList().size() == 0) {
            return BigDecimal.valueOf(0);
        }

        return getSalary().divide(BigDecimal.valueOf(getCandidateList().size()), 2, RoundingMode.HALF_UP);
    }

    public void addEmployee(Employee employee) {
        getCandidateList().add(employee);

    }

    public BigDecimal getSalary() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Employee employee : getCandidateList()) {
            sum = sum.add(employee.getSalary());
        }
        return sum;
    }

}

