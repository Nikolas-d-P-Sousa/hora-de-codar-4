import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class postoPreco(
    val preco: Double,
    val combustivel: String
)
// Ela abastece uau!!!!
fun abastecer(){
    val tanque = 42
    val wayneOilGasolina = arredondarDouble(tanque * 5.82 *  variacaoDePreco())  // Preço da Gasolina Wayne
    val wayneOilAlcool = arredondarDouble(tanque * 4.20 * variacaoDePreco())  // Preço do Alcóol Wayne

    val starkPetrolGasolina = arredondarDouble(tanque * 6.17 *  variacaoDePreco()) // Preço da Gasolina Stark
    val starkPetrolAlcool = arredondarDouble(tanque * 4.35 *  variacaoDePreco()) // Preço do Alcóol Stark

    println("Wayne Oil Gasolina: R$ ${arredondarDouble(wayneOilGasolina/42)}|| Alcóol: R$ ${arredondarDouble(wayneOilAlcool / 42)}")
    println("Stark Petrol: R$ ${arredondarDouble(starkPetrolGasolina/42)}|| Alcóol: R$ ${arredondarDouble(starkPetrolAlcool / 42)}")

    val melhorWayne = compararPreco(wayneOilGasolina, alcool =  wayneOilAlcool, "Wayne Oil")
    val melhorStark = compararPreco(starkPetrolGasolina, alcool =  starkPetrolAlcool, "Stark Petrol")
    precoFinal(melhorStark.preco, melhorStark.combustivel, melhorWayne.preco, melhorWayne.combustivel)
    inicio()

}
// Compara o preço entre o Alcóol e Gasolina. Retorna a opção mais econômica
fun compararPreco(gasoline: Double, alcool: Double, posto: String): postoPreco {
    if (alcool < gasoline * 0.7){
        println("$posto: melhor opção = Alcóol | Total (42L) = R$ $alcool")
        return postoPreco(preco = alcool, combustivel = "Alcóol")
    }
    else{
        println("$posto: melhor opção = Gasolina | Total (42L) = R$ $gasoline")
        return postoPreco(preco = gasoline, combustivel = "Gasolina")
    }
}
// Variação de preço para que os resultados sejam dinâmicos
fun variacaoDePreco (): Double {
    val variacaoDePreco = 1 + (-10..10).random().toDouble() / 100.0
    return kotlin.math.round(variacaoDePreco * 100) / 100.0

}


// Faz a comparação final
fun precoFinal(Stark: Double, StarkCombustivel: String, Wayne: Double, WayneCombustivel: String) {
    // Data e Hora para o relatório
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dataHora = LocalDateTime.now().format(formatter).toString()

    if (Stark < Wayne){
        println("$nome é mais barato abastecer com $StarkCombustivel no posto Stark Petrol")
        val confirma = readSimOuNao("Fazer pedido? (S/N): ")
        when (confirma){
            true -> {
                val relatorioAbastecimento = Relatorio(
                    nome_relator = nome, nome_despesa = "Abastecimento do carro",
                custo = Stark, data = dataHora, descricao = "Abastecimento de $StarkCombustivel no posto Stark Petrol")
                listaDeRelatorio.add(relatorioAbastecimento)

            }
                false -> inicio()
        }
    }
    else {
        println("$nome é mais barato abastecer com $WayneCombustivel no posto Wayne Oil")
        val confirma = readSimOuNao("Fazer pedido? (S/N): ")
        when (confirma){
            true -> {
                val relatorioAbastecimento = Relatorio(
                    nome_relator = nome, nome_despesa = "Abastecimento do carro",
                    custo = Wayne, data = dataHora, descricao = "Abastecimento de $WayneCombustivel no posto Wayne Oil")
                listaDeRelatorio.add(relatorioAbastecimento)

            }
            false -> inicio()
        }
    }
}