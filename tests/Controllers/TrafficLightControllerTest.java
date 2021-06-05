package Controllers;

import com.espertech.esper.client.soda.CreateDataFlowClause;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLightControllerTest {

    /**
     * This function changes the street and pedestrians traffic lights to be opposite to each other
     * if the street traffic light is red, the pedestrians traffic light is green and vice versa
     */
    @org.junit.jupiter.api.Test
    void setStreetTrafficLight() {
        TrafficLightController trafficLightController =  TrafficLightController.getCurrentController();
        trafficLightController.setStreetTrafficLight("red",false);
        assertEquals(trafficLightController.getStreetTrafficLight(),"red");
        assertEquals(trafficLightController.getPedestrianTrafficLight(),"green");
        trafficLightController.setStreetTrafficLight("green",false);
        assertEquals(trafficLightController.getStreetTrafficLight(),"green");
        assertEquals(trafficLightController.getPedestrianTrafficLight(),"red");
    }
}