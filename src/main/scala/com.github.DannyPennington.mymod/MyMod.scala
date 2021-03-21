package com.github.DannyPennington.mymod

import net.minecraft.block.{AbstractBlock, Block, Blocks}
import net.minecraftforge.common.{MinecraftForge, ToolType}
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import org.apache.logging.log4j.LogManager

import java.util.stream.Collectors
import net.minecraftforge.scorge.lang.ScorgeModLoadingContext
import com.github.DannyPennington.mymod.BlockCool
import com.github.DannyPennington.mymod.MyMod.LOGGER
import net.minecraft.block.material.Material
import net.minecraft.item.{BlockItem, Item, ItemGroup}
import net.minecraft.item.Item.Properties

// The value here should match an entry in the META-INF/mods.toml file
@Mod("mymod")
object MyMod { // Directly reference a log4j logger.
  private val LOGGER = LogManager.getLogger

  // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
  // Event bus for receiving Registry Events)
  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  object RegistryEvents {
    @SubscribeEvent
    def onBlocksRegistry(blockRegistryEvent: RegistryEvent.Register[Block]): Unit = { // register a new block here
      LOGGER.info("HELLO from Register Block")
    }
  }
}

@Mod("mymod")
class MyMod() { // Register the setup method for modloading

  val eventBus: net.minecraftforge.eventbus.api.IEventBus = ScorgeModLoadingContext.get.getModEventBus
  eventBus.addListener(this.setup)
  // Register the enqueueIMC method for modloading
  eventBus.addListener(this.enqueueIMC)
  // Register the processIMC method for modloading
  eventBus.addListener(this.processIMC)
  // Register the doClientStuff method for modloading
  eventBus.addListener(this.doClientStuff)

  eventBus.register(this)
  // Register ourselves for server and other game events we are interested in
  MinecraftForge.EVENT_BUS.register(this)

  private def setup(event: FMLCommonSetupEvent): Unit = { // some preinit code
    MyMod.LOGGER.info("HELLO FROM PREINIT")
    MyMod.LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName)
  }

  private def doClientStuff(event: FMLClientSetupEvent): Unit = { // do something that can only be done on the client
    MyMod.LOGGER.info("Got game settings {}", event.getMinecraftSupplier.get.options)
  }

  private def enqueueIMC(event: InterModEnqueueEvent): Unit = { // some example code to dispatch IMC to another mod
    InterModComms.sendTo("examplemod", "helloworld", () => {
      def foo() = {
        MyMod.LOGGER.info("Hello world from the MDK")
        "Hello world"
      }

      foo()
    })
  }

  private def processIMC(event: InterModProcessEvent): Unit = { // some example code to receive and process InterModComms from other mods
    MyMod.LOGGER.info("Got IMC {}", event.getIMCStream.map((m: InterModComms.IMCMessage) => m.getMessageSupplier.get).collect(Collectors.toList))
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  def onServerStarting(event: FMLServerStartingEvent): Unit = { // do something when the server starts
    MyMod.LOGGER.info("HELLO from server starting")
  }

  val block: Block = new BlockCool

  @SubscribeEvent
  def registerBlocks(event: RegistryEvent.Register[Block]): Unit = {
    LOGGER.debug(s"REGISTERING MY TASTY BLOCKS NOW: ${block.toString}")
    event.getRegistry.register(block)
  }

  @SubscribeEvent
  def registerItems(event: RegistryEvent.Register[Item]): Unit = {
    val properties = new Properties
    val blockItem = new BlockItem(block, properties.stacksTo(69).tab(ItemGroup.TAB_BUILDING_BLOCKS)).setRegistryName(block.getRegistryName)
    LOGGER.debug(s"REGISTERING MY TASTY ITEMS NOW: ${blockItem.toString}")
    event.getRegistry.register(blockItem)
  }

}
