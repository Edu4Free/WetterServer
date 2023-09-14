package view;

import javax.swing.*;
import java.awt.*;

/**
 * Stellt die Ausgaben des Webserver in einer Textarea dar.
 */
public class OutputPanel extends JPanel
{
     private String hostAdress = null; // Speichert die Host-IP
    private JTextArea textArea; // Textrea für sie Anzeige der angefragten Wetterdaten

    // Kontsruktor ohne Host-IP Übergabe
    public OutputPanel()
    {
        init(null);
    }

    // Kontsruktor mit Host-IP Übergabe
    public OutputPanel(String hostAdress)
    {
        init(hostAdress);
    }

    /**
     * Initialieren des Panels zur Anzeige
     * @param hostAdress
     */
    public void init(String hostAdress)
    {
        setLayout(new GridLayout(1,1)); // Layout festlegen Gridlayout mit einer Zelle

        this.hostAdress = hostAdress;   // Hostadresse speichern

        textArea = new JTextArea(); // Objekt von TextArea erzeugen ...

        // ... und mit Inhalt füllen
        textArea.setText("Ausgaben:\n");
        textArea.setText(textArea.getText()+"HostAdress="+hostAdress+"\nPortAdress=8000");

        add(textArea); // Textarea in Panel einfügen
    }

    // IP-Adresse in hostdress schreiben
    public void setHostAdress(String hostAdress) {
        this.hostAdress = hostAdress;
    }

    // Zugriff auf Texarea
    public JTextArea getTextArea() {
        return textArea;
    }
}