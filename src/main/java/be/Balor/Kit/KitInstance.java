package be.Balor.Kit;

import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitInstance implements ConfigurationSerializable {

        /**
         * @param name
         * @param delay
         */
        public KitInstance(final String name, final int delay, final Player player) {
                super();
                
        }

        /**
         * Get ItemStacks for given kit
         *
         * @param kit
         * @return
         */
        public List<ItemStack> getItemStacks() {
                return null;
        }

		@Override
		public Map<String, Object> serialize() {
			// TODO Auto-generated method stub
			return null;
		}
}
