# Stoom Store API

Bem-vindo à API da Stoom Store! Esta API foi desenvolvida para gerenciar produtos de uma loja de e-commerce. Ela permite criar, ler, atualizar e excluir produtos, além de fornecer funcionalidades para buscar produtos por marca e categoria.

## Recursos

- CRUD de produtos
- Busca de produtos por marca
- Busca de produtos por categoria
- Inativação de produtos, marcas e categorias

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- Docker
- Swagger para documentação da API

## Como Executar

1. Clone o repositório para a sua máquina local.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn clean install` para construir o projeto e gerar o arquivo JAR.
4. Após a construção bem-sucedida do projeto, execute o comando `docker-compose up` para iniciar a aplicação.

## Documentação da API

A documentação da API está disponível através do Swagger. Você pode acessá-la em `http://localhost:8080/swagger-ui/` após iniciar a aplicação.

## Endpoints da API

Os endpoints da API estão disponíveis no caminho `/api/products`. Aqui estão alguns exemplos:

- `GET /api/products`: Retorna todos os produtos.
- `GET /api/products/{id}`: Retorna o produto com o ID especificado.
- `POST /api/products`: Cria um novo produto.
- `PUT /api/products/{id}`: Atualiza o produto com o ID especificado.
- `DELETE /api/products/{id}`: Exclui o produto com o ID especificado.
- `GET /api/products/brand/{brand}`: Retorna todos os produtos da marca especificada.
- `GET /api/products/category/{category}`: Retorna todos os produtos da categoria especificada.

Para mais detalhes sobre os endpoints e os modelos de dados, consulte a documentação da API no Swagger.

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Se você encontrar algum problema ou tiver alguma sugestão de melhoria, por favor, abra uma issue ou um pull request.

