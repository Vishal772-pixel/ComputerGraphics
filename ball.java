import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ball extends JPanel implements ActionListener {
    private final Timer timer;
    private int ballX = 100; // Ball's initial X position
    private int ballY = 100; // Ball's initial Y position
    private int ballDiameter = 30; // Diameter of the ball
    private int ballXSpeed = 2; // Ball's speed in X direction
    private int ballYSpeed = 2; // Ball's speed in Y direction

    public ball() {
        timer = new Timer(10, this);
        timer.start();
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBall(g);
    }

    private void drawBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update ball position
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Bounce off the walls
        if (ballX < 0 || ballX > getWidth() - ballDiameter) {
            ballXSpeed = -ballXSpeed; // Reverse direction in X
        }
        if (ballY < 0 || ballY > getHeight() - ballDiameter) {
            ballYSpeed = -ballYSpeed; // Reverse direction in Y
        }

        repaint(); // Repaint the panel
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        ball bouncingBall = new ball();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(bouncingBall);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }
}

