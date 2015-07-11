package de.mauricius17.devathlon.competitive.economy;

public enum BlockEconomy {
	
	STONE(1),
	GRASS(0.5),
	DIRT(0.5),
	COBBLESTONBE(1),
	WOOD(1),
	LOG(1),
	LOG_2(1),
	WOOL(2),
	SAND(2),
	GRAVEL(3),
	IRON_ORE(15),
	GOLD_ORE(20),
	COAL_ORE(7),
	REDSTONE_ORE(7),
	LAPIS_ORE(10),
	DIAMOND_ORE(50),
	WHEAT(2),
	POTATO(1),
	CARROT(1),
	CACTUS(3),
	COCOA(1),
	MELONE_BLOCK(2),
	MELONE(2),
	SUGAR_CANE(3),
	SUGAR_CANE_BLOCK(3),
	ICE(2),
	VINES(1),
	FENCE(0.5),
	CLAY(1),
	YELLOW_FLOWER(0.5),
	RED_ROSE(0.5),
	BROWN_MUSHROOM(1),
	RED_MUSHROOM(1),
	PLANT(0.5),
	SNOW(2),
	SNOW_BLOCK(2),
	EMERALD_ORE(25),
	LEAVES(0.5),
	SANSTONE(2),
	PRISMARINE(4),
	PRISMARINE_CRYSTALS(4),
	PRISMARINE_SHARD(4),
	GLASS(4);
	
	double prize;
	
	private BlockEconomy(double prize) {
		this.prize = prize;
	}
	
	public double getPrize() {
		return prize;
		
	}
}
