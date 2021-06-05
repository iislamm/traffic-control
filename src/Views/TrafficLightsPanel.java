package Views;

import javax.swing.*;
import java.awt.*;

public class TrafficLightsPanel extends JPanel {
    private PedestriansPanel pedestriansPanel;
    private CarsPanel carsPanel;
    private JPanel peachLabelPanel;
    private JLabel peachLabel;
    private JPanel carsLabelPanel;
    private JLabel carsLabel;
    private JPanel centralPanel;
    private JLabel topLabel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private Color carsRedColor;
    private Color carsGreenColor;
    private Color pedestrianRedColor;
    private Color pedestrianGreenColor;

    public TrafficLightsPanel() {
        carsRedColor = Color.GRAY;
        carsGreenColor = Color.GREEN;
        pedestrianRedColor = Color.RED;
        pedestrianGreenColor = Color.GRAY;


        pedestriansPanel = new PedestriansPanel(pedestrianRedColor, pedestrianGreenColor);
        carsPanel = new CarsPanel(carsRedColor, carsGreenColor);
        setLayout(new BorderLayout());

        peachLabelPanel = new JPanel();
        peachLabel = new JLabel("Pedestrian Traffic Light");
        peachLabelPanel.add(peachLabel);

        carsLabelPanel = new JPanel();
        carsLabel = new JLabel("Cars Traffic Light");
        carsLabelPanel.add(carsLabel);


        centralPanel = new JPanel(new GridLayout(1, 2));
        topLabel = new JLabel("Traffic Control Management System");
        topLabel.setForeground(new Color(138, 47, 47));
        topPanel = new JPanel();
        topPanel.add(topLabel);

        centralPanel.add(pedestriansPanel);
        centralPanel.add(carsPanel);

        add(centralPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(peachLabelPanel, BorderLayout.EAST);
        bottomPanel.add(carsLabelPanel, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public Color getCarsRedColor() {
        return carsRedColor;
    }

    public void setCarsRedColor(Color carsRedColor) {
        carsPanel.setRedColor(carsRedColor);
        this.carsRedColor = carsRedColor;
    }

    public Color getCarsGreenColor() {
        return carsGreenColor;
    }

    public void setCarsGreenColor(Color carsGreenColor) {
        carsPanel.setGreenColor(carsGreenColor);
        this.carsGreenColor = carsGreenColor;
    }

    public Color getPedestrianRedColor() {
        return pedestrianRedColor;
    }

    public void setPedestrianRedColor(Color pedestrianRedColor) {
        this.pedestrianRedColor = pedestrianRedColor;
        pedestriansPanel.setRedColor(pedestrianRedColor);
    }

    public Color getPedestrianGreenColor() {
        return pedestrianGreenColor;
    }

    public void setPedestrianGreenColor(Color pedestrianGreenColor) {
        this.pedestrianGreenColor = pedestrianGreenColor;
        pedestriansPanel.setGreenColor(pedestrianGreenColor);
    }

    public void updateTrafficLights() {
        pedestriansPanel.repaint();
        carsPanel.repaint();
    }
}
