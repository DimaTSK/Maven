package company;

import human.Department;
import human.Employee;
import viewdepartments.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departments {
    private Map<String, Department> departments = new HashMap<>();


    public void addDepartmentsFromFile(String filePath, View view) {
       /* try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                if (split.length != 3) {
                    System.out.println("Format not support  - " + line);
                    continue;
                }
                String employeeName = split[0];
                String departmentName = split[1];
                BigDecimal salary = new BigDecimal(split[2]).setScale(2, RoundingMode.HALF_UP);
                if (employeeName.isEmpty()) {
                    System.out.println("employeeName - incorrect");
                    continue;
                }
                if (departmentName.isEmpty()) {
                    System.out.println("departmentName - incorrect");
                    continue;
                }
                if (salary.compareTo(BigDecimal.valueOf(0)) < 0) {
                    System.out.println("salary - incorrect");
                    continue;

                }
                Employee employee = new Employee(employeeName, salary);
                Department department = departments.get(departmentName);


                if (department == null) {
                    department = new Department(departmentName);
                    department.addEmployee(employee);
                    departments.put(departmentName, department);
                } else {
                    department.addEmployee(employee);
                }
            }

        } catch (FileNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
        ReadFileTxt readFileTxt = new ReadFileTxt();
        List<List<String>> list = readFileTxt.readFile(filePath);
        int employeeNameMaxSize = -1;
        for (List<String> list1 : list) {
            String employeeName = list1.get(0);
            String departmentName = list1.get(1);
            BigDecimal salary = new BigDecimal(list1.get(2)).setScale(2, RoundingMode.HALF_UP);
            Employee employee = new Employee(employeeName, salary);
            Department department = departments.get(departmentName);


            if (department == null) {
                department = new Department(departmentName);
                department.addEmployee(employee);
                departments.put(departmentName, department);
            } else {
                department.addEmployee(employee);
            }
            if (employeeName.length() > employeeNameMaxSize) {
                employeeNameMaxSize = employeeName.length();
            }

        }

        view.setMaxSize(employeeNameMaxSize);*/
    }

    public void addDepartment(List<String> list) {
        String employeeName = list.get(0);
        String departmentName = list.get(1);
        BigDecimal salary = new BigDecimal(list.get(2)).setScale(2, RoundingMode.HALF_UP);
        Employee employee = new Employee(employeeName, salary);
        Department department = departments.get(departmentName);


        departments.computeIfAbsent(departmentName, Department::new).addEmployee(employee);
      /*  if (department == null) {
            department = new Department(departmentName);
            department.addEmployee(employee);

            departments.put(departmentName, department);
        } else {
            department.addEmployee(employee);
        }*/

        /*Department department1 = departments.computeIfAbsent(departmentName, Department::new);
        department1.addEmployee(employee);*/


    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments.values());
    }
}