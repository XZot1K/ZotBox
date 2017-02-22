package XZot1K.plugins.zl.libraries.locationlib;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class LocationLibrary
{

    private ZotLib plugin = ZotLib.getInstance();

    public List<Block> getBlocksInRadius(Location centerPoint, int xRadius, int yRadius, int zRadius)
    {
        List<Block> blocks = new ArrayList<>();
        for (int x = centerPoint.getBlockX() - xRadius; x < centerPoint.getBlockX() + xRadius; x++)
        {
            for (int y = centerPoint.getBlockY() - yRadius; y < centerPoint.getBlockY() + yRadius; y++)
            {
                for (int z = centerPoint.getBlockZ() - zRadius; z < centerPoint.getBlockZ() + zRadius; z++)
                {
                    Block block = centerPoint.getWorld().getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public void replaceBlocks(List<Block> blocksList, Material fromMaterial, Material toMaterial, byte fromData, byte toData)
    {
        for (Block block : blocksList)
        {
            if (block.getType() == fromMaterial && block.getData() == fromData)
            {
                block.setType(toMaterial);
                block.setData(toData);
            }
        }
    }

    public boolean isLocationBetweenTwoLocations(Location location, Location point1, Location point2)
    {
        return location.toVector().isInAABB(point1.toVector(), point2.toVector())
                || location.toVector().isInAABB(point2.toVector(), point1.toVector());
    }

    public List<Block> getBlocksInChunk(Chunk chunk)
    {
        List<Block> blocks = new ArrayList<>();
        int bx = chunk.getX() << 4;
        int bz = chunk.getZ() << 4;

        for (int xx = bx; xx < (bx + 16); xx++)
        {
            for (int zz = bz; zz < (bz + 16); zz++)
            {
                for (int yy = 0; yy < chunk.getWorld().getMaxHeight(); yy++)
                {
                    Block block = chunk.getWorld().getBlockAt(xx, yy, zz);
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public List<Entity> getEntitiesInRadius(Location centerLocation, double distance)
    {
        List<Entity> entities = new ArrayList<>();
        for (Entity entity : centerLocation.getWorld().getEntities())
        {
            if (entity.getLocation().distance(centerLocation) <= distance)
            {
                entities.add(entity);
            }
        }
        return entities;
    }

    public Direction getYawDirection(float yaw)
    {
        double rotation = (yaw - 90.0F) % 360.0F;
        if (rotation < 0.0D)
        {
            rotation += 360.0D;
        }

        if ((0.0D <= rotation) && (rotation < 45.0D))
        {
            return Direction.WEST;
        } else if ((45.0D <= rotation) && (rotation < 135.0D))
        {
            return Direction.NORTH;
        } else if ((135.0D <= rotation) && (rotation < 225.0D))
        {
            return Direction.EAST;
        } else if ((225.0D <= rotation) && (rotation < 315.0D))
        {
            return Direction.SOUTH;
        } else if ((315.0D <= rotation) && (rotation < 360.0D))
        {
            return Direction.WEST;
        }
        return null;
    }

    public void swapEntities(Entity entity1, Entity entity2)
    {
        Location loc1 = entity1.getLocation();
        Location loc2 = entity2.getLocation();
        entity2.teleport(loc1);
        entity1.teleport(loc2);
    }

    public void swapBlocks(Block block1, Block block2)
    {
        Material block1Material = block1.getType();
        byte block1Data = block1.getData();
        Material block2Material = block2.getType();
        byte block2Data = block2.getData();

        block1.setType(block2Material);
        block1.setData(block2Data);
        block2.setType(block1Material);
        block2.setData(block1Data);
    }

}
