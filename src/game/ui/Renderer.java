package game.ui;

import game.entities.Alien;
import game.entities.Asteroid;
import game.entities.BoostPowerup;
import game.entities.Bullet;
import game.entities.Entity;
import game.entities.Powerup;
import game.entities.Ship;
import game.entities.TripleShotPowerup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Responsible for rendering entities.
 *
 */
public class Renderer {
	private static Color WHITE = new Color(0xF0F0F0);
	private static Color STEEL = new Color(0xB0C4DE);
	private static Color YELLOW = new Color(0xEDD808);
	private static Color PURPLE = new Color(0x693D99);
	
	private static Color ALIEN_COLOR = STEEL;
	private static Color ASTEROID_COLOR = WHITE;
	private static Color HUD_COLOR = WHITE;
	private static Color SHIP_COLOR = WHITE;
	private static Color BOOST_COLOR = YELLOW;
	private static Color TRIPLE_SHOT_COLOR = PURPLE;
	private static Font POINTS_FONT = new Font("Consolas", Font.PLAIN, 14);
	
	/**
	 * Delegates rendering the entity to its proper method if it exists.
	 * @param e the entity
	 * @param g the graphics object
	 */
	public static void render(Entity e, Graphics2D g) {
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		
		if (e instanceof Asteroid) {
			renderAsteroid((Asteroid) e, g);
		} else if (e instanceof Ship) {
			renderShip((Ship) e, g);
		} else if (e instanceof Bullet) {
			renderBullet((Bullet) e, g);
		} else if (e instanceof Alien) {
			renderAlien((Alien) e, g);
		} else if (e instanceof Powerup) {
			renderPowerup((Powerup) e, g);
		}
		
		g.setFont(oldFont);
		g.setColor(oldColor);
	}
	
	/**
	 * Renders the HUD (points, level).
	 * @param points the points to render
	 * @param points the level to render
	 * @param g the graphics object
	 */
	public static void renderHUD(long points, int level, Graphics2D g) {
		g.setFont(POINTS_FONT);
		g.setColor(HUD_COLOR);
		g.drawString("Points: " + points, 4, 16);
		g.drawString(" Level: " + level, 4, 32);
	}
	
	/**
	 * Renders a ship entity.
	 * @param e the ship
	 * @param g the graphics object
	 */
	private static void renderShip(Ship e, Graphics2D g) {
		g.setColor(getShipColor(e));
		g.drawPolygon((Polygon)e.getBounds());
	}
	
	/**
	 * Renders a bullet entity.
	 * @param e the bullet
	 * @param g the graphics object
	 */
	private static void renderBullet(Bullet e, Graphics2D g) {
		g.setColor(ASTEROID_COLOR);
		g.fill((Rectangle)e.getBounds());
	}
	
	/**
	 * Renders an asteroid entity.
	 * @param e the asteroid
	 * @param g the graphics object
	 */
	private static void renderAsteroid(Asteroid e, Graphics2D g) {
		g.setColor(ASTEROID_COLOR);
		g.drawPolygon((Polygon)e.getBounds());
	}
	
	/**
	 * Renders an alien entity.
	 * @param e the alien
	 * @param g the graphics object
	 */
	private static void renderAlien(Alien e, Graphics2D g) {
		g.setColor(ALIEN_COLOR);
		g.drawPolygon((Polygon)e.getBounds());
	}
	
	/**
	 * Renders a powerup entity.
	 * @param e the powerup
	 * @param g the graphics object
	 */
	private static void renderPowerup(Powerup e, Graphics2D g) {
		g.setColor(getPowerupColor(e));
		g.draw(e.getBounds());
	}
	
	/**
	 * Returns the correct ship color.
	 * @param ship the ship
	 * @return the correct ship color
	 */
	private static Color getShipColor(Ship ship) {
		if (ship.hasBoost()) {
			return BOOST_COLOR;
		} else if (ship.hasTripleShot()) {
			return TRIPLE_SHOT_COLOR;
		} else {
			return SHIP_COLOR;
		}
	}
	
	/**
	 * Returns the correct powerup color.
	 * @param powerup the powerup
	 * @return the correct powerup color.
	 */
	private static Color getPowerupColor(Powerup powerup) {
		if (powerup instanceof BoostPowerup) {
			return BOOST_COLOR;
		} else if (powerup instanceof TripleShotPowerup) {
			return TRIPLE_SHOT_COLOR;
		} else {
			return SHIP_COLOR;
		}
	}
}
