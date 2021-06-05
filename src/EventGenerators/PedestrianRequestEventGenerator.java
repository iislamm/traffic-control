package EventGenerators;

import Events.PedestrianRequestEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class PedestrianRequestEventGenerator extends Thread {
    private final EPServiceProvider engine;

    public PedestrianRequestEventGenerator() {
        engine = EPServiceProviderManager.getDefaultProvider();
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            engine.getEPRuntime().sendEvent(new PedestrianRequestEvent(true));
            try {
                sleep((long) (Math.random() * 2 * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
