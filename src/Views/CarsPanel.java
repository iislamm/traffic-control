package Views;

import javax.swing.*;
import java.awt.*;

public class CarsPanel extends JPanel {
    private Color redColor;
    private Color greenColor;

    public CarsPanel(Color redColor, Color greenColor) {
        this.redColor = redColor;
        this.greenColor = greenColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        g.setColor(Color.BLACK);
        g.fillRect(200, 80, 70, 150);

        g.setColor(Color.BLACK);
        g.drawRect(200, 80, 70, 150);

        g.setColor(redColor);
        g.fillOval(210, 100, 50, 50);

        g.setColor(greenColor);
        g.fillOval(210, 160, 50, 50);
    }

    public Color getRedColor() {
        return redColor;
    }

    public void setRedColor(Color redColor) {
        this.redColor = redColor;
    }

    public Color getGreenColor() {
        return greenColor;
    }

    public void setGreenColor(Color greenColor) {
        this.greenColor = greenColor;
    }
}
