package fi.arcada.sos_projekt_chart_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String currency, datefrom, dateto;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = (Linechart) findViewById(R.id.chart);

        // TEMPORÄRA VÄRDEN
        currency = "USD";
        datefrom = "2022-01-01";
        dateto = "2022-02-01";

        // Hämta växelkurser från API
        ArrayList<Double> currencyValues = getCurrencyValues(currency, datefrom, dateto);
        // Skriv ut dem i konsolen
        System.out.println(currencyValues.toString());


        ArrayList<Double> temperatures = Statistics.getDataValues();
        System.out.println(temperatures);
        ArrayList<Double> tempSma = Statistics.sma(temperatures, 3);
        System.out.println(tempSma);

        createChart(temperatures);
    }


    // Färdig metod som hämtar växelkursdata
    public ArrayList<Double> getCurrencyValues(String currency, String from, String to) {

        CurrencyApi api = new CurrencyApi();
        ArrayList<Double> currencyData = null;

        String urlString = String.format("https://api.exchangerate.host/timeseries?start_date=%s&end_date=%s&symbols=%s",
                from.trim(),
                to.trim(),
                currency.trim());

        try {
            String jsonData = api.execute(urlString).get();

            if (jsonData != null) {
                currencyData = api.getCurrencyData(jsonData, currency.trim());
                Toast.makeText(getApplicationContext(), String.format("Hämtade %s valutakursvärden från servern", currencyData.size()), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Kunde inte hämta växelkursdata från servern: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return currencyData;
    }
    public void createChart(ArrayList<Double> dataSet) {
        List<Entry> entries = new ArrayList<Entry>();

        for (Double val: dataSet) {
            for (int i = 0; i < ; i++) {
                entries.add(new Entry(i, dataSet.get(i).floatValue()));
            }

            LineDataSet lineDataSet = new LineDataSet(entries, "Temperatur");
            lineData lineData = new lineData(lineDataSet);

            chart.setData(lineData);
            chart.invalidate();
        }
    }

}