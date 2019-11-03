package stanic.eddcalculator.commands

import org.bukkit.entity.Player
import stanic.eddcalculator.Main
import stanic.eddcalculator.utils.Menus
import stanic.stutils.bukkit.command.command
import stanic.stutils.bukkit.message.send

class CalculatorCMD {

    fun run(m: Main) = m.command("calculadora") { sender, args ->
        if (sender !is Player) {
            sender.send("§cSomente jogadores...")
            return@command
        }

        Menus().openMainMenu(sender)
    }

}