package human;

import java.math.BigDecimal;

public class Employee {
    private String name;
    private BigDecimal salary;

    public Employee(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

     void print(int maxSize) {
        System.out.format("%" + maxSize+ "s - %s %n", getName(), getSalary());
        //   System.out.println(employee.getName() + " " + " - " + employee.getSalary());
    }

    public String getName() {
        return name;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
