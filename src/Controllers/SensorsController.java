package Controllers;

import Views.MainFrame;

public class SensorsController {
    private int waitingCarsCount;
    private int pedestriansCount;
    private final MainFrame mainFrame;

    private static SensorsController currentSensorsController;

    private SensorsController() {
        waitingCarsCount = 0;
        pedestriansCount = 0;

        mainFrame = MainFrame.getCurrentMainFrame();
    }

    public static SensorsController getCurrentController() {
        if (currentSensorsController == null) {
            currentSensorsController = new SensorsController();
        }
        return currentSensorsController;
    }

    public int getWaitingCarsCount() {
        return waitingCarsCount;
    }

    public void setWaitingCarsCount(int waitingCarsCount) {
        this.waitingCarsCount = waitingCarsCount;
    }

    public void increaseWaitingCars(int count) {
        TrafficLightController trafficLightController = TrafficLightController.getCurrentController();
        if (trafficLightController.getStreetTrafficLight().equals("red")) {
            this.waitingCarsCount += count;
        }
        System.out.println("---------");
        System.out.println("New cars: " + count);
        System.out.println("Current waiting cars: " + this.waitingCarsCount);
        System.out.println("---------");
        mainFrame.updateWaitingCarsCount(this.waitingCarsCount);
    }

    public int getPedestriansCount() {
        return pedestriansCount;
    }

    public void setPedestriansCount(int pedestriansCount) {
        this.pedestriansCount = pedestriansCount;
        mainFrame.updatePedestriansCount(this.pedestriansCount);
    }

    public void increasePedestrians(int count) {
        TrafficLightController trafficLightController = TrafficLightController.getCurrentController();
        if (trafficLightController.getPedestrianTrafficLight().equals("red")) {
            this.pedestriansCount += count;
        }
        System.out.println("---------");
        System.out.println("New pedestrians: " + count);
        System.out.println("Current waiting pedestrians: " + this.pedestriansCount);
        System.out.println("---------");
        mainFrame.updatePedestriansCount(this.pedestriansCount);
    }
}
