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
}
