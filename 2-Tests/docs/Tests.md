## Sobre os testes no Springboot

### Annotations usadas nas classes

| Annotation                                | o que faz                                                                                                                                                              |
|-------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| @SpringBootTest                           | Carrega o contexto da aplicação (teste de integração)                                                                                                                  |
| @SpringBootTest <br/>@AutoConfigureMockMvc | Carrega o contexto da aplicação (teste de integração & web) <br/> Trata as requisições sem subir o servidor                                                            |
| @WebMvcTest(Classe.class)                 | Carrega o contexto, porém somente da camada web (teste de unidade: controlador)                                                                                        |
| @ExtendWith(SpringExtension.class)        | Não carrega o contexto, mas permite usar os recursos do Spring com JUnit (teste de unidade: service/component)                                                         |
| @DataJpaTest                              | Carrega somente os componentes relacionados ao Spring Data JPA. <br/> Cada teste é transacional e um rollback é executado no final.<br/>(Teste de unidade: repository) |