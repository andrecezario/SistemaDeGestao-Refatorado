## **Sistema de Gestão de Produtividade Acadêmica**
**Aluno:** André Luiz de Oliveira Cezário

### **Funcionalidades**
**1. O sistema deve permitir a edição de projetos de pesquisa (Implementada)**

*a) alocação de participantes*

Implementada na main, recebe um título de um projeto e nome de um usuário, faz pesquisa no *BD*, e caso as informações retornem algum projeto e usuário e os mesmos 
estejam de acordo com as restrições, adiciona o usuário na ArrayList de participantes do projeto.

*b) alteração de status*

Implementada na main, recebe um título de um projeto e faz pesquisa no *BD*, caso encontrado, solicita alteração do estado atual para o subsequente, atendendo
as restrições para efetivá-la.

**2. O sistema deve permitir a inclusão de informações referentes à produção acadêmica (Implementada)**

Implementada na main, permite a criação de produções acadêmicas (orientações ou publicações) implementadas na classe *BD*; e associação de projetos de pesquisa a publicações, através da
atualização do projeto de pesquisa da publicação, pelo informado.

**3. O sistema deve oferecer consultas (Implementada)**

*a) por colaborador*

Recebe um colaborador na main, mostra as informações básicas (nome e e-mail) e através dos métodos sortColabProj(Colaborador colab) e sortColabProducao(Colaborador colab) da classe *BD* exibe a lista de projetos nos quais este
colaborador participou, ordenados de forma decrescente pela data de término; e lista de sua produção acadêmica ordenada de forma decrescente por ano, respectivamente.

*b) por projeto*

Implementada na classe main e busca na *BD*, recebe o título do projeto e retorna colaboradores alocados e lista de toda a produção
acadêmica do projeto, ordenada por ano.


**4. O sistema deve fornecer um relatório de produção acadêmica do laboratório (Implementada)**

Implementada na classe *BD*, através do método relatorioProducao() exibe o número de colaboradores, projetos em elaboração, projetos em andamento, projetos concluídos, total de projetos e número de produção acadêmica por tipo de produção.


### **Classes**

**Usuario**
- Motivação

O sistema é composto por alunos de graduação, mestrado, doutorado, professores e pesquisadores, como pode ser constatado é um público do meio acadêmico, porém possuem distinções.
Algumas características são comuns a todos, toda pessoa possui um nome e um e-mail. Neste caso, ao invés de replicar estes atributos nos 5 subtipos de usuário, podemos adicioná-los numa superclasse Usuario e criar
as subclasses descritas no problema.

- Solução

Criação de uma superclasse abstrata capaz modularizar melhor o problema, concentrando as informações gerais e dados pertinentes ao usuário em um único lugar. O uso da classe abstrata se deve ao fato de não ser necessário em nenhum momento instanciar um objeto do tipo Usuario diretamente. Essa classe, tem como objetivo servir unicamente de modelo e superclasse para as classes filhas, que realmente
são instanciadas no sistema.

- Vantagens

Reunir todas as informações do usuário em uma única classe, de modo que seja possível obter todos os dados de um determinado objeto usuário, manipulando
a classe *Usuario*. Neste caso, graças a herança é possível por exemplo utilizar um ArrayList de colaboradores do tipo Usuario e este armazenar os 5 tipos de colaboradores (Aluno graduação, mestrado, doutorado, professor e pesquisador)
, evitando o uso de 5 ArrayList para isso. Além disso, sem ela, os atributos teriam que ser adicionados em cada tipo de usuário e haveria duplicação de código.

**Professor**

- Motivação

De acordo com a descrição do sistema, um colaborador pode ser um professor. Logo, uma classe que tratasse do tipo de usuário professor, com seus atributos e particularidades seria o ideal. Por exemplo, o professor possui um Nº de SIAPE, atributo que um aluno
não possui. Deste modo, é importante que haja uma classe deste tipo no sistema. 

- Solução

Criação de uma subclasse da superclasse *Usuario*, do tipo Professor. Está subclasse herdará os atributos da classe pai Usuario, de acordo com os mecanismos de herança, mas 
ainda receberá o atributo específico de um professor o nº de SIAPE.

- Vantagens

Reunir todas as particularidades de um tipo específico de usuário em uma única classe pertinente. Haja vista, que em modificações futuras o professor pode receber mais atributos ou mesmo ter métodos próprios, além de getters e setters, nesse caso
como já possui uma classe específica para ele, é só adicionar as alterações nessa classe. Isto facilita extensibilidade do código.

**AlunoDoutorado**

- Motivação

Pela descrição do sistema, um colaborador pode ser um aluno de doutorado. Nesse caso, uma classe que se tratasse do tipo de usuário aluno de doutorado, com seus atributos e particularidades seria o ideal para o sistema, pois melhoraria a extensibilidade do código, bem
como manutenibilidade em alterações específicas em colaboradores desse tipo.

- Solução

Criação de uma subclasse da superclasse *Usuario*, na qual receberá o atributo específico de um aluno o nº de matrícula.

- Vantagens

