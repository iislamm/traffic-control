package Events;

public class TrafficLightEvent {
    private String streetLight;
    private String pedestrianLight;
    private boolean forced;

    public TrafficLightEvent(String streetLight, String pedestrianLight, boolean forced) {
        this.streetLight = streetLight;
        this.pedestrianLight = pedestrianLight;
        this.forced = forced;
    }

    public String getStreetLight() {
        return streetLight;
    }

    public void setStreetLight(String streetLight) {
        this.streetLight = streetLight;
    }

    public String getPedestrianLight() {
        return pedestrianLight;
    }

    public void setPedestrianLight(String pedestrianLight) {
        this.pedestrianLight = pedestrianLight;
    }

    public boolean isForced() {
        return forced;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }
}
