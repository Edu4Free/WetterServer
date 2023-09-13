package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import interfaces.WeatherData;

/**
 * Einfacher Java-Webserver
 */
public class Webserver {

    private HttpServer server = null;

    WeatherData weather = new MyDummyWeather();

    public Webserver() throws IOException
    {

    }

    /**
     * Starten des Webservers
     */
    public void startServer()
    {

        if(server == null) // Überprüfen, ob ein Serverobjekt existiert
        {
            try
            {
                // Server-Instanz erzeugen, unter ANgabe des zu verwendenen Ports (8000)
                server = HttpServer.create(new InetSocketAddress(8000), 0);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            // Server-Verzeichnis anklegen
            server.createContext("/weather", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }
    }

    /**
     * Server stoppen
     */
    public void stopServer()
    {
        // Server stoppen, wenn eine Serverinstanz existiert
        if(server != null)
        {
            server.stop(2);

            server = null; // Server-Referenz auf null setzen
        }
    }

    /**
     * Serveranfrage verarbeiten
     */
    class MyHandler implements HttpHandler
    {
        @Override
        public void handle(HttpExchange t) throws IOException
        {
            boolean test;
            String response;
            String country,city, data;
            int plz = 0;

            // Parameter der Get-Anfrage splitten und in HashMap ablegen
            HashMap<String, String> hm = getCommands(t.getRequestURI().toString());

            country = hm.get("country");
            city = hm.get("city");
            data = hm.get("data");
            plz = Integer.parseInt(hm.get("postcode"));

            // Durchführen der Wetteranfrage mit vorgegebenen Parametern
            test = weather.doRequest(country, plz, city, data);

            // response = weather.getCSVData();

            if(data.contains("all"))
                response = weather.getCSVData();
            else
                response = weather.getLastCSVData();

            // System.out.println("response="+response);

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    /**
     * * @param httpRequest
     * @return
     *
     * Get-Kommandos splitten und in Hashmap ablegen
     */
    private HashMap<String, String> getCommands(String httpRequest)
    {
        String arr[] = null, split[];
        HashMap<String, String> hm = new HashMap<String, String>();

        arr = httpRequest.substring(httpRequest.indexOf("?")+1).split("&");

        for(String s :arr)
        {
            split = s.split("=");
            hm.put(split[0], split[1]);
        }

        return hm;
    }
}