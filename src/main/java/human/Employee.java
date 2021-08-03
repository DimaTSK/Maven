package human;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;

public class Employee {
    private String name;
    private BigDecimal salary;

    public Employee(){

    }

    public Employee(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, String stringSalary) {
        this.name = name;
        this.salary = new BigDecimal(stringSalary).setScale(2, RoundingMode.HALF_UP);
    }


    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    String print(Department department) {
        return String.format("%-" + department.getMaxSizeName() + "s - %" + department.getMaxSizeSalary() + "s  %n", getName(), getSalary());

    }

    public String getName() {
        return name;
    }

}
