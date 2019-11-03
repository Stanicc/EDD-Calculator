package stanic.eddcalculator.events

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import stanic.eddcalculator.Main
import stanic.stutils.bukkit.event.event
import stanic.stutils.bukkit.message.send

class InventoryEvents {

    //Evento para clicar no inventário
    fun onClick(m: Main) = m.event<InventoryClickEvent> { e ->
        //Verificando se o inventário é o da calculadora
        if (e.inventory.title == "§8Calculadora") {
            //Criando algumas variáveis essenciais
            val p = e.whoClicked as Player
            val item = e.currentItem
            val calculator = m.calculator[p.name]!!

            //Verificando se a pessoa clicou em um item, caso contrário iremos cancelar o código (Senão iria dar erro).
            if (item == null || item.type == Material.AIR) return@event

            //Pegando o número que a pessoa clicou e colocando para a soma.
            if (item.type == Material.SKULL_ITEM) {
                if (calculator.type == null) calculator.number = "${calculator.number}${item.itemMeta.displayName.replace("§7", "")}".toInt()
                else calculator.calculate = "${calculator.calculate}${item.itemMeta.displayName.replace("§7", "")}".toInt()

                p.send("§aVocê clicou em: §f${item.itemMeta.displayName.replace("§7", "").toInt()}")
            }

            //Verificando qual slot a pessoa clicou
            when (e.slot) {
                //Essa parte já dá para entender o que faz...
                25 -> {
                    if (calculator.number <= 0) {
                        p.send("§cEscolha o primeiro número!")
                    } else {
                        calculator.type = "+"
                        p.send("§aTipo escolhido: §f+")
                    }
                }
                34 -> {
                    if (calculator.number <= 0) {
                        p.send("§cEscolha o primeiro número!")
                    } else {
                        calculator.type = "-"
                        p.send("§aTipo escolhido: §f-")
                    }
                }
                43 -> {
                    if (calculator.number <= 0) {
                        p.send("§cEscolha o primeiro número!")
                    } else {
                        calculator.type = "/"
                        p.send("§aTipo escolhido: §f/")
                    }
                }
                52 -> {
                    if (calculator.number <= 0) {
                        p.send("§cEscolha o primeiro número!")
                    } else {
                        calculator.type = "x"
                        p.send("§aTipo escolhido: §fx")
                    }
                }
                //Fazendo a soma
                16 -> {
                    //Verificando se está faltando algo, se estiver iremos fechar o inventário e cancelar a calculadora.
                    if (calculator.number <= 0 || calculator.calculate <= 0 || calculator.type == null) {
                        p.send("§cEstá faltando alguma coisa aí...")
                        calculator.number = 0
                        calculator.calculate = 0
                        calculator.type = null
                        p.closeInventory()
                    } else {
                        //Setando a lore com o resultado no item.
                        val meta = e.inventory.getItem(4).itemMeta
                        meta.lore = listOf("", "§f${calculator.number} ${calculator.type} ${calculator.calculate} = ${when (calculator.type) {
                            "+" -> "${calculator.number + calculator.calculate}"
                            "-" -> "${calculator.number - calculator.calculate}"
                            "/" -> "${calculator.number / calculator.calculate}"
                            "x" -> "${calculator.number * calculator.calculate}"
                            else -> "An error as occurred."
                        }
                        }", "")
                        e.inventory.getItem(4).itemMeta = meta

                        //Resetando a calculadora para envitar erro.
                        calculator.number = 0
                        calculator.calculate = 0
                        calculator.type = null
                        p.send("§aResultado enviado.")
                    }
                }
            }

            e.isCancelled = true
        }
    }

}