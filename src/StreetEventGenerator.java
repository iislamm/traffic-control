import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class StreetEventGenerator extends Thread {
    private String currentColor;
    private final int redSeconds;
    private final int greenSeconds;

    public StreetEventGenerator(String currentColor) {
        this.currentColor = currentColor;
        redSeconds = 2;
        greenSeconds = 1;
    }

    @Override
    public void run() {

    }
}
