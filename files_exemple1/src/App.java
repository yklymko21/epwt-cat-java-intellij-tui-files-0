import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        File file = new File("output");
        writeToFileWithBuffer(file, "This file has been filled from Java\n");
        System.out.println(readFromFileWithBuffer(file));

    }

    static String readFromFileWithBuffer(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bufr = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = bufr.readLine()) != null){
            sb.append(line);
        }
        bufr.close();
        return sb.toString();
    }

    static void writeToFileWithBuffer(File file, String message) throws IOException{

        FileWriter fw = new FileWriter(file, true);

        BufferedWriter bufw = new BufferedWriter(fw);

        bufw.write(message);

        bufw.close();
    }
}
