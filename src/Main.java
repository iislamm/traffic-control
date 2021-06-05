import Controllers.ConflictResolver;
import Controllers.SensorsController;
import Controllers.TrafficLightController;
import EventGenerators.CarArrivalEventGenerator;
import EventGenerators.PedestrianArrivalEventGenerator;
import EventGenerators.PedestrianRequestEventGenerator;
import EventGenerators.TrafficLightEventGenerator;
import Events.CarArrivalEvent;
import Events.PedestrianArrivalEvent;
import Events.PedestrianRequestEvent;
import Events.TrafficLightEvent;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;


public class Main {
    public static void main(String[] args) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
        TrafficLightController trafficLightController = TrafficLightController.getCurrentController();
        SensorsController sensorsController = SensorsController.getCurrentController();

        engine.getEPAdministrator().getConfiguration().addEventType(TrafficLightEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(PedestrianArrivalEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(CarArrivalEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(PedestrianRequestEvent.class);

        EPStatement streetLightStatement = engine
                .getEPAdministrator()
                .createEPL("select streetLight, forced from Events.TrafficLightEvent");

        EPStatement pedestrianArrivalEvent = engine
                .getEPAdministrator()
                .createEPL("select pedestriansCount from PedestrianArrivalEvent");

        EPStatement carArrivalEvent = engine
                .getEPAdministrator()
                .createEPL("select carsCount from CarArrivalEvent");

        EPStatement pedestrianRequestEvent = engine
                .getEPAdministrator()
                .createEPL("select request from PedestrianRequestEvent");


        streetLightStatement.setSubscriber(new Object() {
            public void update(String newColor, boolean forced) {
                trafficLightController.setStreetTrafficLight(newColor, forced);
            }
        });

        pedestrianArrivalEvent.setSubscriber(new Object() {
            public void update(int count) {
                sensorsController.increasePedestrians(count);
            }
        });

        carArrivalEvent.setSubscriber(new Object() {
            public void update(int count) {
                sensorsController.increaseWaitingCars(count);
            }
        });

        pedestrianRequestEvent.setSubscriber(new Object() {
            public void update(boolean request) {
                trafficLightController.setCurrentPedestrianRequestWait(0);
            }
        });

        TrafficLightEventGenerator trafficLightEventGenerator = new TrafficLightEventGenerator();
        PedestrianArrivalEventGenerator pedestrianArrivalEventGenerator = new PedestrianArrivalEventGenerator();
        CarArrivalEventGenerator carArrivalEventGenerator = new CarArrivalEventGenerator();
        PedestrianRequestEventGenerator pedestrianRequestEventGenerator = new PedestrianRequestEventGenerator();
        ConflictResolver conflictResolver = new ConflictResolver();

        trafficLightEventGenerator.start();
        pedestrianArrivalEventGenerator.start();
        carArrivalEventGenerator.start();
        pedestrianRequestEventGenerator.start();
        conflictResolver.start();
    }
}
