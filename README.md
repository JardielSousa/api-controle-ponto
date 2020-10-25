# API Controle Ponto

## Objetivo

Desenvolver aplicação de controle de ponto.

## Instalação
Alterar o arquivo **application.properties** com as credenciais do **Postgresql** que irá ser usada na aplicação.

Criar o banco de dados com o mesmo nome que está na propriedade **spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/controleponto** do arquivo **application.properties**. 

## Execução
#### IDE favorita
O projeto pode ser executado por uma IDE que possua suporte a Java (Eclipse IDE, IntelliJ IDEA, ...), executando o arquivo **ControlePontoApplication.java**.

#### Linha de comando
Caso queira executar via linha de comando, o Spring disponibiliza **Wrapper** do **Maven** para não precisar ter o mesmo instalado.
Para executar basta entrar na pasta raiz do projeto e executar **mvnw spring-boot:run**.
