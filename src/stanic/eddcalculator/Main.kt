package stanic.eddcalculator

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import stanic.eddcalculator.commands.CalculatorCMD
import stanic.eddcalculator.events.InventoryEvents
import stanic.eddcalculator.model.CalculatorModel
import stanic.stutils.bukkit.message.send

class Main: JavaPlugin() {

    //Hash para a calculadora
    //Necessária para calcular as coisas
    val calculator = HashMap<String, CalculatorModel>()

    //Método para ativar o plugin
    override fun onEnable() {
        //Instanciando a main
        instance = this

        //Carregando outros métodos
        loadCommands()
        loadEvents()

        //Enviando mensagem de ativação
        Bukkit.getConsoleSender().send("§e[EDD-Calculator] §fAtivado!")
    }

    //Métodos para carregar comandos e eventos.
    private fun loadCommands() {
        CalculatorCMD().run(this)
    }

    private fun loadEvents() {
        InventoryEvents().onClick(this)
    }

    //instance da main
    companion object {
        lateinit var instance: Main
    }

}