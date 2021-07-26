package readfile;

import company.Departments;
import human.Department;

public class ReadFileInfo {
   private Departments departments;
   private String err;

    public ReadFileInfo(Departments departments, String err) {
        this.departments = departments;
        this.err = err;
    }

    public Departments getDepartments() {
        return departments;
    }

    public String getErr() {
        return err;
    }


}
