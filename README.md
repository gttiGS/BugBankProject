![BugBank Logo](https://bugbank.netlify.app/_ipx/w_256,q_75/%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png?url=%2F_next%2Fstatic%2Fmedia%2Fbugbank.ede6fc83.png&w=256&q=75)

# Projeto de Testes Automatizados - BugBank

Este repositório contém o projeto de testes automatizados da aplicação **BugBank**, um sistema de banco digital fictício usado para fins de estudo e prática de QA.

## 📁 Estrutura do Projeto

```
📁 src
├── 📁 test
│   └── 📁 java
│       ├── 📁 pages         # Page Objects (representação das telas)
│       └── 📁 core          # Configurações e utilitários
│       └── 📁 tests         # Casos de teste automatizados
📁 documentacao_qa           # 📚 Documentação de QA do projeto
├── 1-Requisitos_Funcionais.md
├── 2-Plano_de_Testes.md
├── 3-Histórias_e_Cenários.md
📄 README.md                 # Este arquivo
📄 pom.xml                  # Gerenciador de dependências Maven
```

## 🚀 Como Executar os Testes

1. Clone o repositório:
```bash
git clone https://github.com/gttiGS/BugBankProject
cd BugBankProject
```

2. Execute os testes:
```bash
mvn test
```

> Certifique-se de ter o Java, Maven e um WebDriver configurado no seu ambiente.

---

## 📚 Documentação de QA

A documentação completa de QA pode ser encontrada na pasta [`documentacao_qa`](./documentacao_qa):

- ✅ [Requisitos Funcionais](https://github.com/gttiGS/BugBankProject/blob/main/documentacao_qa/1-Requisitos_Funcionais.md)
- 🧪 [Plano de Testes](https://github.com/gttiGS/BugBankProject/blob/main/documentacao_qa/2-Plano_de_Testes.md)
- 📑 [Histórias, Cenários e Casos de Teste](https://github.com/gttiGS/BugBankProject/blob/main/documentacao_qa/3-Hist%C3%B3rias_e_Cen%C3%A1rios.md)

---

## 👨‍💻 Autor

Gustavo Supranzetti – [@gttiGS](https://github.com/gttiGS)

---

Este projeto é voltado para aprendizado e prática em automação de testes com **Java + Selenium WebDriver + JUnit 5**.
