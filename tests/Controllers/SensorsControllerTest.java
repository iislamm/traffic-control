package Controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorsControllerTest {

    /**
     * This function increase the number of waiting cars while the traffic light is red
     * And it doesn't increase the counter if the light is green
     */
    @Test
    void increaseWaitingCars() {
        SensorsController sensorsController = SensorsController.getCurrentController();
        TrafficLightController trafficLightController =  TrafficLightController.getCurrentController();
        trafficLightController.setStreetTrafficLight("red",false); // creates red traffic light
        sensorsController.increaseWaitingCars(30); // increase waiting cars counter
        assertEquals(sensorsController.getWaitingCarsCount(),30); // checks if the waiting cars counter was increased by 30
        trafficLightController.setStreetTrafficLight("green",false); // creates green traffic light
        sensorsController.increaseWaitingCars(50); // increase waiting cars counter by 50 but the traffic light is green
        assertEquals(sensorsController.getWaitingCarsCount(),0); // the traffic light is green so there is no waiting cars

    }

    @Test
    void increasePedestrians() {
        SensorsController sensorsController = SensorsController.getCurrentController();
        TrafficLightController trafficLightController =  TrafficLightController.getCurrentController();
        trafficLightController.setStreetTrafficLight("green",false); // creates green traffic light and red pedestrians light
        sensorsController.increasePedestrians(10); // increase waiting pedestrians counter
        assertEquals(sensorsController.getPedestriansCount(),10); // checks if the waiting pedestrians counter was increased by 10
        trafficLightController.setStreetTrafficLight("red",false); // creates red traffic light and green pedestrians light
        sensorsController.increasePedestrians(20); // checks if the waiting pedestrians counter was increased by 20
        assertEquals(sensorsController.getPedestriansCount(),0); // the pedestrians light is green so there is no waiting pedestrians
    }
}