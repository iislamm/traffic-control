import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import java.util.TimerTask;

public class PedestrianEventGenerator extends Thread {
    public String getCurrentColor() {
        return currentColor;
    }

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

     void changePedestrianLight() {
        if (this.currentColor.equals("red")) {
            this.currentColor = "green";
        } else {
            this.currentColor = "red";
        }

         try {
             if (this.currentColor.equals("red")) {
                 addPedestrians();
                 sleep(redSeconds * 1000L);
                 addPedestrians();
             } else { // Green
                 sensorsController.setWaitingPedestrians(0);
                 sleep(greenSeconds * 1000L);
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println("pedestrian " + currentColor);
         System.out.println("pedestrians count " + sensorsController.getWaitingPedestrians());
         engine.getEPRuntime().sendEvent(new PedestrianCrossingController(this.currentColor));

    }

    public void addPedestrians (){
        sensorsController.setWaitingPedestrians(sensorsController.getWaitingPedestrians() + 1);
//        System.out.println(sensorsController.getWaitingPedestrians());

    }

    @Override
    public void run() {
        super.run();
        changePedestrianLight();
        while (true) {


        }
    }
}
