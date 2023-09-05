package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BreakoutPong extends JPanel implements ActionListener, MouseMotionListener {
    private int ballX = 200, ballY = 100, ballVelX = 2, ballVelY = 2, ballDiameter = 15;
    private int paddleX = 100, paddleY = 300, paddleWidth = 100, paddleHeight = 20;
    private Timer timer;

    public BreakoutPong() {
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseMotionListener(this);  // MouseMotionListener hinzufügen
        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Zeichne Ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);

        // Zeichne Paddle
        g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Breakout Pong");
        BreakoutPong breakoutPong = new BreakoutPong();
        frame.add(breakoutPong);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ballbewegung
        ballX += ballVelX;
        ballY += ballVelY;

        // Wandkollision
        if (ballX < 0 || ballX > this.getWidth() - ballDiameter) ballVelX = -ballVelX;
        if (ballY < 0 || ballY > this.getHeight() - ballDiameter) ballVelY = -ballVelY;

        // Paddle-Kollision
        if (ballX > paddleX && ballX < paddleX + paddleWidth &&
                ballY > paddleY && ballY < paddleY + paddleHeight) {
            ballVelY = -ballVelY;
        }

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddleX = e.getX() - paddleWidth / 2;  // Zentriert das Paddle an der Mausposition
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Nicht implementiert
    }
}
