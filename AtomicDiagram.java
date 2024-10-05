package Atomic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AtomicDiagram extends JPanel implements ActionListener {
    private final Timer timer;
    private final int atomRadius = 20;
    private final Point[] atoms;
    private final int[][] bonds; // Pairs of atoms to represent bonds
    private final Random random = new Random();

    // Colors and labels for different types of atoms
    private final Color[] atomColors = {Color.RED, Color.BLUE, Color.GREEN}; // Hydrogen, Oxygen, Nitrogen
    private final String[] atomLabels = {"H", "O", "N"};

    public AtomicDiagram() {
        // Define atom positions and bonds
        atoms = new Point[]{
            new Point(200, 300), // H
            new Point(400, 300), // O
            new Point(300, 200), // H
            new Point(300, 400)  // N
        };
        bonds = new int[][]{
            {0, 1}, // H-O bond
            {0, 2}, // H-H bond
            {1, 3}, // O-N bond
            {2, 3}  // H-N bond
        };

        timer = new Timer(50, this);
        timer.start();
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBonds(g);
        drawAtoms(g);
    }

    private void drawAtoms(Graphics g) {
        for (int i = 0; i < atoms.length; i++) {
            g.setColor(atomColors[i % atomColors.length]);
            g.fillOval(atoms[i].x - atomRadius, atoms[i].y - atomRadius, atomRadius * 2, atomRadius * 2);
            g.setColor(Color.WHITE);
            g.drawString(atomLabels[i % atomLabels.length], atoms[i].x - 5, atoms[i].y + 5); // Draw atom labels
        }
    }

    private void drawBonds(Graphics g) {
        g.setColor(Color.BLACK);
        for (int[] bond : bonds) {
            Point atom1 = atoms[bond[0]];
            Point atom2 = atoms[bond[1]];
            g.drawLine(atom1.x, atom1.y, atom2.x, atom2.y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveAtoms();
        repaint();
    }

    private void moveAtoms() {
        for (Point atom : atoms) {
            // Move atoms randomly within bounds
            atom.x += random.nextInt(5) - 2; // Move between -2 and 2
            atom.y += random.nextInt(5) - 2; // Move between -2 and 2
            
            // Keep atoms within the panel
            atom.x = Math.max(atomRadius, Math.min(atom.x, getWidth() - atomRadius));
            atom.y = Math.max(atomRadius, Math.min(atom.y, getHeight() - atomRadius));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Atomic Molecular Diagram");
        AtomicDiagram diagram = new AtomicDiagram();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(diagram);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }
}
