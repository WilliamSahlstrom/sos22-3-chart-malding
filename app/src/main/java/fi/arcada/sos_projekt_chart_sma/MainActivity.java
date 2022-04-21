package fi.arcada.sos_projekt_chart_sma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    LineChart chart;
    EditText textInput;
    String currency, datefrom, dateto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera view-variabler
        chart = (LineChart) findViewById(R.id.chart);
        // TEMPORÄRA VÄRDEN
        currency = "USD";
        datefrom = "2022-01-01";
        dateto = "2022-02-01";

        // Hämta växelkurser från API
        ArrayList<Double> currencyValues = getCurrencyValues(currency, datefrom, dateto);

        ArrayList<Double> temperatures = Statistics.getDataValues();
        ArrayList<Double> tempsSma = Statistics.sma(currencyValues, 3);
        ArrayList<Double> tempsSma2 = Statistics.sma(currencyValues, 10);

        ArrayList<ChartLine> chartLines = new ArrayList<>();
        chartLines.add(new ChartLine(temperatures, "Temperatur", Color.BLUE, 0));
        chartLines.add(new ChartLine(tempsSma, "SMA3", Color.GREEN, 3));
        chartLines.add(new ChartLine(tempsSma2, "SMA10", Color.RED, 10));

        createBetterMultilineGraph(chartLines);

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
    // Metod för att skapa testdata
    public static ArrayList<Double> getDataValues(ArrayList<Double> currencyValues){


        // Skapa ny arraylist för Double-värden
        ArrayList<Double> dataValues = new ArrayList<>();
        // Loopa igenom dataItems och spara endast värdena i den nya arrayListen
        for (double temp: currencyValues) {
            dataValues.add(temp);
            System.out.println(dataValues);
        }

        return dataValues;

    }



    /**
     * Bättre graf med riktiga klassobjekt som linjer!
     *
     * @param chartLines
     */
    public void createBetterMultilineGraph(ArrayList<ChartLine> chartLines) {
        List<ILineDataSet> dataSeries = new ArrayList<>();

        for (ChartLine chartLine: chartLines) {
            LineDataSet lineDataSet = new LineDataSet(chartLine.getEntries(), chartLine.getLabel());

            lineDataSet.setColor(chartLine.getColor());
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            dataSeries.add(lineDataSet);
        }

        LineData lineData = new LineData(dataSeries);
        chart.setData(lineData);
        chart.invalidate(); // refresh

    }


    public void createMultilineGraph(ArrayList<ArrayList<Double>> dataSets) {
        List<ILineDataSet> dataSeries = new ArrayList<>();
        List<Entry> entries;
        //int entryOffset =  0; // var på X ska linjen börja

        String[] labels = { "Temperatur", "SMA", "SMA2", "SMA3" };
        int[] colors = { Color.BLUE, Color.RED, Color.GREEN, Color.BLACK };

        LineDataSet lineDataSet;

        for (int i = 0; i < dataSets.size(); i++) {
            // Det dataset vi nu loopar
            ArrayList<Double> currentDataSet = dataSets.get(i);
            int entryOffset = dataSets.get(0).size() - currentDataSet.size();

            entries = new ArrayList<Entry>();
            for (int j = 0; j < currentDataSet.size(); j++) {
                // OBS i = yttre loopen, j = inre loopen
                entries.add(new Entry(j+entryOffset, currentDataSet.get(j).floatValue()));
            }

            lineDataSet = new LineDataSet(entries, labels[i]);
            lineDataSet.setColor(colors[i]);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            dataSeries.add(lineDataSet);
        }

        LineData lineData = new LineData(dataSeries);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }





}