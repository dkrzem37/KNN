import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OperacjePliki {
    public static Map<String, ArrayList<float[]>> plikNaMape(String path){
        Map<String, ArrayList<float[]>> mapaPliku = new HashMap<>();
        try {
            File plik = new File(path);
            Scanner scanner = new Scanner(plik);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] dane = line.split(",");

                float[] f = new float[dane.length - 1];
                for(int i = 0; i< f. length; i++){
                    f[i] = Float.parseFloat(dane[i]);
                }

                if(!mapaPliku.containsKey(dane[dane.length - 1])){
                    mapaPliku.put(dane[dane.length - 1], new ArrayList<>());
                    mapaPliku.get(dane[dane.length - 1]).add(f);
                }else{
                    //create a vector
                    mapaPliku.get(dane[dane.length - 1]).add(f);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return mapaPliku;
    }

    public static ArrayList<String> plikNaListe(String path){
        ArrayList<String> temp = new ArrayList<>();
        try {
            File plik = new File(path);
            Scanner scanner = new Scanner(plik);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                temp.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }
}
