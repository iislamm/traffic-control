package Events;

public class PedestrianArrivalEvent {
    private int pedestriansCount;

    public PedestrianArrivalEvent(int pedestriansCount) {
        this.pedestriansCount = pedestriansCount;
    }

    public int getPedestriansCount() {
        return pedestriansCount;
    }

    public void setPedestriansCount(int pedestriansCount) {
        this.pedestriansCount = pedestriansCount;
    }
}
