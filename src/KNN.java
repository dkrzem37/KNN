import java.util.*;

public class KNN {

    public static String klasyfObserwacji (float[] wynikiObserwacji, int k, Map<String, ArrayList<float[]>> mapaTreningowa){
        ArrayList<ObserwacjaNode> arrayObserwacji = new ArrayList<>();
        for(Map.Entry<String, ArrayList<float[]>> entry : mapaTreningowa.entrySet()){
            for(float[] tab : entry.getValue()){
                float odleglosc = 0;
                for(int i = 0; i< tab.length; i++){
                    odleglosc += Math.pow(wynikiObserwacji[i] - tab[i],2);
                }
                ObserwacjaNode on = new ObserwacjaNode(entry.getKey(), tab, odleglosc);
                arrayObserwacji.add(on);
            }
        }
        arrayObserwacji.sort(new Comparator<ObserwacjaNode>() {
            @Override
            public int compare(ObserwacjaNode o1, ObserwacjaNode o2) {
                if (o1.getOdleglosc() < o2.getOdleglosc()) {
                    return -1;
                } else if (o1.getOdleglosc() > o2.getOdleglosc()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        Map<String, Integer> liczbaWystapien = new HashMap<>();
        for(int i = 0; (i< k) && (i < arrayObserwacji.size()); i++){

            if (liczbaWystapien.containsKey(arrayObserwacji.get(i).getEtykieta())) {
                liczbaWystapien.put(arrayObserwacji.get(i).getEtykieta(), liczbaWystapien.get(arrayObserwacji.get(i).getEtykieta()) + 1);
            } else {
                liczbaWystapien.put(arrayObserwacji.get(i).getEtykieta(), 1);
            }
        }
        String najWystapienString = null;
        int najWystapienInt = 0;
        for(Map.Entry<String, Integer> entry : liczbaWystapien.entrySet()){
            if(entry.getValue() > najWystapienInt){
                najWystapienString = entry.getKey();
                najWystapienInt = entry.getValue();
            }
        }
        StringBuilder sb = new StringBuilder();
        for(float f: wynikiObserwacji){
            sb.append(f);
            sb.append(",");
        }
        sb.append(najWystapienString);
        return sb.toString();
    }

    public static ArrayList<String> klasyfListyObs(Map<String, ArrayList<float[]>> mapaPlikTestowy, Map<String, ArrayList<float[]>> mapaTreningowa, int k){
        ArrayList<String> temp = new ArrayList<>();
        for(Map.Entry<String, ArrayList<float[]>> entry : mapaPlikTestowy.entrySet()){
            for(float[] tab : entry.getValue()){
                temp.add(klasyfObserwacji(tab, k, mapaTreningowa));
            }
        }
        return temp;
    }

    public static float sprawdzDokladnosc(ArrayList<String> listaPlikTestowy, ArrayList<String> zaklasyfikowane){
        int prawidlowo = 0;
        int wszystkie = 0;
        for(String s: zaklasyfikowane){
            if(listaPlikTestowy.contains(s)){
                prawidlowo++;
            }
            wszystkie++;
        }
        return ((float)prawidlowo/(float)wszystkie) * 100;
    }
}
