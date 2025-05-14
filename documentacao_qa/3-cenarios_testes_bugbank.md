![BugBank Logo](https://bugbank.netlify.app/_ipx/w_256,q_75/%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png?url=%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png&w=256&q=75)

# 📑 Cenários e Casos de Teste - BugBank

Este documento descreve as histórias de teste e os respectivos cenários de testes com base nos requisitos funcionais da aplicação BugBank.
---

## 📝 História 01 - Cadastro

### Cenário 1: Cadastro com todos os campos vazios
**Dado** que estou na tela de cadastro
**Quando** tento cadastrar sem preencher nada  
**Então** vejo mensagens de erro para todos os campos obrigatórios

### Cenário 2: Campo nome vazio
**Dado** que estou na tela de cadastro
**Quando** deixo o nome vazio  
**Então** vejo a mensagem: "Nome não pode ser vazio"

### Cenário 3: Campo email vazio
**Dado** que estou na tela de cadastro
**Quando** deixo o email vazio  
**Então** vejo a mensagem: "Email não pode ser vazio"

### Cenário 4: Campo senha vazio
**Dado** que estou na tela de cadastro
**Quando** deixo o campo senha vazio  
**Então** vejo a mensagem: "Senha não pode ser vazio"

### Cenário 5: Campo confirmação de senha vazio
**Dado** que estou na tela de cadastro
**Quando** deixo o campo de confirmação de senha vazio  
**Então** vejo a mensagem: "Confirmar senha não pode ser vazio"

### Cenário 6: Senha e confirmação diferentes
**Dado** que estou na tela de cadastro
**Quando** preencho senha e confirmação diferentes  
**Então** vejo uma mensagem de erro informando que as senhas devem ser iguais

### Cenário 7: Criar conta com saldo
**Dado** que estou na tela de cadastro
**Quando** marco a opção "Criar conta com saldo" e finalizo o cadastro  
**Então** a conta deve ser criada com saldo de R$ 1.000,00

### Cenário 8: Criar conta sem saldo
**Dado** que estou na tela de cadastro
**Quando** deixo desmarcada a opção "Criar conta com saldo"  
**Então** a conta deve ser criada com saldo de R$ 0,00

### Cenário 9: Cadastro bem-sucedido
**Dado** que estou na tela de cadastro
**Quando** preencho todos os dados corretamente  
**Então** a conta é criada com sucesso e exibe o número da conta

---

## 🔐 História 02 - Login

### Cenário 1: Login com campos vazios
**Dado** que estou na tela de login  
**Quando** clico no botão de login sem preencher email e senha  
**Então** vejo a mensagem "Usuário e senha precisam ser preenchidos"

### Cenário 2: Login com credenciais inválidas
**Dado** que estou na tela de login  
**Quando** preencho email e senha inválidos  
**Então** vejo a mensagem "Usuário ou senha inválido. Tente novamente ou verifique suas informações!"

### Cenário 3: Login com email válido e senha inválida
**Dado** que estou na tela de login  
**Quando** preencho email válido e senha inválida  
**Então** vejo a mensagem "Usuário ou senha inválido. Tente novamente ou verifique suas informações!"

### Cenário 4: Login com email inválido e senha válida
**Dado** que estou na tela de login  
**Quando** preencho email inválido e senha válida  
**Então** vejo a mensagem "Usuário ou senha inválido. Tente novamente ou verifique suas informações!"

### Cenário 5: Login com credenciais válidas
**Dado** que estou na tela de login  
**Quando** preencho email e senha com uma conta válida, corretamente  
**Então** sou redirecionado para a tela da página home

### Cenário 6: Logout
**Dado** que estou na tela de login  
**Quando** preencho email e senha com uma conta válida, corretamente 
**E** sou redirecionado para a página home 
**E** clico no botão "sair"
**Então** sou redirecionado para a página de login

---

## 💸 História 03 - Transferência

### Cenário 1: Transferência para conta inválida
**Dado** que estou autenticado
**Quando** tento transferir para uma conta que não existe  
**Então** vejo a mensagem: "Conta inválida ou inexistente"

### Cenário 2: Campos da conta com letras
**Dado** que estou autenticado
**Quando** tento inserir letras nos campos de número e dígito da conta  
**Então** vejo que apenas números são aceitos

### Cenário 3: Campo descrição vazio
**Dado** que estou autenticado
**Quando** tento transferir sem preencher a descrição  
**Então** vejo que descrição é um campo de preenchimento obrigatório

### Cenário 4: Valor igual ou menor que zero
**Dado** que estou autenticado
**Quando** tento transferir R$ 0,00 ou valor negativo  
**Então** vejo a seguinte mensagem de erro "Valor da transferência não pode ser 0 ou negativo"

### Cenário 5: Saldo insuficiente
**Dado** que estou autenticado
**Quando** tento transferir mais do que tenho de saldo  
**Então** a transferência é negada e é exibida a seguinte mensagem de erro "Você não tem saldo suficiente para essa transação"

### Cenário 6: Transferência com sucesso
**Dado** que estou autenticado
**Quando** preencho todos os dados corretamente e tenho saldo suficiente  
**Então** o valor é debitado da minha conta, vejo a mensagem de sucesso "Transferência realizada com sucesso" e sou redirecionado ao extrato

---

## 📄 História 04 - Extrato

### Cenário 1: Visualização de saldo - Conta criada com saldo
**Dado** que estou autenticado
**Quando** acesso o extrato  
**Então** vejo o saldo atual da conta

### Cenário 2: Visualização de saldo - Conta criada sem saldo
**Dado** que estou autenticado
**Quando** acesso o extrato  
**Então** vejo o saldo atual da conta

### Cenário 3: Visualização de transações
**Dado** que estou autenticado
**Quando** realizo transações (abertura, envio, recebimento)  
**Então** cada uma deve aparecer com data, tipo e comentário

### Cenário 4: Valor de saída
**Dado** que estou autenticado
**Quando** visualizo uma transferência enviada  
**Então** o valor aparece em vermelho e com sinal negativo (-)

### Cenário 5: Valor de entrada
**Dado** que estou autenticado
**Quando** visualizo uma transferência recebida  
**Então** o valor aparece em verde

### Cenário 6: Transação sem descrição
**Dado** que estou autenticado
**Quando** não é informado descrição  
**Então** vejo o símbolo “-” no lugar do comentário

---

## ⚠️ Funcionalidades em Desenvolvimento
- Pagamento
- Saque