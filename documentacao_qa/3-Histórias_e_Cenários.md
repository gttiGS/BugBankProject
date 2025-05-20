![BugBank Logo](https://bugbank.netlify.app/_ipx/w_256,q_75/%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png?url=%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png&w=256&q=75)

# 📑 Cenários e Casos de Teste - BugBank

Este documento descreve as histórias de teste e os respectivos cenários de testes com base nos requisitos funcionais da aplicação BugBank.
---

## 📝 História 01 - Cadastro

## 🧾 Descrição da História
**Como** um novo usuário da plataforma BugBank  
**Quero** me cadastrar informando meus dados corretamente   
**Para** que eu possa criar uma conta bancária com ou sem saldo inicial e acessar os recursos da aplicação

## 🎯 Critérios de Aceitação
- Todos os campos obrigatórios devem ser validados (nome, email, senha e confirmação de senha)
- Deve exibir mensagens de erro específicas para cada campo não preenchido
- Senha e confirmação de senha devem ser iguais
- O usuário deve poder escolher se a conta será criada com saldo inicial ou não
- Ao marcar “Criar conta com saldo”, o saldo inicial deve ser de R$ 1.000,00
- Ao deixar desmarcada a opção, a conta deve iniciar com saldo de R$ 0,00
- Após cadastro bem-sucedido, deve exibir o número da conta criada

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
**Então** vejo a mensagem: "As senhas não são iguais."

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

## 🧾 Descrição da História
**Como** um usuário da plataforma BugBank  
**Quero** acessar minha conta por meio de email e senha válidos  
**Para** que eu possa visualizar meu saldo, realizar transferências e utilizar os recursos da aplicação com segurança

## 🎯 Critérios de aceitação

- O sistema deve impedir o acesso com campos obrigatórios vazios
- O sistema deve validar corretamente credenciais inválidas
- O login bem-sucedido deve redirecionar o usuário para a home
- O botão de logout deve encerrar a sessão e redirecionar para a tela de login

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

## 🧾 Descrição da História
**Como** um usuário autenticado da plataforma BugBank  
**Quero** realizar transferências para outras contas válidas  
**Para** que eu possa enviar valores com segurança e acompanhar o saldo da minha conta

## 🎯 Critérios de Aceitação
- Só é permitida transferência para contas válidas
- Campos de número e dígito da conta devem aceitar apenas números
- O campo descrição é obrigatório e deve ser validado
- O valor da transferência não pode ser igual ou menor que zero
- Caso o valor exceda o saldo da conta, a transferência deve ser negada com a mensagem apropriada
- Quando a transferência for bem-sucedida:
- O valor deve ser debitado da conta de origem
    - Deve ser exibida a mensagem: "Transferência realizada com sucesso"
    - O usuário deve ser redirecionado ao extrato

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

## 🧾 Descrição da História
**Como** um usuário autenticado da plataforma BugBank  
**Quero** visualizar o saldo e o histórico de transações da minha conta  
**Para** que eu possa acompanhar minha movimentação financeira com clareza e transparência

## 🎯 Critérios de Aceitação
- O saldo da conta deve ser exibido corretamente
- Se a conta foi criada com saldo, deve exibir R$ 1.000,00
- Se a conta foi criada sem saldo, deve exibir R$ 0,00
- Todas as transações realizadas devem ser exibidas no extrato
- Mostrar data da transação
- Indicar o tipo da transação (Abertura de conta, Transferência enviada ou recebida)
- Mostrar o comentário/descrição, ou o símbolo “-” caso não informado
- Formatação dos valores:
    - Valores de saída devem aparecer em vermelho e com sinal negativo (-)
    - Valores de entrada devem aparecer em verde

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
