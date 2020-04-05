import com.model.Obstacle;
import com.model.Point;
import com.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    private Position position;
    private Point x;
    private Point y;

    @Before
    public void beforeCoordinatesTest() {
        x = Point.builder()
                .location(1)
                .maxLocation(99)
                .build();

        y = Point.builder()
                .location(2)
                .maxLocation(99)
                .build();

        Obstacle obs1 = Obstacle.builder()
                .x(20)
                .y(20)
                .build();

        Obstacle obs2 = Obstacle.builder()
                .x(30)
                .y(30)
                .build();

        position = Position.builder()
                                .x(x)
                                .y(y)
                                .build();
    }

    @Test
    public void newInstanceShouldSetXAndYParams() {
        assertEquals(position.getX(), x);
        assertEquals(position.getY(), y);
    }

}