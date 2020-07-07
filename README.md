# Quake Log Parser

Aplicativo para ler e interpretar aquivo de Log do jogo Quake 3.

# Recursos

  1. Coletar informações sobre a(s) partidas(s) contidos no arquivo de log*.
        2. Resumo da(s) partida(s).
        3. Expor dois end-points em uma API Rest para a consulta dos dados:
            a. Consultar lista de jogos (Get)
            b. Consultar jogo individual (GetByid)*

* Suporta arquivos em formato TXT. Para desenvolver outros formatos, basta implementar a classe abstrata QuakeLogParserFile.
** Para descobrir o ID do jogo, é necessário consultar o end-point 3a (Get)

### Tecnologias utilizadas

* [Java] - JDK Version 8 Update 251 - Data da release - 14 de abril de 2020 
* [SpringBoot] - v2.3.1.RELEASE (TomCat embedded 9.0.36)
* [Swagger] - springfox-swagger2 v2.92
* [Lombok] - Lombok v1.18.12 "Envious Ferret" is installed. https://projectlombok.org/
* [Maven] - Apache Maven 3.6.3 - Gerenciamento de dependências
* [JUnit] - Testes unitários - jupiter v5.6.2

### Classes de testes

* src\test\java\com\danilogimenes\quakeLogParser\repository\QuakeLogParserTXTTest.java:
    Realiza teste na classe QuakeLogParserTXT. Verifica se a quantide de jogos iniciados é compativel com a quantidade partidas.

* src\test\java\com\danilogimenes\quakeLogParser\controller\GameControllerTest.java:
    Realiza testes de integração.

* src\test\java\com\danilogimenes\quakeLogParser\domain\GameTest.java:
    Realiza teste diversos com a classe Game do modelo de dados que possui os as partidas instanciadas.

### Instalação

Para processar um arquivo de log do jogo, você deve colocar um arquivo de log na pasta /raw do projeto com o nome "games.log". O projeto já possui um!
    
Para rodar a aplicação é necessário seguir os seguintes passos:

1. Abra o terminal do seu S.O e digite "java -version" e confira se a versão é no mínimo "1.8.0_251" ou superior. Caso for inferior:

Instale: 
   [Java] - JDK Version 8 Update 251 - Data da release - 14 de abril de 2020 ou superior
        Obs: Confira se a variável de ambiente PATH do S.O possui a entrada apontando para a pasta onde o java está instalado.
        Exemplo no windows: abra o prompt de comando e digite: echo %PATH% ou echo %JAVA_HOME% e confira se existe uma entrada do tipo: C:\Program Files\Java\jdk1.8.0_251. Que é o local onde o java foi instalado no seu computador.

2. Com o java instalado, abra o terminal e siga os passos: 
    1. Acesse a pasta principal do projeto "quakeLog-Parser" e execute o comando:
        - mvnw clean install
        - mvnw spring-boot:run
    
3. Acesse o endereço: http://localhost:8080/swagger-ui.html

4. Teste!

5. Gratidão! ;)

