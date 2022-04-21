package fi.arcada.sos_projekt_chart_sma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Statistics {

    public static ArrayList<Double> sma(ArrayList<Double> dataset, int window) {
        ArrayList<Double> ma = new ArrayList<>();

        for (int i = window-1; i < dataset.size(); i++) {
            double sum = 0.0;

            for (int j = 0; j < window; j++) {
                sum += dataset.get(i - j); // t.ex. i:2 blir 2-0, 2-1, 2-2
            }
            ma.add(sum / window); // Medelvärde
        }
        return ma;
    }

    // Metod för att skapa testdata
    public static ArrayList<Double> getDataValues() {

        Double[] temps = { -4.7, -4.8, -1.8,  0.7,  0.1, -6.0, -7.8, -7.0, -3.8, -10.6, -10.3, -0.3, 4.8, 2.6,  0.1, 1.2, -1.5, -2.7,  1.8,  0.2, -2.0, -5.5, -1.3,  2.1, -0.6, -0.9,  1.0, -0.5, -1.4, -1.6, -5.3 };
        //Double[] sma =   {                   -2.0, -0.3, -1.7, -4.6, -6.9, -6.2, -7.1, -8.2, -7.1, -1.9,  2.4,  2.5, 1.3, -0.1, -1.0, -0.8, -0.2,  0.0, -2.4, -2.9, -1.6,  0.1,  0.2, -0.2, -0.1, -0.3, -1.2, -2.8 };


        // Skapa ny arraylist för Double-värden
        ArrayList<Double> dataValues = new ArrayList<>();
        // Loopa igenom dataItems och spara endast värdena i den nya arrayListen
        for (double temp: temps) {
            dataValues.add(temp);
        }
        return dataValues;
    }



}
