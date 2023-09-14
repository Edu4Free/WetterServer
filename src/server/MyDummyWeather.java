package server;

import interfaces.WeatherData;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Erzeugen von Dummy-Wetterdaten
 */
public class MyDummyWeather implements WeatherData
{
    private boolean request = false;
    private String country, city;
    private double temperature;
    private int postcode, humidity, windspeed;
    private String winddirection, timestamp;
    private String[] winddirectionlist = new String[] {"N", "NO", "O", "SO", "S", "SW", "W", "NW"};

    // ArrayList (String) für mit Semikolon getrennte Wetterdaten (CSV-Format)
    private ArrayList<String> weatherData = new ArrayList<String>();

    public void MyDummyWeather()
    {
        // Zeile 0 der CSV-Daten (Spalten-Namen)
        weatherData.add("country;postcode;town;temperature;humidity;windspeed;winddirection;timestamp");
    }

    // Erzeugen neuer Wetterdaten und Ablegen in Arraylist
    @Override
    public boolean doRequest(String country, int postcode, String city, String data)
    {
        this.country = country;
        this.postcode = postcode;
        this.city = city;

        String value; // Zwischenspeiher für neuen Wetterdaten-Datensatz

        // Wenn Zeile 0 nicht existent, dann erzeugen und in Arraylist speichern
        if(weatherData.size()==0)
            weatherData.add("country;postcode;town;temperature;humidity;windspeed;winddirection;timestamp");

        // Objekt für Zeitstempel
        Timestamp currenttimestamp = new Timestamp( System.currentTimeMillis());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        // Temperatur erzeugen unf auf 2 Nachkommastellen Runden
        temperature = ((int)((Math.random()*50 - 10)*100d)) / 100d;

        // Luftfeuchtigkeit
        humidity = (int)(Math.random()*100+1);

        // Windgeschwindigkeit
        windspeed = (int)(Math.random()*100+1);

        // Windrichtung
        winddirection = winddirectionlist[(int)(Math.random()*8)];

        // Zeitstempel auslesen
        timestamp = sdf1.format(currenttimestamp);

        // Neue Zeile für CSV-Datei erzeugen...
        value = country+";"+postcode+";"+city+";"+temperature+";";
        value += humidity+"%;"+windspeed+";"+winddirection+";"+timestamp;

        // ...und in Arraylist speichern
        weatherData.add(value);

        request = true;

        return request;
    }

    // Letzten Datensatz zurückliefern
    @Override
    public String getLastCSVData()
    {
        String retValue = null;

        if(weatherData.size() > 1)
        {
            retValue = weatherData.get(0) +"\n";
            retValue += weatherData.get(weatherData.size()-1);
        }

        return retValue;
    }

    // Alle Datensätze zurückliefern
    @Override
    public String getCSVData()
    {
        String retValue = null;

        if (weatherData.size() > 1)
        {
            retValue = "";

            for (String s : weatherData)
            {
                System.out.println(s);
                retValue += "\n" + s;
            }

        }
        return retValue;
    }

    @Override
    public String getJSONData() {
        return null;
    }

    @Override
    public String getLastJSONData() {
        return null;
    }

    @Override
    public String getXMLData() {
        return null;
    }

    @Override
    public String getLastXMLData() {
        return null;
    }
}
