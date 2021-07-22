package human;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> employeeList = new ArrayList<>();
    private String name;


    public Department(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        getEmployeeList().add(employee);
    }


    public void removeLastEmployee() {
        if (getEmployeeList().isEmpty()) {
            return;
        }
        getEmployeeList().remove(getEmployeeList().size() - 1);
    }

    public void removeEmployee(Employee employee) {
        int index = getEmployeeList().indexOf(employee);
        if (index == -1) {
            return;
        }
        getEmployeeList().remove(index);
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }


    public BigDecimal getAvgSalary() {
        if (getEmployeeList().size() == 0) {
            return BigDecimal.valueOf(0);
        }

        return getSalary().divide(BigDecimal.valueOf(getEmployeeList().size()), 2, RoundingMode.HALF_UP);
    }

    public void print(int maxSize) {
        System.out.println("Department - " + getName() + " : " + " Average salary -  " + getAvgSalary());
        for (Employee employee : getEmployeeList()) {
            employee.print(maxSize);
        }
    }


    public BigDecimal getSalary() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Employee employee : getEmployeeList()) {
            sum = sum.add(employee.getSalary());
        }
        return sum;
    }
}
