package Events;

public class PedestrianRequestEvent {
    private boolean request;

    public PedestrianRequestEvent(boolean request) {
        this.request = request;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }
}
