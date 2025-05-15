# 🧪 Projeto de Testes Automatizados - BugBank

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
├── historias.feature (opcional)
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

- ✅ [Requisitos Funcionais](./documentacao_qa/requisitos_bugbank.md)
- 🧪 [Plano de Testes](./documentacao_qa/plano_testes_bugbank.md)
- 📑 [Histórias, Cenários e Casos de Teste](./documentacao_qa/cenarios_testes_bugbank.md)

---

## 👨‍💻 Autor

Gustavo Supranzetti – [@gttiGS](https://github.com/gttiGS)

---

Este projeto é voltado para aprendizado e prática em automação de testes com **Java + Selenium WebDriver + JUnit 5**.
