data class Evento (
    val Convidados: Int,
    val Auditorio: String,
    val Dia: String,
    val HoraInicial: Int,
    val Duracao: Int,
    val Empresa: String,
    val Status: String,
    val Garcons: Int,
    val CustoGarcons: Double,
    val Cafe: Double,
    val Agua: Double,
    val Salgados: Int,
    val CustoBuffet: Double,
    val Total: Double,
)

val listaDeEventos: MutableList<Evento> = mutableListOf()

fun Eventos(){
    val opcao = readInt("""
        Escolha uma opção
        1. Registrar Evento
        2. Listar Eventos
        3. Remover Eventos
        4. Listar Horários
        5. Voltar
        Opção: 
    """.trimIndent())
    when (opcao) {
        1-> registrarEventos()
        2-> listarEventos()
        3-> removerEventos()
        4-> listarHorarios()
        5-> inicio()
    }
}

fun registrarEventos() {
    val convidados = registrarConvidados()
    val auditorio = retornarAuditorio(convidados = convidados)
    var dia = readDia()
    var horariosDisponiveis = retornarHorarios(entrada = dia ,auditorio = auditorio,)
   // Checa se a lista de horários não está vazia, caso esteja,o usúario deve colocar outro dia
    while (horariosDisponiveis[0] == 0 || horariosDisponiveis.isEmpty()){
       println("Não há horários disponíveis")
        dia = readDia()
        horariosDisponiveis = retornarHorarios(entrada = dia ,auditorio = auditorio,)
    }
    println("Horários disponíveis: $horariosDisponiveis ")
    var comeco = readInt("Insira o horário de início do evento: ", horariosDisponiveis[0],  horariosDisponiveis.last())
    if (comeco !in horariosDisponiveis){
        println("Horário indisponível!")
        println("Horários disponíveis: $horariosDisponiveis ")
        comeco = readInt("Insira o horário de início do evento: ", horariosDisponiveis[0], horariosDisponiveis.last())

    }
    var duracao = readDuracao(comeco, horariosDisponiveis)
    while (duracao == null){
        val confirma = readSimOuNao("Deseja continuar (S/N?): ")
        when (confirma) {
            true -> {println("Horários disponíveis: $horariosDisponiveis ")
                    duracao = readDuracao(comeco, horariosDisponiveis)
            }
            false -> inicio()
        }

    }
    // Remove os horários da lista de horários
    reduzirHorarios(comeco, duracao = duracao, dia = dia, auditorio = auditorio)

    // Cálculos e nome da empresa
    val nomeEmpresa = readString("Informe o nome da empresa: ").capitalize()

    val qtdGarcons = (convidados / 12) +(duracao/2)
    val custoGarcons = qtdGarcons * duracao * 10.50

    val qtdCafe = convidados * 0.2
    val qtdAgua = convidados * 0.5
    val qtdSalgados = convidados * 7

    val custoCafe = qtdCafe * 0.8
    val custoAgua = qtdAgua * 0.4
    val custoSalgados = (qtdSalgados / 100) * 34

    val custoBuffet = custoCafe + custoAgua + custoSalgados + custoGarcons
    val custoTotal = custoBuffet + custoGarcons

    println(
        """"---RELATÓRIO TÉCNICO---
    Convidados: $convidados"
    Auditório: $auditorio"
    Dia: $dia"
    Hora inicial: $comeco"
    Duração: $duracao"
    "Nome da Empresa: $nomeEmpresa
    Status: Auditório reservado
    Garçons necessários: $qtdGarcons
    Custo com garçons: R$ $custoGarcons
    Buffet:
    Café: $qtdCafe L
    Agua: $qtdAgua L
    Salgados: $qtdSalgados unidades
    Custo buffet: R$$custoBuffet
    Total do evento: R$$custoTotal""")

    val confirma = readSimOuNao("Confirmar reserva (S/N?): ")
    when (confirma) {
        true -> {

            val evento = Evento(Convidados = convidados, Auditorio = auditorio, Dia = dia, HoraInicial = comeco,
                Duracao = duracao, Empresa = nomeEmpresa, Status = "Reservado", Garcons = qtdGarcons,
                CustoGarcons = custoGarcons, Cafe = qtdCafe, Agua = qtdAgua, Salgados = qtdSalgados,
                CustoBuffet = custoBuffet, Total = custoTotal)
            listaDeEventos.add(evento)
            val relatorioEvento = RelatorioLucros("Evento no auditório $auditorio", lucro = custoTotal )
            listaLucros.add(relatorioEvento)
            Eventos()
        }
            false -> inicio()
    }
}

