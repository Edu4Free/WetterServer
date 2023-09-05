package view;

import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel
{

    private JTextArea textArea;

    public OutputPanel()
    {
        setLayout(new GridLayout(1,1));

        textArea = new JTextArea("Ausgaben:\n");

        textArea.setText("Ausgaben:\n");

        add(textArea);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
