public class PedestrianCrossingController {
    private boolean buttonPressed;
    private String pedestrianTrafficLight;
    private boolean resolverRequested;

    public PedestrianCrossingController(String pedestrianTrafficLight) {
        this.pedestrianTrafficLight = pedestrianTrafficLight;
    }

    public PedestrianCrossingController(boolean buttonPressed, String pedestrianTrafficLight, boolean resolverRequested) {
        this.buttonPressed = buttonPressed;
        this.pedestrianTrafficLight = pedestrianTrafficLight;
        this.resolverRequested = resolverRequested;
    }

    public PedestrianCrossingController(boolean buttonPressed, String pedestrianTrafficLight) {
        this.buttonPressed = buttonPressed;
        this.pedestrianTrafficLight = pedestrianTrafficLight;
    }

    public boolean isResolverRequested() {
        return resolverRequested;
    }

    public void setResolverRequested(boolean resolverRequested) {
        this.resolverRequested = resolverRequested;
    }

    public PedestrianCrossingController(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public String getPedestrianTrafficLight() {
        return pedestrianTrafficLight;
    }

    public void setPedestrianTrafficLight(String pedestrianTrafficLight) {
        this.pedestrianTrafficLight = pedestrianTrafficLight;
    }


    public boolean isRed() {
        return true;
    }

    public void requestResolver() {

    }
}
