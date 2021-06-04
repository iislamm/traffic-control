public class SensorsController {
    private boolean isHeavyTraffic;
    private int waitingCarsCount;
    private int departedCarsCount;
    private int waitingPedestrians;
    private int leftPedestrians;

    private static SensorsController currentSensorController;

    private SensorsController() {
        this.waitingCarsCount = 0;
        this.departedCarsCount = 0;
        this.waitingPedestrians = 0;
        this.leftPedestrians = 0;
        this.isHeavyTraffic = false;
    }

    public static SensorsController getCurrentSensorController() {
        if (currentSensorController == null) {
            currentSensorController = new SensorsController();
        }
        return currentSensorController;
    }

    private SensorsController(boolean isHeavyTraffic, int waitingCarsCount, int departedCarsCount, int waitingPedestrians, int leftPedestrians) {
        this.isHeavyTraffic = isHeavyTraffic;
        this.waitingCarsCount = waitingCarsCount;
        this.departedCarsCount = departedCarsCount;
        this.waitingPedestrians = waitingPedestrians;
        this.leftPedestrians = leftPedestrians;
    }

    public boolean isHeavyTraffic() {
        return isHeavyTraffic;
    }

    public void setHeavyTraffic(boolean heavyTraffic) {
        isHeavyTraffic = heavyTraffic;
    }

    public int getWaitingCarsCount() {
        return waitingCarsCount;
    }

    public void setWaitingCarsCount(int waitingCarsCount) {
        this.waitingCarsCount = waitingCarsCount;
    }

    public int getDepartedCarsCount() {
        return departedCarsCount;
    }

    public void setDepartedCarsCount(int departedCarsCount) {
        this.departedCarsCount = departedCarsCount;
    }

    public int getWaitingPedestrians() {
        return waitingPedestrians;
    }

    public void setWaitingPedestrians(int waitingPedestrians) {
        this.waitingPedestrians = waitingPedestrians;
    }

    public int getLeftPedestrians() {
        return leftPedestrians;
    }

    public void setLeftPedestrians(int leftPedestrians) {
        this.leftPedestrians = leftPedestrians;
    }

}
