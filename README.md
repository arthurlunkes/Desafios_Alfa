# Desafios_Alfa
 
![desafio 1](https://github.com/arthurlunkes/Desafios_Alfa/blob/main/images/desafio1.PNG)

## Descrição do projeto

Desafios da Alfa Consultorias realizados utilizando spring boot com java, react-js e banco H2.

Desafio 1: Criar um gerenciador de chamados simples(CRUD).

Desafio 2: Criar uma API para consumir outra API de buscar receitas pelo nome do prato.

## Tópicos importantes

<ol>
<a href="#Backend-desafio1"><li>Backend desafio 1(Java com Spring)</li></a>
<a href="#Frontend-desafio1"><li>Frontend desafio 1(React)</li></a>
<a href="#Backend-desafio2"><li>Backend desafio 1(Java com Spring)</li></a>
<a href="#Informacoes"><li>Informações adicionais</li></a>
<a href="#Passos"><li>Passos para rodar esse projeto</li></a>
<a href="#Consideracoes"><li>Considerações finais</li></a>
</ol>

### O que foi utilizado para desafio 1

-   Java 17
-   Spring Boot 
-   React

<div id="Backend-desafio1">
<h2>Backend desafio 1(Java com Spring)</h2>
</div>

### Descrição

O Backend do sistema foi desenvolvido utilizando a linguagem Java juntamente com Spring. Essa camada é responsável por gerenciar a lógica de negócio, acessar o banco de dados e disponibilizar APIs REST para serem consumidas pelo Frontend.

| Método | Backend | Frontend |
|--|--|--|
| GET | ✔️ | ✔️ |
| POST | ✔️ | ✔️ |
| PUT | ✔️ | ✔️ |
| DEL | ✔️ | ✔️ |

### Funcionalidades Implementadas

-   CRUD (Create, Read, Update, Delete) de chamados
-   Validação de dados e tratamento de erros
-   Integração com banco de dados utilizando Spring Data JPA e banco de dados em memória H2
-   Implementação de endpoints REST
-   Documentação da API utilizando o Postman

<div id="Frontend-desafio1">
<h2>Frontend desafio 1(React)</h2>
</div>

### Descrição

O Frontend do sistema foi desenvolvido utilizando a biblioteca React. Essa camada é responsável por apresentar a interface de usuário, realizar requisições para o Backend e exibir os dados de forma interativa e amigável.

### Funcionalidades Implementadas

-   Criação de componentes reutilizáveis
-   Gerenciamento de estado
-   Roteamento de páginas utilizando React Router
-   Consumo de APIs RESTful utilizando a biblioteca Axios
-   Estilização de componentes utilizando CSS e bibliotecas

<div id="Backend-desafio2">
<h2>Backend desafio 2(Java com Spring)</h2>
</div>

### Descrição

O Backend do sistema foi desenvolvido utilizando a linguagem Java juntamente com Spring. Essa camada é responsável por gerenciar a lógica de negócio e disponibilizar APIs REST para serem consumidas.

| Método | Backend |
|--|--|
| GET | ✔️ |
| POST | ✔️ |
| PUT | ✔️ |
| DEL | ✔️ |

### Funcionalidades Implementadas

-   Validação de dados e tratamento de erros
-   Implementação de endpoints REST
-   Documentação da API utilizando o Postman

<div id="Informacoes">
<h2>Informações adicionais</h2>
</div>

- Algumas informações úteis estão no arquivo application.properties para o desafio 1:
![properties desafio 1](https://github.com/arthurlunkes/Desafios_Alfa/blob/main/images/properties.PNG)
- Pode acessar a documentação de consumo dos endpoints no postman [aqui!](https://www.postman.com/spacecraft-participant-60213181/workspace/workspacepublic/collection/19564710-269cef77-f786-44e7-a250-577d71f4e615?action=share&creator=19564710), está tudo organizado em pastas e cada request tem seus exemplos de sucesso e erro.
![postman](https://github.com/arthurlunkes/Desafios_Alfa/blob/main/images/postman.PNG)

- Quando rodar o backend do desafio 1, pode acessar o banco H2 pelo navegador utilizando o link: http://localhost:8080/h2-console

<div id="Passos">
<h2>Passos para rodar esse projeto</h2>
</div>

1. Baixe esse repositório via git ou GitHub Desktop
2. Abra a pasta do backend com a IDE de sua preferência
3. Baixe as dependências do projeto, seguindo os passo a passo da sua IDE
4. Starte a aplicação a partir da classe ...Application
5. Abra a pasta do frontend com o VSCode(exceto para projeto 2 que não tem frontend)
6. Abra o terminal na pasta
7. Rode o comando npm install (Para instalar as dependências)
8. Após terminar de baixar, rode o comando npm start
9. Pronto para testar!

<div id="Consideracoes">
<h2>Considerações finais</h2>
</div>

O desenvolvimento dos desafios utilizando java com Spring Boot e React foi concluído com sucesso, todos os requisitos funcionando certinho. Foi feito o tratamento dos erros no desafio 2, com os códigos HTTP certinhos, visto que a API original não tem esses tratamentos.
