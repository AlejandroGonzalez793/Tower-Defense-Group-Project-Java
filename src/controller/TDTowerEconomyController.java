package controller;

import java.lang.Math; 
import model.Player;
import model.Tower;

/**
 * The TDTowerEconomyController class has a collection of methods that
 * handles the player's purchases and sales.
 * 
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 */
public class TDTowerEconomyController 
{
	static final int MONEY_GAINED = 50;
	
	private Player player;
	private Thread playerGainOverTime;
	
	public TDTowerEconomyController(Player player)
	{
		this.player = player;
	}// end TDTowerEconomyController constructor
	
	/**
	 * The gainMoney method will add money to the players total money.
	 * 
	 * @param money Integer that is the amount of money to be added to player's total money.
	 */
	public void gainMoney(int money)
	{
		player.setMoney(player.getMoney() + money);	
	}// end gainMoney
	
	/**
	 * The makePurchase method will subtract the player's total money from the
	 * cost of the tower object. 
	 * 
	 * @param Tower object.
	 * @return boolean if a purchase could be made or not.
	 */
	public boolean makePurchase(Tower tower)
	{
		if (player.getMoney() < tower.getCost())
		{
			return false;
		}// end if
		
		player.setMoney(player.getMoney() - tower.getCost());
		
		return true;	
	}// end makePurchase
	
	/**
	 * The sellBackTower method will add to the player's money the cost of the tower
	 * being sold at a decreased amount. 
	 * 
	 * @param Tower object.
	 */
	public void sellBackTower(Tower tower)
	{
		player.setMoney(player.getMoney() + (int)Math.round(tower.getCost() * tower.SELLBACK_FACTOR));	
	}// end makePurchase

	/**
	 * The playerCurrencyGain method creates a new thread that will constantly
	 * give the player money every few seconds.
	 */
	public void playerCurrencyGain() {
		playerGainOverTime = new Thread() {
			@Override
			public void run() {
				try {
					
					player.setMoney(player.getMoney() + MONEY_GAINED);
					Thread.sleep(2000);// 2 seconds
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.out.println("interrupted");
				}// end try catch


			}// end run
		};// end thread
		playerGainOverTime.start();
 
	}// end playerCurrencyGain
	
	/*
	 * The closeplayerCurrencyGainThread will interrupt the thread to close it.
	 */
	public void closeplayerCurrencyGainThread()
	{
		playerGainOverTime.interrupt();
		//playerGainOverTime.stop();
		System.gc();
	}// end closeplayerCurrencyGainThread
	
}// end TDTowerEconomyController
