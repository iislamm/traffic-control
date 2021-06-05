package Controllers;

import Events.TrafficLightEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class ConflictResolver extends Thread {
    private final int maxPedestrianWait;
    private final TrafficLightController trafficLightController;
    private final EPServiceProvider engine;

    public ConflictResolver() {
        this.maxPedestrianWait = 2;
        trafficLightController = TrafficLightController.getCurrentController();
        engine = EPServiceProviderManager.getDefaultProvider();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("current wait time: " + trafficLightController.getCurrentPedestrianRequestWait());
            if (trafficLightController.getCurrentPedestrianRequestWait() >= maxPedestrianWait) {
                engine.getEPRuntime().sendEvent(new TrafficLightEvent("red", "green", true));
            } else if (trafficLightController.getCurrentPedestrianRequestWait() > -1) {
                trafficLightController.increaseCurrentPedestrianRequestWait(1);
            }
            try {
                sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
