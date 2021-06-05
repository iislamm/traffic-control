package Controllers;

import Views.MainFrame;

import java.awt.*;

public class TrafficLightController {
    private String streetTrafficLight;
    private String pedestrianTrafficLight;
    private int redSeconds;
    private int greenSeconds;
    private final int carsPerSecond;
    private final SensorsController sensorsController;
    private int currentPedestrianRequestWait;
    private final MainFrame mainFrame;
    private volatile boolean suspended;

    private static TrafficLightController currentTrafficLightController;

    private TrafficLightController() {
        streetTrafficLight = "red";
        pedestrianTrafficLight = "green";
        suspended = false;

        redSeconds = 40;
        greenSeconds = 30;
        currentPedestrianRequestWait = -1;
        carsPerSecond = 3;

        sensorsController = SensorsController.getCurrentController();
        mainFrame = MainFrame.getCurrentMainFrame();
    }

    public static TrafficLightController getCurrentController() {
        if (currentTrafficLightController == null) {
            currentTrafficLightController = new TrafficLightController();
        }
        return currentTrafficLightController;
    }

    public String getStreetTrafficLight() {
        return streetTrafficLight;
    }

    public void setStreetTrafficLight(String streetTrafficLight, boolean forced) {
        if (streetTrafficLight.equals("red")) {
            this.streetTrafficLight = streetTrafficLight;
            this.pedestrianTrafficLight = "green";
            sensorsController.setPedestriansCount(0);
            currentPedestrianRequestWait = -1;
        } else if (streetTrafficLight.equals("green")) {
            this.streetTrafficLight = streetTrafficLight;
            this.pedestrianTrafficLight = "red";
            int carsCount = sensorsController.getWaitingCarsCount();
            greenSeconds = carsCount / carsPerSecond;
            sensorsController.setWaitingCarsCount(0);
        }

        if (forced) {
            mainFrame.updateStatus("Forcefully changed lights");
        } else {
            mainFrame.updateStatus("");
        }

        System.out.println("forced = " + forced);
        System.out.println("Street light changed to: " + this.streetTrafficLight);
        System.out.println("Pedestrian light changed to: " + this.pedestrianTrafficLight);

        Color pedestrianColor;
        Color streetColor;

        if (this.streetTrafficLight.equals("red")) {
            pedestrianColor = Color.RED;
            streetColor = Color.GREEN;
        } else {
            pedestrianColor = Color.GREEN;
            streetColor = Color.RED;
        }

        mainFrame.setPedestrianColor(pedestrianColor);
        mainFrame.setCarsColor(streetColor);
        mainFrame.updateTrafficLights();
    }

    public String getPedestrianTrafficLight() {
        return pedestrianTrafficLight;
    }

    public void setPedestrianTrafficLight(String pedestrianTrafficLight) {
        this.pedestrianTrafficLight = pedestrianTrafficLight;
    }

    public int getRedSeconds() {
        return redSeconds;
    }

    public int getGreenSeconds() {
        return greenSeconds;
    }

    public int getCurrentPedestrianRequestWait() {
        return currentPedestrianRequestWait;
    }

    public void setCurrentPedestrianRequestWait(int currentPedestrianRequestWait) {
        if (this.currentPedestrianRequestWait == -1 && currentPedestrianRequestWait == 0) {
            this.currentPedestrianRequestWait = currentPedestrianRequestWait;
        }
    }

    public void increaseCurrentPedestrianRequestWait(int increaseAmount) {
        if (this.pedestrianTrafficLight.equals("red") && this.currentPedestrianRequestWait > -1) {
            currentPedestrianRequestWait += increaseAmount;
        }
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}
