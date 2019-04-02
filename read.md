# Sobre as tecnologias
A principais tecnologias utilizadas na implementacao desse projeto sao: 
1) Java 8 com JDK.1.8.0_181
2) Maven 3.5.4
3) Spring Boot 1.5.19.RELEASE

# Sobre a execucao do projeto
0) Certifique-se que a porta "8080" do servidor em que a aplicacao sera executada esta liberada
1) Para executar o projeto, abra um janela para a execucao de comandos (PROMPT CMD/BASH SHEll) no diretorio raiz do projeto
2) Execute o comando: mvn compile package
3) O diretorio raiz do projeto possui um subdiretorio chamado "target". Na mesma janela de comando,
	navegue ate o diretorio "target" e execute o comando: java -jar backend-1.0.0-SNAPSHOT.jar
4) Para verificar a inicializacao da aplicacao, abra um navegador e acesse a URI: http://<HOST_DE_EXECUCAO_DA_APLICACAO>:8080/login
	e voce recebera a mensagem "Request method 'GET' not supported"