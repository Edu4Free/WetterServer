package menu;

import server.Webserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuPanel extends JPanel implements ActionListener {

    private JTextArea textArea;

    private Webserver webserver = null;

    public MenuPanel(JTextArea textArea)
    {
        this.textArea = textArea;

        try { webserver = new Webserver(); }
        catch (IOException e) {  throw new RuntimeException(e); }

        JButton startServer = new JButton("Start Server");
        JButton stopServer = new JButton("Stop Server");
        JButton clear = new JButton("Clear");

        startServer.setFont(new Font("Dialog", Font.PLAIN, 14));
        stopServer.setFont(new Font("Dialog", Font.PLAIN, 14));
        clear.setFont(new Font("Dialog", Font.PLAIN, 14));

        startServer.addActionListener(this);
        stopServer.addActionListener(this);
        clear.addActionListener(this);

        add(startServer);
        add(stopServer);
        add(clear);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Button "+e.getActionCommand()+" gedrueckt");

        if(e.getActionCommand().contains("Start"))
        {
            try {
                webserver.startServer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            if(e.getActionCommand().contains("Stop"))
            {
                webserver.stopServer();
            }
            else
                if(e.getActionCommand().contains("Clear"))
                {
                    textArea.setText("Ausgaben:\n");
                }
    }
}
