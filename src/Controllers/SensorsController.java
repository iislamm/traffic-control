package Controllers;

public class SensorsController {
    private int waitingCarsCount;
    private int pedestriansCount;

    private static SensorsController currentSensorsController;

    private SensorsController() {
        waitingCarsCount = 0;
        pedestriansCount = 0;
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
    }

    public int getPedestriansCount() {
        return pedestriansCount;
    }

    public void setPedestriansCount(int pedestriansCount) {
        this.pedestriansCount = pedestriansCount;
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
    }
}
