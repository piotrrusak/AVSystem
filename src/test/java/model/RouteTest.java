package model;

import org.example.model.Route;
import org.example.model.road.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteTest {

    @Test
    void testRouteCreation() {
        Route route = new Route(Direction.NORTH, Direction.SOUTH);
        assertEquals(Direction.NORTH, route.getStart());
        assertEquals(Direction.SOUTH, route.getEnd());
    }

    @Test
    void testIsGoingForward() {
        assertTrue(new Route(Direction.NORTH, Direction.SOUTH).isGoingForward());
        assertTrue(new Route(Direction.WEST, Direction.EAST).isGoingForward());
        assertTrue(new Route(Direction.SOUTH, Direction.NORTH).isGoingForward());
        assertTrue(new Route(Direction.EAST, Direction.WEST).isGoingForward());

        assertFalse(new Route(Direction.NORTH, Direction.EAST).isGoingForward());
    }

    @Test
    void testIsTurningRight() {
        assertTrue(new Route(Direction.NORTH, Direction.WEST).isTurningRight());
        assertTrue(new Route(Direction.WEST, Direction.SOUTH).isTurningRight());
        assertTrue(new Route(Direction.SOUTH, Direction.EAST).isTurningRight());
        assertTrue(new Route(Direction.EAST, Direction.NORTH).isTurningRight());

        assertFalse(new Route(Direction.NORTH, Direction.EAST).isTurningRight());
        assertFalse(new Route(Direction.WEST, Direction.NORTH).isTurningRight());
        assertFalse(new Route(Direction.SOUTH, Direction.WEST).isTurningRight());
        assertFalse(new Route(Direction.EAST, Direction.SOUTH).isTurningRight());
    }

    @Test
    void testIsTurningLeft() {
        assertTrue(new Route(Direction.NORTH, Direction.EAST).isTurningLeft());
        assertTrue(new Route(Direction.WEST, Direction.NORTH).isTurningLeft());
        assertTrue(new Route(Direction.SOUTH, Direction.WEST).isTurningLeft());
        assertTrue(new Route(Direction.EAST, Direction.SOUTH).isTurningLeft());

        assertFalse(new Route(Direction.NORTH, Direction.WEST).isTurningLeft());
        assertFalse(new Route(Direction.WEST, Direction.SOUTH).isTurningLeft());
        assertFalse(new Route(Direction.SOUTH, Direction.EAST).isTurningLeft());
        assertFalse(new Route(Direction.EAST, Direction.NORTH).isTurningLeft());
    }

    @Test
    void testHasRoutePriority() {
        Route r1 = new Route(Direction.NORTH, Direction.WEST);
        Route r2 = new Route(Direction.SOUTH, Direction.WEST);
        Route r3 = new Route(Direction.WEST, Direction.EAST);
        Route r4 = new Route(Direction.SOUTH, Direction.NORTH);
        Route r5 = new Route(Direction.EAST, Direction.WEST);

        assertTrue(r1.hasRoutePriority(r2));
        assertFalse(r2.hasRoutePriority(r1));

        assertTrue(r1.hasRoutePriority(r3));
        assertTrue(r3.hasRoutePriority(r1));

        assertTrue(r2.hasRoutePriority(r3));
        assertFalse(r3.hasRoutePriority(r2));

        assertTrue(r4.hasRoutePriority(r3));
        assertFalse(r3.hasRoutePriority(r4));

        assertTrue(r5.hasRoutePriority(r2));
        assertFalse(r2.hasRoutePriority(r5));

    }

    @Test
    void testEquals() {
        Route route1 = new Route(Direction.NORTH, Direction.SOUTH);
        Route route2 = new Route(Direction.NORTH, Direction.SOUTH);
        Route differentRoute = new Route(Direction.NORTH, Direction.EAST);

        assertEquals(route1, route2);
        assertNotEquals(route1, differentRoute);
        assertNotEquals(null, route1);
    }
    
}
