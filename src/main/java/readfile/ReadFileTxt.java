package readfile;

import company.Departments;
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
        int employeeNameMaxSize = -1;
        Departments departments = new Departments();
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                if (split.length != 3) {
                    System.out.println("Format not support  - " + line);
                    continue;
                }

                BigDecimal salary = new BigDecimal(split[2]);
                BigDecimal check = new BigDecimal(split[2]).setScale(2, RoundingMode.FLOOR);

                if (split[0].isEmpty()) {
                    System.out.println("employeeName - incorrect :" + line);
                    continue;
                }
                if (split[1].isEmpty()) {
                    System.out.println("departmentName - incorrect : " + line);
                    continue;
                }
                if (salary.compareTo(BigDecimal.valueOf(0)) < 0) {
                    System.out.println("salary - incorrect, negative : " + line);
                    continue;
                }
                if (salary.subtract(check).compareTo(BigDecimal.valueOf(0.001)) > 0) {
                    System.out.println("salary - incorrect : " + line);
                    continue;
                }


                List<String> oneLine = new ArrayList<>();
                oneLine.add(split[0]);
                oneLine.add(split[1]);
                oneLine.add(split[2]);
                departments.addDepartment(oneLine);

                if (split[0].length() > employeeNameMaxSize) {
                    employeeNameMaxSize = split[0].length();
                }

            }
        } catch (FileNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
        view.setMaxSize(employeeNameMaxSize);
        return departments;
    }

}



