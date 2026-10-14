data class Quarto(
    val numero: Int, // de 1 a 20
    val tipo: String, // Luxo, Padrão, Master
    var hospede: String, // Informar o hóspede
    val total: Double
)
// Usado momentaneamente na função tipoQuarto para retornar o tipo e custo adicional do quarto
data class TipoQuarto(
    val tipo: String,
    val custo: Double,
)
// Lista contendo os quartos
val listaDeQuartos = mutableListOf<Quarto>()

fun quartoOpcoes(){
    val opcao = readInt("""
        1. Cadastrar Quarto
        2. Desocupar Quarto
        3. Checar Quartos
        4. Voltar
        : 
    """.trimIndent(),1,4)

    when (opcao) {
        1-> cadastrarQuartos()
        2-> desocuparQuarto()
        3-> printarQuartos()
        4-> inicio()
    }
}

fun cadastrarQuartos() {
    hospedeCheck()

    // Entradas para criar a data class Quarto
    val diaria = readInt("Informe o valor da diária: ",1)
    val dias = readInt("Informe a quantidade de diárias: ",1,30)
    val hospede = checarHospede()
    val tipoQuarto = tipoQuarto()
    val numQuarto = readQuarto()
    val subTotal = arredondarDouble(diaria * dias * tipoQuarto.custo)
    val taxaServico = arredondarDouble(subTotal * 0.1)
    val totalFinal = arredondarDouble(subTotal + taxaServico)

    println("""
        Resumo:
        Hospede: ${hospede}
        Quarto: $numQuarto (${tipoQuarto.tipo})
        Subtotal: R$ ${subTotal}
        Taxa: R$ ${taxaServico}
        Total: R$ ${totalFinal}
        
    """.trimIndent())
    // Pede para usuário confirma a reserva. Se sim, o quarto é adicionado a lista. Caso contrário, retorna ao men de opções
    val confirma = readSimOuNao("$nome, Confirmar reserva (S/N)?: ")
    when (confirma) {
        true -> {
            val quarto = Quarto(numQuarto, tipoQuarto.tipo, hospede = hospede.toString(), totalFinal)
            listaDeQuartos.add(quarto)
            val index = Hospedes.indexOfFirst { it.nome.equals(hospede.toString(), ignoreCase = true) }
            if (index != -1) {
                Hospedes[index] = Hospedes[index].copy(quarto = numQuarto)}
            val quartoLucro = RelatorioLucros(nome_lucro = hospede.toString(), lucro = totalFinal)
            listaLucros.add(quartoLucro)
            printarQuartos()
            inicio()

        }
            false -> {
                println("Reserva não efetuada")
                inicio()}
    }
}

fun checarHospede(): Any {
    hospedeCheck()
    val hospede = readString("Informe o nome do hóspede: ",1)
    // Busca por hóspedes com nome igual a busca
    val achou = Hospedes.any({ it.nome.equals(hospede, true) })
    // Checa se o hóspede já está em outro quarto
    val duplicado = listaDeQuartos.any{it.hospede.equals(hospede, true) }
    if (duplicado) {
        println("Hóspede já está hospedado em um quarto")
        return quartoOpcoes()
    }
    if (achou) {
        return hospede.capitalize()
    }
    else {
        println("Hóspede não encontrado.")
        return checarHospede()
    }
}

fun tipoQuarto(): TipoQuarto {
    // Valida o tipo do quarto, retornando tipo e custo
    val quarto = readString("Informe o tipo do quarto (S)tandard, (E)xecutivo, (L)uxo: ").uppercase()
    when (quarto) {
        "S","STARDARD" -> return TipoQuarto(tipo ="Standard" , 1.0)
        "E", "EXECUTIVO" -> return TipoQuarto(tipo ="Executivo" , 1.35)
        "L", "LUXO"  -> return TipoQuarto(tipo ="Luxo" , 1.65)
        else -> return tipoQuarto()
    }
}

fun readQuarto(): Int{
    // Valida o número do quarto
    val numQuarto = readInt("Informe o número do quarto: ",1,20)
    // Busca a lista de quartos por um número igual a pesquisa. Caso exista, retorna ocupado
    val ocupado = listaDeQuartos.any{(it.numero == numQuarto)}
    if (ocupado){
        println("Esse quarto já está ocupado")
        return readQuarto()
    }
    return numQuarto
}

fun printarQuartos() {
    // Contador usado para criar quebras de linha
    var contador = 0
    println("--- STATUS DOS QUARTOS ---")
    for (numQuarto in 1..20) {
    if (contador % 5 == 0){
        print("\n")}
        // Checa se o contador do for loop (numQuarto) é igual a um quarto da lista. Quando é igual printa O|, caso contrário L|
        val quarto = listaDeQuartos.find { it.numero == numQuarto }
        if (quarto != null) {
            print("O|")
        } else {
            print("L|")
        }
        contador++
    }
    print("\n")
    return quartoOpcoes()
}

fun hospedeCheck(){
    // Retorna se a lista de hóspedes está vazia
    if (Hospedes.isEmpty()){
        println("Não há Hóspedes registrados")
        return inicio()
    }

}

fun desocuparQuarto() {
    if (listaDeQuartos.isEmpty()) {
        println("Nenhum quarto está ocupado")
        return quartoOpcoes()
    }

    val busca = readInt("Informe o número do quarto: ", 1, 20)
    val quartoEncontrado = listaDeQuartos.find { it.numero == busca }

    if (quartoEncontrado != null) {
        // Encontra o hóspede na lista de Hospedes e remove do quarto de sua data class
        val index = Hospedes.indexOfFirst { it.nome.equals(quartoEncontrado.hospede, ignoreCase = true) }

        if (index != -1) {
            Hospedes[index] = Hospedes[index].copy(quarto = null)
            val hospedeEncontrado = Hospedes[index].nome
            listaLucros.removeIf { it.nome_lucro.equals(hospedeEncontrado, ignoreCase = true) }
        }

        listaDeQuartos.remove(quartoEncontrado)
        println("Quarto desocupado com sucesso")
        return printarQuartos()
    } else {
        println("Quarto não está ocupado")
    }

    return quartoOpcoes()
}