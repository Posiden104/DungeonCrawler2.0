package graphics;

public class LargeSprite extends Sprite{

	
	public static LargeSprite bronze = new LargeSprite(128, 16, 1, 0, SpriteSheet.bars);
	public static LargeSprite silver = new LargeSprite(128, 16, 1, 1, SpriteSheet.bars);
	public static LargeSprite gold = new LargeSprite(128, 16, 1, 2, SpriteSheet.bars);
	
	public static LargeSprite back = new LargeSprite(128, 16, 0, 0, SpriteSheet.bars);
	public static LargeSprite health = new LargeSprite(128, 16, 0, 1, SpriteSheet.bars);
	public static LargeSprite mana = new LargeSprite(128, 16, 0, 2, SpriteSheet.bars);
	
	
	public static LargeSprite big_bronze = new LargeSprite(128, 32, 1, 3, SpriteSheet.bars);
	public static LargeSprite big_silver = new LargeSprite(128, 32, 1, 4, SpriteSheet.bars);
	public static LargeSprite big_gold = new LargeSprite(128, 32, 1, 5, SpriteSheet.bars);
	
	public static LargeSprite enemy_health = new LargeSprite(128, 32, 0, 5, SpriteSheet.bars);
	public static LargeSprite floatingHealth = new LargeSprite(32, 16, 0, 3, SpriteSheet.bars);
	
	
	public LargeSprite(int width, int height, int x, int y, SpriteSheet sheet) {
		super(width, height, width);
		this.x = x * width;
		this.y = y * height;
		pixels = new int[width * height];
		load(width, height, sheet);
	}
	
	protected void load(int width, int height, SpriteSheet sheet) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
