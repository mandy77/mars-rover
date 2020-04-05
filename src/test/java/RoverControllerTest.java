import com.Direction;
import com.controller.RoverController;
import com.model.Obstacle;
import com.model.Point;
import com.model.Position;
import com.model.Rover;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.*;

public class RoverControllerTest {

    private Rover rover;
    private RoverController roverController;
    private Position roverPosition;
    private final Direction direction = Direction.NORTH;
    private Point x;
    private Point y;
    private List<Obstacle> obstacles;

    @Before
    public void beforeRoverTest() {
        x = Point.builder()
                .location(1)
                .maxLocation(9)
                .build();

        y = Point.builder()
                .location(2)
                .maxLocation(9)
                .build();
        obstacles = new ArrayList<Obstacle>();
        roverPosition = Position.builder()
                .x(x)
                .y(y)
                .build();
        rover = new Rover(roverPosition, direction);
        roverController = new RoverController(rover, obstacles);
    }

    @Test
    public void newInstanceShouldSetRoverAndObstacles() {
        assertEquals(roverController.getRover(), rover);
        assertEquals(roverController.getObstacles(), obstacles);
    }

    @Test
    public void moveBackwardShouldIncreaseYWhenDirectionIsSouth() {
        Point expected = Point.builder()
                .location(y.getLocation() + 1)
                .maxLocation(y.getMaxLocation())
                .build();
        rover.setDirection(Direction.SOUTH);
        roverController.setRover(rover);
        roverController.moveBackward();
        assertThat(rover.getPosition().getY(), samePropertyValuesAs(expected));
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expected = y.getLocation() + 1;
        roverController.command('F');
        assertEquals(rover.getPosition().getY().getLocation(), expected);
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expected = y.getLocation() - 1;
        roverController.command('B');
        assertEquals(rover.getPosition().getY().getLocation(), expected);
    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws Exception {
        roverController.command('L');
        assertTrue(rover.getDirection().equals(Direction.WEST));
    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws Exception {
        roverController.command('R');
        assertTrue(rover.getDirection().equals(Direction.EAST));
    }

    @Test
    public void receiveSingleCommandShouldIgnoreCase() throws Exception {
        roverController.command('r');
        assertTrue(rover.getDirection().equals(Direction.EAST));
    }

    @Test(expected = Exception.class)
    public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() throws Exception {
        roverController.command('X');
    }

    @Test
    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws Exception {
        int expected = x.getLocation() + 1;
        roverController.commands("RFR");
        assertEquals(rover.getPosition().getX().getLocation(), expected);
        assertTrue(rover.getDirection().equals(Direction.SOUTH));
    }

    @Test
    public void receiveCommandShouldWhatFromOneEdgeOfTheGridToAnother() throws Exception {
        int expected = x.getMaxLocation() + x.getLocation() - 2;
        roverController.commands("LFFF");
        assertEquals(rover.getPosition().getX().getLocation(), expected);
    }

    @Test
    public void receiveCommandsShouldStopWhenObstacleIsFound() throws Exception {
        int expected = x.getLocation() + 1;
        Obstacle obs = Obstacle.builder()
                .x(expected + 1)
                .y(y.getLocation())
                .build();
        roverController.setObstacles(Arrays.asList(obs));
        rover.setDirection(Direction.EAST);
        roverController.setRover(rover);
        roverController.commands("FFFRF");
        assertEquals(roverController.getRover().getPosition().getX().getLocation(), expected);
        assertEquals(rover.getDirection(), Direction.EAST);
    }

    @Test
    public void positionShouldReturnXYAndDirection() throws Exception {
        roverController.setRover(rover);
        roverController.commands("LFFFRFF");
        assertEquals(roverController.currentPosition(), "8 4 N");
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        Obstacle obs = Obstacle.builder()
                .x(x.getLocation() + 1)
                .y(y.getLocation())
                .build();
        roverController.setObstacles(Arrays.asList(obs));
        rover.setDirection(Direction.EAST);
        roverController.setRover(rover);
        roverController.commands("F");
        assertTrue(roverController.currentPosition().endsWith(" NOK"));
    }

}