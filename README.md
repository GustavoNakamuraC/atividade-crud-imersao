# Atividade de Imersão de Software.

## Informações para o H2
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=root
spring.datasource.password=root

link: [localhost:8080/h2-console](http://localhost:8080/h2-console)

## Documentação utilizando Swagger
link: [localhost:8080/swagger-ui](http://localhost:8080/swagger-ui/index.html)

### Requisição padrão para cadastro de personagem:
```
{
    "nome": "Nome 1",
    "nomeFantasia": "Nomudo",
    "classe": "GUERREIRO",
    "level": 10,
    "itensMagicos": [
        {                    
            "nome": "Espada", 
            "tipoItem": "ARMA",
            "forca": 2,
            "defesa": 0
        },
	{                    
            "nome": "Capacete", 
            "tipoItem": "ARMADURA",
            "forca": 0,
            "defesa": 3
        }
    ],
    "forca": 3,
    "defesa": 1
}
```
