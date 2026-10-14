import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Empresa (
    val nome: String? = null,
    var Valor_por_Aparelho : Double? = null,
    var Quantidade : Int? = null,
    var Desconto: Int? = null,
    var MinimoParaDesconto: Int? = null,
    var Deslocamento: Int? = null,
    var Total: Double? = null
)
// Lista contendo as empresas registradas
val ListaDeEmpresas = mutableListOf<Empresa>()


// Escolhe a opção
fun arCondicionado(){
    val escolha = readInt("1. Cadastro de empresa\n2. Fazer Pedido\n3.Voltar \nOpção: " )
    when (escolha) {
        1 -> CadastrarArCondicionado()
        2 -> fazerPedido(true)
        3 -> inicio()
        else -> arCondicionado()
    }

}

fun CadastrarArCondicionado(){


    val nome_empresa = readString("Empresa: ").capitalize().trim()

    val valorAparelho = readDouble("Valor por aparelho: ")

    val quantidade = readInt("Quantidade: ")

    val desconto = readInt("Desconto (%): ")

    val MinimoParaDesconto = readInt("Mínimo para desconto: ")

    val deslocamento = readInt("Deslocamento: ")
    // Calcula o total
    var total = (valorAparelho * quantidade)
    if (quantidade >= MinimoParaDesconto){
        total -= (total *(desconto/100))
    }
    total += deslocamento

    // Adiciona os dados da empresa à lista, e organiza de forma crescente

    val empresa = Empresa(nome = nome_empresa, Valor_por_Aparelho = valorAparelho, Quantidade = quantidade, Desconto = desconto,
        MinimoParaDesconto = MinimoParaDesconto, Deslocamento = deslocamento, Total = total)
    ListaDeEmpresas.add(empresa)
    ListaDeEmpresas.sortBy { it.Total }


    // Pede se o usuario deseja inserir dados de outra empresa, caso contrário pede para confirmar o pedido
    // A fazer: Pedido ser adicionado aos relatórios
    val confirma = readSimOuNao("Deseja informar novos dados, $nome (S/N)? : ")
    when (confirma) {
        true -> CadastrarArCondicionado()
        false -> { fazerPedido(false)
        }
    }

    arCondicionado()

}


// Faz o pedido e retorna ao menu inicial. Caso a opção seja escolhida pelo menu, o usuário pode mudar a quantidade de aparelhos do pedido
fun fazerPedido(mudarQuantidade: Boolean){
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dataHora = LocalDateTime.now().format(formatter).toString()

    if (mudarQuantidade == true){
        val confirma = readSimOuNao("Deseja alterar a quantidade de aparelhos? (S/N)? : ")
        if (confirma == true){
            val aparelhos = readInt("Informe a nova quantidade de aparelhos: ")
            for ( i in ListaDeEmpresas.indices){
                ListaDeEmpresas[i].Quantidade = aparelhos
                ListaDeEmpresas.sortBy { it.Total }
            }
            }
        }

    print("Melhor preço: ${ListaDeEmpresas[0].Total} -- ${ListaDeEmpresas[0].nome}")
    val quantidade = ListaDeEmpresas[0].Quantidade
    val confirma = readSimOuNao("Confirmar pedido, $nome (S/N)? : ")
    when (confirma) {
        true -> {
            val arCondicionadoRelatorio = Relatorio(nome_relator = nome, nome_despesa = "Ar Condicionado", custo = ListaDeEmpresas[0].Total,
                data = dataHora, descricao = "Compra de $quantidade aparelhos da empresa ${ListaDeEmpresas[0].nome}")
            listaDeRelatorio.add(arCondicionadoRelatorio)
        }
        false -> inicio()
    }
    inicio()

}

fun calcularArCondicionado(){
    val pedido = readInt("Informe a quantidade de aparelhos: ")
for (empresa in ListaDeEmpresas){
    var total = pedido * empresa.Valor_por_Aparelho?.toDouble()!!
    if (pedido >= empresa.MinimoParaDesconto?.toInt()!!){
        total = total - (total * (empresa.Desconto?.toDouble()!!/100))
    }
    total += empresa.Deslocamento?.toDouble()!!
    println("O serviço da ${empresa.nome}\n custará: R$ $total")
}
    arCondicionado()
}