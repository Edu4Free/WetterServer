package interfaces;

import java.net.URL;

public interface WeatherData {

    public boolean doRequest(String country, int postcode, String city, String data);

    public String getCSVData();

    public String getLastCSVData();

    public String getJSONData();

    public String getLastJSONData();

    public String getXMLData();

    public String getLastXMLData();
}
