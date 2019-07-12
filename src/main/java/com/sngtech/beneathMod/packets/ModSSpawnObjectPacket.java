package com.sngtech.beneathMod.packets;

import java.io.IOException;
import java.util.UUID;

import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSpawnGlobalEntityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

public class ModSSpawnObjectPacket extends SSpawnGlobalEntityPacket implements IPacket<IClientPlayNetHandler>
{
	private int speedX;
	private int speedY;
	private int speedZ;
	private int data;
	private EntityType<?> type;
	private int yaw;
	private int pitch;
	private double x;
	private double y;
	private double z;
	private UUID uniqueId;
	private int entityId;

	public ModSSpawnObjectPacket(int entityID, UUID uuid, double posX, double posY, double posZ, float pitch, float yaw, EntityType<?> type, int data, Vec3d velocity) 
	{
		this.entityId = entityID;
		this.uniqueId = uuid;
		this.x = posX;
		this.y = posY;
		this.z = posZ;
		this.pitch = MathHelper.floor(pitch * 256.0F / 360.0F);
		this.yaw = MathHelper.floor(yaw * 256.0F / 360.0F);
		this.type = type;
		this.data = data;
		this.speedX = (int)(MathHelper.clamp(velocity.x, -3.9D, 3.9D) * 8000.0D);
		this.speedY = (int)(MathHelper.clamp(velocity.y, -3.9D, 3.9D) * 8000.0D);
		this.speedZ = (int)(MathHelper.clamp(velocity.z, -3.9D, 3.9D) * 8000.0D);
	 }

	public ModSSpawnObjectPacket(Entity entity) 
	{
		this(entity, 0);
	}

	public ModSSpawnObjectPacket(Entity entityIn, int typeIn) 
	{
		this(entityIn.getEntityId(), entityIn.getUniqueID(), entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.rotationPitch, entityIn.rotationYaw, entityIn.getType(), typeIn, entityIn.getMotion());
	}

	public ModSSpawnObjectPacket(Entity entity, EntityType<?> type, int data, BlockPos blockPos) 
	{
		this(entity.getEntityId(), entity.getUniqueID(), (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), entity.rotationPitch, entity.rotationYaw, type, data, entity.getMotion());
	}
	
	

	@SuppressWarnings("deprecation")
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException 
	{
	      this.entityId = buf.readVarInt();
	      this.uniqueId = buf.readUniqueId();
	      this.type = Registry.ENTITY_TYPE.getByValue(buf.readVarInt());
	      this.x = buf.readDouble();
	      this.y = buf.readDouble();
	      this.z = buf.readDouble();
	      this.pitch = buf.readByte();
	      this.yaw = buf.readByte();
	      this.data = buf.readInt();
	      this.speedX = buf.readShort();
	      this.speedY = buf.readShort();
	      this.speedZ = buf.readShort();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void writePacketData(PacketBuffer buf) throws IOException 
	{
	      buf.writeVarInt(this.entityId);
	      buf.writeUniqueId(this.uniqueId);
	      buf.writeVarInt(Registry.ENTITY_TYPE.getId(this.type));
	      buf.writeDouble(this.x);
	      buf.writeDouble(this.y);
	      buf.writeDouble(this.z);
	      buf.writeByte(this.pitch);
	      buf.writeByte(this.yaw);
	      buf.writeInt(this.data);
	      buf.writeShort(this.speedX);
	      buf.writeShort(this.speedY);
	      buf.writeShort(this.speedZ);
	}

	@Override
	public void processPacket(IClientPlayNetHandler handler) 
	{
		handler.handleSpawnGlobalEntity(this);
	}
}