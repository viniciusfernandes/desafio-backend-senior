# Desafio - Desenvolvedor Java Sênior

Este documento apresenta o desafio técnico pelo qual um candidato a desenvolvedor Back-end da BRy Tecnologia deve passar durante o processo seletivo.

Os itens em avaliação são divididos em itens fundamentais (cujo não cumprimento implica desqualificação automática) e itens desejáveis (que não são obrigatórios, mas serão observados e pesarão no processo).

Fundamentais:
* Interpretação de texto
* Interpretação de requisitos de software
* Capacidade de resolução do problema proposto
* Atendimento aos requisitos
* Familiaridade com ferramentas de versionamento de código (Git)
* Estruturação do código

Desejáveis:
* Legibilidade de código
* Documentação de código
* Testes unitários e de integração
* Familiaridade com ferramentas de desenvolvimento Web

## Como Realizar o Desafio

1. Se não possuir uma conta no [GitLab](https://gitlab.com), cadastre-se.
2. Informe seu nome de usuário do *GitLab* ao representante da BRy responsável pelo seu processo seletivo, para que sejam dadas permissões para seu usuário.
3. Faça um *fork* do projeto [desafio-backend-senior](https://gitlab.com/brytecnologia-team/selecao/desafio-backend-senior) - isso criará um projeto idêntico no seu repositório pessoal.
4. Acesse seu *fork*. Observe que, no diretório raiz do projeto, há um arquivo **enunciado.md** de conteúdo idêntico a este documento.
5. No seu *fork*, acesse o menu *Settings > Members*.
6. Adicione o usuário **brytecnologia** como membro *Guest* do projeto, para que possamos acompanhar seu desenvolvimento.
7. Clone o projeto em sua máquina e mãos à obra!
8. Pedimos que trabalhe apenas no branch *master* - não será relevante, para a avaliação, o uso de branches de desenvolvimento.
9. Para verificar se seu projeto está atendendo aos requisitos, criamos um projeto do [Postman](https://www.getpostman.com/) contido, dentro do projeto, no caminho **src/test/resources/desafio-backend.json**. Este arquivo será utilizado na avaliação para garantir que os requisitos estão sendo atendidos - use-o à vontade para testar seu projeto. Saiba, no entanto, que apenas a versão original do arquivo será utilizada na avaliação.
10. Ao final do desenvolvimento, crie um arquivo *Dockerfile* na raiz do projeto e elabore uma *imagem Docker* para que possamos construir e executar seu projeto. Assuma que os comandos de compilação e empacotamento do projeto serão realizados por nós (*mvn clean install*) e que os artefatos gerados no diretório *target*, criado durante a construção do *Maven*, serão utilizados pela imagem.
11. Havendo quaisquer instruções especiais para execução do projeto, descreva-as num arquivo **readme.md** na raiz do projeto.
12. Ao final do desafio, faça um *push* do seu projeto e notifique o representante da BRy responsável pelo seu processo seletivo de que você terminou o desafio.
13. Se nenhum aviso de finalização for dado até o fim do prazo para cumprimento do desafio, o conteúdo do branch *master* será considerado como a entrega para a avaliação. 

## Enunciado

Você deve desenvolver uma API REST para gerenciar um catálogo de **habitantes** e **endereços**.
O catálogo deve ser persistido em banco de dados (em memória - por exemplo: H2 / HSQLDB).
Para o desenvolvimento, podem ser utilizadas quaisquer bibliotecas e frameworks, desde que a linguagem seja *Java* e utilize-se *Maven* para gerenciamento de dependências.

Cada **habitante** possui 3 atributos: 
1. **Nome**: string
2. **Código**: string
3. **Endereços**: lista (endereço)

O **endereço** de cada **habitante** deve ser composto por:
1. **Habitante**:  objeto (habitante)
2. **Código postal** (CEP): string 
3. **Logradouro**: string
4. **Bairro**: string
5. **Número**: número
6. **Complemento**: string (preenchimento opcional)
7. **Localização** (cidade): string
8. **UF** (Unidade Federativa / "estado"): string

### Segurança da API
A API deve ser protegida e seu acesso restrito da seguinte maneira:
* usuário **admin** deve conseguir realizar todas as operações.
* usuário **readonly** só deve conseguir realizar as requisições *GET*

A senha do usuário **admin** é **adminpassword**.
A senha do usuário **readonly** é **readonlypassword**.

O repositório de usuários deve ser lido do arquivo **src/main/resources/usuarios.properties**, e as senhas dos usuários estão ofuscadas com um hash simples MD5 da senha original.

Deve ser desenvolvido um endpoint de autenticação para retornar Tokens, dadas as credenciais de usuários.
Os tokens gerados devem ser utilizados nas chamadas com o cabeçalho **Authorization** tipo **Bearer**.

#### Endpoint de Autenticação (POST /login)

O endpoint deve responder a requisições *POST* no caminho **/login**. Exemplo:
 
```
POST /login HTTP/1.1
Host: localhost
Content-Type: application/json
{
	"usuario" : "admin",
	"senha" : "adminpassword"
}
```

Deve retornar status 202 e corpo similar ao seguinte:
```json
{
	"token" : [VALOR_DO_TOKEN_QUE_DEVE_SER_UTILIZADO_COMO_BEARER_NAS_REQUISICOES]
}
```

Quando credenciais incorretas forem informadas, o status de retorno deve ser 403.

### API

#### GET /habitantes

Uma chamada REST para este endpoint deve retornar todos os habitantes cadastrados, junto com seus endereços.
Exemplo de Resposta:
```json
[
{
	"codigo" : "00000000191",
	"nome" : "João da Silva",
	"enderecos" : [
		{
			"codigoPostal" : "88036003",
			"logradouro" : "Rua Lauro Linhares",
			"bairro" : "Trindade",
			"numero" : 2123,
			"complemento" : "Torre B, 3o Andar",
			"localidade" : "Florianópolis",
			"uf" : "SC"
		},
		{
			"codigoPostal" : "88036001",
			"logradouro" : "Rua Lauro Linhares",
			"bairro" : "Trindade",
			"numero" : 739,
			"complemento" : "Bloco B, Apto 404",
			"localidade" : "Florianópolis",
			"uf" : "SC"
		}		
	]
},
{
	"codigo" : "24152889934",
	"nome" : "José dos Santos",
	"enderecos" : [
		{
			"codigoPostal" : "88010000",
			"logradouro" : "Rua Felipe Schmidt",
			"bairro" : "Centro",
			"numero" : 410,
			"localidade" : "Florianópolis",
			"uf" : "SC"
		}	
	]
}
]
```

#### GET /habitantes/{codigo}

Uma chamada REST para este endpoint deve retornar as informações de um habitante e seus endereços. Por exemplo:

```
GET /habitantes/00000000191 HTTP/1.1
Host: localhost
Authorization: Bearer [VALOR_DO_TOKEN]
cache-control: no-cache
```
Forneceria a resposta:
```json
{
	"codigo" : "00000000191",
	"nome" : "João da Silva",
	"enderecos" : [
		{
			"codigoPostal" : "88036003",
			"logradouro" : "Rua Lauro Linhares",
			"bairro" : "Trindade",
			"numero" : 2123,
			"complemento" : "Torre B, 3o Andar",
			"localidade" : "Florianópolis",
			"uf" : "SC"
		},
		{
			"codigoPostal" : "88036001",
			"logradouro" : "Rua Lauro Linhares",
			"bairro" : "Trindade",
			"numero" : 739,
			"complemento" : "Bloco B, Apto 404",
			"localidade" : "Florianópolis",
			"uf" : "SC"
		}		
	]
}
```
Caso o usuário não exista, o retorno deve conter corpo vazio e status HTTP 404.

#### POST /habitantes

Esta chamada REST deve cadastrar um habitante. Por exemplo:
```
POST /habitantes HTTP/1.1
Host: localhost
Authorization: Bearer [VALOR_DO_TOKEN]
Content-Type: application/json
{
	"codigo" : "02873871350",
	"nome" : "Maria Xavier",
	"enderecos" : [
		{
			"codigoPostal" : "88010000",
			"numero" : 410
		}	
	]
}
```
Deve retornar o seguinte, contendo status http 201 e um header *Location* com a URL de acesso ao habitante cadastrado:
```json
{
	"codigo" : "02873871350",
	"nome" : "Maria Xavier",
	"enderecos" : [
		{
			"codigoPostal" : "88010000",
			"logradouro" : "Rua Felipe Schmidt",
			"bairro" : "Centro",
			"numero" : 410,
			"localidade" : "Florianópolis",
			"uf" : "SC"
		}	
	]
}
```
##### Regras
* Do endereço, só devem ser informados **código postal**, **número** e, quando houver, um **complemento**. As demais informações devem ser recuperadas do serviço [ViaCEP](https://viacep.com.br)
* Quando um código postal inválido é informado, deve-se negar o cadastro, retornando um status http 400. Um CEP contendo caracteres não numéricos é inválido. Um CEP que não possui correspondência no ViaCEP também é inválido.
* Quando já existir um habitante com o código informado, deve-se negar o cadastro, retornando um status http 409

#### PUT /habitantes/{codigo}

Esta chamada atualiza um habitante com as informações entradas. Por exemplo:
```
PUT /habitantes/02873871350 HTTP/1.1
Host: localhost
Authorization: Bearer [VALOR_DO_TOKEN]
Content-Type: application/json
{
	"codigo" : "02873871350",
	"nome" : "Maria Xavier",
	"enderecos" : [
		{
			"codigoPostal" : "88050000",
			"numero" : 2000
		}	
	]
}
```
Deve retornar uma resposta de corpo vazio e status 204.

##### Regras
* Quaisquer endereços que não constem no corpo da requisição devem ser excluídos. 
* Endereços do corpo da requisição que eram inexistentes devem ser criados.
* Quando um código postal inválido é informado, deve-se negar o cadastro, retornando um status http 400. Um CEP contendo caracteres não numéricos é inválido. Um CEP que não possui correspondência no ViaCEP também é inválido.
* Se o habitante sendo atualizado não existir na base, deve ser retornado status http 404.

#### DELETE /habitantes/{codigo}

Esta chamada remove um habitante. Por exemplo:
```
DELETE /habitantes/02873871350 HTTP/1.1
Host: localhost
Authorization: Bearer [VALOR_DO_TOKEN]
Content-Type: application/json
```
Deve retornar uma resposta de corpo vazio e status 204.

##### Regras
* Se o habitante com o código informado não existir, deve-se retornar status 404.
