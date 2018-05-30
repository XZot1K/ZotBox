package XZot1K.plugins.zb.libraries.locationlib;

import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

enum Direction
{
    NORTH, SOUTH, EAST, WEST
}

public class LocationLibrary
{

    /**
     * Gets all blocks in a radius around the fed center location.
     *
     * @param centerPoint The location to get all blocks around.
     * @param xRadius     The X radius.
     * @param yRadius     The Y Radius.
     * @param zRadius     The Z Radius.
     * @return A list of all the blocks found.
     */
    public List<Block> getBlocksInRadius(Location centerPoint, int xRadius, int yRadius, int zRadius)
    {
        List<Block> blocks = new ArrayList<>();
        for (int x = (centerPoint.getBlockX() - xRadius) - 1; ++x < centerPoint.getBlockX() + xRadius; )
        {
            for (int y = (centerPoint.getBlockY() - yRadius) - 1; ++y < centerPoint.getBlockY() + yRadius; )
            {
                for (int z = (centerPoint.getBlockZ() - zRadius) - 1; ++z < centerPoint.getBlockZ() + zRadius; )
                {
                    Block block = centerPoint.getWorld().getBlockAt(x, y, z);
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    /**
     * Replaces all blocks in the fed list with the specified material and data to the new material and data.
     *
     * @param blocksList   The list of blocks to replace.
     * @param fromMaterial The material that a block has in the block list.
     * @param toMaterial   The material that you want the blocks to change to.
     * @param fromData     The data that a block has in the block list.
     * @param toData       The data that you want the blocks to change to.
     */
    @SuppressWarnings("deprecation")
    public void replaceBlocks(List<Block> blocksList, Material fromMaterial, Material toMaterial, byte fromData,
                              byte toData)
    {
        for (int i = -1; ++i < blocksList.size(); )
        {
            Block block = blocksList.get(i);
            if (block.getType() == fromMaterial && block.getData() == fromData)
            {
                block.setType(toMaterial);
                block.setData(toData);
            }
        }
    }

    /**
     * Gets if the defined location is between the 2 specified points. It's just like the well-known plugin WorldEdit.
     *
     * @param location The location you want to check is between the two locations.
     * @param point1   The 1st location.
     * @param point2   The 2nd location.
     * @return Whether the location is in fact between the two locations or not.
     */
    public boolean isLocationBetweenTwoLocations(Location location, Location point1, Location point2)
    {
        return ((location.getBlockX() <= point1.getBlockX() && location.getBlockX() >= point2.getBlockX())
                || (location.getBlockX() <= point2.getBlockX() && location.getBlockX() >= point1.getBlockX()))
                && ((location.getBlockY() <= point1.getBlockY() && location.getY() >= point2.getBlockY())
                || (location.getBlockY() <= point2.getBlockY() && location.getBlockY() >= point1.getBlockY()))
                && ((location.getBlockZ() <= point1.getZ() && location.getBlockZ() >= point2.getBlockZ())
                || (location.getBlockZ() <= point2.getBlockZ() && location.getBlockZ() >= point1.getBlockZ()));
    }

    /**
     * Gets all the blocks in the specified chunk.
     *
     * @param chunk The chunk you want to get all blocks from.
     * @return A list of all the found blocks.
     */
    public List<Block> getBlocksInChunk(Chunk chunk)
    {
        List<Block> blocks = new ArrayList<>();
        int bx = chunk.getX() << 4;
        int bz = chunk.getZ() << 4;

        for (int xx = bx - 1; ++xx < (bx + 16); )
        {
            for (int zz = bz - 1; ++zz < (bz + 16); )
            {
                for (int yy = -1; ++yy < chunk.getWorld().getMaxHeight(); )
                {
                    Block block = chunk.getWorld().getBlockAt(xx, yy, zz);
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    /**
     * Gets all entities in a radius around the location within the defined distance.
     *
     * @param centerLocation The center location to find entities around.
     * @param distance       The distance from the specified center location.
     * @return A list off all the found entities.
     */
    public List<Entity> getEntitiesInRadius(Location centerLocation, double distance)
    {
        List<Entity> entities = new ArrayList<>();
        for (int i = -1; ++i < centerLocation.getWorld().getEntities().size(); )
        {
            Entity entity = centerLocation.getWorld().getEntities().get(i);
            if (entity.getLocation().distance(centerLocation) <= distance)
                entities.add(entity);
        }
        return entities;
    }

    /**
     * Gets a direction based on the fed yaw value.
     *
     * @param yaw The yaw from a location (player or entity to be specific).
     * @return The direction that was found/calculated.
     */
    public Direction getDirection(float yaw)
    {
        double rotation = (yaw - 90.0F) % 360.0F;
        if (rotation < 0.0D) rotation += 360.0D;
        if ((0.0D <= rotation) && (rotation < 45.0D)) return Direction.WEST;
        else if ((45.0D <= rotation) && (rotation < 135.0D)) return Direction.NORTH;
        else if ((135.0D <= rotation) && (rotation < 225.0D)) return Direction.EAST;
        else if ((225.0D <= rotation) && (rotation < 315.0D)) return Direction.SOUTH;
        else if ((315.0D <= rotation) && (rotation < 360.0D)) return Direction.WEST;
        return null;
    }

    /**
     * Swaps the two entities locations.
     *
     * @param entity1 The 1st entity you want to switch with the 2nd.
     * @param entity2 The 2nd entity you want to switch with the 1st.
     */
    public void swapEntities(Entity entity1, Entity entity2)
    {
        Location loc1 = entity1.getLocation();
        Location loc2 = entity2.getLocation();
        entity2.teleport(loc1);
        entity1.teleport(loc2);
    }

    /**
     * Swaps the 2 fed blocks locations.
     *
     * @param block1 The 1st block you want to swap with the 2nd.
     * @param block2 The 2nd block you want to swap with the 1st.
     */
    @SuppressWarnings("deprecation")
    public void swapBlocks(Block block1, Block block2, boolean useEffects)
    {
        Material block1Material = block1.getType();
        byte block1Data = block1.getData();
        Material block2Material = block2.getType();
        byte block2Data = block2.getData();

        block1.setType(block2Material);
        block1.setData(block2Data);
        block2.setType(block1Material);
        block2.setData(block1Data);

        if (useEffects)
        {
            block1.getWorld().playEffect(block1.getLocation(), Effect.STEP_SOUND, block1.getTypeId());
            block2.getWorld().playEffect(block2.getLocation(), Effect.STEP_SOUND, block2.getTypeId());
        }
    }

    /**
     * Gets a yaw value based on the block face fed to it.
     *
     * @param blockFace The blockface you want to calculate the yaw from.
     * @return The yaw that was calculated from the blockface.
     */
    public double getYaw(BlockFace blockFace)
    {
        switch (blockFace)
        {
            case EAST:
                return (float) 90;
            case SOUTH:
                return (float) 180;
            case WEST:
                return (float) 270;
            default:
                return (float) 0;
        }
    }

}
