import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import java.util.TimerTask;

public class PedestrianEventGenerator extends Thread {
    private String currentColor;
    private final int redSeconds;
    private final int greenSeconds;
    private final SensorsController sensorsController;
    private final EPServiceProvider engine;

    public PedestrianEventGenerator(String currentColor) {
        this.currentColor = currentColor;
        redSeconds = 2;
        greenSeconds = 1;
        sensorsController = SensorsController.getCurrentSensorController();
        engine = EPServiceProviderManager.getDefaultProvider();
    }

    private void changeTrafficLight() {
        if (this.currentColor.equals("red")) {
            this.currentColor = "green";
        } else {
            this.currentColor = "red";
        }
        engine.getEPRuntime().sendEvent(new PedestrianCrossingController(this.currentColor));
        try {
            if (this.currentColor.equals("red")) {
                sleep(redSeconds * 1000L);
            } else {
                sleep(greenSeconds * 1000L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addPedestrians() {
        if (currentColor.equals("red"))
    }

    @Override
    public void run() {
        super.run();

        while (true) {
//            changeTrafficLight();
            int timer;
            if(currentColor.equals("red"))
                timer = redSeconds * 1000;
            else
                timer = greenSeconds * 1000;

            for(int i = 0; i < timer; i++){
                System.out.println("add pedestrian");
            }
        }
    }
}
