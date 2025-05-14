![BugBank Logo](https://bugbank.netlify.app/_ipx/w_256,q_75/%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png?url=%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png&w=256&q=75)

# Projeto de Testes Automatizados - BugBank

Este repositório contém os testes automatizados desenvolvidos para a aplicação **BugBank**, uma plataforma de banco digital com funcionalidades de cadastro, login, transferência, extrato e outras operações bancárias.

## Tecnologias Utilizadas
- Java
- Selenium WebDriver
- JUnit 5
- Maven

## Casos de Teste Automatizados

### 1. Login
- [x] Valida obrigatoriedade dos campos Email e Senha
- [x] Valida mensagem "Usuário e senha precisam ser preenchidos"
- [x] Nega acesso com credenciais inválidas
- [x] Redireciona para home com credenciais válidas

### 2. Cadastro
- [x] Campos obrigatórios: Nome, Email, Senha, Confirmação de senha
- [x] Valida mensagens para campos obrigatórios vazios
- [x] Valida igualdade entre senha e confirmação
- [x] Cria conta com saldo (R$ 1.000,00)
- [x] Cria conta sem saldo (R$ 0,00)
- [x] Exibe número da conta após cadastro bem-sucedido

### 3. Transferência
- [x] Aceita apenas contas válidas
- [x] Permite transferência somente com saldo suficiente
- [x] Mensagem "Conta inválida ou inexistente" para contas inválidas
- [x] Campos de conta aceitam apenas números
- [x] Descrição obrigatória
- [x] Valor não pode ser ≤ 0
- [x] Debita valor e exibe "Transferência realizada com sucesso"
- [x] Redireciona para extrato após transferência

### 4. Extrato
- [x] Exibe saldo atual
- [x] Lista transações com data, tipo e valor
- [x] Valor de saída em vermelho e com sinal negativo
- [x] Valor de entrada em verde
- [x] Transações sem comentário exibem "-"

### 5. Funcionalidades em Desenvolvimento (Não testadas ainda)
- Pagamento
- Saque

## Como Executar os Testes

1. Clone este repositório:
```bash
git clone https://github.com/gttiGS/BugBankProject.git
cd BugBankProject
```

2. Execute os testes via terminal:
```bash
mvn test
```

## Contribuição
Pull requests são bem-vindos! Para melhorias ou sugestões, abra uma issue.

## Autor
Gustavo Supranzetti – [@gttiGS](https://github.com/gttiGS)

---

Este projeto foi desenvolvido para fins de aprendizado e treinamento em testes automatizados utilizando Selenium WebDriver e JUnit.