Reunir todas as particularidades de um tipo específico de usuário em uma única classe pertinente. 

**AlunoMestrado**

- Motivação

Uma classe que se tratasse do tipo de usuário aluno de mestrado, com seus atributos e particularidades.

- Solução

Criação de uma subclasse da superclasse *Usuario*, na qual receberá o atributo específico de um aluno o nº de matrícula.

- Vantagens

Reunir todas as particularidades de um tipo específico de usuário em uma única classe pertinente.

**AlunoGraduacao**

- Motivação

Uma classe que se tratasse do tipo de usuário aluno de graduação, com seus atributos e particularidades.

- Solução

Criação de uma subclasse da superclasse *Usuario*, na qual receberá o atributo específico de um aluno o nº de matrícula.

- Vantagens

Reunir todas as particularidades de um tipo específico de usuário em uma única classe pertinente.

**Pesquisador**

- Motivação

Uma classe que se tratasse do tipo de usuário pesquisador, com seus atributos e particularidades.

- Solução

Criação de uma subclasse da superclasse *Usuario*.

- Vantagens

Reunir todas as particularidades de um tipo específico de usuário em uma única classe pertinente.

**Projeto**

- Motivação

Uma classe para armazenar informações referentes a cada projeto do sistema, de acordo com o domínio do problema.

- Solução 

Criação de uma classe capaz de modularizar melhor o problema, concentrando as informações gerais e dados relacionados ao projeto em um único lugar. A mesma possui um construtor que recebe titulo, data_inicio, data_fim, agencia_finaciadora,
valor_financiado, objetivo e descricao que são as informações obrigatórias para criação do projeto. Além disso, ele possui como atributo um private ArrayList de participantes do tipo Usuario.

- Vantagens

A vantagem, está na modularização do problema mais geral em entidades relacionadas ao domínio ou negócio do sistema. 
De modo que, facilita a busca e utilização de informações importantes para o funcionamento das funcionalidades do sistema.

**Publicacao**

- Motivação

Uma classe para armazenar informações referentes a cada publicação do sistema de gestão acadêmica, de acordo com o domínio do problema.

- Solução 

Criação de uma classe capaz de modularizar melhor o problema, concentrando as informações gerais e dados pertinentes a publicação em um único lugar. 
A mesma possui um construtor que recebe titulo, nomeConferencia, anoPublicacao e uma ArrayList de autores do tipo Usuário que são as informações obrigatórias para criação da publicação.

- Vantagens

A vantagem, está na modularização do problema mais geral em entidades relacionadas ao domínio ou negócio  do sistema. 
De modo que, facilita a busca e utilização de informações importantes para o funcionamento das funcionalidades do sistema.

**Orientacao**

- Motivação

Uma classe para armazenar informações referentes a cada orientação do sistema de gestão acadêmica, de acordo com o domínio do problema.

- Solução 

Criação de uma classe capaz de modularizar melhor o problema, concentrando as informações gerais e dados pertinentes a orientação em um único lugar. A mesma possui um construtor que recebe 
titulo, orientador (tipo Professor), orientando (tipo Usuario) e ano que são as informações obrigatórias para criação de uma orientação.

- Vantagens

A vantagem, está na modularização do problema mais geral em entidades relacionadas ao domínio ou negócio do sistema. 
De modo que, facilita a busca e utilização de informações importantes para o funcionamento das funcionalidades do sistema.

**BD**

- Motivação

Armazenamento de usuários, projetos, publicações e orientações, bem como para criação de métodos que implementam funcionalidades do sistema e operações de 
CRUD. Sendo esta classe requisitada pela Main sempre que necessário.

- Solução

Para armazenamento de todos os usuários, projetos, publicações e orientações do sistema, foram criados quatro ArrayList para cada tipo de objeto. 
Na implementação das funcionalidades, foram implementados métodos para cadastro de projeto, usuário, publicação e orientação, busca de usuário,
projeto, publicação e orientação, checagem de informações, ordenações e relatório.

- Vantagens

Armazenamento de todas os objetos criados em um única classe, para operações de CRUD e funcionalidades, facilitando o reuso em outras classes, como na Main.

**Main**

- Motivação

A classe main foi criada para implementar a GUI na qual são recebidas as entradas e exibidos os resultados para o usuário no console.

- Solução

Neste caso foi utilizado o Scanner como canal de entrada e no console foram exibidas de forma concisa e simples os passos que o usuário poderia seguir para acessar as funcionalidades do sistema. De acordo com a operação eram realizadas chamadas de métodos ou acesso a atributos da classe *BD*, onde essa era um atributo 
(objeto) da classe *Main*.

- Vantagens

Implementação da GUI.


### **Distribuição dos Métodos**

Os métodos mais gerais como Getters, setters e toString foram adicionadas nas classes da camada lógica do problema.

Já os métodos mais específicos que dizem respeito a implementação de funcionalidades propriamente ditas do sistema, foram todos adicionados na classe de banco de dados
*BD*, especificamente:

