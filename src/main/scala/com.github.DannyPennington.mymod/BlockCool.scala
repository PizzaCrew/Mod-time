package com.github.DannyPennington.mymod

import net.minecraft.block.Block
import net.minecraft.block.AbstractBlock
import net.minecraft.block.material.Material

class BlockCool extends Block(AbstractBlock.Properties.of(Material.STONE)) {
  setRegistryName("cool")
}