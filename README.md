<!--- # "Can be a image or a gift from the project pages" -->

# Localização

Esse é um projeto feito para estudos onde o objetivo era explorar algumas funcionalidades do spring como:

1. **Query Methods**
2. **Utilização de** `@Query` **e queries nativas**
3. **Paginação**
4. **Specifications**
5. **Projections**

## Tech Stack

<!--- # "Verify icons availability here https://github.com/tandpfun/skill-icons" -->

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,docker)](https://skillicons.dev)

## Executar projeto sem Docker

1. **Baixar projeto**
2. **No método** `run` **no arquivo:** `LocalizacaoApplication.java` **,adicionar os métodos que deseja rodar.**
3. **Executar método** `main`
4. **Verificar resultado no terminal**

## Executar projeto com Docker

1. **Baixar projeto**
2. **No método** `run` **no arquivo:** `LocalizacaoApplication.java` **,adicionar os métodos que deseja rodar.**
3. **Criar uma imagem docker utilizando Dockerfile do projeto:** `docker build -t johnwick/localizacao`
4. **Rodar um container utilizando imagem criada**: `docker run --name teste-localizacao johnwick/localizacao`
5. **Verificar resultado no terminal**

Obs: substituir o user johnwick pelo seu.

## License

This software is available under the following licenses:

- [MIT](https://rem.mit-license.org)
