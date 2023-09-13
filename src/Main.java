import menu.MenuPanel;
import view.CommandoPanel;
import view.OutputPanel;

import javax.swing.*;
import java.awt.*;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class Main
{
    public Main()
    {
        LookAndFeel(); // System Look & Feel einstellen

        String ipAdress = ""; // IP Adresse des ausführenden Rechners

        // IP Adresse auslesen
        try {
            ipAdress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        //Hauptfenster
        JFrame maiframe = new JFrame("WetterApp | Local-IPadresse:"+ ipAdress);
        maiframe.setLayout(new BorderLayout());
        maiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maiframe.setSize(640, 480);

        // Panel (mitte) für die Ausgabe der Request-Ergebnisse
        OutputPanel outputpanel = new OutputPanel(ipAdress);
        //outputpanel.setHostAdress(ipAdress);

        maiframe.add(new MenuPanel(outputpanel.getTextArea()), BorderLayout.NORTH); // Panel mit drei Auswahl-Buttons
        maiframe.add(new JScrollPane(outputpanel), BorderLayout.CENTER);    // Outputpanel in Scrollfenster übergeben
        maiframe.add(new CommandoPanel(outputpanel.getTextArea(), ipAdress), BorderLayout.SOUTH); // Panel für COmmandozeilen

        maiframe.setVisible(true);  // Hauptfenster sichtbar schalten
    }
    public static void main(String[] args)
    {
        Main main = new Main();
    }

    private void LookAndFeel()
    {
        // Legt das System L&F fest
        try {UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName()); }
        catch (UnsupportedLookAndFeelException e) { }
        catch (ClassNotFoundException e) { }
        catch (InstantiationException e) { }
        catch (IllegalAccessException e) { }
    }
}