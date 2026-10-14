val dias = listOf<Int>(0)
// Lista de horários baseado no dia e auditório
val SEGUNDA_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val SEGUNDA_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val TERCA_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val TERCA_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val QUARTA_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val QUARTA_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val QUINTA_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val QUINTA_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val SEXTA_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val SEXTA_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val SABADO_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val SABADO_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
val DOMINGO_Laranja = mutableListOf<Int>(7,8,9,10,11,12,13,14,15)
val DOMINGO_Colorado = mutableListOf<Int>(7,8,9,10,11,12,13,14,15)


// Valida entrada de usuários
fun registrarConvidados(): Int {
    val convidado = readInt("Informe o número de convidados: ",0,350)
    val convidados = convidado
    return convidados
}
// Valida o autório escolhido
fun retornarAuditorio(convidados: Int?): String {
    var auditorio: String = ""
    if (convidados != null) {
        if (convidados > 220) {
            auditorio = "Colorado"
        }
        if (convidados <= 220) {
            val confirma = readSimOuNao("Deseja realizar seu evento no auditório Laranja ? (S/N): ")
            when (confirma) {
                true -> auditorio = "Laranja"
                false -> auditorio = "Colorado"
            }
        }
    }
    return auditorio
}


// Mostra os horários disponíveis, baseando-se no dia e auditório
fun retornarHorarios(entrada: String, auditorio: String): List<Int>{
    if (auditorio == "Laranja"){
        if (entrada.uppercase().trim() == "SEGUNDA"){
            return SEGUNDA_Laranja
        }
        if (entrada.uppercase().trim() == "TERCA"){
            return TERCA_Laranja
        }
        if (entrada.uppercase().trim() == "QUARTA"){
            return QUARTA_Laranja
        }
        if (entrada.uppercase().trim() == "QUINTA"){
            return QUINTA_Laranja
        }
        if (entrada.uppercase().trim() == "SEXTA"){
            return SEXTA_Laranja
        }
        if (entrada.uppercase().trim() == "SABADO"){
            return SABADO_Laranja
        }
        if (entrada.uppercase().trim() == "DOMINGO"){
            return DOMINGO_Laranja
        }

    }
    if (auditorio == "Colorado"){
        if (entrada.uppercase().trim() == "SEGUNDA"){
            return SEGUNDA_Colorado
        }
        if (entrada.uppercase().trim() == "TERCA"){
            return TERCA_Colorado
        }
        if (entrada.uppercase().trim() == "QUARTA"){
            return QUARTA_Colorado
        }
        if (entrada.uppercase().trim() == "QUINTA"){
            return QUINTA_Colorado
        }
        if (entrada.uppercase().trim() == "SEXTA"){
            return SEXTA_Colorado
        }
        if (entrada.uppercase().trim() == "SABADO"){
            return SABADO_Colorado
        }
        if (entrada.uppercase().trim() == "DOMINGO"){
            return DOMINGO_Colorado
        }
    }
    return dias
}


// Valida duração do evento
fun readDuracao(horaInicial: Int, horarios: List<Int>): Int? {
    print("Insira a duração do evento: ")
    val duracao = readln().toIntOrNull()?.minus(1)

    if (duracao == null || duracao < 0){
        println("Duração não pode ser 0 ou nulo!")
        return null
    }
    if (duracao != null) {
        if (duracao > 12){
            println("O evento não pode durar mais que 12 horas")
            return null
        }
    }
    if (duracao != null){
        if (duracao + horaInicial > horarios.last()){
            println("Você não pode exceder o horário!")
            return null
        }
    }
    val diponibilidade = duracao + horaInicial
    if (diponibilidade !in horarios){
        println("Horário indisponível!")
        return null
    }
    val RetornoDuracao = duracao?.toInt()
    return RetornoDuracao
}


