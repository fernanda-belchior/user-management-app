**User Management Application**

Projeto Java Enterprise composto pelos seguintes módulos: POM (parent com a organização dos módulos e dependências gerais), EJB(dados e negócios com EJBs), WAR (views e managed beans), EAR(módulo de deploy que encapsula os módulos EJB e WAR).

Desenvolvida com: H2 database em memória, servidor Wildfly por plugin Maven versão 2.0.1.Final, JPA/Hibernate, JSF/Primefaces, EJBs, arquitetura em camadas seguindo o padrão MVC com a distribuição nos projetos, plugins Lombok e SonarLint, Intellij IDEA 2020.1.4, JDK 11, Maven 3.6.3, Log4j.

A configuração do servidor que usei é a padrão e a porta para acessar a URL após o deploy é a 8080.

Processo para realizar o deploy:
- deletar as pastas "target" de cada módulo.
- entrar no diretório principal (user-management-app) e dar o comando "mvn clean install".
- verificar .jar das dependências estão classpath (principalmente a javaee-api), caso contrário inserir o .jar manualmente.
- entrar no diretório de deploy (user-management-ear) e executar o comando "mvn wildfly: run". O servidor irá fazer o deploy e a URL principal para acesso é a de login.
- Acessar a URL: "localhost:8080/user-management-war/", os dados de login são: "admin@admin.com.br" e "admin", após o login com sucesso o sistema redirecionará para a página de gerenciamento de usuários.

* O deploy pode ter algum erro por demora do servidor, neste caso basta executar o comando novamente.
* Caso a porta 8080 esteja ocupada, pode-se alterar as configurações no próprio pom.xml.
* As Views de user não estão totalmente completas e os testes unitários não foram desenvolvidos.

Caso possua um servidor Wildfly local, basta fazer o deploy do arquivo ".ear" disponibilizado na target do módulo EAR após o build do projeto. O context é o mesmo: ".../user-management-war/".

