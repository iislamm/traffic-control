package Controllers;

public class TrafficLightController {
    private String streetTrafficLight;
    private String pedestrianTrafficLight;
    private final int redSeconds;
    private final int greenSeconds;
    private final SensorsController sensorsController;
    private int currentPedestrianRequestWait;

    private static TrafficLightController currentTrafficLightController;

    private TrafficLightController() {
        streetTrafficLight = "red";
        pedestrianTrafficLight = "green";

        redSeconds = 6;
        greenSeconds = 4;
        currentPedestrianRequestWait = -1;

        sensorsController = SensorsController.getCurrentController();
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
            sensorsController.setWaitingCarsCount(0);
        }
        System.out.println("forced = " + forced);
        System.out.println("Street light changed to: " + this.streetTrafficLight);
        System.out.println("Pedestrian light changed to: " + this.pedestrianTrafficLight);
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
        currentPedestrianRequestWait += increaseAmount;
    }
}
