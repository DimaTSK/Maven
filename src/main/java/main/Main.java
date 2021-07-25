package main;

import company.Departments;
import human.Candidate;
import human.Employee;
import readfile.ReadFile;
import readfile.ReadFileTxt;
import transference.Transference;
import viewdepartments.View;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("The path to the file is not specified correctly");
            return;
        }
        String filePath = args[0];
        View view = new View();
        ReadFile readFile = new ReadFileTxt();

        Departments departments = readFile.readFile(filePath, view);

        if (departments.getDepartments().isEmpty()) {
            System.out.println("Departments empty");
            return;
        }

        System.out.println(view.viewDepartments(departments));
        List<Candidate> candidates = Transference.findCandidates(departments);
        Transference.moveCandidates(departments, candidates);


    }

}
