# Sistema-de-Estoque

## Endpoints 

- Item 
    - [Cadastrar](#cadastrar-item)
    - Listar todos
    - Apagar
    - Atualizar
    - Pesquisar
- Contas 
    - [Cadastrar](#cadastrar-conta)
    - Apagar
    - Atualizar
    - Detalhes
- Usuario
    - [Cadastrar]
    - Apagar
    - Atualizar
    - Detalhes 

### Cadastrar Item

`POST` /sistemaDeEstoque/api/item

| campo | tipo | obrigatório | descrição
|-------|------|:-----------:|----------
|nomeItem|texto|sim|nome dado ao item/produto que deseja inserir
|quantidadeId|inteiro|sim|quantidade total que o usuário tem do item/produto
|categoriaId|inteiro|sim|o id de uma categoria previamente cadastrada
|valor|decimal|sim|valor do produto
|marca|texto|sim|marca do o item/produto que será cadastrado 
|descricao|texto|sim|um texto sobre o produto



*Exemplo de requisição*

```json
{
    nomeItem: 'Mouse',
    quantidadeId: 8,
    categoriaId: 1,
    valor: 148.99;
    marca: 'hyperX',
    descricao: 'Mouse HyperX Pulsefire Core RGB'
}
```

*Resposta*

| código | descrição
|--------|----------
|201| o item foi cadastrado com sucesso
|400| campos inválidos

...
### Listas

`GET` /sistemaDeEstoque/api/item

*Exemplo de resposta*

```json
{
    nomeItem: 'Mouse',
    quantidadeId: 8,
    categoriaId: 1,
    valor: 148.99;
    marca: 'hyperX',
    descricao: 'Mouse HyperX Pulsefire Core RGB'
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|404| não foi encontrado item com esse nome

...
### Pesquisar Item

`GET` /sistemaDeEstoque/api/item/{id}

*Exemplo de resposta*

```json
{
    nomeItem: 'Mouse',
    quantidadeId: 8,
    categoriaId: 1,
    valor: 148.99;
    marca: 'hyperX',
    descricao: 'Mouse HyperX Pulsefire Core RGB'
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|400| campos inválidos

...
### Cadastrar Conta

`POST` /sistemaDeEstoque/api/conta

| campo | tipo | obrigatório | descrição
|-------|------|:-----------:|----------
|nome|texto|sim|nome da conta
|telefone|texto|sim|número do usuário com ddd
|cpf|texto|sim|cpf do usuário


*Exemplo de requisição*

```json
{
    nome: 'João da Silva',
    telefone: 1199999-9999,
    cpf: 999.999.999-99,
}
```

*Resposta*

| código | descrição
|--------|----------
|201| usuário foi cadastrado com sucesso
|400| campos inválidos

...
### Detalhes Conta

`GET` /sistemaDeEstoque/api/conta/{id}

*Exemplo de resposta*

```json
{
    nome: 'João da Silva',
    telefone: 1199999-9999,
    cpf: 999.999.999-99,
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|404| não foi encontrado o usuário com esse nome
...
### Cadastrar Usuario

`POST` /sistemaDeEstoque/api/usuario

| campo | tipo | obrigatório | descrição
|-------|------|:-----------:|----------
|nome|texto|sim|nome da conta
|email|texto|sim|email do usuário
|senha|texto|sim|senha do usuário


*Exemplo de requisição*

```json
{
    nome: 'João da Silva',
    email: 'joão@gmail.com',
    senha: 'joao@123',
}
```

*Resposta*

| código | descrição
|--------|----------
|201| usuário foi cadastrado com sucesso
|400| campos inválidos

...
### Detalhes Usuário

`GET` /sistemaDeEstoque/api/usuario/{id}

*Exemplo de resposta*

```json
{
    nome: 'João da Silva',
    email: 'joão@gmail.com',
    senha: 'joao@123',
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|404| não foi encontrado o usuário com esse nome

### Login 

`POST` /sistemaDeEstoque/api/login

| campo | tipo | obrigatório | descrição
|-------|------|-------------|----------
|email|texto|sim|email do usuário
|senha|texto|sim|senha do usuário


*Exemplo de requisição*

```json
{
    email: 'joão@gmail.com',
    senha: 'joao@123',
}
```

*Resposta*

| código | descrição
|--------|----------
|200| login bem sucedido
|400| campos inválidos

...
### Detalhes Login

`GET` /sistemaDeEstoque/api/login/{id}

*Exemplo de resposta*

```json
{
    email: 'joão@gmail.com',
    senha: 'joao@123',
}
```

*Resposta*

| código | descrição
|--------|----------
|200| login bem sucedido
|400| campos inválidos

