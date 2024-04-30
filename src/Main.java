import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Podaj ścieżkę do pliku treningowego:");
        Scanner scanner = new Scanner(System.in);
        String sciezkaPlikTreningowy = scanner.nextLine();
        Map<String, ArrayList<float[]>> mapaPlikTreningowy = OperacjePliki.plikNaMape(sciezkaPlikTreningowy);
        System.out.println("Podaj liczbę najbliższych sąsiadów:");
        int k = scanner.nextInt();
        scanner.nextLine();
        while(true){
            System.out.println("1. Klasyfikacja wszystkich obserwacji ze zbioru testowego.");
            System.out.println("2. Klasyfikacja pojedyńczej obserwacji.");
            System.out.println("3. Zmień K.");
            System.out.println("4. Wyjdź z programu.");
            int wyborMenu = scanner.nextInt();
            scanner.nextLine();
            switch(wyborMenu){
                case 1:
                    System.out.println("Podaj ścieżkę do pliku:");
                    String sciezkaPlikTestowy = scanner.nextLine();
                    Map<String, ArrayList<float[]>> mapaPlikTestowy = OperacjePliki.plikNaMape(sciezkaPlikTestowy);
                    ArrayList<String> listaPlikTestowy = OperacjePliki.plikNaListe(sciezkaPlikTestowy);
                    ArrayList<String> zaklasyfikowane = KNN.klasyfListyObs(mapaPlikTestowy,mapaPlikTreningowy,k);
                    for(String s: zaklasyfikowane){
                        System.out.println(s);
                    }
                    System.out.println("Prawidlowo zaklasyfikowano " + KNN.sprawdzDokladnosc(listaPlikTestowy,zaklasyfikowane) + "%.");
                    break;
                case 2:
                    System.out.println("Podaj wyniki obserwacji oddzielone przecinkami:");
                    String temp1 = scanner.nextLine();
                    String[] temp2 = temp1.split(",");
                    float[] wynikiObserwacji = new float[temp2.length];
                    for(int i = 0; i< wynikiObserwacji.length; i++){
                        wynikiObserwacji[i] = Float.parseFloat(temp2[i]);
                    }
                    System.out.println(KNN.klasyfObserwacji(wynikiObserwacji, k, mapaPlikTreningowy));
                    break;
                case 3:
                    System.out.println("Podaj nowe K:");
                    k = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 4:
                    scanner.close();
                    System.exit(1);
                    break;
                default:

            }

        }
    }
}