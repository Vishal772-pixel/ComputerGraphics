import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hand extends JPanel implements ActionListener {
    private int handPositionX = 250; // Initial horizontal position of the hand
    private Timer timer;
    private int shakeDirection = 1; // Direction of the shake
    private boolean shaking = false; // Flag to control shaking

    public hand() {
        timer = new Timer(50, this); // Timer to update hand position
        setPreferredSize(new Dimension(600, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawHand(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight()); // Draw background
    }

    private void drawHand(Graphics g) {
        g.setColor(Color.BLUE);
        // Draw palm
        g.fillRoundRect(handPositionX, 200, 60, 30, 15, 15); // Palm

        // Draw fingers
        int fingerWidth = 10;
        int fingerHeight = 20;

        // Thumb
        g.fillRoundRect(handPositionX + 50, 200, fingerWidth, fingerHeight, 5, 5); // Thumb

        // Index finger
        g.fillRoundRect(handPositionX + 40, 180, fingerWidth, fingerHeight, 5, 5); // Index finger

        // Middle finger
        g.fillRoundRect(handPositionX + 30, 160, fingerWidth, fingerHeight, 5, 5); // Middle finger

        // Ring finger
        g.fillRoundRect(handPositionX + 20, 180, fingerWidth, fingerHeight, 5, 5); // Ring finger

        // Pinky finger
        g.fillRoundRect(handPositionX + 10, 200, fingerWidth, fingerHeight, 5, 5); // Pinky finger
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (shaking) {
            // Change position to create shaking effect
            handPositionX += shakeDirection;
            if (handPositionX > 260 || handPositionX < 240) {
                shakeDirection *= -1; // Reverse direction
            }
            repaint();
        }
    }

    public void startShaking() {
        shaking = true;
        timer.start(); // Start the shaking animation
    }

    public void stopShaking() {
        shaking = false;
        timer.stop(); // Stop the shaking animation
        handPositionX = 250; // Reset position
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shaking Hand Animation");
        hand shakingHandGUI = new hand();

        JButton shakeButton = new JButton("Shake Hand");
        shakeButton.addActionListener(e -> {
            shakingHandGUI.startShaking();
        });

        JButton stopButton = new JButton("Stop Shaking");
        stopButton.addActionListener(e -> {
            shakingHandGUI.stopShaking();
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(shakingHandGUI, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(shakeButton);
        buttonPanel.add(stopButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }
}
