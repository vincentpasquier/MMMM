package ch.eiafr.mmmm.messages;

public enum Direction {
	UP_LEFT(-1, -1), UP(0, -1), UP_RIGHT(1, -1), LEFT(-1, 0), CENTER(0, 0), RIGHT(1, 0), DOWN_LEFT(-1, 1), DOWN(0, 1), DOWN_RIGHT(1, 1);

	private final int x;
	private final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public static Direction getDirection(Tasks task) {
		switch (task) {
		case SIGHT_UP_LEFT:
			return UP_LEFT;
		case SIGHT_UP:
			return UP;
		case SIGHT_UP_RIGHT:
			return UP_RIGHT;
		case SIGHT_LEFT:
			return LEFT;
		case SIGHT_CENTER:
			return CENTER;
		case SIGHT_RIGHT:
			return RIGHT;
		case SIGHT_DOWN_LEFT:
			return DOWN_LEFT;
		case SIGHT_DOWN:
			return DOWN;
		case SIGHT_DOWN_RIGHT:
			return DOWN_RIGHT;
		default:
			return null;
		}
	}
}
