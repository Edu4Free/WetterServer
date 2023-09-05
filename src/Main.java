import menu.MenuPanel;
import view.CommandoPanel;
import view.OutputPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.net.UnknownHostException;

public class Main
{
    public Main()
    {
        LookAndFeel();

        String ipAdress = "";

        try {
            ipAdress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        JFrame maiframe = new JFrame("WetterApp | Local-IPadresse:"+ ipAdress);
        maiframe.setLayout(new BorderLayout());
        maiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maiframe.setSize(640, 480);

        OutputPanel outputpanel = new OutputPanel(ipAdress);
        //outputpanel.setHostAdress(ipAdress);

        maiframe.add(new MenuPanel(outputpanel.getTextArea()), BorderLayout.NORTH);
        maiframe.add(new JScrollPane(outputpanel), BorderLayout.CENTER);
        maiframe.add(new CommandoPanel(outputpanel.getTextArea(), ipAdress), BorderLayout.SOUTH);

        maiframe.setVisible(true);
    }
    public static void main(String[] args)
    {
        Main main = new Main();
    }

    private void LookAndFeel()
    {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
    }
}