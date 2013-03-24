package game;

import game.entities.Entity;
import game.entities.Ship;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class GameField extends Canvas implements KeyListener {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	private static final long serialVersionUID = 1L;
	private static final long NANOS_IN_SECOND = 1000000000;
	private static final long FPS = 30;
	private static final double NSPF = NANOS_IN_SECOND / FPS;
	
	private int level;
	private boolean alive;
	private boolean paused;
	private List<Entity> entities;
	private Ship player;
	
	public GameField() {
		level = 1;
		alive = true;
		paused = false;
		entities = new ArrayList<Entity>();
		
		this.setBackground(new Color(0x292b36));
		this.addKeyListener(InputHandler.getInstance());
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void start() {
		initializeEntities();
		loop();
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {
			this.paused = !this.paused;
		}
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	private void initializeEntities() {
		int asteroidCount = level * 2;
		
		player = new Ship(WIDTH / 2, HEIGHT / 2);
		
		for (int i = 0; i < asteroidCount; i++) {
			// Add asteroid here
		}
	}
	
	private void loop() {
		long delta, now, lastLoop = System.nanoTime();
		long lastFrame = 0;
		long lastSecond = 0;
		
		while(alive && !paused) {
			// Adjust counters
			now = System.nanoTime();
			delta = now - lastLoop;
			lastLoop = now;
			
			lastFrame += delta;
			lastSecond += delta;
			
			// Process every loop
			update(delta);
			
			// Process once for every frame in a second
			if (lastFrame >= NSPF) {
				lastFrame = 0;
				render(delta);
			}
			
			// Process once per second
			if (lastSecond >= NANOS_IN_SECOND) {
				lastSecond = 0;
			}
		}
	}
	
	private void update(long delta) {
		player.update(delta);
		
		for (Entity e : entities) {
			e.update(delta);
		}
	}
	
	private void render(long delta) {
		// Prepare buffer strategy and graphics
		BufferStrategy bufferStrategy = getBufferStrategy();

		if (bufferStrategy == null) {
			createBufferStrategy(2);
			return;
		}
		
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g);
		
		// Render entities
		Renderer.render(player, g);
		
		for (Entity e : entities) {
			Renderer.render(e, g);
		}
		
		// Clean up and show
		g.dispose();
		bufferStrategy.show();
	}
}