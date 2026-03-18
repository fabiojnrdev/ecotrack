# 🌱 EcoTrack — Calculadora de Pegada de Carbono em Java

> Projeto didático progressivo: da linha de comando ao aplicativo completo,
> aprendendo Java na prática com um problema real.

---

## Sobre o projeto

EcoTrack é uma calculadora de emissões de CO₂ construída em Java ao longo de
6 etapas incrementais. Cada etapa introduz novos conceitos da linguagem e da
engenharia de software, sempre evoluindo o mesmo projeto — nunca jogando fora
o que foi feito antes.

O domínio escolhido é intencional: calcular a pegada de carbono de transportes,
alimentação e energia é um problema real, com dados reais (fatores do IPCC,
IATA, ANTP) e resultado tangível para o usuário.

---

## Roadmap

| Etapa | Tema | Conceitos principais | Status |
|-------|------|----------------------|--------|
| [01](./etapa-01/README.md) | Fundamentos no console | `Scanner`, variáveis, loops, métodos estáticos | ✅ Concluída |
| [02](./etapa-02/README.md) | Estruturação com POO | Herança, polimorfismo, classes abstratas, value objects | ✅ Concluída |
| 03 | Persistência de dados | `FileWriter`, `BufferedReader`, CSV, histórico em arquivo | 🔜 Próxima |
| 04 | Relatórios em PDF | iText / PDFBox, bibliotecas externas, Maven | — |
| 05 | Interface gráfica | JavaFX, FXML, event-driven programming | — |
| 06 | Escalabilidade e API | REST client, `HttpClient`, dados externos, open source | — |

---

## Estrutura do repositório

```
ecotrack/
├── README.md                  ← este arquivo
├── etapa-01/
│   ├── README.md              ← documentação completa da etapa
│   └── src/
│       └── EcoTrack.java      ← programa único no console
├── etapa-02/
│   ├── README.md
│   └── src/
│       ├── App.java           ← ponto de entrada
│       ├── Menu.java          ← interação com o usuário
│       ├── CalculadoraEmissao.java
│       ├── Viagem.java
│       ├── Trecho.java
│       ├── Transporte.java    ← classe abstrata base
│       ├── Carro.java
│       ├── Onibus.java
│       └── Aviao.java
├── etapa-03/                  ← em breve
├── etapa-04/                  ← em breve
├── etapa-05/                  ← em breve
└── etapa-06/                  ← em breve
```

---

## Como executar cada etapa

### Etapa 1
```bash
cd etapa-01
javac src/EcoTrack.java
java -cp src EcoTrack
```

### Etapa 2
```bash
cd etapa-02
javac src/*.java
java -cp src App
```

> A partir da Etapa 4 será introduzido o Maven para gerenciar dependências externas.
> Até lá, compilação manual com `javac` é suficiente.

---

## Pré-requisitos

| Ferramenta | Versão mínima | Verificação |
|------------|---------------|-------------|
| JDK | 11+ | `java -version` |
| IDE (opcional) | qualquer | — |

Recomendação: **IntelliJ IDEA Community** (gratuito) para melhor experiência
com Java, especialmente para as etapas de JavaFX e Maven.

---

## Fontes dos dados

Os fatores de emissão utilizados no projeto têm base em publicações científicas
e institucionais:

- **IPCC AR6 (2023)** — fatores de emissão por modal de transporte
- **IATA (2023)** — emissões de aviação incluindo Radiative Forcing Index
- **ANTP (2022)** — transporte urbano brasileiro
- **FAO** — absorção de CO₂ por árvore (~21,77 kg/ano)
- **SEEG Brasil** — dados de emissões por setor (usado na Etapa 6)