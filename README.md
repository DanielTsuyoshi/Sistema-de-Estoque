# Sistema-de-Estoque

## Endpoints 

- Item 
    - [Cadastrar](#cadastrar-item)
    - Listar todos
    - Apagar
    - Atualizar
    - Pesquisar
- Usuário 
    - [Cadastrar](#cadastrar-usuário)
    - Apagar
    - Atualizar
    - Detalhes

### Cadastrar Item

`POST` /sistemaDeEstoque/api/item

| campo | tipo | obrigatório | descrição
|-------|------|:-----------:|----------
|nomeItem|texto|sim|nome dado ao item/produto que deseja inserir
|quantidadeId|decimal Positivo|sim|quantidade total que o usuário tem do item/produto
|categoriaId|texto|sim|o id de uma categoria previamente cadastrada
|marca|texto|sim|marca do o item/produto que será cadastrado 
|descricao|texto|sim|um texto sobre o produto



*Exemplo de requisição*

```json
{
    nomeItem: 'Mouse',
    quantidadeId: 8,
    categoriaId: 'eletronico',
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
    categoriaId: 'eletronico',
    valor: 148.99;
    marca: 'hyperX',
    descricao: 'Mouse HyperX Pulsefire Core RGB'
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|404| campos inválidos

...
### Pesquisar Item

`GET` /sistemaDeEstoque/api/item/{id}

*Exemplo de resposta*

```json
{
    nomeItem: 'Mouse',
    quantidadeId: 8,
    categoriaId: 'eletronico',
    valor: 148.99;
    marca: 'hyperX',
    descricao: 'Mouse HyperX Pulsefire Core RGB'
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|404| campos inválidos

...
### Cadastrar Usuário

`POST` /sistemaDeEstoque/api/usuário

| campo | tipo | obrigatório | descrição
|-------|------|:-----------:|----------
|nome|texto|sim|nome do usuário 
|email|texto|sim|email do usuário
|telefone|decimal 12numeros|sim|número do usuário com ddd
|cpf|decimal 11numeos|sim|cpf do usuário
|senha|texto|sim|senha do usuário
|confirmacao Senha|texto|sim|mesma senha do campo acima para confirmacao 


*Exemplo de requisição*

```json
{
    nome: 'João da Silva',
    email: 'joão@gmail.com',
    telefone: 1199999-9999,
    cpf: 999.999.999-99,
    senha: 'joao@123',
    cofirmacaoSenha: 'joao@123',
}
```

*Resposta*

| código | descrição
|--------|----------
|201| usuário foi cadastrado com sucesso
|400| campos inválidos

...
### Detalhes Usuário

`GET` /sistemaDeEstoque/api/usuário/{id}

*Exemplo de resposta*

```json
{
    nome: 'João da Silva',
    email: 'joão@gmail.com',
    telefone: 1199999-9999,
    cpf: 999.999.999-99,
    senha: 'joao@123',
    confirmacaoSenha: 'joao@123',
}
```

*Resposta*

| código | descrição
|--------|----------
|200| os dados foram retornados
|404| campos inválidos

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
|404| campos inválidos

