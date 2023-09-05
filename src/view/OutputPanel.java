package view;

import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel
{
     private String hostAdress = null;
    private JTextArea textArea;

    public OutputPanel()
    {
        init(null);
    }

    public OutputPanel(String hostAdress)
    {
        init(hostAdress);
    }

    public void init(String hostAdress)
    {
        setLayout(new GridLayout(1,1));

        this.hostAdress = hostAdress;

        textArea = new JTextArea();

        textArea.setText("Ausgaben:\n");
        textArea.setText(textArea.getText()+"HostAdress="+hostAdress+"\n");

        add(textArea);
    }

    public void setHostAdress(String hostAdress) {
        this.hostAdress = hostAdress;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
