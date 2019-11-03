package stanic.eddcalculator.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import stanic.eddcalculator.Main
import stanic.eddcalculator.model.CalculatorModel
import stanic.stutils.server.utils.ItemBuilder
import stanic.stutils.server.utils.SkullUtils

class Menus {

    //Método para abrir o menu da calculadora
    fun openMainMenu(p: Player) {
        //Verificando se a pessoa está na hash da calculadora, caso não esteja iremos adicionar.
        if (!Main.instance.calculator.containsKey(p.name)) {
            //criando uma nova calculadora
            val model = CalculatorModel(0, 0, null)
            //adicionando a pessoa na hash
            Main.instance.calculator[p.name] = model
        }

        //Criando o inventário
        val inv = Bukkit.createInventory(null, 54, "§8Calculadora")
        //Definindo o slot onde os números serão setados
        var slot = 20
        //Fazendo um for do 0 ao 9 (Números da calculadora)
        for (i in 0..9) {
            //Setando o item no slot
            inv.setItem(slot, ItemBuilder(getSkullNumber(i)).setName("§7$i").build())
            //Verificando qual é o slot para setar em outro (Ficar bonitinho e organizado)
            if (++slot == 23) slot = 29
            if (slot == 32) slot = 38
            if (slot == 41) slot = 48
        }

        //Setando outros itens, como de resultado, adição e etc...
        inv.setItem(4, ItemBuilder(Material.DIAMOND).setName("§aResultado").setLore(listOf("", "§cNenhum resultado ainda...", "")).build())

        inv.setItem(16, ItemBuilder(Material.ANVIL).setName("§f=").build())
        inv.setItem(25, ItemBuilder(Material.PAPER).setName("§fAdição").build())
        inv.setItem(34, ItemBuilder(Material.PAPER).setName("§fSubtração").build())
        inv.setItem(43, ItemBuilder(Material.PAPER).setName("§fDivisão").build())
        inv.setItem(52, ItemBuilder(Material.PAPER).setName("§fMultiplicação").build())

        //Abrindo o inventário
        p.openInventory(inv)
    }

    //Método para pegar a skull dos números.
    private fun getSkullNumber(number: Int): ItemStack {
        val baseURL = "http://textures.minecraft.net/texture"
        return SkullUtils().getSkull(when (number) {
            0 -> "$baseURL/1f3d1972ce2f2b79b2df07ad5c574f9ccd0eceb204143df966d301b18e92b0b6"
            1 -> "$baseURL/e0a4c94dd65a7cadc4172cedf3990a8542772bb113cdf1a0f78ef652c6abe6cb"
            2 -> "$baseURL/a47c55064f1d07625af05cdbc0fc7c84173b10541024c8855ed7132ca7767246"
            3 -> "$baseURL/d9cb94d8729197a18a76882c24918a8c04494d34727f4df060fb7a45a1ff8537"
            4 -> "$baseURL/36ad7565fa68942d2ff5f04efda80d9e9f88d33401680f182e0f37af32b8c294"
            5 -> "$baseURL/d0203b124acefd08c446a0492a5c9d800ee0cd8c260c3c12377731659251ad4e"
            6 -> "$baseURL/e491017fd5267fc9a641cce0250d4683804e3b1399b2b5b58f130f5774de2821"
            7 -> "$baseURL/8425acc42caf8b1e95ccb16d379af0b76f95ed29ef2a494073d0b07b15dc22fd"
            8 -> "$baseURL/b4904d5dee3782d4d6c6febe72783d96148277efbe78c1514cc20088d8ff0d09"
            9 -> "$baseURL/6adc65c3b896ab66df1ed7ba4250d16bb8352a6d285a54c00218ea72a4bb7149"
            else -> "Type not found"
        })
    }

}