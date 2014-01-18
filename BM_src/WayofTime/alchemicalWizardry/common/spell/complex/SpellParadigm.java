package WayofTime.alchemicalWizardry.common.spell.complex;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellEffect;

public abstract class SpellParadigm 
{
	protected List<SpellEffect> bufferedEffectList = new ArrayList();
	
	public void addBufferedEffect(SpellEffect effect)
	{
		if(effect!=null)
		{
			this.bufferedEffectList.add(effect);
		}
	}
	
	public void modifyBufferedEffect(SpellModifier modifier)
	{
		SpellEffect effect = this.getBufferedEffect();
		if(effect!=null)
		{
			effect.modifyEffect(modifier);
		}
	}
	
	public void applyEnhancement(SpellEnhancement enh)
	{
		if(bufferedEffectList.isEmpty())
		{
			this.enhanceParadigm(enh);
		}
		else
		{
			SpellEffect effect = this.getBufferedEffect();
			if(effect!=null)
			{
				effect.enhanceEffect(enh);
			}
		}
	}
	
	public abstract void enhanceParadigm(SpellEnhancement enh);
	public abstract void castSpell(World world, EntityPlayer entityPlayer, ItemStack itemStack);
	
	public void applySpellEffect(SpellEffect effect)
	{
		effect.modifyParadigm(this);
	}
	
	public void applyAllSpellEffects()
	{
		for(SpellEffect effect : bufferedEffectList)
		{
			this.applySpellEffect(effect);
		}
	}
	
	public SpellEffect getBufferedEffect()
	{
		if(bufferedEffectList.isEmpty())
		{
			return null;
		}
		else
		{
			return bufferedEffectList.get(bufferedEffectList.size()-1);
		}
	}
}