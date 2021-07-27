package readfile;

import company.Departments;
import human.Department;
import human.Employee;
import viewdepartments.PrintInfoDep;
import writefile.WriteFileInfCompany;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;


public class ReadFileEmployees implements ReadFile {

    @Override
    public Departments readFile(String filePath, WriteFileInfCompany writeFileInfCompany) {
        Departments departments = new Departments();
            try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(";");
                for (int i = 0; i < split.length; i++) {
                    split[i] = split[i].trim();
                }
                boolean sCheck=checkString(split,writeFileInfCompany);
                if (!sCheck) {
                     writeFileInfCompany.writeFile("\n");
                    continue;
                }

                departments.addDepartment(split[1]).addEmployee(new Employee(split[0], split[2]));


            }
        } catch (FileNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
        }
        return departments;
    }

    public boolean checkString(String[] split,WriteFileInfCompany writeFileInfCompany) {
        if (split.length != 3) {
            writeFileInfCompany.writeFile("Format not supported  - " + Arrays.asList(split));
            return false;
        }

        BigDecimal salary;
        try {
            salary = new BigDecimal(split[2]);
        } catch (NumberFormatException e) {
            writeFileInfCompany.writeFile("Incorrect format BigDecimal" + Arrays.asList(split));
            return false;
        }
        BigDecimal check = new BigDecimal(split[2]).setScale(2, RoundingMode.FLOOR);

        if (split[0].trim().isEmpty()) {
            writeFileInfCompany.writeFile("employeeName - incorrect :" + Arrays.asList(split));
            return false;

        }
        if (split[1].trim().isEmpty()) {
            writeFileInfCompany.writeFile("departmentName - incorrect : " + Arrays.asList(split));
            return false;

        }
        if (salary.compareTo(BigDecimal.valueOf(0)) < 0) {
            writeFileInfCompany.writeFile("salary - incorrect, negative : " + Arrays.asList(split));
            return false;

        }
        if (salary.subtract(check).compareTo(BigDecimal.valueOf(0.001)) > 0) {
            writeFileInfCompany.writeFile( "salary - incorrect : " + Arrays.asList(split));
            return false;

        }


        return true;
    }

}



