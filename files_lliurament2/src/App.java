import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class App {

    public static void main(String[] args) throws Exception {

        File file = new File("testfile.html");
        //el nom del fitxer serà testfile.html


        String str = urlReader("http://inslaguineueta.cat");

        saveToFile(str, file, true);

        System.out.println(readFromFile(file));
        //per pantalla.
    }

    static String readFromFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bufr = new BufferedReader(fr);
        StringBuilder sbuild = new StringBuilder();
        String linea;
        while((linea = bufr.readLine()) != null){
            sbuild.append(linea);
        }
        bufr.close();
        return sbuild.toString();
    }

    static void saveToFile(String str, File file, boolean append) throws IOException {
        // FileWriter
        FileWriter fwW = new FileWriter(file, true);

        BufferedWriter bufw = new BufferedWriter(fwW);

        bufw.write(str);

        bufw.close();

    }

    public static String urlReader(String url) throws Exception {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
            return sb.toString();
    }

}

