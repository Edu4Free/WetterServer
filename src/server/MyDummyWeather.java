package server;

import interfaces.WeatherData;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyDummyWeather implements WeatherData
{
    private boolean request = false;
    private String country, city;
    private double temperature;
    private int postcode, humidity, windspeed;
    private String winddirection, timestamp;
    private String[] winddirectionlist = new String[] {"N", "NO", "O", "SO", "S", "SW", "W", "NW"};

    private ArrayList<String> weatherData = new ArrayList<String>();

    public void MyDummyWeather()
    {
        weatherData.add("country;postcode;town;temperature;humidity;windspeed;winddirection;timestamp");
    }

    @Override
    public boolean doRequest(String country, int postcode, String city, String data)
    {
        this.country = country;
        this.postcode = postcode;
        this.city = city;

        if(weatherData.size()==0)
            weatherData.add("country;postcode;town;temperature;humidity;windspeed;winddirection;timestamp");

        Timestamp currenttimestamp = new Timestamp( System.currentTimeMillis());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String value;

        temperature = ((int)((Math.random()*50 - 10)*100d)) / 100d; // 2 Nachkommastellen Runden
        humidity = (int)(Math.random()*100+1);
        windspeed = (int)(Math.random()*100+1);
        winddirection = winddirectionlist[(int)(Math.random()*8)];
        timestamp = sdf1.format(currenttimestamp);

        value = country+";"+postcode+";"+city+";"+temperature+";";
        value += humidity+"%;"+windspeed+";"+winddirection+";"+timestamp;

        weatherData.add(value);

        request = true;

        return request;
    }

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