// Remove os horários do dia e auditório específicado
fun reduzirHorarios(inicio: Int, duracao: Int, dia: String, auditorio: String){
    if (auditorio == "Laranja"){
        if (dia == "SEGUNDA"){
            for ( hora in inicio..inicio+duracao ){
                SEGUNDA_Laranja.remove(hora)
            }
        }
        if (dia == "TERCA"){
            for ( hora in inicio..inicio+duracao ){
                TERCA_Laranja.remove(hora)
            }
        }
        if (dia == "QUARTA"){
            for ( hora in inicio..inicio+duracao ){
                QUARTA_Laranja.remove(hora)
            }
        }
        if (dia == "QUINTA"){
            for ( hora in inicio..inicio+duracao ){
                QUINTA_Laranja.remove(hora)
            }
        }
        if (dia == "SEXTA"){
            for ( hora in inicio..inicio+duracao ){
                SEXTA_Laranja.remove(hora)
            }
        }
        if (dia == "SABADO"){
            for ( hora in inicio..inicio+duracao ){
                SABADO_Laranja.remove(hora)
            }
        }
        if (dia == "DOMINGO"){
            for ( hora in inicio..inicio+duracao ){
                DOMINGO_Laranja.remove(hora)
            }
        }
    }
    if (auditorio == "Colorado"){
        if (dia == "SEGUNDA"){
            for ( hora in inicio..inicio+duracao ){
                SEGUNDA_Colorado.remove(hora)
            }
        }
        if (dia == "TERCA"){
            for ( hora in inicio..inicio+duracao ){
                TERCA_Colorado.remove(hora)
            }
        }
        if (dia == "QUARTA"){
            for ( hora in inicio..inicio+duracao ){
                QUARTA_Colorado.remove(hora)
            }
        }
        if (dia == "QUINTA"){
            for ( hora in inicio..inicio+duracao ){
                QUINTA_Colorado.remove(hora)
            }
        }
        if (dia == "SEXTA"){
            for ( hora in inicio..inicio+duracao ){
                SEXTA_Colorado.remove(hora)
            }
        }
        if (dia == "SABADO"){
            for ( hora in inicio..inicio+duracao ){
                SABADO_Colorado.remove(hora)
            }
        }
        if (dia == "DOMINGO"){
            for ( hora in inicio..inicio+duracao ){
                DOMINGO_Colorado.remove(hora)
            }
        }
    }
}


// Caso o evento seja cancelado, os horários são adicionados novamente a lista de horas/auditório
fun adicionarHorarios(inicio: Int, duracao: Int, dia: String, auditorio: String){
    if (auditorio == "Laranja"){
        if (dia == "SEGUNDA"){
            for ( hora in inicio..inicio+duracao ){
                SEGUNDA_Laranja.add(hora)
                SEGUNDA_Laranja.sort()
            }
        }
        if (dia == "TERCA"){
            for ( hora in inicio..inicio+duracao ){
                TERCA_Laranja.add(hora)
                TERCA_Laranja.sort()
            }
        }
        if (dia == "QUARTA"){
            for ( hora in inicio..inicio+duracao ){
                QUARTA_Laranja.add(hora)
                QUARTA_Laranja.sort()
            }
        }
        if (dia == "QUINTA"){
            for ( hora in inicio..inicio+duracao ){
                QUINTA_Laranja.add(hora)
                QUINTA_Laranja.sort()
            }
        }
        if (dia == "SEXTA"){
            for ( hora in inicio..inicio+duracao ){
                SEXTA_Laranja.add(hora)
                SEXTA_Laranja.sort()
            }
        }
        if (dia == "SABADO"){
            for ( hora in inicio..inicio+duracao ){
                SABADO_Laranja.add(hora)
                SABADO_Laranja.sort()
            }
        }
        if (dia == "DOMINGO"){
            for ( hora in inicio..inicio+duracao ){
                DOMINGO_Laranja.add(hora)
                DOMINGO_Laranja.sort()
            }
        }
    }
    if (auditorio == "Colorado"){
        if (dia == "SEGUNDA"){
            for ( hora in inicio..inicio+duracao ){
                SEGUNDA_Colorado.add(hora)
                SEGUNDA_Colorado.sort()
            }
        }
        if (dia == "TERCA"){
            for ( hora in inicio..inicio+duracao ){
                TERCA_Colorado.add(hora)
                TERCA_Colorado.sort()
            }
        }
        if (dia == "QUARTA"){
            for ( hora in inicio..inicio+duracao ){
                QUARTA_Colorado.add(hora)
                QUARTA_Colorado.sort()
            }
        }
        if (dia == "QUINTA"){
            for ( hora in inicio..inicio+duracao ){
                QUINTA_Colorado.add(hora)
                QUINTA_Colorado.sort()
            }
        }
        if (dia == "SEXTA"){
            for ( hora in inicio..inicio+duracao ){
                SEXTA_Colorado.add(hora)
                SEXTA_Colorado.sort()
            }
        }
        if (dia == "SABADO"){
            for ( hora in inicio..inicio+duracao ){
                SABADO_Colorado.add(hora)
                SABADO_Colorado.sort()
            }
        }
        if (dia == "DOMINGO"){
            for ( hora in inicio..inicio+duracao ){
                DOMINGO_Colorado.add(hora)
                DOMINGO_Colorado.sort()
            }
        }
    }
}

// Valida o dia
fun readDia (): String {
    val validacaoDias = listOf<String>("SEGUNDA", "TERCA", "TERÇA", "QUARTA", "QUINTA", "SEXTA", "SABADO", "SÁBADO", "DOMINGO")
    print("Dia: ")
    val entrada = readlnOrNull()?.uppercase()?.trim()

    if (entrada == "TERCA" || entrada == "TERÇA"){
        return "TERCA"
    }
    if (entrada == "SABADO" || entrada == "SÁBADO"){
        return "SABADO"
    }
    val dia = entrada.toString()
    return dia
}