fun listarHorarios() {
    // Lista os horários e pergunta se o usuário deseja registrar um evento
    val opcao = readString("Escolha um Auditório (L)aranja ou (C)oral: ").uppercase()
    when (opcao) {
        "L" -> {
            println("""
                |Segunda: $SEGUNDA_Laranja
                |----------------------
                |Terça: $TERCA_Laranja
                |----------------------
                |Quarta: $QUARTA_Laranja
                |----------------------
                |Quinta: $QUINTA_Laranja
                |----------------------
                |Sexta: $SEXTA_Laranja
                |----------------------
                |Sábado: $SABADO_Laranja
                |----------------------
                |Domingo: $DOMINGO_Laranja
                |----------------------
            """.trimMargin())
        }
        "C" ->{
            println("""
                |Segunda: $SEGUNDA_Colorado
                |----------------------
                |Terça: $TERCA_Colorado
                |----------------------
                |Quarta: $QUARTA_Colorado
                |----------------------
                |Quinta: $QUINTA_Colorado
                |----------------------
                |Sexta: $SEXTA_Colorado
                |----------------------
                |Sábado: $SABADO_Colorado
                |----------------------
                |Domingo: $DOMINGO_Colorado
                |----------------------
            """.trimMargin())
        }
        else -> {
            println("Erro!")
            inicio()
        }
    }
    val confirma = readSimOuNao("Deseja registrar um evento (S/N)?: ")
    when (confirma) {
        true -> registrarEventos()
        false -> Eventos()
    }
}

fun listarEventos() {
    // Checa se há algum evento registrado
    if (listaDeEventos.isEmpty()) {
        println("Não há eventos registrados")
    }


    for( i in listaDeEventos.indices){
        var contador = 0
    println("""
        ${listaDeEventos[contador].Empresa}
        ${listaDeEventos[contador].Dia}
        Início do evento: ${listaDeEventos[contador].HoraInicial}
        Fim do evento: ${listaDeEventos[contador].Duracao + listaDeEventos[contador].HoraInicial}
        ___________________________________
        """)
    contador++}
    Eventos()
}

fun removerEventos() {
    // Checa se não há eventos registrados
    if (listaDeEventos.isEmpty()) {
        println("Não há eventos registrados")
        Eventos()
    }

    // For loop para listar eventos

    println("EVENTOS CADASTRADOS")
    for( i in listaDeEventos.indices){
        var contador = 0
    println("""
        $contador. ${listaDeEventos[contador].Empresa}
        ___________________________________
        """)
    contador++}

    // Lê o ID do evento, a entrada é limitada por 0 e o último índice da lista de eventos
    val opcao = readInt("Insira o ID do evento que deseja remover: ",0,listaDeEventos.size)

    // Variável usada para guardar o índice da opção para cálculos
    val eventoRemovido = listaDeEventos[opcao]
    adicionarHorarios(inicio = eventoRemovido.HoraInicial, duracao =eventoRemovido.Duracao,
        dia =eventoRemovido.Dia, auditorio =eventoRemovido.Auditorio)

   // Valor usado na hora de remover da lista de lucros. Sem isso deu um erro O-o
    val eventoLucro = eventoRemovido.Total

    // Remove o evento da lista
    listaDeEventos.removeAt(opcao)
    // Remove o evento do relatório de lucros
    listaLucros.removeIf { it.lucro == eventoLucro}
    val confirma = readSimOuNao("Remover outro evento (S/N)?: ")
    when (confirma) {
        true -> removerEventos()
        false -> Eventos()
    }
}