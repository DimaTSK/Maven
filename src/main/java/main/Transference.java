package main;

import company.Departments;
import human.Employee;
import readfile.ReadFile;
import readfile.ReadFileTxt;
import viewdepartments.View;

import java.util.List;


public class Transference {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Invalid Java program file name");
            System.exit(0);
        }
        String filePath = args[0];
        View view = new View();
        ReadFile readFile = new ReadFileTxt();

        Departments departments = readFile.readFile(filePath, view);

        if (departments.getDepartments().isEmpty()) {
            System.out.println("Departments empty");
            System.exit(0);
        }

        view.viewDepartments(departments);
        List<Employee> candidates = View.findCandidates(departments);
        View.moveCandidates(departments, candidates);


    }

}
