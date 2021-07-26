package human;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<Employee> employeeList = new ArrayList<>();
    private String name;

    private int maxSizeName;
    private  int maxSizeSalary;


    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSizeName() {
        return maxSizeName;
    }

    public void setMaxSizeName(int maxSizeName) {
        this.maxSizeName = maxSizeName;
    }

    public int getMaxSizeSalary() {
        return maxSizeSalary;
    }

    public void setMaxSizeSalary(int maxSizeSalary) {
        this.maxSizeSalary = maxSizeSalary;
    }

    public Department(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {

        if (employee.getName().length()>getMaxSizeName()){
            setMaxSizeName(employee.getName().length());
        }
        if (employee.getSalary().toString().length()>getMaxSizeSalary()){
            setMaxSizeSalary(employee.getSalary().toString().length());
        }

        getEmployeeList().add(employee);
    }

    public BigDecimal getAvgSalaryWithOutEmployee(Employee employee){
        BigDecimal salary = getSalary();
        BigDecimal salaryWithOutEmployee = salary.subtract(employee.getSalary());
        BigDecimal avgSalaryWithOutEmployee = salaryWithOutEmployee.divide(BigDecimal.valueOf(getEmployeeList().size()-1),2,RoundingMode.HALF_UP);
        return avgSalaryWithOutEmployee;
    }

    public void removeLastEmployee() {
        if (getEmployeeList().isEmpty()) {
            return;
        }
        getEmployeeList().remove(getEmployeeList().size() - 1);
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

    public String print() {
        StringBuilder dataDepartment = new StringBuilder();
        dataDepartment.append("Department - ").append(getName()).append(" : ").append(" Average salary -  ").append(getAvgSalary()).append("\n");
        for (Employee employee : getEmployeeList()) {
            dataDepartment.append(employee.print(this));
        }
        dataDepartment.append("-------\n");
        return dataDepartment.toString();
    }


    public BigDecimal getSalary() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Employee employee : getEmployeeList()) {
            sum = sum.add(employee.getSalary());
        }
        return sum;
    }
}
