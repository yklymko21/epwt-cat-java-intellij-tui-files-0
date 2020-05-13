import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class App {

    public static void main(String[] args) throws Exception {

        //TODO Crear un fitxer amb la classe File
        //el nom del fitxer serà testfile.html


        String str = urlReader("http://inslaguineueta.cat");

        //TODO cridar la funció perquè guardi str al fitxer


        //TODO LLegir el contingut del fitxer i imprimir-lo
        //per pantalla.
    }

    static String readFromFile(File file) throws IOException {
        //TODO usant FileReader i BufferedReader
    }

    static void saveToFile(String str, File file, boolean append)
            throws IOException {
        //TODO usant FileWriter i BufferedWriter
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

