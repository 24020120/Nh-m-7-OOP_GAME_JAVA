package Item;

import java.awt.Color;

import GameBoard.GameBoard;
import GameObject.ShieldBarrier;

/**
 * Collectible Shield item. When picked up, it creates a temporary barrier at
 * the bottom of the screen which bounces balls.
 */
public class Shield extends Item {

	private static final int DIAMETER = 20;
	private static final double FALL_SPEED = 2.0;
	private final int shieldHeight = 30; // pixels high barrier
	private final int shieldDurationSeconds = 10; // how long the shield lasts

	public Shield(int x, int y) {
	super(x, y, DIAMETER, FALL_SPEED);
	}

	@Override
	protected Color getColor() {
		return Color.MAGENTA;
	}

	@Override
	public void applyEffect(GameBoard board) {
		// Create a shield barrier that spans the bottom and lasts for shieldDurationSeconds
		ShieldBarrier shield = new ShieldBarrier(shieldHeight, shieldDurationSeconds);
		board.setShield(shield);
	}
}
