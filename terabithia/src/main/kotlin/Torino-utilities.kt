import java.math.RoundingMode

// esse é um arquivo biblioteca, contém várias funções recorrentes com base nas necessidades dos exercícios

// função para ler números inteiros, com tratamento de exceções. retorna um int.
// leva três parâmetros: mensagem (obrigatória) que será mostrada ao utilizador
// número mínimo e máximo, para casos onde números negativos são proibidos
fun readInt(
    mensagem: String,
    minimo: Int? = null,
    maximo: Int? = null
): Int {
    // laço que garante execução até alguma entrada ser válida
    while (true) {
        print(mensagem)
        // constante que recebe a entrada e já checa se está vazia
        val entrada = readlnOrNull() ?: continue
        // constante que recebe a conversão da entrada para int
        val numero = entrada.toIntOrNull()

        // tratamento da primeira exceção null também engloba entrada
        // que não pode ser convertida para int
        if (numero == null) {
            println("Operação não autorizada!")
            println("Erro: Valor inválido... ")
            continue
            // checa se número é menor que o mínimo
        } else if (minimo != null && numero < minimo) {
            println("Operação não autorizada!")
            println("Erro: Valor deve ser pelo menos $minimo ")
            continue
            // checa se o número excede o máximo
        } else if (maximo != null && numero > maximo) {
            println("Operação não autorizada!")
            println("Erro: Valor deve ser no máximo $maximo ")
            continue
        }
        // retorna o número caso entrada esteja correta
        return numero
    }
}

fun readDouble(
    mensagem: String,
    minimo: Double? = null,
    maximo: Double? = null
): Double {
    // laço que garante execução até alguma entrada ser válida
    while (true) {
        print(mensagem)
        // constante que recebe a entrada e já checa se está vazia
        val entrada = readlnOrNull() ?: continue
        // constante que recebe a conversão da entrada para int
        val numero = entrada.toDoubleOrNull()

        // tratamento da primeira exceção null também engloba entrada
        // que não pode ser convertida para int
        if (numero == null) {
            println("Operação não autorizada!")
            println("Erro: Valor inválido... ")
            continue
            // checa se número é menor que o mínimo
        } else if (minimo != null && numero < minimo) {
            println("Operação não autorizada!")
            println("Erro: Valor deve ser pelo menos $minimo ")
            continue
            // checa se o número excede o máximo
        } else if (maximo != null && numero > maximo) {
            println("Operação não autorizada!")
            println("Erro: Valor deve ser no máximo $maximo ")
            continue
        }
        // retorna o número caso entrada esteja correta
        return numero
    }
}

// mesma lógica
fun readString(
    mensagem: String,
    minChar: Int? = null,
    maxChar: Int? = null
): String {
    while (true) {
        print(mensagem)
        val entrada = readln()
        val larguraEntrada = entrada.length

        if (minChar != null && larguraEntrada < minChar) {
            println("Erro: Texto inserido deve ter ao menos $minChar caractere(s)! ")
            continue
        } else if (maxChar != null && larguraEntrada > maxChar) {
            println("Erro: Texto inserido excede o máximo de $maxChar caracteres! ")
            continue
        }

        return entrada
    }
}

fun readSimOuNao(
    mensagem: String,
): Boolean {
    val entradasValidas = listOf<String>("S", "SI", "SIM")
    while (true) {
        print(mensagem)
        val entrada = readln().uppercase().trim()
        if (entrada in entradasValidas) {
            return true
        }
        else {
            return false
        }
    }
}

fun arredondarDouble(entrada: Double): Double {
    return kotlin.math.round(entrada * 100) / 100.0
}
