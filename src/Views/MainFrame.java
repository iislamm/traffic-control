package Views;

import Controllers.TrafficLightController;
import Events.PedestrianRequestEvent;
import Events.SuspensionEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TrafficLightsPanel trafficLightsPanel;
    private JPanel mainPanelContainer;
    private JPanel mainPanel;
    private JPanel pedestrianPanel;
    private JPanel carsPanel;
    private JPanel centerPanel;
    private JPanel statusPanel;
    private JPanel summaryPanel;
    private JPanel pedestrianTitlePanel;
    private JPanel pedestrianContentPanel;
    private JLabel pedestrianContentLabel;
    private JPanel pedestrianContainerPanel;
    private JLabel pedestrianTitleLabel;
    private JPanel pedestrianButtonPanel;
    private JButton addPedestrianButton;
    private JButton suspendButton;
    private JLabel statusLabel;
    private JPanel summaryPedestrianPanel;
    private JPanel carsTitlePanel;
    private JLabel carsTitleLabel;
    private JPanel carsContentPanel;
    private JLabel carsContentLabel;
    private JPanel carsContainerPanel;
    private JPanel carsButtonPanel;
    private JButton startButton;
    private JPanel summaryCarsPanel;
    private final EPServiceProvider engine;

    private static MainFrame currentMainFrame;

    public static MainFrame getCurrentMainFrame() {
        if (currentMainFrame == null) {
            currentMainFrame = new MainFrame();
        }
        return currentMainFrame;
    }

    private MainFrame() {
        engine = EPServiceProviderManager.getDefaultProvider();

        trafficLightsPanel = new TrafficLightsPanel();
        setSize(1000, 800);
        setTitle("Traffic Control Management System");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanelContainer = new JPanel();
        mainPanelContainer.setLayout(new GridLayout(2, 1));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(2, 1));
        mainPanel.setBackground(Color.BLUE);

        pedestrianPanel = new JPanel();
        pedestrianPanel.setBackground(Color.DARK_GRAY);

        carsPanel = new JPanel();
        carsPanel.setBackground(Color.ORANGE);

        // adding 2 panel into one panel
        centerPanel = new JPanel(new GridLayout(0, 2));
        centerPanel.add(carsPanel);
        centerPanel.add(pedestrianPanel);

        // adding the centralPanel to the "main panel" which include the other panels: pedestrian, cars
        mainPanel.add(centerPanel);

        //add time panel to the main panel in north direction
        statusPanel = new JPanel();
        statusPanel.setBackground(Color.BLACK);
        mainPanel.add(statusPanel, BorderLayout.NORTH);

        //add summary panel for scroll if needed to the main panel in south direction
        summaryPanel = new JPanel();
        summaryPanel.setBackground(Color.CYAN);
        mainPanel.add(summaryPanel, BorderLayout.SOUTH);

        //adding component to time panel
        statusLabel = new JLabel("Time: ");
        statusLabel.setForeground(Color.WHITE);
        statusPanel.add(statusLabel);

        // adding panels to pedestrian panel to be able to center components

        pedestrianPanel.setLayout(new BorderLayout());

        // adding title inside ped title panel
        pedestrianTitlePanel = new JPanel();
        pedestrianTitleLabel = new JLabel("Pedestrian Controller");
        pedestrianTitleLabel.setForeground(Color.MAGENTA);
        pedestrianTitlePanel.add(pedestrianTitleLabel);

        pedestrianPanel.add(pedestrianTitlePanel, BorderLayout.NORTH);

        pedestrianContentPanel = new JPanel(new GridLayout(2, 1));
        pedestrianContentLabel = new JLabel("Pedestrians#: 0");
        pedestrianContentLabel.setForeground(Color.BLUE);
        pedestrianContentPanel.add(pedestrianContentLabel);

        pedestrianContainerPanel = new JPanel(new GridLayout(2, 1));
        pedestrianContainerPanel.add(pedestrianContentPanel);

        pedestrianButtonPanel = new JPanel();
        addPedestrianButton = new JButton("Add pedestrian");
        addPedestrianButton.setBackground(Color.RED);
        addPedestrianButton.setForeground(Color.white);

        addPedestrianButton.addActionListener(e -> {
            engine.getEPRuntime().sendEvent(new PedestrianRequestEvent(true));
        });

        suspendButton = new JButton("Suspend Service");
        suspendButton.setBackground(Color.RED);
        suspendButton.setForeground(Color.white);

        suspendButton.addActionListener(e -> {
            TrafficLightController trafficLightController = TrafficLightController.getCurrentController();
            if (trafficLightController.isSuspended()) {
                engine.getEPRuntime().sendEvent(new SuspensionEvent(false));
                suspendButton.setText("Suspend Service");
            } else {
                engine.getEPRuntime().sendEvent(new SuspensionEvent(true));
                suspendButton.setText("Activate Service");
            }
        });

        pedestrianButtonPanel.add(addPedestrianButton);

        pedestrianContainerPanel.add(pedestrianButtonPanel);

        pedestrianPanel.add(pedestrianContentPanel, BorderLayout.CENTER);

        summaryPedestrianPanel = new JPanel(new GridLayout(2, 3));
        // add needed variables --> Labels

        pedestrianContainerPanel.add(summaryPedestrianPanel);
        pedestrianPanel.add(pedestrianContainerPanel, BorderLayout.SOUTH);

        //*************************************************************************

        // adding panel to cars panel to be able to center components

        carsPanel.setLayout(new BorderLayout());

        // adding title inside ped title panel
        carsTitlePanel = new JPanel();
        carsTitleLabel = new JLabel("Cars Controller");
        carsTitleLabel.setForeground(Color.BLUE);
        carsTitlePanel.add(carsTitleLabel);

        carsPanel.add(carsTitlePanel, BorderLayout.NORTH);

        carsContentPanel = new JPanel(new GridLayout(2, 1));
        carsContentLabel = new JLabel("Cars#: 0");
        carsContentLabel.setForeground(Color.BLUE);
        carsContentPanel.add(carsContentLabel);

        carsContainerPanel = new JPanel(new GridLayout(2, 1));
        carsContainerPanel.add(carsContentPanel);
        carsContainerPanel.add(suspendButton);

        carsButtonPanel = new JPanel();
        startButton = new JButton("Start");
        startButton.setBackground(Color.GREEN);
       // carsButtonPanel.add(startButton);

        carsContainerPanel.add(carsButtonPanel);

        carsPanel.add(carsContentPanel, BorderLayout.CENTER);

        summaryCarsPanel = new JPanel(new GridLayout(2, 3));
        // add needed variables --> Labels

        carsContainerPanel.add(summaryCarsPanel);
        carsPanel.add(carsContainerPanel, BorderLayout.SOUTH);

        mainPanelContainer.add(mainPanel);
        mainPanelContainer.add(trafficLightsPanel);
        add(mainPanelContainer);
        setVisible(true);
    }

    public void updateWaitingCarsCount(int count) {
        carsContentLabel.setText("Cars#: " + count);
    }

    public void updatePedestriansCount(int count) {
        pedestrianContentLabel.setText("Pedestrians#: " + count);
    }

    public void setPedestrianColor(Color color) {
        if (color == Color.RED) {
            trafficLightsPanel.setPedestrianRedColor(color);
            trafficLightsPanel.setPedestrianGreenColor(Color.GRAY);
        } else {
            trafficLightsPanel.setPedestrianGreenColor(color);
            trafficLightsPanel.setPedestrianRedColor(Color.GRAY);
        }
    }

    public void setCarsColor(Color color) {
        if (color == Color.RED) {
            trafficLightsPanel.setCarsRedColor(color);
            trafficLightsPanel.setCarsGreenColor(Color.GRAY);
        } else {
            trafficLightsPanel.setCarsGreenColor(color);
            trafficLightsPanel.setCarsRedColor(Color.GRAY);
        }
    }

    public void updateTrafficLights() {
        trafficLightsPanel.updateTrafficLights();
    }

    public void updateStatus(String text) {
        statusLabel.setText(text);
    }
}
