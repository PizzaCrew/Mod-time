package com.github.PizzaCrew.mymod.common.materials

import net.minecraft.block.AbstractBlock.Properties
import net.minecraft.block.{AbstractBlock, Block}
import net.minecraft.block.material.Material.Builder
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraft.block._

object CoolBlockMat {
  val coolBlockMat: Material = new Builder(MaterialColor.DIRT).build()

}
