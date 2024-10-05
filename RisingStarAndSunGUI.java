import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RisingStarAndSunGUI extends JPanel implements ActionListener {
    private int starYPosition = 300; // Initial position of the star
    private int sunYPosition = 50;    // Initial position of the sun
    private int birdXPosition1 = 0;   // Initial position of the first bird
    private int birdXPosition2 = -100; // Initial position of the second bird
    private int cloudXPosition = 600;  // Initial position of the cloud
    private Timer timer;

    public RisingStarAndSunGUI() {
        timer = new Timer(20, this); // Timer to update the positions
        timer.start();
        setPreferredSize(new Dimension(600, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMorningSky(g);
        drawSun(g);
        drawStar(g);
        drawBird(g, birdXPosition1, 150); // Draw first bird
        drawBird(g, birdXPosition2, 180); // Draw second bird
        drawCloud(g);
    }

    private void drawMorningSky(Graphics g) {
        // Create a gradient background for the morning sky
        for (int i = 0; i < getHeight(); i++) {
            float ratio = (float) i / getHeight();
            int red = (int) (255 * ratio); // Transition from blue to orange
            int green = (int) (100 * ratio);
            int blue = (int) (200 * (1 - ratio));
            g.setColor(new Color(red, green, blue));
            g.drawLine(0, i, getWidth(), i);
        }
    }

    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(400, sunYPosition, 80, 80); // Draw the sun
    }

    private void drawStar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{300, 290, 320, 280, 310, 300},
                      new int[]{starYPosition, starYPosition + 40, starYPosition + 40, starYPosition + 20, starYPosition + 20, starYPosition}, 6);
    }

    private void drawBird(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        // Draw a simple bird (two wings)
        g.drawLine(x, y, x + 10, y - 5);
        g.drawLine(x + 10, y - 5, x + 20, y);
    }

    private void drawCloud(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(cloudXPosition, 80, 100, 50); // Draw cloud base
        g.fillOval(cloudXPosition + 20, 60, 100, 50); // Draw cloud puff
        g.fillOval(cloudXPosition + 40, 80, 100, 50); // Draw another cloud puff
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move the star up
        if (starYPosition > 50) {
            starYPosition -= 1;
        }

        // Simulate sun rising
        if (sunYPosition < 100) {
            sunYPosition += 1;
        }

        // Move the birds across the screen
        birdXPosition1 += 2;
        birdXPosition2 += 3; // Faster for the second bird
        if (birdXPosition1 > getWidth()) {
            birdXPosition1 = 0; // Reset position when it goes off screen
        }
        if (birdXPosition2 > getWidth()) {
            birdXPosition2 = -100; // Reset position for the second bird
        }

        // Move the cloud across the screen
        cloudXPosition -= 1;
        if (cloudXPosition < -100) {
            cloudXPosition = getWidth(); // Reset position when it goes off screen
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rising Star and Sun Morning Scene");
        RisingStarAndSunGUI risingStarAndSunGUI = new RisingStarAndSunGUI();

        JButton button = new JButton("Reset");
        button.addActionListener(e -> {
            risingStarAndSunGUI.starYPosition = 300; // Reset the star position
            risingStarAndSunGUI.sunYPosition = 50;   // Reset the sun position
            risingStarAndSunGUI.birdXPosition1 = 0;  // Reset first bird position
            risingStarAndSunGUI.birdXPosition2 = -100; // Reset second bird position
            risingStarAndSunGUI.cloudXPosition = 600; // Reset cloud position
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(risingStarAndSunGUI, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }
}
