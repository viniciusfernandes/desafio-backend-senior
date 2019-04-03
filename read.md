# Sobre as tecnologias
A principais tecnologias utilizadas na implementacao desse projeto sao: 
1) Java 8 com JDK.1.8.0_181
2) Maven 3.5.4
3) Spring Boot 1.5.19.RELEASE

# Sobre a execucao do projeto com Docker
1) Certifique-se de que o Docker está devidamente instalado.
2) Certifique-se que a porta "8080" do servidor em que a aplicacao sera executada esta liberada
3) Para executar o projeto, abra um janela para a execucao de comandos (PROMPT CMD/BASH SHEll) no diretorio raiz do projeto
4) Execute o comando: mvn clean install
5) No diretorio raiz do projeto, execute o seguinte comando para criar um container Docker com 
	a aplicação backend-1.0.0-SNAPSHOT.jar instalada: docker build -f Dockerfile -t <O_NOME_DO_CONTAINER_QUE_DESEJA_CRIAR>.
6) Para executar o container criado no item anterior, execute o seguinte comando: docker run -p 8080:8080 <O_NOME_DO_CONTAINER_CRIADO_ANTERIORMENTE>
4) Para verificar a inicializacao da aplicacao, abra um navegador e acesse a URI: http://<HOST_DE_EXECUCAO_DA_APLICACAO>:8080/login
	e voce recebera a mensagem "Request method 'GET' not supported"