package readfile;

import company.Departments;
import human.Employee;
import viewdepartments.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReadFileTxt implements ReadFile {

    @Override
    public Departments readFile(String filePath, View view) {
        Departments departments = new Departments();
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                if (!checkFile(split, line)) {
                    continue;
                }

                departments.addDepartment(split[1]).addEmployee(new Employee(split[0],split[2]));


            }
        } catch (FileNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
        return departments;
    }

     public boolean checkFile(String [] split,String line) {

        if (split.length != 3) {
            System.out.println("Format not supported  - " + line);
            return false;
        }

        BigDecimal salary = new BigDecimal(split[2]);
        BigDecimal check = new BigDecimal(split[2]).setScale(2, RoundingMode.FLOOR);

        if (split[0].trim().isEmpty()) {
            System.out.println("employeeName - incorrect :" + line);
            return false;
        }
        if (split[1].isEmpty()) {
            System.out.println("departmentName - incorrect : " + line);
            return false;
        }
        if (salary.compareTo(BigDecimal.valueOf(0)) < 0) {
            System.out.println("salary - incorrect, negative : " + line);
            return false;
        }
        if (salary.subtract(check).compareTo(BigDecimal.valueOf(0.001)) > 0) {
            System.out.println("salary - incorrect : " + line);
            return false;
        }
        return true;
    }

}



