package human;

public class Candidate {
    private Employee employee;
    private Department department;


    public Candidate(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getEmployee() {
        return employee;
    }


}
