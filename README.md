## Alunos
- nome
- sobrenome
- telefone

## Aula:
- varios alunos
- max 3 alunos no pilates
- max 4 alunos no funcional
- Funcional/Pilates

## Tipos de aulas: 
- Pilates: duração de uma hora
- Funcional: duração de 30 min

## Como rodar o projeto

1- iniciar serviço do docker caso necessario

```shell
sudo service docker start 
```

2- rodar o docker-compose up

```shell
sudo docker-compose up --build
```

3- rodar o spring
```shell
mvn spring-boot:run
```

## Acesso o s3 local:

http://localhost:9444/ui

## Documentação

The Swagger UI page will then be available at *http://server:port/context-path/swagger-ui.html* and the OpenAPI description will be available at the following url for json format: *http://server:port/context-path/v3/api-docs*

Exemplo link acesso local:

http://localhost:8081/swagger-ui/index.html#/