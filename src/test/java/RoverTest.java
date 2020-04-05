import com.Direction;
import com.model.Point;
import com.model.Position;
import com.model.Rover;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoverTest {

    private Rover rover;
    private Position roverPosition;
    private final Direction direction = Direction.NORTH;
    private Point x;
    private Point y;

    @Before
    public void beforeRoverTest() {
        x = Point.builder()
                .location(1)
                .maxLocation(9)
                .build();

        y = Point.builder()
                .location(1)
                .maxLocation(9)
                .build();

        roverPosition = Position.builder()
                .x(x)
                .y(y)
                .build();

        rover = new Rover(roverPosition, direction);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        assertEquals(rover.getPosition(), roverPosition);
        assertEquals(rover.getDirection(), direction);
    }


}