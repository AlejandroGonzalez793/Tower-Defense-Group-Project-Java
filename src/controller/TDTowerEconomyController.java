package controller;

import model.TDModel;

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
	private TDModel model;
	
	public TDTowerEconomyController(TDModel model)
	{
		this.model = model;
	}// end TDTowerEconomyController constructor
	
	public void gainMoney(int money)
	{
		// create a new current player currency then add it to total amount
		model.setMoney(model.getMoney() + money);	
	}// end gainMoney
	
	/**
	 * 
	 * @return boolean if a purchase could be made or not.
	 */
	public boolean makePurchase(TowerObject tower)
	{
		if (model.getMoney() < tower.getCost())
		{
			return false;
		}// end if
		
		model.setMoney(model.getMoney() - tower.getCost());
		
		return true;	
	}// end makePurchase
	
	
	
	
	

}// end TDTowerEconomyController
