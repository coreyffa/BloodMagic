package WayofTime.alchemicalWizardry.common.demonVillage.demonHoard.demon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.AlchemicalWizardry;
import WayofTime.alchemicalWizardry.common.entity.projectile.WindGustProjectile;
import WayofTime.alchemicalWizardry.common.spell.complex.effect.SpellHelper;

public class EntityMinorDemonGruntGuardianWind extends EntityMinorDemonGruntGuardian
{
	public EntityMinorDemonGruntGuardianWind(World par1World) 
	{
		super(par1World);
		this.setDemonID(AlchemicalWizardry.entityMinorDemonGruntGuardianWindID);
	}

	@Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        int i = this.isTamed() ? 25 : 25;
        
        if(par1Entity instanceof IHoardDemon && ((IHoardDemon) par1Entity).isSamePortal(this))
        {
        	return false;
        }
        
        if (par1Entity instanceof EntityPlayer)
        {
            SpellHelper.setPlayerSpeedFromServer((EntityPlayer) par1Entity, par1Entity.motionX, par1Entity.motionY + 4, par1Entity.motionZ);
        } else if (par1Entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) par1Entity).motionY += 4.0D;
        }
        
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) i);
    }
    
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.fallDistance = 0;
	}
	
    /**
     * Attack the specified entity using a ranged attack.
     */
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2)
    {
    	if(par1EntityLivingBase instanceof IHoardDemon && ((IHoardDemon) par1EntityLivingBase).isSamePortal(this))
        {
        	return;
        }
        double xCoord;
        double yCoord;
        double zCoord;
        WindGustProjectile hol = new WindGustProjectile(worldObj, this, par1EntityLivingBase, 1.8f, 0f, 20, 600);
        this.worldObj.spawnEntityInWorld(hol);
    }
}
