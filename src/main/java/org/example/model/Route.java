package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.road.Direction;

import java.util.Objects;

@Getter
@Setter
public class Route {
    private final Direction start;
    private final Direction end;

    public Route(Direction startDirection, Direction endDirection) {
        this.start = startDirection;
        this.end = endDirection;
    }

    public boolean hasRoutePriority(Route other) {
        if (!this.isCollidingWith(other)) {
            return true;
        }

        if (this.isTurningRight() && !other.isGoingForward()) {
            return true;
        }

        return isRightOf(other.start);
    }

    private boolean isCollidingWith(Route other) {
        if (other.end == end) return true;
        if (isGoingForward() && other.isGoingForward() && isPerpendicularly(this.start, other.start)) return true;
        if (isGoingForward() && other.isGoingForward() && !isPerpendicularly(this.start, other.start)) return false;
        if (isTurningLeft() && other.isGoingForward()) return true;
        if (isGoingForward() && other.isGoingForward()) return true;
        if (other.isTurningLeft() && isGoingForward()) return true;

        return false;
    }

    private boolean isPerpendicularly(Direction a, Direction b) {
        return (a == Direction.EAST && b == Direction.NORTH) ||
                (a == Direction.EAST && b == Direction.SOUTH) ||
                (a == Direction.WEST && b == Direction.NORTH) ||
                (a == Direction.WEST && b == Direction.SOUTH) ||
                (a == Direction.NORTH && b == Direction.EAST) ||
                (a == Direction.NORTH && b == Direction.WEST) ||
                (a == Direction.SOUTH && b == Direction.EAST) ||
                (a == Direction.SOUTH && b == Direction.WEST);
    }


    private boolean isRightOf(Direction otherDirection) {
        return switch (otherDirection) {
            case NORTH -> this.start == Direction.WEST;
            case EAST -> this.start == Direction.NORTH;
            case SOUTH -> this.start == Direction.EAST;
            case WEST -> this.start == Direction.SOUTH;
        };
    }


    private boolean isRightOfDirection(Direction thisDirection, Direction otherDirection) {
        return switch (otherDirection) {
            case NORTH -> thisDirection == Direction.WEST;
            case EAST -> thisDirection == Direction.NORTH;
            case SOUTH -> thisDirection == Direction.EAST;
            case WEST -> thisDirection == Direction.SOUTH;
        };
    }


    public boolean isTurningRight() {
        return (start == Direction.NORTH && end == Direction.WEST) ||
                (start == Direction.WEST && end == Direction.SOUTH) ||
                (start == Direction.SOUTH && end == Direction.EAST) ||
                (start == Direction.EAST && end == Direction.NORTH);
    }

    public boolean isTurningLeft() {
        return (start == Direction.NORTH && end == Direction.EAST) ||
                (start == Direction.WEST && end == Direction.NORTH) ||
                (start == Direction.SOUTH && end == Direction.WEST) ||
                (start == Direction.EAST && end == Direction.SOUTH);
    }

    public boolean isGoingForward() {
        return (start == Direction.NORTH && end == Direction.SOUTH) ||
                (start == Direction.WEST && end == Direction.EAST) ||
                (start == Direction.SOUTH && end == Direction.NORTH) ||
                (start == Direction.EAST && end == Direction.WEST);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return start == route.start && end == route.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Route{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
