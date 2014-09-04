package graphics;

public class Sprite {

	public final int SIZE;
	public final int WIDTH, HEIGHT;
	protected int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;

	//Tile Sprites

	public static Sprite yellowFlower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite redFlower = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite woodFloor = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite hedge = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite stoneWall = new Sprite(16, 2, 2, SpriteSheet.tiles);
	public static Sprite coloredWall = new Sprite(16, 2, 3, SpriteSheet.tiles);
	public static Sprite water1 = new Sprite(16, 0, 5, SpriteSheet.tiles);
	public static Sprite water2 = new Sprite(16, 1, 5, SpriteSheet.tiles);
	public static Sprite cobble = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);

	public static Sprite voidSprite = new Sprite(16, 0x1b87e0);

	//Spider

	public static Sprite spider_up = new Sprite(32, 0, 0, SpriteSheet.spider);
	public static Sprite spider_down = new Sprite(32, 1, 0, SpriteSheet.spider);
	public static Sprite spider_right = new Sprite(32, 2, 0, SpriteSheet.spider);
	public static Sprite spider_left = new Sprite(32, 3, 0, SpriteSheet.spider);
	public static Sprite spider_head = new Sprite(32, 0, 1, SpriteSheet.spider);
	public static Sprite spider_dead = new Sprite(32, 1, 1, SpriteSheet.spider);

	//Zombie 

	public static Sprite zombie_up = new Sprite(32, 0, 0, SpriteSheet.zombie);
	public static Sprite zombie_down = new Sprite(32, 1, 0, SpriteSheet.zombie);
	public static Sprite zombie_right = new Sprite(32, 2, 0, SpriteSheet.zombie);
	public static Sprite zombie_left = new Sprite(32, 3, 0, SpriteSheet.zombie);
	public static Sprite zombie_head = new Sprite(32, 0, 1, SpriteSheet.zombie);
	public static Sprite zombie_dead = new Sprite(32, 1, 1, SpriteSheet.zombie);

	//Spell sprites

	public static Sprite fireball = new Sprite(16, 0, 0, SpriteSheet.spells);
	public static Sprite iceball = new Sprite(16, 1, 0, SpriteSheet.spells);
	
	public static Sprite fireball_icon = new Sprite(32, 0, 0, SpriteSheet.spells2);
	public static Sprite iceball_icon = new Sprite(32, 1, 0, SpriteSheet.spells2);
	public static Sprite skill_fireball_icon = new Sprite(32, 2, 0, SpriteSheet.spells2);
	
	//Weapon sprties
	
	public static Sprite sword = new Sprite(32, 0, 0, SpriteSheet.weapons);
	
	public static Sprite daggar = new Sprite(32, 1, 0, SpriteSheet.weapons);
	
	public static Sprite wand = new Sprite(32, 2, 0, SpriteSheet.weapons);
	
	//Effect sprites
	
	public static Sprite crosshairs = new Sprite(32, 0, 0, SpriteSheet.effects);
	public static Sprite freeze = new Sprite(32, 2, 0, SpriteSheet.effects);
	public static Sprite box = new Sprite(32, 0, 1, SpriteSheet.effects);
	public static Sprite back = new Sprite(32, 1, 1, SpriteSheet.effects);
	public static Sprite selected = new Sprite(32, 0, 2, SpriteSheet.effects);	
	
	//Number sprites

	public static Sprite zero = new Sprite(16, 0, 0, SpriteSheet.numbers);
	public static Sprite one = new Sprite(16, 1, 0, SpriteSheet.numbers);
	public static Sprite two = new Sprite(16, 2, 0, SpriteSheet.numbers);
	public static Sprite three = new Sprite(16, 3, 0, SpriteSheet.numbers);
	public static Sprite four = new Sprite(16, 4, 0, SpriteSheet.numbers);
	public static Sprite five = new Sprite(16, 5, 0, SpriteSheet.numbers);
	public static Sprite six = new Sprite(16, 6, 0, SpriteSheet.numbers);
	public static Sprite seven = new Sprite(16, 7, 0, SpriteSheet.numbers);
	public static Sprite eight = new Sprite(16, 8, 0, SpriteSheet.numbers);
	public static Sprite nine = new Sprite(16, 9, 0, SpriteSheet.numbers);

	//Player sprites

	public static Sprite player_forward = new Sprite(32, 1, 2, SpriteSheet.player);
	public static Sprite player_back = new Sprite(32, 1, 0, SpriteSheet.player);
	public static Sprite player_side = new Sprite(32, 1, 1, SpriteSheet.player);

	public static Sprite player_forward_1 = new Sprite(32, 0, 2, SpriteSheet.player);
	public static Sprite player_forward_2 = new Sprite(32, 2, 2, SpriteSheet.player);

	public static Sprite player_back_1 = new Sprite(32, 0, 0, SpriteSheet.player);
	public static Sprite player_back_2 = new Sprite(32, 2, 0, SpriteSheet.player);

	public static Sprite player_side_1 = new Sprite(32, 0, 1, SpriteSheet.player);
	public static Sprite player_side_2 = new Sprite(32, 2, 1, SpriteSheet.player);

	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		WIDTH = size;
		HEIGHT = size;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public Sprite(int width, int height, int size_width) {
		SIZE = size_width;
		WIDTH = width;
		HEIGHT = height;
	}

	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < WIDTH; y++) {
			for (int x = 0; x < HEIGHT; x++) {
				pixels[x + y * WIDTH] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
