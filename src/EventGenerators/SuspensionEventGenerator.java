package EventGenerators;

import Controllers.TrafficLightController;
import Events.TrafficLightEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class SuspensionEventGenerator extends Thread {
    private final EPServiceProvider engine;
    private final TrafficLightController trafficLightController;

    public SuspensionEventGenerator() {
        engine = EPServiceProviderManager.getDefaultProvider();
        trafficLightController = TrafficLightController.getCurrentController();
    }

    @Override
    public void run() {
        super.run();
        String currentColor;
        boolean isSuspended;

        while (true) {
            currentColor = trafficLightController.getStreetTrafficLight();

            try {
                isSuspended = trafficLightController.isSuspended();


                if (isSuspended) {
                    System.out.println("IT IS SUSPENDED");
                    sleep(500L);
                    if (currentColor.equals("red")) {
                        engine.getEPRuntime().sendEvent(new TrafficLightEvent("green", "red", false));
                    } else {
                        engine.getEPRuntime().sendEvent(new TrafficLightEvent("red", "green", false));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
