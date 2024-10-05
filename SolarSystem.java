import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolarSystem extends JPanel implements ActionListener {
    private final Timer timer;
    private final int sunX = 500;  // Centered on a 1000x1000 panel
    private final int sunY = 500;  // Centered on a 1000x1000 panel
    private final int sunRadius = 50;

    // Planet radii for ellipses
    private final int[] planetRadiiX = {100, 130, 160, 190, 220, 250, 280, 310, 340, 370, 400, 430}; 
    private final int[] planetRadiiY = {80, 90, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300}; 
    private final double[] planetAngles = new double[12];
    
    // Planet names and sizes
    private final String[] planetNames = {
            "Mercury", "Venus", "Earth", "Mars", "Jupiter", 
            "Saturn", "Uranus", "Neptune", "Pluto", "Eris", 
            "Haumea", "Makemake"
    };
    
    private final int[] planetSizes = {8, 12, 14, 10, 24, 20, 18, 16, 6, 6, 6, 6}; // Sizes for the planets
    private final Color[] planetColors = {
            Color.GRAY, Color.ORANGE, Color.BLUE, Color.RED,
            Color.YELLOW, Color.CYAN, Color.LIGHT_GRAY, Color.DARK_GRAY,
            Color.PINK, Color.WHITE, Color.MAGENTA, Color.DARK_GRAY
    };

    private boolean starsVisible = true; // To control star visibility for blinking

    public SolarSystem() {
        timer = new Timer(50, this);
        timer.start();
        setPreferredSize(new Dimension(1000, 1000));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        if (starsVisible) {
            drawStars(g);
        }
        drawSun(g);
        drawPlanets(g);
        drawMeteoroids(g);
        drawTrajectories(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight()); // Dark background
    }

    private void drawStars(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            g.fillOval(x, y, 2, 2); // Small stars
        }
    }

    private void drawSun(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(sunX - sunRadius, sunY - sunRadius, sunRadius * 2, sunRadius * 2);
    }

    private void drawPlanets(Graphics g) {
        for (int i = 0; i < planetRadiiX.length; i++) {
            // Calculate position
            int planetX = (int) (sunX + planetRadiiX[i] * Math.cos(planetAngles[i]));
            int planetY = (int) (sunY + planetRadiiY[i] * Math.sin(planetAngles[i]));
            g.setColor(planetColors[i]);
            g.fillOval(planetX - planetSizes[i] / 2, planetY - planetSizes[i] / 2, planetSizes[i], planetSizes[i]); // Draw planets
            
            // Draw planet names
            g.setColor(Color.WHITE);
            g.drawString(planetNames[i], planetX - planetSizes[i] / 2, planetY - planetSizes[i] - 5); // Draw names above the planets

            // Update angles for rotation
            planetAngles[i] += (0.01 * (i + 1)); // Adjust speed of rotation
        }
    }

    private void drawMeteoroids(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) { // 10 meteoroids
            int meteoroidRadius = 3;
            int meteoroidX = (int) (Math.random() * getWidth());
            int meteoroidY = (int) (Math.random() * getHeight());
            g.fillOval(meteoroidX, meteoroidY, meteoroidRadius, meteoroidRadius); // Draw meteoroids
        }
    }

    private void drawTrajectories(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < planetRadiiX.length; i++) {
            g.drawOval(sunX - planetRadiiX[i], sunY - planetRadiiY[i], planetRadiiX[i] * 2, planetRadiiY[i] * 2); // Draw elliptical trajectories
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        starsVisible = !starsVisible; // Toggle star visibility for blinking
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System Simulation");
        SolarSystem solarSystemGUI = new SolarSystem();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(solarSystemGUI);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }
}
