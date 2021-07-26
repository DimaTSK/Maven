package readfile;

import company.Departments;
import human.Employee;
import viewdepartments.PrintInfoDep;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;


public class ReadFileEmployees implements ReadFile {

    @Override
    public ReadFileInfo readFile(String filePath) {
        Departments departments = new Departments();
        StringBuilder dataErr = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                for (int i = 0; i < split.length; i++) {
                    split[i] = split[i].trim();
                }
                String sCheck=checkString(split);
                if (!sCheck.isEmpty()) {
                    dataErr.append(sCheck);
                    dataErr.append("\n");
                    continue;
                }

                departments.addDepartment(split[1]).addEmployee(new Employee(split[0], split[2]));


            }
        } catch (FileNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
        return new ReadFileInfo(departments, dataErr.toString());
    }

    public String checkString(String[] split) {
        if (split.length != 3) {
            return "Format not supported  - " + Arrays.asList(split);
        }

        BigDecimal salary;
        try {
            salary = new BigDecimal(split[2]);
        } catch (NumberFormatException e) {
            return "Incorrect format BigDecimal" + Arrays.asList(split);
        }
        BigDecimal check = new BigDecimal(split[2]).setScale(2, RoundingMode.FLOOR);

        if (split[0].trim().isEmpty()) {
            return "employeeName - incorrect :" + Arrays.asList(split);

        }
        if (split[1].trim().isEmpty()) {
            return "departmentName - incorrect : " + Arrays.asList(split);

        }
        if (salary.compareTo(BigDecimal.valueOf(0)) < 0) {
            return "salary - incorrect, negative : " + Arrays.asList(split);

        }
        if (salary.subtract(check).compareTo(BigDecimal.valueOf(0.001)) > 0) {
            return "salary - incorrect : " + Arrays.asList(split);

        }


        return "";
    }

}



