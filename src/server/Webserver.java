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

    private final int PORT_ADRESS = 8000;
    private HttpServer server = null;   // Server-Instanz

    private int port = PORT_ADRESS;

    private boolean runMode = false;

    WeatherData weather = new MyDummyWeather(); // Auslesen von (Dummy) Wetterdaten

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
                server = HttpServer.create(new InetSocketAddress(port), 0);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            // Server-Verzeichnis anklegen
            server.createContext("/weather", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();

            this.runMode = true;
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

            this.runMode = true;
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
            boolean test; // !! noch nicht weiter verwendet
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

            // Überprüfen, ob alle Daten oder nur die
            // letzte Anfrage zurückgegeben werden soll
            if(data.contains("all"))
                response = weather.getCSVData();
            else
                response = weather.getLastCSVData();

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

        // Get-Daten in Array ablegen
        arr = httpRequest.substring(httpRequest.indexOf("?")+1).split("&");

        // Einzelne Get-Parameter in Hashmap speichern
        for(String s :arr)
        {
            split = s.split("=");
            hm.put(split[0], split[1]);
        }

        return hm;
    }

    // Rückgabe des Webserver-Ports
    public int getPortAdress()
    {
        return this.server.getAddress().getPort();
    }

    public void setPortAdress(int port)
    {
        this.port = port;
    }

    public boolean isRunmode() { return runMode; }
}

