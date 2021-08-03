package writefile;

import java.io.*;

public class WriteFileInfCompany {
    private BufferedWriter bufferedWriter;

    public WriteFileInfCompany(String path) {

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(path));
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void writeFile(String text) {
        try {
            bufferedWriter.append(text);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

