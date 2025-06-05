# Resumo

Pet Cemetery é uma plataforma web dedicada ao gerenciamento de um cemitério de pets, onde usuários clientes podem adquirir jazigos e contratar diversos serviços como enterro, exumação e ornamentação, marcar reuniões, lembretes de visita, dentre outros. Já administradores possuem funcionalidades para visualizar clientes com pagamentos atrasados, montar relatórios CSV ou PDF contendo diversas informações, alterar o horário de funcionamento do cemitério, dentre outras.

## Tecnologias Utilizadas

Este projeto utiliza um conjunto moderno de tecnologias para garantir robustez, escalabilidade e uma ótima experiência de desenvolvimento e uso. Abaixo estão as principais tecnologias e integrações empregadas:

### Back-end

- **Java 20**  
  Linguagem principal para desenvolvimento do back-end.

- **Spring Boot 3.4.3**  
  Framework para construção de aplicações Java, facilitando a configuração, segurança, persistência e integração.

- **Spring Data JPA**  
  Abstração para acesso a bancos de dados relacionais.

- **Spring Data MongoDB**  
  Integração com banco de dados NoSQL MongoDB.

- **Spring Security**  
  Gerenciamento de autenticação e autorização.

- **Spring Boot Mail**  
  Envio de e-mails automáticos.

- **JWT (jjwt 0.11.5)**  
  Autenticação baseada em tokens JWT.

- **PostgreSQL**  
  Banco de dados relacional utilizado em produção.

- **MongoDB**  
  Banco de dados NoSQL para funcionalidades específicas.

- **Lombok**  
  Redução de boilerplate no código Java.

### Front-end

- **Angular 19.2.2**  
  Framework para desenvolvimento de aplicações web SPA.

- **TypeScript**  
  Superset do JavaScript, trazendo tipagem estática ao front-end.

- **Tailwind CSS 4.1.4**  
  Framework utilitário para estilização rápida e responsiva.

- **RxJS 7.8.0**  
  Programação reativa no Angular.

- **jsPDF 3.0.1 & jspdf-autotable 5.0.2**  
  Geração de relatórios PDF no front-end.

- **jwt-decode 4.0.0**  
  Decodificação de tokens JWT no front-end.

### Integrações

- **WeatherAPI**  
  Previsão do tempo utilizando dados meteorológicos.

- **Google Auth**  
Possibilidade de autenticação via conta google. - _EM PROGRESSO_
  
  
---

> Consulte o arquivo `pom.xml` para detalhes das dependências do back-end e `package.json` para as do front-end.



# Minimundo

Pet Cemetery é uma plataforma online inovadora dedicada ao gerenciamento de jazigos que permite os donos de pets falecidos escolherem e administrarem seus jazigos com facilidade. Cada jazigo possui um número único e uma localização específica dentro do cemitério, facilitando a localização e a identificação das sepulturas. Os clientes podem adquirir jazigos mesmo que não tenham pets para enterro. Todo jazigo tem um histórico.

A plataforma permite aos clientes acessarem o sistema, visualizarem o mapa do cemitério e selecionarem um jazigo específico. Os jazigos devem ter um tamanho padrão. Jazigos podem ser comprados de forma perpétua ou alugados por 3 anos. Os jazigos podem ser personalizados com uma foto e uma mensagem de até 80 caracteres. A personalização de jazigo deve ter a aprovação do administrador. Os clientes podem configurar lembretes de visitas regulares ao jazigo e adicionar notas para manter o registro de eventos da vida do pet. Não é permitido revender ou transferir jazigos para outras pessoas.

O sistema fornece acesso a um gerenciamento financeiro na plataforma, controlando pagamentos, atrasos e doações para instituições de caridade relacionadas a pets. O cadastro de informações sobre cada jazigo é permitido, como quais estão disponíveis ou ocupados. O cliente deve conseguir alterar as informações de cadastro.

A manutenção do jazigo é cobrada anualmente e é obrigatória. Se o proprietário não pagar as taxas de manutenção, o cemitério pode remover os restos mortais do pet do jazigo. O sistema deve notificar com antecedência sobre a necessidade de renovar o jazigo alugado ou optar pela remoção dos restos mortais. Caso a renovação não seja realizada dentro do prazo estipulado, o jazigo será considerado disponível novamente para outros proprietários e os restos mortais serão realocados conforme a política do cemitério.

Enterros têm um valor separado e podem ser pagos juntos da compra/aluguel do jazigo ou podem ser pagos posteriormente no momento do enterro do pet. Horários de visita e horários de enterro devem ser mantidos em uma tabela administrativa. Locatários ou proprietários podem agendar reuniões com a administração do cemitério. Agendamentos devem ter no mínimo dois dias de antecedência. O administrador deve visualizar as reuniões.

O sistema de gerenciamento do cemitério envia notificações por e-mail para clientes com pagamentos atrasados e informa os administradores mensalmente sobre a inadimplência. Os serviços oferecidos pelo cemitério incluem exumação, enterro, manutenção anual, ornamentação, aluguel e compra de jazigos. Os valores desses serviços são armazenados em uma tabela administrativa. A plataforma possui uma página "Quem somos" no site e uma página de planos de ornamentação.

O controle de pagamentos dos proprietários dos jazigos é realizado pelo sistema, indicando quais estão em dia e quais estão em atraso. O registro de óbitos ocorridos no cemitério é permitido, incluindo a localização do jazigo e o nome do pet falecido. Além disso, o sistema informa o horário de funcionamento, que pode ser atualizado por um administrador em caso de eventualidades. O sistema possibilita a geração de relatórios diversos, como relatórios de ocupação dos jazigos e pagamentos em atraso, e mantém um registro de todo histórico de pets já registrados em um jazigo. A ornamentação dos jazigos é obrigatória e possui três planos: basic, silver e gold. Os clientes também têm a opção de ornamentar seus jazigos por conta própria.

A plataforma Pet Cemetery conta com características importantes como responsividade, garantindo a adaptação da aplicação a diferentes dispositivos e interfaces; desempenho, assegurando eficiência no armazenamento e recuperação de dados por meio de um banco de dados relacional;