- buscarProjeto(String titulo)
- cadastrarProjeto(Projeto p)
- cadastrarUsuario(Usuario u)
- buscarUsuario(String nome)
- professorAlocado(Projeto p)
- alunoIndisponivel(Usuario u)
- checkInfoProj(Projeto p)
- checkPubProj(Projeto p)
- addPublicacao(Publicacao p)
- addOrientacao(Orientacao o)
- buscarPublicacao(String titulo)
- colabProj(Usuario colab, Projeto proj)
- colabPub(Usuario colab, Publicacao pub)
- colabOrientacao(Professor prof, Orientacao orient)
- sortColabProducao(Usuario colab)
- sortColabProj(Usuario colab)
- projPub(Projeto proj, Publicacao pub)
- sortProjPub(Projeto proj)
- relatorioProducao()

- Motivação 

A motivação para isso foi o reuso de métodos que são invocados mais de uma vez na *Main*, como os métodos de busca, além da implementação de outros métodos
que atendem funcionalidades específicas. 

- Solução

Criação de uma classe de banco de dados *BD* que armazena todos os métodos referentes às operações do sistema. Está solução permite que esses métodos sejam reaproveitados em outra classe, neste caso, na *Main*, onde poderão ser invocados a qualquer
momento, a escolha da classe *BD* para estes métodos, é justamente por a mesma obter todos os dados armazenados e as operações do sistema necessitarem dessas
informações para serem implementadas.

- Vantagens

A implementação dos métodos no banco de dados, tem muitas vantagens, a principal delas é o reuso. Qualquer operação no sistema, basicamente, requer uma consulta na base de dados tanto para verificação de existência daquele objeto, quanto
o retorno do mesmo. A Main poderia implementar esses métodos, porém fugiria um pouco do escopo da mesma que é implementar a GUI. Implementar esse tipo de método no banco, facilita o reuso desta funcionalidade na Main, basta instanciar um objeto do tipo *BD* e fazer
chamadas aos métodos quando necessário. Além disso, temos a facilidade para alterações futuras, ao invés de distribuir os métodos em classes distintas, podemos encontrá-lo facilmente no banco e modificá-lo, tento ganhos no quesito manutenibilidade.

### **Herança**

- Motivação

No sistema de gestão de produtividade acadêmica os usuários eram divididos em diversos subtipos de usuário, 5 especificamente. Logo, para não duplicar códigos como atributos e getters e setters entre os subtipos,
o ideal é fazer uso da herança.

- Solução

Criação da superclasse Usuario, e subclasses Professor, AlunoDoutorado, AlunoMestrado, AlunoGraduacao e Pesquisador, pois permitirá o compartilhamento de atributos comuns através da herança e ainda assim não retroagirá atributos particulares, já que eles podem ser adicionados na subclasse.
  
- Vantagens

Extensibilidade de código, evita duplicação de código, já que os métodos podem ser concentrados na superclasse, que por sua vez são herdados na subclasse.

### **Abstrata**

- Motivação

A superclasse Usuário já reunia os atributos necessários para implementar a herança, porém no sistema o usuário não possui nenhum método a ser implementado nesta classe, nem 
é instanciado no sistema, logo, esta classe era apenas um modelo para suas subclasses, que por sua vez seriam instanciadas.

- Solução

Como a superclasse usuário não era instanciada nem tinha métodos, além dos getters e setters implementados, então ela foi considerada como uma
classe abstrata.
            
- Vantagens

Instanciar objetos de acordo com seu tipo específico, evitando casts, uma vez que não é possível instanciar um objeto do tipo Usuario por ser uma classe abstrata. 

- Desvantagens

Sempre que for necessário instanciar um usuário, ele deve ser feito instanciando o objeto do tipo requisitado e não do tipo Usuario, já que o mesmo é uma classe abstrata.
 
### **Extensibilidade**

- Motivação

O uso de herança no sistema, possui uma característica importante nos softwares: é a extensibilidade. Essa prática, como já foi citada, é importante pois facilita alterações que venham
a surgir no sistema e modulariza melhor o mesmo, definindo entidades e tipos em classes específicas. Por exemplo, a adição de mais atributos ou métodos a um tipo de usuário, não interfere nos demais, visto que
foram implementados em classes diferentes.
             
- Solução

Criação da superclasse Usuario e subclasses Professor, AlunoDoutorado, AlunoMestrado, AlunoGraduacao e Pesquisador.
  
- Vantagens

Reutilização atributos da superclasse herdados, evitando duplicação de código. Facilidade para efetuar mudanças no sistema.
  
### **Reuso**

- Motivação 

Utilização de trechos de código em mais de uma parte do programa, evitando implementação desnecessária.

- Solução

Criação de métodos para comportamentos que eram necessários em mais de uma operação do sistema, principalmente os métodos
de busca e checagem de informações. Neste caso, todos foram agrupados na classe *BD* e sempre que requisitados na *Main* para efetuar alguma
funcionalidade, eram invocados a partir de um objeto do tipo BD. 

- Vantagens

Reutilização desses códigos e de sua funcionalidade em outra parte programa, evitando repetições de código na implementação de funções.
