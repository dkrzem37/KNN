public class ObserwacjaNode{
    private String etykieta;
    private float odleglosc;
    private float[] features;

    public ObserwacjaNode(String etykieta, float[] features, float odleglosc) {
        this.etykieta = etykieta;
        this.features = features;
        this.odleglosc = odleglosc;
    }

    public float getOdleglosc() {
        return odleglosc;
    }

    public String getEtykieta() {
        return etykieta;
    }
}
