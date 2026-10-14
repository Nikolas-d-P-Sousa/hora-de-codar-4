fun login()
        :Boolean {
    println("Bem-vindo(a) ao Mizpah")
    val usuario = readString("Insira seu nome: ").capitalize()
    nome = usuario
    var tentativas = 0 // Rgistra o número de tentativas
    var loginConcluido = false // Encera o processo de login
    while(!loginConcluido){ // Enquanto o login concluído não tiver terminado, pede para que o usuario insira a senha
        var senha = readInt("Senha: ") // Entrada de senha
        if (senha == 2678){ // Checa se a senha está correta e conclui o processo de login
            loginConcluido = true
            println("Bem-vindo(a) ao Hotel Mizpah, $usuario. É um imenso prazer ter você por aqui!")
        }
        else{
            println("Senha invalida") // Informa que senha está errada e aumenta o marcador de tentativas
            tentativas++
        }
        if (tentativas == 3){ // Encerra o programa se houveram 3 tentativas
            println("Encerrando Programa!")
            return false
        }
    }
return true
}