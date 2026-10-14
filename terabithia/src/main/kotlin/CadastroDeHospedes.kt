data class HospedeDC(
    val nome: String,
    val quarto: Int? = null,
)
val Hospedes = mutableListOf<HospedeDC>()

fun CadastroHospede(){
    while (true){
        val opcao = readInt("""
            1. Cadastrar Hospede
            2. Buscar Hospede
            3. Buscar Hospede por Prefixo
            4. Listar Hospedes
            5. Listar em Ordem Alfabética
            6. Remover Hospede
            7. Voltar
            
        """.trimIndent(),1,7)
        when (opcao) {
            1 -> Cadastro()
            2 -> searchHospede()
            3 -> searchHospedePrefixo()
            4 -> listarHospede()
            5 -> listarHospedeA_Z()
            6 -> removerHospede()
            7 -> inicio()
        }
    }

}

fun Cadastro(){
    // Checa se não passou do limite de hóspedes
    if (Hospedes.size >= 15){
        println("Número máximo de hóspedes alcançado")
        CadastroHospede()
    }
    // Registra o hóspede caso ele(a) não esteja no sistema
    val nomeHospede = readString("Informe o nome do hóspede: ",1).capitalize().trim()
    val hospedeClass = HospedeDC(nomeHospede)
    if (hospedeClass !in Hospedes){
        Hospedes.add(hospedeClass)
        println("Hóspede cadastrado com sucesso!")
    }
    else{
        println("Hóspede já cadastrado")
    }
    return
}

fun searchHospede(){
    hospedeCheck()
    val busca = readString("Informe o nome do Hóspede: ").trim()
    // Busca por um Hóspede com o nome igual a busca
    val hospede = Hospedes.firstOrNull { it.nome.equals(busca, ignoreCase = true) } //
        if (hospede != null){
        println("Hóspede encontrado(a)")
    }
    else{
        println("Hóspede não encontrado(a)")
    }
    return
}

fun removerHospede(){
    hospedeCheck()
    val busca = readString("Informe o nome do Hóspede: ").trim()
    // Busca por um Hóspede com o nome igual a busca, caso encontre, ele é removido
    val removido = Hospedes.removeIf {it.nome.equals(busca, ignoreCase = true)  }
    if (removido){
        println("Hóspede removid(a) com sucesso!")}
    else{
        println("Hóspede não encontrado(a)")
    }
    return
}

fun listarHospede(){
    hospedeCheck()
    for (hospede in Hospedes){
        print("Nome: ${hospede.nome} ")
        if (hospede.quarto != null){
            println(" | Quarto: ${hospede.quarto} ")
        }
        else{
            println(" | Quarto: Não Registrado")
        }
    }
    return
}

fun searchHospedePrefixo() {
    hospedeCheck()
    val busca = readString("Informe o prefixo do nome do Hóspede: ").trim()

    // Filtra os hóspedes cujo nome começa com o texto digitado
    val resultados = Hospedes.filter { it.nome.startsWith(busca, ignoreCase = true) }

    if (resultados.isNotEmpty()) {
        println("Hóspede(s) encontrado(s):")
        for (hospede in resultados) {
            val quartoTexto = hospede.quarto?.toString() ?: "Não Registrado"
            println("Nome: ${hospede.nome} | Quarto: $quartoTexto")
        }
    } else {
        println("Nenhum hóspede encontrado(a)")
    }
}

fun listarHospedeA_Z(){
    hospedeCheck()
    val hospedeA_Z = Hospedes.sortedBy { it.nome } // Cria variável que é a lista ordenada
    for (hospede in hospedeA_Z){
        print("Nome: ${hospede.nome} ")

        // Indica se há um quarto reservado no nome do hóspede
        if (hospede.quarto != null){
            println(" | Quarto: ${hospede.quarto} ")
        }
        else{
            println(" | Quarto: Não Registrado")
        }
    }
    return
}