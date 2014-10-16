package main;

import graphics.Screen;
import input.Keyboard;
import input.Mouse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;
import level.SpawnLevel;
import tile.WaterTile;
import entity.mob.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int window_width = 400;
	public static int window_heigth = window_width / 16 * 9;

	public static int window_scale = 3;
	public static int screen_scale = 2;

	public static int width = window_width * window_scale;
	public static int height = window_heigth * window_scale;

	public String title = "Dungeon Crawler 2.0 ";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Mouse mouse;
	public static Level level;
	private Player player;

	private boolean running = false;

	private Screen screen;

	public static BufferedImage image = new BufferedImage(width / screen_scale, height / screen_scale, BufferedImage.TYPE_INT_RGB);
	public static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Mouse.mse = (new Point(25, 25));
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);

		screen = new Screen(width / screen_scale, height / screen_scale);

		key = new Keyboard();
		mouse = new Mouse();
		level = new SpawnLevel("/levels/level.png");
		player = new Player(22, 35, 1, key);
		player.init(level);

		addKeyListener(key);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		addMouseWheelListener(mouse);
	}

	public synchronized void starts() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fastUpdate() {
		player.fastUpdate();
	}

	public void update() {
		key.update();
		player.update();
		level.update();
		WaterTile.updateWater();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);

		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = Screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//	g.drawString("mse: " + Mouse.mse.x + "," + Mouse.mse.y, 100, 100);

		g.dispose();
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		int fups = 0;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				fastUpdate();
				fups++;
				fastUpdate();
				fups++;
				delta--;
			}

			render();
			frames++;

			mouse.update();

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps, " + fups + " fups");
				updates = 0;
				frames = 0;
				fups = 0;
			}
		}
		stop();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame = new JFrame();
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.starts();
	}

}
