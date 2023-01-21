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

### terminal 1 na raiz do projeto
1- iniciar serviço do docker caso necessario

```shell
sudo service docker start 
```

2- rodar o docker-compose up

```shell
sudo docker-compose up --build
```

### terminal 2 na raiz do projeto

1- rodar o spring
```shell
mvn spring-boot:run
```