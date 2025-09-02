# Projeto cadastro de clientes -- estagio NeoApp

# Sobre o projeto

Este projeto é um MVP de uma API RESTful para o gerenciamento de clientes (pessoa física), desenvolvido como parte do processo seletivo para a vaga de Desenvolvedor Back-End Estagiário na NeoApp.

A aplicação foi construída como um sistema de back-office, onde todas as operações de cadastro são controladas por um usuário administrador. 
O sistema implementa um CRUD completo (Criar, Ler, Atualizar, Deletar) para a entidade de clientes, com foco em boas práticas de arquitetura, segurança e documentação.

# Funcionalidades Principais

Autenticação e Autorização: Acesso à API protegido por Spring Security, com autenticação via JWT (JSON Web Token). As operações são restritas a usuários com perfil de ADMIN.

CRUD de Clientes: Implementação completa das operações de POST, GET, PATCH e DELETE para o recurso de clientes.

Busca e Paginação: O endpoint de listagem (GET /clients) suporta paginação e permite a filtragem dinâmica por nome, CPF ou e-mail.

Cálculo de Idade: A API calcula e retorna automaticamente a idade do cliente com base na sua data de nascimento em todas as respostas.

Segurança de Dados:

As senhas dos usuários são armazenadas de forma segura no banco de dados utilizando hash BCrypt.

Dados sensíveis, como o CPF do cliente, são criptografados no banco de dados usando um JPA AttributeConverter com o algoritmo AES.


## Modelo conceitual
<img width="880" height="666" alt="Class Diagram0" src="https://github.com/user-attachments/assets/b54f7197-848e-4aad-8cd5-3c6279d27c0d" />

# Como Executar o Projeto Localmente (via Docker)
O projeto foi totalmente "containerizado" para garantir um ambiente de execução consistente e simplificado.

## Pré-requisitos:
Docker e Docker Compose instalados e em execução.


## Passos para a execução da API

```bash
# Clone este repositório 
git clone https://github.com/Clay1442/livraria-euliro.git

# Navegue até a pasta raiz do projeto
cd MVP-Project\backend

# Execute o Docker Compose:
# Este comando irá construir a imagem da sua aplicação, baixar a imagem do PostgreSQL e iniciar os dois contêineres.
docker-compose up --build

````
## Acesse a Documentação da API:
Com os contêineres rodando, abra o seu navegador e acesse a interface do Swagger:

http://localhost:8080/swagger-ui/index.html

# Como Testar a API (Swagger)
Com a API rodando através do Swagger (http://localhost:8080/swagger-ui/index.html#/)

Faça o primeiro post para se autenticar e começar a utilizar a API 

<img width="668" height="148" alt="image" src="https://github.com/user-attachments/assets/ca0f3711-f978-4ce7-bd35-e5b081e5d75c" />

Autenticação: Vá para o endpoint POST /auth/login, clique em "Try it out" e use as seguintes credenciais de administrador para obter um token JWT
```bash 
{
  "login": "admin",
  "password": "123456"
}

```


<img width="643" height="283" alt="image" src="https://github.com/user-attachments/assets/be868be5-0b6e-4c15-8670-248e11fe3752" />

Você recebera um json contendo o token para se autenticar, o copie 


<img width="664" height="154" alt="image" src="https://github.com/user-attachments/assets/141e0693-b213-4d0f-b8bf-d63503b90595" />

Va no top da pagina e clique  no componente "Authorize" 


<img width="256" height="83" alt="image" src="https://github.com/user-attachments/assets/868c45cf-b8e3-4efd-86f1-6799b59aa830" />

Cole seu token e aperte em Authorize 


<img width="711" height="299" alt="image" src="https://github.com/user-attachments/assets/3e8452f4-8594-4588-9ef3-771e8055b46a" />

# Teste o CRUD
Agora você pode testar todos os endpoints do recurso /clients. O banco de dados já foi populado com dados de exemplo para facilitar os testes.
obrigado pela oportunidade de mostrar um pouco do que sei e do que ainda estou aprendendo espero que gostem do resultado!

# Autor

Clay José Ribeiro Soares

www.linkedin.com/in/clayjose
