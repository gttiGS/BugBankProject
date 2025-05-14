![BugBank Logo](https://bugbank.netlify.app/_ipx/w_256,q_75/%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png?url=%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png&w=256&q=75)

# 🧪 Plano de Testes - BugBank

Este documento descreve o plano de testes para a aplicação **BugBank**, contemplando escopo, estratégia, critérios e recursos utilizados para garantir a qualidade da aplicação.

---

## 🎯 Objetivo

Garantir que as funcionalidades principais da aplicação BugBank estejam funcionando corretamente de acordo com os requisitos funcionais.

---

## 📦 Escopo dos Testes

### Funcionalidades contempladas:
- Login
- Cadastro de conta
- Transferência de valores
- Visualização de extrato

### Funcionalidades fora do escopo:
- Pagamento (em desenvolvimento)
- Saque (em desenvolvimento)
- Testes de performance e segurança

---

## 🧭 Estratégia de Testes

### Tipo de Testes:
- Testes funcionais
- Testes automatizados de interface
- Testes de regressão

### Ferramentas e Tecnologias:
- Java
- Selenium WebDriver
- JUnit 5
- Maven

---

## 📝 Casos de Teste

Os casos de teste estão baseados nos requisitos funcionais documentados e contemplam:

- Fluxos positivos (usuário faz tudo corretamente)
- Fluxos negativos (erros, campos vazios, dados inválidos)

---

## 📌 Critérios de Entrada

- Aplicação disponível e funcional em ambiente de testes
- Requisitos definidos e compreendidos
- Ferramentas de automação configuradas

## ✅ Critérios de Saída

- Todos os testes executados com sucesso
- Falhas documentadas e reportadas
- Verificação de cobertura mínima dos requisitos

---

## ⚙️ Ambiente de Testes

- Navegador: Google Chrome (última versão)
- Sistema operacional: Windows 11
- Aplicação hospedada em: https://bugbank.netlify.app
- Testes executados localmente via Selenium WebDriver

---

## 🧠 Riscos

| Risco | Impacto | Mitigação |
|-------|---------|-----------|
| Mudança de layout | Médio | Utilizar seletores robustos e revisões periódicas |
| Funcionalidades incompletas | Alto | Ajustar escopo e priorizar testes disponíveis |
| Dados não persistentes | Médio | Gerar dados durante o próprio teste |

---

## 📅 Cronograma e Estimativa

| Etapa                        | Tempo estimado |
|-----------------------------|----------------|
| Análise de requisitos       | 1 dia          |
| Escrita dos casos de teste  | 1 dia          |
| Configuração do ambiente    | 0,5 dia        |
| Implementação dos testes    | 2 dias         |
| Execução e documentação     | 1 dia          |
|-----------------------------|----------------|

---

## 👤 Responsável
**QA:** Gustavo Supranzetti

---
