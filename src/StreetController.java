public class StreetController {
    private boolean suspended;
    private String trafficLight;

    public StreetController(boolean suspended, String trafficLight) {
        this.suspended = suspended;
        this.trafficLight = trafficLight;
    }

    public void changeTrafficLight() {

    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(String trafficLight) {
        this.trafficLight = trafficLight;
    }

    public void suspendService() {
        suspended = true;
    }

    public void resumeService() {
        suspended = false;
    }

    public boolean isSuspended() {
        return suspended;
    }
}
