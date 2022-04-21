package fi.arcada.sos_projekt_chart_sma;

// POJO - Plain Old Java Object
public class DataItem {
    // Vi deklarerar attribut (instansvariablerna)
    double value;
    String name;

    // Konstruktorn: den metod vi kör för att skapa ett nytt DataItem-objekt
    public DataItem(String name, double value) {
        this.name = name;
        this.value = value;
    }
    // Getter: metod för att hämta ett värde
    public double getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

    // Setter: metod för att ändra ett värde
    public void setValue(double value) {
        this.value = value;
    }
}
