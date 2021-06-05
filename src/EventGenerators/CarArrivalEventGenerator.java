package EventGenerators;

import Events.CarArrivalEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

public class CarArrivalEventGenerator extends Thread {
    private final EPServiceProvider engine;

    public CarArrivalEventGenerator() {
        engine = EPServiceProviderManager.getDefaultProvider();
    }

    @Override
    public void run() {
        super.run();
        int arrivedCarsCount;
        while (true) {
            arrivedCarsCount = (int) (Math.random() * 5);
            engine.getEPRuntime().sendEvent(new CarArrivalEvent(arrivedCarsCount));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
