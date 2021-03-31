# avalicao1
Atividades avaliativas 1, contendo os Exercícios Teóricos e Práticos

=====================Exercícios Práticos=====================
Caminhos :
http://localhost:8080/contador  => conta a quantidade de Acessos
http://localhost:8080/pessoas  => Mostra lista de Pessoas e Cadastra
http://localhost:8080/produtos => Mostra lista de Produtos e Cadastra
http://localhost:8080/pedidos => Mostra lista de Pedidos e Cadastra


=========================Exercícios Teóricos====================================

ET1)http é um protocolo de comunicação de texto que permite o envio de informações através do navegador até o servidor. Nesse caminho de envio de informações até o servidor é possível capturar dados permitindo então com que pessoas mal intencionadas consiga interceptar e pegar informações durante essa transação.

https segue o padrão do https de envio de comunicação porém possui uma camada a mais que seria a Securty, deste modo evita com que os dados sejam capturados nesse processo de comunicação, disponibiliza um certificado que realiza a comunicação entre o cliente(navegador) e o servidor;


ET2) Rest é um conjunto de princípios de arquitetura, que se baseia no protocolo http.
Possibilita com que os desenvolvedores web crie apis Rest de várias formas, possuindo vantagens de flexibilidades, ser leve e rápido. Um sistemas central possuindo a implementação Rest permite com que várias aplicações feitas por linguagens distintas se comuniquem;



ET3) SQL Injection
É um ataque que injeta código sql em sistemas forçando consultas no banco de dados, esse foi feito em bastante aplicações que possuía mensagens de erro de retorno do banco de dados.
Supondo que uma aplicação web foi feita e em um campo de "login" e "senha" ou cadastro por exemplo, que autenticava diretamente com o banco
permitia com que o invasor quebra-se o sql feito para a função, usando aspas, true e false;

Umas das formas de se evitar isso é focar na segurança entre sua aplicação e o banco, separando os dois, ou utilizar outras frameworks 
que possuam segurança sólida e testada.
