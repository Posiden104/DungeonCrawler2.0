package values;

import entity.mob.Player;

public abstract class SpellVals {

	//Damage Values
	public static int fireball_damage = -(Player.LV * 4 + 50);
	public static int iceball_damage = -(Player.LV * 4 + 35);

	//Mana Cost Values
	public static int fireball_mana = 50;
	public static int iceball_mana = 60;

	//Range Values
	public static int fireball_range = 50;
	public static int skill_fireball_range = 50;
	public static int iceball_range = 75;

	//Ability things
	public static int iceball_freeze = (Player.LV * 3 + 120);

	//Time Values, cast time in seconds = value / 60
	public static int fireball_time = 30;
	public static int iceball_time = 40;

	
	
	
	
	
	
	public static void redim() {
		//Damage Values
		fireball_damage = -(Player.LV * 4 + 50);
		iceball_damage = -(Player.LV * 4 + 35);

		//Mana Cost Values
		fireball_mana = 50;
		iceball_mana = 60;

		//Range Values
		fireball_range = 50;
		iceball_range = 75;

		//Ability things
		iceball_freeze = (Player.LV * 3 + 120);

		//Time Values, cast time in seconds = value / 60
		fireball_time = 30;
		iceball_time = 40;
	}

}
