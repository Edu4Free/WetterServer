package server;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Class: HTTPClientConnection.java
 * @author Blauel
 *
 * Verbindet sich mit dem Server und stellt nach Aufforderung HTTP-Anfragen.
 * Die Serverantwort wird eingelesen und gespeichert
 */
public class HTTPClientConnection {

	private URL url = null;					// URL der Serververbindung
	private URLConnection con = null;		// Verbindungsobjekt zum Server
	private Scanner scan = null;			// Scanner-Objekt zum Einlesen der Server-Antworten

	/**
	 * Konstruktor mit Uebergabe der Serveradresse
	 */
//	public HTTPClientConnection(String hostname)
//	{
//		this.hostname = hostname;
//	}
	
	/**
	 * Vorbereitung der  Anfrage an den Server.
	 * Gibt Antwort des Servers als String zurueck
	 * @param cmd
	 * @return
	 */
	public synchronized String sendRequest(String cmd) {
		
		String retV = "";

		URI uri = null;

		// System.out.println("cmd="+cmd);

		try {
			 uri = new URI(cmd);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		try {
			url = uri.toURL(); // URL mit Anfrage-Zeichenkette anlegen
			
			retV = nextServerRequest(cmd);	// Anfrage-Methode aufrufen
		}
		catch (MalformedURLException e) { e.printStackTrace(); }

		return retV;	// Rueckgabe der Serverantwort
	}
	
	/**
	 * Sendet Anfrage an den Server und gibt Antwort aus String zurueck
	 * @param cmd
	 * @return
	 */
	private synchronized String nextServerRequest(String cmd)
	{
		StringBuffer str = new StringBuffer();	// Speichert die Serverantwort
		
		// Serververbindung herstellen & InputStream initialisieren
		try {
			con = url.openConnection();
						
			scan = new Scanner(con.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(scan!=null)	// Fehlermeldung durch Unterbrechung des Servers abfangen
		{
			while(scan.hasNext())
			{
				str.append(scan.nextLine()+"\n");
			}
			
			scan.close();	// InputStream schliessen
		}
		
		con = null;			// Verbindungsobjekt auf null setzen

		return str.toString();	// Rueckgabe der Serverantwort
	}
}
