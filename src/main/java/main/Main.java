package main;

import company.Departments;
import human.Candidate;
import readfile.ReadFile;
import readfile.ReadFileEmployees;
import readfile.ReadFileInfo;
import transference.Transference;
import viewdepartments.PrintInfoDep;

import java.io.*;
import java.util.List;


public class Main {

    public static void main(String[] args)  {


        if (args.length == 0) {
            System.out.println("The path to the file is not specified correctly");
            return;
        }
        String filePath = args[0];
        PrintInfoDep printInfoDep = new PrintInfoDep();
        ReadFile readFile = new ReadFileEmployees();

        ReadFileInfo readFileInfo  = readFile.readFile(filePath);
        Departments departments=readFileInfo.getDepartments();

        if (departments.getDepartments().isEmpty()) {
            System.out.println("Departments empty");
            return;
        }
        List<Candidate> candidates = Transference.findCandidates(departments);

        String fileoutput= "Output.txt";
        if(args.length>1) {
            fileoutput=args[1];
        }
            File file = new File(fileoutput);


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){

            bufferedWriter.append(readFileInfo.getErr());
            bufferedWriter.append(printInfoDep.viewDepartments(departments));
            bufferedWriter.append(Transference.moveCandidates(departments,candidates));
            bufferedWriter.flush();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }







        System.out.println(readFileInfo.getErr());
        System.out.println(printInfoDep.viewDepartments(departments));
        System.out.println(Transference.moveCandidates(departments, candidates));


    }

}
