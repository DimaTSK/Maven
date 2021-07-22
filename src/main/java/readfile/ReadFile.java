package readfile;

import company.Departments;
import viewdepartments.View;

public interface ReadFile {
     Departments readFile(String filePath, View view);
}
