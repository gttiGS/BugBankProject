![BugBank Logo](https://bugbank.netlify.app/_ipx/w_256,q_75/%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png?url=%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png&w=256&q=75)

# 📋 Requisitos da Aplicação BugBank

Este documento reúne os requisitos funcionais da aplicação **BugBank**, organizados por área funcional.

---

## 🔐 Login
- [ ] Email e Senha são campos obrigatórios.
- [ ] Tentativa de acesso sem preencher os campos obrigatórios deve exibir a mensagem: **"Usuário e senha precisam ser preenchidos"**.
- [ ] Não deve autorizar o acesso para usuários inválidos ou não cadastrados.
- [ ] Usuários válidos devem ser direcionados para a **home** após login com sucesso.

---

## 📝 Cadastro
- [ ] Os campos **Nome**, **Email**, **Senha** e **Confirmação de senha** são obrigatórios.
- [ ] Se o campo **Nome** estiver vazio, deve exibir: **"Nome não pode ser vazio"**.
- [ ] Se o campo **Email** estiver vazio, deve exibir: **"Email não pode ser vazio"**.
- [ ] Se o campo **Senha** estiver vazio, deve exibir: **"Senha não pode ser vazio"**.
- [ ] Se o campo **Confirmação de senha** estiver vazio, deve exibir: **"Confirmar senha não pode ser vazio"**.
- [ ] Senha e confirmação devem ser iguais.
- [ ] Marcar a opção **"Criar conta com saldo"** deve criar uma conta com saldo inicial de **R$ 1.000,00**.
- [ ] Deixar essa opção desmarcada deve criar uma conta com saldo inicial de **R$ 0,00**.
- [ ] Após cadastro bem-sucedido, deve exibir o **número da conta criada**.

---

## 💸 Transferência
- [ ] Só é permitida a transferência para **contas válidas**.
- [ ] Só é permitida a transferência se o **saldo for igual ou maior** que o valor a transferir.
- [ ] Tentativa de transferência para conta inválida deve exibir: **"Conta inválida ou inexistente"**.
- [ ] Os campos **número** e **dígito da conta** devem aceitar **apenas números**.
- [ ] O campo **descrição** é obrigatório.
- [ ] O **valor** da transferência **não pode ser igual ou menor que zero**.
- [ ] Ao realizar uma transferência com sucesso:
  - [ ] O valor deve ser debitado da conta.
  - [ ] A mensagem **"Transferência realizada com sucesso"** deve ser exibida.
  - [ ] O usuário deve ser redirecionado para o **extrato**.

---

## 📄 Extrato
- [ ] Deve exibir o **saldo disponível atual**.
- [ ] Cada transação deve mostrar:
  - [ ] **Data**
  - [ ] **Tipo da transação**: Abertura de conta / Transferência enviada / Transferência recebida
  - [ ] **Comentário** (ou símbolo **"-"** se não houver)
- [ ] Valores de saída devem aparecer em **vermelho** e com sinal **negativo (-)**.
- [ ] Valores de entrada devem aparecer em **verde**.

---

## ⚠️ Funcionalidades em Desenvolvimento
- Pagamento
- Saque
