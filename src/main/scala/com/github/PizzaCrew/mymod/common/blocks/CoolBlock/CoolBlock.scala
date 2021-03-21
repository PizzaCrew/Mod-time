package com.github.PizzaCrew.mymod.common.blocks.CoolBlock

import net.minecraft.block.material.Material
import net.minecraft.block.{AbstractBlock, Block, BlockRenderType, BlockState}
import net.minecraftforge.common.ToolType

class CoolBlock extends Block(AbstractBlock.Properties.of(Material.DIRT)) {
  this.setRegistryName("cool_block")
  this.properties.harvestLevel(4)
    .requiresCorrectToolForDrops()
    .harvestTool(ToolType.HOE)
    .jumpFactor(4)
    .noCollission()

  @Override
  def getRenderType(blockState: BlockState): BlockRenderType = {
    BlockRenderType.MODEL
  }
}
