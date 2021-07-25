package human;

public class Candidate {
    private Employee employee;
    private String departmentName;

    public Candidate(Employee employee, String departmentName) {
        this.employee = employee;
        this.departmentName = departmentName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
