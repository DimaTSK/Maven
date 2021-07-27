package writefile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileInfCompany {
    private String filepath;

    public WriteFileInfCompany(String path){
        this.filepath=path;
    }

    public String getFilepath() {
        return filepath;
    }
    public void writeFile(String text){

        File file = new File(getFilepath());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))){
            bufferedWriter.append(text);
            bufferedWriter.flush();



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
    }
