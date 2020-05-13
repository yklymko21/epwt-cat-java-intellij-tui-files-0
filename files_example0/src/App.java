import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        File file = new File("output");
        writeToFile(file, "This file has been filled from Java\n");
        System.out.println(readFromFile(file));

    }

    static String readFromFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        StringBuilder sb = new StringBuilder();
        int c;
        while((c = fr.read()) != -1){
            sb.append((char)c);
        }
        fr.close();
        return sb.toString();
    }

    static void writeToFile(File file, String message) throws IOException{

        FileWriter fw = new FileWriter(file, true);

        fw.write(message);

        fw.close();
    }
}
