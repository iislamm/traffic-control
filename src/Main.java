import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;


public class Main {
    public static void main(String[] args) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(StreetController.class);
        engine.getEPAdministrator().getConfiguration().addEventType(PedestrianCrossingController.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SensorsController.class);
        PedestrianEventGenerator pedestrianEventGenerator = new PedestrianEventGenerator("red");
        StreetEventGenerator streetEventGenerator = new StreetEventGenerator("green");

        EPStatement pedestrianLightChangeStatement = engine
                .getEPAdministrator()
                .createEPL("select pedestrianTrafficLight from PedestrianCrossingController");
        EPStatement trafficLightChangeStatement = engine
                .getEPAdministrator()
                .createEPL("select trafficLight from StreetController");

        pedestrianLightChangeStatement.setSubscriber(new Object() {
            public void update(String newColor) {
                //System.out.println("pedestrianUpdate " + newColor);
                streetEventGenerator.changeTrafficLight();
            }
        });
        trafficLightChangeStatement.setSubscriber(new Object() {
            public void update(String newColor) {
                //System.out.println("streetUpdate " + newColor);
                pedestrianEventGenerator.changePedestrianLight();
            }
        });
        pedestrianEventGenerator.start();
        streetEventGenerator.start();
    }
}
