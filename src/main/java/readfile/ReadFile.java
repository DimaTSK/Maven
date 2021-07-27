package readfile;

import company.Departments;
import viewdepartments.PrintInfoDep;
import writefile.WriteFileInfCompany;

public interface ReadFile {
     Departments readFile(String filePath, WriteFileInfCompany writeFileInfCompany);
}
