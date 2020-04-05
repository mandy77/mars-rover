import org.junit.Before;
import org.junit.Test;

import com.model.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointTest {

    Point point;
    private final int location = 5;
    private final int maxLocation = 9;

    @Before
    public void beforePointTest() {
        point = Point.builder()
        .location(location)
        .maxLocation(maxLocation)
        .build();
    }

    @Test
    public void newInstanceShouldSetLocationAndMaxLocationParams() {
        assertTrue(point.getLocation() == location);
        assertTrue(point.getMaxLocation() == maxLocation);
    }

    @Test
    public void getForwardLocationShouldIncreasePointValueByOne() {
        int expected = point.getLocation() + 1;
        assertEquals(point.getForwardLocation(), expected);
    }

    @Test
    public void getBackwardLocationShouldDecreasePointValueByOne() {
        int expected = point.getLocation() - 1;
        assertEquals(point.getBackwardLocation(), expected);
    }

    @Test
    public void getForwardLocationShouldSetValueToZeroIfMaxLocationIsPassed() {
        point.setLocation(point.getMaxLocation());
        assertEquals(point.getForwardLocation(), 0);
    }

    @Test
    public void getBackwardLocationShouldSetValueToMaxLocationIfZeroLocationIsPassed() {
        point.setLocation(0);
        assertEquals(point.getBackwardLocation(), point.getMaxLocation());
    }

}