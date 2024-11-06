package tool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class Mole {
    int x, y;      // Position of the mole
    int dx, dy;    // Speed and direction for x and y
    Image img;

    Mole(int x, int y) {
        this.x = x;
        this.y = y;

        // Set random speed and direction between 1 and 2 for each mole
        this.dx = (int)(Math.random() * 2 + 1) * (Math.random() > 0.5 ? 1 : -1);
        this.dy = (int)(Math.random() * 2 + 1) * (Math.random() > 0.5 ? 1 : -1);

        ImageIcon icon = new ImageIcon("E:\\Документы\\2학기\\객체지향프로그래밍\\mole.png");
        img = icon.getImage();
    }

    public void move(int panelWidth, int panelHeight) {
        x += dx;
        y += dy;

        // Reverse direction when hitting the wall
        if (x < 0 || x > panelWidth - 40) dx = -dx;
        if (y < 0 || y > panelHeight - 40) dy = -dy;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public boolean contains(int mx, int my) {
        return mx >= x && mx <= x + 40 && my >= y && my <= y + 40;
    }
}

public class MoleGame extends JFrame implements ActionListener {
    ArrayList<Mole> moles = new ArrayList<>();
    MyPanel panel;
    int caughtMoles = 0;
    boolean gameOver = false;

    class MyPanel extends JPanel {
        public MyPanel() {
            setBackground(Color.white);

            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (gameOver) return; // Ignore clicks if the game is over

                    int mx = e.getX();
                    int my = e.getY();

                    // Check if a mole is clicked
                    for (int i = 0; i < moles.size(); i++) {
                        if (moles.get(i).contains(mx, my)) {
                            moles.remove(i);
                            caughtMoles++;
                            if (caughtMoles == 10) {
                                gameOver = true; // Set game over flag
                            }
                            break;
                        }
                    }
                    repaint();
                }
            });
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw moles if game is not over
            if (!gameOver) {
                for (Mole m : moles) {
                    m.draw(g);
                }
            } else {
                // Display "Game over!!!" message if game is over
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.setColor(Color.BLACK);
                g.drawString("Game over!!!", 200, 200);
            }
        }
    }

    public MoleGame() {
        panel = new MyPanel();
        panel.setPreferredSize(new Dimension(600, 400));
        add(panel);
        pack();
        setTitle("Mole Catch Game");
        for (int i = 0; i < 10; i++) {
            moles.add(new Mole(getRand(600 - 40), getRand(400 - 40)));
        }
        Timer timer = new Timer(10, this);
        timer.start();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    int getRand(int range) {
        return (int) (Math.random() * range + 0);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (!gameOver) {
            for (Mole m : moles) {
                m.move(panel.getWidth(), panel.getHeight());
            }
            repaint();
        }
    }

    public static void main(String[] args) {
        new MoleGame();
    }
}
