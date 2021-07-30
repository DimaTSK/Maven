package main;

import company.Departments;
import human.Candidate;
import human.Department;
import readfile.ReadFile;
import readfile.ReadFileEmployees;
import transference.Transference;
import transference.TransferenceGroup;
import viewdepartments.PrintInfoDep;
import writefile.WriteFileInfCompany;

import java.util.ArrayList;
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

        String fileoutput= "Output.txt";
        if(args.length>1) {
            fileoutput=args[1];
        }

        WriteFileInfCompany writeFileInfCompany = new WriteFileInfCompany(fileoutput);

        Departments departments=readFile.readFile(filePath,writeFileInfCompany);

        if (departments.getDepartments().isEmpty()) {
            System.out.println("Departments empty");
            return;
        }
        List<Candidate> candidates = Transference.findCandidates(departments);





        printInfoDep.viewDepartments(departments,writeFileInfCompany);
        Transference.moveCandidates(departments,candidates,writeFileInfCompany);

        List<Department> listDepRes = new ArrayList<>();
        for (Department department:departments.getDepartments()){
            listDepRes.addAll(TransferenceGroup.findCandidatesGroup(department));
        }

        TransferenceGroup.moveCandidates(departments,listDepRes,writeFileInfCompany);















        /*
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){

            bufferedWriter.append(readFileInfo.getErr());
            bufferedWriter.append(printInfoDep.viewDepartments(departments));
            bufferedWriter.append(Transference.moveCandidates(departments,candidates));
            bufferedWriter.flush();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
*/
       // System.out.println(readFileInfo.getErr());
      // System.out.println(printInfoDep.viewDepartments(departments));
     //   System.out.println(Transference.moveCandidates(departments, candidates));


    }

}
