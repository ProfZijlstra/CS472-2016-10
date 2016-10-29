package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameDao {
	// Players database
	private static Hashtable<String, Player> players;
	
	// Ducks database
	private static List<Duck> ducks;
	
	public static Hashtable<String, Player> Players()
	{
		if (players == null)
			players = new Hashtable<String, Player>();
		
		return players;
	}
	
	
	public static void addPlayer(Player player)
	{
		Players().put(player.getName(), player);
	}
	
	public static Player getPlayer(String name)
	{
		return Players().get(name);
	}
	
	public static void removePlayer(String name)
	{
		Players().remove(name);
	}
	
	public static List<Duck> Ducks()
	{
		if (ducks == null)
		{
			ducks = new ArrayList<>();
		}			
		
		return ducks;
	}
	
	// Generate ducks randomly from 0 - 10
	public static void generateDucks() {
		int numberOfDucks = new Random().nextInt(6) + 1;
		for (int i = 0; i < numberOfDucks; i++)
		{
			Duck duck = new Duck();
			duck.setId(i + 1);
			
			int top = (int) (Math.random() * 100);			
			int left = - (int) (Math.random() * 200);
			int direction = (int) (Math.random() * 2);
			duck.setTop(top);
			duck.setLeft(left);
			duck.setDirection(direction);
			duck.setDead(false);
			
			GameDao.addDuck(duck);
		}
	}
	
	public static void addDuck(Duck duck) {
		Ducks().add(duck);
	}
	
	public static void removeDucks() {
		ducks = Ducks().stream()
					.filter(d -> !d.isDead())
					.collect(Collectors.toList());
	}
}
