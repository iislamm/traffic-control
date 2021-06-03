import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Main {
    public static void main(String[] args) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

        engine.getEPAdministrator().getConfiguration().addEventType(StreetController.class);
        engine.getEPAdministrator().getConfiguration().addEventType(PedestrianCrossingController.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SensorsController.class);

        EPStatement trafficLightChangeStatement = engine
                .getEPAdministrator()
                .createEPL("select trafficLight from StreetController");

        trafficLightChangeStatement.setSubscriber(new Object() {
            public void update(String newColor) {
                System.out.println("Traffic Light Changed to " + newColor);
            }
        });

        StreetEventGenerator streetEventGenerator = new StreetEventGenerator("red");
        streetEventGenerator.start();
    }
}
