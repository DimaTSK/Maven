package writefile;

import java.io.*;
import java.util.Scanner;

public class WriteFileInfCompany {
    private String filepath;

    public WriteFileInfCompany(String path) {
        this.filepath = path;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.append("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getFilepath() {
        return filepath;
    }

    public void writeFile(String text) {

        File file = new File(getFilepath());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {


            bufferedWriter.append(text);
            bufferedWriter.flush();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


}

