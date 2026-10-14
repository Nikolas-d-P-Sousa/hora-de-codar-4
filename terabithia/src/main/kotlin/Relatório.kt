data class Relatorio(
    val nome_relator: String,
    val nome_despesa: String,
    val custo: Double?,
    val data: String,
    val descricao: String
)

data class RelatorioLucros(val nome_lucro: String,
    val lucro: Double, )

val listaDeRelatorio = mutableListOf<Relatorio>()
val listaLucros = mutableListOf<RelatorioLucros>()

fun opcaoRelatorios(){
    while (true){
    val opcao = readInt("""
        1. Quartos Ocupados
        2. Quantidade de Hóspedes
        3. Quantidade de Eventos
        4. Receita
        5. Despesas
        6. Voltar
        
    """.trimIndent(),1,6)
    when (opcao) {
        1 -> relatorioQuartoOcupados()
        2 -> relatorioHospedesRegistrados()
        3 -> relatorioEventosRegistrados()
        4 -> receita()
        5 -> relatoriosDespesas()
        6 -> inicio()
    }
    }

}
fun relatorioQuartoOcupados(){
    // Checa o número de quartos ocupados
    val ocupado = listaDeQuartos.size
    println("Atualmente $ocupado quartos estão ocupados")
    return
}

fun relatorioHospedesRegistrados(){
    // Checa o número de hóspedes registrados
    val ocupado = Hospedes.size
    println("Atualmente há $ocupado hóspedes registrados ")
    return
}

fun relatorioEventosRegistrados(){
    // Checa o número de eventos registrados
    val ocupado = listaDeEventos.size
    println("Atualmente há $ocupado eventos registrados ")
    return
}

fun relatoriosDespesas(){
    // checa se há algum relatório de despesa registrado
    if (listaDeRelatorio.isEmpty()) {
        println("Nenhum relatório de despesa cadastrado.\n")
        return
    }

    val despesa = listaDeRelatorio.sumOf { it.custo ?: 0.0 }
    // Itera a lista de relatórios, printando informações relevantes
    for (contador in listaDeRelatorio.indices){
        println("Relator: ${listaDeRelatorio[contador].nome_relator}")
        println("Despesa: ${listaDeRelatorio[contador].nome_despesa}")
        println("Custo: R$ ${listaDeRelatorio[contador].custo}")
        println("Data: ${listaDeRelatorio[contador].data}")
        println("Descrição: ${listaDeRelatorio[contador].descricao}\n")
        println("_______________________________________________________")
    }
    return
}

fun receita(){
    // Checa se há algum relatório de lucro registrado
    if (listaLucros.isEmpty()) {
        println("Nenhum relatório de lucro cadastrado.\n")
        return
    }
    // Retorna a soma do relatório de despesas
    val despesaOperacional = listaDeRelatorio.sumOf { it.custo ?: 0.0 }
    // Retorna a soma do relatório de lucros
    val lucroBruto = listaLucros.sumOf { it.lucro }
    // Retorna o lucro líquido
    val lucroLiquido = lucroBruto - despesaOperacional
    println("""
        Despesas: R$ $despesaOperacional
        - - - - - -
        LucroBruto: R$ $lucroBruto
        - - - - - -
        LucroLiquido: R$ $lucroLiquido
        - - - - - -
    """.trimIndent())

    return
}