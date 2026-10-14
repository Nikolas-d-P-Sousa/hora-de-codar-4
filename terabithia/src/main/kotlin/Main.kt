import kotlin.system.exitProcess

var nome = ""

fun main() {
    // Função principal que chama a função inicio().
    val usuariologin = login()
    if (usuariologin == true) {inicio()}
}

fun inicio() {
    readString("Pressione ENTER para continuar ")
    // A varival escolha armazena a opção escolhida pelo usuário.
    // uma variavel local é utilizada apenas dentro da função inicio().

    println(
        """
            |1. Cadastrar Quarto 
            |2. Hospedes
            |3. Eventos
            |4. Ar Condicionado
            |5. Abastecimento de Automóveis
            |6. Relatórios
            |7. Sair
            |Opção:""".trimMargin()
    )
    val escolha = readInt("Escolha uma opção: ")
    when (escolha) {
        1 -> quartoOpcoes()
        2 -> CadastroHospede()
        3 -> Eventos()
        4 -> arCondicionado()
        5 -> abastecer()
        6 -> opcaoRelatorios()
        7 -> sairDoHotel()
        else -> erro()
    }
}

fun erro(){
    println("Por favor, informe um número entre 1 e 7.")
    inicio()
}

fun sairDoHotel() {

    val confirma = readSimOuNao("Você deseja sair (S/N)? : ")
    when (confirma) {
        true -> {println ("Até logo!")
        exitProcess(67)}
        false -> inicio()
    }
}