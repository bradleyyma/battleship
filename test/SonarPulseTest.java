import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SonarPulseTest {
    @Test
    public void createSonarPulse(){
        SonarPulse pulse = new SonarPulse();
    }

    @Test
    public void testUse(){
        SonarPulse pulse = new SonarPulse();
        LowerGrid grid = new LowerGrid();
        int [][] testGrid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, -1, -1, -1, -1, -1, 0, 0},
                {0, 0, 0, 0, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        assertArrayEquals(testGrid, pulse.fire(5,5));
    }
}
