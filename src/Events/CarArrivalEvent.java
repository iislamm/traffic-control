package Events;

public class CarArrivalEvent {
    private int carsCount;

    public CarArrivalEvent(int carsCount) {
        this.carsCount = carsCount;
    }

    public int getCarsCount() {
        return carsCount;
    }

    public void setCarsCount(int carsCount) {
        this.carsCount = carsCount;
    }
}
