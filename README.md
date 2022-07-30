# Bootcamp Spring 3.0 - Cap. 02
## Testes automatizados no back end

### Bootcamp Spring 3.0 DEVSUPERIOR

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/bob-okamura/dscatalog-rev-cap02/blob/main/LICENCE)

## Competências

### Fundamentos de testes automatizados
- Tipos de testes
- Benefícios
- TDD - Test Driven Development
- Boas práticas e padrões

### JUnit
- Básico (vanilla)
- Spring Boot
  - Repositories
  - Services
  - Resources (web)
  - Integração

### Mockito & MockBean
- @Mock
- @InjectMocks
- Mockito.when / thenReturn / doNothing / doThrow
- ArgumentMatchers
- Mockito.verify
- @MockBean
- @MockMvc

## Etapas
- Fundamentos + JUnit vanilla (exercício de fixação)
- Testes de repository (exercício de fixação)
- Testes de unidade com Mockito (exercício de fixação)
- Testes da camada web com MockMvc (exercício de fixação)
- Testes de integração

## Fundamentos de testes automatizados
### Tipos de testes
#### Unitário
Teste feito pelo desenvolvedor, responsável por validar o comportamento de unidades funcionais de código. Nesse contexto, entende-se como unidade funcional qualquer porção de código que através de algum estímulo seja capaz de gerar um comportamento esperado (na prática: métodos de uma classe). Um teste unitário não pode acessar outros componentes ou recursos externos (arquivos, bd, rede, web services, etc.).

#### Integração
Teste focado em verificar se a comunicação entre componentes / módulos da aplicação, e também recursos externos, estão interagindo entre si corretamente.

#### Funcional
É um teste do ponto de vista do usuário, se uma determinada funcionalidade está executando corretamente, produzindo o resultado ou comportamento desejado pelo usuário.

## Beneficios
- Detectar facilmente se mudanças violaram as regras
- É uma forma de documentação (comportamento e entradas/saídas esperadas)
- Redução de custos em manutenções, especialmente em fases avançadas
- Melhora design da solução, pois a aplicação testável precisa ser bem delineada

## TDD - Test Driven Development
- É um método de desenvolver software. Consiste em um desenvolvimento guiado pelos testes.

#### Princípios / vantagens:
- Foco nos requisitos
- Tende a melhorar o design do código, pois o código deverá ser testável
- Incrementos no projeto têm menos chance de quebrar a aplicação

## Processo básico:
1. Escreva o teste como esperado (naturalmente que ele ainda estará falhando)
2. Implemente o código necessário para que o teste passe
3. Refatore o código conforme necessidade

## Boas práticas e padrões
### Nomenclatura de um teste

- <AÇÃO> should <EFEITO> [when <CENÁRIO>]

### Padrão AAA
- **Arrange**: instancie os objetos necessários
- **Act**: execute as ações necessárias
- **Assert**: declare o que deveria acontecer (resultado esperado)

### Princípio da inversão de dependência (SOLID)
- Se uma classe A depende de uma instância da classe B, não tem como testar a classe A isoladamente. Na verdade nem seria um teste unitário.
- A inversão de controle ajuda na testabilidade, e garante o isolamento da unidade a ser testada.

### Independência / isolamento
- Um teste não pode depender de outros testes, nem da ordem de execução

### Cenário único
- O teste deve ter uma lógica simples, linear
- O teste deve testar apenas um cenário
- Não use condicionais e loops

### Previsibilidade
- O resultado de um teste deve ser sempre o mesmo para os mesmos dados
- Não faça o resultado depender de coisas que variam, tais como timestamp atual e valores aleatórios.

## JUnit
### Visão geral
- [https://junit.org/junit5]
- O primeiro passo é criar uma classe de testes
- A classe pode conter um ou mais métodos com a annotation @Test
- Um método @Test deve ser void
- O objetivo é que todos métodos @Test passem sem falhas
- O que vai definir se um método @Test passa ou não são as “assertions” deste método
- Se um ou mais falhas ocorrerem, estas são mostradas depois da execução do teste

# Annotations usadas nas classes de teste

```@SpringBootTest``` 
> Carrega o contexto da aplicação (teste de integração)

```
@SpringBootTest
@AutoConfigureMockMvc
```
> Carrega o contexto da aplicação (teste de integração & web)
Trata as requisições sem subir o servidor

```@WebMvcTest(Classe.class)```
> Carrega o contexto, porém somente da camada web (teste de unidade: controlador)

```@ExtendWith(SpringExtension.class)```
> Não carrega o contexto, mas permite usar os recursos do Spring com JUnit (teste de unidade: service/component)

```@DataJpaTest```
> Carrega somente os componentes relacionados ao Spring Data JPA. Cada teste é transacional e dá rollback ao final. (teste de unidade: repository)

## Fixtures
#### É uma forma de organizar melhor o código dos testes e evitar repetições.

## JUnit 5

```@BeforeAll```
> Preparação antes de todos testes da classe (método estático)

```@AfterAll```
> Preparação depois de todos testes da classe (método estático)

```@BeforeEach```
> Preparação antes de cada teste da classe 

```@AfterEach```
> Preparação depois de cada teste da classe 

## Mockito vs @MockBean

```
@Mock
private MyComp myComp;

ou 

myComp = Mockito.mock(MyComp.class);
```
> Usar quando a classe de teste não carrega o contexto da aplicação. É mais rápido e enxuto.
@ExtendWith

```
@MockBean
private MyComp myComp;
```
> Usar quando a classe de teste carrega o contexto da aplicação e precisa mockar algum bean do sistema.
@WebMvcTest
@SpringBootTest

# Tecnologias utilizadas

- Java
- Springboot
- Spring Data JPA 
- Hibernate
- Maven
- Postgresql
- Postman

# Autor

Roberto Okamura

https://www.linkedin.com/in/roberto-okamura-6a9b59b4/
