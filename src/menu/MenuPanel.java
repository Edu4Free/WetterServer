package menu;

import server.Webserver;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 */
public class MenuPanel extends JPanel implements ActionListener {

    private JButton startServer, stopServer,clear;
    private Border defaultBorder;
    private JTextArea textArea;
    private JTextField textField;

    private int oldport, newport;

    private JComboBox<String> selectModel;

    private Webserver webserver = null;

    public MenuPanel(JTextArea textArea, JTextField textField)
    {
        this.textArea = textArea;
        this.textField = textField;

        try { webserver = new Webserver(); }
        catch (IOException e) {  throw new RuntimeException(e); }

        ButtonGroup buttonGroup = new ButtonGroup();

        JLabel portLabel = new JLabel("Port-Adresse");

        selectModel = new JComboBox<>(new String[]{"8000", "8080", "443", "81"});

        startServer = new JButton("Start Server");
        stopServer = new JButton("Stop Server");
        clear = new JButton("Clear");

        defaultBorder = startServer.getBorder();

        startServer.setOpaque(true);
        stopServer.setOpaque(true);
        clear.setOpaque(true);

        buttonGroup.add(startServer);
        buttonGroup.add(stopServer);

        portLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

        selectModel.setFont(new Font("Dialog", Font.PLAIN, 14));

        startServer.setFont(new Font("Dialog", Font.PLAIN, 14));
        stopServer.setFont(new Font("Dialog", Font.PLAIN, 14));
        clear.setFont(new Font("Dialog", Font.PLAIN, 14));

        selectModel.addActionListener(this);
        startServer.addActionListener(this);
        stopServer.addActionListener(this);
        clear.addActionListener(this);

        add(portLabel);
        add(selectModel);
        add(startServer);
        add(stopServer);
        add(clear);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Button "+e.getActionCommand()+" gedrueckt");

        if(e.getActionCommand().contains("Start") || e.getActionCommand().contains("Stop"))
        {
            startServer.setBorder(defaultBorder);
            stopServer.setBorder(defaultBorder);
            ((JButton)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.green, 3));
        }

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

        if(e.getActionCommand().contains("comboBoxChanged"))
        {
            Object obj = e.getSource();

            int portNumber = Integer.parseInt((String) ((JComboBox<String>)obj).getSelectedItem());

            if(webserver.isRunmode())
            {
                oldport = webserver.getPortAdress();

                System.out.println("Stoppen des Webservers");
                webserver.stopServer();

                System.out.println("Setzen des Serverports auf Nummer "+portNumber+".");
                webserver.setPortAdress(portNumber);
                newport = portNumber;

                System.out.println("Starten des Webservers");
                webserver.startServer();

                textField.setText(textField.getText().replaceAll(":"+oldport, ":"+newport));
            }
            else
                System.out.println("Server noch nicht gestartet");
        }

        repaint();
    }
}
