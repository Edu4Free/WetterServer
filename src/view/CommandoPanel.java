package view;

import server.HTTPClientConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CommandoPanel extends JPanel implements KeyListener {
    private JTextField textField;
    private JTextArea textArea;

    public CommandoPanel(JTextArea textArea)
    {
        this.textArea = textArea;

        setLayout(new GridLayout(1,1));

        textField = new JTextField("http://localhost:8000/weather?country=DE&postcode=50679&city=Koeln&data=all");
        // textField = new JTextField("http://www.it-host.de/jserv/java/index.jsp?zugriff=wvsfst");
        // http://www.it-host.de/jserv/java/index.jsp?zugriff=wvsfst

        textField.addKeyListener(this);

        add(textField);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println("keyPressed");

        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            System.out.println("ENTER");

            HTTPClientConnection connection = new HTTPClientConnection();

            System.out.println(textField.getText());

            String result = connection.sendRequest(textField.getText());

            // result = result.replace("\n", "\n\r");

            textArea.setText(textArea.getText()+"/"+result+"\n");
            // textArea.setText(textArea.getText()+"/"+result+"\n");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
