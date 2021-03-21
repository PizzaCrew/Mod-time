package com.github.DannyPennington.mymod

import net.minecraft.block.{AbstractBlock, Block, BlockRenderType, BlockState}
import net.minecraft.block.material.Material
import net.minecraftforge.common.ToolType

class BlockCool extends Block(AbstractBlock.Properties.of(Material.STONE)) {
  this.setRegistryName("cool_block")
  this.properties.harvestLevel(4)
    .requiresCorrectToolForDrops()
    .harvestTool(ToolType.HOE)
    .jumpFactor(4)

  @Override
  def getRenderType(blockState: BlockState): BlockRenderType = {
    BlockRenderType.MODEL
  }
}