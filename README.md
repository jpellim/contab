# Contab
Serviço REST que cria e armazena lançamentos contábeis
e retorna algumas informações sobre eles. Um lançamento contábil é formado por
uma conta contábil (número e descrição), uma data de lançamento e um valor lançado
(positivo ou negativo).

## Getting Started
```
1 - Faça Pull do projeto
2 - Abra o projeto no eclipse
3 - Rodar a classe br.com.futureinnet.contab.Application
4 - Acessar o ARC ou Postman para testar os serviços
 
 
4.1 - Criar um novo lançamento contátil

      url:  POST http://localhost:8181/lancamento/
 
      Exemplo do json:

			{
				"contaContabil": {
				"id": "fb1eae09-0979-4491-901b-a9b518a3b88e",
				"codigoContaContabil": "23423432"
				},
				"dataLancamento": 1522600631137,
				"valorLancamento": 40
			}	  
			
4.2 - Buscar lançamento por ID

	  url:  GET http://localhost:8181/lancamento/da4938e5-08e7-47d7-bf54-db80281232a0

	  
4.3 - Buscar lançamento por Conta contábil

	  url:  GET http://localhost:8181/lancamento/?contaContabil=23423432	  

	  
4.4 - Buscar estatísticas

      url:  GET http://localhost:8181/lancamento/estatistica
	  
4.5 - Buscar estatísticas por Conta contábil

      url   GET http://localhost:8181/lancamento/estatistica/23423432

   
	  
```

### Prerequisites
* *Java 8*


## Author
* **Jane Pellim**
