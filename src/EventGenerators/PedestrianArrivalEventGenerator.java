package EventGenerators;

import Controllers.SensorsController;
import Events.PedestrianArrivalEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class PedestrianArrivalEventGenerator extends Thread {
    private final EPServiceProvider engine;
    private final SensorsController sensorsController;

    public PedestrianArrivalEventGenerator() {
        engine = EPServiceProviderManager.getDefaultProvider();
        sensorsController = SensorsController.getCurrentController();
    }

    @Override
    public void run() {
        super.run();
        int newPedestriansCount;
        while (true) {
            newPedestriansCount = (int) (Math.random() * 10);
            engine.getEPRuntime().sendEvent(new PedestrianArrivalEvent(newPedestriansCount));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
