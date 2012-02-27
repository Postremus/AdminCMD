/************************************************************************
 * This file is part of AdminCmd.									
 *																		
 * AdminCmd is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by	
 * the Free Software Foundation, either version 3 of the License, or		
 * (at your option) any later version.									
 *																		
 * AdminCmd is distributed in the hope that it will be useful,	
 * but WITHOUT ANY WARRANTY; without even the implied warranty of		
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			
 * GNU General Public License for more details.							
 *																		
 * You should have received a copy of the GNU General Public License
 * along with AdminCmd.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package be.Balor.Tools.Egg.Types;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Tools.Utils;
import be.Balor.Tools.Egg.RadiusEgg;
import be.Balor.Tools.Egg.Exceptions.ProcessingArgsException;
import be.Balor.bukkit.AdminCmd.ConfigEnum;
import be.Balor.bukkit.AdminCmd.LocaleHelper;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class HighEgg extends RadiusEgg<Integer> {

	/**
	 * @param defaultRadius
	 * @param maxRadius
	 */
	public HighEgg() {
		super(ConfigEnum.DEGG_HIGH_RADIUS.getInt(), ConfigEnum.MAXEGG_HIGH_RADIUS.getInt());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7809379720699540380L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.Balor.Tools.Egg.EggType#onEvent(org.bukkit.event.player.
	 * PlayerEggThrowEvent)
	 */
	@Override
	public void onEvent(PlayerEggThrowEvent event) {
		event.getEgg().remove();
		event.setHatching(false);
		Location loc = event.getEgg().getLocation();
		final int radius = value * value;
		int timeout = ConfigEnum.EGG_HIGH_TIMEOUT.getInt() * Utils.secInTick;
		for (Player player : Utils.getOnlinePlayers()) {
			if (player.getLocation().distanceSquared(loc) > radius)
				continue;
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, timeout, 5));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, timeout, 100));
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, timeout, 10));
			LocaleHelper.HIGH_EFFECT.sendLocale(player);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.Balor.Tools.Egg.EggType#processArguments(org.bukkit.entity.Player,
	 * be.Balor.Manager.Commands.CommandArgs)
	 */
	@Override
	protected void processArguments(Player sender, CommandArgs args) throws ProcessingArgsException {
		value = getRadius(sender, args);
	}

}
