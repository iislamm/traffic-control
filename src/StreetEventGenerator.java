import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class StreetEventGenerator extends Thread {


    private String currentColor;
    private final int redSeconds;
    private final int greenSeconds;
    private final SensorsController sensorsController;
    private final EPServiceProvider engine;

    public StreetEventGenerator(String currentColor) {
        this.currentColor = currentColor;
        redSeconds = 2;
        greenSeconds = 1;
        sensorsController = SensorsController.getCurrentSensorController();
        engine = EPServiceProviderManager.getDefaultProvider();
    }

    public String getCurrentColor() {
        return currentColor;
    }

    void changeTrafficLight(){

        if (this.currentColor.equals("red")) {
            this.currentColor = "green";
            sensorsController.setWaitingCarsCount((int) (Math.random() * 50));
        } else {
            this.currentColor = "red";
            int x = sensorsController.getWaitingCarsCount();
            if (x <= 30) {
                sensorsController.setWaitingCarsCount(0);
            } else {
                sensorsController.setWaitingCarsCount(x - 30);
            }
        }
        System.out.println("waiting cars count: " + sensorsController.getWaitingCarsCount());

        System.out.println("street " + currentColor);
        engine.getEPRuntime().sendEvent(new StreetController(this.currentColor));
    }

    @Override
    public void run() {
        super.run();

        while (true) {

        }
    }
}
